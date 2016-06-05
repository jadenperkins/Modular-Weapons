package com.jadencode.main.generate.location;

import com.jadencode.main.generate.Generator;
import com.jadencode.main.nbt.NBTTagCompound;
import com.jadencode.main.nbt.NBTTagList;
import com.jadencode.main.util.NBTUtils;

import java.util.*;

/**
 * Created by Jaden on 1/19/2015.
 */
public class LocationGenerator implements Generator<Location> {
    private static final List<String> BEGINNING_WORDS = Arrays.asList("Battle", "Black", "Diamond", "Dragon", "Dust",
            "Dusk", "East", "Gleam", "Gray", "Jade", "Lake", "Moon", "North", "Oak", "Red", "River", "Ruby", "Shear",
            "Sky", "South", "Sun", "Wind", "Winter", "White", "West");
    public static final List<String> ENDING_WORDS = Arrays.asList(
            "camp", "post", "watch", "guard", "hold", "mill",
            "mood", "shire", "shore", "wind", "rift", "forge", "ward", "arch", "rim", "bridge", "sword", "view",
            "spear", "den", "stable", "helm", "fort", "tree", "grove", "reach", "brook", "rook", "gleam", "glow",
            "glare", "blaze", "flare", "ruin", "march", "pass", "night", "gloom", "light", "hail", "heart", "shade",
            "shadow", "flame", "blade", "creed", "sun", "moon", "rock", "fire", "view", "crest", "belt", "spire",
            "run", "point", "shear", "vale", "ville");

    private final List<String> instanceLocations = new ArrayList<>();

    private int index = 0;

    public LocationGenerator() {
    }
    public Location generate(Random r, int level) {
        return null;
    }
    private void preGenerate() {
        for(String s1 : BEGINNING_WORDS) {
            for(String s2: ENDING_WORDS) {
                String name = s1 + s2.toLowerCase();
                this.instanceLocations.add(name);
            }
        }
        Collections.shuffle(this.instanceLocations);

        System.out.println("Locations pregenerated");
    }
    public void checkNames() {
//        List<String> sorted = new ArrayList<>();
//        sorted.addAll(this.instanceLocations);
//        Collections.sort(sorted);
//
//        List<String> newNames = new ArrayList<>();
//
//        System.out.println(sorted.get(0) + ", " + POSSIBLE_NAMES.get(0));
//
//        //If the names do not match
//        if(!sorted.equals(POSSIBLE_NAMES)) {
//            System.out.println("Name lists are not equal, comparing values");
//            for(String name : POSSIBLE_NAMES) {
//                if(!sorted.contains(name)) {
//                    System.out.println(name + " not found in instance, adding!");
//                    newNames.add(name);
//                }
//            }
//
//            List<String> unusedNames = new ArrayList<>();
//            for(int i = this.index; i < this.instanceLocations.size(); i++) {
//                unusedNames.add(this.instanceLocations.get(i));
//            }
//            this.instanceLocations.removeAll(unusedNames);
//
//            unusedNames.addAll(newNames);
//
//            Collections.shuffle(unusedNames, this.rand);
//            this.instanceLocations.addAll(unusedNames);
//        } else {
//            System.out.println("No name changes detected");
//        }
    }
//    public void toJson(JsonObject json) {
//        JsonArray array = new JsonArray();
//        for(String name : this.instanceLocations) {
//            array.add(new JsonPrimitive(name));
//        }
//
//        json.add("instanceLocations", array);
//        json.add("index", new JsonPrimitive(this.index));
//    }
//    public void fromJson(JsonObject json) {
//
//        JsonArray array = json.get("instanceLocations").getAsJsonArray();
//        for(JsonElement e : array) {
//            this.instanceLocations.add(e.getAsString());
//        }
//        this.index = json.get("index").getAsInt();
//    }
    public void writeNBT(NBTTagCompound nbt) {
        NBTTagList list = NBTUtils.toStringList(this.instanceLocations);
        nbt.setTag("instanceLocations", list);
        nbt.setInteger("index", this.index);
    }
    public void readNBT(NBTTagCompound nbt) {
        if(!nbt.hasKey("instanceLocations")) {
            this.preGenerate();
            this.index = 0;
        } else {
            List<String> list = NBTUtils.fromStringList(nbt.getTagList("instanceLocations"));
            list.stream().forEach(i -> this.instanceLocations.add(i));
            this.index = nbt.getInteger("index");
        }
    }

    @Override
    public void onCreated(Random r) {

    }
}