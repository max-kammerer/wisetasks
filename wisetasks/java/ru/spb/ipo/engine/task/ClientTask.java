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

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * User: mike
 * Date: 09.12.12
 * Time: 11:59
 */
public interface ClientTask {

    String getDescription();

    Map getGenParams();

    String getAnswer();

    void setAnswer(String answer);

    String getTitle();

    long getProblemId();

    List<String> getParameterButtons();

    List<Icon> getImages();

    void setImages(List<Icon> images);
}
