/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Playstate extends State {

	public static final int SCALE = 4;
	public static int mouseX;
	public static int mouseY;
	public static World world;
	public static Player player;
	public static Camera camera;
	public static Inventory inventory;
	
	private Daycicle dc;
	
	public Playstate(GameStateManager gsm) {
		super(gsm);
		world = new World("worlds/world.txt");
		player = new Player(40, 40, 8, 31, 0.75f); // Spawn, ..., ..., ..., speed
		camera = new Camera(player);
		dc = new Daycicle();
		
		inventory = new Inventory();
		inventory.addItem(new Item(Material.STONE, Material.STONE.getTexture(), 64, true));
		inventory.addItem(new Item(Material.DIRT, Material.DIRT.getTexture(), 64, true));
		inventory.addItem(new Item(Material.DIRT_GRASS, Material.DIRT_GRASS.getTexture(), 64, true));
		inventory.addItem(new Item(Material.SAND, Material.SAND.getTexture(), 64, true));
		inventory.addItem(new Item(Material.PINE_TRUNK, Material.PINE_TRUNK.getTexture(), 64, true));
		inventory.addItem(new Item(Material.PINE_LEAVES, Material.PINE_LEAVES.getTexture(), 64, true));
		inventory.addItem(new Item(Material.DEFAULT_GRASS, Material.DEFAULT_GRASS.getTexture(), 64, true));
	}

	@Override
	public void update() {
		camera.update();
		player.update();
		inventory.update();
	}

	@Override
	public void render(Graphics2D g) {
		g.clearRect(0, 0, GamePanel.width / GamePanel.SCALE, GamePanel.height / GamePanel.SCALE);
		world.render(g);
		player.render(g);
		dc.render(g);
		inventory.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e, int k) {
		player.keyPressed(e, k);
	}

	@Override
	public void keyReleased(KeyEvent e, int k) {
		player.keyReleased(e, k);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		player.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		player.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
