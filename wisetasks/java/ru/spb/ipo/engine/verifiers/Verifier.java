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

package ru.spb.ipo.engine.verifiers;

import java.lang.reflect.InvocationTargetException;

import ru.spb.ipo.client.ui.ClientUI;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.task.TaskConstant;
import ru.spb.ipo.engine.utils.FractionalNumber;
import ru.spb.ipo.engine.utils.Utils;

/**
 * User: mike
 * Date: 28.11.2004
 */
public abstract class Verifier implements TaskConstant {

    public abstract boolean verify(FractionalNumber [] answer);

    public static Verifier generateVerifier(Node node) throws TaskDeserializationException, SystemException {
    	Node verifierNode = node.getChild(TaskConstant.VERIFIER);
    	String type = verifierNode.getAttr("type");
    	try {
    		return (Verifier) Class.forName("ru.spb.ipo.engine.verifiers." + type).getConstructor(new Class[]{Node.class}).newInstance(new Object[]{node});
    	} catch (ClassNotFoundException e) {
    		//throw new SystemException("Couldn't find class for verifier " + t.getNodeValue(), e);
    		throw new TaskDeserializationException("Не могу найти класс верификатора " + type, e);
		} catch (NoSuchMethodException e) {
			throw new TaskDeserializationException("Не могу найти класс верификатора " + type, e);
		} catch (InstantiationException e) {
			throw new TaskDeserializationException("Не могу найти класс верификатора " + type, e);
		} catch (IllegalAccessException e) {
			throw new TaskDeserializationException("Не могу найти класс верификатора " + type, e);
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof TaskDeserializationException) {
				throw (TaskDeserializationException) e.getCause();
			}
			if (e.getCause() instanceof SystemException) {
				throw (SystemException) e.getCause();
			}
			throw new TaskDeserializationException(e.getMessage(), e);
		}		
    }
    
    public static void setCompleted(float i) {
    	if (ClientUI.client != null) {
    		ClientUI.client.setProcessed(i);
    	}
    }
}
