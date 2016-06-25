package ru.spb.ipo.generator.digits.divs;

import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

public class DivSetPanel extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel = null;
	private JComboBox numberSize = null;

	/**
	 * This is the default constructor
	 */
	public DivSetPanel(BaseGeneratorUI gen) {
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
		jLabel.setText("Количество цифр в числе");
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.setLayout(flowLayout);
		this.setSize(428, 200);
		this.add(jLabel, null);
		this.add(getNumberSize(), null);
	}

	/**
	 * This method initializes numberSize	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getNumberSize() {
		if (numberSize == null) {
			numberSize = new JComboBox(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6", "7", "8"}));
			numberSize.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					int i = numberSize.getSelectedIndex() + 2;
					((DropDigitPanel)((DivGenerator)getGenerator()).getFunctionPanel()).reCreateNumbers(i);
				}
			});
			numberSize.setSelectedIndex(6);
		}
		return numberSize;
	}

	public void fillMaps(Map source, Map func, Map task) {
		source.put("nabor", getNumberSize().getSelectedItem());
		super.fillMaps(source, func, task);
	}

	
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
