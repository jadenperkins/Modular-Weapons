package com.jadencode.main.generate.weapon;

import com.jadencode.main.stat.StatSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jaden on 6/1/2015.
 */
public class WeaponInstance {

    private       WeaponClass                         weaponClass;
    private final HashMap<String, WeaponPartInstance> weaponParts;
    private int level = 1;
    private final String          displayName;
    private final List<String> displayInfo = new ArrayList<>();
    private final List<String> weaponInfo  = new ArrayList<>();
    private final StatSet statSet;

    public WeaponInstance(WeaponClass base, int level, HashMap<String, WeaponPartInstance> parts) {
        this.weaponClass = base;
        this.level = level;
        this.weaponParts = parts;
        this.displayName = base.getWeaponClassName();
        this.weaponParts.values().forEach(s -> this.weaponInfo.add(s.getPartInfo()));
        this.displayInfo.addAll(this.getWeaponInfo());

        this.statSet = this.weaponClass.determineStats(this);

//        StatSet stats = this.weaponClass.getStatSet().copy();
//
//
//        this.statSet = stats;


//        float dmg = this.weaponClass.getBaseDamage() * (float) Math.pow(1.1F, this.level - 1);
//        float rng = this.weaponClass.getBaseRange();
//        float tme = this.weaponClass.getBaseTime();
//        for(WeaponPartInstance part : this.getWeaponParts().values()) {
//            dmg += part.getDamageMod();
//            rng += part.getRangeMod();
//            tme += part.getTimeMod();
//        }
//        this.weaponDamage = dmg;
//        this.weaponRange = rng;
//        this.weaponTime = tme;
    }
    public WeaponInstance scaledInstance(int i) {
        HashMap<String, WeaponPartInstance> parts = new HashMap<>();
        for(String partName : this.getWeaponParts().keySet()) {
            WeaponPartInstance part = this.getWeaponParts().get(partName).scaledInstance(i);
            parts.put(partName, part);
        }
        return new WeaponInstance(this.weaponClass, i, parts);
    }

    public int getLevel() {
        return this.level;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public List<String> getDisplayInfo() {
        return this.displayInfo;
    }
    public void addPart(String type, WeaponPartInstance part) {
        this.weaponParts.put(type, part);
    }
    public WeaponPartInstance getPart(String type) {
        return this.weaponParts.get(type);
    }

    public List<String> getWeaponInfo() {
        return this.weaponInfo;
    }

    public HashMap<String, WeaponPartInstance> getWeaponParts() {
        return this.weaponParts;
    }
    public StatSet getStatSet() {
        return this.statSet;
    }
    //    public List<StatSet> getStats() {
//        List<StatSet> ret = new ArrayList<>();
//        for(WeaponPart part : this.getArmorParts()) {
//            ret.add(part.getStats());
//        }
//        return ret;
//    }
//    public StatSet getWeaponStats() {
//        if(this.weaponStats == null) {
//            List<StatSet> stats = this.getStats();
//            HashMap<StatBase, Integer> values = new HashMap<>();
//            for(StatSet statSet : stats) {
//                for(StatInstance stat : statSet.getStatInstances()) {
//                    StatBase base = stat.getBaseStat();
//                    int val = values.get(base) == null ? 0 : values.get(base);
//                    val += stat.getValue();
//                    values.put(base, val);
//                }
//            }
//            //int damage, int balance, int range, int speed, int puncture, int slash, int blunt, int magic, int defence
//            int dmg = values.get(StatBase.statDamage);
//            int bal = values.get(StatBase.statBalance);
//            int rng = values.get(StatBase.statRange);
//            int spd = values.get(StatBase.statSpeed);
//            int pnc = values.get(StatBase.statPuncture);
//            int sls = values.get(StatBase.statSlash);
//            int blt = values.get(StatBase.statBlunt);
//            int mgc = values.get(StatBase.statMagic);
//            int def = values.get(StatBase.statDefence);
//            StatSet set = new StatSet(dmg, bal, rng, spd, pnc, sls, blt, mgc, def);
//            this.weaponStats = set;
//        }
//        return this.weaponStats;
//    }

//    public Set<WeaponPart> setParts(WeaponPart... parts) {
//        Set<WeaponPart> partSet = new HashSet<>();
//        for(WeaponPart part : parts) {
//            partSet.add(part);
//        }
//        return partSet;
//    }
}