/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsa2020;

import geom.*;

import geom.PointComparator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import sorting.*;

/**
 * @author LTSACH
 */
public class WorkingPanel extends JPanel implements MouseMotionListener, MouseListener, ItemListener, ActionListener, ComponentListener {
    private Graph[] g = new Graph[4];
    SpaceMapping spaceMapping = new SpaceMapping();

    Axis axis = new Axis(spaceMapping.getLogViewport());

    public WorkingPanel() {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addComponentListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D logPoint = this.spaceMapping.device2Logic(e.getX(), e.getY());
        String message = String.format("mouseDragged: Device(x,y)=(%d,%d); Logic(x,y)=(%6.2f, %6.2f)",
                e.getX(), e.getY(), logPoint.getX(), logPoint.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point2D logPoint = this.spaceMapping.device2Logic(e.getX(), e.getY
                ());
        String message = String.format("mouseMoved: Device(x,y)=(%d,%d); Logic(x,y)=(%6.2f, %6.2f)",
                e.getX(), e.getY(), logPoint.getX(), logPoint.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((e.getClickCount() == 2) && !e.isConsumed()) {
            String message = String.format("Mouse Double Clicked: (x,y)=(%d,%d)", e.getX(), e.getY());
            MainFrame.infoPanel.println(message);
            e.consume();
        } else {
            String message = String.format("Mouse Clicked: (x,y)=(%d,%d)", e.getX(), e.getY());
            MainFrame.infoPanel.println(message);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        String message = String.format("mousePressed: (x,y)=(%d,%d)", e.getX(), e.getY());

        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String message = String.format("mouseReleased: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String message = String.format("mouseEntered: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String message = String.format("mouseExited: (x,y)=(%d,%d)", e.getX(), e.getY());
        MainFrame.infoPanel.println(message);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED) {
            MainFrame.infoPanel.println("Selected");
            MainFrame.btnSelect.setText("Selecting");
        } else {
            MainFrame.infoPanel.println("DeSelected");
            MainFrame.btnSelect.setText("Drawing");
        }
    }

    Graph sin;
    Graph quad;
    Graph linear;
    Point2D point2D;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == MainFrame.btnGraph) {
            MainFrame.infoPanel.println("action: draw Graph");
            ISort[] algorithms = {
                    new StraightInsertionSort<Point2D>(),
                    new ShellSort<Point2D>(),
                    new StraightSelectionSort<Point2D>(),
                    new BubbleSort<Point2D>()
            };

            Color[] algo_color = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN};
            for (int aIdx = 0; aIdx < algorithms.length; aIdx++) {
                Point2D[] points = Point2D.generate(100, -20, 20);
                //If you want to sort ...
                algorithms[aIdx].sort(points, new PointComparator(true)); //do sorting
                //If you want to time it ...
                Point2D[] time = SortingEval.timeit(algorithms[aIdx], 500, 100, true);
                //here: more code for other purpose.

                double minX = 0, maxX = 500, minY = 0, maxY = time[time.length - 1].getY();
                this.g[aIdx] = new Graph(time, minX, maxX, minY, maxY, algo_color[aIdx]);
                this.axis.addGraph(this.g[aIdx], algo_color[aIdx], aIdx + 1);
            }

            repaint();
        }
    }


    @Override
    public void componentResized(ComponentEvent e) {
        Dimension size = this.getSize();
        int xGap = 30, yGap = 30;
        this.spaceMapping.updateDevViewPort(xGap, size.width - 2 * xGap, yGap,
                size.height - 2 * yGap);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (this.axis != null)
            this.spaceMapping.updateLogViewPort(this.axis.getViewport());
        this.axis.draw(g, spaceMapping);
    }
}
