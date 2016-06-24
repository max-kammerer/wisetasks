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

import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.Utils;

import java.util.List;

/**
 * User: mike
 * Date: 12.12.2004
 */
abstract public class Function extends AbstractFunction {

    protected AbstractFunction[] fns;

    public void initFunction(Node node) throws TaskDeserializationException, SystemException {
        List<Node> ns = node.getFunctionList();
        fns = new AbstractFunction[ns.size()];
        for (int i = 0; i < ns.size(); i++) {
            fns[i] = AbstractFunction.generateAbstractFunction(ns.get(i));
        }
    }

    public static Function generateFunction(Node node) throws TaskDeserializationException, SystemException {
        String shortType = node.getAttr("type");
        String type = "ru.spb.ipo.engine.functions." + shortType;
        Function fn;
        try {
            fn = (Function) Class.forName(type).newInstance();
            fn.initFunction(node);
        } catch (ClassNotFoundException e) {
//    		throw new SystemException("Couldn't find class for function " + type, e);
            throw new TaskDeserializationException("Couldn't find class for function " + shortType, e);
        } catch (InstantiationException e) {
            throw new TaskDeserializationException("Couldn't find class for function " + shortType, e);
        } catch (IllegalAccessException e) {
            throw new TaskDeserializationException("Couldn't find class for function " + shortType, e);
        }
        return fn;
    }
}
