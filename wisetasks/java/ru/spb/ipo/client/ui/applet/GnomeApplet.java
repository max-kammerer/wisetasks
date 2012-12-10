/*
 * This file is part of Wisetasks
 *
 * Copyright (C) 2006-2008, 2012  Michael Bogdanov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.spb.ipo.client.ui.applet;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JOptionPane;


import ru.spb.ipo.client.ui.ClientUI;
import ru.spb.ipo.client.ui.utils.Actions;
import ru.spb.ipo.engine.utils.FileAccessUtil;
import ru.spb.ipo.engine.utils.SystemProperties;

public class GnomeApplet extends JApplet {

	private ClientUI clientUI;
	
	private boolean isStarted = false;
	
	public void init() {
		System.err.println(getCodeBase());
		setLayout(new BorderLayout());				
		try {
			String remote = this.getParameter("remote");			
			boolean useRemoteProxy = new Boolean(remote);
			if (useRemoteProxy) {				
				SystemProperties.put(SystemProperties.NEED_LOGIN, "true");
				SystemProperties.put(SystemProperties.PROXY_CLASS, "ru.spb.ipo.engine.rmi.ServerAccessor");
				SystemProperties.put(SystemProperties.IS_APPLET, "true");
			} else {
				FileAccessUtil.startApplet(this);
			}
			clientUI = new ClientUI(ClientUI.getServer(null));
		} catch (InstantiationException e) {
			Actions.showMessage("Ошибка при создании прокси для сервера:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			Actions.close(1, this);			
		} catch (IllegalAccessException e) {
			Actions.showMessage("Ошибка при создании прокси для сервера:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			Actions.close(1, this);
		} catch (ClassNotFoundException e) {
			Actions.showMessage("Ошибка при создании прокси для сервера:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			Actions.close(1, this);
		} catch (Exception e) {
			Actions.showMessage("Ошибка при инициализации аплета:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			Actions.close(1, this);
		}
		this.add(clientUI.getJMenuBar(), BorderLayout.NORTH);
		this.add(clientUI.getContentPane(), BorderLayout.CENTER);
	}
	
	public void start() {		
		try {
			if (!clientUI.getActions().login()) {
				Actions.close(0, this);				
			}
		} catch (Exception e) {			
			Actions.showMessage("Ошибка входа в систему:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			Actions.close(1, this);
		} 
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (!isStarted) {
			isStarted = true;
			clientUI.openTests();			
		}
	}

	public static void main (String [] str) {
		new GnomeApplet();
	}
}
