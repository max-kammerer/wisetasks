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

package ru.spb.ipo.client.ui.buttons;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class VirtualKeyboard extends JPanel implements Cloneable{

	private static VirtualKeyboard DIGIT_KEYBOARD_1_3 = new VirtualKeyboard(new FlowLayout(FlowLayout.CENTER, 5, 0), new VirtualButton [] {VirtualButton.ONE, VirtualButton.TWO, VirtualButton.THREE});
	private static VirtualKeyboard DIGIT_KEYBOARD_4_6 = new VirtualKeyboard(new FlowLayout(FlowLayout.CENTER, 5, 0), new VirtualButton [] {VirtualButton.FOUR, VirtualButton.FIVE, VirtualButton.SIX});
	private static VirtualKeyboard DIGIT_KEYBOARD_7_9 = new VirtualKeyboard(new FlowLayout(FlowLayout.CENTER, 5, 0), new VirtualButton [] {VirtualButton.SEVEN, VirtualButton.EIGHT, VirtualButton.NINE});
	//public static VirtualKeyboard DIGIT_KEYBOARD_0 = new VirtualKeyboard(new FlowLayout(), new VirtualButton [] {null, VirtualButton.ZERO, null});
	
	//private static VirtualKeyboard DIGIT_KEYBOARD_0 = new VirtualKeyboard(new FlowLayout(FlowLayout.CENTER, 5, 0), new VirtualButton [] {VirtualButton.LEFT_BRACKET, VirtualButton.ZERO, VirtualButton.RIGHT_BRACKET});	
	private static VirtualKeyboard DIGIT_KEYBOARD_0 = new VirtualKeyboard(new GridLayout(4, 3, 1, 1), new VirtualButton [] {VirtualButton.ONE, VirtualButton.TWO, VirtualButton.THREE, VirtualButton.FOUR, VirtualButton.FIVE, VirtualButton.SIX, VirtualButton.SEVEN, VirtualButton.EIGHT, VirtualButton.NINE, VirtualButton.LEFT_BRACKET, VirtualButton.ZERO, VirtualButton.RIGHT_BRACKET});
	
	//public static VirtualKeyboard BASIC_OPERATIONS = new VirtualKeyboard(Box.createVerticalBox(), new VirtualButton [] {VirtualButton.PLUS, VirtualButton.MINUS, VirtualButton.MULT, VirtualButton.DIV});
	public static VirtualKeyboard BASIC_OPERATIONS = new VirtualKeyboard(new GridLayout(0, 2, 1, 1), new VirtualButton [] {VirtualButton.PLUS, VirtualButton.MINUS, VirtualButton.MULT, VirtualButton.DIV, VirtualButton.FACTORIAL, VirtualButton.POWER, VirtualButton.LEFT_BUTTON, VirtualButton.RIGHT_BUTTON});
	//public static VirtualKeyboard EXTENDS_OPERATIONS = new VirtualKeyboard(Box.createVerticalBox(), new VirtualButton [] {VirtualButton.FACTORIAL, VirtualButton.LAYOUT, VirtualButton.COMBINATION, VirtualButton.DEL});
	public static VirtualKeyboard EXTENDS_OPERATIONS = new VirtualKeyboard(new GridLayout(4, 1, 1, 1), new VirtualButton [] {VirtualButton.COMBINATION, VirtualButton.LAYOUT, VirtualButton.COMMA ,VirtualButton.DEL });
	public static VirtualKeyboard EFUNCTIONS = new VirtualKeyboard(new GridLayout(4, 1, 1, 1), new JComponent [] {VirtualButton.PARAMETERS, VirtualButton.EFUNCTIONS, new JPanel(), VirtualButton.CLEAR});
	
	
	
	//public static  VirtualKeyboard DIGIT_KEYBOARD = new VirtualKeyboard(Box.createVerticalBox(), new VirtualKeyboard [] {VirtualKeyboard.DIGIT_KEYBOARD_1_3, VirtualKeyboard.DIGIT_KEYBOARD_4_6, VirtualKeyboard.DIGIT_KEYBOARD_7_9, VirtualKeyboard.DIGIT_KEYBOARD_0});
	public static  VirtualKeyboard DIGIT_OPERATION_KEYBOARD = new VirtualKeyboard(Box.createHorizontalBox(), new Component [] {VirtualKeyboard.DIGIT_KEYBOARD_0, Box.createHorizontalStrut(10), VirtualKeyboard.BASIC_OPERATIONS, Box.createHorizontalStrut(10), VirtualKeyboard.EXTENDS_OPERATIONS, Box.createHorizontalStrut(10), EFUNCTIONS});

	public static VirtualKeyboard MULTI_ANSWERS = new VirtualKeyboard(new GridLayout(1, 2, 1, 1), new VirtualButton [] {VirtualButton.ANSWER_SEPARATOR, VirtualButton.EMPTY_ANSWER});
	public static VirtualKeyboard EFUNCTIONS_2 = new VirtualKeyboard(new GridLayout(4, 1, 1, 1), new JComponent [] {VirtualButton.PARAMETERS, VirtualButton.EFUNCTIONS, MULTI_ANSWERS, VirtualButton.CLEAR});
	public static  VirtualKeyboard DIGIT_OPERATION_KEYBOARD_2 = new VirtualKeyboard(Box.createHorizontalBox(), new Component [] {VirtualKeyboard.DIGIT_KEYBOARD_0, Box.createHorizontalStrut(10), VirtualKeyboard.BASIC_OPERATIONS, Box.createHorizontalStrut(10), VirtualKeyboard.EXTENDS_OPERATIONS, Box.createHorizontalStrut(10), EFUNCTIONS_2});
	
	
	//private LayoutManager layout;
	private Component [] components;
	
	private ArrayList buttons;
	
	private Box myBox;
	
	public VirtualKeyboard(LayoutManager layout, JComponent [] comps) {
		super(layout);
		//this.layout = layout;
		components = comps;  
		for (int i = 0; i < comps.length; i++) {
			super.add(comps[i]);
		}
	}
	
	public VirtualKeyboard(Box box, Component [] comps) {
		//super();
		//this.layout = layout;
		myBox = box;
		this.add(box);
		components = comps;
		for (int i = 0; i < comps.length; i++) {
			box.add(comps[i]);
		}
	}
	
	public void addMyActionListener(ActionListener listener) {	
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof VirtualButton) {
				((VirtualButton)components[i]).addActionListener(listener);
			} else if (components[i] instanceof VirtualKeyboard) {
				((VirtualKeyboard)components[i]).addMyActionListener(listener);
			}
		}
	}
	

	public void removeMyActionListener(ActionListener listener) {
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof VirtualButton) {
				((VirtualButton)components[i]).removeActionListener(listener);
			} else if (components[i] instanceof VirtualKeyboard) {
				((VirtualKeyboard)components[i]).removeMyActionListener(listener);
			}
		}
	}
	
	//TODO rewrite with MAP
	public VirtualButton getButton(String textPrefix) {
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof VirtualButton) {
				if (((VirtualButton)components[i]).getTextKey().startsWith(textPrefix))
					return (VirtualButton)components[i];
			} else if (components[i] instanceof VirtualKeyboard) {
				((VirtualKeyboard)components[i]).getButton(textPrefix);
			}
		}
		return null;
	}
	
