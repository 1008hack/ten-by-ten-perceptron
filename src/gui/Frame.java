package gui;

import logic.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    JButton[][] buttons;
    JButton boost;
    JButton stress;
    JButton go;
    JLabel label;
    Grid grid;

    public Frame() {
        setTitle("Do think");
        setSize(400, 400);
        setLayout(null);
        grid = new Grid();
        buttons = new JButton[10][10];
        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) {
            final int currentI = i;
            final int currentJ = j;
            ActionListener currentActionListener;
            currentActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton current = (JButton) e.getSource();
                    boolean state = grid.getCurrentStateOf(currentI, currentJ);
                    grid.setCurrentStateOf(currentI, currentJ, !state);
                    current.setBackground(!state ? Color.WHITE : Color.BLACK);
                    boost.setEnabled(false);
                    stress.setEnabled(false);
                    go.setEnabled(true);
                    go.setText("calculate");
                }
            };
            JButton current;
            current = new JButton();
            current.setBounds(i * 30, j * 30, 30, 30);
            current.setBackground(Color.BLACK);
            current.addActionListener(currentActionListener);
            buttons[i][j] = current;

            add(current);
        }
        {
            ActionListener boostActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    while (grid.expectation() < 0) grid.boost();
                    grid.boost();
                    go.setEnabled(false);
                    boost.setEnabled(false);
                }
            };

            ActionListener stressActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    while (!(grid.expectation() < 0)) grid.stress();
                    go.setEnabled(false);
                    stress.setEnabled(false);
                }
            };

            boost = new JButton();
            stress = new JButton();

            boost.addActionListener(boostActionListener);
            stress.addActionListener(stressActionListener);

            boost.setText("plus");
            stress.setText("minus");

            boost.setBounds(0, 300, 100, 30);
            stress.setBounds(100, 300, 100, 30);



            add(boost);
            add(stress);
        }
        {
            ActionListener goActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean currentExpectation = grid.expectation() < 0;
                    go.setText(currentExpectation ? "negative" : "positive");
                    (currentExpectation ? boost : stress).setEnabled(true);
                }
            };
            go = new JButton();
            go.setText("calculate");
            go.addActionListener(goActionListener);
            go.setBounds(200, 300, 100, 30);

            add(go);
        }
        setVisible(true);
    }
}
