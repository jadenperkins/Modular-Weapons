package com.jadencode.main.generate.character;

import java.util.Random;

/**
 * Created by Jaden on 2/3/2015.
 */
public class Actor {
    private final String name;
    private final Clan clan;
    private Random rand = new Random();
    private int age;
    private boolean isMale;

    public Actor(String fullName, String clanName, boolean isMale) {
        this.name = fullName;
        this.clan = Clan.getClan(clanName);
        this.clan.addDenizen(this);
        this.isMale = isMale;
        int mark = this.rand.nextInt(40);
        this.age = mark + this.rand.nextInt(mark + 1);
    }

    public String getName() {
        return this.name;
    }

    public boolean hasClan() {
        return this.clan != Clan.clanless;
    }

    public Clan getClan() {
        return this.clan;
    }

    public int getAge() {
        return this.age;
    }

    public boolean isMale() {
        return this.isMale;
    }
}
