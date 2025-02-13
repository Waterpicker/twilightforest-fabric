package twilightforest.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;

import java.util.Random;

public final class RotationUtil {
	public static final Rotation[] ROTATIONS = Rotation.values();
	public static final Direction[] CARDINALS = { Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST };

	private RotationUtil() {
	}

	public static Rotation getRandomRotation(Random random) {
		return ROTATIONS[random.nextInt(ROTATIONS.length)];
	}

	public static Rotation add(Rotation original, int rotations) {
		return original.getRotated(ROTATIONS[(rotations + 4) & 3]);
	}

	public static Rotation subtract(Rotation original, Rotation rotation) {
		switch (rotation) {
			case CLOCKWISE_180:

				switch (original) {
					case NONE:
						return Rotation.CLOCKWISE_180;
					case CLOCKWISE_90:
						return Rotation.COUNTERCLOCKWISE_90;
					case CLOCKWISE_180:
						return Rotation.NONE;
					case COUNTERCLOCKWISE_90:
						return Rotation.CLOCKWISE_90;
					default:
						return original;
				}

			case COUNTERCLOCKWISE_90:

				switch (original) {
					case NONE:
						return Rotation.CLOCKWISE_90;
					case CLOCKWISE_90:
						return Rotation.CLOCKWISE_180;
					case CLOCKWISE_180:
						return Rotation.COUNTERCLOCKWISE_90;
					case COUNTERCLOCKWISE_90:
						return Rotation.NONE;
					default:
						return original;
				}

			case CLOCKWISE_90:

				switch (original) {
					case NONE:
						return Rotation.COUNTERCLOCKWISE_90;
					case CLOCKWISE_90:
						return Rotation.NONE;
					case CLOCKWISE_180:
						return Rotation.CLOCKWISE_90;
					case COUNTERCLOCKWISE_90:
						return Rotation.CLOCKWISE_180;
					default:
						return original;
				}

			default:
				return original;
		}
	}

	public static Rotation getRelativeRotation(Direction original, Direction destination) {
		switch (original) {
			case NORTH:
			default:
				switch (destination) {
					case NORTH:
					default:
						return Rotation.NONE;
					case SOUTH:
						return Rotation.CLOCKWISE_180;
					case EAST:
						return Rotation.CLOCKWISE_90;
					case WEST:
						return Rotation.COUNTERCLOCKWISE_90;
				}
			case SOUTH:
				switch (destination) {
					case SOUTH:
					default:
						return Rotation.NONE;
					case NORTH:
						return Rotation.CLOCKWISE_180;
					case WEST:
						return Rotation.CLOCKWISE_90;
					case EAST:
						return Rotation.COUNTERCLOCKWISE_90;
				}
			case EAST:
				switch (destination) {
					case EAST:
					default:
						return Rotation.NONE;
					case WEST:
						return Rotation.CLOCKWISE_180;
					case SOUTH:
						return Rotation.CLOCKWISE_90;
					case NORTH:
						return Rotation.COUNTERCLOCKWISE_90;
				}
			case WEST:
				switch (destination) {
					case WEST:
					default:
						return Rotation.NONE;
					case EAST:
						return Rotation.CLOCKWISE_180;
					case NORTH:
						return Rotation.CLOCKWISE_90;
					case SOUTH:
						return Rotation.COUNTERCLOCKWISE_90;
				}
		}
	}

	public static Direction getRandomFacing(Random random) {
		return CARDINALS[random.nextInt(CARDINALS.length)];
	}
}
