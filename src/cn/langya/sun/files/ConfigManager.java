package cn.langya.sun.files;

import cn.langya.sun.Sun;
import cn.langya.sun.files.Config;
import cn.langya.sun.files.configs.ModuleConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LangYa
 * @ClassName ConfigManager
 * @date 2024/1/7 下午 03:28
 * @Version 1.0
 */

public class ConfigManager {
    private final List<Config> configs = new ArrayList<>();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public File dir = new File(Sun.fold, "config");
    public ConfigManager() {
        if (!dir.exists()){
            dir.mkdir();
        }
        configs.add(new ModuleConfig("modules.json"));
    }

    public void loadConfig(String name) {
        File file = new File(dir, name);
        if (file.exists()) {
            System.out.println("Loading config: " + name);
            for (Config config : configs) {
                if (config.name.equals(name)) {
                    try {
                        config.load(new JsonParser().parse(new FileReader(file)).getAsJsonObject());
                        break;
                    } catch (FileNotFoundException e) {
                        System.out.println("Failed to load config: " + name);
                        e.printStackTrace();
                        break;
                    }
                }
            }
        } else {
            System.out.println("Config " + name + " doesn't exist, creating a new one...");
            saveConfig(name);
        }
    }
    public void saveConfig(String name) {
        File file = new File(dir, name);
        try {
            System.out.println("Saving config: " + name);
            file.createNewFile();
            for (Config config : configs) {
                if (config.name.equals(name)) {
                    FileUtils.writeByteArrayToFile(file, gson.toJson(config.save()).getBytes(StandardCharsets.UTF_8));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save config: " + name);
        }
    }
    public List<Config> getConfigs() {
        return configs;
    }
}
