package com.jadencode.main.constants;

import com.google.gson.JsonObject;
import com.jadencode.main.material.Material;
import com.jadencode.main.material.MaterialModified;
import com.jadencode.main.material.MaterialModifier;
import com.jadencode.main.material.MaterialType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JPERKI8 on 6/13/2016.
 */
public final class Materials extends ContentManager {
    private static final HashMap<MaterialType, List<Material>> MATERIALS = new HashMap<>();

    public static final Material iron = new Material("Iron", Colors.get("METAL_IRON"), 32F, 1F, 1, MaterialTypes.MATERIAL_METAL);
    public static final Material silver = new Material("Silver", Colors.get("METAL_SILVER"), 16F, 0.6F, 10, MaterialTypes.MATERIAL_METAL);
    public static final Material gold = new Material("Gold", Colors.get("METAL_GOLD"), 8F, 0.8F, 20, MaterialTypes.MATERIAL_METAL);
    public static final Material cobalt = new Material("Cobalt", Colors.get("METAL_COBALT"), 4F, 1.2F, 30, MaterialTypes.MATERIAL_METAL);
    public static final Material tungsten = new Material("Tungsten", Colors.get("METAL_TUNGSTEN"), 2F, 1.4F, 40, MaterialTypes.MATERIAL_METAL);
    public static final Material titanium = new Material("Titanium", Colors.get("METAL_TITANIUM"), 1F, 1.8F, 50, MaterialTypes.MATERIAL_METAL);

    public static final Material ash       = new Material("Ash", Colors.get("WOOD_ASH"), 21F, 1F, 1, MaterialTypes.MATERIAL_WOOD);
    public static final Material beech     = new Material("Beech", Colors.get("WOOD_BEECH"), 20F, 1F, 4, MaterialTypes.MATERIAL_WOOD);
    public static final Material birch     = new Material("Birch", Colors.get("WOOD_BIRCH"), 19F, 1F, 9, MaterialTypes.MATERIAL_WOOD);
    public static final Material elm       = new Material("Elm", Colors.get("WOOD_ELM"), 18F, 1F, 12, MaterialTypes.MATERIAL_WOOD);
    public static final Material ebony     = new Material("Ebony", Colors.get("WOOD_EBONY"), 17F, 1F, 15, MaterialTypes.MATERIAL_WOOD);
    public static final Material holly     = new Material("Holly", Colors.get("WOOD_HOLLY"), 16F, 1F, 18, MaterialTypes.MATERIAL_WOOD);
    public static final Material hornbeam  = new Material("Hornbeam", Colors.get("WOOD_HORNBEAM"), 15F, 1F, 21, MaterialTypes.MATERIAL_WOOD);
    public static final Material juniper   = new Material("Juniper", Colors.get("WOOD_JUNIPER"), 14F, 1F, 24, MaterialTypes.MATERIAL_WOOD);
    public static final Material koa       = new Material("Koa", Colors.get("WOOD_KOA"), 13F, 1F, 27, MaterialTypes.MATERIAL_WOOD);
    public static final Material laurel    = new Material("Laurel", Colors.get("WOOD_LAUREL"), 12F, 1F, 30, MaterialTypes.MATERIAL_WOOD);
    public static final Material locust    = new Material("Locust", Colors.get("WOOD_LOCUST"), 11F, 1F, 33, MaterialTypes.MATERIAL_WOOD);
    public static final Material maple     = new Material("Maple", Colors.get("WOOD_MAPLE"), 10F, 1F, 36, MaterialTypes.MATERIAL_WOOD);
    public static final Material mesquite  = new Material("Mesquite", Colors.get("WOOD_MESQUITE"), 9F, 1F, 39, MaterialTypes.MATERIAL_WOOD);
    public static final Material mahogany  = new Material("Mahogany", Colors.get("WOOD_MAHOGANY"), 8F, 1F, 42, MaterialTypes.MATERIAL_WOOD);
    public static final Material oak       = new Material("Oak", Colors.get("WOOD_OAK"), 7F, 1F, 45, MaterialTypes.MATERIAL_WOOD);
    public static final Material palm      = new Material("Palm", Colors.get("WOOD_PALM"), 6F, 1F, 48, MaterialTypes.MATERIAL_WOOD);
    public static final Material snakewood = new Material("Snakewood", Colors.get("WOOD_SNAKEWOOD"), 5F, 1F, 51, MaterialTypes.MATERIAL_WOOD);
    public static final Material teak      = new Material("Teak", Colors.get("WOOD_TEAK"), 4F, 1F, 54, MaterialTypes.MATERIAL_WOOD);
    public static final Material walnut    = new Material("Walnut", Colors.get("WOOD_WALNUT"), 3F, 1F, 57, MaterialTypes.MATERIAL_WOOD);
    public static final Material yew       = new Material("Yew", Colors.get("WOOD_YEW"), 1F, 1F, 60, MaterialTypes.MATERIAL_WOOD);
    public static final Material zebrawood = new Material("Zebrawood", Colors.get("WOOD_ZEBRAWOOD"), 1F, 1F, 63, MaterialTypes.MATERIAL_WOOD);


