package com.jadencode.main.dungeons.api.dungeons;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum DungeonDirection
{
	//UP(-2),
	//DOWN(-1),
	NORTH(0, 0, -1),
	EAST(1, 1, 0),
	SOUTH(2, 0, 1),
	WEST(3, -1, 0);
	
	public final int code;
	public final int x;
	public final int y;
	private DungeonDirection(int c, int x, int y)
	{
		code = c;
		this.x = x;
		this.y = y;
	}
	public static DungeonDirection random()
	{
		Random r = new Random();
		DungeonDirection[] all = DungeonDirection.values();
		int size = all.length;
		return all[r.nextInt(size)];
	}
	public static List<DungeonDirection> all()
	{
		List<DungeonDirection> ret = Arrays.asList(new DungeonDirection[]{NORTH, EAST, SOUTH, WEST});
		return ret;
	}
	public DungeonDirection opposite()
	{
		switch(this)
		{
		case NORTH: return SOUTH;
		case SOUTH: return NORTH;
		case EAST: return WEST;
		case WEST: return EAST;
		}
		return null;
	}
	public DungeonDirection rotated(int deg)
	{
		if(deg < 0)
		{
			deg = (deg % 360) + 360;
		}
		int code = this.code;
		
		int numberRot = deg / 90;
		code = (code + numberRot) % 4;
		switch(code)
		{
		case 0:
			return NORTH;
		case 1:
			return EAST;
		case 2:
			return SOUTH;
		case 3:
			return WEST;
		}
		return null;
	}
}