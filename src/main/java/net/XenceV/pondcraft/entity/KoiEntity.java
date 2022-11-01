package net.XenceV.pondcraft.entity;

import net.XenceV.pondcraft.entity.variant.KoiVariant;
import net.XenceV.pondcraft.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Markings;
import net.minecraft.world.entity.animal.horse.Variant;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.fluids.FluidType;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;

public class KoiEntity extends AbstractSchoolingFish /*implements IAnimatable*/ {

    //public AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(KoiEntity.class, EntityDataSerializers.INT);

    public KoiEntity(EntityType<? extends AbstractSchoolingFish> type, Level level) {
        super(type, level);
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

    /*
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.koi.swim", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.koi.idle", true));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    */

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    // Variants
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty,
                                        MobSpawnType spawnReason, @Nullable SpawnGroupData entityData,
                                        @Nullable CompoundTag entityNbt) {
        KoiVariant variant = Util.getRandom(KoiVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt);
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

    public static boolean canSpawn(EntityType<KoiEntity> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return checkSurfaceWaterAnimalSpawnRules(entityType, levelAccessor, mobSpawnType, blockPos, randomSource);
    }
}