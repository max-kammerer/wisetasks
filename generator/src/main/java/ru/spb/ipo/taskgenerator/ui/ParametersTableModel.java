package ru.spb.ipo.taskgenerator.ui;

public class ParametersTableModel extends AttributeTableModel{

	public static int VALUE_COLUMN2 = 0;
	public static int DESCRIPTION_COLUMN = 1;
	
	private String [] columns = new String[] {"Значение", "Описание"};
	
	public String getColumnName(int column) {		
		return columns[column];
	}

	
}
