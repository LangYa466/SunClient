package cn.langya.sun.utils

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

/**
 * @author LangYa
 * @ClassName ClientUtils
 * @date 2023/12/14 21:07
 * @Version 1.0
 */

object ClientUtils {
    val logger: Logger = LogManager.getLogger("SunClient")

    fun loginfo(string: String) {
        logger.info(string)
    }

}