// paimon
package cn.langya.sun.modules.impl.misc;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.modules.impl.combat.KillAura;
import cn.langya.sun.values.BoolValue;
import cn.langya.sun.values.DoubleValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.ClickType;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketEntityAction;

public class AutoLobby extends Module
{
    private final DoubleValue health;
    private final BoolValue randomhub;
    private final BoolValue disabler;
    private final BoolValue keepArmor;
    private final BoolValue noHub;

    public AutoLobby() {
        super("AutoLobby", Category.Misc);
        this.health = new DoubleValue("Health", 5.0, 20.0, 0.0);
        this.randomhub = new BoolValue("RandomHub", false);
        this.disabler = new BoolValue("AutoDisable-KillAura-Velocity", true);
        this.keepArmor = new BoolValue("KeepArmor", true);
        this.noHub = new BoolValue("NoHub", false);
    }

    @EventTarget
    public void onUpdate(final EventUpdate event) {
        final KillAura killAura =  Sun.moduleManager.getModule(KillAura.class);
        if (!this.noHub.get()) {
            if (mc.player.getHealth() <= this.health.get().floatValue()) {
                if (this.keepArmor.get()) {
                    for (int i = 0; i <= 3; ++i) {
                        final int armorSlot = 3 - i;
                        this.move(8 - armorSlot);
                    }
                }
                if (this.randomhub.get()) {
                    mc.player.sendChatMessage("/hub " + (int)(Math.random() * 100.0 + 1.0));
                }
                else {
                    mc.player.sendChatMessage("/hub");
                }
                if (this.disabler.get()) {
                   killAura.state = false;
                }
            }
        }
        else if ((mc.player.isDead || mc.player.getHealth() == 0.0f || mc.player.getHealth() <= 0.0f) && this.disabler.get()) {
           killAura.setState(false);
        }
    }

    private void move(final int item) {
        if (item != -1) {
            final boolean openInventory = !(mc.currentScreen instanceof GuiInventory);
            if (openInventory) {
                mc.getConnection().sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.OPEN_INVENTORY));
            }
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, item, 0, ClickType.PICKUP, mc.player);
            if (openInventory) {
                mc.getConnection().sendPacket(new CPacketCloseWindow());
            }
        }
    }
}
