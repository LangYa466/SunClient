// 代码来自派蒙大神

package cn.langya.sun.ui.screen.clickgui;

import cn.langya.sun.Sun;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.shit.MathUtil;
import cn.langya.sun.ui.FontManager;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.values.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import org.lwjgl.input.*;
import net.minecraft.util.*;
import java.util.*;
import java.io.*;
import java.util.List;

public class DropdownClickGUI extends GuiScreen
{
    List<Float> posX;
    List<Float> posY;
    List<Module> inSetting;
    List<AbstractValue<?>> inMode;

    public List<Float> cGUIPosX = new ArrayList<Float>();
    public List<Float> cGUIPosY = new ArrayList<Float>();
    public List<Module> cGUIInSetting = new ArrayList<Module>();
    public List<AbstractValue<?>> cGUIInMode  = new ArrayList<AbstractValue<?>>();

    
    public DropdownClickGUI() {
        this.posX = new ArrayList<Float>();
        this.posY = new ArrayList<Float>();
        this.inSetting = new ArrayList<Module>();
        this.inMode = new ArrayList<AbstractValue<?>>();
    }
    
    public void initGui() {
        this.posX = (List<Float>)cGUIPosX;
        this.posY = (List<Float>)cGUIPosY;
        this.inSetting = (List<Module>)cGUIInSetting;
        this.inMode = (List<AbstractValue<?>>)cGUIInMode;
    }
    
