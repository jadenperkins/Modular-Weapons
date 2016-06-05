package com.jadencode.main.generate.weapon;

import com.jadencode.main.item.StatSet;
import com.jadencode.main.material.MaterialLibrary;
import com.jadencode.main.material.MaterialResource;

import java.util.List;

/**
 * Created by Jaden on 9/17/2015.
 */
public class WeaponPartSword extends WeaponPartBase {
    public WeaponPartSword(String name, String mod, float weight, StatSet stats, List<WeaponPart> list, MaterialLibrary... mats) {
        super(name, mod, weight, stats, list, mats);
    }
    public WeaponPartSword(String name, String mod, StatSet stats, List<WeaponPart> list, MaterialLibrary... mats) {
        this(name, mod, 1F, stats, list, mats);
    }
    @Override
    public StatSet modifyStats(MaterialResource resource) {

        StatSet set = this.getStatSet().copy();

        float slash = scaleFloat(this.getStatSet().getFloat("slashDamage"), resource);
        float pierce = scaleFloat(this.getStatSet().getFloat("pierceDamage"), resource);
        float blunt = scaleFloat(this.getStatSet().getFloat("bluntDamage"), resource);
        float prepTime = scaleFloat(this.getStatSet().getFloat("prepTime"), resource);
        float downTime = scaleFloat(this.getStatSet().getFloat("downTime"), resource);

        set.setFloat("slashDamage", slash);
        set.setFloat("pierceDamage", pierce);
        set.setFloat("bluntDamage", blunt);
        set.setFloat("prepTime", prepTime);
        set.setFloat("downTime", downTime);

        return set;
    }

    @Override
    public StatSet scaleStats(WeaponPartInstance partInstance) {
        StatSet set = partInstance.getWeaponPart().getStats().copy();

//        set.setFloat("slashDamage", (float) Math.floor(set.getFloat("slashDamage") * (float) Math.pow(1.1F, partInstance.getLevel())));
//        set.setFloat("pierceDamage", (float) Math.floor(set.getFloat("pierceDamage") * (float) Math.pow(1.1F, partInstance.getLevel())));
//        set.setFloat("bluntDamage", (float) Math.floor(set.getFloat("bluntDamage") * (float) Math.pow(1.1F, partInstance.getLevel())));
        set.setFloat("slashDamage", set.getFloat("slashDamage") * (float) Math.pow(1.1F, partInstance.getLevel() - 1));
        set.setFloat("pierceDamage", set.getFloat("pierceDamage") * (float) Math.pow(1.1F, partInstance.getLevel() - 1));
        set.setFloat("bluntDamage", set.getFloat("bluntDamage") * (float) Math.pow(1.1F, partInstance.getLevel() - 1));

        return set;
    }

    private static float scaleFloat(float f, MaterialResource resource) {
        return f * resource.getMultiplier();
//        float ret = f * (float) Math.pow(resource.getMultiplier(), Math.signum(f));
//        return ret;
    }
}