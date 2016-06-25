package ru.spb.ipo.generator.equation;

import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.FuncUtil;

class IntervalElement implements ComplexElement {

	private int min = -1;
	private int max = -1;
	private int axis;
	private boolean isEqual;
	private boolean isValue;


	IntervalElement(int min, int max, int axis, boolean isValue) {
		this.max = max;
		this.min = min;
		this.axis = axis;
		this.isValue = isValue;
	}

	IntervalElement(int equal, int axis, boolean isValue) {
		this.min = equal;
		this.axis = axis;
		isEqual = true;
		this.isValue = isValue;
	}

	public String generateXml() {
		StringBuilder sb = new StringBuilder();

		if (!isEqual) {
			sb.append("<function type=\"Not\">\n");
		}

		if (max != -1) {
			sb.append("<function type=\"Greater\">\n");
		} else if (min != -1 && !isEqual) {
			sb.append("<function type=\"Smaller\">\n");
		} else if (isEqual) {
			sb.append("<function type=\"Equals\">\n");
		}
		sb.append(FuncUtil.projection("" + axis));
		if (isValue) {
			sb.append(FuncUtil.constElement("" + (max != -1 ? max : min)));
		} else {
			sb.append(FuncUtil.projection("" + (max != -1 ? max : min)));
		}

		sb.append("</function>\n");

		if (!isEqual) {
			sb.append("</function>\n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public String toDescription() {
		return toString();
	}

	public String toString() {
		if (min != -1 && max != -1) {
			return min +  " <=  x" + (axis ) + " <= " + max;
		} else {
			if (isEqual) {
				return "x" + (axis) + " == " + (isValue ? min : "x" + (min) + "");
			} else {
				if (min != -1) {
					return (isValue ? min : "x" + (min) + "") +  " <=  x" + (axis) + "";
				} else {
					return "x" + (axis) + " <= " + (isValue ? max : "x" + (max) + "");
				}
			}
		}

	}

}
