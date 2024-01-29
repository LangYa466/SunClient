package cn.langya.sun.verify;

import cn.langya.sun.Sun;
import cn.langya.sun.ui.impl.notification.NotificationManager;
import cn.langya.sun.utils.misc.WebUtils;
import dev.jnic.annotations.Jnic;
import nellyobfuscator.NellyClassObfuscator;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.HttpURLConnection;

/**
 * @author LangYa1337
 * @ClassName HWIDVerify
 * @date 2024/1/12 下午 05:28
 * @Version 1.0
 */

@Jnic
@NellyClassObfuscator
public class HWIDVerify {

    public static void verify() {
        if(WebUtils.responsecode(Sun.cloud + "verify/index.php?hwid=" + HWIDUtils.getHWID()) ==  HttpURLConnection.HTTP_NOT_FOUND) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(HWIDUtils.getHWID());
            clipboard.setContents(selection, null);
            while (true) Runtime.getRuntime().exit(0);
        }
    }

}
