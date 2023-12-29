package cn.langya.sun.values;

public class FloatValue {
    public final String name;
    public float number;

    public FloatValue(String name, float defaultValue, float max, float min) {
        this.name = name;
        this.number = defaultValue;
    }

    public void set(float value) {
        this.number = value;
    }

    public float get() {
        return this.number;
    }
}
