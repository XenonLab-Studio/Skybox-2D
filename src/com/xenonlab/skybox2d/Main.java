/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int BLOCKSIZE = 16;
	public static Spritesheet spritesheet = new Spritesheet(ImageLoader.loadImage("img/tilesheet.png"), 8, BLOCKSIZE, BLOCKSIZE);
	
	// Custom mouse cursor
	public void CustomCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("img/cursor.png");
		Point point = new Point(0, 0);
		Cursor cursor = toolkit.createCustomCursor(img, point, "cursor");
		
		setCursor(cursor);
	}
	
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
		CustomCursor(); // Mouse cursor
	}
	
	public static void main(String args[]) {
		new Main();
	}
	
	public static int randInt(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}

