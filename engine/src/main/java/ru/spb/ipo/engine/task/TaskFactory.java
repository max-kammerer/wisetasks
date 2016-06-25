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

package ru.spb.ipo.engine.task;

import org.xml.sax.SAXException;
import ru.spb.ipo.engine.exception.XmlException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User: mike
 * Date: 09.12.12
 * Time: 11:51
 */
public interface TaskFactory {

    ServerTask createServerTask(String taskFile, long problemId) throws XmlException, IOException;

    ClientTask createClientTask(String title, String description, Map genParams, long problemId, List<String> buttons) throws XmlException, IOException;

    XmlTask createXmlTask(String fileName) throws XmlException, IOException, ParserConfigurationException, SAXException;
}