    public static final Material cotton = new Material("Cotton", Colors.get("PLANT_COTTON"), 1F, 1F, 1, MaterialTypes.MATERIAL_PLANT);
    public static final Material jute   = new Material("Jute", Colors.get("PLANT_JUTE"), 1F, 1F, 25, MaterialTypes.MATERIAL_PLANT);



    public static final Material jade     = new Material("Jade", Colors.get("GEM_JADE"), 128F, 1F, 1, MaterialTypes.MATERIAL_GEM);
    public static final Material topaz    = new Material("Topaz", Colors.get("GEM_TOPAZ"), 64F, 1F, 8, MaterialTypes.MATERIAL_GEM);
    public static final Material garnet   = new Material("Garnet", Colors.get("GEM_GARNET"), 32F, 1F, 15, MaterialTypes.MATERIAL_GEM);
    public static final Material amethyst = new Material("Amethyst", Colors.get("GEM_AMETHYST"), 16F, 1F, 22, MaterialTypes.MATERIAL_GEM);
    public static final Material sapphire = new Material("Sapphire", Colors.get("GEM_SAPPHIRE"), 8F, 1F, 29, MaterialTypes.MATERIAL_GEM);
    public static final Material emerald  = new Material("Emerald", Colors.get("GEM_EMERALD"), 4F, 1F, 36, MaterialTypes.MATERIAL_GEM);
    public static final Material ruby     = new Material("Ruby", Colors.get("GEM_RUBY"), 2F, 1F, 43, MaterialTypes.MATERIAL_GEM);
    public static final Material diamond  = new Material("Diamond", Colors.get("GEM_DIAMOND"), 1F, 1F, 50, MaterialTypes.MATERIAL_GEM);






    public static void register(MaterialType type, Material material) {
        if(!MATERIALS.containsKey(type)) MATERIALS.put(type, new ArrayList<>());
        MATERIALS.get(type).add(material);
    }
    public static List<Material> getMaterials(MaterialType type) {
        return MATERIALS.getOrDefault(type, new ArrayList<>());
    }

    public static void load() {
        for (MaterialType type : MaterialTypes.getMaterialTypes()) {
            type.generateExotics();
            List<Material> baseMaterials = new ArrayList<>(getMaterials(type));
            for (Material material : baseMaterials) {
                for (MaterialModifier modifier : MaterialModifiers.getModifiers(type)) {
                    new MaterialModified(material, modifier);
                }
            }
            System.out.println(String.format("Registered %d %s materials!", getMaterials(type).size(), type.getName()));
        }
    }

    public Materials() {
        super("Materials", 6);
    }

    @Override
    public void consume(String name, JsonObject obj) {

    }
}