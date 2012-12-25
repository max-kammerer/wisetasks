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

package ru.spb.ipo.engine.sets;

import ru.spb.ipo.engine.elements.Element;
import ru.spb.ipo.engine.exception.SystemException;
import ru.spb.ipo.engine.exception.TaskDeserializationException;
import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.task.Node;
import ru.spb.ipo.engine.utils.Utils;


/**
 * User: mike
 * Date: 28.11.2004
 */

/*
 * Базовый класс множества.
 */
abstract public class Set {

    /**
     * Инициализирует поля объекта, созданного с помощью  метода generateSet(node).
     * @param node - объект типа org.w3c.dom.Node с tagName = set
     * @throws XmlException 
     * @throws SystemException 
     * @throws TaskDeserializationException 
     */
    abstract protected void initSet(Node node) throws SystemException, TaskDeserializationException;

    /**
     * Сооздает множество на основе вершины из DOM XML файла, описывающего задачу. Вызывает initSet для инициализации полей.
     * Множество должно иметь конструктор по умолчанию. 
     * @param node - объект типа org.w3c.dom.Node с tagName = set
     * @return Возвращает множество, указанное в аттрибуте type элемента set
     * @throws XmlException 
     * @throws SystemException 
     * @throws  
     */
    public static Set generateSet(Node node) throws TaskDeserializationException, SystemException {
    	String shortType = node.getAttr("type");
        String type = "ru.spb.ipo.engine.sets." + shortType;
        Set s = null;
        try {
            s = (Set)Class.forName(type).newInstance();
            s.initSet(node);
        } catch (ClassNotFoundException e) {
        	throw new TaskDeserializationException("Ошибка при создании класса для множества " + shortType, e);
		} catch (InstantiationException e) {
			throw new TaskDeserializationException("Ошибка при создании класса для множества " + shortType, e);
		} catch (IllegalAccessException e) {
			throw new TaskDeserializationException("Ошибка при создании класса для множества " + shortType, e);
		}
        return s;

    }

    /**
     * Возвращает размер множества.
     */
    abstract public long getSize();

    /**
     * Возвращает длину картежа.
     */
    abstract public int getLength();

    /**
     * Возвращает элемент по его номеру.
     * Используется для генерации сочетаний и размещений, в них самих она не реализована.
     */
    abstract public Element getElement(long index);

    abstract public SetIterator iterator();

}