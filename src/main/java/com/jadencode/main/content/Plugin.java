package com.jadencode.main.content;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.util.JsonHelper;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by gtrpl on 6/20/2016.
 */
public class Plugin implements Comparable<Plugin> {

    private final String pluginName;
    private final JsonObject pluginObject;
    private final List<String> dependencies;

    public Plugin(File pluginFile) {
        this.pluginName = pluginFile.getName().replace(Strings.IO.EXT_JSON, "").replace(Strings.IO.EXT_PLUGIN, "");
        this.pluginObject = load(pluginFile);

        JsonHelper helper = new JsonHelper(this.pluginObject);
        this.dependencies = JsonHelper.fromArray(helper.getArray(Strings.JsonKey.DEPENDENCIES));
    }

    private static JsonObject load(File file) {
        return file.getName().endsWith(Strings.IO.EXT_PLUGIN) ? loadStandard(file) : loadNonstandard(file);
    }
    private static JsonObject loadStandard(File file) {
        DataInputStream dataInputStream = null;
        JsonObject returnedObject = null;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));

            String jsonString = dataInputStream.readUTF();
            returnedObject = new JsonParser().parse(jsonString).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(dataInputStream);
        }
        if(returnedObject == null) returnedObject = new JsonObject();
        return returnedObject;
    }
    private static JsonObject loadNonstandard(File file) {
        FileReader reader = null;
        JsonObject returnedObject = null;
        try {
            reader = new FileReader(file);
            returnedObject = new JsonParser().parse(reader).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        if(returnedObject == null) returnedObject = new JsonObject();
        return returnedObject;
    }

    public String getPluginName() {
        return pluginName;
    }

    public JsonObject getPluginObject() {
        return this.pluginObject;
    }

    @Override
    public int compareTo(@NotNull Plugin o) {
        return this.dependencies.contains(o.pluginName) ? 1 : -1;
    }
}