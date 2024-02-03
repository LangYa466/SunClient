package cn.langya.sun.values;

/**
 * @author LangYa
 * @date 2024/2/3 обнГ 04:43
 */

public class StringValue extends AbstractValue {
    private final String name;
    private String value;


    public StringValue(String name,String value) {
        super(name);
        this.name = name;
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public void set(Object value) {
        if(value.toString().isEmpty()) return;

        this.value = (String) value;


    }
}
