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

import java.util.*;

import ru.spb.ipo.engine.exception.XmlException;
import ru.spb.ipo.engine.utils.MyLogger;

public class ParametersSandbox {
	
	private String type;	
	private Map<String, List<Parameter>> class2parameters  = new TreeMap<String, List<Parameter>>();
	private ArrayList<Parameter> parameters = new ArrayList<Parameter>();
    private List<String> parameterNames;

    private List classedParameters;
		
	public ParametersSandbox(Node node) throws XmlException {		
        if (node == null || node.isEmptyWrapper()) {
        	//no parameters
        	return;
        }
        
        if (TaskConstant.VERIFIER_PARAMS.equals(node.getNodeName()) || TaskConstant.DESCRIPTION_PARAMS.equals(node.getNodeName())) {
        	type = node.getNodeName();        
        } else {
        	return;
        }
        
        List<Node> parameters = node.getChilds(TaskConstant.PARAM);
        if (parameters == null || parameters.size() == 0) {
        	MyLogger.getLogger().warning("task has none parameters in verifier parameters");
            return;
        }

        for (Node parameter: parameters) {
            String name = parameter.getAttr(TaskConstant.PARAM_NAME);
            String pClass = parameter.getAttrIfExists(TaskConstant.PARAM_TYPE, "[class" + this.parameters.size());

            List<Node> valueNodes = parameter.getChilds(TaskConstant.PARAM_SET);
            List<Value> values = new ArrayList<Value>(valueNodes.size());

            for(Node valueNode: valueNodes) {
                String value = valueNode.getText();
                String text = valueNode.getAttrIfExists(TaskConstant.PARAM_TEXT, null);

                values.add(new Value(value, text));
            }
            Parameter pparameter = new Parameter(name, pClass, values);
            this.parameters.add(pparameter);
            ArrayList params = (ArrayList) class2parameters.get(pClass);
            if (params == null) {
            	params = new ArrayList();
            	class2parameters.put(pClass, params);
            }
            params.add(pparameter);
        }
	}
	
	private int getClassSize(String name) {
		ArrayList parameters = (ArrayList) class2parameters.get(name);
		if (parameters == null || parameters.size() == 0) {
			return 0;
		}
		return ((Parameter)parameters.get(0)).getValues().size();
	}

	
	public boolean isEmpty() {
		if (parameters == null || parameters.size() == 0) {
			return true;
		} 
		return false;		
	}
	
	public Iterator getIterator(){
			if (isEmpty()) return EMPTY_MAP.keySet().iterator();//HACK =)
			return new Iterator() {
				
				private int numberOfclasses = class2parameters.keySet().size();
				private int index = 0;
				private int size = getSize();
				HashMap hm = new HashMap();
				
				private int getSize() {
					int temp = 1;
					for (Iterator iter = class2parameters.keySet().iterator(); iter.hasNext();){
						String myClass = (String)iter.next();
						temp *= getClassSize(myClass);
					}
					return temp;
				}
				
				public boolean hasNext() {
			        return (index + 1 <= size);
				}

				public Object next() {
					Map m = new HashMap();
					Iterator i = parameters.iterator();
			        int tmp = index;
			        hm.clear();
			        while (i.hasNext()) {
			            Parameter parameter = (Parameter)i.next();
			            List<Value> values = parameter.getValues();
			            parameter.getClass();
			            String pClass = parameter.getMyClass();
			            int classSize = getClassSize(pClass);
			            int valueIndex = 0;
			            if (hm.containsKey(pClass)) {
			            	valueIndex = ((Integer) hm.get(pClass)).intValue();
			            } else {
			            	valueIndex = tmp % classSize;
			                hm.put(pClass, new Integer(valueIndex));
			                tmp = tmp / classSize;
			            }
			            m.put(parameter.getName(), values.get(valueIndex).getValue());
                        if (!"".equals(values.get(valueIndex).getText())) {
                            m.put(parameter.getName()+"-text", values.get(valueIndex).getText());
                        }
                    }
			        index++;
			        System.out.println(index + "   " + m);			        
			        return m;					
				}

				public void remove() {					
					throw new UnsupportedOperationException();
				}
			};
	}

    public Map getRandomParameters(){
		if (isEmpty()) {
            return EMPTY_MAP;
        }

        if (classedParameters == null) {
            classedParameters = new ArrayList();
            Iterator it = getIterator();
            while (it.hasNext()) {
                classedParameters.add(it.next());
            }
        }

        Random random = new Random();
        int index = random.nextInt(classedParameters.size());
        return (Map) classedParameters.get(index);
	}
	
	private static Map EMPTY_MAP = Collections.EMPTY_MAP;
	
	public List<String> getParameterNames() {
		if (isEmpty()) { 
			return Collections.emptyList();
		} else {
			if (parameterNames == null) {
				parameterNames = new ArrayList<String>(parameters.size()) ;
				for (Parameter parameter: parameters) {
					parameterNames.add(parameter.getName());
				}
                parameterNames = Collections.unmodifiableList(parameterNames);
			}
			return parameterNames;
		}
	}


}
