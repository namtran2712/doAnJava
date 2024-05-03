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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DTO.accountDTO;
import controller.taiKhoanController;

public class ViewUpdateAccount extends JFrame {
    public int id;
    public JDialog viewUpdate;
    public JComboBox<String> comboBoxAuthorize;
    public JTextField textFieldUsername;
    public JTextField textFieldPassword;

    public JDialog view(accountDTO acc) {
        id = acc.getIdAccount();
        viewUpdate = new JDialog();
        viewUpdate.setSize(500, 400);
        viewUpdate.setTitle("Sửa tài khoản");
        viewUpdate.setLocationRelativeTo(null);
        viewUpdate.setLayout(new BorderLayout(20, 20));
        viewUpdate.setResizable(false);

        JPanel panelTitle = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelTitle.setBackground(Color.PINK);

        JLabel title = new JLabel("Thông tin có thể sửa", JLabel.CENTER);
        panelTitle.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        JPanel panelContent = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel labelAuthorize = new JLabel("Vai trò");
        panelContent.add(labelAuthorize);
        labelAuthorize.setFont(new Font("Arial", Font.ITALIC, 20));
        labelAuthorize.setPreferredSize(new Dimension(200, 50));

        JTextField textFieldAuthorize = new JTextField(11);
        textFieldAuthorize.setText(acc.getVaiTro().getName());
        textFieldAuthorize.setPreferredSize(new Dimension(200, 40));
        panelContent.add(textFieldAuthorize);
        textFieldAuthorize.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldAuthorize.setMargin(new Insets(5, 5, 5, 5));
        textFieldAuthorize.setEditable(false);

        JLabel labelUsername = new JLabel("Username");
        panelContent.add(labelUsername);
        labelUsername.setFont(new Font("Arial", Font.ITALIC, 20));
        labelUsername.setPreferredSize(new Dimension(200, 50));

        textFieldUsername = new JTextField(11);
        textFieldUsername.setText(acc.getUsername());
        panelContent.add(textFieldUsername);
        textFieldUsername.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldUsername.setMargin(new Insets(5, 5, 5, 5));
        textFieldUsername.setEditable(false);

        JLabel labelPassword = new JLabel("Password");
        panelContent.add(labelPassword);
        labelPassword.setFont(new Font("Arial", Font.ITALIC, 20));
        labelPassword.setPreferredSize(new Dimension(200, 50));

        textFieldPassword = new JTextField(11);
        textFieldPassword.setText(acc.getPassword());
        panelContent.add(textFieldPassword);
        textFieldPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldPassword.setMargin(new Insets(5, 5, 5, 5));

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnUpdate = new JButton("Sửa");
        panelButton.add(btnUpdate);
        btnUpdate.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        btnUpdate.setBackground(new Color(85, 239, 196, 1));
        btnUpdate.setOpaque(false);
        btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUpdate.setPreferredSize(new Dimension(100, 40));
        btnUpdate.addActionListener(new taiKhoanController(this));

        viewUpdate.add(panelTitle, BorderLayout.NORTH);
        viewUpdate.add(panelContent, BorderLayout.CENTER);
        viewUpdate.add(panelButton, BorderLayout.SOUTH);

        viewUpdate.setModalityType(ModalityType.MODELESS);
        viewUpdate.setModal(true);
        viewUpdate.setVisible(true);

        return viewUpdate;
    }

    // public static void main(String[] args) {
    // JDialog a = new ViewUpdateAccount().view(null);
    // a.setVisible(true);
    // }
}
