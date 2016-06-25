package ru.spb.ipo.taskgenerator.model;

public class KeyValue {

	private String key;
	
	private Object value;
	
	public KeyValue(String key, Object value) {
		this.key = key;
		this.value = value;		 
	}

	public String getValue() {
		return (String)value;
	}
	
	public Object getObject() {
		return value;
	}

	public String getKey() {
		return key;
	}

	public boolean equals(Object obj) {			
		if (obj instanceof KeyValue) {
			KeyValue o = (KeyValue) obj;
			if (!o.key.equals(key)) {
				return false;
			}			
			return true;
//			if (value == null) {
//				if (o.value == null) {
//					return true;
//				} else {
//					return false;
//				}
//			} else {
//				return value.equals(o.value);
//			}
		} else if (obj instanceof String) {
			return key.equals(obj);
		}
		return false;
	}

	public int hashCode() {		
		return key.hashCode() / 2 + (value != null ? value.hashCode() / 2 : 0);
	}
	
	
}
