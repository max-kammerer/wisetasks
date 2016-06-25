package ru.spb.ipo.taskgenerator.ui;

import javax.swing.table.DefaultTableModel;

public class AttributeTableModel extends DefaultTableModel {

	public static String [] columns = new String [] {"Аттрибут", "Значения"};
	
	public static int NAME_COLUMN = 0;
	public static int VALUE_COLUMN = 1;
	
	private static int DEFAULT_ROW_COUNT = 5;
	private int myRowCount = -1;
	
	public AttributeTableModel() {
		super(DEFAULT_ROW_COUNT, 2);
		myRowCount = DEFAULT_ROW_COUNT;
	}
	public int getColumnCount() {		
		return columns.length;
	}

	public String getColumnName(int column) {		
		return columns[column];
	}

	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return super.getValueAt(row, column);
	}

	public void updateTable(String [][] keys) {
		if ((keys == null || keys.length == 0)/* && (values == null || values.length == 0)*/) {
			setClear(DEFAULT_ROW_COUNT);			
			return;
		}
		setDataVector(keys, columns);
		if (keys.length < DEFAULT_ROW_COUNT) {
			for (int i = 0; i < DEFAULT_ROW_COUNT - keys.length - 1; i++) {
				addRow(EMPTY_ROW);
			}
		}		
		addRow(EMPTY_ROW);
	}
	
	public void setClear(int rowsNumber) {		
		setDataVector(null, columns);
		setRowCount(rowsNumber);	
	}
	private static String [][] EMPTY_ROW = new String [0][0];
}
