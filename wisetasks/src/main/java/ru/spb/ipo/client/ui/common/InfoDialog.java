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

package ru.spb.ipo.client.ui.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;

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
	
	
	private JPanel mainPanel = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private JPanel infoPanel = null;
	private JPanel actionPanel = null;
	private JButton okButton = null;
	private JLabel developer = null;
	private JLabel email = null;
	private JLabel developerName = null;

	private JPanel stub2 = null;

	private JPanel stub3 = null;

	private JPanel jPanel = null;

	private JLabel scientificAdviserName = null;

	private JLabel scientificAdviser = null;

	private JPanel jPanel1 = null;

	private JLabel jLabel = null;

	private JPanel jPanel2 = null;

	private JLabel copyright = null;

	private JPanel jPanel3 = null;

	private JPanel jPanel4 = null;

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
        //showAtCenter();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {


        this.setContentPane(getMainPanel());
        this.setTitle("WiseTasks");
        this.pack();
        this.addWindowListener(myDef);
        //pack();
        this.setResizable(false);
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
			mainPanel.setSize(new Dimension(420, 140));
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
            infoPanel = new JPanel();
            infoPanel.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder(null,
                                    "WiseTasks v 0.71",
                                    TitledBorder.DEFAULT_JUSTIFICATION,
                                    TitledBorder.DEFAULT_POSITION,
                                    new Font("Dialog", Font.BOLD, 12),
                                    new Color(51, 51, 51)),
                            BorderFactory.createEmptyBorder(5,5,5,5)));


            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

			copyright = new JLabel("(C) 2006-2008, 2012 Wisetasks", JLabel.CENTER);

            infoPanel.add(wrapFlow(copyright));
            infoPanel.add(Box.createVerticalStrut(10));

            scientificAdviser = new JLabel("Cергей Поздняков", JLabel.CENTER);
			scientificAdviser.setForeground(new java.awt.Color(3, 3, 147));

            scientificAdviserName = new JLabel("Научный руководитель", JLabel.CENTER);

            infoPanel.add(wrapFlow(scientificAdviserName));
            infoPanel.add(wrapFlow(scientificAdviser));


			developerName = new JLabel();
			developerName.setText("Михаил Богданов");
			developerName.setForeground(new java.awt.Color(3, 3, 147));

            developer = new JLabel();
            developer.setText("Разработчик");

            infoPanel.add(Box.createVerticalStrut(10));
            infoPanel.add(wrapFlow(developer));
            infoPanel.add(wrapFlow(developerName));
            infoPanel.add(Box.createVerticalStrut(10));

			email = new JLabel();
			email.setText("<html><body><a href=\"\">max.kammerer@yahoo.com</a>&nbsp;&nbsp;</body></html>");
			email.setHorizontalAlignment(SwingConstants.RIGHT);
			email.setForeground(new java.awt.Color(3,3,147));
            infoPanel.add(wrapFlow(email));


		}
		return infoPanel;
	}

    private JPanel wrapFlow(JLabel label) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.add(label);
        return panel;
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

//	private void showAtCenter() {
//		Point position = getOwner().getLocation();
//		Dimension screenSize = getOwner().getSize();
//        setLocation(position.x + screenSize.width / 2 - getSize().width / 2, position.y + screenSize.height / 2 - getSize().height / 2);
//        setVisible(true);
//	}

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
			stub3.setPreferredSize(new Dimension(40, 10));
		}
		return stub3;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GridBagLayout());
			jPanel4.setPreferredSize(new Dimension(10, 10));
		}
		return jPanel4;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
