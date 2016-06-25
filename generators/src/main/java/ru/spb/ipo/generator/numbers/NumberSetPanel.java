package ru.spb.ipo.generator.numbers;

import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NumberSetPanel extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel = null;
	private JComboBox naborSize = null;
	private JLabel jLabel1 = null;
	private JComboBox maxDigit = null;
	private JPanel jPanel2 = null;
	private JCheckBox jCheckBox = null;

	/**
	 * This is the default constructor
	 */
	public NumberSetPanel(BaseGeneratorUI gen) {
		super(gen);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(387, 65);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));		
		this.add(getJPanel1(), null);
		this.add(getJPanel2(), null);
		this.add(getJPanel(), null);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			jLabel = new JLabel();
			jLabel.setText("Размер набора");
			jPanel = new JPanel();
			jPanel.setLayout(flowLayout);
			jPanel.add(jLabel, null);
			jPanel.add(getNaborSize(), null);
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
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(FlowLayout.LEFT);
			jLabel1 = new JLabel();
			jLabel1.setText("Цифры в наборе от 0 до ");
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout1);
			jPanel1.add(jLabel1, null);
			jPanel1.add(getMaxDigit(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes naborSize	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getNaborSize() {
		if (naborSize == null) {
			naborSize = new JComboBox();
			naborSize.setModel(new DefaultComboBoxModel(new String[]  {"3","4" ,"5", "6", "7", "8", "9", "10"}));
			naborSize.setSelectedIndex(2);
		}
		return naborSize;
	}

	/**
	 * This method initializes maxDigit	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getMaxDigit() {
		if (maxDigit == null) {
			maxDigit = new JComboBox();
			maxDigit.setModel(new DefaultComboBoxModel(new String[]  {"2", "3", "4", "5", "6", "7", "8", "9"}));
			maxDigit.setSelectedIndex(7);
		}
		return maxDigit;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new FlowLayout());
			jPanel2.add(getJCheckBox(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.setText("Первая цифра не 0");
		}
		return jCheckBox;
	}
	
	public boolean isNumber() {
		return jCheckBox.isSelected();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
