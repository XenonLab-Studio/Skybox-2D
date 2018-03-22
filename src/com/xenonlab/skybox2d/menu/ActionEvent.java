/*
 * Copyright (C) 2018 Stefano Peris
 * eMail: <xenon77.dev@gmail.com>
 * Github: https://github.com/XenonCoder
 */

package com.xenonlab.skybox2d.menu;

public class ActionEvent {

	private Object source;
	
	public ActionEvent(Object source) {
		
		this.source = source;
	}
	
	public Object getSource() {
		return source;
	}
}
