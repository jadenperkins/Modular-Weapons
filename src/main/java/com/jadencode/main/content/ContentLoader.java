package com.jadencode.main.content;

import com.google.gson.*;
import com.jadencode.main.constants.Materials;
import com.jadencode.main.constants.ItemParts;
import com.jadencode.main.content.loaders.ContentManager;
import com.jadencode.main.pluginbuilder.JsonHelper;
import org.reflections.Reflections;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

/**
 * Created by gtrpl on 6/15/2016.
 */
public final class ContentLoader {
    public static final void load() {
        compressSourcePlugins();
        loadStaticContent();

        Materials.load();
        ItemParts.generateWeaponParts();
        ItemParts.countParts();
    }
    private static final void compressSourcePlugins() {
        File pluginDir = new File("plugins");
        File[] compressedPlugins = pluginDir.listFiles(a -> a.getName().endsWith(".plugin"));
        File[] sourcePlugins = new File("plugins/source").listFiles(a -> a.getName().endsWith(".json"));

        List<String> compressed = Arrays.asList(compressedPlugins).stream().map(file -> file.getName().replace(".plugin", "")).collect(Collectors.toList());
        List<File> source = Arrays.asList(sourcePlugins);

        for (File s : source) {
            String sourceName = s.getName().replace(".json", "");
            if(!compressed.contains(sourceName)) {
                try {
                    File out = new File(pluginDir, sourceName + ".plugin");
                    out.createNewFile();

                    JsonObject pluginContents = new JsonParser().parse(new FileReader(s)).getAsJsonObject();

                    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(out))));
                    dataOutputStream.writeUTF(pluginContents.toString());
                    dataOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static final void loadStaticContent() {
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

        File dir = new File("./plugins");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File[] pluginFiles = dir.listFiles(a -> a.getName().endsWith(".plugin"));

        List<Plugin> plugins = Arrays.asList(pluginFiles).stream().map(Plugin::new).collect(Collectors.toList());
        plugins.sort(null);

        for (ContentManager manager : managers) {
            String managerName = manager.getName();
            for(Plugin plugin : plugins) {
                System.out.println("Loading " + managerName + " from " + plugin.getPluginName());
                JsonArray array = new JsonHelper(plugin.getPluginObject()).getArray(managerName);
                for (JsonElement jsonElement : array) {
                    JsonObject content = jsonElement.getAsJsonObject();
                    manager.consume(content.get("name").getAsString(), content);
                }
            }
        }

//        for (File plugin : plugins) {
//            try {
//                DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(plugin))));
//                String s = datainputstream.readUTF();
//                datainputstream.close();
//
//                JsonObject pluginObject = new JsonParser().parse(s).getAsJsonObject();
//
//                for (ContentManager manager : managers) {
//                    System.out.println("Loading " + manager.getName() + " content from " + plugin.getName());
//                    String name = manager.getName();
//                    if(pluginObject.has(name)) {
//                        JsonArray contentObjects = pluginObject.getAsJsonArray(name);
//                        for (JsonElement element : contentObjects) {
//                            JsonObject pieceOfContent = element.getAsJsonObject();
//                            manager.consume(pieceOfContent.get("name").getAsString(), pieceOfContent);
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
    private static final void loadScripts() {
//        Set<Class<? extends ScriptLoader>> managerClasses = new Reflections("com.jadencode.main.content.loaders.scripts").getSubTypesOf(ScriptLoader.class);
//        List<ScriptLoader> managers = new ArrayList<>();
//
//        for (Class<? extends ScriptLoader> managerClass : managerClasses) {
//            try {
//                managers.add(managerClass.newInstance());
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//
//        for (ScriptLoader manager : managers) {
//            File[] scriptFiles = manager.getDirectory().listFiles(a -> a.getName().endsWith(".js"));
//            for (File script : scriptFiles) {
//                try {
//                    String scriptContents = FileUtils.readFileToString(script, Charset.defaultCharset());
//                    manager.load(script.getName().replace(".js", ""), scriptContents);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}