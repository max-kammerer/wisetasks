package ru.spb.ipo.taskgenerator.ui;

import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import ru.spb.ipo.common.ui.InfoDialog;
import ru.spb.ipo.taskgenerator.util.Actions;


public class MainMenu extends JMenuBar {

	private JMenu file = null;
	private JMenuItem open = null;
	private JMenuItem save = null;
	private JFileChooser fileChooser = null;
	private JMenuItem newTask = null;
	private JMenuItem exit = null;
	private JMenu help = null;
	private JMenuItem about = null;
	private JMenu elements = null;
	private JMenuItem descriptionParameters = null;
	private JMenuItem verifierParameters = null;
	private JMenuItem expand = null;
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
        this.add(getElements());
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
			file.add(getNewTask());
			file.add(getOpen());
			file.add(getSave());
			file.addSeparator();
			file.add(getExit());
		}
		return file;
	}

	/**
	 * This method initializes open	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getOpen() {
		if (open == null) {
			open = new JMenuItem();
			open.setText("Открыть...");
			open.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = getFileChooser().showOpenDialog(TaskGenerator.getFrame());
		            if (result == JFileChooser.APPROVE_OPTION) {
		                File file = getFileChooser().getSelectedFile();
		                if (file.length() != 0) {
		                    Actions.load(file);
		                }
		            }
				}
			});
		}
		return open;
	}

	/**
	 * This method initializes save	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSave() {
		if (save == null) {
			save = new JMenuItem();
			save.setText("Сохранить...");
			save.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = getFileChooser().showSaveDialog(TaskGenerator.getFrame());
		            if (JFileChooser.APPROVE_OPTION == result) {		                
		                File file = getFileChooser().getSelectedFile();
		                if (!file.getName().toLowerCase().endsWith(".xml")) {
		                	file = new File(file.getAbsolutePath() + ".xml");
		                }
		                try {		                    
		                    Actions.save2file(file);		                    
		                } catch (IOException e1) {
		                    e1.printStackTrace();
		                }
		            }
				}
			});
		}
		return save;
	}
	
	public JFileChooser getFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser(new File(".").getAbsolutePath());			
			fileChooser.setDialogTitle("Выберите файл...");
			fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);	        
			fileChooser.setFileFilter(new FileFilter() {
	            public boolean accept(File pathname) {
	                if (pathname.isDirectory()) return true;
	                if (pathname.isFile())
	                    if (pathname.getName().toLowerCase().endsWith(".xml")) return true;
	                return false;
	            }

	            public String getDescription() {
	                return "xml файлы (*.xml)";
	            }
	        });
		}
		return fileChooser;
	}

	/**
	 * This method initializes newTask	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getNewTask() {
		if (newTask == null) {
			newTask = new JMenuItem();
			newTask.setText("Создать");
			newTask.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.load(new File("emptytask.xml"));
					TaskGenerator.getFrame().setTitleWithFile(null);
				}
			});
		}
		return newTask;
	}

	/**
	 * This method initializes exit	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExit() {
		if (exit == null) {
			exit = new JMenuItem();
			exit.setText("Выход");
			exit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.exit();
				}
			});
		}
		return exit;
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
					JDialog dialog = new InfoDialog(TaskGenerator.getFrame());
				}
			});
		}
		return about;
	}

	/**
	 * This method initializes elements	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getElements() {
		if (elements == null) {
			elements = new JMenu();
			elements.setText("Элементы");
			elements.add(getDescriptionParameters());
			elements.add(getVerifierParameters());
			elements.addSeparator();
			elements.add(getExpand());			
			elements.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e){					
					if (Actions.isParameterExists(false)) {
						getVerifierParameters().setEnabled(false);
					} else {
						getVerifierParameters().setEnabled(true);
					}
					
					if (Actions.isParameterExists(true)) {
						getDescriptionParameters().setEnabled(false);
					} else {
						getDescriptionParameters().setEnabled(true);
					}
				}
			});
			
		}
		return elements;
	}

	/**
	 * This method initializes descriptionParameters	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDescriptionParameters() {
		if (descriptionParameters == null) {
			descriptionParameters = new JMenuItem();
			descriptionParameters.setText("Добавить параметры генерации");
			descriptionParameters.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.addParameterSet(true);
				}
			});
		}
		return descriptionParameters;
	}

	/**
	 * This method initializes verifierParameters	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getVerifierParameters() {
		if (verifierParameters == null) {
			verifierParameters = new JMenuItem();
			verifierParameters.setText("Добавить параметры верификации");
			verifierParameters.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.addParameterSet(false);
				}
			});
		}
		return verifierParameters;
	}

	/**
	 * This method initializes expand	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExpand() {
		if (expand == null) {
			expand = new JMenuItem();
			expand.setText("Раскрыть все вершины");
			expand.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.expand();
				}
			});
		}
		return expand;
	}

}
