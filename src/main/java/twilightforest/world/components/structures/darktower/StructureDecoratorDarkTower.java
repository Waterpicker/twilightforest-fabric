package twilightforest.world.components.structures.darktower;

import net.minecraft.world.level.block.Blocks;
import twilightforest.block.TFBlocks;
import twilightforest.world.components.structures.TFStructureDecorator;

public class StructureDecoratorDarkTower extends TFStructureDecorator {

	public StructureDecoratorDarkTower() {
		this.blockState = TFBlocks.TOWERWOOD.defaultBlockState();
		this.accentState = TFBlocks.ENCASED_TOWERWOOD.defaultBlockState();
		this.fenceState = Blocks.OAK_FENCE.defaultBlockState();
		this.stairState = Blocks.SPRUCE_STAIRS.defaultBlockState();
		this.pillarState = TFBlocks.ENCASED_TOWERWOOD.defaultBlockState();
		this.platformState = TFBlocks.ENCASED_TOWERWOOD.defaultBlockState();
		this.randomBlocks = new TowerwoodProcessor();
	}

}
