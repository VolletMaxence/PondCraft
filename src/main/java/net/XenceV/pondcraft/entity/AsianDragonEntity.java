package net.XenceV.pondcraft.entity;

import net.XenceV.pondcraft.entity.variant.KoiVariant;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class AsianDragonEntity extends Animal {


    //public AnimationFactory factory = new AnimationFactory(this);

    public AsianDragonEntity(EntityType/*<? extends AbstractSchoolingFish>*/ type, Level level) {
        super(type, level);
    }

    /*
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty,
                                        MobSpawnType spawnReason, @Nullable SpawnGroupData entityData,
                                        @Nullable CompoundTag entityNbt) {
        KoiVariant variant = Util.getRandom(KoiVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt);
    }
    */

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    public static AttributeSupplier.Builder getAsianDragonAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 500.0D);
    }

}
