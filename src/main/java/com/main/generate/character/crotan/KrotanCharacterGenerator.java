package com.main.generate.character.crotan;

import com.main.generate.Generator;
import com.main.generate.character.Actor;
import com.main.nbt.NBTTagCompound;
import com.main.nbt.NBTTagList;
import com.main.util.NBTUtils;

import java.util.*;

/**
 * Created by Jaden on 7/20/2015.
 */
public class KrotanCharacterGenerator implements Generator<Actor> {
    private static final List<String> MALE_FIRST  = Arrays.asList("Kra", "Kre", "Kri", "Kro", "Kru", "Kry");
    private static final List<String> MALE_MIDDLE = Arrays.asList("ga", "ge", "gi", "go", "gu", "gy", "");
    private static final List<String> MALE_LAST   = Arrays.asList("tan", "ten", "tin", "ton", "tun", "tyn");

    private static final List<String> FEMALE_FIRST  = Arrays.asList("Cra", "Cre", "Cri", "Cro", "Cru", "Cry");
    private static final List<String> FEMALE_MIDDLE = Arrays.asList("ya", "ye", "yi", "yo", "yu", "yy", "");
    private static final List<String> FEMALE_LAST   = Arrays.asList("lan", "len", "lin", "lon", "lun", "lyn");

    private static final List<String> LAST_FIRST = Arrays.asList(
            "Dar", "Der", "Dir", "Dor", "Dur", "Dyr",
            "Mar", "Mer", "Mir", "Mor", "Mur", "Myr");
    private static final List<String> LAST_LAST  = Arrays.asList("",
            "zaq", "zeq", "ziq", "zoq", "zuq", "zyq",
            "haq", "heq", "hiq", "hoq", "huq", "hyq");

    private final List<String> instanceNames = new ArrayList<>();

    private int index = 0;

    private void preGenerate(Random r) {
        long start = System.currentTimeMillis();
        for (String l1 : LAST_FIRST) {
            for (String l2 : LAST_LAST) {
                String last = l1 + l2;

                for (String s1 : MALE_FIRST) {
                    for (String s2 : MALE_MIDDLE) {
                        for (String s3 : MALE_LAST) {
                            String n = String.format("%s%s%s %s", s1, s2, s3, last);
                            instanceNames.add(n);
                        }
                    }
                }
                for (String s1 : FEMALE_FIRST) {
                    for (String s2 : FEMALE_MIDDLE) {
                        for (String s3 : FEMALE_LAST) {
                            String n = String.format("%s%s%s %s", s1, s2, s3, last);
                            instanceNames.add(n);
                        }
                    }
                }
            }
        }
        long end = System.currentTimeMillis();

        Collections.shuffle(this.instanceNames, r);
        System.out.println(end - start);
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