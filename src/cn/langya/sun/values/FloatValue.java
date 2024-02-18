package cn.langya.sun.values;

public class FloatValue extends AbstractValue<Float> {
    private final String name;
    private float number;
    private final float maximum;
    private final float minimum;
    public FloatValue(String name, float defaultValue, float maximum, float minimum) {
        super(name);
        this.name = name;
        this.number = defaultValue;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    @Override
    public Float get() {
        return this.number;
    }

    @Override
    public void set(Float value) {
        this.number = value;
    }

    public String getName() {
        return name;
    }

    public float getMaximum() {
        return maximum;
    }

    public float getMinimum() {
        return minimum;
    }
}
