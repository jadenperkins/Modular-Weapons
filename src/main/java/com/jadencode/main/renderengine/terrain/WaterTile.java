package com.jadencode.main.renderengine.terrain;

import com.jadencode.main.renderengine.toolbox.Transform;
import org.lwjgl.util.vector.Vector3f;

public class WaterTile implements Transform {
	
	public static final float TILE_SIZE = 60;

	private final Vector3f translation;
	private final Vector3f rotation = new Vector3f(0, 0, 0);
	private final Vector3f scale = new Vector3f(TILE_SIZE, TILE_SIZE, TILE_SIZE);

	public WaterTile(float centerX, float centerZ, float height){
		this.translation = new Vector3f(centerX, height, centerZ);
	}
	@Override
	public Vector3f getTranslation() {
		return translation;
	}
	@Override
	public Vector3f getRotation() {
		return rotation;
	}
	@Override
	public Vector3f getScale() {
		return scale;
	}
}
