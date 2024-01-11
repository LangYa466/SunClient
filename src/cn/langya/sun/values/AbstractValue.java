package cn.langya.sun.values;

public abstract class AbstractValue<V> {
    public String name;
    public abstract V get();
    public abstract void set(V value);
}
