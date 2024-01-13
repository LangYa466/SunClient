package cn.langya.sun.files;

import com.google.gson.JsonObject;

import java.io.File;

public abstract class Config {
    public String name;
    public Config(String name) {
        this.name = name;
    }
    public abstract JsonObject save();
    public abstract void load(JsonObject object);
}
