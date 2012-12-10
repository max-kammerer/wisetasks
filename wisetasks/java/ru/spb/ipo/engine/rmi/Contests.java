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

package ru.spb.ipo.engine.rmi;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ru.spb.ipo.engine.utils.FileAccessUtil;
import ru.spb.ipo.engine.exception.TaskDeserializationException;

import java.io.IOException;

/**
 * User: Michael Bogdanov
 * Date: 26.03.2005
 */
public class Contests {

    private String [] titles;
    private String [] files;

    public Contests() throws TaskDeserializationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder parser = factory.newDocumentBuilder();
            doc = parser.parse(FileAccessUtil.getInputStream("tests.xml"));
        } catch (Exception e) {
            throw new TaskDeserializationException("Ошибка при разборе файла с задачниками: tests.xml: \n" + e.getMessage());
        }
        NodeList nl = doc.getElementsByTagName("test");
        titles = new String [nl.getLength()];
        files = new String [nl.getLength()];
        for (int i = 0; i < nl.getLength(); i++) {
            titles[i] = nl.item(i).getAttributes().getNamedItem("title").getNodeValue();
            files[i] = nl.item(i).getAttributes().getNamedItem("folder").getNodeValue().replaceAll("\\\\", "/");
        }
    }

    public String[] getTitles() {
        return titles;
    }

    public String getFile(int index) {
        return files[index];
    }   

}
