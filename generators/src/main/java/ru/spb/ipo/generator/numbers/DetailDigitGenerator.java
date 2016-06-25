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
			StringBuilder sb = new StringBuilder("<function type=");
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
		
		private String toString(String[] str, String del) {
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

		private String forOne(String id) {
			return "<function type=\"Projection\" axis=\"" + id + "\">\n" +
					"		<current-set-element/>\n" +
					"</function>\n";
		}
		
		private String forPart(String[] ids) {
			StringBuilder sb = new StringBuilder();
			sb.append("<function type=\"Add\">\n");
			for (String id : ids) {
				sb.append(forOne(id));
			}
			sb.append("</function>\n");	
			return sb.toString();
		}
}
