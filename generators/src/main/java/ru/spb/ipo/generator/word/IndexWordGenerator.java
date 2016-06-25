package ru.spb.ipo.generator.word;

import java.util.Map;

import javax.swing.JOptionPane;

import ru.spb.ipo.generator.base.BaseGenerator;
import ru.spb.ipo.generator.base.ListElement;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;

public class IndexWordGenerator extends WordGenerator {
	
	public IndexWordGenerator() {
//        initialize();
        ((WordFPanel) getFunctionPanel()).getIsSingle().setEnabled(false);
    }

	public String getEditorTitle() {
		//return "����������� ����� �� ���������� ����";
		return "�������� \"���������� ����\"";
	}
	
	protected ConstraintPanel getSetPanel() {
		if (functionPanel == null) {
			functionPanel = new IndexFPanel(this);
		}
		return functionPanel;
	}

	public static void main(String[] args) {
		new IndexWordGenerator().setVisible(true);
	}

	public BaseGenerator createGenerator(Map source, Map func, Map task) {		
		return new IndexXmlGenerator(source, func, task);
	}

	protected void fillParameters(Map source, Map func, Map task) {		
		super.fillParameters(source, func, task);
		//todo parse words
		String value = ((IndexFPanel) getFunctionPanel()).getWord().getText();
		StringBuffer sb = new StringBuffer("<indexingElement>");
		sb.append("<constElement>");
		ListElement [] letters = WordGenerator.getTokenList();
		for (int i = 0; i < value.length(); i++) {
			String s = "" + value.charAt(i);			
			ListElement res = null;
			for (int j = 0; j < letters.length; j++) {
				if (letters[j].toString().equals(s)) {
					res = letters[j];
					sb.append(res.generateXml());
					break;
				}
			}
			if (res == null) {
				//TODO
			}
		}
		sb.append("</constElement>");
		sb.append("</indexingElement>");
		func.put("indexingElement", sb.toString());
		func.put("indexingElementText", value);
	}
	
	protected boolean checkCanSave() {
	
		boolean canSave = super.checkCanSave();
		if (!canSave) {
			return false;
		}
		
		if (isEmpty(((IndexFPanel) getFunctionPanel()).getWord().getText())) {
			JOptionPane.showMessageDialog(this, "����� ��� ���������� �� ������" , "������� ������ �� ������", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	
	
}
