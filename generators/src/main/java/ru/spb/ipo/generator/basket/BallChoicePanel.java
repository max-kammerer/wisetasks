package ru.spb.ipo.generator.basket;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.UIUtils;

public class BallChoicePanel extends JPanel {
	
	HashMap<Integer, ComplexElement> ces = new HashMap<Integer, ComplexElement>();  //  @jve:decl-index=0:
	ArrayList<ComplexElement> cesList = new ArrayList<ComplexElement>();
	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JButton addButton = null;
	protected JPanel choiceContent = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	JComboBox color, number;

	public BallChoicePanel() {	
		super();
		initialize();	
	}
	
	protected ComplexElement getElementToAdd() {
		JPanel panel = getChoiceContent();
		JComboBox cb1 = (JComboBox)panel.getComponent(0);
		JComboBox cb2 = (JComboBox)panel.getComponent(2);
		try {
			return new BasketBallComplexElement(cb2.getSelectedIndex(), Integer.valueOf(cb1.getSelectedItem().toString()));
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected JPanel getChoiceContent() {
		if (choiceContent == null) {
			choiceContent = new JPanel();
			choiceContent.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));			
			
			number = new JComboBox(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
			choiceContent.add(number);
			choiceContent.add(new JLabel(""));
			color = new JComboBox(new DefaultComboBoxModel(BasketXmlGenerator.ballColors));
			choiceContent.add(color);
			choiceContent.add(new JLabel(""));
		}
		return choiceContent;
	}
	
	

	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		BorderLayout borderLayout1 = new BorderLayout();
		borderLayout1.setVgap(5);
		//setMinimumSize(new Dimension(0, 0));
		//setMaximumSize(new Dimension(520, 120));
		this.setLayout(borderLayout1);		
		this.add(getJPanel(), BorderLayout.NORTH);
		this.add(getJPanel2(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(5);
			borderLayout.setVgap(0);
			jPanel = new JPanel();
			jPanel.setLayout(borderLayout);
			jPanel.add(getChoiceContent(), BorderLayout.CENTER);
			jPanel.add(getJPanel1(), BorderLayout.EAST);
		}
		return jPanel;
	}

	/**
	 * This method initializes addButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("Добавить");
			addButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int color1 = color.getSelectedIndex();
					int number1 = Integer.valueOf(number.getSelectedItem().toString());
					BasketBallComplexElement ce = new BasketBallComplexElement(color1, number1);
					ComplexElement old = ces.put(color1, ce);
					int index = cesList.size();
					JPanel nPanel = new JPanel(new FlowLayout());
					nPanel.setBackground(UIUtils.getColor(color1));
					nPanel.add(new JLabel("" + number1));					
					if (old != null) {
						index = cesList.indexOf(old);
						cesList.remove(index);
						getJPanel2().remove(index);
					} 
					cesList.add(index, ce);
					getJPanel2().add(nPanel, index);
					getJPanel2().revalidate();
				}
			});
		}
		return addButton;
	}

	protected ComplexElement [] getChoices() {
		return cesList.toArray(new ComplexElement[cesList.size()]);		
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(5);
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout);
			jPanel1.add(getAddButton(), null);
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
			jPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));			
		}
		return jPanel2;
	}

	public void clear() {
		ces.clear();
		cesList.clear();
		getJPanel2().removeAll();
		getJPanel2().revalidate();
	}
	
}
