package ru.spb.ipo.generator.base.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import ru.spb.ipo.generator.base.FileUtil.ListIdEntry;
import ru.spb.ipo.generator.base.FileUtil;

public class TestPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSplitPane jSplitPane = null;  //  @jve:decl-index=0:visual-constraint="10,36"
	private JPanel jPanel = null;
	private JPanel jPanel2 = null;	
	private JScrollPane jScrollPane = null;
	private JList tests = null;
	private JPanel jPanel1 = null;
	private JButton addTest = null;
	private JPanel namePanel = null;  //  @jve:decl-index=0:visual-constraint="418,291"
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JPanel jPanel3 = null;
	private JScrollPane jScrollPane1 = null;
	private JList tasks = null;
	private JFileChooser fileChooser; 

	/**
	 * This is the default constructor
	 */
	public TestPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {		
		this.setLayout(new BorderLayout());
		this.add(getJSplitPane(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setDividerLocation(300);			
			jSplitPane.setLeftComponent(getJPanel());
			jSplitPane.setRightComponent(getJPanel2());
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u044b\u0435 \u0441\u0431\u043e\u0440\u043d\u0438\u043a\u0438", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.add(getJScrollPane(), BorderLayout.CENTER);
			jPanel.add(getJPanel1(), BorderLayout.SOUTH);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.add(getJPanel3(), BorderLayout.CENTER);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTests());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes tests	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getTests() {
		if (tests == null) {
			tests = new JList();
			DefaultListModel model = createModel(FileUtil.getTestList());
			tests.setModel(model);			
			tests.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
						ListIdEntry entry = (ListIdEntry)getTests().getSelectedValue();
				    	if (entry != null) {        
				    		getTasks().setModel(createModel(FileUtil.getTasks(entry)));
				    	}
					}
					if (e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3) {
//						int [] obj = getFunctionList().getSelectedIndices();
//				    	if (obj != null && obj.length != 0) {        
//				    		getCondPopup().show(e.getComponent(), e.getX(), e.getY());
//				    	}
					}	
				}
			});
		}
		return tests;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout);
			jPanel1.add(getAddTest(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes addTest	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddTest() {
		if (addTest == null) {
			addTest = new JButton();
			addTest.setText("Создать новый сборник");
			addTest.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String input = JOptionPane.showInputDialog(TestPanel.this, "Название сборника", "Создание нового сборника...", JOptionPane.PLAIN_MESSAGE);
					if (input != null && !"".equals(input)) {
						try {
							int res = getFileChooser().showSaveDialog(TestPanel.this);
							if (res == JFileChooser.APPROVE_OPTION) {
								ListIdEntry newCollection = FileUtil.createTest(input, getFileChooser().getSelectedFile());						
								((DefaultListModel)getTests().getModel()).addElement(newCollection);
								JOptionPane.showMessageDialog(TestPanel.this, "Новый сборник успешно создан.", "Создание нового сборника", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(TestPanel.this, "Не могу создать сборник: \n" + ex.getMessage(), "Ошибка при создании", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		}
		return addTest;
	}

	/**
	 * This method initializes namePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getNamePanel() {
		if (namePanel == null) {
			namePanel = new JPanel();
			namePanel.setLayout(new FlowLayout());
			namePanel.setSize(new Dimension(166, 35));
			namePanel.add(getJLabel(), null);
			namePanel.add(getJTextField(), null);
		}
		return namePanel;
	}

	/**
	 * This method initializes jLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel() {
		if (jLabel == null) {
			jLabel = new JLabel();
			jLabel.setText("JLabel");
		}
		return jLabel;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setColumns(10);
		}
		return jTextField;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.weightx = 1.0;
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.setBorder(BorderFactory.createTitledBorder(null, "\u0421\u043f\u0438\u0441\u043e\u043a \u0437\u0430\u0434\u0430\u0447", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel3.add(getJScrollPane1(), gridBagConstraints);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getTasks());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes tasks	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getTasks() {
		if (tasks == null) {
			tasks = new JList();
		}
		return tasks;
	}
	
	public static DefaultListModel createModel(ListIdEntry [] entries) {
		DefaultListModel model = new DefaultListModel();		
		for (int i = 0; i < entries.length; i++) {
			model.addElement(entries[i]);				
		}
		return model;
	}

	public JFileChooser getFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser(new File("./tasks").getAbsolutePath());			
			fileChooser.setDialogTitle("Выберите папку для сборника...");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);	        
			fileChooser.setFileFilter(new FileFilter() {
	            public boolean accept(File pathname) {
	                return true;	                
	            }

				public String getDescription() {
					return "Папки";
				}
	        });
		}
		return fileChooser;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
