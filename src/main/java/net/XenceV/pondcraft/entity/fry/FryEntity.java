package net.XenceV.pondcraft.entity.fry;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import javax.annotation.Nullable;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.ModEntityTypes;
import net.XenceV.pondcraft.entity.koi.KoiEntity;
import net.XenceV.pondcraft.entity.koi.KoiVariant;
import net.XenceV.pondcraft.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.frog.TadpoleAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static net.XenceV.pondcraft.entity.ModEntityTypes.KOI;

public class FryEntity extends AbstractFish implements IAnimatable {
    @VisibleForTesting
    public static int ticksToBeKoi = Math.abs(-24000);
    public static float HITBOX_WIDTH = 0.3F;
    public static float HITBOX_HEIGHT = 0.2F;
    private int age;
    protected static final ImmutableList<SensorType<? extends Sensor<? super FryEntity>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.HURT_BY, SensorType.FROG_TEMPTATIONS);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.NEAREST_VISIBLE_ADULT, MemoryModuleType.TEMPTATION_COOLDOWN_TICKS, MemoryModuleType.IS_TEMPTED, MemoryModuleType.TEMPTING_PLAYER, MemoryModuleType.BREED_TARGET, MemoryModuleType.IS_PANICKING);
    private AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(KoiEntity.class, EntityDataSerializers.INT);

    public FryEntity(EntityType<? extends AbstractFish> p_218686_, Level p_218687_) {
        super(p_218686_, p_218687_);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    protected PathNavigation createNavigation(Level p_218694_) {
        return new WaterBoundPathNavigation(this, p_218694_);
    }

    protected Brain.Provider<FryEntity> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    protected Brain<?> makeBrain(Dynamic<?> p_218696_) {
        return FryAi.makeBrain(this.brainProvider().makeBrain(p_218696_));
    }

    public Brain<FryEntity> getBrain() {
        return (Brain<FryEntity>)super.getBrain();
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.TADPOLE_FLOP;
    }

    protected void customServerAiStep() {
        this.level.getProfiler().push("tadpoleBrain");
        this.getBrain().tick((ServerLevel)this.level, this);
        this.level.getProfiler().pop();
        this.level.getProfiler().push("tadpoleActivityUpdate");
        FryAi.updateActivity(this);
        this.level.getProfiler().pop();
        super.customServerAiStep();
    }

    public static AttributeSupplier.Builder getFryAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D);
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.setAge(this.age + 1);
        }

    }

    public void addAdditionalSaveData(CompoundTag p_218709_) {
        super.addAdditionalSaveData(p_218709_);
        p_218709_.putInt("Age", this.age);
    }

    public void readAdditionalSaveData(CompoundTag p_218698_) {
        super.readAdditionalSaveData(p_218698_);
        this.setAge(p_218698_.getInt("Age"));
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource p_218713_) {
        return SoundEvents.TADPOLE_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.TADPOLE_DEATH;
    }

    public InteractionResult mobInteract(Player p_218703_, InteractionHand p_218704_) {
        ItemStack itemstack = p_218703_.getItemInHand(p_218704_);
        if (this.isFood(itemstack)) {
            this.feed(p_218703_, itemstack);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return Bucketable.bucketMobPickup(p_218703_, p_218704_, this).orElse(super.mobInteract(p_218703_, p_218704_));
        }
    }

    protected void sendDebugPackets() {
        super.sendDebugPackets();
        DebugPackets.sendEntityBrain(this);
    }

    public boolean fromBucket() {
        return true;
    }

    public void setFromBucket(boolean p_218732_) {
    }

    public void saveToBucketTag(ItemStack p_218725_) {
        Bucketable.saveDefaultDataToBucketTag(this, p_218725_);
        CompoundTag compoundtag = p_218725_.getOrCreateTag();
        compoundtag.putInt("Age", this.getAge());
    }

    public void loadFromBucketTag(CompoundTag p_218715_) {
        Bucketable.loadDefaultDataFromBucketTag(this, p_218715_);
        if (p_218715_.contains("Age")) {
            this.setAge(p_218715_.getInt("Age"));
        }

    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.FRY_BUCKET.get());
    }

    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_TADPOLE;
    }

    private boolean isFood(ItemStack p_218727_) {
        return KoiEntity.TEMPTATION_ITEM.test(p_218727_);
    }

    private void feed(Player p_218691_, ItemStack p_218692_) {
        this.usePlayerItem(p_218691_, p_218692_);
        this.ageUp(AgeableMob.getSpeedUpSecondsWhenFeeding(this.getTicksLeftUntilAdult()));
        this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
    }

    private void usePlayerItem(Player p_218706_, ItemStack p_218707_) {
        if (!p_218706_.getAbilities().instabuild) {
            p_218707_.shrink(1);
        }

    }

    private int getAge() {
        return this.age;
    }

    private void ageUp(int p_218701_) {
        this.setAge(this.age + p_218701_ * 20);
    }

    private void setAge(int p_218711_) {
        this.age = p_218711_;
        if (this.age >= ticksToBeKoi) {
            this.ageUp();
        }

    }

    private void ageUp() {
        Level $$1 = this.level;
        if ($$1 instanceof ServerLevel serverlevel) {
            EntityType<KoiEntity> koiEntityType = ModEntityTypes.KOI.get();
            KoiEntity koiEntity = koiEntityType.create(this.level);
            koiEntity.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            koiEntity.finalizeSpawn(serverlevel, this.level.getCurrentDifficultyAt(koiEntity.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData)null, (CompoundTag)null);
            koiEntity.setNoAi(this.isNoAi());
            if (this.hasCustomName()) {
                koiEntity.setCustomName(this.getCustomName());
                koiEntity.setCustomNameVisible(this.isCustomNameVisible());
            }

            koiEntity.setPersistenceRequired();
            this.playSound(SoundEvents.TADPOLE_GROW_UP, 0.15F, 1.0F);
            serverlevel.addFreshEntityWithPassengers(koiEntity);
            this.discard();
        }

    }

    private int getTicksLeftUntilAdult() {
        return Math.max(0, ticksToBeKoi - this.age);
    }

    public boolean shouldDropExperience() {
        return false;
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void setVariant(KoiVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    public KoiVariant getVariant() {
        return KoiVariant.byId(this.getTypeVariant() & 255);
    }
}