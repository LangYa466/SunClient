package cn.langya.sun.values;

public class IntValue {
    public final String name;
    public int number;

    public IntValue(String name, int defaultValue, int max, int min) {
        this.name = name;
        this.number = defaultValue;
    }

    public void set(int value) {
        this.number = value;
    }

    public int get() {
        return this.number;
    }
}
