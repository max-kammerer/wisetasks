package ru.spb.ipo.taskgenerator.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester.Digester;

public class Config {

	public final static String TYPE_ROOTELEMENT = "root";
	
	public final static String TYPE_FUNCTION = "function";
	public final static String TYPE_SET = "set";
	public final static String TYPE_DESCPARAMSET = "description-params";
    public final static String TYPE_VERPARAMSET = "verifier-params";
    public final static String TYPE_DESCRIPTION = "description";
    
    public final static String TYPE_ELEMENT = "element";
    public final static String TYPE_COMMAND = "command";
    public final static String TYPE_TEXT = "text";
    
    public final static String TYPE_PARAM = "param";

    public final static int ADD_SET = 1;
    public final static int ADD_FUNCTION = 2;
    public final static int ADD_COMMAND = 4;
    //public final static int ADD_VPARAM = 8;
    public final static int ADD_PARAM = 16;
    public final static int UPDATE = 32;
    public final static int DELETE = 64;
    public final static int ADD_TEXT = 128;
    public final static int ADD_VPARAMSET = 256;
    public final static int ADD_GPARAMSET = 512;
    public final static int NO_OPERATIONS = 1048576;
    public final static int DEFAULT_ELEMENT = NO_OPERATIONS;
    public final static int ALL_OPERATIONS = 2147483647;
	
    private Map functions = new HashMap();
    private List functionsList = new ArrayList();
    
    private Map sets = new HashMap();
    private Map rootelements = new HashMap();
    private static Map operations = new HashMap();
    private Map commands = new HashMap();

    
    private static Config myConfig = loadConfig();
    
    public static Config getInstance() {
    	return  myConfig;
    }

    
    public List getFunctions() {
        return functionsList;
    }

    public Map getSets() {
        return sets;
    }

    public Element getFunction(String name) {
        return (Element)functions.get(name);
    }

    public Element getSet(String name) {
        return (Element)sets.get(name);
    }

    public void addFunction(Element e) {
    	functionsList.add(e);
        functions.put(e.getName(), e);
    }

    public void addSet(Element e) {
        sets.put(e.getName(), e);
    }

    public static void addOperation(Operation op) {
        operations.put(op.getName(), op);
    }

    public static int getOperation(String name) {
        if (operations.containsKey(name))
            return ((Operation)operations.get(name)).getInt();
        else return NO_OPERATIONS;
    }

    public static Map getOperations() {
        return operations;
    }

    public void addRootElement(RootElement e){
        rootelements.put(e.getName(), e);
    }

    public Element getRootElement(String name){
        return (Element) rootelements.get(name);
    }

    
    public Element getRootModelElement(String name){
    	if (rootelements.containsKey(name)) {
    		return (Element) rootelements.get(name);
    	} else {
    		return (Element)commands.get(name);
    	}
    }

    public Map getCommands() {
        return commands;
    }

    public Element getCommand(String name) {
        return (Element)commands.get(name);
    }

    public void addCommand(CommandElement c) {
        commands.put(c.getName(), c);
    }

    public Element getElementByName(String name) {
        Element e = getRootModelElement(name);
        if (e != null) return e;
        e = getFunction(name);
        if (e != null) return e;
        e = getSet(name);
        if (e != null) return e;
        e = getCommand(name);
        return e;
    }
    
