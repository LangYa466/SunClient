package cn.langya.sun.modules.impl.combat;

import cn.langya.sun.event.Event;
import cn.langya.sun.event.EventManager;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.render.RenderUtils;
import cn.langya.sun.value.BoolValue;
import cn.langya.sun.value.FloatValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static net.minecraft.util.math.MathHelper.atan2;


public class KillAura extends Module {

    // 攻击距离
    private final FloatValue rangeValue = new FloatValue("Range", 3F, 3F, 8F);

    // 视角
    private final FloatValue fovValue = new FloatValue("FOV", 180f, 0f, 180f);

    // 距离光环显示
    private final BoolValue circleValue = new BoolValue("Circle", true);
    private final FloatValue circleRed = new FloatValue("CircleRed", 255F, 0F, 255F);
    private final FloatValue circleGreen = new FloatValue("CircleGreen", 255F, 0F, 255F);
    private final FloatValue circleBlue = new FloatValue("CircleBlue", 255F, 0F, 255F);
    private final FloatValue circleAlpha = new FloatValue("CircleAlpha", 255F, 0F, 255F);
    private final FloatValue circleAccuracy = new FloatValue("CircleAccuracy", 15F, 0F, 60F);

    // 打人光环显示
    private final BoolValue mark = new BoolValue("Mark", true);

    private EntityLivingBase target;
    private int click = 0;
    private boolean blocking = false;

    public KillAura() {
        super("杀人气质", true, Category.Combat);
    }

    @Event
    public void onUpdate() {

        if (!getState()) return;

        Minecraft mc = Minecraft.getMinecraft();

        for (Entity entity : mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
                if (entity instanceof EntityLivingBase && mc.player.getDistanceToEntity(entity) <= rangeValue.get()) {
                    target = (EntityLivingBase) entity;
                    setRotation(entityLivingBase);
                    attackEntity(entityLivingBase);
                }
            }
        }

        if (target == null) {
            stopBlocking();
            click = 0;
        }
    }

    @Event
    public void onRender3D() {

        if (circleValue.get()) {
            GL11.glPushMatrix();
            GL11.glTranslated(Minecraft.getMinecraft().player.lastTickPosX +
                            (Minecraft.getMinecraft().player.posX - Minecraft.getMinecraft().player.lastTickPosX) *
                                    Minecraft.getMinecraft().timer.field_194147_b - Minecraft.getMinecraft().getRenderManager().renderPosX,
                    Minecraft.getMinecraft().player.lastTickPosY +
                            (Minecraft.getMinecraft().player.posY - Minecraft.getMinecraft().player.lastTickPosY) *
                                    Minecraft.getMinecraft().timer.field_194147_b - Minecraft.getMinecraft().getRenderManager().renderPosY,
                    Minecraft.getMinecraft().player.lastTickPosZ +
                            (Minecraft.getMinecraft().player.posZ - Minecraft.getMinecraft().player.lastTickPosZ) *
                                    Minecraft.getMinecraft().timer.field_194147_b - Minecraft.getMinecraft().getRenderManager().renderPosZ);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            GL11.glLineWidth(1F);
            GL11.glColor4f(
                    circleRed.get() / 255.0F,
                    circleGreen.get() / 255.0F,
                    circleBlue.get() / 255.0F,
                    circleAlpha.get() / 255.0F
            );
            GL11.glRotatef(90F, 1F, 0F, 0F);
            GL11.glBegin(GL11.GL_LINE_STRIP);

            for (int i = 0; i <= 360; i += (61 - circleAccuracy.get())) {
                GL11.glVertex2f(
                        (float) (cos(i * Math.PI / 180.0) * rangeValue.get()),
                        (float) ((sin(i * Math.PI / 180.0) * rangeValue.get()))
                );
            }
            GL11.glVertex2f(
                    (float) (cos(360 * Math.PI / 180.0) * rangeValue.get()),
                    (float) ((sin(360 * Math.PI / 180.0) * rangeValue.get()))
            );

            GL11.glEnd();

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);

            GL11.glPopMatrix();
        }
    }

    private boolean isFovInRange(Entity entity, float fov) {
        fov *= 0.5f;
        double v = ((Minecraft.getMinecraft().player.rotationYaw - getPlayerRotation(entity)) % 360.0 + 540.0) % 360.0 - 180.0;
        return (v > 0.0 && v < fov) || (-fov < v && v < 0.0);
    }

    private float getPlayerRotation(Entity entity) {
        double x = entity.posX - Minecraft.getMinecraft().player.posX;
        double z = entity.posZ - Minecraft.getMinecraft().player.posZ;
        float yaw = (float) (atan2(x, z) * 57.2957795);
        yaw = -yaw;
        return yaw;
    }

    private float getRotationtoEntityYaw(EntityLivingBase entity) {
        double xDiff = entity.posX - Minecraft.getMinecraft().player.posX;
        double zDiff = entity.posZ - Minecraft.getMinecraft().player.posZ;
        return (float) ((atan2(zDiff, xDiff) * 180.0 / Math.PI) - 90.0f);
    }

    private float getRotationtoEntityPitch(EntityLivingBase entity) {
        double xDiff = entity.posX - Minecraft.getMinecraft().player.posX;
        double zDiff = entity.posZ - Minecraft.getMinecraft().player.posZ;
        double yDiff = entity.posY + entity.getEyeHeight() * 0.9 - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        double distance = Math.sqrt(xDiff * xDiff + zDiff * zDiff + yDiff * yDiff);
        float pitch = (float) -Math.toDegrees(Math.atan2(yDiff, Math.sqrt(xDiff * xDiff + zDiff * zDiff)));
        float yaw = (float) Math.toDegrees(Math.atan2(zDiff, xDiff)) - 90.0f;
        yaw = -yaw;
        return pitch;
    }

    private void setRotation(EntityLivingBase entity) {
        float yaw = getRotationtoEntityYaw(entity);
        float pitch = getRotationtoEntityPitch(entity);
        Minecraft.getMinecraft().player.rotationYaw = updateRotation(Minecraft.getMinecraft().player.rotationYaw, yaw, fovValue.get());
        Minecraft.getMinecraft().player.rotationPitch = updateRotation(Minecraft.getMinecraft().player.rotationPitch, pitch, fovValue.get());
    }

    private float updateRotation(float current, float target, float speed) {
        float angle = MathHelper.wrapDegrees(target - current);
        if (angle > speed) {
            angle = speed;
        } else if (angle < -speed) {
            angle = -speed;
        }
        return current + angle;
    }

    private void attackEntity(EntityLivingBase entity) {
        if (click <= 0) {
            Minecraft.getMinecraft().playerController.attackEntity(Minecraft.getMinecraft().player, entity);
            click = 7;
        } else {
            click--;
        }
        startBlocking();
    }

    private void startBlocking() {
        if (!blocking) {
            blocking = true;
            Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
        }
    }

    private void stopBlocking() {
        if (blocking) {
            blocking = false;
            Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
        }
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}