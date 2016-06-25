package ru.spb.ipo.taskgenerator.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.spb.ipo.taskgenerator.model.KeyValue;

public class Element {

    protected String type;
    protected String name;
    protected boolean isRequired = true;

    protected Map subElements = new HashMap();
    protected List attributes = new ArrayList();
    protected int operations;
    protected boolean containingText;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubElement(Element e){
        subElements.put(e.getName(), e);
    }

    public Element getSubElement(String name){
        return (Element)subElements.get(name);
    }

    public Map getSubElements() {
        return subElements;
    }

    public List getAttributes() {
        return attributes;
    }

    public void setAttribute(Element e) {
        attributes.add(new KeyValue(e.getName(), e));
    }

    public boolean getRequired() {
        return isRequired;
    }

    public void setRequired(boolean b) {
        isRequired = b;
    }

    public void setOperations(String operations) {
        this.operations = Config.getOperation(operations);
    }

    public int getIntOperations() {
        return operations;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
