package com.jadencode.main.constants;

import javassist.bytecode.ClassFile;
import org.reflections.Reflections;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * Created by gtrpl on 6/15/2016.
 */
public final class ContentLoader {
    public static final void load() {
        File dir = new File("./plugins");
        dir.mkdirs();
        File[] files = dir.listFiles(f -> f.getName().endsWith(".class"));
        for(File pluginFile : files) {
            try {
                addPlugin(pluginFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Set<Class<?>> plugins = new Reflections().getTypesAnnotatedWith(Plugin.class);
        System.out.println(plugins);

//        Set<Class<? extends ContentManager>> managerClasses = new Reflections("com.jadencode.main.constants").getSubTypesOf(ContentManager.class);
//
//        List<ContentManager> managers = new ArrayList<>();
//
//        for (Class<? extends ContentManager> managerClass : managerClasses) {
//            try {
//                managers.add(managerClass.newInstance());
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        managers.sort((a, b) -> Integer.compare(a.getLoadOrder(), b.getLoadOrder()));
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        File dir = new File("./plugins");
//        if(!dir.exists()) {
//            dir.mkdirs();
//        }
//        File[] plugins = dir.listFiles(a -> a.getName().endsWith(".json"));
//        for (File plugin : plugins) {
//            try {
//                JsonObject out = new JsonObject();
//                JsonObject pluginObject = gson.fromJson(new FileReader(plugin), JsonObject.class);
//                for (ContentManager manager : managers) {
//                    String name = manager.getName();
//                    if(pluginObject.has(name)) {
//                        JsonArray contentObjects = pluginObject.getAsJsonArray(name);
//                        for (JsonElement element : contentObjects) {
//                            JsonObject pieceOfContent = element.getAsJsonObject();
//                            manager.consume(pieceOfContent.get("name").getAsString(), pieceOfContent);
//                        }
//                    }
//                    JsonArray array = manager.supply();
//                    out.add(name, array);
//                }
//                File f = new File(dir, "Output.json");
//                f.createNewFile();
//                FileWriter writer = new FileWriter(f);
//                gson.toJson(out, writer);
//                writer.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}