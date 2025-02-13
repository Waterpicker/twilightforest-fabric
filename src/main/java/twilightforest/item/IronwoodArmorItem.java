package twilightforest.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public class IronwoodArmorItem extends ArmorItem {

	public IronwoodArmorItem(ArmorMaterial armorMaterial, EquipmentSlot armorType, Properties props) {
		super(armorMaterial, armorType, props);
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
		if (allowdedIn(tab)) {
			ItemStack istack = new ItemStack(this);
			switch (this.getSlot()) {
				case HEAD:
					istack.enchant(Enchantments.AQUA_AFFINITY, 1);
					break;
				case CHEST:
				case LEGS:
					istack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1);
					break;
				case FEET:
					istack.enchant(Enchantments.FALL_PROTECTION, 1);
					break;
				default:
					break;
			}
			list.add(istack);
		}
	}
}
