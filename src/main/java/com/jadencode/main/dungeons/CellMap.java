package com.jadencode.main.dungeons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CellMap
{
	public StackMap<Integer, Integer, Cell> cells = new StackMap<Integer, Integer, Cell>();
	public List<Cell> allCells = new ArrayList<Cell>();
	public HashMap<Integer, List<Cell>> cellByLevel = new HashMap<Integer, List<Cell>>();
	private int size = 0;
	
	public Cell cellAt(int x, int y)
	{
		Cell c = cells.get(x, y);
		return c;
	}
	public boolean hasCell(int x, int y)
	{
		return cellAt(x, y) != null;
	}
	public boolean addCell(Cell cell)
	{
		if(hasCell(cell.xCoord, cell.yCoord))
		{
			return false;
		}
		this.cells.put(cell.xCoord, cell.yCoord, cell);
		size += 1;
		this.allCells.add(cell);
		this.cellsForLevel(cell.cellLevel).add(cell);
		return true;
	}
	public List<Cell> cellsForLevel(int level)
	{
		List<Cell> cells = this.cellByLevel.get(level);
		if(cells == null)
		{
			cells = new ArrayList<Cell>();
			this.cellByLevel.put(level, cells);
		}
		return cells;
	}
	public int size()
	{
		return this.size;
	}
}