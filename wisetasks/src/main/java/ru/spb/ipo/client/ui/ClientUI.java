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

import ru.spb.ipo.client.ui.buttons.VirtualButton;
import ru.spb.ipo.client.ui.buttons.VirtualKeyboard;
import ru.spb.ipo.client.ui.utils.Actions;
import ru.spb.ipo.client.ui.utils.Logger;
import ru.spb.ipo.engine.rmi.ProblemProxy;
import ru.spb.ipo.engine.rmi.Server;
import ru.spb.ipo.engine.rmi.ContestProblemAccessor;
import ru.spb.ipo.engine.utils.SystemProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

public class ClientUI extends JFrame {
			
	private JPanel rootPanel = null;
	private JPanel taskSplit = null;
	private JPanel logPanel = null;
	private JPanel tasksList = null;
	private JPanel taskPanel = null;
	private JList tasks = null;
	private JTextArea logs = null;
	private JMenuBar mainMenu = null;

	public static ClientUI client;
	private JScrollPane jScrollPane = null;
	private JScrollPane jScrollPane1 = null;
	private JPanel descriptionPanel = null;
	private JScrollPane jScrollPane2 = null;
	private JTextPane textDescription = null;
	private JPanel inputPanel = null;
	private JPanel keyboard = null;
	private JPanel textPanel = null;
	private JTextField answerText = null;
	private JButton answerButton = null;

	/**
	 * This method initializes
	 *
	 */
	
	private Actions actions;
	
	public ClientUI(Server server) {
		super();		
		initialize();
		client = this;
		showAtCenter();		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String login = "default";
		String password = "default";
		if (SystemProperties.getBooleanFromString("needLogin")) {
			
		}
		
		actions = new Actions(server);
		
        Logger.tfield = getLogs();        
    }
	
