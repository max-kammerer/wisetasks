package ru.spb.ipo.generator.word;

public class SignGenerator extends WordCondition {

	private int sign;
	
	public SignGenerator(int sign) {
		super(null);
		this.sign = sign;
	}

	public String generateXml() {
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

		return ("<function type=\"" + type + "\">\n") +
				"<function type=\"Add\">\n" +
				"<for name=\"i\" first=\"1\" last=\"${length}\" inc=\"1\">\n" +
				"<function type=\"Equals\">\n" +
				"<function type=\"Projection\" axis=\"2\">\n" +
				"<function type=\"Projection\" axis=\"${i}\">\n" +
				"		<current-set-element/>\n" +
				"</function>\n" +
				"</function>\n" +
				"<constElement>0</constElement>\n" +
				"</function>\n" +
				"</for>" +
				"</function>\n" +
				"<function type=\"Add\">\n" +
				"<for name=\"i\" first=\"1\" last=\"${length}\" inc=\"1\">\n" +
				"<function type=\"Equals\">\n" +
				"<function type=\"Projection\" axis=\"2\">\n" +
				"<function type=\"Projection\" axis=\"${i}\">\n" +
				"		<current-set-element/>\n" +
				"</function>\n" +
				"</function>\n" +
				"<constElement>1</constElement>\n" +
				"</function>\n" +
				"</for>" +
				"</function>\n" +
				"</function>\n";
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
