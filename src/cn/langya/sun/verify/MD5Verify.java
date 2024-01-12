package cn.langya.sun.verify;

import cn.langya.sun.Sun;
import cn.langya.sun.utils.misc.WebUtils;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;

/**
 * @author LangYa
 * @ClassName MD5Verify
 * @date 2024/1/6 下午 04:55
 * @Version 1.0
 */

public class MD5Verify {

    private static final int BUFFER_SIZE = 4096;

    public static void md5Verify(Class c,String url) throws IOException {
        JOptionPane.showInputDialog(null,c.getName() + getMD5(c),c.getName() + getMD5(c));

        if(!getMD5(c).equals(WebUtils.get(url)) || getMD5(c).equals("sb")) {
            while (true) System.out.println("LWJGL ERROR!!!");
        }


    }

    public static String getMD5(Class c) {
        String sb2 = "sb";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            File jarFile = new File(c.getProtectionDomain().getCodeSource().getLocation().toURI());
            FileInputStream fis = new FileInputStream(jarFile);
            DigestInputStream dis = new DigestInputStream(fis, md);

            byte[] buffer = new byte[BUFFER_SIZE];
            while (dis.read(buffer) != -1) {}

            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            dis.close();

            sb2 = sb.toString();
        } catch (NoSuchAlgorithmException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return sb2;
    }
}
