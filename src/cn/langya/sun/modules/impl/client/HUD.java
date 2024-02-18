package cn.langya.sun.modules.impl.client;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.render.EventRender2D;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.modules.impl.misc.AutoL;
import cn.langya.sun.ui.font.FontManager;
import cn.langya.sun.utils.render.*;
import cn.langya.sun.values.BoolValue;
import cn.langya.sun.values.ColorValue;
import cn.langya.sun.values.DoubleValue;
import cn.langya.sun.values.ListValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;

/**
 * @author LangYa
 * @ClassName HUD
 * @date 2024/1/11 下午 02:42
 * @Version 1.0
 */

public class HUD extends Module {
    public static final BoolValue arraylist = new BoolValue("Arraylist",true);
    public static final ListValue logo = new ListValue("Logo", "Sun");
    public static final ListValue thud = new ListValue("TargetHud", "Neon");
    public static final ListValue gameinfo = new ListValue("GameInfo", "Sun");
    public static final DoubleValue animationSpeed = new DoubleValue("Animation Speed", 4.0, 10.0, 1.0);
    public static final ColorValue mainColor = new ColorValue("Main Color",Color.white.getRGB());

    public HUD() {
        super("HUD", Category.Render);
        setState(true);
        add(animationSpeed,mainColor,logo,thud,gameinfo,arraylist);
        /*
        thud.getValues().add("Neon");
        thud.getValues().add("Moon");
        thud.getValues().add("Raven");
        thud.getValues().add("Novoline");
         */
        thud.getValues().add("Sun");
        thud.getValues().add("Style");
        thud.getValues().add("LangYa");
        thud.getValues().add("None");
        gameinfo.getValues().add("LangYa");
        gameinfo.getValues().add("None");
        gameinfo.getValues().add("Sun");
        logo.getValues().add("LangYa");
        logo.getValues().add("Sun2");
        logo.getValues().add("Sun");
        logo.getValues().add("Tenacity");
        logo.getValues().add("None");
    }


