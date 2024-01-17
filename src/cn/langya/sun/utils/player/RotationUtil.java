package cn.langya.sun.utils.player;

import cn.langya.sun.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

/**
 * @author LangYa
 * @ClassName RotationUtil
 * @date 2024/1/17 ÏÂÎç 09:48
 * @Version 1.0
 */

public class RotationUtil extends Utils {

    public static void setVisualRotations(float yaw) {
        mc.player.rotationYawHead = mc.player.renderYawOffset = yaw;
    }

    public static float[] getGrimRotations(EntityLivingBase target) {
        if (target == null) {
            return null;
        }

        Minecraft mc = Minecraft.getMinecraft();
        AxisAlignedBB bb = target.getEntityBoundingBox();
        double x = MathHelper.clamp(mc.player.posX, bb.minX, bb.maxX) + new java.util.Random().nextDouble() * 0.4 - 0.2;
        double y = MathHelper.clamp(mc.player.posY + mc.player.getEyeHeight(), bb.minY, bb.maxY) +  new java.util.Random().nextDouble() * 0.4 - 0.2;
        double z = MathHelper.clamp(mc.player.posZ, bb.minZ, bb.maxZ) + new java.util.Random().nextDouble() * 0.4 - 0.2;
        double xSize = x - mc.player.posX;
        double ySize = y - (mc.player.posY + mc.player.getEyeHeight());
        double zSize = z - mc.player.posZ;
        double theta = MathHelper.sqrt(xSize * xSize + zSize * zSize);
        float yaw = (float) ((Math.atan2(zSize, xSize) * 180 / Math.PI) - 90);
        float pitch = (float) (-(Math.atan2(ySize, theta) * 180 / Math.PI));
        return new float[]{mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - mc.player.rotationYaw), mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - mc.player.rotationPitch)};
    }

}