//	public static VirtualKeyboard setParameterButtons(VirtualKeyboard keyboard, String [] additionalButtons) {
//		if(additionalButtons.length == 0) return keyboard;
//		VirtualButton [] vbuttons = new VirtualButton[additionalButtons.length]; 
//		for (int i = 0; i < additionalButtons.length; i++) {
//			vbuttons[i] = new  VirtualButton(additionalButtons[i],additionalButtons[i], "Parameter", 0, null);
//		}
//		VirtualKeyboard newPiece =  null; 
//		newPiece = new VirtualKeyboard(new GridLayout(4, 0, 1, 1), vbuttons);
//		newPiece = new VirtualKeyboard(Box.createHorizontalBox(), new VirtualKeyboard[] {newPiece});
//		return new VirtualKeyboard(Box.createHorizontalBox(), new VirtualKeyboard[] {keyboard, newPiece});
//	}

	public static void setParameters(List strs) {
		VirtualButton.EFUNCTIONS.setEnabled(false);
		if (strs == null || strs.size() == 0) {
			VirtualButton.PARAMETERS.setEnabled(false);
		} else {
			VirtualButton.PARAMETERS.putClientProperty(VirtualButton.POPUP, strs);
			VirtualButton.PARAMETERS.setEnabled(true);
		}
	}
	
	public static void init() {
		VirtualButton.EFUNCTIONS.setEnabled(false);
		VirtualButton.PARAMETERS.setEnabled(false);
	}
	
}
 