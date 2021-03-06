/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



public class GameStateManager {

	public static final int PLAYSTATE = 0;
	
	private State[] states;
	private int state;
	
	public GameStateManager(int state) {
		this.state = state;
		states = new State[1];
		
		//init states
		states[0] = new Playstate(this);
		setState(PLAYSTATE);
	}
	
	public void update() {
		states[state].update();
	}
	
	public void render(Graphics2D g) {
		states[state].render(g);
	}
	
	public void keyPressed(KeyEvent e, int k) {
		states[state].keyPressed(e, k);
	}

	public void keyReleased(KeyEvent e, int k) {
		states[state].keyReleased(e, k);
	}
	
	public void mousePressed(MouseEvent e) {
		states[state].mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		states[state].mouseReleased(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		states[state].mouseMoved(e);
	}
	
	public void setState(int state) {
		this.state = state;
	}
}
