package ru.spb.ipo.generator.equation;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.FuncUtil;
import ru.spb.ipo.generator.base.ui.ValidatedTextField;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import java.util.Map;

public class EquationSetPanel extends ConstraintPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JComboBox xNumber = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField resultNumber = null;

	/**
	 * This is the default constructor
	 */
	public EquationSetPanel(BaseGeneratorUI generator) {
		super(generator);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(475, 74);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(null, "\u0411\u0430\u0437\u043e\u0432\u044b\u0435 \u043e\u0433\u0440\u0430\u043d\u0438\u0447\u0435\u043d\u0438\u044f", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
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
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJPanel1(), null);
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
			flowLayout1.setAlignment(FlowLayout.RIGHT);
			jLabel = new JLabel();
			jLabel.setText("Количество неизвестных");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.weightx = 1.0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(flowLayout1);
			jPanel1.add(jLabel, null);
			jPanel1.add(getXNumber(), null);
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
			flowLayout.setAlignment(FlowLayout.LEFT);
			jLabel1 = new JLabel();
			jLabel1.setText("Конечная сумма");
			jPanel2 = new JPanel();
			jPanel2.setLayout(flowLayout);
			jPanel2.add(jLabel1, null);
			jPanel2.add(getResultNumber(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes xNumber	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getXNumber() {
		if (xNumber == null) {
			xNumber = new JComboBox(new DefaultComboBoxModel(new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"}));
			xNumber.setSelectedIndex(2);
		}
		return xNumber;
	}

	/**
	 * This method initializes resultNumber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getResultNumber() {
		if (resultNumber == null) {			
			resultNumber = new ValidatedTextField(50);
			resultNumber.setColumns(3);
			resultNumber.setText("25");
			resultNumber.setToolTipText("Введите число от 0 до 50");
		}
		return resultNumber;
	}

	public void fillMaps(Map<String, Object> source, Map<String, Object> func, Map<String, Object> task) {
		source.put("resultX", resultNumber.getText());
		source.put("nabor", xNumber.getSelectedItem());
		String function = (String) func.get("function");
		if (function == null) {
			function = "";
		}

		String sb =
				"<function type=\"Equals\">\n" +
					FuncUtil.constElement("${result}") +
				"	<function type=\"Add\">\n" +
				"		<for name=\"i\" first=\"1\" last=\"${length}\" inc=\"1\">\n" +
							FuncUtil.projection("${i}") +
				"		</for>\n" +
				"	</function>\n" +
				"</function>\n";

		function = function + "\n" + sb;
		func.put("function", function);		
		
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
