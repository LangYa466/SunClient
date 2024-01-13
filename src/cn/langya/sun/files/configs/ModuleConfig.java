package cn.langya.sun.files.configs;

import cn.langya.sun.Sun;
import cn.langya.sun.files.Config;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.awt.*;

public class ModuleConfig extends Config {
    public ModuleConfig(String name) {
        super(name);
    }
    @Override
    public JsonObject save() {
        JsonObject object = new JsonObject();
        for (Module module : Sun.moduleManager.getModules()){
            JsonObject moduleObj = new JsonObject();
            moduleObj.addProperty("State",module.isEnabled());
            moduleObj.addProperty("KeyCode",module.getKeyCode());
            JsonObject valueObj = new JsonObject();
            moduleObj.add("Values",valueObj);
            for (AbstractValue<?> value : module.getValues()){
                if (value instanceof IntValue) {
                    valueObj.addProperty(value.name, ((IntValue) value).get());
                } else if (value instanceof FloatValue) {
                    valueObj.addProperty(value.name, ((FloatValue) value).get());
                } else if (value instanceof DoubleValue) {
                    valueObj.addProperty(value.name, ((DoubleValue) value).get());
                } else if (value instanceof BoolValue) {
                    valueObj.addProperty(value.name, ((BoolValue) value).get());
                } else if (value instanceof StringValue) {
                    valueObj.addProperty(value.name, ((StringValue) value).get());
                }else if (value instanceof ColorValue) {
                    valueObj.addProperty(value.name,((ColorValue) value).getColor());
                }
            }
            object.add(module.getName(),moduleObj);
        }
        return object;
    }

    @Override
    public void load(JsonObject object) {
        for (Module module : Sun.moduleManager.getModules()){
            if (object.has(module.name)){
                JsonObject moduleObject = object.get(module.getName()).getAsJsonObject();
                if (moduleObject.has("State")) {
                    module.setState(moduleObject.get("State").getAsBoolean());
                }
                if (moduleObject.has("KeyCode")) {
                    module.setKeyCode(moduleObject.get("KeyCode").getAsInt());
                }
                if (moduleObject.has("Values")) {
                    JsonObject valuesObject = moduleObject.get("Values").getAsJsonObject();
                    for (AbstractValue<?> value : module.getValues()) {
                        if (valuesObject.has(value.name)) {
                            JsonElement theValue = valuesObject.get(value.name);
                            if (value instanceof IntValue) {
                                ((IntValue) value).set(theValue.getAsNumber().intValue());
                            } else if (value instanceof FloatValue) {
                                ((FloatValue) value).set(theValue.getAsNumber().floatValue());
                            } else if (value instanceof DoubleValue) {
                                ((DoubleValue) value).set(theValue.getAsNumber().doubleValue());
                            } else if (value instanceof BoolValue) {
                                ((BoolValue) value).set(theValue.getAsBoolean());
                            } else if (value instanceof StringValue) {
                                ((StringValue) value).set(theValue.getAsString());
                            } else if (value instanceof ColorValue) {
                                Color color = new Color(theValue.getAsInt());
                                ((ColorValue) value).set(new Color(color.getRed(), color.getGreen(),color.getBlue()).getRGB());
                            }
                        }
                    }
                }
            }
        }
    }
}
