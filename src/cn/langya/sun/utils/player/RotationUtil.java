package cn.langya.sun.utils.player;

import cn.langya.sun.utils.Utils;
import com.google.common.base.Predicates;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.*;

import java.util.List;

/**
 * @author LangYa
 * @date 2024/2/7 ÏÂÎç 05:23
 */

public class RotationUtil extends Utils {

    public static boolean isMouseOver(final float yaw, final float pitch, final Entity target, final double range) {
        final float partialTicks = mc.timer.field_194147_b;
        final Entity entity = mc.getRenderViewEntity();
        RayTraceResult objectMouseOver;
        Entity mcPointedEntity = null;

        if (entity != null && mc.world != null) {
            final double d0 = mc.playerController.getBlockReachDistance();
            objectMouseOver = entity.rayTrace(d0, partialTicks);
            double d1 = d0;
            final Vec3d vec3 = entity.getPositionEyes(partialTicks);
            final boolean flag = d0 > range;

            if (objectMouseOver != null) {
                d1 = objectMouseOver.hitVec.distanceTo(vec3);
            }

            final Vec3d vec31 = mc.player.getVectorForRotation(pitch, yaw);
            final Vec3d vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
            Entity pointedEntity = null;
            Vec3d vec33 = null;
            final float f = 1.0F;
            final List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand(f, f, f), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::canBeCollidedWith));
            double d2 = d1;

            for (final Entity entity1 : list) {
                final float f1 = entity1.getCollisionBorderSize();
                final AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f1, f1, f1);
                final RayTraceResult RayTraceResult = axisalignedbb.calculateIntercept(vec3, vec32);

                if (axisalignedbb.isVecInside(vec3)) {
                    if (d2 >= 0.0D) {
                        pointedEntity = entity1;
                        vec33 = RayTraceResult == null ? vec3 : RayTraceResult.hitVec;
                        d2 = 0.0D;
                    }
                } else if (RayTraceResult != null) {
                    final double d3 = vec3.distanceTo(RayTraceResult.hitVec);

                    if (d3 < d2 || d2 == 0.0D) {
                        pointedEntity = entity1;
                        vec33 = RayTraceResult.hitVec;
                        d2 = d3;
                    }
                }
            }

            if (pointedEntity != null && flag && vec3.distanceTo(vec33) > range) {
                pointedEntity = null;
                objectMouseOver = new RayTraceResult(RayTraceResult.Type.MISS, vec33, null, new BlockPos(vec33));
            }

            if (pointedEntity != null && (d2 < d1 || objectMouseOver == null)) {
                if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame) {
                    mcPointedEntity = pointedEntity;
                }
            }

            return mcPointedEntity == target;
        }

        return false;
    }

    public static float[] getTargetRotations(final Entity entity) {
        if (entity == null) {
            return null;
        }
        Minecraft mc = Minecraft.getMinecraft();
        final double xSize = entity.posX - mc.player.posX;
        final double ySize = entity.posY + entity.getEyeHeight() / 2 - (mc.player.posY + mc.player.getEyeHeight());
        final double zSize = entity.posZ - mc.player.posZ;
        final double theta = MathHelper.sqrt(xSize * xSize + zSize * zSize);
        final float yaw = (float) (Math.atan2(zSize, xSize) * 180 / Math.PI) - 90;
        final float pitch = (float) (-(Math.atan2(ySize, theta) * 180 / Math.PI));
        return new float[]{(mc.player.rotationYaw + MathHelper.wrapAngleTo180(yaw - mc.player.rotationYaw)) % 360, (mc.player.rotationPitch + MathHelper.wrapAngleTo180(pitch - mc.player.rotationPitch)) % 360.0f};
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
        return new float[]{mc.player.rotationYaw + MathHelper.wrapAngleTo180(yaw - mc.player.rotationYaw), mc.player.rotationPitch + MathHelper.wrapAngleTo180(pitch - mc.player.rotationPitch)};
    }


}
