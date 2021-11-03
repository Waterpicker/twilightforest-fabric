package twilightforest.world.registration;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import twilightforest.TwilightForestMod;
import twilightforest.TwilightForestMod;
import twilightforest.compat.clothConfig.configFiles.TFConfig;
import twilightforest.world.components.TFBiomeDistributor;
import twilightforest.world.components.TFBiomeProvider;
import twilightforest.world.components.chunkgenerators.ChunkGeneratorTwilight;

import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;

public class TFDimensions {
	// Find a different way to validate if a world is passible as a "Twilight Forest" instead of hardcoding Dim ID (Instanceof check for example)
	public static final ResourceKey<Level> twilightForest = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(TwilightForestMod.COMMON_CONFIG.dimension.portalDestinationID));

	public static void init() {
		Registry.register(Registry.BIOME_SOURCE, TwilightForestMod.prefix("smart_distribution"), TFBiomeDistributor.TF_CODEC);
		// TODO legacy
		Registry.register(Registry.BIOME_SOURCE, TwilightForestMod.prefix("grid"), TFBiomeProvider.TF_CODEC);

		Registry.register(Registry.CHUNK_GENERATOR, TwilightForestMod.prefix("structure_locating_wrapper"), ChunkGeneratorTwilight.CODEC);
	}
}
