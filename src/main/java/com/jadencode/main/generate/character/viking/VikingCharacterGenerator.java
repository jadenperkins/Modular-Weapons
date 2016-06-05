package com.jadencode.main.generate.character.viking;

import com.jadencode.main.generate.Generator;
import com.jadencode.main.generate.character.Actor;
import com.jadencode.main.nbt.NBTTagCompound;
import com.jadencode.main.nbt.NBTTagList;
import com.jadencode.main.util.NBTUtils;

import java.util.*;

/**
 * Created by Jaden on 1/20/2015.
 */
public class VikingCharacterGenerator implements Generator<Actor> {

    private static final List<String> MALE_FIRST  = Arrays.asList("Hak", "Ran", "Ral", "Gjal", "Kul", "Tal",
            "Stal", "Rogg", "Hal", "Bjor", "Skul", "Had");
    private static final List<String> MALE_MIDDLE = Arrays.asList("un", "on", "ar", "en", "ir", "");
    private static final List<String> MALE_LAST   = Arrays.asList("vir", "mir", "of", "lund", "jar", "var",
            "nir", "nar", "dir", "lam");

    private static final List<String> FEMALE_FIRST  = Arrays.asList("Aa", "Ab", "Ad", "Ae", "Af", "Ag", "Ai",
            "Aj", "Ak", "Al", "Am", "An", "Ap", "Aq", "Ar", "As", "At", "Au", "Aw");
    private static final List<String> FEMALE_MIDDLE = Arrays.asList("ka", "dol", "ku", "el", "ri", "");
    private static final List<String> FEMALE_LAST   = Arrays.asList("da", "fia", "ja", "sta", "lu", "pak", "run",
            "ina", "la", "elle", "da");

    private static final List<String> LAST_FIRST = Arrays.asList("Axe", "Salt", "Strong", "Iron", "Steel", "Dragon",
            "Shadow", "Snake", "White");
    private static final List<String> LAST_LAST  = Arrays.asList("sage", "will", "hand", "arm", "kin", "born", "bound",
            "mane", "back", "tongue", "skin");
    private static final List<String> HONOR      = Arrays.asList("", "the Hated", "the Skald", "the Bard",
            "the Untouchable", "the Unmarked", "the Feared", "the Cruel", "the Fearless", "the Wise", "the Everlasting",
            "the Illusive", "the Deceitful");

    private final List<String> instanceNames = new ArrayList<>();

    private int index = 0;

    private void preGenerate(Random r) {
        long start = System.currentTimeMillis();
        for (String l1 : LAST_FIRST) {
            for (String l2 : LAST_LAST) {
                for (String h : HONOR) {
                    String last = l1 + l2;

                    for (String s1 : MALE_FIRST) {
                        for (String s2 : MALE_MIDDLE) {
                            for (String s3 : MALE_LAST) {
                                String n = String.format("%s%s%s %s %s", s1, s2, s3, last, h);
                                instanceNames.add(n);
                            }
                        }
                    }
                    for(String s1 : FEMALE_FIRST) {
                        for(String s2 : FEMALE_MIDDLE) {
                            for(String s3 : FEMALE_LAST) {
                                String n = String.format("%s%s%s %s %s", s1, s2, s3, last, h);
                                instanceNames.add(n);
                            }
                        }
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        Collections.shuffle(this.instanceNames, r);
    }

    @Override
    public Actor generate(Random r, int level) {

        String name = this.instanceNames.get(this.index);
        this.index++;
        if(this.index == this.instanceNames.size()) {
            this.index = 0;
            this.instanceNames.clear();
            this.preGenerate(r);
        }
        return new Actor(name, level+"", true);
    }
    @Override
    public void readNBT(NBTTagCompound nbt) {
        List<String> list = NBTUtils.fromStringList(nbt.getTagList("instanceNames"));
        this.instanceNames.addAll(list);
        this.index = nbt.getInteger("index");
    }
    @Override
    public void writeNBT(NBTTagCompound nbt) {
        NBTTagList list = NBTUtils.toStringList(this.instanceNames);
        nbt.setTag("instanceNames", list);
        nbt.setInteger("index", this.index);
    }
    @Override
    public void onCreated(Random r) {
        this.preGenerate(r);
        this.index = 0;
    }
}
