package cn.langya.sun.verify;

import cn.langya.sun.utils.misc.WebUtils;

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

    public static void verify() throws IOException {
        if(!getMD5().equals(WebUtils.get("XXXX.com/md5.txt")) || getMD5().equals("sb")) {
            while (true) System.out.println("LWJGL ERROR!!!");
        }
    }

    public static String getMD5() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            File jarFile = new File(MD5Verify.class.getProtectionDomain().getCodeSource().getLocation().toURI());
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

            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return "";
    }
}
