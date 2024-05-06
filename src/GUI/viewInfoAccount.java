package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class viewInfoAccount extends JFrame {

    public JPanel viewInfo() {
        JPanel view = new JPanel();
        setContentPane(view);
        view.setSize(1100, 700);
        view.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        view.setLayout(new GridLayout(1, 2));

        JPanel panelContent = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        view.add(panelContent);

        JPanel panelTitle = new JPanel();
        JLabel labelTitle = new JLabel("Thông tin tài khoản", JLabel.CENTER);
        panelTitle.add(labelTitle);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 30));
        labelTitle.setForeground(Color.WHITE);
        labelTitle.setPreferredSize(new Dimension(550, 50));
        panelTitle.setBackground(Color.pink);

        panelContent.add(panelTitle);

        JPanel panelCenter = new JPanel(new GridLayout(7, 1, 10, 10));
        Font haha = new Font("hehe", Font.BOLD, 20);
        JLabel labelFullName = new JLabel("    Tên đầy đủ: " + "", JLabel.LEFT);
        labelFullName.setPreferredSize(new Dimension(200, 50));
        // labelFullName.setHorizontalAlignment();

        JLabel labelPhone = new JLabel("    Số điện thoại: " + "", JLabel.LEFT);
        labelPhone.setPreferredSize(new Dimension(550, 50));

        JLabel labelBirday = new JLabel("   Ngày sinh: " + "", JLabel.LEFT);
        labelBirday.setPreferredSize(new Dimension(550, 50));

        JLabel labelSalary = new JLabel("   Lương: " + "", JLabel.LEFT);

        JLabel labelAuthor = new JLabel("   Chức vụ: " + "", JLabel.LEFT);
        JLabel labelUsername = new JLabel("    Tên đăng nhập: " + "", JLabel.LEFT);
        JLabel labelPassword = new JLabel("    Mật khẩu: " + "", JLabel.LEFT);

        labelFullName.setFont(haha);
        labelPhone.setFont(haha);
        labelBirday.setFont(haha);
        labelSalary.setFont(haha);
        labelAuthor.setFont(haha);
        labelUsername.setFont(haha);
        labelPassword.setFont(haha);

        panelCenter.add(labelFullName);
        panelCenter.add(labelPhone);
        panelCenter.add(labelBirday);
        panelCenter.add(labelSalary);
        panelCenter.add(labelAuthor);
        panelCenter.add(labelUsername);
        panelCenter.add(labelPassword);

        panelContent.add(panelCenter);

        JLabel labelImage = new JLabel();
        labelImage.setIcon(new ImageIcon(formLoginView.class.getResource("/image/backgroundInfo.jpg")));
        view.add(labelImage);

        view.setVisible(true);
        return view;
    }

    public static void main(String[] args) {
        JFrame a = new JFrame();
        a.setSize(1100, 700);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel b = new viewInfoAccount().viewInfo();
        a.add(b);
        a.setVisible(true);
    }
}
