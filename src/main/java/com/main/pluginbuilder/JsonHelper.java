package com.main.pluginbuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JPERKI8 on 6/20/2016.
 */
public class JsonHelper {
    private final JsonObject jsonObject;
    public JsonHelper(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
    public JsonHelper add(String name, String value) {
        this.jsonObject.add(name, new JsonPrimitive(value));
        return this;
    }
    public JsonHelper add(String name, Number value) {
        this.jsonObject.add(name, new JsonPrimitive(value));
        return this;
    }
    public JsonHelper add(String name, char value) {
        this.jsonObject.add(name, new JsonPrimitive(value));
        return this;
    }
    public JsonHelper add(String name, boolean value) {
        this.jsonObject.add(name, new JsonPrimitive(value));
        return this;
    }
    public JsonHelper add(String name, JsonObject obj) {
        this.jsonObject.add(name, obj);
        return this;
    }
    public JsonHelper add(String name, JsonArray array) {
        this.jsonObject.add(name, array);
        return this;
    }
    public JsonHelper addNotEmpty(String name, JsonArray array) {
        if(array.size() > 0) {
            this.add(name, array);
        }
        return this;
    }
    public String getString(String name, String def) {
        return this.jsonObject.has(name) && this.jsonObject.get(name).isJsonPrimitive() && this.jsonObject.get(name).getAsJsonPrimitive().isString() ? this.jsonObject.get(name).getAsString() : def;
    }
    public boolean getBoolean(String name, boolean def) {
        return this.jsonObject.has(name) && this.jsonObject.get(name).isJsonPrimitive() && this.jsonObject.get(name).getAsJsonPrimitive().isBoolean() ? this.jsonObject.get(name).getAsBoolean() : def;
    }
    public char getChar(String name, char def) {
        return this.jsonObject.has(name) && this.jsonObject.get(name).isJsonPrimitive() && this.jsonObject.get(name).getAsJsonPrimitive().isString() && !this.jsonObject.get(name).getAsString().isEmpty() ? this.getString(name).charAt(0) : def;
    }
    public byte getByte(String name, byte def) {
        return this.isNumber(name) ? this.jsonObject.get(name).getAsByte() : def;
    }
    public short getShort(String name, short def) {
        return this.isNumber(name) ? this.jsonObject.get(name).getAsShort() : def;
    }
    public int getInt(String name, int def) {
        return this.isNumber(name) ? this.jsonObject.get(name).getAsInt() : def;
    }
    public long getLong(String name, long def) {
        return this.isNumber(name) ? this.jsonObject.get(name).getAsLong() : def;
    }
    public float getFloat(String name, float def) {
        return this.isNumber(name) ? this.jsonObject.get(name).getAsFloat() : def;
    }
    public double getDouble(String name, double def) {
        return this.isNumber(name) ? this.jsonObject.get(name).getAsDouble() : def;
    }
    public JsonArray getArray(String name, JsonArray def) {
        return this.jsonObject.has(name) && this.jsonObject.get(name).isJsonArray() ? this.jsonObject.get(name).getAsJsonArray() : def;
    }
    public JsonObject getObject(String name, JsonObject def) {
        return this.jsonObject.has(name) && this.jsonObject.get(name).isJsonObject() ? this.jsonObject.get(name).getAsJsonObject() : def;
    }
    public String getString(String name) {
        return this.getString(name, "");
    }
    public boolean getBoolean(String name) {
        return this.getBoolean(name, false);
    }
    public char getChar(String name) {
        return this.getChar(name, ' ');
    }
    public byte getByte(String name) {
        return this.getByte(name, (byte) 0);
    }
    public short getShort(String name) {
        return this.getShort(name, (short) 0);
    }
    public int getInt(String name) {
        return this.getInt(name, 0);
    }
    public long getLong(String name) {
        return this.getLong(name, 0);
    }
    public float getFloat(String name) {
        return this.getFloat(name, 0);
    }
    public double getDouble(String name) {
        return this.getDouble(name, 0);
    }
    public JsonArray getArray(String name) {
        return this.getArray(name, new JsonArray());
    }
    public JsonObject getObject(String name) {
        return this.getObject(name, new JsonObject());
    }
    public JsonHelper getHelper(String name) {
        return new JsonHelper(this.getObject(name));
    }
    private boolean isNumber(String name) {
        return this.jsonObject.has(name) && this.jsonObject.get(name).isJsonPrimitive() && this.jsonObject.get(name).getAsJsonPrimitive().isNumber();
    }
    public static JsonArray toArray(List<String> list) {
        JsonArray array = new JsonArray();
        list.forEach(array::add);
        return array;
    }
    public static List<String> fromArray(JsonArray array) {
        List<String> strings = new ArrayList<>();
        for (JsonElement jsonElement : array)
            if(jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString())
                strings.add(jsonElement.getAsString());
        return strings;
    }
    public static JsonArray toArray(HashMap<String, Double> map, String keyKey, String valueKey) {
        JsonArray array = new JsonArray();
        map.forEach((k, v) -> array.add(new JsonHelper(new JsonObject()).add(keyKey, k).add(valueKey, v).jsonObject));
        return array;
    }
    public static HashMap<String, Double> fromArray(JsonArray array, String keyKey, String valueKey) {
        HashMap<String, Double> map = new HashMap<>();
        for (JsonElement jsonElement : array) {
            JsonHelper helper = new JsonHelper(jsonElement.getAsJsonObject());
            map.put(helper.getString(keyKey), helper.getDouble(valueKey));
        }
        return map;
    }
}