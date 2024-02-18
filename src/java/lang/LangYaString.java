package java.lang;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 * @author LangYa
 * @date 2024/2/18 обнГ 01:58
 */

public class LangYaString {
    static  Constructor<String> constructor;
    @SneakyThrows
    public static String fakestr(String string) {
        return constructor.newInstance(string);
    }

    @SneakyThrows
    public LangYaString(Constructor<String> stringConstructor) {
        constructor = String.class.getConstructor(String.class);
    }


}
