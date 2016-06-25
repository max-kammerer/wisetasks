package ru.spb.ipo.generator.digits.divs;

import java.util.Map;

import javax.swing.JOptionPane;

import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

public class DivGenerator extends BaseGeneratorUI {

	public DivGenerator() {
		super();
		initialize();
	}
	
	public static void main(String[] args) {
		new DivGenerator().setVisible(true);
	}
    
    protected ConstraintPanel getFunctionPanel() {
		if (functionPanel == null) {
			functionPanel = new DropDigitPanel(this);
		}
		return functionPanel;
	}

	protected ConstraintPanel getSetPanel() {
		if (setPanel == null) {
			setPanel = new DivSetPanel(this);
		}
		return setPanel;
	}

	public String getEditorTitle() {
		return "Редактор \"Задачи на делимость\"";
	}

	public BaseGenerator createGenerator(Map source, Map func, Map task) {		
		return new DivXmlGenerator(source, func, task);
	}
	
	protected boolean checkCanSave() {		
		boolean res = super.checkCanSave();
		if (!res) {
			return res;
		}
		if (getFunctionList().getModel().getSize() == 0) {
			JOptionPane.showMessageDialog(this, "Не выбрано ни одного условия!" , "Не выбрано ни одного условия!", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return ((DropDigitPanel) getFunctionPanel()).checkCanSave();
	}

	protected void clear() {				
		super.clear();
		((DropDigitPanel) getFunctionPanel()).clear();
	}
}
