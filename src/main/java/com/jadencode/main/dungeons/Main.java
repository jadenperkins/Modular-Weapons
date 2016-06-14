package com.jadencode.main.dungeons;

import com.jadencode.main.dungeons.api.dungeons.DungeonRegistry;
import com.jadencode.main.dungeons.dungeons.DungeonRoomEmpty;

public class Main
{
	public static void main(String[] args)
	{
		DungeonRegistry.registerDungeonRoom(DungeonRoomEmpty.class);
		new DungeonDisplay();
	}
}