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
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class DragonStatueBlock extends HorizontalDirectionalBlock {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public DragonStatueBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public float getShadeBrightness(BlockState p_48731_, BlockGetter p_48732_, BlockPos p_48733_) {
        return 1.0F;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState p_54125_, Rotation p_54126_) {
        return p_54125_.setValue(FACING, p_54126_.rotate(p_54125_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_54122_, Mirror p_54123_) {
        return p_54122_.rotate(p_54123_.getRotation(p_54122_.getValue(FACING)));
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
        builder.add(ACTIVATED, FACING);
    }
}
