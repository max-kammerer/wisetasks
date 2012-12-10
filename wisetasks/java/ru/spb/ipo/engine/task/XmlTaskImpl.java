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

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * User: mike
 * Date: 09.12.12
 * Time: 12:50
 */
public class XmlTaskImpl implements XmlTask {

    private Document document;

    public XmlTaskImpl(String fileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        document = parser.parse(fileName);
    }

    public Node getMathDescription() {
        return new NodeImpl(document.getElementsByTagName(MATH_DESCRIPTION).item(0));
    }

    public Node getDescription() {
        return new NodeImpl(document.getElementsByTagName(DESCRIPTION).item(0));
    }

    public String getTitle() {
        return document.getDocumentElement().getAttribute("title");
    }

    public Node getDesriptionParams() {
        return new NodeImpl(document.getElementsByTagName(DESCRIPTION_PARAMS).item(0));
    }

    public Node getVerifierParams() {
        return new NodeImpl(document.getElementsByTagName(VERIFIER_PARAMS).item(0));
    }


}
