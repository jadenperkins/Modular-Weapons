package com.main.plugins;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.main.constants.Materials;
import com.main.constants.WeaponParts;
import com.main.plugins.modules.Module;
import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gtrpl on 6/15/2016.
 */
public final class ContentLoader {
    private final File pluginDirectory = new File("plugins");

    public ContentLoader() {
    }

    public void load() {
        loadStaticContent();

        Materials.load();
        WeaponParts.generateWeaponParts();
        WeaponParts.countParts();
    }
    private File getPluginDirectory() {
        return safeGet(pluginDirectory);
    }
    private File safeGet(File file) {
        if (!file.exists()) file.mkdirs();
        return file;
    }

    private void loadStaticContent() {
        Set<Class<? extends Module>> moduleClasses = new Reflections("com.main.plugins.modules").getSubTypesOf(Module.class);
        List<Module> modules = new ArrayList<>();

        for (Class<? extends Module> moduleClass : moduleClasses) {
            try {
                Module module = moduleClass.newInstance();
                module.postInit();
                modules.add(module);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        modules.sort(Comparator.comparingInt(Module::getLoadOrder));

        File[] pluginFiles = getPluginDirectory().listFiles(a -> a.getName().endsWith(".json"));

        Gson gson = new GsonBuilder().create();

        List<Plugin> plugins = Arrays.asList(pluginFiles).stream().map(file -> fromJson(gson, file)).collect(Collectors.toList());
        plugins.sort(null);

        for (Module module : modules) {
            String moduleName = module.getModuleName();
            for(Plugin plugin : plugins) {
                System.out.println("Loading " + moduleName + " from " + plugin.getPluginName());
                module.loadContent(plugin);
            }
        }
    }
    private Plugin fromJson(Gson gson, File file) {
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            return gson.fromJson(reader, Plugin.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return null;
    }
}