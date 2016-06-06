package com.jadencode.main.generate.weapon;


import com.jadencode.main.stat.StatSet;
import com.jadencode.main.material.MaterialResource;
import com.jadencode.main.util.Weightable;

import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponPart implements Weightable {

    private final WeaponPartBase   baseWeaponPart;
    private final MaterialResource baseResource;
    private final String           partName;
    private final String           partDescription;
    private final float            weight;
    private final StatSet          stats;

    public WeaponPart(WeaponPartBase part, MaterialResource resource) {//}, StatSet stats) {
        this.baseWeaponPart = part;
        this.baseResource = resource;
        this.partName = resource.getName() + " " + part.getPartName();
        this.partDescription = String.format("A %s crafted from %s", part.getPartName(), resource.getName());
        this.weight = part.getWeight() * resource.getWeight();

        this.stats = part.modifyStats(resource);

        part.getPartList().add(this);
    }
    public WeaponPart(WeaponPartBase part) {//}, StatSet stats) {
        this.baseWeaponPart = part;
        this.baseResource = null;
        this.partName = part.getPartName();
        this.partDescription = String.format("A %s", part.getPartName());
        this.weight = part.getWeight() * 1F;

        this.stats = part.getStatSet();

        part.getPartList().add(this);
    }
    public WeaponPartBase getBaseWeaponPart() {
        return this.baseWeaponPart;
    }
    public StatSet getStats() {
        return stats;
    }

    public boolean hasBaseResource() {
        return this.getBaseResource() != null;
    }
    public MaterialResource getBaseResource() {
        return this.baseResource;
    }

    public String getPartName() {
        return this.partName;
    }
    public String getPartInfo() {
        return this.partDescription;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    public static void generateWeaponParts() {
        for(WeaponPartBase partBase : WeaponPartBase.getBaseParts()) {

            if(partBase.usesMaterials()) {
                List<MaterialResource> resourceList = partBase.getMaterials();
                for(MaterialResource resource : resourceList) {
                    WeaponPart part = new WeaponPart(partBase, resource);
                }
            } else {
                WeaponPart part = new WeaponPart(partBase);
            }
        }
    }
}
