package cn.langya.sun.ui.lunar.util;


import net.minecraft.util.math.MathHelper;

public class ClientMathUtils {

    private static final double[] a = new double[65536];

    private static final double[] b = new double[360];

    static {
        int i;
        for (i = 0; i < 65536; i++)
            a[i] = Math.sin(i * Math.PI * 2.0D / 65536.0D);
        for (i = 0; i < 360; i++)
            b[i] = Math.sin(Math.toRadians(i));
    }

    public static double getAngle(int paramInt) {
        paramInt %= 360;
        return b[paramInt];
    }

    public static double getRightAngle(int paramInt) {
        paramInt += 90;
        paramInt %= 360;
        return b[paramInt];
    }




}
