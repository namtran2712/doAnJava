package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BUS.accountBUS;
import DAO.accountDao;
import DTO.accountDTO;
import controller.taiKhoanController;

public class Viewtaikhoan extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel viewPanel;
    private DefaultTableModel model;
    private JTable tableAccount;
    private accountBUS listAccount;
    public menuChucNang chucnang;

    public Viewtaikhoan() {
        listAccount = new accountBUS();
    }

    public JPanel view() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);

        viewPanel = new JPanel(new BorderLayout());

        setContentPane(viewPanel);
        viewPanel.setSize(1100, 750);
        JPanel panel = new JPanel();
        panel.setLocation(0, 130);
        panel.setSize(1020, 583);

        tableAccount = new JTable();
        tableAccount.setDefaultRenderer(Object.class, new defaulttablemode());
        tableAccount.setShowGrid(false);
        tableAccount.setRowHeight(30);
        tableAccount.setShowGrid(false);
        tableAccount.setRowHeight(30);
        // tableAccount.addMouseListener();

        tableAccount.setDefaultRenderer(Object.class, new defaulttablemode());

        model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        tableAccount.setModel(model);
        model.addColumn("Stt");
        model.addColumn("Id");
        model.addColumn("Vai trò");
        model.addColumn("Tên người dùng");
        model.addColumn("Username");
        model.addColumn("Password");
        panel.setLayout(new GridLayout(1, 1, 20, 20));
        model.setRowCount(25);
        JScrollPane pane = new JScrollPane(tableAccount);
        pane.setPreferredSize(new Dimension(1020, 583));
        JTableHeader header = tableAccount.getTableHeader();

        header.setFont(new Font("Arial", Font.ITALIC, 18));
        header.setBackground(Color.pink);
        header.setForeground(new Color(255, 0, 127));

        TableColumnModel tableColumnModel = tableAccount.getColumnModel();
        tableColumnModel.getColumn(0).setPreferredWidth(70);
        tableColumnModel.getColumn(1).setPreferredWidth(70);
        tableColumnModel.getColumn(2).setPreferredWidth(300);
        tableColumnModel.getColumn(3).setPreferredWidth(300);
        tableColumnModel.getColumn(4).setPreferredWidth(300);
        tableColumnModel.getColumn(5).setPreferredWidth(200);

        panel.add(pane);

        chucnang = new menuChucNang();
        viewPanel.add(chucnang.createmenuChucNang(), BorderLayout.NORTH);
        viewPanel.add(panel, BorderLayout.CENTER);

        showInfo(listAccount.getListAccount());

        chucnang.bin_btn.addMouseListener(new taiKhoanController(this));
        chucnang.see_btn.setEnabled(false);
        chucnang.search_btn.addMouseListener(new taiKhoanController(this));
        chucnang.textField.addKeyListener(new taiKhoanController(this));
        chucnang.comboBox.addActionListener(new taiKhoanController(this));
        chucnang.comboBox.removeAllItems();
        chucnang.comboBox.addItem("Tất cả");
        chucnang.comboBox.addItem("Theo username");
        chucnang.comboBox.addItem("Theo id");

        return viewPanel;
    }

    public JTable getTableAccount() {
        return tableAccount;
    }

    public accountBUS getListAccount() {
        return listAccount;
    }

    public void setListAccount(accountBUS listAccount) {
        this.listAccount = listAccount;
    }

    public void showInfo(ArrayList<accountDTO> list) {
        model.setRowCount(0);
        int i = 0;
        for (accountDTO acc : list) {
            model.addRow(new Object[] {
                    ++i,
                    acc.getIdAccount(),
                    acc.getVaiTro().getName(),
                    acc.getNhanVien().getName(),
                    acc.getUsername(),
                    acc.getPassword()
            });
        }
    }

    public void reloadData() {
        listAccount.setListAccount(new accountDao().selectAll());
    }
}
