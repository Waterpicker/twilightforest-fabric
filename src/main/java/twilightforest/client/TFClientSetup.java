package twilightforest.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
<<<<<<< Updated upstream
=======
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
>>>>>>> Stashed changes
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.loader.api.FabricLoader;
import shadow.fabric.api.client.rendering.v1.ArmorRenderingRegistry;
import twilightforest.ASMHooks;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlockItems;
import twilightforest.block.TFBlocks;
import twilightforest.block.entity.TFBlockEntities;
import twilightforest.client.model.TFLayerDefinitions;
import twilightforest.client.model.TFModelLayers;
import twilightforest.client.particle.TFParticleType;
import twilightforest.client.providers.*;
import twilightforest.compat.clothConfig.TFClientConfigEvent;
import twilightforest.entity.TFEntities;
import twilightforest.inventory.TFContainers;
import twilightforest.item.TFItems;
import twilightforest.network.TFPacketHandler;
<<<<<<< Updated upstream
=======
import twilightforest.world.registration.TFDimensions;

>>>>>>> Stashed changes
import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class TFClientSetup implements ClientModInitializer {

	public static boolean optifinePresent = false;

    protected static boolean optifineWarningShown = false;

	@Override
	public void onInitializeClient() {
        TwilightForestMod.LOGGER.debug(FabricLoader.getInstance().isModLoaded("optifabric") + ": Optifine loaded?");
        if(FabricLoader.getInstance().isModLoaded("optifabric"))
            optifinePresent = true;

        FabricClientEvents();
        FabricRenderingCalls();
        //TODO: Currently only work's in Dev Environment
        //TFClientSetup.addLegacyPack();
		TFItems.addItemModelProperties();

        ScreenEvents.BEFORE_INIT.register(((client, screen, scaledWidth, scaledHeight) -> LoadingScreenListener.onOpenGui(screen))); //MinecraftForge.EVENT_BUS.register(new LoadingScreenListener());
        RenderLayerRegistration.init();
		TFBlockEntities.registerTileEntityRenders();
        TFBlockItems.registerRenderers();
        TFContainers.renderScreens();

        TwilightForestRenderInfo renderInfo = new TwilightForestRenderInfo(128.0F, false, DimensionSpecialEffects.SkyType.NONE, false, false);
        DimensionRenderingRegistry.registerDimensionEffects(TwilightForestMod.prefix("renderer"), renderInfo);
        DimensionRenderingRegistry.registerSkyRenderer(TFDimensions.twilightForest, renderInfo.getSkyRenderHandler());
        DimensionRenderingRegistry.registerWeatherRenderer(TFDimensions.twilightForest, renderInfo.getWeatherRenderHandler());

        Minecraft.getInstance().execute(() -> {
            Sheets.SIGN_MATERIALS.put(TFBlocks.TWILIGHT_OAK, Sheets.createSignMaterial(TFBlocks.TWILIGHT_OAK));
            Sheets.SIGN_MATERIALS.put(TFBlocks.CANOPY, Sheets.createSignMaterial(TFBlocks.CANOPY));
            Sheets.SIGN_MATERIALS.put(TFBlocks.MANGROVE, Sheets.createSignMaterial(TFBlocks.MANGROVE));
            Sheets.SIGN_MATERIALS.put(TFBlocks.DARKWOOD, Sheets.createSignMaterial(TFBlocks.DARKWOOD));
            Sheets.SIGN_MATERIALS.put(TFBlocks.TIMEWOOD, Sheets.createSignMaterial(TFBlocks.TIMEWOOD));
            Sheets.SIGN_MATERIALS.put(TFBlocks.TRANSFORMATION, Sheets.createSignMaterial(TFBlocks.TRANSFORMATION));
            Sheets.SIGN_MATERIALS.put(TFBlocks.MINING, Sheets.createSignMaterial(TFBlocks.MINING));
            Sheets.SIGN_MATERIALS.put(TFBlocks.SORTING, Sheets.createSignMaterial(TFBlocks.SORTING));
        });

        armorRegistry();
    }

	private static Field field_EntityRenderersEvent$AddLayers_renderers;

//	@SuppressWarnings("unchecked")
//	public static void attachRenderLayers(EntityRenderersEvent.AddLayers event) {
//		if (field_EntityRenderersEvent$AddLayers_renderers == null) {
//			try {
//				field_EntityRenderersEvent$AddLayers_renderers = EntityRenderersEvent.AddLayers.class.getDeclaredField("renderers");
//				field_EntityRenderersEvent$AddLayers_renderers.setAccessible(true);
//			} catch (NoSuchFieldException e) {
//				e.printStackTrace();
//			}
//		}
//		if (field_EntityRenderersEvent$AddLayers_renderers != null) {
//			event.getSkins().forEach(renderer -> {
//				LivingEntityRenderer<Player, EntityModel<Player>> skin = event.getSkin(renderer);
//				attachRenderLayers(Objects.requireNonNull(skin));
//			});
//			try {
//				((Map<EntityType<?>, EntityRenderer<?>>) field_EntityRenderersEvent$AddLayers_renderers.get(event)).values().stream().
//						filter(LivingEntityRenderer.class::isInstance).map(LivingEntityRenderer.class::cast).forEach(TFClientSetup::attachRenderLayers);
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	private static <T extends LivingEntity, M extends EntityModel<T>> void attachRenderLayers(LivingEntityRenderer<T, M> renderer) {
//		renderer.addLayer(new ShieldLayer<>(renderer));
//		renderer.addLayer(new IceLayer<>(renderer));
	}

    public static void armorRegistry() {
        ArcticArmorProvider arcticArmorProvider = new ArcticArmorProvider();
        ArmorRenderingRegistry.registerModel(arcticArmorProvider, TFItems.ARCTIC_BOOTS, TFItems.ARCTIC_LEGGINGS, TFItems.ARCTIC_CHESTPLATE, TFItems.ARCTIC_HELMET);
        ArmorRenderingRegistry.registerTexture(arcticArmorProvider, TFItems.ARCTIC_BOOTS, TFItems.ARCTIC_LEGGINGS, TFItems.ARCTIC_CHESTPLATE, TFItems.ARCTIC_HELMET);

        FieryArmorProvider fieryArmorProvider = new FieryArmorProvider();
        ArmorRenderingRegistry.registerModel(fieryArmorProvider, TFItems.FIERY_BOOTS, TFItems.FIERY_LEGGINGS, TFItems.FIERY_CHESTPLATE, TFItems.FIERY_HELMET);
        ArmorRenderingRegistry.registerTexture(fieryArmorProvider, TFItems.FIERY_BOOTS, TFItems.FIERY_LEGGINGS, TFItems.FIERY_CHESTPLATE, TFItems.FIERY_HELMET);

        KnightArmorProvider knightArmorProvider = new KnightArmorProvider();
        ArmorRenderingRegistry.registerModel(knightArmorProvider, TFItems.KNIGHTMETAL_BOOTS, TFItems.KNIGHTMETAL_LEGGINGS, TFItems.KNIGHTMETAL_CHESTPLATE, TFItems.KNIGHTMETAL_HELMET);
        ArmorRenderingRegistry.registerTexture(knightArmorProvider, TFItems.KNIGHTMETAL_BOOTS, TFItems.KNIGHTMETAL_LEGGINGS, TFItems.KNIGHTMETAL_CHESTPLATE, TFItems.KNIGHTMETAL_HELMET);

        PhantomArmorProvider phantomArmorProvider = new PhantomArmorProvider();
        ArmorRenderingRegistry.registerModel(phantomArmorProvider, TFItems.PHANTOM_HELMET, TFItems.PHANTOM_CHESTPLATE);
        ArmorRenderingRegistry.registerTexture(phantomArmorProvider, TFItems.PHANTOM_HELMET, TFItems.PHANTOM_CHESTPLATE);

        YetiArmorProvider yetiArmorProvider = new YetiArmorProvider();
        ArmorRenderingRegistry.registerModel( yetiArmorProvider, TFItems.YETI_BOOTS, TFItems.YETI_LEGGINGS, TFItems.YETI_CHESTPLATE, TFItems.YETI_HELMET);
        ArmorRenderingRegistry.registerTexture(yetiArmorProvider, TFItems.YETI_BOOTS, TFItems.YETI_LEGGINGS, TFItems.YETI_CHESTPLATE, TFItems.YETI_HELMET);

        ArmorRenderingRegistry.registerTexture(new NagaArmorProvider(), TFItems.NAGA_CHESTPLATE, TFItems.NAGA_LEGGINGS);

        ArmorRenderingRegistry.registerTexture(new IronwoodArmorProvider(), TFItems.STEELEAF_BOOTS, TFItems.STEELEAF_LEGGINGS, TFItems.STEELEAF_CHESTPLATE, TFItems.STEELEAF_HELMET);

        ArmorRenderingRegistry.registerTexture(new SteeleafArmorProvider(), TFItems.STEELEAF_BOOTS, TFItems.STEELEAF_LEGGINGS, TFItems.STEELEAF_CHESTPLATE, TFItems.STEELEAF_HELMET);
    }

    private static void FabricClientEvents(){
        TFClientConfigEvent.init();

        ASMHooks.registerMultipartEvents();
        TFPacketHandler.CHANNEL.initClient();
    }

    private static void FabricRenderingCalls() {
        TFClientEvents.registerFabricEvents();
        TFLayerDefinitions.registerLayers();
        TFModelLayers.init();
        TFClientEvents.registerModels();
        TFEntities.EntityRenderers.registerEntityRenderer();
        TFParticleType.registerFactories();
    }

}
