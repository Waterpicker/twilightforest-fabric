package twilightforest.world.components.structures.minotaurmaze;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import twilightforest.block.TFBlocks;

import java.util.Random;

public class MazestoneProcessor extends StructurePiece.BlockSelector {

	@Override
	public void next(Random random, int x, int y, int z, boolean wall) {
		if (!wall) {
			this.next = Blocks.AIR.defaultBlockState();
		} else {
			this.next = TFBlocks.MAZESTONE.defaultBlockState();
			float rf = random.nextFloat();

			if (rf < 0.2F) {
				this.next = TFBlocks.MOSSY_MAZESTONE.defaultBlockState();
			} else if (rf < 0.5F) {
				this.next = TFBlocks.CRACKED_MAZESTONE.defaultBlockState();
			} else {
				this.next = TFBlocks.MAZESTONE_BRICK.defaultBlockState();
			}
		}
	}

}
