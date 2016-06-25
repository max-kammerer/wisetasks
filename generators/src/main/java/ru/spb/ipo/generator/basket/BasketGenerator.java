package ru.spb.ipo.generator.basket;

import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BasketGenerator extends BaseGeneratorUI {

	public BasketGenerator(){
		initialize();
	}

    protected Dimension getGeneratorSize() {
        return new Dimension(800, 450);
    }

    protected boolean checkCanSave() {
		if (getFunctionList().getModel().getSize() == 0) {
			JOptionPane.showMessageDialog(this, "Не указаны шары, вытаскиваемые из урны!" , "Не указаны шары, вытаскиваемые из урны", JOptionPane.WARNING_MESSAGE);
			return false;			
		}
		return super.checkCanSave();
	}


	protected ConstraintPanel getSetPanel() {
		if (setPanel == null) {
			setPanel = new BasketSetPanel();
		}
		return setPanel;
		
	}

	protected ConstraintPanel getFunctionPanel() {
		if (functionPanel == null) {
			functionPanel = new BasketConditions(this);
		}		
		return functionPanel;
	}
	
	public static void main(String[] args) {
		new BasketGenerator().setVisible(true);
	}
	
	public BaseGenerator createGenerator(Map source, Map func, Map task) {
		return new BasketXmlGenerator(source, func, task);
	}

	
	protected void clear() {
		((BasketSetPanel) getSetPanel()).clear();
		super.clear();
	}

	public String getEditorTitle() {
		return "Редактор \"Шары и урны\"";
	}


	protected Dimension getRightPanelDimension() {
		return new Dimension(305, 190);
	}
	
	
	
}
