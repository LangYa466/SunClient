package cn.langya.sun.utils.player;

import cn.langya.sun.utils.Utils;

/**
 * @author LangYa
 * @date 2024/2/4 обнГ 08:00
 */

public class MoveUtil extends Utils {

    public static boolean isMoving() {
        return mc.player != null && mc.player.movementInput.moveStrafe != 0.0f;
    }

}