    @EventTarget
    public void onRender2D(EventRender2D event) {


        // arraylist
        if(arraylist.get()) {
            float sb = 0;
            for (final Module m :Sun.moduleManager.getModules()) {
                if (m.state) {
                    sb += FontManager.S20.getHeight() - 5;
                    GaussianBlur.startBlur();
                    RoundedUtil.drawRound(RenderUtil.getWidth() - 100,sb,FontManager.S20.getStringWidth(m.getName()), FontManager.S20.getHeight() - 6, 0,new Color(0,0,0,130));
                    GaussianBlur.endBlur(13, 0);
                    RoundedUtil.drawRound(RenderUtil.getWidth() - 100,sb,FontManager.S20.getStringWidth(m.getName()), FontManager.S20.getHeight() - 6, 0,new Color(0,0,0,50));
                    RenderUtil.resetColor();
                    FontManager.S20.drawStringWithShadow(m.getName(),RenderUtil.getWidth() - 100,sb,-1);
                }
            }
        }

        if (logo.get().equals("Tenacity")) {
            float xVal = 6f;
            float yVal = 6f;
            float width = FontManager.T50.getStringWidth(Sun.name);


            RenderUtil.resetColor();
            GradientUtil.applyGradientHorizontal(xVal, yVal, width, 20, 1,new Color(236, 133, 209),  new Color(28, 167, 222), () -> {
                RenderUtil.setAlphaLimit(0);
                FontManager.T50.drawString(Sun.name, xVal, yVal, 0);
            });
        }


            // logo
        if (logo.get().equals("Sun")) {
            final String str = TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + mc.player.getName() + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + Minecraft.getDebugFPS() + "fps" + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + (HUD.mc.isSingleplayer() ? "SinglePlayer" : HUD.mc.getCurrentServerData().serverIP);
            RenderUtil.drawRect(6.0f, 6.0f, (float) (FontManager.S20.getStringWidth(str) + 18), 19.0f, new Color(19, 19, 19, 230).getRGB());
            RenderUtil.drawRect(6.0f, 6.0f, (float) (FontManager.S20.getStringWidth(str) + 18), 1.0f, ColorUtil.color(8).getRGB());
            RenderUtil.resetColor();
            FontManager.S20.drawString(str, 11 + FontManager.S20.getStringWidth(Sun.name.toUpperCase()), (int) 7.5f, Color.WHITE.getRGB());
            FontManager.S20.drawString(Sun.name.toUpperCase(), (int) 10.0f, (int) 7.5f, Color.WHITE.getRGB());
        }

        if (logo.get().equals("Sun2")) {
            final String str = TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + mc.player.getName() + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + Minecraft.getDebugFPS() + "fps" + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + (HUD.mc.isSingleplayer() ? "SinglePlayer" : HUD.mc.getCurrentServerData().serverIP);
            RenderUtil.drawBorderedRect(6.0f, 6.0f, (float) (FontManager.S20.getStringWidth(str) + 25), 19.0f,3f, new Color(0,0,0, 120).getRGB(),new Color(0,0,0, 120).getRGB());
            RenderUtil.resetColor();
            FontManager.S20.drawString(str, 11 + FontManager.S20.getStringWidth(Sun.name.toUpperCase()), (int) 7.5f, Color.WHITE.getRGB());
            FontManager.S20.drawString(Sun.name.toUpperCase(), (int) 10.0f, (int) 7.5f, Color.WHITE.getRGB());
        }

        if (logo.get().equals("LangYa")) {
            final String str = TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + mc.player.getName() + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + Minecraft.getDebugFPS() + "fps" + TextFormatting.DARK_GRAY + " | " + TextFormatting.WHITE + (HUD.mc.isSingleplayer() ? "SinglePlayer" : HUD.mc.getCurrentServerData().serverIP);
            GaussianBlur.startBlur();
            RoundedUtil.drawRound(6.0f, 6.0f, (float) (FontManager.S20.getStringWidth(str) + 13), 13.0f,0f, new Color(0, 0, 0, 130));
            GaussianBlur.endBlur(13, 2);
            RoundedUtil.drawRound(6.0f, 6.0f, (float) (FontManager.S20.getStringWidth(str) + 13), 13.0f,0f, new Color(0, 0, 0, 130));


            RenderUtil.resetColor();
            ShadowUtil.drawShadow(6.0f, 6.0f, (float) (FontManager.S20.getStringWidth(str) + 13), 13.0f);
            FontManager.S20.drawString(str, 11 + FontManager.S20.getStringWidth(Sun.name.toUpperCase()), (int) 7.5f, Color.WHITE.getRGB());
            FontManager.S20.drawString(Sun.name.toUpperCase(), (int) 10.0f, (int) 7.5f, Color.WHITE.getRGB());
        }

        if (gameinfo.get().equals("Sun")) {
            RoundedUtil.drawRound(80, 120, 130, 60, 0, new Color(49,49,49));
            RoundedUtil.drawRound(80, 120, 130, 15, 0, new Color(19,19,19));
            RoundedUtil.drawRound(80, 120, 130, 3, 0, new Color(30,126,190));
            RenderUtil.resetColor();
            ShadowUtil.drawShadow(80, 120, 130, 60);

            // 绘制文本
            FontManager.T25.drawCenteredString("Info",98, 123,-1);

            FontManager.T20.drawCenteredString("Player ID",106, 138,-1);
            FontManager.T20.drawCenteredString(getPlayerName(),180, 138,-1);

            FontManager.T20.drawCenteredString("Health",101, 138 + FontManager.T20.FONT_HEIGHT - 5,-1);
            FontManager.T20.drawCenteredString(getPlayerHealth(),200, 138 + FontManager.T20.FONT_HEIGHT - 5,-1);

            FontManager.T20.drawCenteredString("Kills",95, 138 + FontManager.T20.FONT_HEIGHT - 5 + FontManager.T20.FONT_HEIGHT - 5,-1);
            FontManager.T20.drawCenteredString(String.valueOf(AutoL.kill),200, 138 + FontManager.T20.FONT_HEIGHT - 5 + FontManager.T20.FONT_HEIGHT - 5,-1);

        }

        if (gameinfo.get().equals("LangYa")) {
            GaussianBlur.startBlur();
            RoundedUtil.drawRound(80, 120, 130, 60, 0, new Color(0,0,0,80));
            GaussianBlur.endBlur(2, 2);
            RoundedUtil.drawRound(80, 120, 130, 60, 0, new Color(0,0,0,80));

            RoundedUtil.drawRound(80, 120, 130, 15, 2, new Color(0,0,0,180));
            RenderUtil.resetColor();
            ShadowUtil.drawShadow(80, 120, 130, 60);

            // 绘制文本
            FontManager.T25.drawCenteredString("Info",98, 123,-1);

            FontManager.T20.drawCenteredString("Player ID",106, 138,-1);
            FontManager.T20.drawCenteredString(getPlayerName(),180, 138,-1);

            FontManager.T20.drawCenteredString("Health",101, 138 + FontManager.T20.FONT_HEIGHT - 5,-1);
            FontManager.T20.drawCenteredString(getPlayerHealth(),200, 138 + FontManager.T20.FONT_HEIGHT - 5,-1);

            FontManager.T20.drawCenteredString("Kills",95, 138 + FontManager.T20.FONT_HEIGHT - 5 + FontManager.T20.FONT_HEIGHT - 5,-1);
            FontManager.T20.drawCenteredString(String.valueOf(AutoL.kill),200, 138 + FontManager.T20.FONT_HEIGHT - 5 + FontManager.T20.FONT_HEIGHT - 5,-1);

        }

        if (mc.player != null && mc.world != null) {
            if(thud.get().equals("Style")) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(10, 15, 0.0f);

                // draw rect
                RoundedUtil.drawRound(10, 10, 90 + mc.fontRendererObj.getStringWidth(mc.player.getName()), 60, 3, new Color(0,0,0,80));
                RoundedUtil.drawRound(10, 10, 90 + mc.fontRendererObj.getStringWidth(mc.player.getName()), 10, 3, new Color(0,0,0,130));

                // draw string
                FontManager.S20.drawCenteredString("Session",45, 10,-1);
                FontManager.S15.drawCenteredString("Played for 52m 52s",82, 25,Color.GRAY.getRGB());
                FontManager.S25.drawCenteredString(mc.player.getName(),90f, 38f,Color.GRAY.getRGB());
                FontManager.S20.drawCenteredString("0 Kills, 0 Game won.",93f, 55f,Color.GRAY.getRGB());

                // draw head
                RenderUtil.drawBigHead(13.5f, 38f, 28.0f, 28.0f, mc.player);

                GlStateManager.resetColor();
                GlStateManager.enableAlpha();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }

            if(thud.get().equals("LangYa")) {

                // @Date 2024/1/26/10/10/05
                // @Author LangYa466

                GlStateManager.pushMatrix();

                // draw rect
                GaussianBlur.startBlur();
                RoundedUtil.drawRound(10, 30, 60 + mc.fontRendererObj.getStringWidth(mc.player.getName()), 28, 0, new Color(0,0,0,130));
                GaussianBlur.endBlur(2, 2);
                RoundedUtil.drawRound(10, 30, 60 + mc.fontRendererObj.getStringWidth(mc.player.getName()), 28, 0, new Color(0,0,0,130));

                RenderUtil.resetColor();
                RoundedUtil.drawRound(40, 50,mc.player.getHealth() * 3f, 3, 1, ColorUtil.getHealthColor(mc.player.getHealth(),mc.player.getMaxHealth()));
                RenderUtil.resetColor();

                // draw shadow
                ShadowUtil.drawShadow(10, 30, 60 + mc.fontRendererObj.getStringWidth(mc.player.getName()), 28);

                // draw head
                RenderUtil.drawBigHead(13.5f, 32.5f, 23.0f, 23.0f, mc.player);

                // draw string
                FontManager.T18.drawString(mc.player.getName(), 47f, 36.0f, -1);

                GlStateManager.resetColor();
                GlStateManager.enableAlpha();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();

            }


            if(thud.get().equals("Sun")) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(10, 15, 0.0f);

                // draw rect
                RoundedUtil.drawRound(10, 10, 60 + mc.fontRendererObj.getStringWidth(mc.player.getName()), 28, 5, new Color(0,0,0,80));
                RenderUtil.resetColor();
                RoundedUtil.drawRound(40, 30,mc.player.getHealth() * 3f, 3, 1,new Color(61,131,173));
                RenderUtil.resetColor();

                // draw head
                RenderUtil.drawBigHead(13.5f, 12.5f, 23.0f, 23.0f, mc.player);

                // draw string
                FontManager.T18.drawString(mc.player.getName(), 47f, 16.0f, -1);

                GlStateManager.resetColor();
                GlStateManager.enableAlpha();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }

