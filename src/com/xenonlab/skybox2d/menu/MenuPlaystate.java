/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d.menu;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class MenuPlaystate implements ActionListener {

	private Button button1;
	private Button button2;
	
	// Constructor
	public MenuPlaystate() {
		button1 = new Button(this, "Start", 250, 100, 100, 40);
		button2 = new Button(this, "Exit", 250, 200, 100, 40);
	}
	
	// Logic
	public void update() {
		
	}
	
	// Rendering
	public void render(Graphics2D g) {
		button1.render(g);
		button2.render(g);
	}
	
	// Mouse input handling
	public void mousePressed(MouseEvent e) {
		button1.mousePressed(e);
		button2.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		button1.mouseReleased(e);
		button2.mouseReleased(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			System.out.println("Start!");
		}
		
		if(e.getSource() == button2) {
			System.exit(0); // Application close
		}
	}
}
