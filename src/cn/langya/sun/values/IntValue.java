package cn.langya.sun.values;

public class IntValue extends AbstractValue<Integer> {
    private final String name;
    private Integer number;
    private final int maximum;
    private final int minimum;
    public IntValue(String name, int defaultValue, int maximum, int minimum) {
        super(name);
        this.name = name;
        this.number = defaultValue;
        this.maximum = maximum;
        this.minimum = minimum;
    }
    @Override
    public Integer get() {
        return this.number;
    }

    @Override
    public void set(Integer value) {
        this.number = value;
    }

    public String getName() {
        return name;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getMinimum() {
        return minimum;
    }
}
