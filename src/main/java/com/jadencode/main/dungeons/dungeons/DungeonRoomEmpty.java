package com.jadencode.main.dungeons.dungeons;

import java.util.ArrayList;
import java.util.List;

import com.jadencode.main.dungeons.api.dungeons.CellCoordinate;
import com.jadencode.main.dungeons.api.dungeons.CellGenerator;
import com.jadencode.main.dungeons.api.dungeons.DungeonDirection;
import com.jadencode.main.dungeons.api.dungeons.IDungeonRoom;

public class DungeonRoomEmpty implements IDungeonRoom
{
	@Override
	public String getName()
	{
		return "Dungeon Room Empty";
	}
	@Override
	public int getWeight()
	{
		return 10;
	}
	@Override
	public List<CellCoordinate> getChestLocations()
	{
		List<CellCoordinate> ret = new ArrayList<CellCoordinate>();
		ret.add(new CellCoordinate(-2, 0));
		ret.add(new CellCoordinate(2, 0));
		ret.add(new CellCoordinate(0, -2));
		ret.add(new CellCoordinate(0, 2));
		return ret;
	}
	@Override
	public List<CellCoordinate> getKeyChestLocations()
	{
		List<CellCoordinate> ret = new ArrayList<CellCoordinate>();
		ret.add(new CellCoordinate(-1, -1));
		ret.add(new CellCoordinate(-1, 1));
		ret.add(new CellCoordinate(1, 1));
		ret.add(new CellCoordinate(1, -1));
		return ret;
	}
	@Override
	public List<DungeonDirection> getDoorCenterLocations()
	{
		return DungeonDirection.all();
	}
	@Override
	public void populateRoom(CellGenerator generator)
	{
		
	}
}
