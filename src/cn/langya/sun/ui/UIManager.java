package cn.langya.sun.ui;

import cn.langya.sun.Sun;
import cn.langya.sun.modules.impl.render.*;
import cn.langya.sun.ui.impl.notification.*;
import cn.langya.sun.values.AbstractValue;

import java.util.List;

public class UIManager {

    public List<Ui> ui = new java.util.ArrayList<>();


    public UIManager() {}

    void addUi(Ui uI) {
        ui.add(uI);
        Sun.eventManager.register(uI);
    }

    public void init() throws InstantiationException, IllegalAccessException {
        addUi(new ArrayList());
        addUi(new Text());
    }

    public void addNotification(String title, String content, NotificationType type) {
        NotificationManager.add(title, content, type);
    }

    private List<AbstractValue> values = new java.util.ArrayList<>();
    public void add(AbstractValue value) {
        values.add(value);
    }
    public List<AbstractValue> getValues() {
        return values;
    }



}