            /*
            for (Entity target1 : mc.world.loadedEntityList) {
                 //                 if (mc.player.getDistanceToEntity(target1) <= Sun.moduleManager.getModule(KillAura.class).getRange() && target1 != mc.player && !Teams.isSameTeam(target1) && !target1.isDead && target1 != mc.player && target1 instanceof EntityLivingBase){
                if (mc.player.getDistanceToEntity(target1) <= 3.0 && target1 != mc.player && !Teams.isSameTeam(target1) && !target1.isDead && target1 != mc.player && target1 instanceof EntityLivingBase){
                    EntityLivingBase target = (EntityLivingBase) target1;
                    final Color firstColor = ColorUtil.color(1);
                    final Color secondColor = ColorUtil.color(6);
                    double width;
                    double x = 50;
                    double y = 50;
                    final FontDrawer fr = FontManager.S20;
                    final double healthPercentage = MathHelper.clamp((target.getHealth() + target.getAbsorptionAmount()) / (target.getMaxHealth() + target.getAbsorptionAmount()), 0.0f, 1.0f);
                    width = Math.max(120, fr.getStringWidth(target.getName()) + 50);
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
                            fr.drawStringWithShadow("?", (float) (x + 17.0f - fr.getStringWidth("?") / 2.0f), (float) (y + 21.0f - fr.FONT_HEIGHT / 2.0f), mcTextColor);
                        }
                        GlStateManager.enableBlend();
                        GlStateManager.blendFunc(770, 771);
                        fr.drawStringWithShadow(target.getName(), (float) (x + 34.0f), (float) (y + 3.0f), mcTextColor);
                        final String healthText = MathUtil.round(healthPercentage * 100.0, 0) + "%";
                        fr.drawStringWithShadow(healthText, (float) (x + 17.0f + width / 2.0 - fr.getStringWidth(healthText) / 2.0f), (float) (y + 16.0f), mcTextColor);
                    }
                    if(thud.get().equals("Raven")) {
                        GlStateManager.pushMatrix();
                        GlStateManager.translate(x, y, 0.0f);
                        RoundedUtil.drawRound(0.0f, 0.0f, 70.0f + mc.fontRendererObj.getStringWidth(target.getName()), 40.0f, 12.0f, new Color(0, 0, 0, 92));
                        RenderUtil.drawOutline(8.0f, 0.0f, 62.0f + mc.fontRendererObj.getStringWidth(target.getName()), 24.0f, 8.0f, 2.0f, 6.0f, firstColor.brighter(), secondColor.brighter());
                        FontManager.T18.drawStringWithShadow(target.getName(), 7.0f, 10.0f, new Color(244, 67, 54).getRGB());
                        FontManager.T18.drawStringWithShadow(this.DF_1.format(target.getHealth()), 7.0f + mc.fontRendererObj.getStringWidth(target.getName()) + 4.0f, 10.0f, ColorUtil.getHealthColor(target.getHealth(), target.getMaxHealth()).getRGB());
                        GradientUtil.drawGradientLR(6.0f, 25.0f, (float)((int)((70.0f + mc.fontRendererObj.getStringWidth(target.getName()) - 5.0f) * (target.getHealth() / target.getMaxHealth())) - 6), 5.0f, 2.0f, firstColor.brighter(), secondColor.brighter());
                        GlStateManager.resetColor();
                        GlStateManager.enableAlpha();
                        GlStateManager.disableBlend();
                        GlStateManager.popMatrix();
                    }
                    if(thud.get().equals("Moon")) {
                        float getMaxHel = Math.min(target.getMaxHealth(), 20.0f);

                        // blur
                        RoundedUtil.drawRound((float) x, (float) y, Math.max(39.0f + getMaxHel * 3.0f, (float)(39 + FontManager.T18.getStringWidth(target.getName()))), 36.0f, 5.0f, new Color(0, 0, 0));
                        // end

                        RoundedUtil.drawRound((float) x, (float) y, Math.max(39.0f + getMaxHel * 3.0f, (float)(39 + FontManager.T18.getStringWidth(target.getName()))), 36.0f, 5.0f, new Color(0, 0, 0, 100));
                        if (target instanceof AbstractClientPlayer) {
                            this.drawBigHeadRound((float) (x + 3.0f), (float) (y + 3.0f), 30.0f, 30.0f, (AbstractClientPlayer)target);
                        }
                        FontManager.T18.drawString(target.getName(), x + 36.0f, y + 6.0f, new Color(255, 255, 255).getRGB());
                        double nmsl;
                        if (target.getHealth() - Math.floor(target.getHealth()) >= 0.5) {
                            nmsl = 0.5;
                        }
                        else {
                            nmsl = 0.0;
                        }
                        FontManager.T15.drawString(Math.floor(target.getHealth()) + nmsl + " HP", x + 36.0f, y + 6.0f + FontManager.T18.getHeight(), new Color(255, 255, 255).getRGB());
                        target.animatedHealthBar = AnimationUtil.animate(target.animatedHealthBar, target.getHealth(), 0.15f);
                        RoundedUtil.drawRound((float) (x + 36.0f), (float) (y + 16.0f + FontManager.T15.getHeight()), target.animatedHealthBar / target.getMaxHealth() * Math.max(getMaxHel * 3.0f, (float)FontManager.T18.getStringWidth(target.getName())), 5.0f, 2.5f, ColorUtil.color(8));
                        break;
                    }
                    if (thud.get().equals("Neon")) {
                        width = (float) Math.max(128, FontManager.C20.getStringWidth("Name: " + target.getName()) + 60);
                        Gui.drawRect3((double) x, (double) y, (double) width, 50.0, new Color(19, 19, 19, 180).getRGB());
                        RenderUtil.drawHGradientRect(x, y, width, 1.0, firstColor.getRGB(), secondColor.getRGB());
                        final int textColor = -1;
                        Gui.drawRect3((double) x, (double) y, (double) width, 50.0, new Color(0, 0, 0, (int) (110.0f * 1)).getRGB());
                        final int scaleOffset = (int) (target.hurtTime * 0.35f);
                        final float healthPercent = (target.getHealth() + target.getAbsorptionAmount()) / (target.getMaxHealth() + target.getAbsorptionAmount());
                        final float var = (float) ((width - 28.0f) * healthPercent);
                        target.animatedHealthBar = AnimationUtil.animate(target.animatedHealthBar, var, 0.1f);
                        RenderUtil.drawHGradientRect(x + 5.0f, y + 40.0f, target.animatedHealthBar, 5.0, firstColor.getRGB(), secondColor.getRGB());
                        if (target instanceof AbstractClientPlayer) {
                            GlStateManager.pushMatrix();
                            RenderUtil.drawBigHead((float) (x + 5.0f + scaleOffset / 2.0f), (float) (y + 5.0f + scaleOffset / 2.0f), (float) (30 - scaleOffset), (float) (30 - scaleOffset), (AbstractClientPlayer) target);
                            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                            GlStateManager.popMatrix();
                        } else {
                            GlStateManager.pushMatrix();
                            GlStateManager.translate(x + 5.0f + scaleOffset / 2.0f + 15.0f, y + 5.0f + scaleOffset / 2.0f + 25.0f, 40.0f);
                            GlStateManager.scale(0.31, 0.31, 0.31);
                            drawModel(target.rotationYaw, target.rotationPitch, target);
                            GlStateManager.popMatrix();
                        }
                        final double healthNum = MathUtil.round(target.getHealth() + target.getAbsorptionAmount(), 1);
                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                        FontManager.S20.drawString(String.valueOf(healthNum), (int) (x + target.animatedHealthBar + 8.0f), (int) (y + 38.0f), textColor);
                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                        FontManager.C20.drawString("Name: " + target.getName(), (int) (x + 40.0f), (int) (y + 8.0f), textColor);
                        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                        FontManager.S20.drawString("Distance: " + MathUtil.round(mc.player.getDistanceToEntity((Entity) target), 1), (int) (x + 40.0f), (int) (y + 20.0f), textColor);
                        break;
                    }
                }
            }

             */

        }
    }

    private String getPlayerHealth() {

        return ( (int) (mc.player.getHealth() / mc.player.getMaxHealth() * 100)) + "%";
    }

    private String getPlayerName() {
        String name;
        String playername = mc.player.getName();
        if(playername.length() > 11) {
            name = playername.substring(0, playername.length() - 3) + "??";
        } else {
            name = playername;
        }
        return name;
    }

    /*


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

    protected void drawBigHeadRound(final float x, final float y, final float width, final float height, final AbstractClientPlayer player) {
        StencilUtil.initStencilToWrite();
        RenderUtil.renderRoundedRect(x, y, width, height, 4.0f, -1);
        StencilUtil.readStencilBuffer(1);
        RenderUtil.color(-1);
        drawBigHead(x, y, width, height, player);
        StencilUtil.uninitStencilBuffer();
        GlStateManager.disableBlend();
    }

    protected void drawBigHead(final float x, final float y, final float width, final float height, final AbstractClientPlayer player) {
        final double offset = -(player.hurtTime * 23);
        RenderUtil.glColor(new Color(255, (int)(255.0 + offset), (int)(255.0 + offset)).getRGB());
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        mc.getTextureManager().bindTexture(player.getLocationSkin());
        Gui.drawScaledCustomSizeModalRect(x, y, 8.0f, 8.0f, 8, 8, width, height, 64.0f, 64.0f);
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
    }

     */

}
