package ru.spb.ipo.generator.cards;

import javax.swing.DefaultComboBoxModel;

import ru.spb.ipo.generator.base.ListElement;
import ru.spb.ipo.generator.word.DiffGenerator;
import ru.spb.ipo.generator.word.NextGenerator;
import ru.spb.ipo.generator.word.SignGenerator;
import ru.spb.ipo.generator.word.WordCondition;

public class TypeModell extends DefaultComboBoxModel {

	public static final ListElement []  type_cards =  { 
		new ListElement("карт бубновой масти", new Generator("1")),
		new ListElement("карт червовой масти", new Generator("2")),
		new ListElement("карт пиковой масти", new Generator("3")),
		new ListElement("карт трефовой масти", new Generator("4")),
		new ListElement("красных карт", new Generator(new String [] {"1", "2"})),
		new ListElement("черных карт",  new Generator(new String [] {"3", "4"})),

		new ListElement("двоек", new SimpleGenerator("1", "1")),
		new ListElement("троек", new SimpleGenerator("2", "1")),
		new ListElement("четверок", new SimpleGenerator("3", "1")),
		new ListElement("пятерок", new SimpleGenerator("4", "1")),
		new ListElement("шестерок", new SimpleGenerator("5", "1")),
		new ListElement("семерок", new SimpleGenerator("6", "1")),
		new ListElement("восьмерок", new SimpleGenerator("7", "1")),
		new ListElement("девяток", new SimpleGenerator("8", "1")),
		new ListElement("десяток", new Generator("9", "1")),
		new ListElement("вальтов", new Generator("10", "1")),
		new ListElement("дам", new Generator("11", "1")),
		new ListElement("королей", new Generator("12", "1")),
		new ListElement("тузов", new Generator("13", "1"))
		//new ListElement("какой-либо масти", "7")		
	};
	
	public static final ListElement [] valueMast_cards =  { 
		new ListElement("бубей", new SimpleGenerator("1", "2")),
		new ListElement("червей", new SimpleGenerator("2", "2")),
		new ListElement("пик", new SimpleGenerator("3", "2")),
		new ListElement("треф", new SimpleGenerator("4", "2"))				
	};
	
	public static final ListElement [] valueType_cards =  { 
		new ListElement("двойка", new SimpleGenerator("1", "1")),
		new ListElement("тройка", new SimpleGenerator("2", "1")),
		new ListElement("четверка", new SimpleGenerator("3", "1")),
		new ListElement("пятерка", new SimpleGenerator("4", "1")),
		new ListElement("шестерка", new SimpleGenerator("5", "1")),
		new ListElement("семерка", new SimpleGenerator("6", "1")),
		new ListElement("восьмерка", new SimpleGenerator("7", "1")),
		new ListElement("девятка", new SimpleGenerator("8", "1")),
		new ListElement("десятка", new SimpleGenerator("9", "1")),
		new ListElement("валет", new SimpleGenerator("10", "1")),
		new ListElement("дама", new SimpleGenerator("11", "1")),
		new ListElement("король", new SimpleGenerator("12", "1")),
		new ListElement("туз", new SimpleGenerator("13", "1"))						
	};
	
	
	public static final ListElement []  word_cond =  { 
		new ListElement("Слово является палиндромом", new WordCondition("Слово является палиндромом")),
		new ListElement("Гласные и согласные чередуются", new DiffGenerator("Гласные и согласные чередуются")),
		new ListElement("После каждой согласной идет гласная", new NextGenerator("После каждой согласной идет гласная", 0)),
		new ListElement("После каждой гласной идет согласная", new NextGenerator("После каждой гласной идет согласная", 1)),
		new ListElement("Согласных меньше чем гласных", new SignGenerator(-1)),
		new ListElement("Согласных больше гласных", new SignGenerator(1)),
		new ListElement("Согласных столько же сколько гласных", new SignGenerator(0))				
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
