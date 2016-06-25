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

import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

public interface FileAccessor {
		
	public InputStream getInputStream(String fileName) throws IOException;
		
	public String [] list(String dirName, final String... filter) throws IOException;
		
	public ImageIcon getIcon(String fileName) throws IOException;
		
	public String [] list(InputStream is) throws IOException;	
}
