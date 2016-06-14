package com.jadencode.main.dungeons.api.dungeons;

import com.jadencode.main.dungeons.Cell;
import com.jadencode.main.dungeons.DungeonDisplayPanel;
import com.jadencode.main.dungeons.Tile;


public class CellGenerator
{
	private int cellX;
	private int cellZ;
//	public IDungeonPiece dungeonPiece;
//	public CellGenerator(int x, int z, IDungeonPiece piece)
//	{
//		cellX = x;
//		cellZ = z;
//		this.dungeonPiece = piece;
//	}
	public CellGenerator(int x, int z)
	{
		cellX = x;
		cellZ = z;
	}
	public void preGenerate()
	{
		int end = Cell.CELL_SIZE / 2;
		for(int i = -end; i <= end; i++)
		{
			for(int k = -end; k <= end; k++)
			{
				if((i == -end || i == end) && (k >= -end && k <= end))
				{
					placeWall(i, k);
				}
				else if((i >= -end && i <= end) && (k == -end || k == end))
				{
					placeWall(i, k);
				}
				else
				{
					placeFloor(i, k);
				}
			}
		}
	}
	public void placeDoor(int x, int z)
	{
		placeTile(x, z, Tile.dungeonDoor);
	}
	public void placeWall(int x, int z)
	{
		placeTile(x, z, Tile.dungeonWall);
	}
	public void placeFloor(int x, int z)
	{
		placeTile(x, z, Tile.dungeonFloor);
	}
	public void placeTile(int x, int z, Tile t)
	{
		int xShift = Cell.CELL_SIZE * cellX + x;
		int zShift = Cell.CELL_SIZE * cellZ + z;
		DungeonDisplayPanel.theDisplay.placeTileInWorld(xShift, zShift, t);
//		this.parentCell.placeTile(x, z, t);
	}
}