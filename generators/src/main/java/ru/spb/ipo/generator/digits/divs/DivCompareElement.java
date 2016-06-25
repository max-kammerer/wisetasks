package ru.spb.ipo.generator.digits.divs;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.FuncUtil;

public class DivCompareElement implements ComplexElement {

	private int result;
	private int compare;
	private String desc;
	
	public DivCompareElement(int res, int compare, String desc) {
		this.result = res;
		this.desc = desc;
		this.compare = compare;
	}
	
	
	public String generateXml() {
		StringBuffer sb = new StringBuffer();
		String shift = "${shift}"; 
		if (result == 0) {
			sb.append(FuncUtil.equals(shift, FuncUtil.cse()));			
		} else {			
			String mod = "";
			if (result == 1) {
				mod = FuncUtil.func("Mod", FuncUtil.func("ToDigit", shift), FuncUtil.func("ToDigit", FuncUtil.cse()));				
			} else {
				mod = FuncUtil.func("Mod", FuncUtil.func("ToDigit", FuncUtil.cse()), FuncUtil.func("ToDigit", shift));
			}
			String isMod = FuncUtil.equals(mod, FuncUtil.constElement(0));
			
			
			String div = "";
			if (result == 1) {
				div = FuncUtil.func("Div", FuncUtil.func("ToDigit", shift), FuncUtil.func("ToDigit", FuncUtil.cse()));				
			} else {
				div = FuncUtil.func("Div", FuncUtil.func("ToDigit", FuncUtil.cse()), FuncUtil.func("ToDigit", shift));
			}
			String isDiv = FuncUtil.equals(div, FuncUtil.constElement(compare == -1 ? 1 : compare ));

			//not same for 
			if (compare == -1) {
				isDiv = FuncUtil.func("Not", isDiv);
			}
			
			sb.append(FuncUtil.func("And", isMod, isDiv));
			
		}
		
		return sb.toString();
	}

	public String toDescription() {
		
		if (compare == -1) {
			return desc;
		} else {
			return desc + " " + compare + "  раз";
		}
	}

	public String toString() {
		return toDescription();
	}

}
