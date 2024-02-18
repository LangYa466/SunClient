package cn.langya.sun.verify.impl;

import cn.langya.sun.verify.Verify;

import java.lang.reflect.Method;
import java.security.MessageDigest;

/**
 * @author LangYa
 * @date 2024/2/18 ÉÏÎç 11:11
 */

public class HWIDVerify extends Verify {

    @Override
    public void verify() {
    }

    public String getHWID() {
        try {
            Class<?> systemClass = Class.forName("java.lang.System");
            Method getenvMethod = systemClass.getMethod("getenv", String.class);
            String computerName = (String) getenvMethod.invoke(null, "COMPUTERNAME");
            String userName = System.getProperty("user.name");
            String processorIdentifier = (String) getenvMethod.invoke(null, "PROCESSOR_IDENTIFIER");
            String processorLevel = (String) getenvMethod.invoke(null, "PROCESSOR_LEVEL");

            String toEncrypt = computerName + userName + processorIdentifier + processorLevel;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toEncrypt.getBytes());
            byte[] byteData = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return new LangYaString(String.class.getConstructor(String.class)).fakestr(hexString.toString());
        } catch (Exception e) {
            e.printStackTrace();
            crash();
            return null;
        }
    }

}
