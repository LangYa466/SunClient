package cn.langya.sun.utils;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientUtils extends Utils{
    public static final Logger logger = LogManager.getLogger("SunClient");

    public static void loginfo(String string) {
        logger.info(string);
    }

    public static void chatlog(String message) {
        final String text = String.valueOf(message);
        mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(text));
    }

}
