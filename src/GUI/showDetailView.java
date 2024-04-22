package GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class showDetailView extends JFrame {

    public static void main(String[] args) {
        new showDetailView().View();
    }

    public void View() {
        JDialog detail = new JDialog(this, "Chi tiết phiếu xuất");
        // detail.setSize();

        this.add(detail);
    }
}
