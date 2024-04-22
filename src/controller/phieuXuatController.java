package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import GUI.Viewphieuxuat;

public class phieuXuatController implements MouseListener {

    Viewphieuxuat view;

    public phieuXuatController(Viewphieuxuat view) {
        this.view = view;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int i = view.getTbPhieuXuat().getSelectedRow();
        JLabel btn = (JLabel) e.getComponent();
        if (btn.getText().equals("Xem chi tiết")) {

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
