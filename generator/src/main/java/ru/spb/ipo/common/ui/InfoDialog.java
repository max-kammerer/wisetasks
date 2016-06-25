package ru.spb.ipo.common.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class InfoDialog extends JDialog {

	private ActionListener myClose = new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			InfoDialog.this.setVisible(false);
			InfoDialog.this.dispose();					
		}
	};
	
	private WindowAdapter myDef = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			InfoDialog.this.setVisible(false);
			InfoDialog.this.dispose();
		}
	};
	
	
	private JPanel mainPanel = null;
	private JPanel infoPanel = null;
	private JPanel actionPanel = null;
	private JButton okButton = null;
	private JLabel copyright = null;
	private JLabel email = null;
	private JPanel stub = null;

	private JLabel emailLabel = null;

	private JLabel createor = null;

	private JPanel stub2 = null;

	private JPanel stub3 = null;

	/**
	 * This method initializes 
	 * 
	 */
	public InfoDialog(JFrame owner) {
		super(owner);
		initialize();
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		setLocationRelativeTo(owner);
		setVisible(true);
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(405, 141));
        this.setResizable(false);
        this.setContentPane(getMainPanel());
        this.setTitle("��������� �����");
        this.addWindowListener(myDef);
	}

	/**
	 * This method initializes mainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(getInfoPanel(), java.awt.BorderLayout.CENTER);
			mainPanel.add(getActionPanel(), java.awt.BorderLayout.SOUTH);
		}
		return mainPanel;
	}

	/**
	 * This method initializes infoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInfoPanel() {
		if (infoPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.insets = new java.awt.Insets(0,40,0,0);
			gridBagConstraints5.gridy = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.insets = new java.awt.Insets(0,0,0,20);
			gridBagConstraints3.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.weightx = 1.0D;
			gridBagConstraints3.gridy = 2;
			createor = new JLabel();
			createor.setText("Michael Bogdanov");
			createor.setForeground(new java.awt.Color(0,102,51));
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints2.gridy = 4;
			emailLabel = new JLabel();
			emailLabel.setText("e-mail");
			emailLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 3;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new java.awt.Insets(0,0,0,20);
			gridBagConstraints1.gridy = 4;
			gridBagConstraints1.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints1.weightx = 1.0D;
			gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridx = 3;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new java.awt.Insets(0,0,0,0);
			gridBagConstraints.gridy = 2;
			gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints.weightx = 0.0D;
			gridBagConstraints.gridx = 1;
			email = new JLabel();
			email.setText("mikhael.bogdanov@gmail.com");
			email.setForeground(new java.awt.Color(0,102,51));
			copyright = new JLabel();
			copyright.setText("Copyright (C) 2006-2008");
			copyright.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
			infoPanel = new JPanel();
			infoPanel.setLayout(new GridBagLayout());
			infoPanel.setBorder(BorderFactory.createTitledBorder(null, "Task Generator v 0.77", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			infoPanel.add(copyright, gridBagConstraints);
			infoPanel.add(email, gridBagConstraints1);
			infoPanel.add(getStub(), gridBagConstraints11);
			infoPanel.add(emailLabel, gridBagConstraints2);
			infoPanel.add(createor, gridBagConstraints3);
			infoPanel.add(getStub2(), gridBagConstraints4);
			infoPanel.add(getStub3(), gridBagConstraints5);
		}
		return infoPanel;
	}

	/**
	 * This method initializes actionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getActionPanel() {
		if (actionPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
			actionPanel = new JPanel();
			actionPanel.setLayout(flowLayout);
			actionPanel.add(getOkButton(), null);
		}
		return actionPanel;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("OK");
			okButton.addActionListener(myClose);
		}
		return okButton;
	}

	/**
	 * This method initializes stub	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStub() {
		if (stub == null) {
			stub = new JPanel();
		}
		return stub;
	}

	/**
	 * This method initializes stub2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStub2() {
		if (stub2 == null) {
			stub2 = new JPanel();
		}
		return stub2;
	}

	/**
	 * This method initializes stub3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStub3() {
		if (stub3 == null) {
			stub3 = new JPanel();
		}
		return stub3;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
