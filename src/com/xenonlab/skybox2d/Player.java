/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Player extends Entity {

	
	private boolean destroyingBlock;
	
	
	public Player(float x, float y, int width, int height, float speed) {
		super(new Spritesheet(ImageLoader.loadImage("img/player.png"), 2, 20, 32), x, y, width, height, speed);
	}
	
	@Override
	public void update() {
		super.update();
		
		if(destroyingBlock) {
			destroyBlock();
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(animation.getImage(), GamePanel.width / 2 / GamePanel.SCALE - width / 2 - 7, 
				GamePanel.height / 2 / GamePanel.SCALE - height / 2, null);
		
		//OUTLINING THE TARGET BLOCK IF IN RADIUS
		if(isBlockInRadius(new Point(GamePanel.mouse.mouseConvertedX, GamePanel.mouse.mouseConvertedY), 2)) {
			Block block = Playstate.world.getBlock(GamePanel.mouse.mouseConvertedX, GamePanel.mouse.mouseConvertedY);
			g.setColor(new Color(1, 1, 1, 0.2F));
			g.fillRect((int)block.getX() - Playstate.camera.getCamX(), (int)block.getY() - Playstate.camera.getCamY(), Main.BLOCKSIZE, Main.BLOCKSIZE);
		}

	}
	
	//BLOCK DESTROYING
	private void destroyBlock() {
		if(isMouseOnScreen()) {
			if(isBlockInRadius(new Point(GamePanel.mouse.mouseConvertedX, GamePanel.mouse.mouseConvertedY), 2)) {
				Playstate.world.getBlock(GamePanel.mouse.mouseConvertedX, GamePanel.mouse.mouseConvertedY).destroyBlock();	
			}
		}
	}
	
	//CHECK IF BLOCK IS IN RADIUS
	public boolean isBlockInRadius(Point p, int radius) {
		int colstart = Playstate.world.getColTile((int)getCenterX()) - radius;
		int rowstart = Playstate.world.getRowTile((int)getCenterY()) - radius;
		for(int row = 0; row < 2*radius+1; row++) {
			for(int col = 0; col < 2*radius+1; col++) {
				int blockX = colstart+col;
				int blockY = rowstart+row;
				if(blockX >= 0 && blockY >= 0 && blockX < Playstate.world.getBlocksX() && blockY < Playstate.world.getBlocksY()) {
					if(Playstate.world.getBlocks()[blockY][blockX].getBox().contains(p)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	//CHECK IF MOUSE IS INSIDE WORLD
	private boolean isMouseOnScreen() {
		World world = Playstate.world;
		int mx = GamePanel.mouse.mouseConvertedX;
		int my = GamePanel.mouse.mouseConvertedY;
		if(mx >= 0 && my >= 0 && mx <= world.getBlocksX() * Main.BLOCKSIZE && my <= world.getBlocksY() * Main.BLOCKSIZE) {
			return true;
		} else return false;
	}
	
	public void keyPressed(KeyEvent e, int k) {
		switch(k) {
		case KeyEvent.VK_A: left = true; break;
		case KeyEvent.VK_D: right = true; break;
		case KeyEvent.VK_SPACE: {
			if(!falling && !jumping) jumping = true; 
			break;
		}
		}
	}
	
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			destroyingBlock = true;
		} 
		
		//PLACING BLOCKS
		else if(e.getButton() == MouseEvent.BUTTON3) {
			Item item = Playstate.inventory.getSelectedItem();
			if(item != null) {
				if(item.getType() instanceof Material && isMouseOnScreen() 
						&& isBlockInRadius(new Point(GamePanel.mouse.mouseConvertedX, GamePanel.mouse.mouseConvertedY), 2)) {
					System.out.println("hi");
					Material material = (Material) item.getType();
					Playstate.world.placeBlock(material, GamePanel.mouse.mouseConvertedX, GamePanel.mouse.mouseConvertedY);
				
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e, int k) {
		switch(k) {
		case KeyEvent.VK_A: left = false; break;
		case KeyEvent.VK_D: right = false; break;
		case KeyEvent.VK_SPACE: jumping = false; break;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		destroyingBlock = false;
	}
}
