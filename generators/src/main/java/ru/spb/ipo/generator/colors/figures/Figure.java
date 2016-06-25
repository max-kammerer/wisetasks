package ru.spb.ipo.generator.colors.figures;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public abstract class Figure {
    protected String WhatToBrush;
    protected int permCount;
    protected int dim;
    public Figure(String WhatToBrush) {
        this.WhatToBrush = WhatToBrush;
    }
    public int getPermCount() {
        return permCount;
    }
    public int getDim() {
        return dim;
    }
    public abstract ArrayList<Integer> getPermutation(int index);
}
