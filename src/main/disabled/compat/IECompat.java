package twilightforest.compat;

import blusunrize.immersiveengineering.api.energy.ThermoelectricHandler;
import blusunrize.immersiveengineering.api.shader.ShaderRegistry;
import blusunrize.immersiveengineering.api.tool.RailgunHandler;
import blusunrize.immersiveengineering.common.EventHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;
import twilightforest.client.TFClientEvents;
import twilightforest.client.renderer.ShaderGrabbagStackRenderer;
import twilightforest.client.shader.ShaderManager;
import twilightforest.compat.ie.IEShaderRegister;
import twilightforest.compat.ie.TFShaderItem;
import twilightforest.compat.ie.TFShaderGrabbagItem;
import twilightforest.entity.boss.*;
import twilightforest.entity.projectile.CicadaShotEntity;

import javax.annotation.Nullable;

/**
 * FIXME a few things still dont work in IE compat. Here is a list:
 * - Shader Grabbags dont have fancy rendering in the GUI like trophies do
 *   - all the rendering stuff is in {@link ShaderGrabbagStackRenderer}
 *   - I fixed up the calls to make it error-free, but it still might not render properly. If you can get the fancy rendering back online, I'll fix the visuals for it. -Gizmo
 * - 5 Shaders are hopelessly broken at the moment, theyre commented out in {@link IEShaderRegister#initShaders}
 *   - I wasnt able to properly call {@link ShaderManager#initShaders}, so that may be why
 * - There was a lonely command in the client proxy that I moved to its own command class. Once ShaderManager is back online, verify the command works and add it to the list of commands.
 * - A class in the disabled bin called GradientMappedTexture is helplessly broken. If anyone can fix this, re-enable the event in {@link TFClientEvents.ModBusEvents#texStitch} and correct the bandaid fixes in {@link IEShaderRegister}
 */
public class IECompat extends TFCompat {

    public IECompat() {
        super("immersiveengineering");
    }

    @Override
    protected void initItems(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> r = evt.getRegistry();
        r.register(new TFShaderItem().setRegistryName(TwilightForestMod.prefix("shader")));
        for (Rarity rarity : ShaderRegistry.rarityWeightMap.keySet()) {
            r.register(new TFShaderGrabbagItem(rarity));
        }
    }

    @Override
    protected boolean preInit() {
        ShaderRegistry.rarityWeightMap.put(TwilightForestMod.getRarity(), 1);
        return true;
    }

    @Override
    protected void init() {
        // Yeah, it's a thing! https://twitter.com/AtomicBlom/status/1004931868012056583
        RailgunHandler.registerProjectile(() -> Ingredient.of(TFBlocks.cicada.get().asItem()),
                (new RailgunHandler.StandardRailgunProjectile(2.0D, 0.25D) {

                    @Override
                    public Entity getProjectile(@Nullable Player shooter, ItemStack ammo, Entity projectile) {
                        Vec3 look = shooter.getLookAngle();
                        //FallingBlockEntity doesnt like cicadas, so custom entity it is
                        return new CicadaShotEntity(shooter.getCommandSenderWorld(), shooter, look.x * 20.0D, look.y * 20.0D, look.z * 20.0D);
                    }

                    @Override
                    public boolean isValidForTurret() {
                        return false;
                    }
                }));

        ThermoelectricHandler.registerSourceInKelvin(TFBlocks.fiery_block.get(), 2500);

        IEShaderRegister.initShaders();

        //our bosses arent boring, but we dont want them dropping the lame default IE shaders
        EventHandler.listOfBoringBosses.add(NagaEntity.class);
        EventHandler.listOfBoringBosses.add(LichEntity.class);
        EventHandler.listOfBoringBosses.add(MinoshroomEntity.class);
        EventHandler.listOfBoringBosses.add(HydraEntity.class);
        EventHandler.listOfBoringBosses.add(KnightPhantomEntity.class);
        EventHandler.listOfBoringBosses.add(UrGhastEntity.class);
        EventHandler.listOfBoringBosses.add(AlphaYetiEntity.class);
        EventHandler.listOfBoringBosses.add(SnowQueenEntity.class);
    }

    @Override
    protected void postInit() {

    }

    @Override
    protected void sendIMCs() {

    }
}
