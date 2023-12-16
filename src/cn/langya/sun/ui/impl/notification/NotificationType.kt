package cn.langya.sun.ui.impl.notification

import java.awt.*;

enum class NotificationType(var renderColor:Color) {
    SUCCESS(Color(0x60E092)),
    ERROR(Color(0xFF2F2F)),
    WARNING(Color(0xF5FD00)),
    INFO(Color(0x6490A7));
}
