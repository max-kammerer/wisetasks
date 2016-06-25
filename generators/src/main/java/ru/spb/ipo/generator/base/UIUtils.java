package ru.spb.ipo.generator.base;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;

import ru.spb.ipo.generator.basket.BasketXmlGenerator;

public class UIUtils {

	public static void enableAll(JComponent comp, boolean enable) {
		comp.setEnabled(enable);
		Component [] cs = comp.getComponents();
		for (Component c : cs) {
			if (c instanceof JComponent) {
				c.setEnabled(enable);
				enableAll(((JComponent) c), enable);
			}
		}
	}
	
	public static Color getColor(int color) {
		return BasketXmlGenerator.colors[color];		
	}
}
