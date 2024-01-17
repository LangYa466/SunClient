// by paimon
package cn.langya.sun.modules.impl.misc;

import cn.langya.sun.Sun;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.BoolValue;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import java.util.*;

public class Teams extends Module
{
    private static BoolValue armorValue;
    private static BoolValue colorValue;
    private static BoolValue scoreboardValue;
    
    public Teams() {
        super("Teams", Category.Misc);
        armorValue = new BoolValue("Armor", true);
        colorValue = new BoolValue("Color", true);
        scoreboardValue = new BoolValue("Scoreboard", true);
        add(armorValue, colorValue, scoreboardValue);
    }
    
    public static boolean isSameTeam(final Entity entity) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer) entity;
            return Objects.requireNonNull(Sun.moduleManager.getModule("Teams")).state && ((armorValue.get() && armorTeam(entityPlayer)) || (colorValue.get() && colorTeam(entityPlayer)) || (scoreboardValue.get() && scoreTeam(entityPlayer)));
        }
        return false;
    }

    public static boolean colorTeam(EntityPlayer sb) {
        String targetName = sb.getDisplayName().getFormattedText().replace("§r", "");
        String clientName = mc.player.getDisplayName().getFormattedText().replace("§r", "");
        return targetName.startsWith("§" + clientName.charAt(1));
    }

    public static boolean armorTeam(EntityPlayer entityPlayer) {
        if (entityPlayer.inventory.armorInventory.get(3) != null) {
            ItemStack myHead = mc.player.inventory.armorInventory.get(3);
            if (myHead.getItem() instanceof ItemArmor) {
                ItemArmor myItemArmor = (ItemArmor) myHead.getItem();
                ItemStack entityHead = entityPlayer.inventory.armorInventory.get(3);
                ItemArmor entityItemArmor = (ItemArmor) entityHead.getItem();
                if (String.valueOf(entityItemArmor.getColor(entityHead)).equals("10511680")) {
                    return true;
                }
                return myItemArmor.getColor(myHead) == entityItemArmor.getColor(entityHead);
            }
            return false;
        }
        return false;
    }

    public static boolean scoreTeam(EntityPlayer entityPlayer) {
        return mc.player.isOnSameTeam(entityPlayer);
    }


    static {
        armorValue = new BoolValue("ArmorColor", true);
        colorValue = new BoolValue("Color", true);
        scoreboardValue = new BoolValue("ScoreboardTeam", true);
    }
}
