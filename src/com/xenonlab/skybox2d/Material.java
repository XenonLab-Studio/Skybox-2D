package com.xenonlab.skybox2d;
import java.awt.image.BufferedImage;


public enum Material {
	
	AIR(0, true, true),
	STONE(1, false, true),
	DIRT(2, false, true),
	DIRT_GRASS(3, false, true),
	SAND(4, false, false),
	DEFAULT_GRASS(5, true, false),
	PINE_TRUNK(6, false, false),
	PINE_LEAVES(7, false, false);
	
	private int id;
	private boolean walkable;
	private boolean weightless;
	private BufferedImage texture;

	private Material(int id, boolean walkable, boolean weightless) {
		this.id = id;
		this.walkable = walkable;
		this.weightless = weightless;
		this.texture = Main.spritesheet.getTexture(id);
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public boolean isWeightless() {
		return weightless;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public static Material getMaterial(int id) {
		return Material.values()[id];
	}
}
