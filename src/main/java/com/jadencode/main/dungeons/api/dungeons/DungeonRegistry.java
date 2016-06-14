package com.jadencode.main.dungeons.api.dungeons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DungeonRegistry
{
	private static Set<Class<IDungeonRoom>> dungeonRooms = new HashSet<Class<IDungeonRoom>>();
	
	@SuppressWarnings("unchecked")
	public static void registerDungeonRoom(Class<? extends IDungeonRoom> room)
	{
		dungeonRooms.add((Class<IDungeonRoom>) room);
	}
	@SuppressWarnings("rawtypes")
	public static IDungeonRoom getRandomDungeonRoom()
	{
		List<Class<IDungeonRoom>> list = new ArrayList<Class<IDungeonRoom>>();
		list.addAll(dungeonRooms);
		Collections.shuffle(list);
		int size = list.size();
		Class[] classes = list.toArray(new Class[0]);
		Class c = classes[new java.util.Random().nextInt(size)];
		try
		{
			return (IDungeonRoom) c.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}