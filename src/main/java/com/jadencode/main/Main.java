package com.jadencode.main;

import com.jadencode.main.content.ContentLoader;
import com.jadencode.main.generate.character.viking.VikingCharacterGenerator;
import com.jadencode.main.generate.character.viking.VikingSettlementGenerator;
import com.jadencode.main.generate.item.ItemGenerator;
import com.jadencode.main.generate.item.instance.Item;
import com.jadencode.main.renderengine.toolbox.DisplayManager;
import com.jadencode.main.renderengine.Loader;
import com.jadencode.main.renderengine.MasterRenderer;
import com.jadencode.main.renderengine.entities.Camera;
import com.jadencode.main.renderengine.entities.Entity;
import com.jadencode.main.renderengine.entities.Light;
import com.jadencode.main.renderengine.entities.Player;
import com.jadencode.main.renderengine.models.TexturedModel;
import com.jadencode.main.renderengine.terrain.Terrain;
import com.jadencode.main.renderengine.textures.ModelTexture;
import com.jadencode.main.renderengine.textures.TerrainTexture;
import com.jadencode.main.renderengine.textures.TerrainTexturePack;
import com.jadencode.main.renderengine.toolbox.OBJLoader;
import com.jadencode.main.renderengine.toolbox.Time;
import org.lwjgl.util.vector.Vector3f;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaden on 1/19/2015.
 */
public class Main {

    public static final int LEVEL_CAP = 100;

    public static final World theWorld = new World();
    private static final File WORLD_SAVE = new File("./WorldSave.dat");

    //    private static void saveGame(File file, NBTTagCompound nbt) {
//        try {
//            file.createNewFile();
//
//            CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(file));
////            CompressedStreamTools.writeCompressed(nbt, new GZIPOutputStream(new FileOutputStream(save)));
//
////          JsonObject json = new JsonObject();
////          gen.toJson(json);
////          FileUtils.writeStringToFile(save, json.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static NBTTagCompound loadGame(File file) {
//        try {
//            if (file.exists()) {
//
//                NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(file));
//                return nbt;
////                String fileString = FileUtils.readFileToString(save);
////                JsonObject json = new Gson().fromJson(fileString, JsonObject.class);
////                gen.fromJson(json);
//            } else {
//                return new NBTTagCompound();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new NBTTagCompound();
//        }
//    }
//
//    private static void loadGenerator(Generator gen, NBTTagCompound nbt) {
//        if(nbt == null) {
//            gen.onCreated(theWorld.getRNG());
//        } else {
//            gen.readNBT(nbt);
//        }
//    }
//    private static NBTTagCompound saveGenerator(Generator gen) {
//        NBTTagCompound nbt = new NBTTagCompound();
//        gen.writeNBT(nbt);
//        return nbt;
//    }
    private static boolean OR(Object target, Object... others) {
        for (Object o : others) {
            if (target == o) {
                return true;
            }
        }
        return false;
    }

    //    private static void printItem(Item item) {
//        boolean standard = true;
//        int width = 0;
//        int height = 0;
//        for (WeaponPartInstance part : item.getPartsList()) {
//            if(part.getWeaponPart().getIcon().getWidth() > width) {
//                width = part.getWeaponPart().getIcon().getWidth();
//            }
//            if(part.getWeaponPart().getIcon().getHeight() > height) {
//                height = part.getWeaponPart().getIcon().getHeight();
//            }
//            if(part.getWeaponPart().getType().getIcon() == null) {
//                standard = false;
//            }
//        }
//        File dir = new File("pictures");
//        File out = new File(dir, item.getDisplayName().replace(" ", "_") + ".png");
//
//        try {
//            out.mkdirs();
//            out.createNewFile();
//            BufferedImage image = new BufferedImage(width + 64, height, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g2d = image.createGraphics();
//            if(!standard) {
//                for (int x = 0; x < item.getPartsList().size(); x++) {
//                    Color c = item.getPartsList().get(x).getColor();
//                    c = c == null ? Color.WHITE : c;
//                    g2d.setColor(c);
//                    g2d.drawRect(0, x * 16, 16, 16);
//                }
//            } else {
//                for(WeaponPartInstance part : item.getPartsList()) {
//                    BufferedImage icon = part.getWeaponPart().getIcon();
//                    Color c = part.getColor();
//                    if(c == null) {
//                        g2d.drawImage(icon, 0, 0, null);
//                    } else {
//                        for(int x = 0; x < icon.getWidth(); x++) {
//                            for(int y = 0; y < icon.getHeight(); y++) {
//                                if(icon.getRGB(x, y) >> 24 != 0x00) {
//                                    image.setRGB(x, y, part.getColor().getRGB());
//                                }
//                            }
//                        }
//                    }
//                }
//                g2d.setPaint(Color.WHITE);
//                g2d.fillRect(width, 0, 64, image.getHeight());
//
//                BufferedImage after = new BufferedImage(image.getWidth() * 8, image.getHeight() * 8, BufferedImage.TYPE_INT_ARGB);
//                AffineTransform at = new AffineTransform();
//                at.scale(8, 8);
//                AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//                after = scaleOp.filter(image, after);
//                image = after;
//
//                g2d = image.createGraphics();
//
//                g2d.setPaint(item.getQualityLevel().getColor());
//                g2d.setFont(new Font("Helvetica", Font.BOLD, 14));
//                int i = 2;
//                g2d.drawString(String.format("%s (%d)", item.getDisplayName(), item.getLevel()), width * 8 + 8, g2d.getFontMetrics().getHeight() * i);
//
//                i += 2;
//                g2d.setPaint(Color.DARK_GRAY);
//                g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
//                for (WeaponPartInstance weaponPartInstance : item.getPartsList()) {
//                    g2d.drawString(String.format("        %s", weaponPartInstance.getPartInfo(), weaponPartInstance.getLevel()), width * 9, g2d.getFontMetrics().getHeight() * i);
//                    i++;
//                    StatSet stats = weaponPartInstance.getStats();
//                    for (StatBase statBase : stats.getStatsRaw().keySet()) {
//                        g2d.drawString(String.format("                %s: %.2f", statBase.getStatName(), stats.get(statBase)), width * 9, g2d.getFontMetrics().getHeight() * i);
//                        i++;
//                    }
//                }
//
//                i += 1;
//                g2d.setPaint(Color.BLUE);
//                StatSet stats = item.getStatSet();
//                for (StatBase statBase : stats.getStatsRaw().keySet()) {
//                    g2d.drawString(String.format("%s: %.2f", statBase.getStatName(), stats.get(statBase)), width * 9, g2d.getFontMetrics().getHeight() * i);
//                    i++;
//                }
//
//                g2d.dispose();
//
//            }
//            ImageIO.write(image, "PNG", out);
//            System.out.println("Images stored in " + dir.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        DisplayManager display = new DisplayManager();
        Loader loader = new Loader();

