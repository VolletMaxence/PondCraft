package net.XenceV.pondcraft.block;

import net.XenceV.pondcraft.PondCraft;
import net.XenceV.pondcraft.entity.AsianDragonEntity;
import net.XenceV.pondcraft.entity.AsianDragonEntityModel;
import net.XenceV.pondcraft.entity.ModEntityTypes;
import net.XenceV.pondcraft.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.RegistryObject;

public class DragonStatueBlock extends Block {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public DragonStatueBlock(Properties p_49795_) {

        super(p_49795_);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockpos,
                                 Player player, InteractionHand hand, BlockHitResult result){
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND && player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ModItems.EMERALD_DRAGON_EYE.get() && state.getValue(ACTIVATED))
        {
            Entity entityToSpawn = new AsianDragonEntity(ModEntityTypes.ASIAN_DRAGON.get(), level);
            entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(blockpos.getX(), blockpos.getY()+1, blockpos.getZ())));
            level.addFreshEntity(entityToSpawn);

            level.setBlock(blockpos, state.cycle(ACTIVATED), 3);
        }
        return super.use(state, level, blockpos, player, hand, result);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }
}
