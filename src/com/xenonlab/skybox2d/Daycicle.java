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
	
	public static final int DAY = 1;
	public static final int NIGHT = 0;
	private final long FADE_TIME = 62000L;
	
	private boolean fading;
	private long startTime;
	private long fadeStartTime;
	private long duration;
	private float alpha;
	private int time;
	
	public Daycicle(int time, long duration) {
		this.time = time;
		this.duration = duration;
		startTime = System.currentTimeMillis();
		fadeStartTime = System.currentTimeMillis();
		
		if(time == DAY)alpha = 230f;
		
		light = new Light(100, 50, 50);
	}
	
	public void update() {
		if (fading) {
			if (time == NIGHT) {
				alpha = (float) (255 * Math.sin(Math.PI / 2 * (System.currentTimeMillis() - fadeStartTime) / FADE_TIME));
				if (alpha >= 230) {
					alpha = 230;
					fading = false;
					time = DAY;
					startTime = System.currentTimeMillis();
					return;
				}
			}
			
			if (time == DAY) {
				alpha = (float) (230 * Math.cos(Math.PI / 2 * (System.currentTimeMillis() - fadeStartTime) / FADE_TIME));
				if (alpha <= 0) {
					alpha = 0;
					fading = false;
					time = NIGHT;
					startTime = System.currentTimeMillis();
					return;
				}
			}
		}
		
		if (!fading) {
			if ((System.currentTimeMillis() - startTime) >= (duration / 2)) {
				fadeStartTime = System.currentTimeMillis();
				fading = true;
				return;
			}
		}
	}
	
	public void render(Graphics2D g) {
		
		BufferedImage image = new BufferedImage(GamePanel.width / GamePanel.SCALE, GamePanel.height / GamePanel.SCALE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(new Color(0, 0, 0, (int) alpha));
		g2.fillRect(0, 0, image.getWidth(), image.getHeight());
		
		// See the official documentation on: https://docs.oracle.com/javase/tutorial/2d/advanced/compositing.html
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_OUT);
		g2.setComposite(ac);
		
		light.render(g2);
		
		g.drawImage(image, 0, 0, null);
		g2.dispose();
		
	}
}
