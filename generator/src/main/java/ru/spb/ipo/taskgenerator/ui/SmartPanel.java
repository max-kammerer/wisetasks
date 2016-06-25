package ru.spb.ipo.taskgenerator.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import ru.spb.ipo.taskgenerator.util.Actions;

public class SmartPanel extends JPanel{

	public static int PARAMETER = 0;
	public static int DESCRIPTION = 1;
	public static int ELEMENT = 2;
	public static int DISABLE = 3;
	
	private JPanel attributePanel = null;
	private JScrollPane jScrollPane = null;
	private JTable attributes = null;
	private AttributeTableModel attributeTableModel = null;  //  @jve:decl-index=0:visual-constraint=""
	private JPanel actionPAnel = null;
	private JButton apply = null;
	public static JTable table;
	/**
	 * This method initializes 
	 * 
	 */
	public SmartPanel() {
		super();
		initialize();
		table = getAttributes();
		smartPanel = this;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setLayout(new BorderLayout());
        this.setSize(new java.awt.Dimension(271,123));
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Свойства", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));        
        this.setPreferredSize(new java.awt.Dimension(155,183));
        this.add(getActionPAnel(), java.awt.BorderLayout.SOUTH);
        this.add(getTabbedPane(), java.awt.BorderLayout.CENTER);
			
	}

	/**
	 * This method initializes attributePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAttributePanel() {
		if (attributePanel == null) {
			BorderLayout borderLayout = new BorderLayout();			
			attributePanel = new JPanel();
			attributePanel.setLayout(borderLayout);
			attributePanel.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return attributePanel;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();			
			jScrollPane.setViewportView(getAttributes());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes attributes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getAttributes() {
		if (attributes == null) {
			attributes = new JTable();			
			//attributes.setShowGrid(true);
			attributes.setModel(getAttributeTableModel());
			((DefaultCellEditor)attributes.getDefaultEditor(Object.class)).setClickCountToStart(1);
			//attributes.setCellEditor(new DefaultCellEditor())
			//((DefaultCellEditor)attributes.getCellEditor()).setClickCountToStart(1);
		}
		return attributes;
	}

	/**
	 * This method initializes attributeTableModel	
	 * 	
	 * @return ru.spb.ipo.taskgenerator.ui.AttributeTableModel	
	 */
	private AttributeTableModel getAttributeTableModel() {
		if (attributeTableModel == null) {
			attributeTableModel = new AttributeTableModel();
			attributeTableModel.addTableModelListener(new TableModelListener() {

				public void tableChanged(TableModelEvent e) {
					Actions.smartModelChanged();
					
				}
				
			});
		}
		return attributeTableModel;
	}

	/**
	 * This method initializes actionPAnel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getActionPAnel() {
		if (actionPAnel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
			actionPAnel = new JPanel();
			actionPAnel.setLayout(flowLayout);
			actionPAnel.add(getApply(), null);
		}
		return actionPAnel;
	}

	/**
	 * This method initializes apply	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getApply() {
		if (apply == null) {
			apply = new JButton();
			apply.setText("Обновить");
			apply.setEnabled(false);
			apply.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.setAttributes();
				}
			});
		}
		return apply;
	}
	
	
	public static SmartPanel smartPanel;
	private JPanel textPanel = null;
	private JTextArea textArea = null;
	/**
	 * This method initializes textPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTextPanel() {
		if (textPanel == null) {
			textPanel = new JPanel();
			textPanel.setLayout(new BorderLayout());
			textPanel.add(getJScrollPane1(), java.awt.BorderLayout.CENTER);
		}
		return textPanel;
	}

	/**
	 * This method initializes textArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setRows(2);			
			textArea.setLineWrap(true);
			textArea.getDocument().addDocumentListener(new DocumentListener(){

				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					System.out.println("uuuuuuu");
				}

				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					Actions.smartModelChanged();
				}

				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					Actions.smartModelChanged();
				}
				
			});
			textArea.addPropertyChangeListener("text", new PropertyChangeListener(){

				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					System.out.println("ccccccccccc");
				}
				
			});
			
		}
		return textArea;
	}
	
	private TaskTreeNode currentNode;
	
	public void updateTabs(int elementType, TaskTreeNode node, String [] [] attributes, String text, String [][] parameters) {
		currentNode = node;
		getTabbedPane().setEnabledAt(0, true);
		getAttributes().setEnabled(true);
		getTabbedPane().setEnabledAt(1, true);
		getTextArea().setEnabled(true);
		getTabbedPane().setEnabledAt(2, true);
		getParameters().setEnabled(true);
		getAttributeTableModel().updateTable(attributes);
		getParametersTableModel().updateTable(parameters);
		getTextArea().setText(text);
		if (elementType == DISABLE) {
			getTabbedPane().setEnabledAt(0, false);
			getAttributes().setEnabled(false);
			getTabbedPane().setEnabledAt(1, false);
			getTextArea().setEnabled(false);
			getTabbedPane().setEnabledAt(2, false);
			getParameters().setEnabled(false);
			getApply().setEnabled(false);
		} else if (elementType == PARAMETER) {
			getTabbedPane().setSelectedIndex(2);
			getTabbedPane().setEnabledAt(1, false);						
		} else if (elementType == DESCRIPTION) {
			getTabbedPane().setSelectedIndex(1);
			getTabbedPane().setEnabledAt(2, false);			
		} else {		
			getTabbedPane().setSelectedIndex(0);									
			getTabbedPane().setEnabledAt(2, false);
		}
		updateUI();
	}
	
	private boolean myIsTable = true;
	private JScrollPane jScrollPane1 = null;
	private JTabbedPane tabbedPane = null;
	private JPanel parameterPanel = null;
	private JScrollPane parameterScroll = null;
	private JTable parameters = null;
	private ParametersTableModel parametersTableModel = null;  //  @jve:decl-index=0:visual-constraint=""
	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getTextArea());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes tabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();			
			tabbedPane.addTab("Аттрибуты", null, getAttributePanel(), null);
			tabbedPane.addTab("Текст", null, getTextPanel(), null);
			tabbedPane.addTab("Параметры", null, getParameterPanel(), null);			
		}
		return tabbedPane;
	}

	/**
	 * This method initializes parameterPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getParameterPanel() {
		if (parameterPanel == null) {
			parameterPanel = new JPanel();
			parameterPanel.setLayout(new BorderLayout());
			parameterPanel.add(getParameterScroll(), java.awt.BorderLayout.NORTH);
		}
		return parameterPanel;
	}

	/**
	 * This method initializes parameterScroll	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getParameterScroll() {
		if (parameterScroll == null) {
			parameterScroll = new JScrollPane();
			parameterScroll.setViewportView(getParameters());
		}
		return parameterScroll;
	}

	/**
	 * This method initializes parameters	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getParameters() {
		if (parameters == null) {
			parameters = new JTable();
			parameters.setModel(getParametersTableModel());
			((DefaultCellEditor)parameters.getDefaultEditor(Object.class)).setClickCountToStart(1);
		}
		return parameters;
	}

	/**
	 * This method initializes parametersTableModel	
	 * 	
	 * @return ru.spb.ipo.taskgenerator.ui.ParametersTableModel	
	 */
	public ParametersTableModel getParametersTableModel() {
		if (parametersTableModel == null) {
			parametersTableModel = new ParametersTableModel();
			parametersTableModel.addTableModelListener(new TableModelListener() {

				public void tableChanged(TableModelEvent e) {
					Actions.smartModelChanged();
					
				}
				
			});

		}
		return parametersTableModel;
	}

	public TaskTreeNode getCurrentNode() {
		return currentNode;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
