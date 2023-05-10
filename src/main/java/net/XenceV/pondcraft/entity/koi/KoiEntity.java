package net.XenceV.pondcraft.entity.koi;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.ModEntityTypes;
import net.XenceV.pondcraft.entity.fry.FryEntity;
import net.XenceV.pondcraft.item.ModItems;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.AnimalMakeLove;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.fluids.FluidType;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

import static net.XenceV.pondcraft.entity.koi.KoiEntityRenderer.TEXTUREMAGICARP;

public class KoiEntity extends AbstractSchoolingFish implements IAnimatable {

    public static final Ingredient TEMPTATION_ITEM = Ingredient.of(ModItems.KOI_PALLET.get());
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(KoiEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(KoiEntity.class, EntityDataSerializers.BOOLEAN);
    private AnimationFactory factory = new AnimationFactory(this);
    private int inLove;

    public KoiEntity(EntityType<? extends AbstractSchoolingFish> type, Level level) {
        super(type, level);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(6, new TemptGoal(this, 1.1D, TEMPTATION_ITEM, false));

    }

    public static AttributeSupplier.Builder getKoiAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.KOI_FISH_BUCKET.get());
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
        this.setFromBucket(tag.getBoolean("FromBucket"));
    }

    // Variants
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty,
                                        MobSpawnType spawnReason, @Nullable SpawnGroupData entityData,
                                        @Nullable CompoundTag entityNbt) {
        KoiVariant variant;
        /*
        if(Math.random() < 0.00083333333) {
            //Spawn Magicarp
            variant = KoiVariant.MAGICARP;
            setVariant(variant);
            if(Math.random() < 0.00024414062) {
                //Spawn Magicarp Shiny
                variant = KoiVariant.SHINY_MAGICARP;
                setVariant(variant);
            }

        } else {
            variant = Util.getRandom(KoiVariant.values(), this.random);
        }
        */
        variant = Util.getRandom(KoiVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.isFromBucket() && !this.hasCustomName();
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.isFromBucket();
    }

    private boolean isFromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean p_203706_1_) {
        this.entityData.set(FROM_BUCKET, p_203706_1_);
    }

    private void setVariant(KoiVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    public KoiVariant getVariant() {
        return KoiVariant.byId(this.getTypeVariant() & 255);
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Override
    public LivingEntity self() {
        return super.self();
    }

    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return super.canSwimInFluidType(type);
    }

    @Override
    public void sinkInFluid(FluidType type) {
        super.sinkInFluid(type);
    }

    public static boolean canSpawn(EntityType<KoiEntity> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return checkSurfaceWaterAnimalSpawnRules(entityType, levelAccessor, mobSpawnType, blockPos, randomSource);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.koi.swim", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.koi.swim", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public boolean isFood(ItemStack p_218535_) {
        return TEMPTATION_ITEM.test(p_218535_);
    }

    /*
    public FryEntity getBreedOffspring(ServerLevel p_149533_, KoiEntity p_149534_) {

        KoiEntity koi = p_149534_;
        EntityType<FryEntity> fryEntityType = ModEntityTypes.FRY.get();
        FryEntity fryEntity = fryEntityType.create(this.level);

        int i = this.random.nextInt(9);
        KoiVariant variant;
        if (i < 4) {
            variant = this.getVariant();
        } else if (i < 8) {
            variant = koi.getVariant();
        } else {
            variant = Util.getRandom(KoiVariant.values(), this.random);;
        }

        fryEntity.setVariant(variant);
        return fryEntity;
    }

    public void spawnChildFromBreeding(ServerLevel p_27564_, KoiEntity p_27565_) {
        FryEntity fryEntity = this.getBreedOffspring(p_27564_, p_27565_);
        final net.minecraftforge.event.entity.living.BabyEntitySpawnEvent event = new net.minecraftforge.event.entity.living.BabyEntitySpawnEvent(this, p_27565_, ageablemob);
        final boolean cancelled = net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        fryEntity = event.getChild();
        if (cancelled) {
            //Reset the "inLove" state for the animals
            this.resetLove();
            p_27565_.resetLove();
            return;
        }
        if (fryEntity != null) {
            ServerPlayer serverplayer = this.getLoveCause();
            if (serverplayer == null && p_27565_.getLoveCause() != null) {
                serverplayer = p_27565_.getLoveCause();
            }

            if (serverplayer != null) {
                serverplayer.awardStat(Stats.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, this, p_27565_, fryEntity);
            }

            this.resetLove();
            p_27565_.resetLove();
            fryEntity.setBaby(true);
            fryEntity.moveTo(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            p_27564_.addFreshEntityWithPassengers(fryEntity);
            p_27564_.broadcastEntityEvent(this, (byte)18);
            if (p_27564_.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                p_27564_.addFreshEntity(new ExperienceOrb(p_27564_, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
            }

        }
    }

    public void resetLove() {
        this.inLove = 0;
    }
    */
}