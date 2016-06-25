package ru.spb.ipo.generator.digits.mods;

import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.util.Map;

public class ModSetPanel extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel = null;
	private JPanel jPanel = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel2 = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel3 = null;
	private JPanel jPanel1 = null;
	private JTextField expression = null;
	private JTextField jTextField2 = null;
	private JTextField modNumber = null;
	/**
	 * This is the default constructor
	 */
	public ModSetPanel(BaseGeneratorUI gen) {
		super(gen);
		initialize();
		setSize(new Dimension(500, 500));
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(1);
		gridLayout.setRows(0);
		jLabel2 = new JLabel();
		jLabel2.setText("Последние ");
		jLabel = new JLabel();
		jLabel.setText("Делимое");
		jLabel.setToolTipText("Введите число или арифметическое выражение");
		this.setLayout(gridLayout);		
		this.add(getJPanel(), null);
		//this.add(getJPanel3(), null);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(FlowLayout.LEFT);
			jLabel1 = new JLabel();
			jLabel1.setText("Делитель");
			jLabel1.setToolTipText("Введите число");
			jPanel = new JPanel();
			jPanel.setLayout(flowLayout1);
			jPanel.add(jLabel, null);
			jPanel.add(getJPanel1(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getModNumber(), null);
		}
		return jPanel;
	}



	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("цифры равняются ");
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setHgap(10);
			jPanel3 = new JPanel();
			jPanel3.setLayout(flowLayout);
			jPanel3.add(jLabel2, null);
			jPanel3.add(getJComboBox(), null);
			jPanel3.add(jLabel3, null);			
			jPanel3.add(getJTextField2(), null);
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
			jComboBox = new JComboBox(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6" ,"7", "8", "9"}));
			jComboBox.setSelectedIndex(2);
		}
		return jComboBox;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.weightx = 1.0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(getExpression(), gridBagConstraints3);
		}
		return jPanel1;
	}

	/**
	 * This method initializes expression	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getExpression() {
		if (expression == null) {
			expression = new JTextField();
			expression.setColumns(20);
			expression.setToolTipText("Введите число или арифметическое выражение (выражение не должно содержать вложенного возведения в степень - 5^6^7)");
		}
		return expression;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setColumns(10);
			jTextField2.setText("123");
		}
		return jTextField2;
	}

	/**
	 * This method initializes modNumber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getModNumber() {
		if (modNumber == null) {
			modNumber = new JTextField();
			modNumber.setColumns(5);
			modNumber.setToolTipText("Введите число");
		}
		return modNumber;
	}
	
	protected void clear() {
		modNumber.setText("");
		expression.setText("");
	}
	
	protected void fillParameters(Map source, Map func, Map task) {
		func.put("mod", modNumber.getText());
		func.put("expression", expression.getText());		
	}
	
	protected boolean checkCanSave() {
		String value = modNumber.getText().trim();
		if ("".equals(value)) {
			JOptionPane.showMessageDialog(this, "Не указан делитель!" , "Не указан делитель", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		value = expression.getText().trim();
		if ("".equals(value)) {
			JOptionPane.showMessageDialog(this, "Не указано делимое!" , "Не указано делимое", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

}  //  @jve:decl-index=0:visual-constraint="10,-11"