        TerrainTexture background = new TerrainTexture(loader.loadTexture("grassy2"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(background, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

        OBJLoader objLoader = new OBJLoader(loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("grass"));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        Light light = new Light(new Vector3f(0, 2000, 0), new Vector3f(1, 1, 1));
        Camera camera = new Camera();

        Entity entity = new Entity(
                new TexturedModel(objLoader.loadObjModel("Stall"), new ModelTexture(loader.loadTexture("models/Stall"))),
                new Vector3f(0, 0, -10), new Vector3f(0, 45, 0), new Vector3f(1, 1, 1));

        MasterRenderer renderer = new MasterRenderer();
        List<Terrain> terrains = new ArrayList<>();

        int rad = 3;
        for (int i = -rad; i <= rad; i++) {
            for (int j = -rad; j <= rad; j++) {
                terrains.add(new Terrain(i, j, loader, texturePack, blendMap));
            }
        }

        Player player = new Player(entity.getModel(), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));

        while (!display.isCloseRequested()) {
            Time.update();

            camera.move();
            player.move();
            renderer.processEntity(player);
            renderer.processEntity(entity);
            terrains.forEach(renderer::processTerrain);
            renderer.render(light, camera);
            display.update();
        }
        renderer.cleanUp();
        loader.cleanUp();
        display.destroy();

        System.exit(0);

        ContentLoader.load();


        int itemLevel = theWorld.getRNG().nextInt(50) + 1;

        Item item = new ItemGenerator().generate(theWorld.getRNG(), itemLevel);
        System.out.println(item.getDisplayName());
        item.printItemCard();
    }

    public static void loadSaveTest() {
        TimeKeeper timer = new TimeKeeper();

        timer.start("Loading Game Save. . .");
//        NBTTagCompound rootNBT = loadGame(WORLD_SAVE);
        timer.stopAndDisplay();

        VikingCharacterGenerator genCharacter = new VikingCharacterGenerator();
        VikingSettlementGenerator genLocations = new VikingSettlementGenerator();

//        NBTTagCompound characterNBT = rootNBT.hasKey("character") ? rootNBT.getCompoundTag("character") : null;
//        NBTTagCompound locationsNBT = rootNBT.hasKey("locations") ? rootNBT.getCompoundTag("locations") : null;

//        loadGenerator(genCharacter, characterNBT);
//        loadGenerator(genLocations, locationsNBT);

        for (int i = 0; i < 10; i++) {
            System.out.println(genCharacter.generate(theWorld.getRNG(), 0).getName());
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(genLocations.generate(theWorld.getRNG(), 0).getName());
        }

        timer.start("Saving Game. . .");

//        characterNBT = saveGenerator(genCharacter);
//        locationsNBT = saveGenerator(genLocations);
//        rootNBT = new NBTTagCompound();
//        rootNBT.setTag("character", characterNBT);
//        rootNBT.setTag("locations", locationsNBT);

//        saveGame(WORLD_SAVE, rootNBT);
        timer.stopAndDisplay();
        System.exit(0);
    }
}
