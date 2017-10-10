package com.main.content;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.main.pluginbuilder.JsonHelper;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by gtrpl on 6/20/2016.
 */
public class Plugin implements Comparable<Plugin> {
    private final String pluginName;
    private final List<String> dependencies;
    private final JsonObject pluginObject;

    public Plugin(File pluginFile) {
        this.pluginName = pluginFile.getName().replace(".json", "").replace(".plugin", "");
        this.pluginObject = load(pluginFile);

        JsonHelper helper = new JsonHelper(this.pluginObject);
        this.dependencies = JsonHelper.fromArray(helper.getArray("Dependencies"));
    }
    public String getPluginName() {
        return pluginName;
    }
    public JsonObject getPluginObject() {
        return this.pluginObject;
    }
    @Override
    public int compareTo(Plugin o) {
        return this.dependencies.contains(o.pluginName) ? 1 : -1;
    }
    private static JsonObject load(File file) {
        try {
            if(file.getName().endsWith(".plugin")) {
                DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
                String s = datainputstream.readUTF();
                datainputstream.close();

                return new JsonParser().parse(s).getAsJsonObject();
            } else {
                return new JsonParser().parse(new FileReader(file)).getAsJsonObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonObject();
    }
}