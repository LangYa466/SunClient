// by paion
package cn.langya.sun.utils.player;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.nbt.*;
import net.minecraft.potion.*;

public class HYTUtils
{
    private static final Minecraft mc;
    
    public static boolean isInLobby() {
        return mc.world.playerEntities.stream().anyMatch(e -> e.getName().contains("问题反馈"));
    }
    
    public static boolean isHoldingGodAxe(final EntityPlayer player) {
        final ItemStack holdingItem = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        return isGodAxe(holdingItem);
    }
    
    public static boolean isGodAxe(final ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (stack.getItem() != Items.GOLDEN_AXE) {
            return false;
        }
        final int durability = stack.getMaxDamage() - stack.getItemDamage();
        if (durability > 2) {
            return false;
        }
        final NBTTagList enchantmentTagList = stack.getEnchantmentTagList();
        if (enchantmentTagList == null) {
            return false;
        }
        for (int i = 0; i < enchantmentTagList.tagCount(); ++i) {
            final NBTTagCompound nbt = (NBTTagCompound)enchantmentTagList.get(i);
            if (nbt.hasKey("id") && nbt.hasKey("lvl") && nbt.getInteger("id") == 16) {
                final int level = nbt.getInteger("lvl");
                if (level >= 666) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isKBBall(final ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (stack.getItem() != Items.SLIME_BALL) {
            return false;
        }
        final NBTTagList enchantmentTagList = stack.getEnchantmentTagList();
        if (enchantmentTagList == null) {
            return false;
        }
        for (int i = 0; i < enchantmentTagList.tagCount(); ++i) {
            final NBTTagCompound nbt = (NBTTagCompound)enchantmentTagList.get(i);
            if (nbt.hasKey("id") && nbt.hasKey("lvl") && nbt.getInteger("id") == 19) {
                final int level = nbt.getInteger("lvl");
                if (level >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isFireEnchantBall(final ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (stack.getItem() != Items.MAGMA_CREAM) {
            return false;
        }
        final NBTTagList enchantmentTagList = stack.getEnchantmentTagList();
        if (enchantmentTagList == null) {
            return false;
        }
        for (int i = 0; i < enchantmentTagList.tagCount(); ++i) {
            final NBTTagCompound nbt = (NBTTagCompound)enchantmentTagList.get(i);
            if (nbt.hasKey("id") && nbt.hasKey("lvl") && nbt.getInteger("id") == 20) {
                final int level = nbt.getInteger("lvl");
                if (level >= 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isHoldingEnchantedGoldenApple(final EntityPlayer player) {
        final ItemStack holdingItem = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        return holdingItem != null && holdingItem.getItem() == Items.GOLDEN_APPLE && holdingItem.hasEffect();
    }


    public static int hasEatenGoldenApple(final EntityPlayer player) {
        final PotionEffect regenPotion = player.getActivePotionEffect(MobEffects.REGENERATION);
        if (regenPotion == null) {
            return -1;
        }
        if (regenPotion.getAmplifier() < 4) {
            return -1;
        }
        return regenPotion.getDuration();
    }
    
    public static int isRegen(final EntityPlayer player) {
        final PotionEffect regenPotion = player.getActivePotionEffect(MobEffects.REGENERATION);
        if (regenPotion == null) {
            return -1;
        }
        return regenPotion.getDuration();
    }
    
    public static int isStrength(final EntityPlayer player) {
        final PotionEffect strengthPotion = player.getActivePotionEffect(MobEffects.STRENGTH);
        if (strengthPotion == null) {
            return -1;
        }
        return strengthPotion.getDuration();
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
