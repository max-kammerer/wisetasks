package ru.spb.ipo.generator.colors.figures;

import java.util.ArrayList;

public class Prizma extends Figure{
    public Prizma(String WhatToBrush, int corners) {
        super(WhatToBrush);
        if (WhatToBrush.equals("вершины")) {
            dim = 2*corners;
        }
        else if (WhatToBrush.equals("рёбра")) {
            dim = 3*corners;
        }
        else {
            dim = corners + 2;
        }
        permCount = 2;
    }
    @Override
    public ArrayList<Integer> getPermutation(int index) {
        ArrayList<Integer> list = new ArrayList<Integer> ();
        // вращение по основанию
        if (index == 0) {
            if (WhatToBrush.equals("вершины")) {
                list.add(Integer.valueOf(dim/2));
                for (int i = 1; i < dim/2; i++)
                    list.add(Integer.valueOf(i));
                list.add(Integer.valueOf(dim));
                for (int i = dim/2+1; i < dim; i++)
                    list.add(Integer.valueOf(i));
            }
            else if (WhatToBrush.equals("грани")) {
                for (int i = dim-2; i > 0; i--)
                    list.add(Integer.valueOf(i));
                list.add(dim-1);
                list.add(dim);
            }
            else {
                for (int i = 2; i < dim/3+1; i++) {
                    list.add(Integer.valueOf(i));
                }
                list.add(1);
                for (int i = dim/3+2; i < 2*dim/3+1; i++) {
                    list.add(Integer.valueOf(i));
                }
                list.add(Integer.valueOf(dim/3+1));
                for (int i = 2*dim/3+2; i < dim+1; i++) {
                    list.add(Integer.valueOf(i));
                }
                list.add(Integer.valueOf(2*dim/3+1));
            }
        }
        else {
            if (WhatToBrush.equals("грани")) {
                for (int i = 0; i < dim/2; i++) {
                    list.add(Integer.valueOf(dim/2-i));
                }
                for(int i = dim-2; i > dim/2; i--)
                    list.add(i);
                list.add(dim);
                list.add(dim-1);
            }
            else if(WhatToBrush.equals("вершины")) {
                // если четное число вершин
                for (int i = 0; i < dim/4; i++) {
                    list.add(Integer.valueOf(3*dim/4-i));
                }
                for(int i = dim; i > 3*dim/4; i--)
                    list.add(i);
                
                for (int i = 0; i < dim/4; i++) {
                    list.add(Integer.valueOf(dim/4-i));
                }
                for(int i = dim/2; i > dim/4; i--)
                    list.add(i);
            }
            else {
                // верхняя грань
                for (int i = 0; i < dim/6+1; i++) {
                    list.add(Integer.valueOf(dim/2+1-i));
                }
                for(int i = 2*dim/3; i > dim/2+1; i--)
                    list.add(i);
                // нижняя грань
                for (int i = 0; i < dim/6+1; i++) {
                    list.add(Integer.valueOf(dim/6+1-i));
                }
                for(int i = dim/3; i > dim/6+1; i--)
                    list.add(i);
                // боковые ребра
                for (int i = 0; i < dim/6; i++) {
                    list.add(Integer.valueOf(5*dim/6-i));
                }
                for(int i = dim; i > 5*dim/6; i--)
                    list.add(i);
            }
        }
        return list;
    }
}
