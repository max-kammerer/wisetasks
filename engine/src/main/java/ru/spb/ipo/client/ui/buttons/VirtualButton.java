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
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;


public class VirtualButton extends JButton implements PerformAction {

	public static final PerformAction PERFORMER = new BaseExecutor();
	public static final String DELETE = "delete";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String POPUP = "POPUP";
	public static final VirtualButton ONE = new VirtualButton("1", "1", 0, null); 
	public static final VirtualButton TWO = new VirtualButton("2", "2", 0, null);
	public static final VirtualButton THREE = new VirtualButton("3", "3", 0, null);
	public static final VirtualButton FOUR = new VirtualButton("4", "4", 0, null);	
	public static final VirtualButton FIVE = new VirtualButton("5", "5", 0, null);	
	public static final VirtualButton SIX = new VirtualButton("6", "6", 0, null);
	public static final VirtualButton SEVEN = new VirtualButton("7", "7", 0, null);
	public static final VirtualButton EIGHT = new VirtualButton("8", "8", 0, null);
	public static final VirtualButton NINE = new VirtualButton("9", "9", 0, null);
	public static final VirtualButton ZERO = new VirtualButton("0", "0", 0, null);
	public static final VirtualButton PLUS = new VirtualButton("+", "+", 0, null);
	public static final VirtualButton MINUS = new VirtualButton("-", "-", 0, null);
	public static final VirtualButton MULT = new VirtualButton("*", "*", 0, null);
	public static final VirtualButton DIV = new VirtualButton("/", "/", 0, null);
	public static final VirtualButton POWER = new VirtualButton("^", "^", "Степень", 0, null);
	public static final VirtualButton LEFT_BRACKET = new VirtualButton("(", "(", 0, null);
	public static final VirtualButton RIGHT_BRACKET = new VirtualButton(")", ")", 0, null);	
	public static final VirtualButton ANSWER_SEPARATOR = new VirtualButton(";", ";", "Разделитель решений (при наличии нескольких решений)", 0, null);
	public static final VirtualButton EMPTY_ANSWER = new VirtualButton("e", "empty", "Пустой ответ", 0, null);
	
	public static final VirtualButton FACTORIAL = new VirtualButton("!", "!", "Факториал", 0, null);
	public static final VirtualButton LAYOUT = new VirtualButton("A", "A( , )", "Количество размещений", 0, new BaseExecutor() {
																protected void updateParameters(JTextField field, ActionEvent event){
																	super.updateParameters(field, event);
																	position += 1;
																}	
											});
	public static final VirtualButton COMBINATION = new VirtualButton("C", "C( , )", "Количесво комбинаций", 0, new BaseExecutor() {
																			protected void updateParameters(JTextField field, ActionEvent event){
																				super.updateParameters(field, event);
																				position += 1;
																			}	
																	});
	public static final VirtualButton COMMA = new VirtualButton(",", ",", "Запятая", 0, null);
	public static final VirtualButton DEL = new VirtualButton("BS", DELETE, "BackSpace", 0, new BaseExecutor() {
													protected void updateParameters(JTextField field, ActionEvent event){
														if (position != 0) {
								            				newValue = oldValue.substring(0,position - 1) + oldValue.substring(position, oldValue.length());
								            				position -= 1;
							            				} 													
													}	
						});

	public static final VirtualButton LEFT_BUTTON = new VirtualButton("\u2190", LEFT, "Влево", 0, new BaseExecutor() {
																			protected void updateParameters(JTextField field, ActionEvent event){																			
																				position -= 1;
																			}	
																								});	
	public static final VirtualButton RIGHT_BUTTON = new VirtualButton("\u2192", RIGHT, "Вправо", 0, new BaseExecutor() {
																						protected void updateParameters(JTextField field, ActionEvent event){
																							//newValue = oldValue;
																							position += 1;
																						}
																				}	);
	
	public static final VirtualButton PARAMETERS = new VirtualButton("Параметры", RIGHT, "Параметры", 0, new BaseExecutorAdapter() {
		private JPopupMenu menu = new JPopupMenu();
		private BaseExecutor ex = new BaseExecutor();		
		protected void updateParameters(final JTextField field, ActionEvent event) {			
			menu.removeAll();
			VirtualButton button = (VirtualButton)event.getSource();
			List<String> parameters = (List)button.getClientProperty(POPUP);
			
			if (parameters == null || parameters.size() == 0){
				button.setEnabled(false);
			} else {
				ActionListener al = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String value = ((JMenuItem)e.getSource()).getText();
						int position = field.getCaretPosition();
						String oldValue = field.getText();
						String newValue = oldValue.substring(0,position) + value +oldValue.substring(position, oldValue.length());
						field.setText(newValue);
						field.setCaretPosition(position + value.length());
					}
					
				};

                for (String parameter: parameters) {
					JMenuItem item = new JMenuItem(parameter);
					item.addActionListener(al);
					menu.add(item);
				}				
				menu.show(button, 0, button.getHeight());
			}
		}
		
	});
	
	public static final VirtualButton EFUNCTIONS = new VirtualButton("Функции", RIGHT, "Функции", 0, new BaseExecutorAdapter() {
		
	});
	
	public static final VirtualButton CLEAR = new VirtualButton("Очистить", RIGHT, "Очистить", 0, new BaseExecutorAdapter() {
		protected void updateParameters(JTextField field, ActionEvent event) {
			field.setText("");
		}		
	});
	
	
	private String textKey;
	private String valueKey;
	private int type;
	
	
	private PerformAction myPerformer;
	
	public VirtualButton(String textKey, String valueKey, int type, PerformAction performer) {
		//super(textKey);
		
		init(textKey, valueKey, type, performer);
		//setToolTipText(textKey);
	}

	public VirtualButton(String textKey, String valueKey, String toolTip, int type, PerformAction performer) {
		init(textKey, valueKey, type, performer);
		setToolTipText(toolTip);
	}
	
	
	private void init(String textKey, String valueKey, int type, PerformAction performer) {
		
		setText(textKey);
		//setToolTipText(valueKey);
		int w = 40;
		int h = 26;
		//setPreferredSize(new Dimension(w, h));
		//setMinimumSize(new Dimension(w, h));
		//setMaximumSize(new Dimension(w, h));
		this.textKey = textKey;
		this.valueKey = valueKey;
		this.type = type;
		//setMnemonic(textKey.charAt(0));		
		if (performer == null)
			myPerformer = PERFORMER;
		else myPerformer = performer;
	}
	
	public String getValueKey() {
		return valueKey;
	}

	public void setValueKey(String valueKey) {
		this.valueKey = valueKey;
	}

	public String getTextKey() {
		return textKey;
	}

	public void setTextKey(String textKey) {
		this.textKey = textKey;
	}

	public void perform(JTextField field, ActionEvent event) {		
 		myPerformer.perform(field, event);
	}

		
}

