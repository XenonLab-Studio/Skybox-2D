package com.xenonlab.skybox2d;
import java.awt.image.BufferedImage;



public class Item {
	
	private int maxAmount;
	private boolean stackable;
	private Object type;
	private BufferedImage image;
	
	public Item(Object type, BufferedImage image, int maxAmount, boolean stackable) {
		this.maxAmount = maxAmount;
		this.stackable = stackable;
		this.type = type;
		this.image = image;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public boolean isStackable() {
		return stackable;
	}

	public Object getType() {
		return type;
	}

	public BufferedImage getImage() {
		return image;
	}
}
