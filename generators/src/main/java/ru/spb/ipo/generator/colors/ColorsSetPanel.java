/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;

/**
 *
 * @author Admin
 */
public class ColorsSetPanel extends ConstraintPanel {
    public JTabbedPane tabs;
    public BaseGeneratorUI generator;
    
    private BeadsPanel beads;
    private PolygonPanel polygons;
    private PolyhedronPanel polyhedrons;
    
    public ColorsSetPanel (BaseGeneratorUI gen) {
        super(gen);
        this.generator = gen;
        initialize(gen);
    }
    public void fillMaps(Map source, Map func, Map task) {
        switch(tabs.getSelectedIndex()) {
            case 0:
                beads.fillMaps(source, func, task);
                break;
            case 1:
                polygons.fillMaps(source, func, task);
                break;
            case 2:
                polyhedrons.fillMaps(source, func, task);
                break;
        }
    }
    private void initialize(BaseGeneratorUI gen) {
        TabsListener listener = new TabsListener();
        this.setPreferredSize(new Dimension(380, 308));
        tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        beads = new BeadsPanel(gen,this);
        beads.addComponentListener(listener);
        tabs.add("Бусы",beads);
        polygons = new PolygonPanel(gen,this);
        tabs.add("Многоугольники",polygons);
        polygons.addComponentListener(listener);
        polyhedrons = new PolyhedronPanel(gen,this);
        tabs.add("Многогранники",polyhedrons);
        polyhedrons.addComponentListener(listener);
        setLayout(new BorderLayout());
        add(tabs,BorderLayout.CENTER);
    }
    
    class TabsListener implements ComponentListener {

        public void componentResized(ComponentEvent e) {
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
            generator.getFunctionList().setModel(new DefaultListModel());
            switch(tabs.getSelectedIndex()) {
            case 1:
                polygons.unlockForColoring();
                break;
            case 2:
                polyhedrons.unlockForColoring();
                break;
        }
        }

        public void componentHidden(ComponentEvent e) {
        }
    }
    
    public String isRightColors() {
        String msg = null;
        switch(tabs.getSelectedIndex()) {
            case 0:
                msg = beads.isRightColors();
                break;
            case 1:
                msg = polygons.isRightColors();
                break;
            case 2:
                msg = polyhedrons.isRightColors();
                break;
        }
        return msg;
    }
}
