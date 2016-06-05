package com.upadvisor.main.generate.armor;

import com.upadvisor.main.material.MaterialResource;
import com.upadvisor.main.util.Weightable;

import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public class ArmorPart {/*implements Weightable {

    private final ArmorPartBase    baseArmorPart;
    private final MaterialResource baseResource;
    private final String           partName;
    private final String           partDescription;
    private final float            weight;
    private final float            slash;
    private final float            pierce;
    private final float            blunt;
    private final float            mobility;

    public ArmorPart(ArmorPartBase part, MaterialResource resource) {//}, StatSet stats) {
        this.baseArmorPart = part;
        this.baseResource = resource;
        this.partName = resource.getName() + " " + part.getPartName();
        this.partDescription = String.format("A %s crafted from %s", part.getPartName(), resource.getName());
        this.weight = part.getWeight() * resource.getWeight();

//        this.slash = part.getSlash() * (float) Math.pow(resource.getStrengthMultiplier(), Math.signum(part.getSlash()));
        this.slash = part.getSlash() * (float) Math.pow(resource.getStrengthMultiplier(), Math.signum(part.getSlash()));
        this.pierce = part.getPierce() * (float) Math.pow(resource.getStrengthMultiplier(), Math.signum(part.getPierce()));
        this.blunt = part.getBlunt() * (float) Math.pow(resource.getStrengthMultiplier(), Math.signum(part.getBlunt()));
        this.mobility = part.getMobility() * (float) Math.pow(resource.getWeaknessMultiplier(), Math.signum(part.getMobility()));
        part.getPartList().add(this);
    }
    public ArmorPart(ArmorPartBase part) {//}, StatSet stats) {
        this.baseArmorPart = part;
        this.baseResource = null;
        this.partName = part.getPartName();
        this.partDescription = String.format("A legendary %s", part.getPartName());
        this.weight = part.getWeight() * 1F;
//        this.slash = part.getSlash();
        this.slash = 1F;
        this.pierce = 1F;
        this.blunt = 1F;
        this.mobility = 1F;
        part.getPartList().add(this);
    }


    public ArmorPartBase getBaseArmorPart() {
        return this.baseArmorPart;
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

    public float getPierce() {
        return pierce;
    }

    public float getSlash() {
        return slash;
    }

    public float getBlunt() {
        return blunt;
    }

    public float getMobility() {
        return mobility;
    }

    public static void generateArmorParts() {
        for(ArmorPartBase partBase : ArmorPartBase.getBaseParts()) {

            if(partBase.usesMaterials()) {
                List<MaterialResource> resourceList = partBase.getMaterials();
                for(MaterialResource resource : resourceList) {
                    ArmorPart part = new ArmorPart(partBase, resource);
                }
            } else {
                ArmorPart part = new ArmorPart(partBase);
            }
        }
    }     */
}
