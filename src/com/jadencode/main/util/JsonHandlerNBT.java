package com.upadvisor.main.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.upadvisor.main.nbt.*;
import org.apache.commons.lang3.text.WordUtils;

import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Jaden
 * Date: 6/18/14
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonHandlerNBT implements JsonHandler<NBTTagCompound>
{
    public static Class[] classByID = {
            NBTTagEnd.class, NBTTagByte.class, NBTTagShort.class, NBTTagInt.class, NBTTagLong.class,
            NBTTagFloat.class, NBTTagDouble.class, NBTTagByteArray.class, NBTTagString.class,
            NBTTagList.class, NBTTagCompound.class, NBTTagIntArray.class, null
    };

    @Override
    public NBTTagCompound fromJson(JsonObject jsonObject) {
        return parseCompound(jsonObject);
    }

    private NBTTagCompound parseCompound(JsonObject jsonObject) {
        NBTTagCompound ret = new NBTTagCompound();
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> e : entries) {
            String key = e.getKey();
            JsonElement element = e.getValue();
            String[] split = key.split(":");
            byte id = (byte) Integer.parseInt(split[0]);
            key = split[1];

            String type = NBTBase.NBTTypes[id];
            if (type.equals("COMPOUND")) {
                ret.setTag(key, parseCompound(element.getAsJsonObject()));
            }
            else if(type.equals("LIST"))
            {
                ret.setTag(key, parseList(element.getAsJsonArray()));
            }
            else if(type.equals("BYTE[]"))
            {
                ret.setByteArray(key, parseBytes(element.getAsJsonArray()));
            }
            else if(type.equals("INT[]"))
            {
                ret.setIntArray(key, parseInts(element.getAsJsonArray()));
            }
            else
            {
                ret.setTag(key, parsePrimitive(id, key, element.getAsJsonPrimitive()));
            }
        }
        return ret;
    }
    private byte[] parseBytes(JsonArray array)
    {
        byte[] bytes = new byte[array.size()];
        for(int i = 0; i < bytes.length; i++)
        {
            bytes[i] = array.get(i).getAsByte();
        }
        return bytes;
    }
    private int[] parseInts(JsonArray array)
    {
        int[] ints = new int[array.size()];
        for(int i = 0; i < array.size(); i++)
        {
            ints[i] = array.get(i).getAsInt();
        }
        return ints;
    }
    private NBTTagList parseList(JsonArray array)
    {
        NBTTagList list = new NBTTagList();
        for(int i = 0; i < array.size(); i++)
        {
            JsonElement element = array.get(i);
            if(element.isJsonObject())
            {
                list.appendTag(parseCompound(element.getAsJsonObject()));
            }
            else if(element.isJsonArray())
            {
                list.appendTag(parseList(element.getAsJsonArray()));
            }
            else if(element.isJsonPrimitive())
            {
                String primitive = element.getAsString();
                byte id = Byte.parseByte(primitive.split(":")[0]);
                String key = primitive.split(":")[1];
                list.appendTag(parsePrimitive(id, "", new JsonPrimitive(key)));
            }
        }
        return list;
    }
    private static HashMap<String, Class> primitives = new HashMap<String, Class>();
    static
    {
        primitives.put("Byte", byte.class);
        primitives.put("Int", int.class);
        primitives.put("Double", double.class);
        primitives.put("Float", float.class);
        primitives.put("Long", long.class);
        primitives.put("Short", short.class);
    }
    private NBTBase parsePrimitive(byte id, String key, JsonPrimitive primitive)
    {
        String type = WordUtils.capitalize(NBTBase.NBTTypes[id].toLowerCase());
        if(type.equals("String"))
        {
            return new NBTTagString(primitive.getAsString());
        }
        else
        {
            try
            {
                Class c = Class.forName("java.lang." + (type.equals("Int") ? "Integer" : type));
                Method parse = c.getDeclaredMethod("parse" + type, String.class);
                NBTBase nbt = (NBTBase)classByID[id].getConstructor(String.class, primitives.get(type)).newInstance(key, parse.invoke(null, primitive.getAsString()));
                return nbt;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public JsonObject toJson(NBTTagCompound obj)
    {
        return parseCompound(obj);
    }
    private JsonObject parseCompound(NBTTagCompound nbt)
    {
        JsonObject json = new JsonObject();
        for(String s : (Set<String>)nbt.getTags())
        {
            if(nbt.getTag(s).getClass() == NBTTagCompound.class)
            {
                json.add(Integer.toString(nbt.getTag(s).getId()), parseCompound((NBTTagCompound)nbt.getTag(s)));
            }
            else if(nbt.getTag(s).getClass() == NBTTagList.class)
            {
                json.add(Integer.toString(nbt.getTag(s).getId()), parseList((NBTTagList)nbt.getTag(s)));
            }
            else if(nbt.getTag(s).getClass() == NBTTagIntArray.class)
            {
                json.add(Integer.toString(nbt.getTag(s).getId()), parseInts((NBTTagIntArray)nbt.getTag(s)));
            }
            else if(nbt.getTag(s).getClass() == NBTTagByteArray.class)
            {
                json.add(Integer.toString(nbt.getTag(s).getId()), parseBytes((NBTTagByteArray)nbt.getTag(s)));
            }
            else if(nbt.getTag(s).getClass() == NBTTagString.class)
            {
                json.addProperty(Integer.toString(nbt.getTag(s).getId()), nbt.getTag(s).toString());
            }
            else
            {
                try
                {
                    json.addProperty(Integer.toString(nbt.getTag(s).getId()), NumberFormat.getInstance().parse(nbt.getTag(s).toString()));
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }
    private JsonArray parseList(NBTTagList list)
    {
        JsonArray array = new JsonArray();
        for(int i = 0; i < list.tagCount(); i++)
        {
            NBTBase n = list.tagAt(i);
            if(n.getClass() == NBTTagCompound.class)
            {
                array.add(parseCompound((NBTTagCompound) n));
            }
            else if(n.getClass() == NBTTagList.class)
            {
                array.add(parseList((NBTTagList)n));
            }
            else if(n.getClass() == NBTTagIntArray.class)
            {
                array.add(parseInts((NBTTagIntArray)n));
            }
            else if(n.getClass() == NBTTagByteArray.class)
            {
                array.add(parseBytes((NBTTagByteArray)n));
            }
            else
            {
                array.add(new JsonPrimitive(n.getId() + ":" + n.toString()));
            }
        }
        return array;
    }
    private JsonArray parseInts(NBTTagIntArray array)
    {
        JsonArray ret = new JsonArray();
        for(int i : array.intArray)
        {
            ret.add(new JsonPrimitive(i));
        }
        return ret;
    }
    private JsonArray parseBytes(NBTTagByteArray array)
    {
        JsonArray ret = new JsonArray();
        for(byte b : array.byteArray)
        {
            ret.add(new JsonPrimitive(b));
        }
        return ret;
    }
}