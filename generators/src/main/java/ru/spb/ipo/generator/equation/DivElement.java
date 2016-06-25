package ru.spb.ipo.generator.equation;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.FuncUtil;

public class DivElement implements ComplexElement {

	private int mod, axis;
	private boolean isValue;
	
	public DivElement(int mod, int axis, boolean isValue) {		
		this.mod = mod;
		this.axis = axis;
		this.isValue = isValue;
	}
	
	public String generateXml() {
		StringBuffer sb = new StringBuffer();		
		sb.append("<function type=\"Equals\">\n");		
		sb.append("<function type=\"Mod\" base=\"" + mod + "\">\n");
		sb.append(FuncUtil.projection(axis));		
		sb.append("</function>\n");
		if (isValue) {
			sb.append(FuncUtil.constElement(mod));
		} else {
			//sb.append(FuncUtil.projection());			
		}		
		sb.append("</function>\n");		
		return sb.toString();
	}

	public String toDescription() {		
		return toString();
	}

	public String toString() {		
		return "x" + (axis) + " делится на " + mod;
	}
	
}