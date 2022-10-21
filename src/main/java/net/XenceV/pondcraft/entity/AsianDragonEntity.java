package net.XenceV.pondcraft.entity;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.XenceV.pondcraft.item.ModItems;
import net.minecraft.commands.arguments.NbtTagArgument;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Objects;

public class AsianDragonEntity extends AbstractVillager {

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

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    public static AttributeSupplier.Builder getAsianDragonAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 500.0D);
    }

    //Trade section
    public boolean showProgressBar() {
        return false;
    }

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

    //Merci à remykingV2 pour la conv sur League of Legend que j'ai eu lors de la création des trades
    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> ASIAN_DRAGON_TRADES =
            toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
                            new ItemsAndItemsToItems(Items.WITHER_SKELETON_SKULL, 1, Items.POPPY, 20, Items.WITHER_ROSE, 20, 100, 5),
                    },
                    2,new VillagerTrades.ItemListing[]{
                            new ItemsAndItemsToExplorationPearl(Items.RABBIT_FOOT, 1, Items.NETHER_STAR, 1, 1, 5),
                            new ItemsAndItemsToStrenghtPearl(Items.ENCHANTED_GOLDEN_APPLE, 1, Items.NETHER_STAR, 1, 1, 5),
                            new ItemsAndItemsToResistancePearl(Items.TURTLE_HELMET, 1, Items.NETHER_STAR, 1, 1, 5)
                    },
                    3,new VillagerTrades.ItemListing[]{
                            new ItemsAndItemsToEnchantedSword(Items.DIAMOND_SWORD, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.DIAMOND_SWORD, 1, 1, 5),
                            new ItemsAndItemsToEnchantedSword(Items.DIAMOND_AXE, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.DIAMOND_AXE, 1, 1, 5),
                            new ItemsAndItemsToEnchantedTrident(Items.TRIDENT, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.TRIDENT, 1, 1, 5),
                            new ItemsAndItemsToEnchantedBow(Items.BOW, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.BOW, 1, 1, 5),
                            new ItemsAndItemsToEnchantedCrossbow(Items.CROSSBOW, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.CROSSBOW, 1, 1, 5)
                    },
                    4,new VillagerTrades.ItemListing[]{
                            new ItemsAndItemsToEnchantedArmor(Items.DIAMOND_HELMET, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.DIAMOND_HELMET, 1, 1, 5),
                            new ItemsAndItemsToEnchantedArmor(Items.DIAMOND_CHESTPLATE, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.DIAMOND_CHESTPLATE, 1, 1, 5),
                            new ItemsAndItemsToEnchantedArmor(Items.DIAMOND_LEGGINGS, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.DIAMOND_LEGGINGS, 1, 1, 5),
                            new ItemsAndItemsToEnchantedArmor(Items.DIAMOND_BOOTS, 1, Items.ENCHANTED_GOLDEN_APPLE, 1, Items.DIAMOND_BOOTS, 1, 1, 5)
                    }));



    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_35631_) {
        return new Int2ObjectOpenHashMap<>(p_35631_);
    }

    protected void updateTrades() {
        VillagerTrades.ItemListing[] avillagertrades$itemlisting = ASIAN_DRAGON_TRADES.get(1);
        VillagerTrades.ItemListing[] avillagertrades$itemlisting1 = ASIAN_DRAGON_TRADES.get(2);
        VillagerTrades.ItemListing[] avillagertrades$itemlisting2 = ASIAN_DRAGON_TRADES.get(3);
        VillagerTrades.ItemListing[] avillagertrades$itemlisting3 = ASIAN_DRAGON_TRADES.get(4);

        if (avillagertrades$itemlisting != null && avillagertrades$itemlisting1 != null && avillagertrades$itemlisting2 != null && avillagertrades$itemlisting3 != null) {
            MerchantOffers merchantoffers = this.getOffers();

            this.addOffersFromItemListings(merchantoffers, avillagertrades$itemlisting, 1);

            int i = this.random.nextInt(avillagertrades$itemlisting1.length);
            VillagerTrades.ItemListing villagertrades$itemlisting1 = avillagertrades$itemlisting1[i];
            MerchantOffer merchantoffer1 = villagertrades$itemlisting1.getOffer(this, this.random);

            i = this.random.nextInt(avillagertrades$itemlisting2.length);
            VillagerTrades.ItemListing villagertrades$itemlisting2 = avillagertrades$itemlisting2[i];
            MerchantOffer merchantoffer2 = villagertrades$itemlisting2.getOffer(this, this.random);

            i = this.random.nextInt(avillagertrades$itemlisting3.length);
            VillagerTrades.ItemListing villagertrades$itemlisting3 = avillagertrades$itemlisting3[i];
            MerchantOffer merchantoffer3 = villagertrades$itemlisting3.getOffer(this, this.random);

            if (merchantoffer1 != null && merchantoffer2 != null && merchantoffer3 != null) {
                merchantoffers.add(merchantoffer1);
                merchantoffers.add(merchantoffer2);
                merchantoffers.add(merchantoffer3);
            }
        }
    }

    protected void rewardTradeXp(MerchantOffer p_35859_) {
        if (p_35859_.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }

    }

    //Trade method
    static class ItemsAndItemsToEnchantedSword implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final ItemStack fromResultItem;
        private final int fromCountResult;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;


        ItemsAndItemsToEnchantedSword(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, ItemLike fromResultItem, int fromCountResult, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.fromResultItem = new ItemStack(fromResultItem);
            this.fromCountResult = fromCountResult;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            ItemStack itemstack = new ItemStack(this.fromResultItem.getItem(), this.fromCountResult);
            // Sharpness / Smite / Bane of Arthropode
            itemstack.enchant(Objects.requireNonNull(Enchantments.SHARPNESS), 5);
            itemstack.enchant(Objects.requireNonNull(Enchantments.SMITE), 5);
            itemstack.enchant(Objects.requireNonNull(Enchantments.BANE_OF_ARTHROPODS), 5);

            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsAndItemsToEnchantedTrident implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final ItemStack fromResultItem;
        private final int fromCountResult;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;


        ItemsAndItemsToEnchantedTrident(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, ItemLike fromResultItem, int fromCountResult, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.fromResultItem = new ItemStack(fromResultItem);
            this.fromCountResult = fromCountResult;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            ItemStack itemstack = new ItemStack(this.fromResultItem.getItem(), this.fromCountResult);
            // Sharpness / Impaling
            itemstack.enchant(Objects.requireNonNull(Enchantments.SHARPNESS), 5);
            itemstack.enchant(Objects.requireNonNull(Enchantments.IMPALING), 5);

            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsAndItemsToEnchantedArmor implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final ItemStack fromResultItem;
        private final int fromCountResult;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;


        ItemsAndItemsToEnchantedArmor(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, ItemLike fromResultItem, int fromCountResult, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.fromResultItem = new ItemStack(fromResultItem);
            this.fromCountResult = fromCountResult;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            ItemStack itemstack = new ItemStack(this.fromResultItem.getItem(), this.fromCountResult);
            //Protection / Projectile Protection / Fire Protection / Blast Protection
            itemstack.enchant(Objects.requireNonNull(Enchantments.ALL_DAMAGE_PROTECTION), 4);
            itemstack.enchant(Objects.requireNonNull(Enchantments.PROJECTILE_PROTECTION), 4);
            itemstack.enchant(Objects.requireNonNull(Enchantments.FIRE_PROTECTION), 4);
            itemstack.enchant(Objects.requireNonNull(Enchantments.BLAST_PROTECTION), 4);
            //itemstack, this.fromCountResult
            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsAndItemsToEnchantedBow implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final ItemStack fromResultItem;
        private final int fromCountResult;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;


        ItemsAndItemsToEnchantedBow(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, ItemLike fromResultItem, int fromCountResult, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.fromResultItem = new ItemStack(fromResultItem);
            this.fromCountResult = fromCountResult;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            ItemStack itemstack = new ItemStack(this.fromResultItem.getItem(), this.fromCountResult);
            //Infinity / Mending
            itemstack.enchant(Objects.requireNonNull(Enchantments.INFINITY_ARROWS), 1);
            itemstack.enchant(Objects.requireNonNull(Enchantments.MENDING), 1);
            //itemstack, this.fromCountResult
            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsAndItemsToEnchantedCrossbow implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final ItemStack fromResultItem;
        private final int fromCountResult;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;


        ItemsAndItemsToEnchantedCrossbow(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, ItemLike fromResultItem, int fromCountResult, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.fromResultItem = new ItemStack(fromResultItem);
            this.fromCountResult = fromCountResult;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            ItemStack itemstack = new ItemStack(this.fromResultItem.getItem(), this.fromCountResult);
            //Piercing / Multishot
            itemstack.enchant(Objects.requireNonNull(Enchantments.PIERCING), 4);
            itemstack.enchant(Objects.requireNonNull(Enchantments.MULTISHOT), 1);
            //itemstack, this.fromCountResult
            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
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

    static class ItemsAndItemsToItems implements VillagerTrades.ItemListing {
        private final ItemStack fromItem1;
        private final int fromCount1;
        private final ItemStack fromItem2;
        private final int fromCount2;
        private final ItemStack fromResultItem;
        private final int fromCountResult;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsAndItemsToItems(ItemLike item1, int count1, ItemLike item2, int count2, ItemLike fromResultItem, int fromCountResult, int maxUses, int villagerXp, ItemStack fromItem1) {
            this(item1, count1, item2, count2, fromResultItem, fromCountResult, maxUses, villagerXp);
        }

        ItemsAndItemsToItems(ItemLike fromItem1, int fromCount1, ItemLike fromItem2, int fromCount2, ItemLike fromResultItem, int fromCountResult, int maxUses, int villagerXp) {
            this.fromItem1 = new ItemStack(fromItem1);
            this.fromCount1 = fromCount1;
            this.fromItem2 = new ItemStack(fromItem2);
            this.fromCount2 = fromCount2;
            this.fromResultItem = new ItemStack(fromResultItem);
            this.fromCountResult = fromCountResult;
            this.maxUses = maxUses;
            this.villagerXp = villagerXp;
            this.priceMultiplier = 0.05f;
        }


        @Nullable
        public MerchantOffer getOffer(Entity p_219696_, RandomSource p_219697_) {
            return new MerchantOffer(new ItemStack(this.fromItem1.getItem(), this.fromCount1), new ItemStack(this.fromItem2.getItem(), this.fromCount2), new ItemStack(this.fromResultItem.getItem(), this.fromCountResult), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}