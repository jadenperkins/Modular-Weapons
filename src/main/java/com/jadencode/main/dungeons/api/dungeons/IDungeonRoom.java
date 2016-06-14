package com.jadencode.main.dungeons.api.dungeons;

import java.util.List;

public interface IDungeonRoom extends IDungeonPiece
{
	/**Determine where chests may be placed in this room. Return null if the generator should avoid placing
	 * random loot chests. Do not include key chest locations in this - this if for random loot only
	 * @return a list of valid BlockLocations for loot chests*/
	public List<CellCoordinate> getChestLocations();
	/**Determine where key chests may be placed in this room
	 * This should return a list with at least one block location, or else the chest will always be random
	 * @return a list of valid BlockLocations for key chests*/
	public List<CellCoordinate> getKeyChestLocations();
	/**Get locations where doors can be placed. Requires at least one door location to work. See:
	 * {@link DungeonDirection#NORTH}
	 * {@link DungeonDirection#SOUTH}
	 * {@link DungeonDirection#EAST}
	 * {@link DungeonDirection#WEST}
	 * @return list of valid door locations*/
	public List<DungeonDirection> getDoorCenterLocations();
	/**Use this to add blocks and entities to the room.*/
	public void populateRoom(CellGenerator generator);
}