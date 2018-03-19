/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Cell extends GameObject {

	public static final BufferedImage TEXTURE = ImageLoader.loadImage("img/slot.jpg");
	public static final BufferedImage SELECTED_TEXTURE = ImageLoader.loadImage("img/slotselected.png");
	
	private int amount;
	private Item item;
	
	public Cell(int x, int y, int width, int height) {
		super(x, y, width, height);
		item = null;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(TEXTURE, (int)x, (int)y, null);
	}
	
	public void addItem(Item item) {
		this.item = item;
		amount++;
	}
	
	public void deleteItem() {
		item = null;
		amount = 0;
	}

	public int getItemAmount() {
		return amount;
	}

	public Item getItem() {
		return item;
	}
	
	
}
