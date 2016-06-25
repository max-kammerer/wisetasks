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

import ru.spb.ipo.engine.elements.Element;

public class Equals extends Function {

    public Element compute(Element parameter) {
        if ((fns[0] == null) || (fns[1] == null)) System.err.println("fns [0] or [1] is null");

        if (fns[0].compute(parameter).equals(fns[1].compute(parameter))) return Element.ptrue;
        else return Element.pfalse;
    }
}
