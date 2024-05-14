package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.thongKeBus;
import DAO.billDao;
import DAO.receiptDao;
import DTO.thongkeDTO;

import java.awt.*;
import java.util.ArrayList;

public class Viewthongke extends JFrame {

        private static final long serialVersionUID = 1L;
        private JPanel Viewthongke;
        private thongKeBus thongke;
        private DefaultTableModel model;
        private JTable tablethongke;

        public Viewthongke() {
                thongke = new thongKeBus();
        }

        public JPanel View() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1100, 750);

                Viewthongke = new JPanel(new BorderLayout());
                JPanel header = new JPanel(new GridLayout(1, 3, 10, 10));
                header.setPreferredSize(new Dimension(1000, 200));
                Viewthongke.add(header, BorderLayout.NORTH);

                float doanhThuCurrent = new billDao().getDoanhThuCurrent();
                JPanel tk1 = new cardborder(Color.BLACK, Color.white, "src\\icon\\ccc.png", "Doanh thu hôm nay",
                                item.price(doanhThuCurrent),
                                "").View();
                header.add(tk1);

                float chiPhiCurrent = new receiptDao().getChiPhiCurrent();
                JPanel tk2 = new cardborder(Color.BLACK, Color.white, "src\\icon\\ccc.png", "chi phí hôm nay",
                                item.price(chiPhiCurrent),
                                "").View();
                header.add(tk2);

                float loiNhuanCurrent = doanhThuCurrent - chiPhiCurrent;
                JPanel tk3 = new cardborder(Color.BLACK, Color.white, "src\\icon\\ccc.png", "Lợi nhuận hôm nay",
                                item.price(loiNhuanCurrent),
                                "").View();
                header.add(tk3);

                JPanel navThongke = new JPanel(new GridLayout(1, 4));
                Viewthongke.add(navThongke, BorderLayout.CENTER);

                model = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                };
                model.addColumn("Thời gian");
                model.addColumn("Chi Phí");
                model.addColumn("Doanh thu");
                model.addColumn("Lợi nhuận");

                model.setRowCount(10);
                tablethongke = new JTable();
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

                loadData(thongke.getList());

                JScrollPane pane = new JScrollPane(tablethongke);
                Viewthongke.add(pane, BorderLayout.SOUTH);
                return Viewthongke;
        }

        public void loadData(ArrayList<thongkeDTO> thongke) {
                model.setRowCount(0);
                for (thongkeDTO tk : thongke) {
                        float loiNhuan = tk.getTotalDoanhThu() - tk.getTotalChiPhi();
                        model.addRow(new Object[] {
                                        tk.getTime(),
                                        item.price(tk.getTotalChiPhi()),
                                        item.price(tk.getTotalDoanhThu()),
                                        item.price(loiNhuan)
                        });
                }
        }

}
