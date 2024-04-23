package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DTO.staffDTO;
import controller.nhanVienController;

public class ViewUpdateStaff extends JFrame {

    public JTextField textFieldFullName;
    public JTextField textFieldPhone;
    public JTextField textFieldBirthday;
    public JTextField textFieldSalary;
    public int id;
    public JDialog content;

    public JDialog view(staffDTO s) {
        id = s.getId();
        content = new JDialog(this, "Sửa thông tin", false);
        content.setSize(500, 450);
        content.setLocationRelativeTo(null);

        JPanel panelTitle = new JPanel(new FlowLayout());
        panelTitle.setBackground(Color.PINK);

        JLabel title = new JLabel("Thông tin có thể sửa");
        panelTitle.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        JPanel panelContent = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel labelFullname = new JLabel("Tên đầy đủ:");
        labelFullname.setFont(new Font("Arial", Font.BOLD, 20));
        labelFullname.setPreferredSize(new Dimension(150, 50));
        panelContent.add(labelFullname);

        textFieldFullName = new JTextField(15);
        textFieldFullName.setText(s.getName());
        textFieldFullName.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldFullName.setMargin(new Insets(5, 5, 5, 5));
        panelContent.add(textFieldFullName);

        JLabel labelPhone = new JLabel("Số điện thoại:");
        labelPhone.setFont(new Font("Arial", Font.BOLD, 20));
        labelPhone.setPreferredSize(new Dimension(150, 50));
        panelContent.add(labelPhone);

        textFieldPhone = new JTextField(15);
        textFieldPhone.setText(s.getPhoneNumber());
        textFieldPhone.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldPhone.setMargin(new Insets(5, 5, 5, 5));
        panelContent.add(textFieldPhone);

        JLabel labelBirthday = new JLabel("Ngày sinh:");
        labelBirthday.setFont(new Font("Arial", Font.BOLD, 20));
        labelBirthday.setPreferredSize(new Dimension(150, 50));
        panelContent.add(labelBirthday);

        textFieldBirthday = new JTextField(15);
        textFieldBirthday.setText(s.getBirthday() + "");
        textFieldBirthday.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldBirthday.setMargin(new Insets(5, 5, 5, 5));
        panelContent.add(textFieldBirthday);

        JLabel labelSalary = new JLabel("Lương:");
        labelSalary.setFont(new Font("Arial", Font.BOLD, 20));
        labelSalary.setPreferredSize(new Dimension(150, 50));
        panelContent.add(labelSalary);

        textFieldSalary = new JTextField(15);
        String salary = String.format("%.0f", s.getSalary());
        textFieldSalary.setText(salary);
        textFieldSalary.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldSalary.setMargin(new Insets(5, 5, 5, 5));
        panelContent.add(textFieldSalary);

        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JButton btnUpdate = new JButton("Sửa");
        btnUpdate.setPreferredSize(new Dimension(100, 40));
        btnUpdate.setBackground(new Color(85, 239, 196, 1));
        btnUpdate.setOpaque(false);
        btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelFooter.add(btnUpdate);
        btnUpdate.addActionListener(new nhanVienController(this));

        content.add(panelTitle, BorderLayout.NORTH);
        content.add(panelContent, BorderLayout.CENTER);
        content.add(panelFooter, BorderLayout.SOUTH);

        content.setModalityType(ModalityType.MODELESS);
        content.setModal(true);
        content.setVisible(true);

        return content;
    }
}
