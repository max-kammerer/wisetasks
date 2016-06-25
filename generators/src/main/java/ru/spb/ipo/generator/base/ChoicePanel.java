package ru.spb.ipo.generator.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public abstract class ChoicePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JButton addButton = null;
	private JPanel choiceContent = null;
	private JList jList = null;
	private JScrollPane jScrollPane = null;
	private JPanel jPanel1 = null;

	/**
	 * This is the default constructor
	 */
	public ChoicePanel() {
		super();
		initialize();
	}
	
	public ChoicePanel(JPanel choiceContent) {
		super();
		this.choiceContent = choiceContent;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		BorderLayout borderLayout1 = new BorderLayout();
		borderLayout1.setVgap(5);
		//setMinimumSize(new Dimension(0, 0));
		//setMaximumSize(new Dimension(520, 120));
		this.setLayout(borderLayout1);		
		this.add(getJPanel(), BorderLayout.NORTH);
		this.add(getJScrollPane(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(5);
			borderLayout.setVgap(0);
			jPanel = new JPanel();
			jPanel.setLayout(borderLayout);
			jPanel.add(getChoiceContent(), BorderLayout.CENTER);
			jPanel.add(getJPanel1(), BorderLayout.EAST);
		}
		return jPanel;
	}

	/**
	 * This method initializes addButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("Добавить");
			addButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ComplexElement ce = getElementToAdd();
					((DefaultListModel)jList.getModel()).addElement(ce);
				}
			});
		}
		return addButton;
	}

	/**
	 * This method initializes choiceContent	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	protected JPanel getChoiceContent() {
		if (choiceContent == null) {
			choiceContent = new JPanel();
			choiceContent.setLayout(new GridBagLayout());
		}
		return choiceContent;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setModel(new DefaultListModel());
//			jList.setMinimumSize(new Dimension(0, 0));
//			jList.setMaximumSize(new Dimension(200, 100));
		}
		return jList;
	}
	
	protected abstract ComplexElement getElementToAdd();
	
	protected ComplexElement [] getChoices() {
		ComplexElement [] res = new ComplexElement [jList.getModel().getSize()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (ComplexElement)jList.getModel().getElementAt(i); 
		} 
		return res;		
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
//			jScrollPane.setMinimumSize(new Dimension(0, 0));
//			jScrollPane.setMaximumSize(new Dimension(200, 100));
//			jScrollPane.setPreferredSize(new Dimension(200, 100));
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(5);
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout);
			jPanel1.add(getAddButton(), null);
		}
		return jPanel1;
	}

}
