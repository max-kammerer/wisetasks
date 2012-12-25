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

package ru.spb.ipo.client.ui.utils;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import ru.spb.ipo.client.ui.ClientUI;
import ru.spb.ipo.client.ui.ExceptionHandler;
import ru.spb.ipo.client.ui.TestsDialog;
import ru.spb.ipo.client.ui.buttons.VirtualKeyboard;
import ru.spb.ipo.engine.exception.AuthentificationException;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.UserAnswerParseException;
import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.rmi.ProblemProxy;
import ru.spb.ipo.engine.rmi.Server;
import ru.spb.ipo.engine.rmi.UserChoice;
import ru.spb.ipo.engine.task.ClientTask;
import ru.spb.ipo.engine.utils.SystemProperties;

public class Actions {

	private UserChoice currentChoise;
	private Server engine;
	private ClientTask currentTask;
	public static boolean closed = false;
		
	public Actions(Server engine) {
		connect(engine);
	}
	
	public void chooseTest(){
		if (closed) {
			return;
		}
        try {
            new TestsDialog(getClient(), engine.getContestList()).setVisible(true);
        } catch (TaskDeserializationException e) {
            showMessage("Ошибка при попытке чтения списка задачников:\n " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        } catch (SystemException e) {
			 showMessage("Ошибка при попытке чтения списка задачников:\n " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
    }
	
	public static ClientUI getClient() {
		return ClientUI.client;
	}
	
	
	public void testSelected(long testNumber) {
		clearTask();
		currentChoise.clear();
		currentTask = null;
		ProblemProxy[] problems;
		try {
			currentChoise = engine.getUC(currentChoise, testNumber);
			problems = engine.getProblemList(currentChoise);
		} catch (TaskDeserializationException e) {
			e.printStackTrace();
			showMessage("Ошибка при открытии задачника:\n " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			return;
		} catch (SystemException e) {
			showMessage("Ошибка при открытии задачника:\n " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			return;
		}
		DefaultListModel model = new DefaultListModel();
		for (int j = 0; j < problems.length; j++) {
			model.addElement(problems[j]);
        }
		getClient().getTasks().setModel(model);
		Logger.log("Выбран новый задачник: "  + currentChoise.getContestName());
	}
	
	public void taskSelected(long taskNumber) {
		try {			
			currentChoise.setProblemId(taskNumber);
			currentTask = engine.getProblem(currentChoise);
			getClient().getTextDescription().setText(currentTask.getDescription().replaceAll("\\s+", " ").trim());
			List<Icon> icons = currentTask.getImages();
			if (icons != null && !icons.isEmpty()) {
				JTextPane pane = getClient().getTextDescription();
				pane.setText(pane.getText() + "\n\nКартинка к задаче:\n");
                for (Icon icon: icons) {
					getClient().getTextDescription().insertIcon(icon);
				}				
			}
			getClient().getAnswerText().setText("");
			VirtualKeyboard.setParameters(currentTask.getParameterButtons());
			Logger.log("Выбрана задача: "  + currentTask.getTitle());			
		} catch (Exception e) {
			e.printStackTrace();
			showMessage("Не могу загрузить задачу: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
			Logger.log("Не могу загрузить задачу: " + e.getMessage());			
		}
	}
	
	public void clearTask() {
		getClient().getTextDescription().setText("");
		getClient().getAnswerText().setText("");
		VirtualKeyboard.setParameters(null);
	}


    private void connect(Server engine) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
            }
        });
		this.engine = engine;			
	    currentTask = null;
		currentChoise = null;
		VirtualKeyboard.init();
		Logger.log("Выберите задачник");
	}
    
    public boolean login() throws SystemException, AuthentificationException {
    	closed = false;
    	if (SystemProperties.getBooleanFromString(SystemProperties.NEED_LOGIN)) {
    		JPanel panel = new JPanel();
    		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    		JPanel login = new JPanel();
    		JPanel pswd = new JPanel();
    		panel.add(login);
    		panel.add(pswd);
    		login.add(new JLabel("  Логин  "));
    		JTextField loginText = new JTextField(15);
    		login.add(loginText);
    		
    		pswd.add(new JLabel("Пароль"));
    		JPasswordField pswdText = new JPasswordField(15);
    		pswd.add(pswdText);
	    	while (true) {
	    		int res = JOptionPane.showConfirmDialog(getClient(), panel, "Введите имя пользователя и пароль", JOptionPane.YES_NO_OPTION);
	    		if (res == JOptionPane.OK_OPTION) {
			    	try {
			    		this.currentChoise = engine.authorize(loginText.getText(), pswdText.getText());
			    		return true;
			    	} catch (AuthentificationException e) {
			    		showMessage("Неверное имя пользователя или пароль", JOptionPane.ERROR_MESSAGE);
			    		loginText.setText("");
			    		pswdText.setText("");
			    	}
	    		} else {
	    			return false;	    			
	    		}
	    	}
    	} else {
    		this.currentChoise = engine.authorize("default", "default");
    	}
    	return true;
    }

    public void answer(String answer){
        answer(answer, null, null);
    }


    public void answer(String answer, final ObjectHolder holder, final Runnable callBack){
//		try {
			if (currentTask == null) {
				showMessage("Задача не выбрана", JOptionPane.WARNING_MESSAGE);
				return;
			}			
			if ("".equals(answer.trim())) {
				showMessage("Ответ не введен", JOptionPane.WARNING_MESSAGE);
				return;
			}
			currentTask.setAnswer(answer);
			Logger.log("Проверка ответа: " + answer);

			Runnable run1 = new Runnable() {
				public void run() {
					ClientUI.client.startProcessedDialog();				
				}
			};
			
			final Runnable run = new Runnable() {
				public void run() {					
					try {
                        boolean result = engine.verify(currentChoise, currentTask);
                        if (holder != null) {
                            holder.value = new Boolean(result);
                            callBack.run();
                        }
                        if (result) {
							ClientUI.client.stopProcessedDialog();
							showMessage("Ответ верен. Задача решена.", JOptionPane.INFORMATION_MESSAGE);
						} else {
							ClientUI.client.stopProcessedDialog();
							showMessage("Ответ не верен", JOptionPane.WARNING_MESSAGE);
						}                        
                    } catch (XmlException e) {
						ClientUI.client.stopProcessedDialog();
						e.printStackTrace();
						showMessage("Ошибка в в файле описания задачи: \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
					} catch (TaskDeserializationException e) {
						ClientUI.client.stopProcessedDialog();
						e.printStackTrace();						
						showMessage("Ошибка в в файле описания задачи: \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
					} catch (SystemException e) {
						ClientUI.client.stopProcessedDialog();						
						e.printStackTrace();
						showMessage("Ошибка в системе: \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
					} catch (UserAnswerParseException e) {
						ClientUI.client.stopProcessedDialog();
						e.printStackTrace();
						showMessage(e.getMessage(), JOptionPane.ERROR_MESSAGE);
					} catch(Exception e) {
                        ClientUI.client.stopProcessedDialog();
						e.printStackTrace();
						showMessage("Внутренняя ошибка: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                    } finally {
						ClientUI.client.stopProcessedDialog();
					}
				}
			};

			SwingUtilities.invokeLater(run1);
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					new Thread(run).start();
					
				}
				
			});	        
    }
	
	public static void showMessage(String str, int type) {
		Logger.log(str);
		JOptionPane.showMessageDialog(getClient(), str, "Решение", type);
	}
	
	public static void close(int status, JApplet owner) {
		if (SystemProperties.getBooleanFromString(SystemProperties.IS_APPLET) && owner != null) {
			if (ClientUI.client != null) {
				owner.getRootPane().setVisible(false);
				closed = true;
			}
		} else {
			System.exit(status);
		}
	}
}
