package ru.spb.ipo.generator.base.ui;

import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import ru.spb.ipo.generator.base.ComplexElement;

public class ConstraintPanel extends JPanel {

	private BaseGeneratorUI gen;
	
	public ConstraintPanel (BaseGeneratorUI gen) {
		this.gen = gen;
	}

    //add condition to condition list
    public void addCondition(ComplexElement element) {
		((DefaultListModel)gen.getFunctionList().getModel()).add(gen.getFunctionList().getModel().getSize(), element);
	}
	
	public ComplexElement [] getConditions() {
		return gen.getConditions();
	}

    //set current ui parameters to maps for task generation process
    public void fillMaps(Map<String, Object> source, Map<String, Object> func, Map<String, Object> task) {
		
	}

	public BaseGeneratorUI getGenerator() {
		return gen;
	}	
}