	protected void login(String name, String password) {
		
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new java.awt.Dimension(736,600));
        this.setJMenuBar(getMainMenu());
        this.setContentPane(getRootPanel());
        this.setTitle("WiseTasks");
	}

	/**
	 * This method initializes rootPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	protected JPanel getRootPanel() {
		if (rootPanel == null) {
			rootPanel = new JPanel();
			rootPanel.setLayout(new BorderLayout());
			rootPanel.add(getTaskSplit(), java.awt.BorderLayout.CENTER);
			rootPanel.add(getLogPanel(), java.awt.BorderLayout.SOUTH);
		}
		return rootPanel;
	}

	/**
	 * This method initializes taskSplit	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTaskSplit() {
		if (taskSplit == null) {
			taskSplit = new JPanel();
			taskSplit.setLayout(new BorderLayout());
			taskSplit.add(getTasksList(), java.awt.BorderLayout.WEST);
			taskSplit.add(getTaskPanel(), java.awt.BorderLayout.CENTER);
		}
		return taskSplit;
	}

	/**
	 * This method initializes logPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLogPanel() {
		if (logPanel == null) {
			logPanel = new JPanel();
			logPanel.setLayout(new BorderLayout());
			logPanel.setPreferredSize(new java.awt.Dimension(0,100));
			logPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Системные сообщения", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
			logPanel.add(getJScrollPane1(), java.awt.BorderLayout.CENTER);
		}
		return logPanel;
	}

	/**
	 * This method initializes tasksList	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	protected JPanel getTasksList() {
		if (tasksList == null) {
			tasksList = new JPanel();
			tasksList.setLayout(new BorderLayout());
			tasksList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Список задач", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			tasksList.setPreferredSize(new java.awt.Dimension(300,157));
			tasksList.setMaximumSize(new java.awt.Dimension(300,157));
			tasksList.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return tasksList;
	}

	/**
	 * This method initializes taskPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTaskPanel() {
		if (taskPanel == null) {
			taskPanel = new JPanel();
			taskPanel.setLayout(new BorderLayout());
			taskPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Задача", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			taskPanel.add(getDescriptionPanel(), java.awt.BorderLayout.CENTER);
			taskPanel.add(getInputPanel(), java.awt.BorderLayout.SOUTH);
		}
		return taskPanel;
	}

	/**
	 * This method initializes tasks	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getTasks() {
		if (tasks == null) {
			tasks = new JList();
			tasks.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {					
					if (e.getSource() == getTasks()) {
			            if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
			                if (getTasks().getSelectedIndex() != -1) {
			                    actions.taskSelected(((ProblemProxy)getTasks().getSelectedValue()).getId());
			                }
			            }
			        }
			    }
				
			});
		}
		return tasks;
	}

	/**
	 * This method initializes logs	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getLogs() {
		if (logs == null) {
			logs = new JTextArea();
			logs.setRows(0);
			logs.setEditable(false);
		}
		return logs;
	}

	/**
	 * This method initializes mainMenu	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMainMenu() {
		if (mainMenu == null) {
			mainMenu = new MainMenu();
		}
		return mainMenu;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTasks());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getLogs());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes descriptionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDescriptionPanel() {
		if (descriptionPanel == null) {
			descriptionPanel = new JPanel();
			descriptionPanel.setLayout(new BorderLayout());
			descriptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Условие задачи", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			descriptionPanel.add(getJScrollPane2(), java.awt.BorderLayout.CENTER);
		}
		return descriptionPanel;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getTextDescription());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes textDescription	
	 * 	
	 * @return javax.swing.JTextPane
	 */
	public JTextPane getTextDescription() {
		if (textDescription == null) {
			textDescription = new JTextPane();
			textDescription.setEditable(false);
//			textDescription.setLineWrap(true);
//			textDescription.setWrapStyleWord(true);
			
		}
		return textDescription;
	}

	/**
	 * This method initializes inputPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInputPanel() {
		if (inputPanel == null) {
			inputPanel = new JPanel();
			inputPanel.setLayout(new BorderLayout());
			inputPanel.setPreferredSize(new java.awt.Dimension(10,200));
			inputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Панель ввода ответа", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
			inputPanel.add(getKeyboard(), java.awt.BorderLayout.CENTER);
			inputPanel.add(getTextPanel(), java.awt.BorderLayout.NORTH);
		}
		return inputPanel;
	}

	/**
	 * This method initializes keyboard	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getKeyboard() {
		if (keyboard == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			keyboard = new JPanel(); 
			keyboard.setLayout(flowLayout);
			keyboard.add(VirtualKeyboard.DIGIT_OPERATION_KEYBOARD_2);
			VirtualKeyboard.DIGIT_OPERATION_KEYBOARD_2.addMyActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Object source = e.getSource();
	        		if (source instanceof VirtualButton) {
	        			((VirtualButton)source).perform(getAnswerText(), e);
	        			getAnswerText().getCaret().setVisible(true);	        			
	        		}
					
				}
				
			});
		}
		return keyboard;
	}

	/**
	 * This method initializes textPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTextPanel() {
		if (textPanel == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(5);
			textPanel = new JPanel();
			textPanel.setLayout(borderLayout);
			textPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,5,5,5));
			textPanel.add(getAnswerText(), java.awt.BorderLayout.CENTER);
			textPanel.add(getAnswerButton(), java.awt.BorderLayout.EAST);
		}
		return textPanel;
	}

	/**
	 * This method initializes answerText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getAnswerText() {
		if (answerText == null) {
			answerText = new JTextField();
		}
		return answerText;
	}

	/**
	 * This method initializes answerButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getAnswerButton() {
		if (answerButton == null) {
			answerButton = new JButton();
			answerButton.setText("Ответить");
			answerButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					actions.answer(getAnswerText().getText());
				}
			});
		}
		return answerButton;
	}

	public static void main (String [] args) {

		String serverClass = null;

		if (args != null && args.length != 0) {
			serverClass = args[0];
			SystemProperties.put(SystemProperties.NEED_LOGIN, "true");
		}
		ClientUI client = null;
		try {
			client = new ClientUI(getServer(serverClass));
		} catch (Exception e) {
			proxyError(e);
			System.exit(1);
		}
		try {
			if(!client.getActions().login()) {
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Actions.showMessage("Ошибка входа в систему:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		client.setVisible(true);
		client.openTests();
	}
	
	public static Server getServer(String serverClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (serverClass == null) {
			serverClass = SystemProperties.getString(SystemProperties.PROXY_CLASS);
			if (serverClass == null) {
				serverClass = "ru.spb.ipo.engine.rmi.ServerImpl";
			}
		}
		return (Server)Class.forName(serverClass).newInstance();		
	}

    public static Server getServerWithAccessor(String serverClass, ContestProblemAccessor accessor) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
		if (serverClass == null) {
			serverClass = SystemProperties.getString(SystemProperties.PROXY_CLASS);
			if (serverClass == null) {
				serverClass = "ru.spb.ipo.engine.rmi.ServerImpl";
			}
		}
		return (Server)Class.forName(serverClass).getConstructor(ContestProblemAccessor.class).newInstance(accessor);
	}

    public void openTests() {
		((MainMenu)client.getMainMenu()).getChooseTest().doClick();
	}
	
	private void showAtCenter() {
        setLocationRelativeTo(null);
	}

	private JDialog process;
	
	private JProgressBar progress;
	
	public void setProcessed(double finished) {
		if (process != null) {
			progress.setValue((int) (100 * finished));
		}
	}
	
	public void startProcessedDialog() {		
		process = new JDialog(this,"Проверка..." , true);
		int height = 55;
		if (SystemProperties.getBooleanFromString(SystemProperties.IS_APPLET)) {
			height = 80;			
		}
		process.setSize(new Dimension(250, height));
		
		progress = new JProgressBar(0, 100);
		progress.setStringPainted(true);
		process.getContentPane().add(progress, BorderLayout.CENTER);
		process.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		process.setLocationRelativeTo(this);
		process.setVisible(true);		
	}
	
	public void stopProcessedDialog() {
		if (process != null) {			
			process.dispose();
			process = null;
		}
	}
	
	public static JComponent getMainPanel() {
		return (JComponent)client.getContentPane();
	}
	
	public static void proxyError(Exception e) {
		e.printStackTrace();
		Actions.showMessage("Ошибка соединения:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}

	public Actions getActions() {
		return actions;
	}	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
