package com.xenonlab.skybox2d;
import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;


public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int BLOCKSIZE = 16;
	public static Spritesheet spritesheet = new Spritesheet(ImageLoader.loadImage("img/terrain.png"), 8, BLOCKSIZE, BLOCKSIZE);

	
	public Main() {
		super("Skybox 2D");
		GamePanel panel = new GamePanel();
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		pack();
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String args[]) {
		new Main();
	}
	
	public static int randInt(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}
