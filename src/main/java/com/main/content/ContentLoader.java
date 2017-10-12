package com.main.content;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.main.constants.Materials;
import com.main.constants.WeaponParts;
import com.main.content.loaders.ContentManager;
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
    private final File pluginDirectory = new File("plugins");
    private final File sourceDirectory = new File(pluginDirectory, "source");

    public ContentLoader() {
    }

    public void load() {
        compressSourcePlugins();
        loadStaticContent();

        Materials.load();
        WeaponParts.generateWeaponParts();
        WeaponParts.countParts();
    }
    private File getPluginDirectory() {
        return safeGet(pluginDirectory);
    }
    private File getSourceDirectory() {
        return safeGet(sourceDirectory);
    }
    private File safeGet(File file) {
        if (!file.exists()) file.mkdirs();
        return file;
    }

    private void compressSourcePlugins() {
        File[] pluginsFiles = getPluginDirectory().listFiles(a -> a.getName().endsWith(".plugin"));
        File[] sourceFiles = getSourceDirectory().listFiles(a -> a.getName().endsWith(".json"));

        List<String> compressed = Arrays.asList(pluginsFiles).stream().map(file -> file.getName().replace(".plugin", "")).collect(Collectors.toList());

        for (File sourceFile : sourceFiles) {
            String sourceName = sourceFile.getName().replace(".json", "");
            if (!compressed.contains(sourceName)) compress(new File(getPluginDirectory(), sourceName + ".plugin"), sourceFile);
        }
    }
    private void compress(File outputFile, File sourceFile) {
        FileReader reader = null;
        DataOutputStream dataOutputStream = null;
        try {
            reader = new FileReader(sourceFile);
            outputFile.createNewFile();

            JsonObject pluginContents = new JsonParser().parse(reader).getAsJsonObject();

            dataOutputStream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(outputFile))));
            dataOutputStream.writeUTF(pluginContents.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(dataOutputStream);
            IOUtils.closeQuietly(reader);
        }
    }
    private void loadStaticContent() {
        Set<Class<? extends ContentManager>> managerClasses = new Reflections("com.main.content.loaders").getSubTypesOf(ContentManager.class);
        List<ContentManager> managers = new ArrayList<>();

        for (Class<? extends ContentManager> managerClass : managerClasses) {
            try {
                managers.add(managerClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        managers.sort(Comparator.comparingInt(ContentManager::getLoadOrder));

        File[] pluginFiles = getPluginDirectory().listFiles(a -> a.getName().endsWith(".plugin"));

        Gson gson = new GsonBuilder().create();

        List<Plugin> plugins = Arrays.asList(pluginFiles).stream().map(file -> fromJson(gson, file)).collect(Collectors.toList());
        plugins.sort(null);

        for (ContentManager manager : managers) {
            String managerName = manager.getName();
            for(Plugin plugin : plugins) {
                System.out.println("Loading " + managerName + " from " + plugin.getPluginName());
                manager.loadContent(plugin);
            }
        }
    }
    private Plugin fromJson(Gson gson, File file) {
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            return gson.fromJson(reader, Plugin.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return null;
    }
}