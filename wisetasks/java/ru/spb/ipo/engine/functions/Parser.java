/*
 * This file is part of Wisetasks
 *
 * Copyright (C) 2006-2008, 2012  Michael Bogdanov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.spb.ipo.engine.functions;

import java.util.Map;

import ru.spb.ipo.engine.elements.FractionalElement;
import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.FractionalNumber;

public class Parser extends Function {

	FractionalElement fe;

	public void initFunction(Node node){
		Map m = super.getAttributes(node);
		FractionalNumber mod = (FractionalNumber)m.get("mod");		
		if (m.containsKey("exp")) {
			FractionalNumber exp = (FractionalNumber)m.get("exp");
			exp = mod == null ? exp : new FractionalNumber(exp.getBigInteger().mod(mod.getBigInteger()));
			fe = new FractionalElement(exp);
		} else fe = new FractionalElement(new FractionalNumber(0));
	}
	
	public Element compute(Element e) {		
		return fe;
	}
}
