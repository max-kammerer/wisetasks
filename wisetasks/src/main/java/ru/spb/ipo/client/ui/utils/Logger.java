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

package ru.spb.ipo.client.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;


public class Logger {
        
    private static boolean isFirst = false;
    public static JTextArea tfield;

    public static void log(String str) {
        if (tfield == null) return;
		str = new SimpleDateFormat("HH:mm").format(new Date()) + " " + str;
		if (!isFirst) {
			tfield.append(str);
			isFirst = true;
		} else {
			tfield.append("\n" + str);
		}
		tfield.setCaretPosition(tfield.getText().length());
	}
}
