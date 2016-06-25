package ru.spb.ipo.generator.word;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.ListElement;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;
import ru.spb.ipo.generator.cards.TypeModel;

class WordFPanel extends ConstraintPanel {

	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel = null;
	private JComboBox wordCondiion = null;
	private JButton jButton = null;
	
	private JCheckBox isSingle = null;
	/**
	 * This is the default constructor
	 */
	public WordFPanel(BaseGeneratorUI gen) {
		super(gen);		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(441, 81);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(null, "\u041e\u0433\u0440\u0430\u043d\u0438\u0447\u0435\u043d\u0438\u044f", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
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
			jLabel.setText("  Ограничения  ");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(jLabel, null);
			jPanel.add(getWordCondiion(), null);
			jPanel.add(Box.createHorizontalStrut(5));
			jPanel.add(getJButton(), null);
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
			flowLayout.setAlignment(FlowLayout.LEFT);
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout);
			jPanel1.add(getIsSingle(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes wordCondiion	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getWordCondiion() {
		if (wordCondiion == null) {
			wordCondiion = new JComboBox();
			wordCondiion.setModel(new TypeModel(TypeModel.word_cond));
			wordCondiion.setSelectedIndex(0);
		}
		return wordCondiion;
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
					ListElement gen = (ListElement)wordCondiion.getSelectedItem();
					((DefaultListModel)(getGenerator().getFunctionList().getModel())).addElement((ComplexElement)gen.getGen());
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes isSingle	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	JCheckBox getIsSingle() {
		if (isSingle == null) {
			isSingle = new JCheckBox();
			isSingle.setText("Буквы могут повторяться");
			isSingle.setSelected(true);
		}
		return isSingle;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
