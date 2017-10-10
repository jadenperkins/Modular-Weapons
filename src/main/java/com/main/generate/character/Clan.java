package com.main.generate.character;

import com.main.BiMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jaden on 2/3/2015.
 */
public class Clan {

    private static final HashMap<String, Clan> clans = new HashMap<>();
    public static final Clan clanless = new Clan("Clanless");
    static {
        clans.put("", clanless);
    }

    public static Clan getClan(String name) {
        if(clans.get(name) != null) {
            return clans.get(name);
        }
        Clan clan = new Clan(name);
        clans.put(name, clan);
        return clan;
    }


    private final BiMap<Actor, Actor, Relationship> relationships               = new BiMap<>();
    private final HashMap<String, Actor>                        denizens           = new HashMap<>();
    private final HashMap<Actor, Set<Relationship>>             actorRelationships = new HashMap<>();
    private final Set<Actor>                                    males              = new HashSet<>();
    private final Set<Actor>                                    females            = new HashSet<>();
    private final Set<Actor>                                    allActors          = new HashSet<>();

    private final String name;

    public Clan(String name) {
        this.name = name;
    }

    public int clanSize() {
        return this.allActors.size();
    }

    public void addDenizen(Actor actor) {
        this.identifyRelationships(actor);
        denizens.put(actor.getName(), actor);
        if (actor.isMale())
            males.add(actor);
        else
            females.add(actor);
        this.allActors.add(actor);
    }

    public String getName() {
        return name;
    }

    public Set<Actor> getFemales() {
        return this.females;
    }

    public Set<Actor> getMales() {
        return this.males;
    }

    public Set<Actor> getAll() {
        return this.allActors;
    }

    public void identifyRelationships(Actor actor) {
//        if(this != clanless) {
//            int actorAge = actor.getAge();
//            boolean actorMale = actor.isMale();
//
//            for(Actor other : this.males) {
//                if(rand.nextInt(4) == 0) {
//                    if(actorMale) {
//                        if(actorAge < other.getAge() + 18) {
//                            this.relationships.put(actor, other, Relationship.SON);
//                            this.relationships.put(other, actor, Relationship.FATHER);
//                        }
//                        if(actorAge > other.getAge() + 18) {
//                            this.relationships.put(actor, other, Relationship.FATHER);
//                            this.relationships.put(other, actor, Relationship.SON);
//                        }
//                        else {
//                            this.relationships.put(actor, other, Relationship.BROTHER);
//                            this.relationships.put(other, actor, Relationship.BROTHER);
//                        }
//                    }
//                    else {
//                        if(actorAge + 18 < other.getAge()) {
//                            this.relationships.put(actor, other, Relationship.SON);
//                            this.relationships.put(other, actor, Relationship.MOTHER);
//                        }
//                        if(actorAge > other.getAge() + 18) {
//                            this.relationships.put(actor, other, Relationship.FATHER);
//                            this.relationships.put(other, actor, Relationship.DAUGHTER);
//                        }
//                    }
//                }
//            }
//        }
    }

    public Relationship addRelationship(Actor actor1, Actor actor2) {
        int age1 = actor1.getAge();
        int age2 = actor2.getAge();
        boolean gender1 = actor1.isMale();
        boolean gender2 = actor2.isMale();

        Relationship theRelationship = null;

        //both are males
        if(gender1 && gender2) {
            //assume that fathers are at least 18 years older
            if(age1 - age2 >= 18) {
            }
        }
        return theRelationship;
    }

    public Set<Relationship> getRelationships(Actor actor) {
        return this.actorRelationships.get(actor);
    }
}