    private static Config loadConfig(){
        Digester digester = new Digester();
        digester.setValidating( false );

        digester.addObjectCreate( "config", Config.class );

        digester.addObjectCreate( "config/operations-set/operations", Operation.class );
        digester.addSetProperties( "config/operations-set/operations", "name", "name");
        digester.addSetProperties( "config/operations-set/operations/operation", "name", "operation");
        digester.addSetProperties( "config/operations-set/operations/super", "name", "super");
        digester.addSetNext("config/operations-set/operations","addOperation");

        digester.addObjectCreate( "config/sets/set", SetElement.class );
        digester.addSetProperties( "config/sets/set", "name", "name");
        digester.addSetProperties( "config/sets/set", "operations", "operations");

        digester.addObjectCreate( "config/sets/set/subElement", Element.class );
        digester.addSetProperties( "config/sets/set/subElement", "required", "required");
        digester.addSetProperties( "config/sets/set/subElement", "name", "name");
        digester.addSetNext("config/sets/set/subElement","setSubElement");

        digester.addObjectCreate( "config/sets/set/attribute", Element.class );
        digester.addSetProperties( "config/sets/set/attribute", "required", "required");
        digester.addSetProperties( "config/sets/set/attribute", "name", "name");
        digester.addSetNext("config/sets/set/attribute","setAttribute");
        digester.addSetNext("config/sets/set","addSet");

        digester.addObjectCreate( "config/functions/function", FunctionElement.class );
        digester.addSetProperties( "config/functions/function", "name", "name");
        digester.addSetProperties( "config/functions/function", "operations", "operations");
        digester.addObjectCreate( "config/functions/function/subElement", Element.class );
        digester.addSetProperties( "config/functions/function/subElement", "required", "required");
        digester.addSetProperties( "config/functions/function/subElement", "name", "name");
        digester.addSetNext("config/functions/function/subElement","setSubElement");

        digester.addObjectCreate( "config/functions/function/attribute", Element.class );
        digester.addSetProperties( "config/functions/function/attribute", "required", "required");
        digester.addSetProperties( "config/functions/function/attribute", "name", "name");
        digester.addSetNext("config/functions/function/attribute","setAttribute");
        digester.addSetNext("config/functions/function","addFunction");

        digester.addObjectCreate( "config/rootelements/element", RootElement.class );
        digester.addSetProperties( "config/rootelements/element", "name", "name");
        digester.addSetProperties( "config/rootelements/element", "operations", "operations");
        digester.addSetProperties( "config/rootelements/element", "type", "type");
        digester.addObjectCreate( "config/rootelements/element/subElement", Element.class );
        digester.addSetProperties( "config/rootelements/element/subElement", "required", "required");
        digester.addSetProperties( "config/rootelements/element/subElement", "name", "name");
        digester.addSetNext("config/rootelements/element/subElement","setSubElement");

        digester.addObjectCreate( "config/rootelements/element/attribute", Element.class );
        digester.addSetProperties( "config/rootelements/element/attribute", "required", "required");
        digester.addSetProperties( "config/rootelements/element/attribute", "name", "name");
        //digester.addSetProperties( "config/rootelements/element/attribute", "type", "type");
        digester.addSetNext("config/rootelements/element/attribute","setAttribute");
        digester.addSetNext("config/rootelements/element","addRootElement");

        digester.addObjectCreate( "config/preprocessorcommands/command", CommandElement.class );
        digester.addSetProperties( "config/preprocessorcommands/command", "name", "name");
        digester.addSetProperties( "config/preprocessorcommands/command", "operations", "operations");
        digester.addObjectCreate( "config/preprocessorcommands/command/subElement", Element.class );
        digester.addSetProperties( "config/preprocessorcommands/command/subElement", "required", "required");
        digester.addSetProperties( "config/preprocessorcommands/command/subElement", "name", "name");
        digester.addSetNext("config/preprocessorcommands/command/subElement","setSubElement");

        digester.addObjectCreate( "config/preprocessorcommands/command/attribute", Element.class );
        digester.addSetProperties( "config/preprocessorcommands/command/attribute", "required", "required");
        digester.addSetProperties( "config/preprocessorcommands/command/attribute", "name", "name");
        digester.addSetProperties( "config/preprocessorcommands/command/attribute", "type", "type");
        digester.addSetNext("config/preprocessorcommands/command/attribute","setAttribute");
        digester.addSetNext("config/preprocessorcommands/command","addCommand");

        File input = new File("tgconfig.xml");

        Config c  = null;
        try {
            c = (Config)digester.parse( input );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

}
