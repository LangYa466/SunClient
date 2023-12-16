package cn.langya.sun.modules

/**
 * @author LangYa
 * @ClassName Module
 * @date 2023/12/14 21:10
 * @Version 1.0
 */

enum class Category(val displayName: String) {
    Combat("战斗"),
    Move("移动"),
    Render("视觉"),
    Client("客户端"),
    Misc("其他"),
}