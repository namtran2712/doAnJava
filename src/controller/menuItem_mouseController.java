package controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GUI.Viewtrangchu;
import GUI.menuItems;

public class menuItem_mouseController extends MouseAdapter {
    Viewtrangchu view;

    public menuItem_mouseController(Viewtrangchu view) {
        this.view = view;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Container menu = e.getComponent().getParent();
        for (Component label : menu.getComponents()) {
            menuItems it = (menuItems) label;
            it.setIselected(false);
        }

        menuItems label = (menuItems) e.getComponent();
        label.setIselected(label.getParent().getComponentZOrder(label) + 1 == label.getIndex());
        view.loadViewAll();
        view.changePage(label.getText());
    }

}