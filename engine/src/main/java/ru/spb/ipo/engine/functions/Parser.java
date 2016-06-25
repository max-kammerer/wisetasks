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

import ru.spb.ipo.engine.elements.RationalElement;
import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.RationalNumber;

public class Parser extends Function {

    private RationalElement fe;

    public void initFunction(Node node) {
        Map m = getAttributes(node);
        RationalNumber mod = (RationalNumber) m.get("mod");
        if (m.containsKey("exp")) {
            RationalNumber exp = (RationalNumber) m.get("exp");
            exp = mod == null ? exp : new RationalNumber(exp.getBigInteger().mod(mod.getBigInteger()));
            fe = new RationalElement(exp);
        } else {
            fe = new RationalElement(new RationalNumber(0));
        }
    }

    public Element compute(Element e) {
        return fe;
    }
}
