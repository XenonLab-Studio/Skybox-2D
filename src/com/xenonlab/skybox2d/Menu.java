/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Menu extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public boolean started = false;
	
	public Menu() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(Box.createVerticalStrut(280));
		
		JButton button = new JButton("START");
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				start();
				
			}
		});
		
		add(button);
		
		add(Box.createVerticalGlue());
		
		try {
			
			URL url = Menu.class.getResource("menu.ogg");
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			Clip music = AudioSystem.getClip();
			music.open(audio);
			music.loop(-1);
			
			
		} catch (Exception ex) {}
	}
	
	public void start() {
		
		removeAll();
		started = true;
		repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponents(g);
		if(!started)
			g.drawImage(new ImageIcon(Menu.class.getResource("img/menu_bg.png")).getImage(), 0, 0, 800, 600, this);
		else
			setBackground(Color.BLUE);
	}
}
