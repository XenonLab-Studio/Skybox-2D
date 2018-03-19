/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.Graphics2D;
import java.awt.Point;


public class Block extends GameObject {

	public static Spritesheet destroy = new Spritesheet(ImageLoader.loadImage("img/destroy.png"), 10, 16, 16);
	
	private Material material;
	private Animation animation;
	
	//BLOCK DESTROYING
	private long destroyDuration;
	private long destroyStartTime;
	private boolean destroying;
	
	public Block(Material material, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.material = material;
		this.destroyDuration = 1000L;
		long dt = destroyDuration / destroy.getCols();
		animation = new Animation(destroy, dt);
	}

	public void update() {
		//BLOCK DESTROYING
		if(destroying) {
			int mouseX = GamePanel.mouse.mouseConvertedX;
			int mouseY = GamePanel.mouse.mouseConvertedY;
	
			animation.update();
			
			//INTERRUPTS IF CONDITIONS ARE NOT FULFILLED 
			if(!GamePanel.mouse.pressed || !getBox().contains(new Point(mouseX, mouseY)) || !Playstate.player.isBlockInRadius(new Point((int)x, (int)y), 2)) {
				destroying = false;	
				animation.stop();
			}
			
			//DESTROYS THE BLOCK AFTER DESTROYING TIME
			if(System.currentTimeMillis() - destroyStartTime >= destroyDuration) {
				material = Material.AIR;
				destroying = false;
				animation.stop();
			}
		} 
	}
	
	public void render(Graphics2D g) {
		g.drawImage(material.getTexture(), (int)x - Playstate.camera.getCamX(), 
				(int)y - Playstate.camera.getCamY(), null);
		
		//DESTROYING ANIMATION
		if(destroying) {
			g.drawImage(animation.getImage(), (int)x - Playstate.camera.getCamX(), 
					(int)y - Playstate.camera.getCamY(), null);
		}
	}
	
	//INIT BLOCK DESTROYING
	public void destroyBlock() {
		if(!destroying && material != Material.AIR) {
			destroying = true;
			destroyStartTime = System.currentTimeMillis();
			animation.start(0, destroy.getCols());
		}
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
