package ru.spb.ipo.generator.numbers;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.ListElement;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;
import ru.spb.ipo.generator.cards.TypeModell;

public class NumberFunctionPanel extends ConstraintPanel {

	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JComboBox functSelect = null;
	private JButton addF1 = null;
	
	public static final ListElement []  number_cond =  { 
		new ListElement("Набор состоит из различных цифр", new DiifNumbersGenerator("Набор состоит из различных цифр")),
		new ListElement("Соседние цифры набора различны", new DiffNearGenerator("Соседние цифры набора различны")),
		new ListElement("Цифры набора идут в убывающем порядке", new OrderNumberGenerator( -1)),
		new ListElement("Цифры набора идут в возрастающем порядке", new OrderNumberGenerator(1)),
		new ListElement("Цифры набора идут в неубывающем порядке", new OrderNumberGenerator(2)),
		new ListElement("Цифры набора идут в невозрастающем порядке", new OrderNumberGenerator(-2)),						
	};
	private JLabel jLabel = null;

	/**
	 * This is the default constructor
	 */
	public NumberFunctionPanel(BaseGeneratorUI gen) {
		super(gen);		 
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(435, 145);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getJPanel(), null);
		this.add(getJPanel1(), null);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setText("Ограничения");
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			jPanel = new JPanel();
			jPanel.setLayout(flowLayout);
			jPanel.add(jLabel, null);
			jPanel.add(getFunctSelect(), null);
			jPanel.add(getAddF1(), null);
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
			jPanel1.setLayout(new GridBagLayout());
		}
		return jPanel1;
	}

	/**
	 * This method initializes functSelect	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getFunctSelect() {
		if (functSelect == null) {
			functSelect = new JComboBox();
			functSelect.setModel(new TypeModell(number_cond));
			functSelect.setSelectedIndex(0);
		}
		return functSelect;
	}

	/**
	 * This method initializes addF1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddF1() {
		if (addF1 == null) {
			addF1 = new JButton();
			addF1.setText("Добавить");
			addF1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ListElement gen = (ListElement)functSelect.getSelectedItem();
					((DefaultListModel)(getGenerator().getFunctionList().getModel())).addElement((ComplexElement)gen.getGen());
				}
			});
		}
		return addF1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
