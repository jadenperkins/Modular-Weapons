package com.jadencode.main.generate.weapon;

import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;
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

        float slash = scaleFloat(this.getStatSet().get(StatBase.DAMAGE_SLASH), resource);
        float pierce = scaleFloat(this.getStatSet().get(StatBase.DAMAGE_PIERCE), resource);
        float blunt = scaleFloat(this.getStatSet().get(StatBase.DAMAGE_BLUNT), resource);

        set.add(StatBase.DAMAGE_SLASH, slash);
        set.add(StatBase.DAMAGE_PIERCE, pierce);
        set.add(StatBase.DAMAGE_BLUNT, blunt);

        return set;
    }

    @Override
    public StatSet scaleStats(WeaponPartInstance partInstance) {
        StatSet set = partInstance.getWeaponPart().getStats().copy();

        float f = (float) Math.pow(1.1F, partInstance.getLevel() - 1);
        set.add(StatBase.DAMAGE_SLASH, set.get(StatBase.DAMAGE_SLASH) * f);
        set.add(StatBase.DAMAGE_PIERCE, set.get(StatBase.DAMAGE_SLASH) * f);
        set.add(StatBase.DAMAGE_BLUNT, set.get(StatBase.DAMAGE_SLASH) * f);

        return set;
    }

    private static float scaleFloat(float f, MaterialResource resource) {
        return f * resource.getMultiplier();
//        float ret = f * (float) Math.pow(resource.getMultiplier(), Math.signum(f));
//        return ret;
    }
}