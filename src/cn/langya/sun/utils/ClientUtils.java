package cn.langya.sun.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientUtils {
    private static final Logger logger = LogManager.getLogger("太阳客户端");

    public static void loginfo(String string) {
        logger.info("(太阳客户端) - " + string);
    }
}
