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
	
	public Playstate(GameStateManager gsm) {
		super(gsm);
		world = new World("worlds/world2.txt");
		player = new Player(40, 40, 8, 31, 0.75f); // Player spawn
		camera = new Camera(player);
		inventory = new Inventory();
		inventory.addItem(new Item(Material.GRASS, Material.GRASS.getTexture(), 64, true));
		inventory.addItem(new Item(Material.SAND, Material.SAND.getTexture(), 64, true));
		inventory.addItem(new Item(Material.OAK_LEAVES, Material.OAK_LEAVES.getTexture(), 64, true));
		inventory.addItem(new Item(Material.OAK_TRUNK, Material.OAK_TRUNK.getTexture(), 64, true));
		inventory.addItem(new Item(Material.OAK_SAPLING, Material.OAK_SAPLING.getTexture(), 64, true));
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
