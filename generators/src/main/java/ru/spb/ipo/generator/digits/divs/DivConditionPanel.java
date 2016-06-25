package ru.spb.ipo.generator.digits.divs;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DivConditionPanel extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox condparams = null;
	private JButton add = null;
	private JTextField comNumber = null;
	private JLabel jLabel = null;

	/**
	 * This is the default constructor
	 */
	public DivConditionPanel(BaseGeneratorUI gen) {
		super(gen);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel = new JLabel();
		jLabel.setText("раз");
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.setLayout(flowLayout);		
		this.add(getCondparams(), null);
		this.add(getComNumber(), null);
		this.add(jLabel, null);
		this.add(getAdd(), null);
		setMinimumSize(new Dimension(420, 35));
		setPreferredSize(new Dimension(420, 35));
	}

	/**
	 * This method initializes condparams	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCondparams() {
		if (condparams == null) {
			condparams = new JComboBox(new DefaultComboBoxModel(new String [] {"увеличивается в целое число раз", "уменьшается в целое число раз", "не меняется", "увеличивается в", "уменьшаются в"} ));
			condparams.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					boolean enable = condparams.getSelectedIndex() > 2; 
					getComNumber().setEnabled(enable);
					jLabel.setEnabled(enable);
				}
			});
			condparams.setSelectedIndex(1);
		}
		return condparams;
	}

	/**
	 * This method initializes add	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAdd() {
		if (add == null) {
			add = new JButton();
			add.setText("Добавить");
			add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DivCompareElement element = null;
					String text = (String)getCondparams().getSelectedItem();
					if (getComNumber().isEnabled()) {
						String i = getComNumber().getText();
						int res = -1;
						try {
							res = Integer.valueOf(i);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(getGenerator(), "Неверный параметр условия: проверьте правильность введеного числа", "Ошибка", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (res <= 1) {
							JOptionPane.showMessageDialog(getGenerator(), "Введите число большее 1");
							return;
						}
					}
					switch (getCondparams().getSelectedIndex()) {
					case 0: element = new DivCompareElement(1, -1, text); break;
					case 1: element = new DivCompareElement(-1, -1, text); break;
					case 2: element = new DivCompareElement(0, -1, text); break;
					case 3: element = new DivCompareElement(1, Integer.valueOf(getComNumber().getText()), text); break; 
					case 4: element = new DivCompareElement(-1, Integer.valueOf(getComNumber().getText()), text); break; 
					}
					addCondition(element);
				}
				
			});
		}
		return add;
	}

	/**
	 * This method initializes comNumber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getComNumber() {
		if (comNumber == null) {
			comNumber = new JTextField();
			comNumber.setColumns(5);
		}
		return comNumber;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
