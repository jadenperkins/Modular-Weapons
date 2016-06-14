package com.jadencode.main.dungeons.api.dungeons;

import java.util.ArrayList;
import java.util.List;

public class CellCoordinate
{
	public static CellCoordinate DOOR_NORTH = new CellCoordinate(0, -4);
	public static CellCoordinate DOOR_SOUTH = new CellCoordinate(0, 4);
	public static CellCoordinate DOOR_EAST = new CellCoordinate(4, 0);
	public static CellCoordinate DOOR_WEST = new CellCoordinate(-4, 0);
	
	public int xCoord;
	public int zCoord;
	
	public CellCoordinate(int i, int k)
	{
		this.xCoord = i;
		this.zCoord = k;
	}
	public static List<CellCoordinate> getAllDoors()
	{
		List<CellCoordinate> ret = new ArrayList<CellCoordinate>();
		ret.add(DOOR_NORTH);
		ret.add(DOOR_SOUTH);
		ret.add(DOOR_EAST);
		ret.add(DOOR_WEST);
		return ret;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof CellCoordinate))
		{
			return false;
		}
		else
		{
			CellCoordinate coord = (CellCoordinate) obj;
			return this.xCoord == coord.xCoord && this.zCoord == coord.zCoord;
		}
	}
	@Override
	public String toString()
	{
		return String.format("(%d, %d)", this.xCoord, this.zCoord);
	}
}