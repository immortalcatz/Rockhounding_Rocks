package com.globbypotato.rockhounding_rocks.blocks.sculpts;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_rocks.blocks.BaseLanterns;
import com.globbypotato.rockhounding_rocks.enums.rocks.EnumRocksC;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LanternsC extends BaseLanterns {
	public static final PropertyEnum VARIANT = PropertyEnum.create("type", EnumRocksC.class);

    public LanternsC(String[] array, String name, boolean isTranslucent){
        super(array, name, isTranslucent);
		GameRegistry.register(new BaseMetaIB(this, EnumRocksC.getNames()).setRegistryName(name));
		for(int x = 0; x < 16; x++){setHarvestLevel("pickaxe", EnumRocksC.values()[x].harvest(), getState(x));}
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumRocksC.values()[0]));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getState(meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumRocksC)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	private IBlockState getState(int i) {
		return getDefaultState().withProperty(VARIANT, EnumRocksC.values()[i]);
	}

	@Override
	public float getBlockHardness(IBlockState state, World worldIn, BlockPos pos) {
		return EnumRocksC.values()[getMetaFromState(state)].hardness();
	}

}