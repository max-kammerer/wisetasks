/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.spb.ipo.generator.colors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import ru.spb.ipo.generator.base.ComplexElement;
import ru.spb.ipo.generator.base.ui.BaseGeneratorUI;
import ru.spb.ipo.generator.base.ui.ConstraintPanel;

/**
 *
 * @author Admin
 */
public class BeadsPanel extends ConstraintPanel{
    private JComboBox color;
    private JComboBox quantity;
    private JButton addButton;
    private ColorsSetPanel parent;
    private Map<String, String> selectedColors;
    private JPanel all;
    private JRadioButton type2Btn;
    private JButton addFigureElement;
    private JSpinner colorCount;
    private JRadioButton type1Btn;
    private JSpinner beadsCount;
    private int taskType = 1;

    BeadsPanel(BaseGeneratorUI gen, ColorsSetPanel parent) {
        super(gen);
        this.parent = parent;
        selectedColors = new HashMap<String, String>();
        initComponents();
    }
    
    private void initComponents() {

        quantity = new javax.swing.JComboBox();
        type2Btn = new javax.swing.JRadioButton();
        addFigureElement = new javax.swing.JButton();
        color = new javax.swing.JComboBox();
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        colorCount = new javax.swing.JSpinner();
        type1Btn = new javax.swing.JRadioButton();
        JLabel jLabel1 = new javax.swing.JLabel();
        JLabel jLabel2 = new javax.swing.JLabel();
        
        ButtonGroup bg = new ButtonGroup();
        type1Btn.setText("По количеству красок");
        type1Btn.setActionCommand("type1");
        type1Btn.addActionListener(new ColorListener());
        type1Btn.setSelected(true);
        bg.add(type1Btn);
        type2Btn.setText("По количеству бусин определенного цвета");
        type2Btn.setActionCommand("type2");
        type2Btn.addActionListener(new ColorListener());
        bg.add(type2Btn);
        
        colorCount = new JSpinner(new SpinnerNumberModel(2,2,10,1));
        beadsCount = new JSpinner(new SpinnerNumberModel(3,3,10,1));

        color.addItem("красный");
        color.addItem("желтый");
        color.addItem("синий");
        color.addItem("зеленый");
        color.addItem("белый");
        color.addItem("черный");
        color.addItem("оранжевый");
        color.addItem("коричневый");
        color.addItem("фиолетовый");
        color.setEnabled(false);
        
        for (int i = 1; i < 10; i++)
            quantity.addItem(String.valueOf(i));
        quantity.setEnabled(false);

        addFigureElement.setText("Добавить");
        addFigureElement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCondFromParameters();
            }
        });
        addFigureElement.setEnabled(false);
        
        jLabel4.setText("Количество:");
        jLabel3.setText("Цвет:");
        jLabel1.setText("Краски:");
        jLabel2.setText("Бусины:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colorCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beadsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(addFigureElement))
                            .addComponent(type2Btn)
                            .addComponent(type1Btn))))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(type1Btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(beadsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(type2Btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFigureElement))
                .addContainerGap(184, Short.MAX_VALUE))
        );
    }
    
    private class ColorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("type1")) {
                colorCount.setEnabled(true);
                beadsCount.setEnabled(true);
                color.setEnabled(false);
                quantity.setEnabled(false);
                addFigureElement.setEnabled(false);
                taskType = 1;
            }
            else {
                colorCount.setEnabled(false);
                beadsCount.setEnabled(false);
                color.setEnabled(true);
                quantity.setEnabled(true);
                addFigureElement.setEnabled(true);
                taskType = 2;
            }
        }
    }

    private void createCondFromParameters() {
        ComplexElement [] conds = parent.generator.getConditions();
        if (conds.length == 0)
            selectedColors = new HashMap<String, String>();
        String colorName = (String)color.getSelectedItem();
        String countColor = selectedColors.get(colorName);
        if (countColor != null) {
            JOptionPane.showMessageDialog(this, "Этот цвет уже выбран");
        }
        else {
            String count = (String)quantity.getSelectedItem();
            selectedColors.put(colorName, count);
            System.out.println(colorName+"  "+count);
            addCondition(new BeadsCondition(colorName,count));
        }
    }
    
    @Override
    public void fillMaps(Map<String, Object> source, Map<String, Object> func, Map<String, Object> task) {
	int length = 0;
        if (taskType == 2) {
            ArrayList<Integer> cList = new ArrayList<Integer>();
            for (String key : selectedColors.keySet()) {
                String val = selectedColors.get(key);
                cList.add(Integer.valueOf(selectedColors.get(key)));
                length = length + Integer.valueOf(val);
            }
            source.put("colors", selectedColors.size());
            source.put("cList", cList);
            source.put("beadsLength", length);
        }
        else {
            source.put("colors", colorCount.getValue());
            source.put("beadsLength", beadsCount.getValue());
        }
        source.put("tabNum", parent.tabs.getSelectedIndex() + 1);
        source.put("taskType", taskType);
    }
    
    String isRightColors() {
        String msg = null;
        if (taskType == 1) {
            if ((Integer)colorCount.getValue() > (Integer)beadsCount.getValue())
                msg = "Количество цветов должно быть меньше количества бус.";
        }
        else {
            ComplexElement [] conds = parent.generator.getConditions();
            if (conds.length == 0)
                msg = "Добавьте несколько бусин разного цвета.";
        }
        return msg;
    }
}
