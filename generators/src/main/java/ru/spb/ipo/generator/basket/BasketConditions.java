package ru.spb.ipo.generator.basket;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import java.util.Map;

public class BasketConditions extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JButton jButton = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel = null;
	private JComboBox jComboBox = null;
	private JPanel jPanel4 = null;
	private JCheckBox isCont = null;
	
	public BasketConditions (BaseGeneratorUI gen) {
		super(gen);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(null, "\u0418\u0437 \u043a\u043e\u0440\u0437\u0438\u043d\u044b ...", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		this.add(getJPanel(), null);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(getJPanel1(), null);
			jPanel.add(getJPanel4(), null);
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
			jPanel1.setLayout(new BorderLayout());
			jPanel1.add(getJPanel2(), BorderLayout.EAST);
			jPanel1.add(getJPanel3(), BorderLayout.CENTER);
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
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			jPanel2 = new JPanel();
			jPanel2.setLayout(flowLayout);
			jPanel2.add(getJButton(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Добавить");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					addCondition(new BasketBallComplexElement(jComboBox.getSelectedIndex(), 1));
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(FlowLayout.LEFT);
			jLabel = new JLabel();
			jLabel.setText("Вытаскивают");
			jPanel3 = new JPanel();
			jPanel3.setLayout(flowLayout1);
			jPanel3.add(jLabel, null);
			jPanel3.add(getJComboBox(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setModel(new DefaultComboBoxModel(BasketXmlGenerator.ballColors));
		}
		return jComboBox;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setAlignment(FlowLayout.LEFT);
			jPanel4 = new JPanel();
			jPanel4.setLayout(flowLayout2);
			jPanel4.add(getIsCont(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes isCont	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getIsCont() {
		if (isCont == null) {
			isCont = new JCheckBox();
			isCont.setText("Последовательно");
			isCont.setToolTipText("Последовательно или одновременно");
		}
		return isCont;
	}

	
	public void fillMaps(Map source, Map func, Map task) {		
		func.put("isCont", isCont.isSelected());		
		func.put("toFind", getConditions());
	}
	
	

}
