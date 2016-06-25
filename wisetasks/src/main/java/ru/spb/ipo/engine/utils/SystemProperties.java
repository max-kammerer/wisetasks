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

package ru.spb.ipo.engine.utils;

import java.util.HashMap;

import javax.swing.JComponent;

import ru.spb.ipo.client.ui.utils.Actions;

/**
 * User: mike
 * Date: 16.10.2008
 */
public class SystemProperties {

	public static final String REMOTE_HOST = "remoteHost";
	
	public static final String RMI_PORT = "port";
	
	public static final String PROXY_CLASS = "proxyClass";
	
	public static final String SYSTEM_NAME = "WiseTasks";
	
	public static final String IS_APPLET = "isApplet";
	
	public static final String NEED_LOGIN = "needLogin";
                                                         
    public static final String ENV = "env";

    public static SystemProperties instance = new SystemProperties();
	
	private HashMap properties = new HashMap();
		
	private SystemProperties() {
		properties.put(REMOTE_HOST, "localhost");
		properties.put(RMI_PORT, "5001");
		properties.put(PROXY_CLASS, "ru.spb.ipo.engine.rmi.ServerImpl");
		properties.put(IS_APPLET, "false");
		properties.put(SYSTEM_NAME, "turtle");
	}
	
	public static Object get(String key) {
		return instance.properties.get(key);
	}
	
	public static Object put(String key, Object value) {
		return instance.properties.put(key, value);
	}
	
	public static String getConnectionString() {
        System.out.println(getClientConnectionString());
	    return getClientConnectionString();
        // return "rmi://" + SystemProperties.get(SystemProperties.REMOTE_HOST) + ":" + get(RMI_PORT) + "/" + SystemProperties.get(SystemProperties.SYSTEM_NAME);
		//return "rmi://" + "192.168.11.2" + ":" + get(RMI_PORT) + "/" + SystemProperties.get(SystemProperties.SYSTEM_NAME);
	}
	
	public static String getClientConnectionString() {
        System.out.println("rmi://" + "192.168.1.2" + ":" + get(RMI_PORT) + "/" + SystemProperties.get(SystemProperties.SYSTEM_NAME));
	    return "rmi://" + "192.168.1.2" + ":" + get(RMI_PORT) + "/" + SystemProperties.get(SystemProperties.SYSTEM_NAME);
		//return "rmi://" + "84.17.23.130" + ":" + get(RMI_PORT) + "/" + SystemProperties.get(SystemProperties.SYSTEM_NAME);
	}


	public static boolean getBooleanFromString(String key) {
		return new Boolean((String)instance.properties.get(key)).booleanValue();
	}	
	
	public static String getString(String key) {
		return (String)instance.properties.get(key);
	}
	
}
