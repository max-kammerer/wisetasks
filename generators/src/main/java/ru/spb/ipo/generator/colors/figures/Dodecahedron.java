/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors.figures;

import java.util.ArrayList;
import ru.spb.ipo.generator.colors.figures.Figure;

/**
 *
 * @author Admin
 */
public class Dodecahedron extends Figure {
    public Dodecahedron(String WhatToBrush) {
        super(WhatToBrush);
        if (WhatToBrush.equals("вершины")) {
            dim = 20;
        }
        else if (WhatToBrush.equals("рёбра")) {
            dim = 30;
        }
        else {
            dim = 12;
        }
        permCount = 2;
    }
    @Override
    public ArrayList<Integer> getPermutation(int index) {
       ArrayList<Integer> list = new ArrayList<Integer>();
        if (index == 0) {
            if (WhatToBrush.equals("вершины")) {
                list.add(5);
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(10);
                list.add(6);
                list.add(7);
                list.add(8);
                list.add(9);
                list.add(15);
                list.add(11);
                list.add(12);
                list.add(13);
                list.add(14);
                list.add(20);
                list.add(16);
                list.add(17);
                list.add(18);
                list.add(19);
            }
            else if (WhatToBrush.equals("грани")) {
                list.add(1);
                list.add(6);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(5);
                list.add(11);
                list.add(7);
                list.add(8);
                list.add(9);
                list.add(10);
                list.add(12);
            }
            else {
                list.add(5);
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(10);
                list.add(6);
                list.add(7);
                list.add(8);
                list.add(9);
                list.add(20);
                list.add(11);
                list.add(12);
                list.add(13);
                list.add(14);
                list.add(15);
                list.add(16);
                list.add(17);
                list.add(18);
                list.add(19);
                list.add(25);
                list.add(21);
                list.add(22);
                list.add(23);
                list.add(24);
                list.add(30);
                list.add(26);
                list.add(27);
                list.add(28);
                list.add(29);
            }
        }
        else {
            if (WhatToBrush.equals("вершины")) {
                list.add(5);
                list.add(4);
                list.add(5);
                list.add(15);
                list.add(10);
                list.add(1);
                list.add(3);
                list.add(14);
                list.add(20);
                list.add(11);
                list.add(6);
                list.add(2);
                list.add(8);
                list.add(19);
                list.add(16);
                list.add(7);
                list.add(13);
                list.add(18);
                list.add(17);
                list.add(12);
            }
            else if (WhatToBrush.equals("грани")) {
                list.add(6);
                list.add(2);
                list.add(1);
                list.add(5);
                list.add(10);
                list.add(11);
                list.add(3);
                list.add(4);
                list.add(9);
                list.add(12);
                list.add(7);
                list.add(8);
                
            }
            else {
                list.add(10);
                list.add(5);
                list.add(9);
                list.add(18);
                list.add(19);
                list.add(1);
                list.add(4);
                list.add(7);
                list.add(25);
                list.add(20);
                list.add(6);
                list.add(2);
                list.add(3);
                list.add(8);
                list.add(16);
                list.add(24);
                list.add(29);
                list.add(30);
                list.add(21);
                list.add(11);
                list.add(12);
                list.add(7);
                list.add(15);
                list.add(28);
                list.add(26);
                list.add(13);
                list.add(14);
                list.add(23);
                list.add(27);
                list.add(22);
            }
        }
        return list;
    }

}
