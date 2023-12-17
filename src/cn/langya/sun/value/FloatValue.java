package cn.langya.sun.value;

public class FloatValue {
    private String name;
    private float number;

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
