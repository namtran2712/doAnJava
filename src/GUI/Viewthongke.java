package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class Viewthongke extends JFrame {

        private static final long serialVersionUID = 1L;
        private JPanel Viewthongke;

        public JPanel View() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1100, 750);

                Viewthongke = new JPanel(new BorderLayout());
                JPanel header = new JPanel(new GridLayout(1, 3, 10, 10));
                header.setPreferredSize(new Dimension(1000, 200));
                Viewthongke.add(header, BorderLayout.NORTH);

                JPanel tk1 = new cardborder(Color.BLACK, Color.white, "src\\icon\\ccc.png", "Doanh số hôm nay",
                                "1.400.000$",
                                "increase 125%").View();
                header.add(tk1);

                JPanel tk2 = new cardborder(Color.BLACK, Color.white, "src\\icon\\ccc.png", "Doanh số hôm nay",
                                "1.400.000$",
                                "increase 125%").View();
                header.add(tk2);

                JPanel tk3 = new cardborder(Color.BLACK, Color.white, "src\\icon\\ccc.png", "Doanh số hôm nay",
                                "1.400.000$",
                                "increase 125%").View();
                header.add(tk3);

                JPanel navThongke = new JPanel(new GridLayout(1, 4));
                Viewthongke.add(navThongke, BorderLayout.CENTER);

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Ngày");
                model.addColumn("Chi Phí");
                model.addColumn("Doanh thu");
                model.addColumn("Lợi nhuận");

                model.setRowCount(10);
                JTable tablethongke = new JTable();
                tablethongke.setDefaultRenderer(Object.class, new defaulttablemode());
                tablethongke.setShowGrid(false);
                tablethongke.setRowHeight(30);
                tablethongke.setModel(model);

                JTableHeader theader = tablethongke.getTableHeader();
                Font fontHeader = new Font("header", Font.BOLD, 15);
                Font fontText = new Font("text", ABORT, 23);
                theader.setFont(fontHeader);

                theader.setBackground(Color.pink);
                theader.setForeground(new Color(255, 0, 127));

                JScrollPane pane = new JScrollPane(tablethongke);
                Viewthongke.add(pane, BorderLayout.SOUTH);
                return Viewthongke;

        }

}
