package twilightforest.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FieryArmorItem extends ArmorItem {
	private static final MutableComponent TOOLTIP = new TranslatableComponent("item.twilightforest.fiery_armor.tooltip").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY));

	public FieryArmorItem(ArmorMaterial armorMaterial, EquipmentSlot armorType, Properties props) {
		super(armorMaterial, armorType, props);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, world, tooltip, flags);
		tooltip.add(TOOLTIP);
	}

//	@Override
//	public @NotNull HumanoidModel<LivingEntity> getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot armorSlot, HumanoidModel<LivingEntity> defaultModel) {
//		EntityModelSet models = Minecraft.getInstance().getEntityModels();
//		ModelPart root = models.bakeLayer(armorSlot == EquipmentSlot.LEGS ? TFModelLayers.FIERY_ARMOR_INNER : TFModelLayers.FIERY_ARMOR_OUTER);
//		return new FieryArmorModel(root);
//	}
//
//	@Override
//	public @NotNull ResourceLocation getArmorTexture(LivingEntity entity, ItemStack stack, EquipmentSlot slot, boolean secondLayer, @org.jetbrains.annotations.Nullable String suffix, ResourceLocation defaultTexture) {
//		if (slot == EquipmentSlot.LEGS) {
//			return new ResourceLocation(TwilightForestMod.ARMOR_DIR + "fiery_2.png");
//		} else {
//			return new ResourceLocation(TwilightForestMod.ARMOR_DIR + "fiery_1.png");
//		}
//	}

//	@Override
//	public String getArmorTexture(ItemStack itemstack, Entity entity, EquipmentSlot slot, String layer) {
//		if (slot == EquipmentSlot.LEGS) {
//			return TwilightForestMod.ARMOR_DIR + "fiery_2.png";
//		} else {
//			return TwilightForestMod.ARMOR_DIR + "fiery_1.png";
//		}
//	}

//	@Override
//	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
//		consumer.accept(ArmorRender.INSTANCE);
//	}
//
//	private static final class ArmorRender implements IItemRenderProperties {
//		private static final ArmorRender INSTANCE = new ArmorRender();
//
//		@Override
//		@SuppressWarnings("unchecked")
//		public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A defModel) {
//			EntityModelSet models = Minecraft.getInstance().getEntityModels();
//			ModelPart root = models.bakeLayer(armorSlot == EquipmentSlot.LEGS ? TFModelLayers.FIERY_ARMOR_INNER : TFModelLayers.FIERY_ARMOR_OUTER);
//			return (A) new FieryArmorModel(root);
//		}
//	}
}
