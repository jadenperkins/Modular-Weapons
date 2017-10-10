package com.main.generate.character.viking;

import com.main.generate.Generator;
import com.main.generate.character.Settlement;
import com.main.nbt.NBTTagCompound;
import com.main.nbt.NBTTagList;
import com.main.util.NBTUtils;

import java.util.*;

/**
 * Created by Jaden on 1/21/2015.
 */
public class VikingSettlementGenerator implements Generator<Settlement> {

    //22
    private static final List<String> BEGINNING_WORDS = Arrays.asList("Battle", "Black", "Dragon", "Dusk", "Dust",
            "East", "Gleam", "Gray", "Lake", "Moon", "North", "Oak", "Red", "River", "Sky", "South", "Storm", "Sun",
            "West", "White", "Wind", "Winter");
    //55
    private static final List<String> ENDING_WORDS    = Arrays.asList("arch", "belt", "blade", "blaze", "bridge",
            "brook", "camp", "creed", "crest", "den", "fire", "flame", "flare", "forge", "fort", "glare", "gleam",
            "gloom", "glow", "grove", "guard", "heart", "helm", "hold", "light", "march", "mill", "mood", "night",
            "pass", "point", "post", "reach", "rift", "rim", "rock", "rook", "ruin", "run", "shade", "shadow", "shear",
            "shire", "shore", "spear", "spire", "stable", "sword", "tree", "vale", "view", "ville", "ward", "watch",
            "wind");
    public static final List<String> types  = Arrays.asList("Monastery", "Province", "Retreat", "Command", "Harbor",
            "Village", "Outpost", "Refuge", "Sanctuary", "Landing", "Sanctum", "Stronghold", "Citadel", "Bastion",
            "Castle", "Redoubt", "Plateau", "Fields", "Embrace", "Isle", "Keep", "Rise", "Overlook", "Shrine", "Lair",
            "Hills", "Oasis", "River", "Lake", "City");

    private final List<String> instanceLocations = new ArrayList<>();
    private int index = 0;

    public VikingSettlementGenerator() {
    }

    private void preGenerate(Random r) {
        BEGINNING_WORDS.stream().forEach(s1 -> ENDING_WORDS.stream().forEach(s2 -> this.instanceLocations.add(s1 + s2)));
        Collections.shuffle(this.instanceLocations, r);
    }

    public Settlement generate(Random r, int level) {
        String name = this.instanceLocations.get(this.index);
        this.index++;
        if(this.index == this.instanceLocations.size()) {
            this.index = 0;
            this.instanceLocations.clear();
            this.preGenerate(r);
        }
        return new Settlement(name);
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        List<String> list = NBTUtils.fromStringList(nbt.getTagList("instanceLocations"));
        this.instanceLocations.addAll(list);
        this.index = nbt.getInteger("index");
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        NBTTagList list = NBTUtils.toStringList(this.instanceLocations);
        nbt.setTag("instanceLocations", list);
        nbt.setInteger("index", this.index);
    }

    @Override
    public void onCreated(Random r) {
        this.preGenerate(r);
        this.index = 0;
    }
}
