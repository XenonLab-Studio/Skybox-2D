/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Daycicle {
	
	private Light light;
	
	public Daycicle() {
		
		light = new Light(50, 25, 100);
	}
	
	public void render(Graphics2D g) {
		
		BufferedImage image = new BufferedImage(GamePanel.width / GamePanel.SCALE, GamePanel.height / GamePanel.SCALE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(new Color(0, 0, 0, 230));
		g2.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		// See the official documentation on: https://docs.oracle.com/javase/tutorial/2d/advanced/compositing.html
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_OUT);
		
	}
}
