package com.main.util;

import com.main.nbt.NBTTagCompound;
import com.main.nbt.NBTTagList;
import com.main.nbt.NBTTagString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaden on 7/23/2014.
 */
public class NBTUtils {
    public static final int NBT_ID_END      = 0;
    public static final int NBT_ID_BYTE     = 1;
    public static final int NBT_ID_SHORT    = 2;
    public static final int NBT_ID_INT      = 3;
    public static final int NBT_ID_LONG     = 4;
    public static final int NBT_ID_FLOAT    = 5;
    public static final int NBT_ID_DOUBLE   = 6;
    public static final int NBT_ID_BYTEA    = 7;
    public static final int NBT_ID_STRING   = 8;
    public static final int NBT_ID_LIST     = 9;
    public static final int NBT_ID_COMPOUND = 10;
    public static final int NBT_ID_INTA     = 11;

    public static NBTTagList toTagList(List<? extends NBTTaggable> list) {
        NBTTagList tag = new NBTTagList();
        for (int i = 0; i < list.size(); i++) {
            NBTTagCompound o = new NBTTagCompound();
            list.get(i).writeToNBT(o);
            tag.appendTag(o);
        }
        return tag;
    }

    public static <T extends NBTTaggable> List<T> fromTagList(NBTTagList tag, Class<T> c) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < tag.tagCount(); i++) {
            NBTTagCompound o = (NBTTagCompound) tag.tagAt(i);
            try {
                T object = c.newInstance();
                object.readFromNBT(o);
                list.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static <T> NBTTagList toTagList(List<T> list, NBTTagger<T> tagger) {
        NBTTagList tag = new NBTTagList();

        for (T o : list) {
            NBTTagCompound nbt = tagger.toNBT(o);
            tag.appendTag(nbt);
        }
        return tag;
    }

    public static <T> List<T> fromTagList(NBTTagList tag, NBTTagger<T> tagger) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < tag.tagCount(); i++) {
            try {
                NBTTagCompound nbt = (NBTTagCompound) tag.tagAt(i);
                T object = tagger.fromNBT(nbt);
                list.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static NBTTagList toStringList(List<String> list) {
        NBTTagList tag = new NBTTagList();
        for (String s : list) {
            tag.appendTag(new NBTTagString(s, s));
        }
        return tag;
    }

    public static ArrayList<String> fromStringList(NBTTagList tag) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < tag.tagCount(); i++) {
            String s = ((NBTTagString)tag.tagAt(i)).data;
            list.add(s);
        }
        return list;
    }
    public static boolean hasKeysAnd(NBTTagCompound nbt, String... keys) {
        for (String k : keys) {
            if (!nbt.hasKey(k)) return false;
        }
        return true;
    }
    public static boolean hasKeysOr(NBTTagCompound nbt, String... keys) {
        for (String k : keys) {
            if (nbt.hasKey(k)) return true;
        }
        return false;
    }
}