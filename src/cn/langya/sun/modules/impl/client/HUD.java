package cn.langya.sun.modules.impl.client;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.modules.impl.combat.KillAura;
import cn.langya.sun.modules.impl.misc.Teams;
import cn.langya.sun.ui.FontManager;
import cn.langya.sun.utils.misc.MathUtil;
import cn.langya.sun.utils.render.AnimationUtil;
import cn.langya.sun.utils.render.ColorUtils;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.utils.render.RoundedUtils;
import cn.langya.sun.values.*;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * @author LangYa
 * @ClassName HUD
 * @date 2024/1/11 下午 02:42
 * @Version 1.0
 */

public class HUD extends Module {
    public static final BoolValue logo = new BoolValue("Logo", true);
    public static final BoolValue info = new BoolValue("Info", true);
    public static final StringValue thud = new StringValue("TargetHud", "Neon");
    public static final StringValue gameinfo = new StringValue("GameInfo", "Sun");
    public static final DoubleValue animationSpeed = new DoubleValue("Animation Speed", 4.0, 10.0, 1.0);
    public static final ColorValue mainColor = new ColorValue("Main Color",Color.white.getRGB());
    public static final DecimalFormat DF_1 = new DecimalFormat("0.0");

    public HUD() {
        super("HUD", Category.Render);
        setState(true);
        add(animationSpeed,mainColor,logo,info,thud,gameinfo);
        thud.getValues().add("Novoline");
        thud.getValues().add("Neon");
        thud.getValues().add("None");
        gameinfo.getValues().add("Sun");
        gameinfo.getValues().add("None");
    }


    @EventTarget
    public void onRender2D(Render2DEvent event) {
        if (!isEnabled()) return;

        // logo
        if (logo.get()) {
            final String str = TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + mc.player.getName() + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + Minecraft.getDebugFPS() + "fps" + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + (HUD.mc.isSingleplayer() ? "SinglePlayer" : HUD.mc.getCurrentServerData().serverIP);
            RenderUtil.drawRect(6.0f, 6.0f, (float) (FontManager.getStringWidth(str) + 8 + FontManager.getStringWidth(Sun.name.toUpperCase())) + 5, 15.0f, new Color(19, 19, 19, 230).getRGB());
            RenderUtil.drawRect(6.0f, 6.0f, (float) (FontManager.getStringWidth(str) + 8 + FontManager.getStringWidth(Sun.name.toUpperCase())) + 5, 1.0f, ColorUtils.color(8).getRGB());
            FontManager.drawString(str, 11 + FontManager.getStringWidth(Sun.name.toUpperCase()), (int) 7.5f, Color.WHITE.getRGB());
            FontManager.drawString(Sun.name.toUpperCase(), (int) 10.0f, (int) 7.5f, Color.WHITE.getRGB());
        }

        if (info.get()) {
            // info
            FontManager.drawStringWithShadow("X: " + (int) mc.player.posX + " Y: " + (int) mc.player.posY + " Z: " + (int) mc.player.posZ, 3F, RenderUtil.getHeight() - (3F + FontManager.height), -1);
            FontManager.drawStringWithShadow("FPS:" + mc.getDebugFPS(), 3F, RenderUtil.getHeight() - (3F + FontManager.height) * 2, -1);
        }

        if(gameinfo.get().equals("Sun")) {
            RoundedUtils.drawRound(80,80,50,50,5, new Color(0,0,0,50));
        }

        for (Entity target1 : mc.world.loadedEntityList) {
            final FloatValue rangeValue = Sun.moduleManager.getModule(KillAura.class).getRangeValue();
            if (mc.player.getDistanceToEntity(target1) <= rangeValue.get() && target1 != mc.player && !Teams.isSameTeam(target1) && !target1.isDead) {
                EntityLivingBase target = (EntityLivingBase) target1;
                final Color firstColor = ColorUtils.color(1);
                final Color secondColor = ColorUtils.color(6);
                double width;
                double height;
                double x = 50;
                double y = 50;
                final FontRenderer fr = mc.fontRendererObj;
                final double healthPercentage = MathHelper.clamp((target.getHealth() + target.getAbsorptionAmount()) / (target.getMaxHealth() + target.getAbsorptionAmount()), 0.0f, 1.0f);
                width = Math.max(120, fr.getStringWidth(target.getName()) + 50);
                height = 36.0;
                final int alphaInt = (int) (255.0f);
                if (thud.get().equals("Novoline")) {
                    Gui.drawRect3((double) x, (double) y, width, 36.0, new Color(29, 29, 29, alphaInt).getRGB());
                    Gui.drawRect3((double) (x + 1.0f), (double) (y + 1.0f), width - 2.0, 34.0, new Color(40, 40, 40, alphaInt).getRGB());
                    Gui.drawRect3((double) (x + 34.0f), (double) (y + 15.0f), 83.0, 10.0, -14213603);
                    final float f = (float) (83.0 * healthPercentage);
                    target.animatedHealthBar = AnimationUtil.animate(target.animatedHealthBar, f, 0.1f);
                    RenderUtil.drawHGradientRect(x + 34.0f, y + 15.0f, target.animatedHealthBar, 10.0, firstColor.darker().darker().getRGB(), secondColor.darker().darker().getRGB());
                    RenderUtil.drawHGradientRect(x + 34.0f, y + 15.0f, f, 10.0, firstColor.getRGB(), secondColor.getRGB());
                    final int textColor = -1;
                    final int mcTextColor = -1;
                    GlStateManager.enableBlend();
                    GlStateManager.blendFunc(770, 771);
                    if (target instanceof AbstractClientPlayer) {
                        RenderUtil.glColor(textColor);
                        RenderUtil.drawBigHead((float) (x + 3.5f), (float) (y + 3.0f), 28.0f, 28.0f, (AbstractClientPlayer) target);
                    } else {
                        fr.drawStringWithShadow("?", (float) (x + 17.0f - fr.getStringWidth("?") / 2.0f), (float) (y + 17.0f - fr.FONT_HEIGHT / 2.0f), mcTextColor);
                    }
                    GlStateManager.enableBlend();
                    GlStateManager.blendFunc(770, 771);
                    fr.drawStringWithShadow(target.getName(), (float) (x + 34.0f), (float) (y + 5.0f), mcTextColor);
                    final String healthText = MathUtil.round(healthPercentage * 100.0, 0) + "%";
                    fr.drawStringWithShadow(healthText, (float) (x + 17.0f + width / 2.0 - fr.getStringWidth(healthText) / 2.0f), (float) (y + 16.0f), mcTextColor);
                }
                if (thud.get().equals("Neon")) {
                      width = (float)Math.max(128, FontManager.getStringWidth("Name: " + target.getName()) + 60);
                    height = 50.0;
                    Gui.drawRect3((double)x, (double)y, (double)width, 50.0, new Color(19, 19, 19, 180).getRGB());
                    RenderUtil.drawHGradientRect(x, y, width, 1.0, firstColor.getRGB(), secondColor.getRGB());
                    final int textColor = -1;
                    Gui.drawRect3((double)x, (double)y, (double)width, 50.0, new Color(0, 0, 0, (int)(110.0f * 1)).getRGB());
                    final int scaleOffset = (int)(target.hurtTime * 0.35f);
                    final float healthPercent = (target.getHealth() + target.getAbsorptionAmount()) / (target.getMaxHealth() + target.getAbsorptionAmount());
                    final float var = (float) ((width - 28.0f) * healthPercent);
                    target.animatedHealthBar = AnimationUtil.animate(target.animatedHealthBar, var, 0.1f);
                    RenderUtil.drawHGradientRect(x + 5.0f, y + 40.0f, target.animatedHealthBar, 5.0, firstColor.getRGB(), secondColor.getRGB());
                    if (target instanceof AbstractClientPlayer) {
                        GlStateManager.pushMatrix();
                        RenderUtil.drawBigHead((float) (x + 5.0f + scaleOffset / 2.0f), (float) (y + 5.0f + scaleOffset / 2.0f), (float)(30 - scaleOffset), (float)(30 - scaleOffset), (AbstractClientPlayer)target);
                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                        GlStateManager.popMatrix();
                    }
                    else {
                        GlStateManager.pushMatrix();
                        GlStateManager.translate(x + 5.0f + scaleOffset / 2.0f + 15.0f, y + 5.0f + scaleOffset / 2.0f + 25.0f, 40.0f);
                        GlStateManager.scale(0.31, 0.31, 0.31);
                        drawModel(target.rotationYaw, target.rotationPitch, target);
                        GlStateManager.popMatrix();
                    }
                    final double healthNum = MathUtil.round(target.getHealth() + target.getAbsorptionAmount(), 1);
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                    FontManager.drawString(String.valueOf(healthNum), (int) (x + target.animatedHealthBar + 8.0f), (int) (y + 38.0f), textColor);
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                    FontManager.drawString("Name: " + target.getName(), (int) (x + 40.0f), (int) (y + 8.0f), textColor);
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                    FontManager.drawString("Distance: " + MathUtil.round(mc.player.getDistanceToEntity((Entity)target), 1), (int) (x + 40.0f), (int) (y + 20.0f), textColor);
                    break;
                }
            }
        }
    }



