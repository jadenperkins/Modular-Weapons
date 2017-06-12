package com.jadencode.main.content;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jadencode.main.constants.ItemParts;
import com.jadencode.main.constants.ItemTypes;
import com.jadencode.main.constants.Materials;
import com.jadencode.main.constants.Strings;
import com.jadencode.main.content.loaders.ContentManager;
import com.jadencode.main.util.JsonHelper;
import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

/**
 * Created by gtrpl on 6/15/2016.
 */
public final class ContentLoader {

    public static void load() {
        File pluginDir = new File(Strings.IO.ROOT_PLUGINS);
        compressSourcePlugins(pluginDir);
        loadStaticContent(pluginDir);

        Materials.load();
        ItemParts.generateItemParts();
        ItemTypes.generateMaterializedItems();
    }

    private static void compressSourcePlugins(File pluginDir) {
        List<String> compressed = Arrays.asList(pluginDir.listFiles(a -> a.getName().endsWith(Strings.IO.EXT_PLUGIN))).stream().map(file -> file.getName().replaceAll(Strings.IO.EXT_PLUGIN, "")).collect(Collectors.toList());
        List<File> source = Arrays.asList(new File(pluginDir, Strings.IO.PATH_SOURCE).listFiles(a -> a.getName().endsWith(Strings.IO.EXT_JSON)));
        source.forEach(file -> compressSource(file, pluginDir, compressed));
    }
    private static void compressSource(File source, File pluginDir, List<String> compressed) {
        String sourceName = source.getName().replace(Strings.IO.EXT_JSON, "");
        if (compressed.contains(sourceName)) return;

        DataOutputStream dataOutputStream = null;

        try {
            File out = new File(pluginDir, sourceName + Strings.IO.EXT_PLUGIN);
            out.createNewFile();

            JsonObject pluginContents = new JsonParser().parse(new FileReader(source)).getAsJsonObject();
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(out))));

            dataOutputStream.writeUTF(pluginContents.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(dataOutputStream);
        }
    }
    private static void loadStaticContent(File pluginDir) {
        Set<Class<? extends ContentManager>> managerClasses = new Reflections(Strings.IO.PACKAGE_LOADERS).getSubTypesOf(ContentManager.class);
        List<ContentManager> managers = new ArrayList<>();

        for (Class<? extends ContentManager> managerClass : managerClasses) {
            try {
                managers.add(managerClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        managers.sort(Comparator.comparingInt(ContentManager::getLoadOrder));

        if (!pluginDir.exists()) pluginDir.mkdirs();

        List<Plugin> plugins = Arrays.asList(pluginDir.listFiles(a -> a.getName().endsWith(Strings.IO.EXT_PLUGIN))).stream().map(Plugin::new).collect(Collectors.toList());
        plugins.sort(null);

        for (ContentManager manager : managers) {
            String managerName = manager.getName();
            for (Plugin plugin : plugins) {
                System.out.println("Loading " + managerName + " from " + plugin.getPluginName());
                JsonArray array = new JsonHelper(plugin.getPluginObject()).getArray(managerName);
                for (JsonElement jsonElement : array) {
                    JsonObject content = jsonElement.getAsJsonObject();
                    manager.consume(content.get(Strings.JsonKey.NAME).getAsString(), content);
                }
            }
        }
    }
}