    public void onGuiClosed() {
      //  Sun.configManager.saveAllConfigs();
        cGUIPosX = this.posX;
        cGUIPosY = this.posY;
        cGUIInSetting = this.inSetting;
        cGUIInMode = this.inMode;
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        RenderUtil.drawRect(0.0, 0.0, this.width, this.height, new Color(0, 0, 0, 100).getRGB());
        int cOrder = -1;
        for (final Category c : Category.values()) {
            ++cOrder;
            final String category = c.toString();
            try {
                this.posY.get(cOrder);
            }
            catch (Exception e2) {
                this.posY.add(5.0f);
            }
            try {
                this.posX.get(cOrder);
            }
            catch (Exception e2) {
                this.posX.add(cOrder * 120 + 80.0f);
            }
            final float x = this.posX.get(cOrder);
            final float y = this.posY.get(cOrder);
            RenderUtil.drawRect(x, y, x + 110.0f, y + 15.0f, new Color(255, 255, 255).getRGB());
            FontManager.drawString(category, (int) (x + 55.0f), (int) (y + 5.0f), new Color(30, 30, 30).getRGB());
            int mOrder = -1;
            float settingY = 0.0f;
            for (final Module m : Sun.moduleManager.getModsByCategory(c)) {
                ++mOrder;
                final boolean isOnModule = mouseX > x && mouseY > y + 15.0f + settingY + mOrder * 20 && mouseX < x + 110.0f && mouseY < y + mOrder * 20 + 35.0f + settingY;
                final int backgroundColor = isOnModule ? (m.state ? new Color(140, 190, 240).getRGB() : new Color(240, 240, 240).getRGB()) : (m.state ? new Color(150, 200, 250).getRGB() : new Color(250, 250, 250).getRGB());
                RenderUtil.drawRect(x, y + 15.0f + mOrder * 20 + settingY, x + 110.0f, y + mOrder * 20 + 35.0f + settingY, backgroundColor);
                FontManager.drawString(m.name, (int) (x + 55.0f), (int) (y + 22.0f + mOrder * 20 + settingY),  Color.BLACK.getRGB());
                if (this.inSetting.contains(m)) {
                    int currentSettingY = 0;
                    for (final AbstractValue<?> v : m.getValues()) {
                        RenderUtil.drawRect(x, y + mOrder * 20 + 35.0f + settingY + currentSettingY, x + 110.0f, y + mOrder * 20 + 35.0f + settingY + currentSettingY + 15.0f, m.state ? new Color(140, 190, 240).getRGB() : new Color(240, 240, 240).getRGB());
                        FontManager.drawString(v.name, (int) (x + 1.0f), (int) (y + mOrder * 20 + 35.0f + settingY + currentSettingY + 5.0f), new Color(50, 50, 50).getRGB());
                        if (v instanceof StringValue) {
                            RenderUtil.drawRect(x + 50.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 108.0f, y + mOrder * 20 + 48.0f + settingY + currentSettingY, m.state ? new Color(155, 205, 255).getRGB() : new Color(255, 255, 255).getRGB());
                            if (this.inMode.contains(v)) {
                                RenderUtil.drawTriangle(x + 100.0f, y + mOrder * 20 + 39.0f + settingY + currentSettingY, x + 100.0f, y + mOrder * 20 + 46.0f + settingY + currentSettingY, x + 106.0f, y + mOrder * 20 + 42.5 + settingY + currentSettingY, new Color(70, 70, 70).getRGB());
                            }
                            else {
                                RenderUtil.drawTriangle(x + 100.0f, y + mOrder * 20 + 40.0f + settingY + currentSettingY, x + 103.0f, y + mOrder * 20 + 46.0f + settingY + currentSettingY, x + 106.0f, y + mOrder * 20 + 40.0f + settingY + currentSettingY, new Color(70, 70, 70).getRGB());
                            }
                            FontManager.drawString(v.get().toString(), (int) (x + 75), (int) (y + mOrder * 20 + 40 + settingY + currentSettingY), new Color(30, 30, 30).getRGB());
                            if (this.inMode.contains(v)) {
                                RenderUtil.drawShadow(x + 115.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 160.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY + ((StringValue)v).getValues().size() * 10);
                                int modeOrder = 0;
                                for (final String e : ((StringValue)v).getValues()) {
                                    RenderUtil.drawRect(x + 115.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY + modeOrder * 10, x + 160.0f, y + mOrder * 20 + 47.0f + settingY + currentSettingY + modeOrder * 10, m.state ? new Color(140, 190, 240).getRGB() : new Color(240, 240, 240).getRGB());
                                    if (v.get() == e) {
                                        FontManager.drawString(e, (int) (x + 116.0f), (int) (y + mOrder * 20 + 40.5f + settingY + currentSettingY + modeOrder * 10), new Color(30, 30, 30).getRGB());
                                    }
                                    else {
                                        float v1 = y + mOrder * 20 + 40.5f + settingY + currentSettingY + modeOrder * 10;
                                        FontManager.drawString(e, (int) (x + 116.0f), (int) v1, new Color(150, 150, 150).getRGB());
                                    }
                                    ++modeOrder;
                                }
                            }
                        }
                        if (v instanceof IntValue) {
                            final IntValue n = (IntValue)v;
                            final double diffMaxMin = ((IntValue)v).getMaximum() - ((IntValue)v).getMinimum();
                            final double diffValMin = n.get() - ((IntValue)v).getMinimum();
                            RenderUtil.drawRect(x + 50.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 108.0f, y + mOrder * 20 + 48.0f + settingY + currentSettingY, m.state ? new Color(155, 205, 255).getRGB() : new Color(255, 255, 255).getRGB());
                            RenderUtil.drawRect(x + 50.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 50.0f + 58.0 * diffValMin / diffMaxMin, y + mOrder * 20 + 48.0f + settingY + currentSettingY, m.state ? new Color(255, 255, 255).getRGB() : new Color(155, 205, 255).getRGB());
                            FontManager.drawString(v.get().toString(), (int) (x + 107.0f - FontManager.getStringWidth(v.get().toString())), (int) (y + mOrder * 20 + 41.0f + settingY + currentSettingY), new Color(30, 30, 30).getRGB());
                            if (Mouse.isButtonDown(0) && mouseX >= x + 50.0f && mouseY >= y + mOrder * 20 + 37.0f + settingY + currentSettingY && mouseX <= x + 108.0f && mouseY <= y + mOrder * 20 + 48.0f + settingY + currentSettingY) {
                                n.set((int) MathUtil.round((mouseX - x - 50.0f) / 58.0f * (diffMaxMin + 1.0) + n.getMinimum(), 1));
                                if (n.get() > n.getMaximum()) {
                                    n.set(n.getMaximum());
                                }
                                if (n.get() < n.getMinimum()) {
                                    n.set(n.getMinimum());
                                }
                            }
                        }
                        if (v instanceof DoubleValue) {
                            final DoubleValue n = (DoubleValue)v;
                            final double diffMaxMin = ((DoubleValue)v).getMaximum() - ((DoubleValue)v).getMinimum();
                            final double diffValMin = n.get() - ((DoubleValue)v).getMinimum();
                            RenderUtil.drawRect(x + 50.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 108.0f, y + mOrder * 20 + 48.0f + settingY + currentSettingY, m.state ? new Color(155, 205, 255).getRGB() : new Color(255, 255, 255).getRGB());
                            RenderUtil.drawRect(x + 50.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 50.0f + 58.0 * diffValMin / diffMaxMin, y + mOrder * 20 + 48.0f + settingY + currentSettingY, m.state ? new Color(255, 255, 255).getRGB() : new Color(155, 205, 255).getRGB());
                            FontManager.drawString(v.get().toString(), (int) (x + 107.0f - FontManager.getStringWidth(v.get().toString())), (int) (y + mOrder * 20 + 41.0f + settingY + currentSettingY), new Color(30, 30, 30).getRGB());
                            if (Mouse.isButtonDown(0) && mouseX >= x + 50.0f && mouseY >= y + mOrder * 20 + 37.0f + settingY + currentSettingY && mouseX <= x + 108.0f && mouseY <= y + mOrder * 20 + 48.0f + settingY + currentSettingY) {
                                n.set( MathUtil.round((mouseX - x - 50.0f) / 58.0f * (diffMaxMin + 1.0) + n.getMinimum(), 1));
                                if (n.get() > n.getMaximum()) {
                                    n.set(n.getMaximum());
                                }
                                if (n.get() < n.getMinimum()) {
                                    n.set(n.getMinimum());
                                }
                            }
                        }
                        if (v instanceof FloatValue) {
                            final FloatValue n = (FloatValue)v;
                            final double diffMaxMin = ((FloatValue)v).getMaximum() - ((FloatValue)v).getMinimum();
                            final double diffValMin = n.get() - ((FloatValue)v).getMinimum();
                            RenderUtil.drawRect(x + 50.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 108.0f, y + mOrder * 20 + 48.0f + settingY + currentSettingY, m.state ? new Color(155, 205, 255).getRGB() : new Color(255, 255, 255).getRGB());
                            RenderUtil.drawRect(x + 50.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 50.0f + 58.0 * diffValMin / diffMaxMin, y + mOrder * 20 + 48.0f + settingY + currentSettingY, m.state ? new Color(255, 255, 255).getRGB() : new Color(155, 205, 255).getRGB());
                            FontManager.drawString(v.get().toString(), (int) (x + 107.0f - FontManager.getStringWidth(v.get().toString())), (int) (y + mOrder * 20 + 41.0f + settingY + currentSettingY), new Color(30, 30, 30).getRGB());
                            if (Mouse.isButtonDown(0) && mouseX >= x + 50.0f && mouseY >= y + mOrder * 20 + 37.0f + settingY + currentSettingY && mouseX <= x + 108.0f && mouseY <= y + mOrder * 20 + 48.0f + settingY + currentSettingY) {
                                n.set((float)MathUtil.round((mouseX - x - 50.0f) / 58.0f * (diffMaxMin + 1.0) + n.getMinimum(), 1));
                                if (n.get() > n.getMaximum()) {
                                    n.set(n.getMaximum());
                                }
                                if (n.get() < n.getMinimum()) {
                                    n.set(n.getMinimum());
                                }
                            }
                        }
                        if (v instanceof BoolValue) {
                            final BoolValue o = (BoolValue)v;
                            RenderUtil.drawRect(x + 97.0f, y + mOrder * 20 + 37.0f + settingY + currentSettingY, x + 108.0f, y + mOrder * 20 + 48.0f + settingY + currentSettingY, m.state ? (o.get() ? new Color(160, 210, 255).getRGB() : new Color(110, 160, 210).getRGB()) : (o.get() ? new Color(255, 255, 255).getRGB() : new Color(210, 210, 210).getRGB()));
                        }
                        currentSettingY += 15;
                    }
                    RenderUtil.drawTexturedRect(x, y + mOrder * 20 + 35.0f + settingY, 110.0f, 9.0f, new ResourceLocation("shaders/panelbottom.png"), Color.white.getRGB());
                    settingY += currentSettingY;
                }
            }
            RenderUtil.drawShadow(x, y, x + 110.0f, y + mOrder * 20 + 35.0f + settingY);
            RenderUtil.drawTexturedRect(x, y + 15.0f, 110.0f, 9.0f, new ResourceLocation("shaders/panelbottom.png"), Color.white.getRGB());
            final boolean isOnCategory = mouseX > x && mouseY > y && mouseX < x + 110.0f && mouseY < y + 15.0f;
            if (Mouse.isButtonDown(0) && isOnCategory) {
                this.posX.set(cOrder, x + Mouse.getDX() / 2.0f);
                this.posY.set(cOrder, y - Mouse.getDY() / 2.0f);
            }
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        int cOrder = -1;
        for (final Category c : Category.values()) {
            ++cOrder;
            final float x = this.posX.get(cOrder);
            final float y = this.posY.get(cOrder);
            int mOrder = -1;
            float settingY = 0.0f;
            for (final Module m : Sun.moduleManager.getModsByCategory(c)) {
                ++mOrder;
                final boolean isOnModule = mouseX > x && mouseY > y + 15.0f + settingY + mOrder * 20 && mouseX < x + 110.0f && mouseY < y + mOrder * 20 + 35.0f + settingY;
                if (isOnModule && mouseButton == 0) {
                    m.setState(!m.state);
                }
                if (isOnModule && mouseButton == 1 && !m.getValues().isEmpty()) {
                    if (this.inSetting.contains(m)) {
                        this.inSetting.remove(m);
                    }
                    else {
                        this.inSetting.add(m);
                    }
                }
                if (this.inSetting.contains(m)) {
                    int currentSettingY = 0;
                    for (final AbstractValue<?> v : m.getValues()) {
                        if (v instanceof StringValue) {
                            if (mouseX > x + 50.0f && mouseY > y + mOrder * 20 + 37.0f + settingY + currentSettingY && mouseX < x + 108.0f && mouseY < y + mOrder * 20 + 48.0f + settingY + currentSettingY) {
                                if (this.inMode.contains(v)) {
                                    this.inMode.remove(v);
                                }
                                else {
                                    this.inMode.add(v);
                                }
                            }
                            if (this.inMode.contains(v)) {
                                int modeOrder = 0;
                                for (final String e : ((StringValue)v).getValues()) {
                                    if (mouseX > x + 115.0f && mouseY > y + mOrder * 20 + 37.0f + settingY + currentSettingY + modeOrder * 10 && mouseX < x + 160.0f && mouseY < y + mOrder * 20 + 47.0f + settingY + currentSettingY + modeOrder * 10) {
                                        ((StringValue)v).set(e);
                                    }
                                    ++modeOrder;
                                }
                            }
                        }
                        if (v instanceof IntValue) {}
                        if (v instanceof FloatValue) {}
                        if (v instanceof DoubleValue) {}
                        if (v instanceof BoolValue) {
                            final BoolValue o = (BoolValue)v;
                            if (mouseX > x + 97.0f && mouseY > y + mOrder * 20 + 37.0f + settingY + currentSettingY && mouseX < x + 108.0f && mouseY < y + mOrder * 20 + 48.0f + settingY + currentSettingY) {
                                o.set(!o.get());
                            }
                        }
                        currentSettingY += 15;
                    }
                    settingY += currentSettingY;
                }
            }
            final boolean isOnCategory = mouseX > x && mouseY > y && mouseX < x + 110.0f && mouseY < y + 15.0f;
            if (Mouse.isButtonDown(0) && isOnCategory) {
                this.posX.set(cOrder, x + Mouse.getDX() / 2.0f);
                this.posY.set(cOrder, y - Mouse.getDY() / 2.0f);
            }
        }
    }
}
