package net.XenceV.pondcraft.entity;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.XenceV.pondcraft.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class AsianDragonEntity extends AbstractVillager {

    private static final int NUMBER_OF_TRADE_OFFERS = 5;


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

    //Trade section
    public InteractionResult mobInteract(Player p_35856_, InteractionHand p_35857_) {
        ItemStack itemstack = p_35856_.getItemInHand(p_35857_);
        if (!itemstack.is(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby()) {
            if (p_35857_ == InteractionHand.MAIN_HAND) {
                p_35856_.awardStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            } else {
                if (!this.level.isClientSide) {
                    this.setTradingPlayer(p_35856_);
                    this.openTradingScreen(p_35856_, this.getDisplayName(), 1);
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        } else {
            return super.mobInteract(p_35856_, p_35857_);
        }
    }

    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> ASIAN_DRAGON_TRADES =
            toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
                    new ItemsAndItemsToStrenghtPearl(Items.ENCHANTED_GOLDEN_APPLE, 1, Items.NETHER_STAR, 1, 1, 5),
                    new ItemsAndItemsToExplorationPearl(Items.RABBIT_FOOT, 1, Items.NETHER_STAR, 1, 1, 5)},
                    2,new VillagerTrades.ItemListing[]{
                            new ItemsAndItemsToResistancePearl(Items.TURTLE_HELMET, 1, Items.NETHER_STAR, 1, 1, 5)
                    }));
                    //new VillagerTrades.ItemsAndEmeraldsToItems(Items.COD, 6, Items.COOKED_COD, 6, 16, 1),

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_35631_) {
        return new Int2ObjectOpenHashMap<>(p_35631_);
    }

    static class ItemsAndItemsToStrenghtPearl implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsAndItemsToStrenghtPearl(ItemLike item1, int count1, ItemLike item2, int count2, int maxUses, int villagerXp, ItemStack fromItem1) {
            this(item1, count1, item2, count2, maxUses, villagerXp);
        }

        ItemsAndItemsToStrenghtPearl(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), new ItemStack(ModItems.DRAGON_PEARL_STRENGHT.get(), 1), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsAndItemsToExplorationPearl implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsAndItemsToExplorationPearl(ItemLike item1, int count1, ItemLike item2, int count2, int maxUses, int villagerXp, ItemStack fromItem1) {
            this(item1, count1, item2, count2, maxUses, villagerXp);
        }

        ItemsAndItemsToExplorationPearl(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), new ItemStack(ModItems.DRAGON_PEARL_EXPLORATION.get(), 1), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsAndItemsToResistancePearl implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsAndItemsToResistancePearl(ItemLike item1, int count1, ItemLike item2, int count2, int maxUses, int villagerXp, ItemStack fromItem1) {
            this(item1, count1, item2, count2, maxUses, villagerXp);
        }

        ItemsAndItemsToResistancePearl(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), new ItemStack(ModItems.DRAGON_PEARL_RESISTANCE.get(), 1), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    protected void updateTrades() {

        VillagerTrades.ItemListing[] avillagertrades$itemlisting = ASIAN_DRAGON_TRADES.get(1);
        VillagerTrades.ItemListing[] avillagertrades$itemlisting1 = ASIAN_DRAGON_TRADES.get(2);
        if (avillagertrades$itemlisting != null && avillagertrades$itemlisting1 != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addOffersFromItemListings(merchantoffers, avillagertrades$itemlisting, 5);
            int i = this.random.nextInt(avillagertrades$itemlisting1.length);
            VillagerTrades.ItemListing villagertrades$itemlisting = avillagertrades$itemlisting1[i];
            MerchantOffer merchantoffer = villagertrades$itemlisting.getOffer(this, this.random);
            if (merchantoffer != null) {
                merchantoffers.add(merchantoffer);
            }

        }
    }

    protected void rewardTradeXp(MerchantOffer p_35859_) {
        if (p_35859_.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }

    }
}
