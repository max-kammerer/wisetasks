
package ru.spb.ipo.generator.colors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
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
import ru.spb.ipo.generator.colors.figures.Figure;
import ru.spb.ipo.generator.colors.figures.Polygon;
import ru.spb.ipo.generator.colors.figures.Rectangle;
import ru.spb.ipo.generator.colors.figures.Romb;
import ru.spb.ipo.generator.colors.figures.Triangle;

/**
 *
 * @author Admin
 */
public class PolygonPanel extends ConstraintPanel{
    private JComboBox color;
    private JComboBox elementsCount;
    private JButton addFigureElement;
    
    private JComboBox forColoring;
    private JComboBox corners;
    private JRadioButton polygon;
    private JRadioButton triangle;
    private JRadioButton rectangle;
    private JRadioButton romb;
    private ButtonGroup bgFigure;
    
    private int taskType = 1;
    private JSpinner colorCount;
    
    ColorsSetPanel parent;
    Hashtable selectedColors;
    
    String radioCommand = "Правильный многоугольник";
    
    private JPanel figurePanel;
    private JPanel colorPanel;
    private JRadioButton type1Btn;
    private JRadioButton type2Btn;
    
    public PolygonPanel (BaseGeneratorUI gen, final ColorsSetPanel parent) {
        super(gen);
        this.parent = parent;
        selectedColors = new Hashtable();
        initComponents();
    }
    
