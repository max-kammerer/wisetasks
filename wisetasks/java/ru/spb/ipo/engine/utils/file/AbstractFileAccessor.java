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

package ru.spb.ipo.engine.utils.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class AbstractFileAccessor implements FileAccessor {

	protected String getSafe(String name) {
		return name.replaceAll("\\\\", "/");
	}
	
	public String [] list(InputStream is) throws IOException {
		ArrayList res = new ArrayList();		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String filter = ".xml";
		String line = "";
		while(line != null) {
			line = reader.readLine();
			System.out.println(line);
			if (line == null) {
				break;
			} else {
				line = line.trim();
				if (line.toLowerCase().endsWith(filter)) {
					res.add(line);
				}
			}
		}
		is.close();
		return (String [])res.toArray(new String[res.size()] );
	}
}
