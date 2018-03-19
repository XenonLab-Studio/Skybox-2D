package com.xenonlab.skybox2d;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class World {
	
	private int blocksX;
	private int blocksY;
	private Block[][] blocks;
	
	public World(String filepath) {
		loadWorld(filepath);
	}
	
	public void render(Graphics2D g) {
		Player player = Playstate.player;
		int startX = (int)player.getCenterX() - GamePanel.width / GamePanel.SCALE / 2;
		int startY = (int)player.getCenterY() - GamePanel.height / GamePanel.SCALE / 2;
		int endX = (int)player.getCenterX() + GamePanel.width / GamePanel.SCALE / 2 + Main.BLOCKSIZE;
		int endY = (int)player.getCenterY() + GamePanel.height / GamePanel.SCALE / 2 + Main.BLOCKSIZE;
		
		for(int row = startY; row <= endY; row+=Main.BLOCKSIZE) {
			for(int col = startX; col < endX; col+=Main.BLOCKSIZE) {
				int blockX = getColTile(col);
				int blockY = getRowTile(row);
				if(blockX >= 0 && blockY >= 0 && blockX < this.blocksX && blockY < this.blocksY) {
					blocks[blockY][blockX].update();
					blocks[blockY][blockX].render(g);
				}
			}
		}
	}
	
	public boolean placeBlock(Material material, int x, int y) {
		Block block = getBlock(x, y);
		if(block.getMaterial().equals(Material.AIR)) {
			block.setMaterial(material);
			return true;
		} else return false;
	}
	
	
	public Block getBlock(int x, int y) {
		int blockx = x / Main.BLOCKSIZE;
		int blocky = y / Main.BLOCKSIZE;
		return blocks[blocky][blockx];
	}
	
	private void loadWorld(String filepath) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
			blocksX = Integer.parseInt(reader.readLine());
			blocksY = Integer.parseInt(reader.readLine());
			blocks = new Block[blocksY][blocksX];
			
			for(int row = 0; row < blocksY; row++) {
				String line = reader.readLine();
				String tokens[] = line.split(" ");
				for(int col = 0; col < blocksX; col++) {
					int id = Integer.parseInt(tokens[col]);
					Material material = Material.values()[id];
					blocks[row][col] = new Block(material, col * Main.BLOCKSIZE, row * Main.BLOCKSIZE, Main.BLOCKSIZE, Main.BLOCKSIZE);
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getColTile(int x) {
		return x / Main.BLOCKSIZE;
	}
	
	public int getRowTile(int y) {
		return y / Main.BLOCKSIZE;
	}
	
	public Block[][] getBlocks() {
		return blocks;
	}
	
	public int getBlocksX() {
		return blocksX;
	}
	
	public int getBlocksY() {
		return blocksY;
	}
}