    private void initComponents() {

        figurePanel = new javax.swing.JPanel();
        romb = new javax.swing.JRadioButton();
        rectangle = new javax.swing.JRadioButton();
        triangle = new javax.swing.JRadioButton();
        polygon = new javax.swing.JRadioButton();
        JLabel jLabel2 = new javax.swing.JLabel();
        corners = new javax.swing.JComboBox();
        forColoring = new javax.swing.JComboBox();
        JLabel jLabel1 = new javax.swing.JLabel();
        colorPanel = new javax.swing.JPanel();
        type1Btn = new javax.swing.JRadioButton();
        type2Btn = new javax.swing.JRadioButton();
        JLabel jLabel3 = new javax.swing.JLabel();
        color = new javax.swing.JComboBox();
        JLabel jLabel4 = new javax.swing.JLabel();
        elementsCount = new javax.swing.JComboBox();
        addFigureElement = new javax.swing.JButton();

        figurePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Фигура"));

        FigureListener listener = new FigureListener();
        romb.setText("Ромб");
        romb.addActionListener(listener);
        romb.setActionCommand("Ромб");
        rectangle.setText("Прямоугольник");
        rectangle.addActionListener(listener);
        rectangle.setActionCommand("Прямоугольник");
        triangle.setText("Равнобедренный треугольник");
        triangle.addActionListener(listener);
        triangle.setActionCommand("Равнобедренный треугольник");
        polygon.setText("Правильный многоугольник");
        polygon.setSelected(true);
        polygon.addActionListener(listener);
        polygon.setActionCommand("Правильный многоугольник");
        bgFigure = new ButtonGroup();
        bgFigure.add(polygon);
        bgFigure.add(triangle);
        bgFigure.add(rectangle);
        bgFigure.add(romb);

        jLabel2.setText("Количество вершин:");

        for (int i = 3; i < 9; i++)
            corners.addItem(String.valueOf(i));

        forColoring.addItem(new String("рёбра"));
        forColoring.addItem(new String("вершины"));

        jLabel1.setText("Выберите что красить:");

        javax.swing.GroupLayout figurePanelLayout = new javax.swing.GroupLayout(figurePanel);
        figurePanel.setLayout(figurePanelLayout);
        figurePanelLayout.setHorizontalGroup(
            figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(figurePanelLayout.createSequentialGroup()
                .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(figurePanelLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(forColoring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(figurePanelLayout.createSequentialGroup()
                        .addComponent(polygon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(corners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rectangle)
                    .addComponent(romb)
                    .addComponent(triangle))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        figurePanelLayout.setVerticalGroup(
            figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(figurePanelLayout.createSequentialGroup()
                .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(forColoring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(figurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(polygon)
                    .addComponent(jLabel2)
                    .addComponent(corners, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(triangle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rectangle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(romb)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        colorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Краски"));

        ButtonGroup colorBG = new ButtonGroup();
        type1Btn.setText("По количеству красок");
        type2Btn.setText("По элементам фигуры");
        colorBG.add(type1Btn);
        type1Btn.setActionCommand("type1");
        type1Btn.addActionListener(new ColorListener());
        type1Btn.setSelected(true);
        colorBG.add(type2Btn);
        type2Btn.setActionCommand("type2");
        type2Btn.addActionListener(new ColorListener());
        
        colorCount = new JSpinner(new SpinnerNumberModel(2,2,10,1));

        jLabel3.setText("Цвет:");

        color.addItem(new String ("красный"));
        color.addItem(new String ("желтый"));
        color.addItem(new String ("синий"));
        color.addItem(new String ("зеленый"));
        color.addItem(new String ("белый"));
        color.addItem(new String ("черный"));
        color.addItem(new String ("оранжевый"));
        color.addItem(new String ("коричневый"));
        color.addItem(new String ("фиолетовый"));
        color.setEnabled(false);

        jLabel4.setText("Количество:");
        for (int i = 1; i < 10; i++) {
            elementsCount.addItem(String.valueOf(i));
        }
        elementsCount.setEnabled(false);

        addFigureElement.setText("Добавить");
        addFigureElement.setEnabled(false);
        addFigureElement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                forColoring.setEnabled(false);
                createColorCond();
            }
        });

        javax.swing.GroupLayout colorPanelLayout = new javax.swing.GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(colorPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elementsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(addFigureElement))
                    .addGroup(colorPanelLayout.createSequentialGroup()
                        .addComponent(type1Btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colorCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(type2Btn))
                .addGap(26, 26, 26))
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type1Btn)
                    .addComponent(colorCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(type2Btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(elementsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addFigureElement)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(figurePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(figurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }
    
    class FigureListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            radioCommand = e.getActionCommand();
            if (radioCommand.equals("Правильный многоугольник"))
                corners.setEnabled(true);
            else
                corners.setEnabled(false);
        }
    }
    
    class ColorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("type1")) {
                colorCount.setEnabled(true);
                color.setEnabled(false);
                elementsCount.setEnabled(false);
                addFigureElement.setEnabled(false);
                taskType = 1;
            }
            else {
                colorCount.setEnabled(false);
                color.setEnabled(true);
                elementsCount.setEnabled(true);
                addFigureElement.setEnabled(true);
                taskType = 2;
            }
        }
    }
    
    private void createColorCond() {
        ComplexElement [] conds = parent.generator.getConditions();
        if (conds.length == 0)
            selectedColors = new Hashtable();
        String curColor = (String)color.getSelectedItem();
        String countColor = (String) selectedColors.get(curColor);
        if (countColor != null) {
            JOptionPane.showMessageDialog(this, "Этот цвет уже выбран");
        }
        else {
            String count = (String)elementsCount.getSelectedItem();
            String whatBrush = (String)forColoring.getSelectedItem();
            addCondition(new ColorCondition(curColor,count,whatBrush));
            selectedColors.put(curColor, count);
        }
    }
    
    public void fillMaps(Map source, Map func, Map task) {
        PolygonCondition cond;
        if (corners.isEnabled()) {
            cond = new PolygonCondition((String)forColoring.getSelectedItem(),
                radioCommand, (String)corners.getSelectedItem());
            source.put("corners", (String)corners.getSelectedItem());
        }
        else 
            cond = new PolygonCondition((String)forColoring.getSelectedItem(),
                radioCommand,null);
        if (taskType == 1)
            source.put("colors",(Integer)colorCount.getValue());
        else {
            source.put("colors", selectedColors.size());
            ArrayList<Integer> cList = new ArrayList<Integer>();
            Enumeration en = selectedColors.keys();
            while(en.hasMoreElements()) {
                String key = (String)en.nextElement();
                cList.add(Integer.valueOf((String)selectedColors.get(key)));
            }
            source.put("cList", cList);
        }
        source.put("polygon", radioCommand);
        source.put("whatToBrush", (String)forColoring.getSelectedItem());
        source.put("polygonText", cond.toDescription());
        source.put("taskType",Integer.valueOf(taskType));
        source.put("tabNum", Integer.valueOf(parent.tabs.getSelectedIndex()+1));
    }
    
    public String isRightColors() {
        String msg=null;
        if (taskType == 2) {
        int colCount = 0;
        Enumeration en = selectedColors.keys();
        while(en.hasMoreElements()) {
            String key = (String)en.nextElement();
            colCount = colCount + Integer.valueOf((String)selectedColors.get(key));
        }
        Figure fig;
        if (corners.isEnabled()) {
            fig = new Polygon((String)forColoring.getSelectedItem(),
                    Integer.valueOf((String)corners.getSelectedItem()).intValue());
        }
        else {
            if (radioCommand.equals("Равнобедренный треугольник"))
                fig = new Triangle((String)forColoring.getSelectedItem());
            else if (radioCommand.equals("Прямоугольник"))
                fig = new Rectangle((String)forColoring.getSelectedItem());
            else 
                fig = new Romb((String)forColoring.getSelectedItem());
        } 
        if (fig.getDim() != colCount)
            if (fig.getDim() < 5)
                msg = new String("Выберите "+fig.getDim()+" цвета.");
            else    
                msg = new String("Выберите "+fig.getDim()+" цветов.");
        }
        return msg;
    }
    
    public void unlockForColoring () {
        forColoring.setEnabled(true);
    }
}
