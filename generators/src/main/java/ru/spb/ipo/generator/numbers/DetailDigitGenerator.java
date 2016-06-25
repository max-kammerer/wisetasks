package ru.spb.ipo.generator.numbers;

import ru.spb.ipo.generator.base.ComplexElement;

public class DetailDigitGenerator implements ComplexElement {
		
		private String cond;
		private String [] left;
		
		private String [] right;
		
		public DetailDigitGenerator(String [] left, String [] right, String cond) {
			this.cond = cond;
			this.left = left;
			this.right = right;
		}
		
		public String toString() {
			String s = "";
			switch (cond.charAt(0)) {
				case '<': s = "меньше"; break;
				case '=': s = "равно"; break;
				case '>': s = "больше"; break;		
			}
			return toString(left, "+") + " " + cond + " " + toString(right, "+");		
		}
		
		public String toDescription() {
			String s = "";
			switch (cond.charAt(0)) {
				case '<': s = "меньше"; break;
				case '=': s = "равна"; break;
				case '>': s = "больше"; break;		
			}
			return "сумма элементов   " + toString(left, ", ") + " " + s + " сумм" + (cond.equals("=") ? "е" : "ы") + " элементов " + toString(right, ", ");		
		}
		
		public String generateXml() {
			StringBuffer sb = new StringBuffer("<function type=");
			String func = "";
			switch (cond.charAt(0)) {
				case '<': func = "Smaller"; break;
				case '=': func = "Equals"; break;
				case  '>': func = "Greater"; break;
			}
			sb.append("\"" + func + "\">\n");
			
			sb.append(forPart(left) +"\n");
		
			sb.append(forPart(right) +"\n");
			sb.append("</function>");
			return sb.toString();		
		}
		
		String toString (String [] str, String del) {
			String res = "";
			for (int i = 0; i < str.length; i++) {
				if ("+".equals(del)) {
					res += "[";
				}
				res += str[i];
				if ("+".equals(del)) {
					res += "]";
				}
				if (i < str.length - 1) {
					res += del;
				}
			}
			return res;
		}

		public String forOne(String id) {
			StringBuffer sb = new StringBuffer();					
			sb.append("<function type=\"Projection\" axis=\"" + id+ "\">\n");			
			sb.append("		<current-set-element/>\n");
			sb.append("</function>\n");	
			return sb.toString();
		}
		
		public String forPart(String [] ids) {
			StringBuffer sb = new StringBuffer();
			sb.append("<function type=\"Add\">\n");
			for (int i = 0; i < ids.length; i++) {
				sb.append(forOne(ids[i]));
			}
			sb.append("</function>\n");	
			return sb.toString();
		}
}
