package cn.langya.sun.values;

public class DoubleValue extends AbstractValue<Double> {
    private final String name;
    private Double number;
    private final double maximum;
    private final double minimum;
    public DoubleValue(String name, double defaultValue, double maximum, double minimum) {
        super(name);
        this.name = name;
        this.number = defaultValue;
        this.maximum = maximum;
        this.minimum = minimum;
    }
    @Override
    public Double get() {
        return this.number;
    }

    @Override
    public void set(Double value) {
        this.number = value;
    }

    public String getName() {
        return name;
    }

    public double getMaximum() {
        return maximum;
    }

    public double getMinimum() {
        return minimum;
    }
}
