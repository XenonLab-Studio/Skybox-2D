/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Button {

	// FIELDS
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean enabled;
	private boolean pressed;
	
	private String text;
	private final Font font = new Font("Verdana", Font.PLAIN, 14);
	
	private ActionListener listener;
	
	// CONSTRUCTOR
	public Button(ActionListener listener, String text, int x, int y, int width, int height) {
		this.listener = listener;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		enabled = true;
	}
	
	public void render(Graphics2D g) {
		if(pressed) {
			g.setColor(Color.gray); // pressed
		} else {
			g.setColor(Color.white); // released
		}
		
		if(enabled) {
			g.fillRect(x, y, width, height);
			g.setFont(font);
			g.setColor(Color.BLACK); // text button color
			
			int stringWidth = g.getFontMetrics().stringWidth(text);
			g.drawString(text, x + width / 2 - stringWidth / 2, y + height / 2);
		}
	}
	
	private boolean isPressed(int x, int y) {
		return x >= this.x && x <= this.x + width
				&& y >= this.y && y <= this.y + height;
	}
	
	public void mousePressed(MouseEvent e) {
		if(isPressed(e.getX(), e.getY())) {
			pressed = true;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(pressed = enabled) {
			listener.actionPerformed(new ActionEvent(this));
			pressed = false;
		}
	}
}
