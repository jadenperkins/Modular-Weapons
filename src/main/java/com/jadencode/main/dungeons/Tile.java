package com.jadencode.main.dungeons;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Tile
{
	public final String tileID;
	public Image texture;
	public static HashMap<String, Tile> tileList = new HashMap<String, Tile>();
	public static final int tileSize = 8;
	
	public static Tile dungeonFloor = new Tile("dungeonFloor", "fractured_floor_0");
	public static Tile dungeonWall = new Tile("dungeonWall", "fractured_floor_8");
	public static Tile dungeonDoor = new Tile("dungeonDoor", "fractured_floor_6");
	public static Tile chest = new Tile("chest", "chest");
	public static Tile key = new Tile("key", "key");
	public static Tile keyRed = new Tile("keyRed", "keyRed");
	public static Tile keyGreen = new Tile("keyGreen", "keyGreen");
	public static Tile keyBlue = new Tile("keyBlue", "keyBlue");
	public static Tile doorRed = new Tile("doorRed", "doorRed");
	public static Tile doorGreen = new Tile("doorGreen", "doorGreen");
	public static Tile doorBlue = new Tile("doorBlue", "doorBlue");
	
	public Tile(String id, String s)
	{
		if(!tileList.containsKey(id))
		{
			this.tileID = id;
			this.texture = new ImageIcon("floortiles/" + s + ".png").getImage().getScaledInstance(tileSize, tileSize, Image.SCALE_SMOOTH);
			tileList.put(tileID, this);
		}
		else
		{
			throw new RuntimeException("Tile with ID " + id + " already exists");
		}
	}
}