package com.jadencode.main.constants;

import com.google.gson.*;
import org.reflections.Reflections;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by gtrpl on 6/15/2016.
 */
public final class ContentLoader {
    public static final void load() {
        Set<Class<? extends ContentManager>> managerClasses = new Reflections("com.jadencode.main.constants").getSubTypesOf(ContentManager.class);

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
                JsonObject out = new JsonObject();
                JsonObject pluginObject = gson.fromJson(new FileReader(plugin), JsonObject.class);
                for (ContentManager manager : managers) {
                    String name = manager.getName();
                    if(pluginObject.has(name)) {
                        JsonArray contentObjects = pluginObject.getAsJsonArray(name);
                        for (JsonElement element : contentObjects) {
                            JsonObject pieceOfContent = element.getAsJsonObject();
                            manager.consume(pieceOfContent.get("name").getAsString(), pieceOfContent);
                        }
                    }
                    JsonArray array = manager.supply();
                    out.add(name, array);
                }
                File f = new File(dir, "Output.json");
                f.createNewFile();
                FileWriter writer = new FileWriter(f);
                gson.toJson(out, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Colors
        //Stats
        //MaterialTypes
        //PartTypes
        //StatSets
        //MaterialModifiers
        //Materials
        //WeaponTypes
        //WeaponParts
    }
}