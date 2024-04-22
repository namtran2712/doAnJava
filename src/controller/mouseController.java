package controller;

import javax.swing.JLabel;

import GUI.mainViewChild;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mouseController extends MouseAdapter {
    mainViewChild view;

    public mouseController(mainViewChild view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        JLabel lb = (JLabel) e.getComponent();
        view.changePage(lb.getText());

    }

}
