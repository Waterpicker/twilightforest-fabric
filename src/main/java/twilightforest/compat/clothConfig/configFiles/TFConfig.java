package twilightforest.compat.clothConfig.configFiles;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import twilightforest.TwilightForestMod;

@Config(name = TwilightForestMod.ID)
@Config.Gui.CategoryBackground(category = "client", background = TwilightForestMod.ID + ":textures/block/maze_stone_decorative.png")
@Config.Gui.CategoryBackground(category = "common", background = TwilightForestMod.ID + ":textures/block/maze_stone_brick.png")
public class TFConfig extends PartitioningSerializer.GlobalData{

    @ConfigEntry.Category("common")
    @ConfigEntry.Gui.TransitiveObject
    public TFConfigCommon tfConfigCommon = new TFConfigCommon();

    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.TransitiveObject
    @Environment(EnvType.CLIENT)
    public TFConfigClient tfConfigClient = new TFConfigClient();

}
