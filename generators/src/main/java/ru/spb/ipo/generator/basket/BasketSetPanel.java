package ru.spb.ipo.generator.basket;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import java.util.Map;

import javax.swing.JCheckBox;

import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.UIUtils;

public class BasketSetPanel extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JCheckBox jCheckBox = null;
	private JPanel jPanel3 = null;
	private BallChoicePanel bcpanel1;
	private BallChoicePanel bcpanel2;
	/**
	 * This is the default constructor
	 */
	public BasketSetPanel() {
		super(null);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//this.setSize(511, 172);
		this.setLayout(new BorderLayout());
		this.add(getJPanel(), BorderLayout.CENTER);
		setMinimumSize(new Dimension(350, 110));
		setPreferredSize(new Dimension(300, 110));
		jCheckBox.doClick();
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getJPanel1(), null);			
			//jPanel.add(getJPanel2(), null);
			getJPanel2();
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
			jPanel1.setLayout(new BoxLayout(getJPanel1(), BoxLayout.Y_AXIS));
			jPanel1.setBorder(BorderFactory.createTitledBorder(null, "\u041e\u0441\u043d\u043e\u0432\u043d\u0430\u044f \u043a\u043e\u0440\u0437\u0438\u043d\u0430", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			bcpanel1 = new BallChoicePanel();
			jPanel1.add(bcpanel1);
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
			jPanel2.setLayout(new BoxLayout(getJPanel2(), BoxLayout.Y_AXIS));			
			jPanel2.setBorder(BorderFactory.createTitledBorder(null, "\u0414\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u0430\u044f \u043a\u043e\u0440\u0437\u0438\u043d\u0430", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel2.add(getJPanel3(), null);
			bcpanel2 = new BallChoicePanel(); 
			jPanel2.add(bcpanel2, null);
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
			jCheckBox.setText("Дополнительная корзина");
			jCheckBox.setSelected(true);			
			jCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					bcpanel2.setEnabled(jCheckBox.isSelected());
					UIUtils.enableAll(bcpanel2, jCheckBox.isSelected());
					
				}
			});			
		}
		return jCheckBox;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			jPanel3 = new JPanel();
			jPanel3.setLayout(flowLayout);
			jPanel3.add(getJCheckBox(), null);
		}
		return jPanel3;
	}
	
	public void fillMaps(Map<String, Object> source, Map<String, Object> func, Map<String, Object> task) {
		source.put("isSingle", !jCheckBox.isSelected());
		source.put("basket1", bcpanel1.getChoices());
		source.put("basket2", bcpanel2.getChoices());
	}

	protected void clear() {
		bcpanel1.clear();
		bcpanel2.clear();
	}	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
