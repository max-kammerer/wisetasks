package ru.spb.ipo.generator.word;

public class SignGenerator extends WordCondition {

	private int sign;
	
	public SignGenerator(int sign) {
		super(null);
		this.sign = sign;
	}

	public String generateXml() {
		StringBuffer sb = new StringBuffer();
		String type = "";
		switch (sign) {
		case -1:
			type = "Smaller";			
			break;

		case 0:
			type = "Equals";
			break;
			
		case 1:
			type = "Greater";
			break;
		}

		
		sb.append("<function type=\"" + type + "\">\n");		
		
		sb.append("<function type=\"Add\">\n");
		sb.append("<for name=\"i\" first=\"1\" last=\"${length}\" inc=\"1\">\n");
		sb.append("<function type=\"Equals\">\n");		
		sb.append("<function type=\"Projection\" axis=\"2\">\n");
		sb.append("<function type=\"Projection\" axis=\"${i}\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		sb.append("</function>\n");
		sb.append("<constElement>0</constElement>\n");		
		sb.append("</function>\n");		
		sb.append("</for>");
		sb.append("</function>\n");
		
		sb.append("<function type=\"Add\">\n");
		sb.append("<for name=\"i\" first=\"1\" last=\"${length}\" inc=\"1\">\n");
		sb.append("<function type=\"Equals\">\n");		
		sb.append("<function type=\"Projection\" axis=\"2\">\n");
		sb.append("<function type=\"Projection\" axis=\"${i}\">\n");			
		sb.append("		<current-set-element/>\n");
		sb.append("</function>\n");
		sb.append("</function>\n");
		sb.append("<constElement>1</constElement>\n");		
		sb.append("</function>\n");                	
		sb.append("</for>");
		sb.append("</function>\n");
		
		sb.append("</function>\n");
		
		return sb.toString();
	}

	public String toDescription() {
		String s = "";
		switch (sign) {
		case -1:
			s = "меньше чем";
			break;

		case 0:
			s = "столько же сколько";
			break;
			
		case 1:
			s = "больше чем";
			break;
		}
		return "Согласных " + s + " гласных";		
	}	
}
