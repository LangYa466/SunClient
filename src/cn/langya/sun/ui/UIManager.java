package cn.langya.sun.ui;

import cn.langya.sun.Sun;
import cn.langya.sun.modules.impl.render.ArrayList;
import cn.langya.sun.ui.impl.notification.NotificationManager;
import cn.langya.sun.ui.impl.notification.NotificationType;

import java.util.List;

public class UIManager {

    public List<Ui> ui = new java.util.ArrayList<>();


    public UIManager() {}

    void addUi(Ui uI) {
        ui.add(uI);
        Sun.eventManager.register(uI);
    }

    public void init() {
        addUi(new ArrayList(0,0,0,0));
    }

    public void addNotification(String title, String content, NotificationType type) {
        NotificationManager.add(title, content, type);
    }


}
