package cn.langya.sun.value;

public class BoolValue extends ValueManager {
    private boolean state = false;
    private String name = null;

    public BoolValue(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    public void set(boolean value) {
        this.state = value;
    }

    public boolean get() {
        return this.state;
    }
}
