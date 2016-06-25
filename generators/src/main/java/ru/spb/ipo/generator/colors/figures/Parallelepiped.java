package ru.spb.ipo.generator.colors.figures;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Parallelepiped extends Figure{
    public Parallelepiped(String WhatToBrush) {
        super(WhatToBrush);
        if (WhatToBrush.equals("вершины")) {
            dim = 8;
        }
        else if (WhatToBrush.equals("рёбра")) {
            dim = 12;
        }
        else {
            dim = 6;
        }
        permCount = 2;
    }

    @Override
    public ArrayList<Integer> getPermutation(int index) {
       ArrayList<Integer> list = new ArrayList<Integer>();
        if (index == 0) {
            if (WhatToBrush.equals("вершины")) {
                list.add(3);
                list.add(4);
                list.add(1);
                list.add(2);
                list.add(7);
                list.add(8);
                list.add(5);
                list.add(6);
            }
            else if (WhatToBrush.equals("грани")) {
                list.add(3);
                list.add(4);
                list.add(1);
                list.add(2);
                list.add(5);
                list.add(6);
            }
            else {
                list.add(7);
                list.add(6);
                list.add(5);
                list.add(8);
                list.add(3);
                list.add(2);
                list.add(1);
                list.add(4);
                list.add(10);
                list.add(9);
                list.add(12);
                list.add(11);
            }
        }
        else {
            if (WhatToBrush.equals("вершины")) {
                list.add(6);
                list.add(5);
                list.add(7);
                list.add(8);
                list.add(2);
                list.add(1);
                list.add(4);
                list.add(3);
            }
            else if (WhatToBrush.equals("грани")) {
                list.add(3);
                list.add(2);
                list.add(1);
                list.add(4);
                list.add(6);
                list.add(5);
            }
            else {
                list.add(3);
                list.add(4);
                list.add(1);
                list.add(2);
                list.add(7);
                list.add(8);
                list.add(5);
                list.add(6);
                list.add(11);
                list.add(12);
                list.add(9);
                list.add(10);
            }
        }
        return list;
    }
}
