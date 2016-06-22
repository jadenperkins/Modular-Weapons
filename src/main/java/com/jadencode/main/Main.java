package com.jadencode.main;

import com.jadencode.main.content.ContentLoader;
import com.jadencode.main.generate.Generator;
import com.jadencode.main.generate.character.Actor;
import com.jadencode.main.generate.character.Settlement;
import com.jadencode.main.generate.character.crotan.KrotanCharacterGenerator;
import com.jadencode.main.generate.character.crotan.KrotanSettlementGenerator;
import com.jadencode.main.generate.character.viking.VikingCharacterGenerator;
import com.jadencode.main.generate.character.viking.VikingSettlementGenerator;
import com.jadencode.main.generate.weapon.WeaponGenerator;
import com.jadencode.main.generate.weapon.WeaponInstance;
import com.jadencode.main.generate.weapon.WeaponPartInstance;
import com.jadencode.main.magic.SpellBase;
import com.jadencode.main.magic.SpellObject;
import com.jadencode.main.nbt.CompressedStreamTools;
import com.jadencode.main.nbt.NBTTagCompound;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Jaden on 1/19/2015.
 */
public class Main {

    public static final int LEVEL_CAP = 100;

    public static final  World theWorld   = new World();
    private static final File  WORLD_SAVE = new File("./WorldSave.dat");

    private static void saveGame(File file, NBTTagCompound nbt) {
        try {
            file.createNewFile();

            CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(file));
//            CompressedStreamTools.writeCompressed(nbt, new GZIPOutputStream(new FileOutputStream(save)));

//          JsonObject json = new JsonObject();
//          gen.toJson(json);
//          FileUtils.writeStringToFile(save, json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static NBTTagCompound loadGame(File file) {
        try {
            if (file.exists()) {

                NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(file));
                return nbt;
//                String fileString = FileUtils.readFileToString(save);
//                JsonObject json = new Gson().fromJson(fileString, JsonObject.class);
//                gen.fromJson(json);
            } else {
                return new NBTTagCompound();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new NBTTagCompound();
        }
    }

