package ru.spb.ipo.generator.numbers;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JPanel;

import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

public class NumberGenerator extends BaseGeneratorUI {

	private JPanel detailPanel = null;

    public NumberGenerator() {
        initialize();
    }

    protected Dimension getGeneratorSize() {
        return new Dimension(920, 430);
    }

	/**
	 * This method initializes detailPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDetailPanel() {
		if (detailPanel == null) {
			detailPanel = new DetailDigitPanel(this);			
		}
		return detailPanel;
	}

	public static void main(String[] args) {
		new NumberGenerator().setVisible(true);
	}

	
	protected ConstraintPanel getFunctionPanel() {
		if (functionPanel == null) {
			functionPanel = new NumberFunctionPanel(this);
			functionPanel.add(getDetailPanel());
		}
		return functionPanel;
	}

	
	protected ConstraintPanel getSetPanel() {
		if (setPanel == null) {
			setPanel = new NumberSetPanel(this);
		}
		return setPanel;
	}
	
	public BaseGenerator createGenerator(Map source, Map func, Map task) {
		return new NumberXmlGenerator(source, func, task);
	}

	protected void fillParameters(Map source, Map func, Map task) {
		source.put("nabor", ((NumberSetPanel) getSetPanel()).getNaborSize().getSelectedItem());
		source.put("isNumber", ((NumberSetPanel) getSetPanel()).isNumber());
		source.put("maxDigit", ((NumberSetPanel) getSetPanel()).getMaxDigit().getSelectedItem());
	}

	public String getEditorTitle() {
		//return "Конструктор задач с наборами цифр";
		return "Редактор \"Упорядоченные числовые наборы\"";
	}
	
	private static BaseGeneratorUI instance = null;
	
	public static BaseGeneratorUI getInstance() {
		if (instance == null) {
			return instance = new NumberGenerator();
		}
		return instance;
	}

	protected void clear() {
		super.clear();
		((DetailDigitPanel)getDetailPanel()).getClearCond().doClick();		
	}
	
}
