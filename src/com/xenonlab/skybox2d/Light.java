/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Light {
	
	public static final BufferedImage LIGHT = ImageLoader.loadImage("img/light.png");
	
	private int x;
	private int y;
	private int radius;
	
	public Light(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(LIGHT, x-radius - Playstate.camera.getCamX(), y-radius - Playstate.camera.getCamY(), radius*2, radius*2, null);
	}
}
