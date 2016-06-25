package ru.spb.ipo.taskgenerator.config;



public class Operation {

    private String name;
    private int ops = 0;

    public void setOperation(String name) {
        try {
            ops |= Config.class.getField(name).getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getInt() {
        return ops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuper(String name){
        ops = ops | Config.getOperation(name);
    }
}
