package com.jadencode.main.dungeons;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DungeonDisplayPanel extends JPanel
{
	public static DungeonDisplayPanel theDisplay;
	
	public HashMap<Integer, HashMap<Integer, Tile>> worldTileMap = new HashMap<Integer, HashMap<Integer, Tile>>();
	
	public int originX = 16 * 41;
	public int originZ = 16 * 20;
	
	
	public DungeonGenerator generator = new DungeonGenerator();
		
	public DungeonDisplayPanel()
	{
		theDisplay = this;
		generator.generate();
	}
	public void placeTileInWorld(int x, int z, Tile t)
	{
		HashMap<Integer, Tile> map = worldTileMap.get(x);
		if(map == null)
		{
			map = new HashMap<Integer, Tile>();
		}
		map.put(z, t);
		worldTileMap.put(x, map);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for(int x : worldTileMap.keySet())
		{
			for(int z : worldTileMap.get(x).keySet())
			{
				Image i = worldTileMap.get(x).get(z).texture;
				int shiftedX = this.originX + (x * Tile.tileSize);
				int shiftedZ = this.originZ - (z * Tile.tileSize);
				g.drawImage(i, shiftedX, shiftedZ, this);
			}
		}
	}
}