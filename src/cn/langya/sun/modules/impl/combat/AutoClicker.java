package cn.langya.sun.modules.impl.combat;

import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.events.impl.Render3DEvent;
import cn.langya.sun.events.impl.TickEvent;
import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.BoolValue;
import cn.langya.sun.values.IntValue;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumHand;
import org.apache.commons.lang3.RandomUtils;

import static cn.langya.sun.shit.MathUtil.random;

/**
 * @author LangYa
 * @ClassName AutoClicker
 * @date 2023/12/31 10:58
 * @Version 1.0
 */

public class AutoClicker extends Module {

    public AutoClicker() {
    super("AutoClicker", true, Category.Combat);
    }

    private final IntValue maxCPSValue = new IntValue("MaxCPS", 8, 1, 20) ;

    private final IntValue minCPSValue = new IntValue("MinCPS", 5, 1, 20);

    private final BoolValue rightValue = new BoolValue("Right", true);
    private final BoolValue leftValue = new BoolValue("Left", true);
    private final BoolValue jitterValue = new BoolValue("Jitter", false);
    private final BoolValue onlyBlock = new BoolValue("onlyBlock", true);

    private long rightDelay = randomClickDelay(minCPSValue.get(), maxCPSValue.get());
    private long rightLastSwing = 0L;
    private long leftDelay = randomClickDelay(minCPSValue.get(), maxCPSValue.get());
    private long leftLastSwing = 0L;

    @Event
    public void onTick(TickEvent e) {
        if(maxCPSValue.get() > minCPSValue.get()) {
            maxCPSValue.set(minCPSValue.get() + 1);
        }
    }

    @Event
    public void onRender3D(Render3DEvent event) {

        if (onlyBlock.get() && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBlock) {
            // Left click
            if (mc.gameSettings.keyBindAttack.isKeyDown() && leftValue.get()
                    && System.currentTimeMillis() - leftLastSwing >= leftDelay && mc.playerController.curBlockDamageMP == 0F) {
                KeyBinding.onTick(mc.gameSettings.keyBindAttack.getKeyCode()); // Minecraft Click Handling

                leftLastSwing = System.currentTimeMillis();
                leftDelay = randomClickDelay(minCPSValue.get(), maxCPSValue.get());
            }

            // Right click
            if (mc.gameSettings.keyBindUseItem.isKeyDown() && !mc.player.isUsingItem() && rightValue.get()
                    && System.currentTimeMillis() - rightLastSwing >= rightDelay) {
                KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode()); // Minecraft Click Handling

                rightLastSwing = System.currentTimeMillis();
                rightDelay = randomClickDelay(minCPSValue.get(), maxCPSValue.get());
            }
        } else if(! onlyBlock.get()) {
            // Left click
            if (mc.gameSettings.keyBindAttack.isKeyDown() && leftValue.get()
                    && System.currentTimeMillis() - leftLastSwing >= leftDelay && mc.playerController.curBlockDamageMP == 0F) {
                KeyBinding.onTick(mc.gameSettings.keyBindAttack.getKeyCode()); // Minecraft Click Handling

                leftLastSwing = System.currentTimeMillis();
                leftDelay = randomClickDelay(minCPSValue.get(), maxCPSValue.get());
            }

            // Right click
            if (mc.gameSettings.keyBindUseItem.isKeyDown() && !mc.player.isUsingItem() && rightValue.get()
                    && System.currentTimeMillis() - rightLastSwing >= rightDelay) {
                KeyBinding.onTick(mc.gameSettings.keyBindUseItem.getKeyCode()); // Minecraft Click Handling

                rightLastSwing = System.currentTimeMillis();
                rightDelay = randomClickDelay(minCPSValue.get(), maxCPSValue.get());
            }
        }
    }

    @Event
    public void onUpdate(UpdateEvent event) {
        if (jitterValue.get() && (leftValue.get() && mc.gameSettings.keyBindAttack.isKeyDown() && mc.playerController.curBlockDamageMP == 0F
                || rightValue.get() && mc.gameSettings.keyBindUseItem.isKeyDown() && !mc.player.isUsingItem())) {
            if (random.nextBoolean()) {
                mc.player.rotationYaw += random.nextBoolean() ? -RandomUtils.nextFloat(0F, 1F) : RandomUtils.nextFloat(0F, 1F);
            }

            if (random.nextBoolean()) {
                mc.player.rotationPitch += random.nextBoolean() ? -RandomUtils.nextFloat(0F, 1F) : RandomUtils.nextFloat(0F, 1F);

                // Make sure pitch is not going into unlegit values
                if (mc.player.rotationPitch > 90) {
                    mc.player.rotationPitch = 90F;
                } else if (mc.player.rotationPitch < -90) {
                    mc.player.rotationPitch = -90F;
                }
            }
        }

    }

    public static long randomClickDelay(final int minCPS, final int maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }
}