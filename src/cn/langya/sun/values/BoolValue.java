package cn.langya.sun.values;

public class BoolValue extends ValueManager {
    public boolean state = false;
    public String name = null;

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
