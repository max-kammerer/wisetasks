package ru.spb.ipo.generator.cards;

import javax.swing.DefaultComboBoxModel;

import ru.spb.ipo.generator.base.ListElement;
import ru.spb.ipo.generator.word.DiffGenerator;
import ru.spb.ipo.generator.word.NextGenerator;
import ru.spb.ipo.generator.word.SignGenerator;
import ru.spb.ipo.generator.word.WordCondition;

public class TypeModell extends DefaultComboBoxModel {

	public static final ListElement []  type_cards =  { 
		new ListElement("���� �������� �����", new Generator("1")),
		new ListElement("���� �������� �����", new Generator("2")),
		new ListElement("���� ������� �����", new Generator("3")),
		new ListElement("���� �������� �����", new Generator("4")),
		new ListElement("������� ����", new Generator(new String [] {"1", "2"})),
		new ListElement("������ ����",  new Generator(new String [] {"3", "4"})),

		new ListElement("�����", new SimpleGenerator("1", "1")),
		new ListElement("�����", new SimpleGenerator("2", "1")),
		new ListElement("��������", new SimpleGenerator("3", "1")),
		new ListElement("�������", new SimpleGenerator("4", "1")),
		new ListElement("��������", new SimpleGenerator("5", "1")),
		new ListElement("�������", new SimpleGenerator("6", "1")),
		new ListElement("���������", new SimpleGenerator("7", "1")),
		new ListElement("�������", new SimpleGenerator("8", "1")),
		new ListElement("�������", new Generator("9", "1")),
		new ListElement("�������", new Generator("10", "1")),
		new ListElement("���", new Generator("11", "1")),
		new ListElement("�������", new Generator("12", "1")),
		new ListElement("�����", new Generator("13", "1"))
		//new ListElement("�����-���� �����", "7")		
	};
	
	public static final ListElement [] valueMast_cards =  { 
		new ListElement("�����", new SimpleGenerator("1", "2")),
		new ListElement("������", new SimpleGenerator("2", "2")),
		new ListElement("���", new SimpleGenerator("3", "2")),
		new ListElement("����", new SimpleGenerator("4", "2"))				
	};
	
	public static final ListElement [] valueType_cards =  { 
		new ListElement("������", new SimpleGenerator("1", "1")),
		new ListElement("������", new SimpleGenerator("2", "1")),
		new ListElement("��������", new SimpleGenerator("3", "1")),
		new ListElement("�������", new SimpleGenerator("4", "1")),
		new ListElement("��������", new SimpleGenerator("5", "1")),
		new ListElement("�������", new SimpleGenerator("6", "1")),
		new ListElement("���������", new SimpleGenerator("7", "1")),
		new ListElement("�������", new SimpleGenerator("8", "1")),
		new ListElement("�������", new SimpleGenerator("9", "1")),
		new ListElement("�����", new SimpleGenerator("10", "1")),
		new ListElement("����", new SimpleGenerator("11", "1")),
		new ListElement("������", new SimpleGenerator("12", "1")),
		new ListElement("���", new SimpleGenerator("13", "1"))						
	};
	
	
	public static final ListElement []  word_cond =  { 
		new ListElement("����� �������� �����������", new WordCondition("����� �������� �����������")),
		new ListElement("������� � ��������� ����������", new DiffGenerator("������� � ��������� ����������")),
		new ListElement("����� ������ ��������� ���� �������", new NextGenerator("����� ������ ��������� ���� �������", 0)),
		new ListElement("����� ������ ������� ���� ���������", new NextGenerator("����� ������ ������� ���� ���������", 1)),
		new ListElement("��������� ������ ��� �������", new SignGenerator(-1)),
		new ListElement("��������� ������ �������", new SignGenerator(1)),
		new ListElement("��������� ������� �� ������� �������", new SignGenerator(0))				
	};

	
	private ListElement [] cards;
	
	public TypeModell(ListElement [] cards) {
		this.cards = cards;
	}
		
	public int getSize() {		
		return cards.length;
	}

	public Object getElementAt(int index) {		
		return cards[index];
	}
	
	public static class Generator {
		
		private Object value;
		private String axis;
		
		public Generator(Object value) {
			this(value, "2");
		}
		
		Generator(Object value, String axis) {
			this.value = value;
			this.axis = axis;
		}
		
		protected String generateCond(String value) {
			StringBuffer sb = new StringBuffer();
			sb.append("<function type=\"Equals\">\n");
			sb.append("	<function type=\"Projection\" axis=\"" + getAxis() + "\">\n");
			sb.append("		<function type=\"Projection\" axis=\"${i}\">\n");			
			sb.append("			<current-set-element/>\n");
			sb.append("		</function>\n");
			sb.append("	</function>\n");
			sb.append("	<constElement>" + value + "</constElement>\n");
			sb.append("</function>\n");
			return sb.toString();
		}
		
		public String generateXml() {
			String str =  "<function type=\"Add\">\n";
			StringBuffer sb = new StringBuffer();
			sb.append(str);
			sb.append("<for name=\"i\" first=\"1\" last=\"${length}\" inc=\"1\">\n");
			
            if (value instanceof String) {            	
        		sb.append(generateCond((String)value));            	
            } else {
            	String [] d1 = (String []) value;
            	sb.append("<function type=\"Or\">\n");
            	sb.append(generateCond((String)d1[0]));
            	sb.append(generateCond((String)d1[1]));
            	sb.append("</function>\n");
            }			
			
			sb.append("</for>");			
			sb.append("</function>\n");			
			
			return sb.toString();
		}

		protected String getAxis() {
			return axis;
		}

		public Object getValue() {
			return value;
		}
	}
	
	public static class AnyTypeGenerator extends Generator {

		public AnyTypeGenerator() {
			super("", "");
		}
		
		public String generateXml() {			
        	StringBuffer sb = new StringBuffer();        		
            sb.append("<function type=\"Count\" axis=\"2\">\n");
            sb.append("	<current-set-element/>\n");        		
        	sb.append("</function>\n");
        	return sb.toString();            
		}
	}
	
	public static class SimpleGenerator extends Generator {

		public SimpleGenerator(String value, String axis) {
			super(value, axis);
		}
		
		
		public String generateXml() {
			return generateCond((String)getValue());						
		}			
	}
}
