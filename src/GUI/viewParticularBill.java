package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import DTO.billDTO;
import DTO.particularBill;

public class viewParticularBill {
    private DefaultTableModel model;

    public JDialog view(billDTO bill) {
        JDialog viewParticular = new JDialog();
        viewParticular.setTitle("Chi tiết phiếu xuất");
        viewParticular.setSize(700, 470);
        viewParticular.setLocationRelativeTo(null);
        viewParticular.setResizable(false);

        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel labelTitle = new JLabel("Chi tiết phiếu xuất", JLabel.CENTER);
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
        model.addColumn("Size");
        model.addColumn("Giá");
        model.addColumn("Số lượng");

        JTableHeader headerTable = table.getTableHeader();
        Font fontHeader = new Font("header", Font.BOLD, 15);

        headerTable.setFont(fontHeader);
        headerTable.setBackground(Color.pink);
        headerTable.setForeground(new Color(255, 0, 127));

        TableColumnModel tableColumnModel1 = table.getColumnModel();
        tableColumnModel1.getColumn(0).setPreferredWidth(30);
        tableColumnModel1.getColumn(1).setPreferredWidth(300);
        tableColumnModel1.getColumn(2).setPreferredWidth(30);
        tableColumnModel1.getColumn(3).setPreferredWidth(50);
        tableColumnModel1.getColumn(4).setPreferredWidth(100);

        viewParticular.add(panelHeader, BorderLayout.NORTH);
        viewParticular.add(scrollPane, BorderLayout.CENTER);

        loadData(bill);

        viewParticular.setModalityType(ModalityType.MODELESS);
        viewParticular.setModal(true);
        viewParticular.setVisible(true);
        return viewParticular;
    }

    public void loadData(billDTO bill) {
        model.setRowCount(0);
        int i = 0;
        for (particularBill items : bill.getDetail()) {
            model.addRow(new Object[] {
                    ++i,
                    items.getProduct().getName(),
                    items.getSize(),
                    item.price(items.getPrice()),
                    items.getQuantity()
            });
        }
    }
}
