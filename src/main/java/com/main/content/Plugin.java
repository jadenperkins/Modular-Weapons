package com.main.content;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.main.pluginbuilder.JsonHelper;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by gtrpl on 6/20/2016.
 */
public class Plugin implements Comparable<Plugin> {
    private final String pluginName;
    private final List<String> dependencies;
    private final JsonObject content;

    public Plugin(File pluginFile) {
        this.pluginName = pluginFile.getName().replace(".json", "").replace(".plugin", "");
        this.content = load(pluginFile);

        JsonHelper helper = new JsonHelper(this.content);
        this.dependencies = JsonHelper.fromArray(helper.getArray("Dependencies"));
    }
    public String getPluginName() {
        return pluginName;
    }
    public JsonObject getContent() {
        return this.content;
    }
    @Override
    public int compareTo(Plugin o) {
        return this.dependencies.contains(o.pluginName) ? 1 : -1;
    }
    private static JsonObject load(File file) {
        if (!file.getName().endsWith(".plugin")) {
            FileReader reader = null;
            try {
                reader = new FileReader(file);
                return new JsonParser().parse(reader).getAsJsonObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new JsonObject();
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }

        DataInputStream dataInputStream = null;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
            String s = dataInputStream.readUTF();
            dataInputStream.close();

            return new JsonParser().parse(s).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(dataInputStream);
        }
        return new JsonObject();
    }
}