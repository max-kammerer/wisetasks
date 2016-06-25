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

package ru.spb.ipo.client.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ru.spb.ipo.client.ui.common.InfoDialog;
import ru.spb.ipo.client.ui.utils.Actions;
import ru.spb.ipo.engine.utils.FileAccessUtil;
import ru.spb.ipo.engine.utils.SystemProperties;

public class MainMenu extends JMenuBar {

	private JMenu file = null;
	private JMenuItem close = null;
	private JMenuItem chooseTest = null;
	private JMenu help = null;
	private JMenuItem about = null;
    private JMenuItem result = null;
	/**
	 * This method initializes 
	 * 
	 */
	public MainMenu() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.add(getFile());
        this.add(getHelp());
			
	}

	/**
	 * This method initializes file	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFile() {
		if (file == null) {
			file = new JMenu();
			file.setText("Файл");
			file.add(getChooseTest());
			if (!SystemProperties.getBooleanFromString(SystemProperties.IS_APPLET)) {
				file.addSeparator();
				file.add(getClose());
			}
		}
		return file;
	}

	/**
	 * This method initializes close	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getClose() {
		if (close == null) {
			close = new JMenuItem();
			close.setText("Выход");
			close.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return close;
	}

	/**
	 * This method initializes chooseTest	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	protected JMenuItem getChooseTest() {
		if (chooseTest == null) {
			chooseTest = new JMenuItem();
			chooseTest.setText("Задачник...");
			chooseTest.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.getClient().getActions().chooseTest();
				}
			});
		}
		return chooseTest;
	}

	/**
	 * This method initializes help	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelp() {
		if (help == null) {
			help = new JMenu();
			help.setText("Справка");
			help.add(getAbout());
			//help.addSeparator();			
            //help.add(getResult());
        }
		return help;
	}

	/**
	 * This method initializes about	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAbout() {
		if (about == null) {
			about = new JMenuItem();
			about.setText("О программе...");
			about.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new InfoDialog(ClientUI.client);					
					//JOptionPane.showMessageDialog(ClientUI.client,"Клиент к системе *** v0.51\n Copyright (C)\t flown&co", "О программе...", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return about;
	}

	private JMenuItem getResult() {
		if (result == null) {
			result = new JMenuItem();
			result.setText("Результаты...");
			result.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new ResultDialog(ClientUI.client, null);
					//JOptionPane.showMessageDialog(ClientUI.client,"Клиент к системе *** v0.51\n Copyright (C)\t flown&co", "О программе...", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return result;
	}

}
