package twilightforest.api.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;

@Mixin(Mob.class)
public interface MobAccessor {
    @Accessor
    GoalSelector getTargetSelector();
}
