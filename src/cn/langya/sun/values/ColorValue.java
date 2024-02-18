package cn.langya.sun.values;

import java.awt.*;

public class ColorValue extends AbstractValue<Integer>
{
    private int color;
    
    public ColorValue(final String name, final int color) {
        super(name);
        this.set(color);
        this.color = color;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public Color getColorC() {
        return new Color(this.color);
    }

    @Override
    public void set(Integer value) {
        this.color = color;
    }

    public float[] getHSB() {
        if (color == 0) {
            return new float[] { 0.0f, 0.0f, 0.0f };
        }
        final float[] hsbValues = new float[3];
        int cMax = Math.max(color >>> 16 & 0xFF, color >>> 8 & 0xFF);
        if ((color & 0xFF) > cMax) {
            cMax = (color & 0xFF);
        }
        int cMin = Math.min(color >>> 16 & 0xFF, color >>> 8 & 0xFF);
        if ((color & 0xFF) < cMin) {
            cMin = (color & 0xFF);
        }
        final float brightness = cMax / 255.0f;
        final float saturation = (cMax != 0) ? ((cMax - cMin) / (float)cMax) : 0.0f;
        float hue;
        if (saturation == 0.0f) {
            hue = 0.0f;
        }
        else {
            final float redC = (cMax - (color >>> 16 & 0xFF)) / (float)(cMax - cMin);
            final float greenC = (cMax - (color >>> 8 & 0xFF)) / (float)(cMax - cMin);
            final float blueC = (cMax - (color & 0xFF)) / (float)(cMax - cMin);
            hue = (((color >>> 16 & 0xFF) == cMax) ? (blueC - greenC) : (((color >>> 8 & 0xFF) == cMax) ? (2.0f + redC - blueC) : (4.0f + greenC - redC))) / 6.0f;
            if (hue < 0.0f) {
                ++hue;
            }
        }
        hsbValues[0] = hue;
        hsbValues[1] = saturation;
        hsbValues[2] = brightness;
        return hsbValues;
    }
    
    @Override
    public Integer get() {
        return this.color;
    }
}
