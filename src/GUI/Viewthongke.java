package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class Viewthongke extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel Viewthongke;
    private JTextField textField;

    public JPanel View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);

        Viewthongke = new JPanel();
        Viewthongke.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(Viewthongke);
        Viewthongke.setSize(1100, 750);
        Viewthongke.setLayout(null);

        JPanel menu_chucnang = new JPanel();
        menu_chucnang.setBounds(0, 10, 1086, 110);
        menu_chucnang.setLayout(new GridLayout(1, 2));
        Viewthongke.add(menu_chucnang);

        JPanel chucNang = new JPanel();
        chucNang.setLayout(new GridLayout(1, 6, 10, 20));
        chucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        menu_chucnang.add(chucNang);

        JLabel add_btn = new JLabel("Thêm");
        add_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        add_btn.setIcon(new ImageIcon("src\\icon\\add.png"));
        add_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        add_btn.setHorizontalTextPosition(SwingConstants.CENTER);
        add_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chucNang.add(add_btn);

        JLabel bin_btn = new JLabel("Xóa");
        bin_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        bin_btn.setIcon(new ImageIcon("src\\icon\\bin.png"));
        bin_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        bin_btn.setHorizontalTextPosition(SwingConstants.CENTER);
        bin_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chucNang.add(bin_btn);

        JLabel search_btn = new JLabel("Sửa");
        search_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        search_btn.setIcon(new ImageIcon("src\\icon\\pem.png"));
        search_btn.setHorizontalTextPosition(SwingConstants.CENTER);
        search_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        search_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chucNang.add(search_btn);

        JLabel see_btn = new JLabel("Xem chi tiết");
        see_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        see_btn.setIcon(new ImageIcon("src\\icon\\see.png"));
        see_btn.setHorizontalTextPosition(SwingConstants.CENTER);
        see_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        see_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chucNang.add(see_btn);

        JLabel excel_btn = new JLabel("Xuất excel");
        excel_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        excel_btn.setIcon(new ImageIcon("src\\icon\\excel.png"));
        excel_btn.setHorizontalTextPosition(SwingConstants.CENTER);
        excel_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        excel_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chucNang.add(excel_btn);

        JPanel filter = new JPanel();

        menu_chucnang.add(filter);
        FlowLayout fl_filter = new FlowLayout(FlowLayout.CENTER, 0, 30);
        filter.setLayout(fl_filter);
        filter.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

        JPanel filter_contain = new JPanel();
        filter.add(filter_contain);
        filter_contain.setLayout(new GridLayout(1, 2, 20, 0));

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setMaximumRowCount(15);
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        filter_contain.add(comboBox);
        comboBox.addItem("Tất cả");
        comboBox.addItem("Theo tên");
        comboBox.addItem("Theo id");
        comboBox.setSelectedItem(0);

        textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        textField.setColumns(15);
        filter_contain.add(textField);

        JPanel table_kh = new JPanel();
        table_kh.setLocation(0, 130);
        table_kh.setSize(1086, 583);
        Viewthongke.add(table_kh);

        JTable tableDataKh = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        tableDataKh.setDefaultRenderer(Object.class, new defaulttablemode()); 
        tableDataKh.setModel(model);
        model.addColumn("Stt");
        model.addColumn("Id");
        model.addColumn("Tên sản phẩm");
        model.addColumn("Loại");
        model.addColumn("Chất liệu");
        model.addColumn("Giá bán");
        model.addColumn("Ngày thêm");

        table_kh.setLayout(new GridLayout(0, 1, 0, 0));
        JScrollPane pane = new JScrollPane(tableDataKh);
        model.setRowCount(20);
        tableDataKh.setRowHeight(24);
        table_kh.add(pane);

        return Viewthongke;

    }
}
