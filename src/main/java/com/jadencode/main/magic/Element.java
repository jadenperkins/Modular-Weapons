package com.jadencode.main.magic;

import com.jadencode.main.util.RandomUtil;
import com.jadencode.main.util.RangeObject;
import com.jadencode.main.util.WeightedItem;

import java.util.*;

/**
 * Created by Jaden on 2/9/2015.
 */
public class Element implements WeightedItem {

    private static final HashMap<String, Element> elements = new HashMap<>();

    public static final Element ARCANE  = new Element("Arcana", 2F, "Pure magical energy", "Arcane", Arrays.asList(
            new RangeObject<>(0, "Arcane"), new RangeObject<>(5, "Magic"),
            new RangeObject<>(20, "Runic"), new RangeObject<>(50, "Mystic")));
    public static final Element AIR     = new Element("Air", 1F, "The manifestation of air", "Aetheric", Arrays.asList(
            new RangeObject<>(0, "Air"), new RangeObject<>(5, "Feathery"),
            new RangeObject<>(20, "Spiritual"), new RangeObject<>(50, "Aetheric")));
    public static final Element WATER   = new Element("Water", 1F, "The manifestation of water", "Streaming", Arrays.asList(
            new RangeObject<>(0, "Water"), new RangeObject<>(5, "Blue"),
            new RangeObject<>(20, "Ocean"), new RangeObject<>(50, "Poseidon's")));
    public static final Element EARTH   = new Element("Earth", 1F, "The manifestation of earth", "Earthen", Arrays.asList(
            new RangeObject<>(0, "Earth"), new RangeObject<>(5, "Solid"),
            new RangeObject<>(20, "Stable"), new RangeObject<>(50, "Dense")));
    public static final Element FIRE    = new Element("Fire", 1F, "The manifestation of fire", "Fiery", Arrays.asList(
            new RangeObject<>(0, "Fire"), new RangeObject<>(5, "Blazing"),
            new RangeObject<>(20, "Blistering"), new RangeObject<>(50, "Scorching")));
    public static final Element WIND    = new Element("Wind", 0.5F, "The concentration of air magic", "Windy", Arrays.asList(
            new RangeObject<>(0, "Wind"), new RangeObject<>(15, "Breezy"), new RangeObject<>(40, "Zephyr")));
    public static final Element ICE     = new Element("Ice", 0.5F, "The concentration of water magic", "Frigid", Arrays.asList(
            new RangeObject<>(0, "Ice"), new RangeObject<>(15, "Arctic"), new RangeObject<>(40, "Glacial")));
    public static final Element STONE   = new Element("Stone", 0.5F, "The concentration of earth magic", "Hardened", Arrays.asList(
            new RangeObject<>(0, "Stone"), new RangeObject<>(15, "Rocky"), new RangeObject<>(40, "Boulder")));
    public static final Element THUNDER = new Element("Thunder", 0.5F, "The concentration of fire magic", "Thundering", Arrays.asList(
            new RangeObject<>(0, "Thunder"), new RangeObject<>(15, "Voltaic"), new RangeObject<>(40, "Electrical")));
    public static final Element STORM   = new Element("Storm", 0.25F, "The powerful combination of air and water", "Stormy", Arrays.asList(
            new RangeObject<>(0, "Storm"), new RangeObject<>(15, "Atmospheric"), new RangeObject(40, "Tempest")));
    public static final Element DUST    = new Element("Dust", 0.25F, "The powerful combination of air and earth", "Arid", Arrays.asList(
            new RangeObject<>(0, "Dust"), new RangeObject<>(15, "Sand"), new RangeObject<>(40, "Barren")));
    public static final Element SMOKE   = new Element("Smoke", 0.25F, "The powerful combination of air and fire", "Smoldering", Arrays.asList(
            new RangeObject<>(0, "Smoke"), new RangeObject(15, "Sooty"), new RangeObject(40, "Fuming")));
    public static final Element MUD     = new Element("Mud", 0.25F, "The powerful combination of earth and water", "Sludgy", Arrays.asList(
            new RangeObject<>(0, "Mud"), new RangeObject<>(15, "Slimy"), new RangeObject<>(40, "Deluge")));
    public static final Element STEAM   = new Element("Steam", 0.25F, "The powerful combination of fire and water", "Vaporous", Arrays.asList(
            new RangeObject<>(0, "Steam"), new RangeObject<>(15, "Condensed"), new RangeObject<>(40, "Hazy")));
    public static final Element MAGMA   = new Element("Magma", 0.25F, "The powerful combination of fire and earth", "Magmatic", Arrays.asList(
            new RangeObject<>(0, "Magma"), new RangeObject<>(15, "Lava"), new RangeObject<>(40, "Slag")));
    public static final Element LIGHT   = new Element("Light", 0.125F, "The deadly manipulation of light", "Luminous", Arrays.asList(
            new RangeObject<>(0, "Light"), new RangeObject<>(25, "Shining"), new RangeObject<>(60, "Shining")));
    public static final Element SHADOW  = new Element("Shadow", 0.125F, "The deadly manipulation of shadow", "Sinister", Arrays.asList(
            new RangeObject<>(0, "Shadow"), new RangeObject<>(25, "Dark"), new RangeObject<>(60, "Shady")));
    public static final Element BLOOD   = new Element("Blood", 0.125F, "The deadly manipulation of blood", "Sanguineous", Arrays.asList(
            new RangeObject<>(0, "Blood"), new RangeObject<>(25, "Red"), new RangeObject<>(60, "Sanguine")));
    public static final Element MIASMA  = new Element("Miasma", 0.125F, "The deadly manipulation of poison", "Toxic", Arrays.asList(
            new RangeObject<>(0, "Miasma"), new RangeObject<>(25, "Poisonous"), new RangeObject<>(60, "Venomous")));

    private final String                    name;
    private final String                    description;
    private final float                     weight;
    private final String                    adjective;
    private final List<RangeObject<String>> spellVariants;

    public Element(String name, float weight, String desc, String adj, List<RangeObject<String>> syn) {
        if (elements.get(name) != null) {
            throw new IllegalArgumentException("Duplicate element! " + name);
        }
        this.name = name;
        this.description = desc;
        this.weight = weight;
        this.adjective = adj;
        this.spellVariants = syn;
        elements.put(name, this);
    }
    public Element(String name, float weight, String desc, String adj) {
        this(name, weight, desc, adj, new ArrayList<>());
    }

    public static HashMap<String, Element> getElements() {
        return elements;
    }

    public String getName() {
        return name;
    }
    public String getName(Random r) {
        return RandomUtil.random(this.spellVariants, r).getRangeObj();
    }
    public String getAdjective() {
        return adjective;
    }
    public String getDescription() { return this.description; }
    @Override
    public float getWeight() {
        return weight;
    }
}