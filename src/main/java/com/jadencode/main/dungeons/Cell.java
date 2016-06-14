package com.jadencode.main.dungeons;

import java.util.ArrayList;
import java.util.List;

public class Cell
{
	public static final int CELL_SIZE = 9;

	public final int xCoord;
	public final int yCoord;
	public final int cellLevel;
	
	public List<Cell> connectedCells = new ArrayList<Cell>();
	
	public Cell(int x, int y, int level)
	{
		this.xCoord = x;
		this.yCoord = y;
		this.cellLevel = level;
	}
}