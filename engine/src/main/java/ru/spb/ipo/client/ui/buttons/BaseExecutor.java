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

import java.awt.event.ActionEvent;

import javax.swing.JTextField;


public class BaseExecutor implements PerformAction {

	protected String buttonValue;
	protected String newValue;
	protected String oldValue;
	protected int position;
	
	
	public void perform(JTextField field, ActionEvent event) {
		preExecute(field, event);
		updateParameters(field, event);
		postExecute(field, event);
	}
	
	protected void preExecute(JTextField field, ActionEvent event){
		buttonValue = ((VirtualButton)event.getSource()).getValueKey();		
		oldValue = field.getText();
		newValue = oldValue;
		position = field.getCaretPosition();
	}

	protected void updateParameters(JTextField field, ActionEvent event){ 
		newValue = oldValue.substring(0,position) + buttonValue +oldValue.substring(position, oldValue.length());
		position += 1;
	}
	
	protected void postExecute (JTextField field, ActionEvent event){
		if (newValue == null) newValue = "";
		field.setText(newValue);
		if ((position > -1) &&(position < newValue.length()))
		field.setCaretPosition(position);
	}

}
