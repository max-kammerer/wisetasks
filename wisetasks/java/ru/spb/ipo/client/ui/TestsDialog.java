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

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import ru.spb.ipo.client.ui.utils.Actions;
import ru.spb.ipo.engine.rmi.ContestProxy;
import javax.swing.JScrollPane;

public class TestsDialog extends JDialog {

	private JPanel jContentPane = null;
	private JPanel testsList = null;
	private JPanel actions = null;
	private JButton okButton = null;
	private JButton cancelButton = null;
	private JList tests = null;
	private ContestProxy[] myTests;
	private JScrollPane jScrollPane = null;

	/**
	 * This is the default constructor
	 */
	public TestsDialog(JFrame owner, ContestProxy[] tests) {
		super(owner, true);
		myTests = tests;
		initialize();
		//showAtCenter();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);
    }

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(353, 239);
		this.setResizable(false);
		this.setTitle("Выберите нужный задачник");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getTestsList(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getActions(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}

//	private void showAtCenter() {
//		Point position = getOwner().getLocation();
//		Dimension screenSize = getOwner().getSize();
//        setLocation(position.x + screenSize.width / 2 - getSize().width / 2, position.y + screenSize.height / 2 - getSize().height / 2);
//	}

	/**
	 * This method initializes testsList	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTestsList() {
		if (testsList == null) {
			testsList = new JPanel();
			testsList.setLayout(new BorderLayout());
			testsList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Список задачников", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
			testsList.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return testsList;
	}

	/**
	 * This method initializes actions	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getActions() {
		if (actions == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
			actions = new JPanel();
			actions.setLayout(flowLayout);
			actions.add(getCancelButton(), null);
			actions.add(getOkButton(), null);
		}
		return actions;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("Выбрать");
			okButton.setEnabled(false);
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Actions.getClient().getActions().testSelected(myTests[getTests().getSelectedIndex()].getId());
					dispose();
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes cancelButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Назад");
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelButton;
	}

	/**
	 * This method initializes tests	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getTests() {
		if (tests == null) {
			tests = new JList();
			DefaultListModel model = new DefaultListModel();	        
			for (int i = 0; i < myTests.length; i++){
				model.add(i, new Integer(i + 1) + ". " + myTests[i].getTitle());
			}
			tests.setModel(model);
			tests.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					getOkButton().setEnabled(true);
				}
			});
			tests.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
		            if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
		                if (getTests().getSelectedIndex() != -1) {
		                    dispose();
		                    Actions.getClient().getActions().testSelected(myTests[getTests().getSelectedIndex()].getId());
                        }
		            }
		        }				
			});
		}
		return tests;
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
