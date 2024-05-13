package controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GUI.Viewkhachhang;
import GUI.Viewphieunhap;
import GUI.Viewphieuxuat;
import GUI.Viewtonkho;
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

        if (label.getText().equals("Khách hàng")) {
            view.container.remove(view.khachhangView);
            view.khachhangView = new Viewkhachhang().View();
            view.container.add(view.khachhangView, "khách hàng");
        } else if (label.getText().equals("Phiếu nhập")) {
            view.container.remove(view.phieunhapView);
            view.phieunhapView = new Viewphieunhap().View();
            view.container.add(view.phieunhapView, "phiếu nhập");
        } else if (label.getText().equals("Phiếu xuất")) {
            view.container.remove(view.phieuxuatView);
            view.phieuxuatView = new Viewphieuxuat().View();
            view.container.add(view.phieuxuatView, "phiếu xuất");
        } else if (label.getText().equals("Tồn kho")) {
            view.container.remove(view.tonkhoView);
            view.tonkhoView = new Viewtonkho().View();
            view.container.add(view.tonkhoView, "tồn kho");
        }

        view.changePage(label.getText());
    }

}