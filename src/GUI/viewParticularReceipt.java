package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import DAO.categoryDAO;
import DAO.materialDAO;
import DTO.particularReceiptDTO;
import DTO.receiptDTO;

public class viewParticularReceipt extends JFrame {

    private DefaultTableModel model;

    public JDialog view(receiptDTO receipt) {
        JDialog viewParticular = new JDialog();
        viewParticular.setTitle("Chi tiết phiếu nhập");
        viewParticular.setSize(700, 470);
        viewParticular.setLocationRelativeTo(null);
        viewParticular.setResizable(false);

        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel labelTitle = new JLabel("Danh sách sản phẩm đã đặt", JLabel.CENTER);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setForeground(Color.PINK);
        panelHeader.add(labelTitle);

        JTable table = new JTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(680, 400));

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(model);
        table.setShowGrid(false);
        table.setRowHeight(30);
        table.setDefaultRenderer(Object.class, new defaulttablemode());

        model.addColumn("STT");
        model.addColumn("Tên sản phẩm");
        model.addColumn("Loại");
        model.addColumn("Chất liệu");
        model.addColumn("Size");
        model.addColumn("Giá");
        model.addColumn("Số lượng");

        JTableHeader headerTable = table.getTableHeader();
        Font fontHeader = new Font("header", Font.BOLD, 15);

        headerTable.setFont(fontHeader);
        headerTable.setBackground(Color.pink);
        headerTable.setForeground(new Color(255, 0, 127));

        TableColumnModel tableColumnModel1 = table.getColumnModel();
        tableColumnModel1.getColumn(0).setPreferredWidth(50);
        tableColumnModel1.getColumn(1).setPreferredWidth(150);
        tableColumnModel1.getColumn(2).setPreferredWidth(80);
        tableColumnModel1.getColumn(3).setPreferredWidth(80);
        tableColumnModel1.getColumn(4).setPreferredWidth(50);
        tableColumnModel1.getColumn(5).setPreferredWidth(150);
        tableColumnModel1.getColumn(6).setPreferredWidth(100);

        viewParticular.add(panelHeader, BorderLayout.NORTH);
        viewParticular.add(scrollPane, BorderLayout.CENTER);

        loadData(receipt);

        viewParticular.setModalityType(ModalityType.MODELESS);
        viewParticular.setModal(true);
        viewParticular.setVisible(true);
        return viewParticular;
    }

    public void loadData(receiptDTO receipt) {
        model.setRowCount(0);
        int i = 0;

        for (particularReceiptDTO particular : receipt.getParticular()) {
            String category = new categoryDAO().selectByID(particular.getProduct().getIdCategory()).getCategoryName();
            String material = new materialDAO().selectByID(particular.getProduct().getIdMaterial()).getMaterial();

            model.addRow(new Object[] {
                    ++i,
                    particular.getProduct().getName(),
                    category,
                    material,
                    particular.getSize(),
                    item.price(particular.getPrice()),
                    particular.getQuantity()
            });
        }
    }
}
