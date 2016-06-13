package com.jadencode.main.constants;

import com.jadencode.main.generate.weapon.WeaponPartBase;
import com.jadencode.main.material.MaterialLibrary;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;

/**
 * Created by gtrpl on 6/11/2016.
 */
public class StatSets {
    public static final StatSet EMPTY = new StatSet();

    //Base weapon stats
    public static final StatSet BASE_SWORD = new StatSet()
            .add(Stats.DAMAGE_SLASH, 50F)
            .add(Stats.DAMAGE_PIERCE, 10F)
            .add(Stats.DAMAGE_BLUNT, 0F);

    public static final StatSet BASE_HAMMER = new StatSet()
            .add(Stats.DAMAGE_PIERCE, 0F)
            .add(Stats.DAMAGE_BLUNT, 80F)
            .add(Stats.ATTACK_TIME, 0F);

    public static final StatSet BASE_AXE = new StatSet()
            .add(Stats.DAMAGE_SLASH, 50F)
            .add(Stats.DAMAGE_BLUNT, 30F)
            .add(Stats.DAMAGE_PIERCE, 0F)
            .add(Stats.ATTACK_TIME, 0F);

    public static final StatSet BASE_BOW = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 0F)
            .add(Stats.RANGE, 50F)
            .add(Stats.DRAW_TIME, 0F);

    public static final StatSet BASE_CROSSBOW = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 0F)
            .add(Stats.RANGE, 50F)
            .add(Stats.DRAW_TIME, 0F);

    //Sword grips
    public static final StatSet GRIP_HEAVY = new StatSet()
            .add(Stats.DAMAGE_SLASH, 10F)
            .add(Stats.DAMAGE_PIERCE, 4F)
            .add(Stats.DAMAGE_BLUNT, 1F);
    public static final StatSet GRIP_BASIC = new StatSet()
            .add(Stats.DAMAGE_SLASH, 5F)
            .add(Stats.DAMAGE_PIERCE, 2F)
            .add(Stats.DAMAGE_BLUNT, 0F);
    public static final StatSet GRIP_LIGHT = new StatSet()
            .add(Stats.DAMAGE_SLASH, 0F)
            .add(Stats.DAMAGE_PIERCE, 0F)
            .add(Stats.DAMAGE_BLUNT, 0F);

    //Sword hilts
    public static final StatSet HILT_HEAVY = new StatSet()
            .add(Stats.DAMAGE_SLASH, 16F)
            .add(Stats.DAMAGE_PIERCE, 6F)
            .add(Stats.DAMAGE_BLUNT, 2F);
    public static final StatSet HILT_MEDIUM = new StatSet()
            .add(Stats.DAMAGE_SLASH, 8F)
            .add(Stats.DAMAGE_PIERCE, 3F)
            .add(Stats.DAMAGE_BLUNT, 1F);
    public static final StatSet HILT_LIGHT = new StatSet()
            .add(Stats.DAMAGE_SLASH, 0F)
            .add(Stats.DAMAGE_PIERCE, 0F)
            .add(Stats.DAMAGE_BLUNT, 0F);

    //Sword blades
    public static final StatSet BLADE_BROAD = new StatSet()
            .add(Stats.DAMAGE_SLASH, 15F)
            .add(Stats.DAMAGE_PIERCE, 15F)
            .add(Stats.DAMAGE_BLUNT, 0F);
    public static final StatSet BLADE_LONG  = new StatSet()
            .add(Stats.DAMAGE_SLASH, 20F)
            .add(Stats.DAMAGE_PIERCE, 10F)
            .add(Stats.DAMAGE_BLUNT, 0F);
    public static final StatSet BLADE_SHORT = new StatSet()
            .add(Stats.DAMAGE_SLASH, 10F)
            .add(Stats.DAMAGE_PIERCE, 5F)
            .add(Stats.DAMAGE_BLUNT, 0F);

    //Hammer handles
    public static final StatSet HANDLE_WAR_HAMMER = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet HANDLE_BATTLE_HAMMER  = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 20F)
            .add(Stats.ATTACK_TIME, 2F);
    public static final StatSet HANDLE_MAUL_HAMMER = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 30F)
            .add(Stats.ATTACK_TIME, 3F);

    //Hammer heads
    public static final StatSet HEAD_DOUBLE_HAMMER = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 20F)
            .add(Stats.ATTACK_TIME, 2F);
    public static final StatSet HEAD_SINGLE_HAMMER  = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet HEAD_HEAVY_HAMMER = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 15F)
            .add(Stats.ATTACK_TIME, 1.5F);
    public static final StatSet HEAD_SPIKED_HAMMER = new StatSet()
            .add(Stats.DAMAGE_PIERCE, 20F)
            .add(Stats.ATTACK_TIME, 0.5F);

    //Axe handles
    public static final StatSet HANDLE_WAR_AXE = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.DAMAGE_SLASH, 10F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet HANDLE_LONG_AXE  = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 15F)
            .add(Stats.DAMAGE_SLASH, 15F)
            .add(Stats.ATTACK_TIME, 1.5F);
    public static final StatSet HANDLE_BATTLE_AXE = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 20F)
            .add(Stats.DAMAGE_SLASH, 20F)
            .add(Stats.ATTACK_TIME, 2F);

    //Axe heads
    public static final StatSet HEAD_DOUBLE_AXE = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.DAMAGE_SLASH, 10F)
            .add(Stats.ATTACK_TIME, 2F);
    public static final StatSet HEAD_SINGLE_AXE  = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 5F)
            .add(Stats.DAMAGE_SLASH, 5F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet HEAD_BROAD_AXE = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 5F)
            .add(Stats.DAMAGE_SLASH, 10F)
            .add(Stats.ATTACK_TIME, 1.5F);
    public static final StatSet HEAD_SPIKED_AXE = new StatSet()
            .add(Stats.DAMAGE_PIERCE, 20F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet HEAD_SCYTHE_AXE = new StatSet()
            .add(Stats.DAMAGE_SLASH, 20F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet HEAD_ROUND_AXE  = new StatSet()
            .add(Stats.DAMAGE_BLUNT, 20F)
            .add(Stats.ATTACK_TIME, 1F);

    //Bow Strings
    public static final StatSet STRING_HEAVY_BOW = new StatSet()
            .add(Stats.RANGE, 50F)
            .add(Stats.DRAW_TIME, 2F);
    public static final StatSet STRING_LIGHT_BOW = new StatSet()
            .add(Stats.RANGE, 25F)
            .add(Stats.DRAW_TIME, 1F);

    //Bow Limbs
    public static final StatSet LIMBS_SHORT_BOW = new StatSet()
            .add(Stats.RANGE, 10F)
            .add(Stats.DRAW_TIME, 0.5F)
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet LIMBS_LONG_BOW = new StatSet()
            .add(Stats.RANGE, 30F)
            .add(Stats.DRAW_TIME, 1.5F)
            .add(Stats.DAMAGE_BLUNT, 20F)
            .add(Stats.ATTACK_TIME, 2F);
    public static final StatSet LIMBS_MEDIUM_BOW = new StatSet()
            .add(Stats.RANGE, 20F)
            .add(Stats.DRAW_TIME, 1F)
            .add(Stats.DAMAGE_BLUNT, 15F)
            .add(Stats.ATTACK_TIME, 1.5F);

    //Bow Grips
    public static final StatSet GRIP_QUICK_BOW = new StatSet()
            .add(Stats.RANGE, 20F)
            .add(Stats.DRAW_TIME, 0.5F)
            .add(Stats.DAMAGE_BLUNT, 5F)
            .add(Stats.ATTACK_TIME, 0.5F);
    public static final StatSet GRIP_STEADY_BOW = new StatSet()
            .add(Stats.RANGE, 50F)
            .add(Stats.DRAW_TIME, 2F)
            .add(Stats.DAMAGE_BLUNT, 5F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet GRIP_BALANCED_BOW = new StatSet()
            .add(Stats.RANGE, 30F)
            .add(Stats.DRAW_TIME, 1F)
            .add(Stats.DAMAGE_BLUNT, 5F)
            .add(Stats.ATTACK_TIME, 0.5F);

    //Crossbow Strings
    public static final StatSet STRING_HEAVY_CBOW = new StatSet()
            .add(Stats.RANGE, 70F)
            .add(Stats.DRAW_TIME, 2.5F);
    public static final StatSet STRING_LIGHT_CBOW = new StatSet()
            .add(Stats.RANGE, 35F)
            .add(Stats.DRAW_TIME, 1.5F);

    //Crossbow Limbs
    public static final StatSet LIMBS_LIGHT_CBOW = new StatSet()
            .add(Stats.RANGE, 20F)
            .add(Stats.DRAW_TIME, 1F)
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 0.5F);
    public static final StatSet LIMBS_HEAVY_CBOW = new StatSet()
            .add(Stats.RANGE, 40F)
            .add(Stats.DRAW_TIME, 2F)
            .add(Stats.DAMAGE_BLUNT, 20F)
            .add(Stats.ATTACK_TIME, 1F);

    //Crossbow Stocks
    public static final StatSet STOCK_RAPID_CBOW = new StatSet()
            .add(Stats.RANGE, 20F)
            .add(Stats.DRAW_TIME, 1F)
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 0.5F);
    public static final StatSet STOCK_ACCURATE_CBOW = new StatSet()
            .add(Stats.RANGE, 50F)
            .add(Stats.DRAW_TIME, 2F)
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 1F);
    public static final StatSet STOCK_BALANCED_CBOW = new StatSet()
            .add(Stats.RANGE, 35F)
            .add(Stats.DRAW_TIME, 1.5F)
            .add(Stats.DAMAGE_BLUNT, 10F)
            .add(Stats.ATTACK_TIME, 0.5F);
}
