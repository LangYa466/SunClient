package cn.langya.sun.files;

import cn.langya.sun.files.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LangYa
 * @ClassName ConfigManager
 * @date 2024/1/7 下午 03:28
 * @Version 1.0
 */

public class ConfigManager {
    public static final List<Config> configs = new ArrayList<>();

    public void loadAllConfigs() {
        for (Config config : configs) {
            config.load();
        }
    }

    public void saveAllConfigs() {
        for (Config config : configs) {
            config.save();
        }
    }

    private static void registerConfig(Config config) {
        configs.add(config);
    }

}
