package cn.langya.sun.values;

public abstract class AbstractValue<V> {
    public abstract V get();
    public abstract void set(V value);
}
