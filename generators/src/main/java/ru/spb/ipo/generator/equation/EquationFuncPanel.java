package ru.spb.ipo.generator.equation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

public class EquationFuncPanel extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JComboBox xNumber = null;
	private JButton addRegion = null;
	private JLabel jLabel3 = null;
	private JComboBox jComboBox = null;
	private JComboBox jComboBox1 = null;
	private JButton addConstraint = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JComboBox constraint = null;
	private JComboBox comdConstraint = null;
	private JComboBox constrNumber = null;
	/**
	 * This is the default constructor
	 */
	public EquationFuncPanel(BaseGeneratorUI gen) {
		super(gen);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(450, 138);
		this.setMinimumSize(new Dimension(440, 138));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));		
		this.setBorder(BorderFactory.createTitledBorder(null, "\u041e\u0433\u0440\u0430\u043d\u0438\u0447\u0435\u043d\u0438\u044f", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
		this.add(getJPanel1(), null);
		//this.add(getJPanel(), null);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(FlowLayout.RIGHT);
			jLabel3 = new JLabel();
			jLabel3.setText("Ограничения  ");
			jPanel = new JPanel();
			jPanel.setLayout(flowLayout1);
			jPanel.add(jLabel3, null);
			jPanel.add(getJComboBox(), null);
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJComboBox1(), null);
			jPanel.add(getJPanel3(), null);
			jPanel.add(getAddConstraint(), null);
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
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout);			
			jPanel1.add(getXNumber(), null);
			jPanel1.add(getComdConstraint(), null);
			jPanel1.add(getConstrNumber(), null);
			jPanel1.add(getAddRegion(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes xNumber	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getXNumber() {
		if (xNumber == null) {
			xNumber = new JComboBox(new DefaultComboBoxModel(new String[]{"x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9", "x10"}));
		}
		return xNumber;
	}

	/**
	 * This method initializes addRegion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddRegion() {
		if (addRegion == null) {
			addRegion = new JButton();
			addRegion.setText("Добавить");
			addRegion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Object cond = comdConstraint.getSelectedItem();
					int index = constrNumber.getSelectedIndex();
					int number = -1;
					if (index > 0) {
						number = index;
					} else {						
						try {
							number = Integer.valueOf((String)constrNumber.getSelectedItem());
						} catch (Exception ew) {
						}
						if (number < 0 || number > 50) {
							JOptionPane optionPane = new JOptionPane("Значение поля должно быть числом от 0 до 50",
								 	JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
								 	optionPane.createDialog(EquationFuncPanel.this, "Неверное значение").setVisible(true);
					 		return;
						}
					}
					boolean isValue = index > 0 ? false : true;
					int axis = xNumber.getSelectedIndex() + 1;
					if (cond.equals("<=")) {						
						addCondition(new DiaposonElement(-1, number, axis, isValue));
					} else if (cond.equals(">=") ){
						addCondition(new DiaposonElement(number, -1, axis, isValue));
					} else if (cond.equals("==") ){
						addCondition(new DiaposonElement(number, axis, isValue));
					} else {
						addCondition(new DivElement(number, axis, isValue));
					}
				}
			});
		}
		return addRegion;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox(new DefaultComboBoxModel(new String[]{"x[1]", "x[2]", "x[3]", "x[4]", "x[5]", "x[6]", "x[7]", "x[8]", "x[9]", "x[10]"}));
		}
		return jComboBox;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox(new DefaultComboBoxModel(new String[]{"четное", "нечетное", "делится на 3", "делится на 5", "x[5]", "x[6]", "x[7]", "x[8]", "x[9]", "x[10]"}));
		}
		return jComboBox1;
	}

	/**
	 * This method initializes addConstraint	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddConstraint() {
		if (addConstraint == null) {
			addConstraint = new JButton();
			addConstraint.setText("Добавить");
		}
		return addConstraint;
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
			jPanel3.setLayout(new FlowLayout());
		}
		return jPanel3;
	}

	/**
	 * This method initializes constraint	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getConstraint() {
		if (constraint == null) {
			constraint = new JComboBox(new DefaultComboBoxModel(new String[]{"", "четное", "нечетное", "делится на 3", "делится на 5", "x[5]", "x[6]", "x[7]", "x[8]", "x[9]", "x[10]"}));
			constraint.setEditable(true);
			constraint.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					Object item = e.getItem();
					if (0 >= constraint.getSelectedIndex()) {						
												
						constraint.setEditable(true);
					} else {						
												
						constraint.setEditable(false);
					}
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			
		}
		return constraint;
	}

	/**
	 * This method initializes comdConstraint	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComdConstraint() {
		if (comdConstraint == null) {
			comdConstraint = new JComboBox(new DefaultComboBoxModel(new String[]{"<=", "==" , ">=", "делится на"}));
			comdConstraint.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (comdConstraint.getSelectedIndex() == 3) {
						if (constrNumber.getSelectedIndex() > 0) { 
							constrNumber.setSelectedIndex(0);			
						}									
					}
				}
			});
		}
		return comdConstraint;
	}

	/**
	 * This method initializes constrNumber	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getConstrNumber() {
		if (constrNumber == null) {
			constrNumber =  new JComboBox(new DefaultComboBoxModel(new String[]{"", "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9", "x10"}));
			constrNumber.setEditable(true);
			constrNumber.setSelectedItem("2");
			constrNumber.setToolTipText("Введите число или выберите неизвестную");
			constrNumber.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					Object value = e.getItem();
					if (constrNumber.getSelectedIndex() <= 0) {
						constrNumber.setEditable(true);						
					} else {
						constrNumber.setEditable(false);
						if (comdConstraint.getSelectedIndex() == 3) {
							constrNumber.setSelectedIndex(0);												
						}
					}
				}
			});
		}
		return constrNumber;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
