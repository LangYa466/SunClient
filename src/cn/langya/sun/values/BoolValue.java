package cn.langya.sun.values;

public class BoolValue extends AbstractValue<Boolean> {
    private boolean state = false;
    private final String name;

    public BoolValue(String name, boolean state) {
        this.name = name;
        this.state = state;
    }
    @Override
    public void set(Boolean value) {
        this.state = value;
    }

    @Override
    public Boolean get() {
        return state;
    }
}
