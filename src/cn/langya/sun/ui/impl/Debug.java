//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Downloads\Minecraft1.12.2 Mappings"!

//Decompiled by Procyon!

package cn.langya.sun.ui.impl;


public class Debug{

    
    private float toFloat(final double value) {
        return (int)value + (int)(value * 10.0) % 10 / 10.0f;
    }
    
    private float toDouble(final double value) {
        return (int)value + (int)(value * 100.0) % 100 / 100.0f;
    }
}
