package com.jadencode.main.content;

import com.google.gson.*;
import com.jadencode.main.constants.Materials;
import com.jadencode.main.constants.WeaponParts;
import com.jadencode.main.content.loaders.ContentManager;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by gtrpl on 6/15/2016.
 */
public final class ContentLoader {
    public static final void load() {
        Set<Class<? extends ContentManager>> managerClasses = new Reflections("com.jadencode.main.content.loaders").getSubTypesOf(ContentManager.class);
        List<ContentManager> managers = new ArrayList<>();

        for (Class<? extends ContentManager> managerClass : managerClasses) {
            try {
                managers.add(managerClass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        managers.sort((a, b) -> Integer.compare(a.getLoadOrder(), b.getLoadOrder()));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File dir = new File("./plugins");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File[] plugins = dir.listFiles(a -> a.getName().endsWith(".json"));
        for (File plugin : plugins) {
            try {
                JsonObject pluginObject = new JsonParser().parse(new FileReader(plugin)).getAsJsonObject();
                for (ContentManager manager : managers) {
                    System.out.println("Loading " + manager.getName() + " content from " + plugin.getName());
                    String name = manager.getName();
                    if(pluginObject.has(name)) {
                        JsonArray contentObjects = pluginObject.getAsJsonArray(name);
                        for (JsonElement element : contentObjects) {
                            JsonObject pieceOfContent = element.getAsJsonObject();
                            manager.consume(pieceOfContent.get("name").getAsString(), pieceOfContent);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Materials.load();
        WeaponParts.generateWeaponParts();
        WeaponParts.countParts();
    }
}