    public void drawModel(final float yaw, final float pitch, final EntityLivingBase entityLivingBase) {
        GlStateManager.resetColor();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0f, 0.0f, 50.0f);
        GlStateManager.scale(-50.0f, 50.0f, 50.0f);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        final float renderYawOffset = entityLivingBase.renderYawOffset;
        final float rotationYaw = entityLivingBase.rotationYaw;
        final float rotationPitch = entityLivingBase.rotationPitch;
        final float prevRotationYawHead = entityLivingBase.prevRotationYawHead;
        final float rotationYawHead = entityLivingBase.rotationYawHead;
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate((float)(-Math.atan(pitch / 40.0f) * 20.0), 1.0f, 0.0f, 0.0f);
        entityLivingBase.renderYawOffset = yaw - 0.4f;
        entityLivingBase.rotationYaw = yaw - 0.2f;
        entityLivingBase.rotationPitch = pitch;
        entityLivingBase.rotationYawHead = entityLivingBase.rotationYaw;
        entityLivingBase.prevRotationYawHead = entityLivingBase.rotationYaw;
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager renderManager = mc.getRenderManager();
        renderManager.setPlayerViewY(180.0f);
        renderManager.setRenderShadow(false);
        renderManager.setRenderShadow(true);
        entityLivingBase.renderYawOffset = renderYawOffset;
        entityLivingBase.rotationYaw = rotationYaw;
        entityLivingBase.rotationPitch = rotationPitch;
        entityLivingBase.prevRotationYawHead = prevRotationYawHead;
        entityLivingBase.rotationYawHead = rotationYawHead;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.resetColor();
    }

}
