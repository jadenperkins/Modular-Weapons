package com.jadencode.main.dungeons;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DungeonDisplay extends JFrame
{
	public static Image[] dungeonTiles = getFloorTiles();
	public DungeonDisplay()
	{
		super("Dungeon Rummage generator");
		this.setSize(1360, 704);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new DungeonDisplayPanel());
		this.setVisible(true);
	}
	public static Image[] getFloorTiles()
	{
		Image[] ret = new Image[11];
		for(int i = 0; i < 11; i++)
		{
			ret[i] = new ImageIcon("floortiles/fractured_floor_" + i + ".png").getImage();
		}
		return ret;
	}
}