/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;


public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int BLOCKSIZE = 16;
	public static Spritesheet spritesheet = new Spritesheet(ImageLoader.loadImage("img/tilesheet.png"), 8, BLOCKSIZE, BLOCKSIZE);

	
	public Main() {
		super("SKYBOX 2D");
		GamePanel panel = new GamePanel();
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		pack();
		setResizable(false);
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