    private static void loadGenerator(Generator gen, NBTTagCompound nbt) {
        if(nbt == null) {
            gen.onCreated(theWorld.getRNG());
        } else {
            gen.readNBT(nbt);
        }
    }
    private static NBTTagCompound saveGenerator(Generator gen) {
        NBTTagCompound nbt = new NBTTagCompound();
        gen.writeNBT(nbt);
        return nbt;
    }
    private static boolean OR(Object target, Object... others) {
        for(Object o : others) {
            if(target == o) {
                return true;
            }
        }
        return false;
    }
    private static void printWeapon(WeaponInstance weap) {
        boolean standard = true;
        for (WeaponPartInstance part : weap.getPartsList()) {
            if(part.getWeaponPart().getType().getIcon() == null) {
                standard = false;
            }
        }
        File dir = new File("pictures");
        File out = new File(dir, weap.getDisplayName().replace(" ", "_") + ".png");

        try {
            out.mkdirs();
            out.createNewFile();
            BufferedImage image = new BufferedImage(64, weap.getPartsList().size() * 16, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            if(!standard) {
                for (int x = 0; x < weap.getPartsList().size(); x++) {
                    Color c = weap.getPartsList().get(x).getColor();
                    c = c == null ? Color.WHITE : c;
                    g2d.setColor(c);
                    g2d.drawRect(0, x * 16, 16, 16);
                }
            } else {
                for(WeaponPartInstance part : weap.getPartsList()) {
                    BufferedImage icon = part.getWeaponPart().getIcon();
                    Color c = part.getColor();
                    if(c == null) {
                        g2d.drawImage(icon, 0, 0, null);
                    } else {
                        for(int x = 0; x < icon.getWidth(); x++) {
                            for(int y = 0; y < icon.getHeight(); y++) {
                                if(icon.getRGB(x, y) >> 24 != 0x00) {
                                    image.setRGB(x, y, part.getColor().getRGB());
                                }
                            }
                        }
                    }
                }
                g2d.setPaint(Color.WHITE);
                g2d.fillRect(16, 0, 48, image.getHeight());

                BufferedImage after = new BufferedImage(image.getWidth() * 8, image.getHeight() * 8, BufferedImage.TYPE_INT_ARGB);
                AffineTransform at = new AffineTransform();
                at.scale(8, 8);
                AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                after = scaleOp.filter(image, after);
                image = after;

                g2d = image.createGraphics();

                StatSet stats = weap.getStatSet();
                g2d.setPaint(Color.BLACK);
                g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
                int i = 4;
                g2d.drawString(String.format("%s (%d)", weap.getDisplayName(), weap.getLevel()), 16 * 8 + 8, g2d.getFontMetrics().getHeight() * i);

                i += 1;
                g2d.setPaint(Color.DARK_GRAY);
                for (WeaponPartInstance weaponPartInstance : weap.getPartsList()) {
                    g2d.drawString(String.format("        %s", weaponPartInstance.getPartInfo(), weaponPartInstance.getLevel()), 16 * 9, g2d.getFontMetrics().getHeight() * i);
                    i++;
                }

                i += 1;
                g2d.setPaint(Color.BLUE);
                for (StatBase statBase : stats.getStatsRaw().keySet()) {
                    g2d.drawString(String.format("%s: %.2f", statBase.getStatName(), stats.get(statBase)), 16 * 9, g2d.getFontMetrics().getHeight() * i);
                    i++;
                }

                g2d.dispose();

            }
            ImageIO.write(image, "PNG", out);
            System.out.println("Images stored in " + dir.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ContentLoader.load();
        //Initialize all materials

//        for(MaterialModified resource : MaterialLibrary.getMetalLibrary().getMaterialResources().values()) {
//            System.out.println(String.format("%s: %f, %f, %f", resource.getName(), resource.getStrengthMultiplier(), resource.getRangeMultiplier(), resource.getWeaknessMultiplier()));
//        }

//        List<MaterialModified> resources = new ArrayList<>();
//        resources.addAll(MaterialLibrary.getGemLibrary().getMaterialResources().values());
//        resources.sort((a, b) -> {
//            int c = Integer.compare(a.getMaterialLevel(), b.getMaterialLevel());
//            return c == 0 ? a.getName().compareTo(b.getName()) : c;
//        });
//
//        for(MaterialModified resource : resources) {
//            MaterialBase b = resource.getParent();
//            System.out.println(resource.getName() + ": Level " + resource.getMaterialLevel());
//        }
//        System.exit(0);


        //Create all item parts - work on this
//        ItemPart.generateItemParts();

//        TimeKeeper timer = new TimeKeeper();
//        timer.start("Making recipes");
//
//        RecipeManager.makeInstances();
//
//        for(Recipe r : RecipeManager.getRecipes()) {
//            if(r != null) {
//                StringBuilder in = new StringBuilder();
//                r.getInput().forEach(i -> in.append(i.getPartInfo()));
//                System.out.println(String.format("%s -> %s", in.toString(), r.getOutput().getPartInfo()));
//            }
//        }
//        timer.stopAndDisplay();
//
//        System.exit(0);
//
//        List<ItemPart> parts = ItemPart.getAll();
//        parts.sort((a, b) -> a.getPartName().compareTo(b.getPartName()));
//
//        parts.forEach(i -> System.out.println(i.getPartName()));
//        System.out.println(parts.size() + " parts");

        //Create all weapon parts
//        ArmorPart.generateArmorParts();


        int weaponLevel = 250;
        WeaponInstance weap = new WeaponGenerator().generate(theWorld.getRNG(), weaponLevel);
        System.out.println("Created " + weap.getDisplayName());
//        StatSet s = weap.getStatSet();
//        Set<StatBase> base = weap.getWeaponType().getStatSet().getStatsRaw().keySet();
//
//
//        System.out.println(weap.getDisplayName());
//        base.forEach(stat -> System.out.println(String.format("\t%s: %f", stat.getStatName(), s.get(stat))));
//
//        System.out.println("\t\t" + weap.getDisplayInfo());

//        WeaponInstance scaled = weap.scaled(20);
//        StatSet s1 = scaled.getStatSet();
//        s1.getStatsRaw().keySet().forEach(stat -> System.out.println(String.format("\t%s: %f", stat.getStatName(), s1.get(stat))));

        printWeapon(weap);

//        for(WeaponPartInstance part : weap.getWeaponParts().values()) {
//            for(String key : part.getPartTypes().getFloatKeys()) {
//                System.out.println(String.format("%s = %f", key, part.getPartTypes().getFloat(key)));
//            }
//        }

//        System.out.println(String.format("%s: %f, %f, %f,", weap.getDisplayName(), weap.getWeaponDamage(), weap.getWeaponRange(), weap.getWeaponTime()));

//        ArmorInstance armor = new ArmorGenerator().generate(theWorld.getRNG(), 1);
//        System.out.println(String.format("%s: %f, %f, %f, %f", armor.getDisplayName(), armor.getSlashValue(), armor.getPierceValue(), armor.getBluntValue(), armor.getMobilityValue()));
//        System.out.println("\t" + armor.getDisplayInfo());
//        float power = (armor.getSlashValue() + armor.getPierceValue() + armor.getBluntValue()) / armor.getMobilityValue();
//        System.out.println(power);

        System.exit(0);

        //Create instances for spell bases
        SpellBase.loadBaseSpells();
        System.out.println(SpellBase.getBaseSpells().size() + " spell bases!");

        for(int i = 0; i < 10; i++) {
            SpellObject obj = SpellObject.generateRandom(theWorld.getRNG(), i * 10 + 10);
            System.out.println(obj.getName());
        }
        System.exit(0);

        Generator<Settlement> settlementGenerator = new KrotanSettlementGenerator();
        Generator<Actor> actorGenerator = new KrotanCharacterGenerator();

        settlementGenerator.onCreated(theWorld.getRNG());
        actorGenerator.onCreated(theWorld.getRNG());

        for(int i = 0; i < 25; i++) {
            Actor actor = actorGenerator.generate(theWorld.getRNG(), 0);
            System.out.println(actor.getName());
        }
        System.exit(0);

        //Instance of a weapon generator
        WeaponGenerator generator = new WeaponGenerator();

        //Generate a random weapon using the world's RNG, level 0 (NYI)
        int weaponCount = 10;
        int maxItr = 5;
        int maxLvl = 250;

        List<WeaponInstance> weapons = new ArrayList<>();

        for(int i = 0; i < weaponCount; i++) {
            int itr = theWorld.getRNG().nextInt(maxItr + 1);

            int level = theWorld.getRNG().nextInt(maxLvl + 1) + 1;
            for(int j = 0; j < itr; j++) {
                level = theWorld.getRNG().nextInt(level + 1) + 1;
            }
            WeaponInstance weapon = generator.generate(theWorld.getRNG(), level);
            weapons.add(weapon);
//            System.out.println("\t" + weapon.getDisplayInfo());
            printWeapon(weapon);
        }
//        weapons.sort((weapon1, weapon2) -> Integer.compare(weapon1.getLevel(), weapon2.getLevel()));
//        weapons.forEach(weapon -> System.out.println(String.format("%s (Level %d)", weapon.getDisplayName(), weapon.getLevel())));
        //List all base weapon parts, true to write to enum.txt, false to write to console
//        WeaponPartBase.enumerateParts(true);

        //Count the parts - displays the total of each part list and the total of each weapon type, then total weapons
//        WeaponGenerator.countParts();

        //Early program termination
        System.exit(0);

        //Take a look at other classes like VikingSettlementGenerator and VikingCharacterGenerator. Same operation
        //as SpellGenerator and WeaponGenerator (.generate(random, int))




//        SpellGenerator gen = new SpellGenerator();
//
//        List<SpellObject> o = new ArrayList<>();
//        Random r = new Random("test".hashCode());
//        for(int i = 0; i < 20; i++) {
//            o.add(gen.generate(r, i));
//        }
//
//        for(SpellObject obj : o) {
//            System.out.println(obj.getName() + ", " + obj.getSpellBase().getDescription());
//        }


//        LocationGenerator.init();
//
//        LocationGenerator gen = new LocationGenerator("test");
//
//        File save = new File("./LocationsSave.dat");
//
//        try {
//            if(save.exists()) {
//
//                NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(save));
//                gen.readNBT(nbt);
//
////                String fileString = FileUtils.readFileToString(save);
////                JsonObject json = new Gson().fromJson(fileString, JsonObject.class);
////                gen.fromJson(json);
//            } else {
//                save.createNewFile();
//                gen.preGenerate();
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        gen.checkNames();
//
//        for(String s : gen.getLocations(10)) {
//            System.out.println(s);
//        }
//
//        try {
//            save.createNewFile();
//
//            NBTTagCompound nbt = new NBTTagCompound();
//            gen.writeNBT(nbt);
//            CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(save));
////            CompressedStreamTools.writeCompressed(nbt, new GZIPOutputStream(new FileOutputStream(save)));
//
////          JsonObject json = new JsonObject();
////          gen.toJson(json);
////          FileUtils.writeStringToFile(save, json.toString());
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

//        List<String> swords = SwordGenerator.makeAll(true);

//        Collections.sort(swords, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                int f1 = SwordGenerator.calculate(o1);
//                int f2 = SwordGenerator.calculate(o2);
//                return f1 < f2 ? 1 : f1 > f2 ? -1 : 0;
//            }
//        });
//        for(String s : swords) {
//            System.out.println(s);
//        }

//        Random rand = new Random();
//
//        for(int i = 0; i < 15; i++) {
//            System.out.println(SwordGenerator.generateName(rand));
//        }


//        World world = new World();

//        System.out.println(world.getBlock(0, 74, 100).getBlockID());
//        world.setBlock(-16, 0, 0, Block.dirt);
//        world.setBlock(1400, 161, -1511, Block.stone);
//        System.out.println(world.getBlock(-16, 0, 0).getBlockID());
//        System.out.println(world.getBlock(1400, 161, -1511).getBlockID());

//        SwordParts.init();
//
//        SwordGenerator gen = new SwordGenerator();
//        Sword s = gen.generate();
//        s.printImage();

//        MaterialLibrary.init();
//        SpellBase.loadBaseSpells();
//        List<SpellBase> list = getStuff(SpellBase.getBaseSpells().values(), new ArrayList<SpellBase>());
//        Collections.sort(list, new Comparator<SpellBase>() {
//            @Override
//            public int compare(SpellBase o1, SpellBase o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
//
//        for(SpellBase base : list) {
//            System.out.println(String.format("%s (%f) - %s", base.getName(), base.getWeight(), base.getDescription()));
//        }
//        Counter<SpellBase> counter = new Counter<>();
//        Random r = new Random();
//        for(int i = 0; i < 500; i++) {
//            counter.count(SpellBase.getRandom(r));
//        }
//        counter.displaySorted();

//        Random rand = new Random();
//        for(int i = 0; i < 10; i++) {
//            int level = rand.nextInt(LEVEL_CAP + 1) + 1;
//            SpellObject spell = SpellObject.generateRandom(rand, level);
//            System.out.println(String.format("Level is %d, %s: %d, %d", level, spell.getName(), spell.getLevel(), spell.getIntegers().get("damage")));
//        }

//        MaterialBase.initExotics();
//        MaterialModified.init();

//        for(MaterialLibrary lib : MaterialLibrary.getLibraries()) {
//            HashMap<String, MaterialModified> resources = lib.getMaterialResources();
//            File main = new File("./colors/" + lib.getName());
//            main.mkdir();
//            for(String key : resources.keySet()) {
//                MaterialModified res = resources.get(key);
//                String path;
//
//                if(res.getModifier().isNone())
//                    path = main + "/Base";
//                else
//                    path = main + "/" + res.getModifier().getName();
//
//                File dir = new File(path);
//                if(!dir.exists())
//                    dir.mkdir();
//
//                BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
//                img.setRGB(0, 0, res.getColor().getRGB());
//                File out = new File(dir + "/" + res.getParent().getName() + ".png");
//                try {
//                    out.createNewFile();
//                    ImageIO.write(img, "PNG", out);
//                } catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        List<Actor> people = populate(new VikingCharacterGenerator(), 10);
//        List<Settlement> places = populate(new VikingSettlementGenerator(), 10);

//        for(Actor person : people)
//            System.out.println(String.format("%s age %d, %s: %s", person.getName(), person.getAge(), (person.isMale() ? "male" : "female"), person.getClan().getName()));


//        HashMap<String, Location> locations = new HashMap<>();
//        LocationGenerator generator = LocationGenerator.instance;
//        int num = 10;
//
//        HashMap<String, Integer> counts = new HashMap<>();
//
//        for(int i = 0; i < num; i++) {
//            Location l;
//            do {
//                l = generator.generate();
//            } while(locations.containsKey(l.getName()));
//
//            locations.put(l.getName(), l);
//            System.out.println(l);
//            int c = counts.containsKey(l.getName()) ? counts.get(l.getName()) : 0;
//            c++;
//            counts.put(l.getName(), c);
//        }
//        for(String s : counts.keySet()) {
//            int c = counts.get(s);
//            if(c > 1) {
//                System.out.println(String.format("%s picked %d times", s, c));
//            }
//        }
    }

    public static void loadSaveTest() {
        TimeKeeper timer = new TimeKeeper();

        timer.start("Loading Game Save. . .");
        NBTTagCompound rootNBT = loadGame(WORLD_SAVE);
        timer.stopAndDisplay();

        VikingCharacterGenerator genCharacter = new VikingCharacterGenerator();
        VikingSettlementGenerator genLocations = new VikingSettlementGenerator();

        NBTTagCompound characterNBT = rootNBT.hasKey("character") ? rootNBT.getCompoundTag("character") : null;
        NBTTagCompound locationsNBT = rootNBT.hasKey("locations") ? rootNBT.getCompoundTag("locations") : null;

        loadGenerator(genCharacter, characterNBT);
        loadGenerator(genLocations, locationsNBT);

        for(int i = 0; i < 10; i++) {
            System.out.println(genCharacter.generate(theWorld.getRNG(), 0).getName());
        }

        for(int i = 0; i < 10; i++) {
            System.out.println(genLocations.generate(theWorld.getRNG(), 0).getName());
        }

        timer.start("Saving Game. . .");

        characterNBT = saveGenerator(genCharacter);
        locationsNBT = saveGenerator(genLocations);
        rootNBT = new NBTTagCompound();
        rootNBT.setTag("character", characterNBT);
        rootNBT.setTag("locations", locationsNBT);

        saveGame(WORLD_SAVE, rootNBT);
        timer.stopAndDisplay();
        System.exit(0);
    }

    //None: 0
    //Activity: 1
    //ActivityType: 2
    //Class: 3
    //Gender: 4
    //InventoryBucket: 5
    //InventoryItem: 6
    //Progression: 7
    //Race: 8
    //Stat: 9
    //TalentGrid: 10
    //StatGroup: 11
    //UnlockFlag: 12
    //Vendor: 13
    //Destination: 14
    //Place: 15
    //DirectorBook: 16
    //MaterialRequirement: 17
    //SandboxPerk: 18
    //ArtDye: 19
    //ArtDyeChannel: 20
    //ActivityBundle: 21
    //GearAsset: 22
}
