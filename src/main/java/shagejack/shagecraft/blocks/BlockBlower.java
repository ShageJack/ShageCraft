package shagejack.shagecraft.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import shagejack.shagecraft.Shagecraft;
import shagejack.shagecraft.blocks.includes.ShageBlock;
import shagejack.shagecraft.tile.TileEntityClayFurnaceBottom;
import shagejack.shagecraft.util.ShageBlockHelper;
import shagejack.shagecraft.util.ShageStringHelper;

public class BlockBlower extends ShageBlock {

    public BlockBlower(Material material, String name) {
        super(material, name);
        setHardness(1.0F);
        setHarvestLevel("axe", 1);
        setResistance(4.0F);
        setCreativeTab(Shagecraft.TAB_Shagecraft);
        setHasRotation();
        setRotationType(ShageBlockHelper.RotationType.FOUR_WAY);
        setSoundType(SoundType.GROUND);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            EnumFacing rotation = state.getValue(ShageBlock.PROPERTY_DIRECTION);
            TileEntity tileEntity = null;
            IBlockState stateTube;
            EnumFacing rotationTube;
            switch (rotation) {
                case EAST:
                    stateTube = worldIn.getBlockState(pos.add(-1, 0, 0));
                    rotationTube = state.getValue(ShageBlock.PROPERTY_DIRECTION);
                    if (rotationTube == EnumFacing.WEST || rotationTube == EnumFacing.EAST) {
                        tileEntity = worldIn.getTileEntity(pos.add(-2, -1, 0));
                    }
                    break;
                case WEST:
                    stateTube = worldIn.getBlockState(pos.add(1, 0, 0));
                    rotationTube = state.getValue(ShageBlock.PROPERTY_DIRECTION);
                    if (rotationTube == EnumFacing.WEST || rotationTube == EnumFacing.EAST) {
                        tileEntity = worldIn.getTileEntity(pos.add(2, -1, 0));
                    }
                    break;
                case SOUTH:
                    stateTube = worldIn.getBlockState(pos.add(0, 0, -1));
                    rotationTube = state.getValue(ShageBlock.PROPERTY_DIRECTION);
                    if (rotationTube == EnumFacing.NORTH || rotationTube == EnumFacing.SOUTH) {
                        tileEntity = worldIn.getTileEntity(pos.add(0, -1, -2));
                    }
                    break;
                case NORTH:
                    stateTube = worldIn.getBlockState(pos.add(0, 0, 1));
                    rotationTube = state.getValue(ShageBlock.PROPERTY_DIRECTION);
                    if (rotationTube == EnumFacing.NORTH || rotationTube == EnumFacing.SOUTH) {
                        tileEntity = worldIn.getTileEntity(pos.add(0, -1, 2));
                    }
                    break;
            }

            if (tileEntity instanceof TileEntityClayFurnaceBottom && ((TileEntityClayFurnaceBottom) tileEntity).checkComplete(worldIn) != -1) {
                if (playerIn.getHeldItem(hand).getItem() == Shagecraft.ITEMS.wind_flag) {
                    double oxygenFlow = ((TileEntityClayFurnaceBottom) tileEntity).getOxygenFlow() - 0.25D + 0.5 * worldIn.rand.nextDouble();
                    if (worldIn.isRemote) playerIn.sendMessage(new TextComponentString("\u6c27\u6c14\u6d41\u91cf\u770b\u8d77\u6765\u5927\u6982\u662f\u2026\u2026" + ShageStringHelper.formatNumber(oxygenFlow) + "mol/t"));
                    return true;
                }
                ((TileEntityClayFurnaceBottom) tileEntity).blowerInput(worldIn);
                return true;
            }

            return true;
    }



}