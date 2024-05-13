package test;

import java.awt.GridLayout;

import javax.swing.*;

public class CustomInputDialog {
    public static void main(String[] args) {
        // Tạo các thành phần trong cửa sổ tùy chỉnh
        JTextField textField1 = new JTextField();
        textField1.setColumns(20);
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        JComboBox<Integer> comboBox = new JComboBox<>(model);
        model.addElement(1);
        model.addElement(4);
        model.addElement(12);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Giá trị 1:"));
        panel.add(textField1);
        panel.add(new JLabel("Giá trị 2:"));
        panel.setLayout(new GridLayout(2, 1,20,20));
        panel.add(comboBox);

        // Hiển thị cửa sổ tùy chỉnh và xử lý sự kiện khi người dùng nhấn nút
        int result = JOptionPane.showConfirmDialog(null, panel, "Nhập giá trị", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String value1 = textField1.getText();

            // Hiển thị các giá trị lấy được

            System.out.println(value1);
            System.out.println(model.getSelectedItem());
        }

    }
}
