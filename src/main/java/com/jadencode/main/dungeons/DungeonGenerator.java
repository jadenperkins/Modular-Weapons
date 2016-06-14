package com.jadencode.main.dungeons;

import java.util.Random;

import com.jadencode.main.dungeons.api.dungeons.CellGenerator;
import com.jadencode.main.dungeons.api.dungeons.DungeonDirection;

public class DungeonGenerator
{
	public static final int DUNGEON_MIN_SIZE = 10;
	public static final int DUNGEON_MAX_SIZE = 30;
	
	public Random rand = new Random();
	public final int size = rand.nextInt(DUNGEON_MAX_SIZE - DUNGEON_MIN_SIZE + 1) + DUNGEON_MIN_SIZE;
	public int currentSize = 0;
	
	public int lockedDoors = 0;
	
	public final CellMap cellMap = new CellMap();
	
	public void generate()
	{
		int x = 0;
		int y = 0;
		Cell entryCell = new Cell(x, y, 0);
		this.cellMap.addCell(entryCell);
		CellGenerator g = new CellGenerator(x, y);
		g.preGenerate();
		g.placeTile(0, 0, Tile.key);
		this.generateBranch(entryCell, this.size, true);
		System.out.println("Max Size = " + this.size + ", Actual = " + this.currentSize);
	}
	public void generateBranch(Cell start, int maxSize, boolean keys)
	{
		int x = start.xCoord;
		int y = start.yCoord;
		int currentSize = 0;
		while(currentSize < maxSize && this.currentSize < this.size)
		{
			//Pick a random direction to try and generate
			DungeonDirection next = DungeonDirection.random();
			//If the space in the selected direction is full
			//Try 3 times to pick a new direction
			int tries = 0;
			while(tries < 3 && this.cellMap.hasCell(x + next.x,  y + next.y))
			{
				next = next.rotated(90);
				tries++;
			}
			//If every space is full, it can't continue, so break from this
			if(this.cellMap.hasCell(x + next.x, y + next.y))
			{
				break;
			}
			Cell parent = this.cellMap.cellAt(x, y);
			
			int level = parent.cellLevel + 1;
			
			x += next.x;
			y += next.y;
			Cell c = new Cell(x, y, level);
			this.cellMap.addCell(c);
			currentSize++;
			this.currentSize = this.cellMap.size();
			parent.connectedCells.add(c);
			
			CellGenerator gen = new CellGenerator(x, y);			
			gen.preGenerate();
			
			if(keys)
			{
				gen.placeTile(0, 0, Tile.chest);
			}
			if(this.rand.nextDouble() < 0.33333D)
			{
				this.generateBranch(parent, this.rand.nextInt(1 + (maxSize / 5)) + 1, false);
			}
		}
		for(Cell cell : this.cellMap.allCells)
		{
			int cellX = cell.xCoord;
			int cellY = cell.yCoord;
			CellGenerator gen1 = new CellGenerator(cellX, cellY);
			for(Cell child : cell.connectedCells)
			{
				int cX = child.xCoord;
				int cY = child.yCoord;
				CellGenerator gen2 = new CellGenerator(cX, cY);
				
				int shiftX = cellX > cX ? 1 : cellX < cX ? -1 : 0;
				shiftX *= Cell.CELL_SIZE / 2;
				int shiftY = cellY > cY ? 1 : cellY < cY ? -1 : 0;
				shiftY *= Cell.CELL_SIZE / 2;
				
				gen1.placeDoor(-shiftX, -shiftY);
				gen2.placeDoor(shiftX, shiftY);
			}
		}
	}
}