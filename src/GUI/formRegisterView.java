package GUI;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.formButtonRegisterController;

import java.awt.Toolkit;
import java.util.Date;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class formRegisterView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel formRegister;
    private JTextField fieldFullName;
    private JTextField fieldPhoneNumber;
    private JTextField fieldUser;
    private JPasswordField passwordField;
    private JDateChooser birthdayChooser;

    formButtonRegisterController actFormButton = new formButtonRegisterController(this);

    /**
     * Create the frame.
     */
    public formRegisterView() {
        setIconImage(
                Toolkit.getDefaultToolkit().getImage(formRegisterView.class.getResource("/icon/iconRegister.png")));
        setTitle("Đăng ký");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        this.setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(1, 2));

        setContentPane(contentPane);
        formRegister = new JPanel();
        formRegister.setBackground(Color.WHITE);
        formRegister.setLayout(new GridLayout(7, 1, 0, 0));

        JLabel labelTitle = new JLabel("Đăng ký");
        labelTitle.setBackground(Color.WHITE);
        labelTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        formRegister.add(labelTitle);

        JPanel panelFullName = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panelFullName.getLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(30);
        panelFullName.setBackground(Color.WHITE);
        formRegister.add(panelFullName);

        JLabel iconFullName = new JLabel("");
        iconFullName.setIcon(new ImageIcon(formRegisterView.class.getResource("/icon/iconFullName.png")));
        iconFullName.setHorizontalAlignment(SwingConstants.CENTER);
        panelFullName.add(iconFullName);

        fieldFullName = new JTextField();
        fieldFullName.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (fieldFullName.getText().equals("Nhập vào họ tên")) {
                    fieldFullName.setText("");
                    fieldFullName.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                if (fieldFullName.getText().equals("")) {
                    fieldFullName.setText("Nhập vào họ tên");
                    fieldFullName.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
        fieldFullName.setText("Nhập vào họ tên");
        fieldFullName.setForeground(Color.LIGHT_GRAY);
        fieldFullName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelFullName.add(fieldFullName);
        fieldFullName.setColumns(15);

        JPanel panelPhoneNumber = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panelPhoneNumber.getLayout();
        flowLayout_1.setVgap(20);
        flowLayout_1.setHgap(40);
        panelPhoneNumber.setBackground(Color.WHITE);
        formRegister.add(panelPhoneNumber);

        JLabel iconPhoneNumber = new JLabel("");
        iconPhoneNumber.setIcon(new ImageIcon(formRegisterView.class.getResource("/icon/iconPhoneNumber.png")));
        iconPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        panelPhoneNumber.add(iconPhoneNumber);

        fieldPhoneNumber = new JTextField();
        fieldPhoneNumber.setForeground(Color.LIGHT_GRAY);
        fieldPhoneNumber.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (fieldPhoneNumber.getText().equals("Nhập vào số điện thoại")) {
                    fieldPhoneNumber.setText("");
                    fieldPhoneNumber.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                if (fieldPhoneNumber.getText().equals("")) {
                    fieldPhoneNumber.setText("Nhập vào số điện thoại");
                    fieldPhoneNumber.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
        fieldPhoneNumber.setText("Nhập vào số điện thoại");
        fieldPhoneNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fieldPhoneNumber.setColumns(15);
        panelPhoneNumber.add(fieldPhoneNumber);

        JPanel panelUsername = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panelUsername.getLayout();
        flowLayout_2.setVgap(20);
        flowLayout_2.setHgap(40);
        panelUsername.setBackground(Color.WHITE);
        formRegister.add(panelUsername);

        JLabel iconUser = new JLabel("");
        iconUser.setIcon(new ImageIcon(formRegisterView.class.getResource("/icon/iconAccount.png")));
        iconUser.setHorizontalAlignment(SwingConstants.CENTER);
        panelUsername.add(iconUser);

        fieldUser = new JTextField();
        fieldUser.setForeground(Color.LIGHT_GRAY);
        fieldUser.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (fieldUser.getText().equals("Nhập vào tên đăng nhập")) {
                    fieldUser.setText("");
                    fieldUser.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                if (fieldUser.getText().equals("")) {
                    fieldUser.setText("Nhập vào tên đăng nhập");
                    fieldUser.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(formRegisterView.class.getResource("/image/imgBackgroundRegister.jpg")));
        contentPane.add(lblNewLabel);
        fieldUser.setText("Nhập vào tên đăng nhập");
        fieldUser.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fieldUser.setColumns(15);
        panelUsername.add(fieldUser);

        JPanel panelPassword = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panelPassword.getLayout();
        flowLayout_3.setVgap(20);
        flowLayout_3.setHgap(40);
        panelPassword.setBackground(Color.WHITE);
        formRegister.add(panelPassword);

        JLabel iconPass = new JLabel("");
        iconPass.setIcon(new ImageIcon(formRegisterView.class.getResource("/icon/iconPassword.png")));
        iconPass.setHorizontalAlignment(SwingConstants.CENTER);
        panelPassword.add(iconPass);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passwordField.setColumns(15);
        panelPassword.add(passwordField);

        JPanel panelBirthday = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) panelBirthday.getLayout();
        flowLayout_4.setVgap(20);
        flowLayout_4.setHgap(40);
        panelBirthday.setBackground(Color.WHITE);
        formRegister.add(panelBirthday);

        JLabel iconBirthday = new JLabel("");
        iconBirthday.setIcon(new ImageIcon(formRegisterView.class.getResource("/icon/iconBirthday.png")));
        iconBirthday.setHorizontalAlignment(SwingConstants.CENTER);
        panelBirthday.add(iconBirthday);

        birthdayChooser = new JDateChooser();
        birthdayChooser.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelBirthday.add(birthdayChooser);
        birthdayChooser.setPreferredSize(new Dimension(230, 35));
        birthdayChooser.setDate(new Date());

        JPanel panelButton = new JPanel();
        panelButton.setBackground(Color.WHITE);
        FlowLayout flowLayout_5 = (FlowLayout) panelButton.getLayout();
        flowLayout_5.setVgap(30);
        flowLayout_5.setHgap(40);
        formRegister.add(panelButton);

        JButton buttonRegisterToLogin = new JButton("Đăng nhập");
        buttonRegisterToLogin.setForeground(Color.WHITE);
        buttonRegisterToLogin.setBackground(Color.PINK);
        buttonRegisterToLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelButton.add(buttonRegisterToLogin);
        buttonRegisterToLogin.setPreferredSize(new Dimension(150, 40));
        buttonRegisterToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonRegisterToLogin.addActionListener(actFormButton);

        JButton buttonRegister = new JButton("Đăng ký");
        buttonRegister.setForeground(Color.WHITE);
        buttonRegister.setBackground(Color.PINK);
        buttonRegister.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelButton.add(buttonRegister);
        buttonRegister.setPreferredSize(new Dimension(150, 40));
        buttonRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonRegister.addActionListener(actFormButton);
        buttonRegister.addKeyListener(new formButtonRegisterController(this));

        contentPane.add(formRegister);

        setVisible(true);
    }

    public JTextField getFieldFullName() {
        return fieldFullName;
    }

    public void setFieldFullName(JTextField fieldFullName) {
        this.fieldFullName = fieldFullName;
    }

    public JTextField getFieldPhoneNumber() {
        return fieldPhoneNumber;
    }

    public void setFieldPhoneNumber(JTextField fieldPhoneNumber) {
        this.fieldPhoneNumber = fieldPhoneNumber;
    }

    public JTextField getFieldUser() {
        return fieldUser;
    }

    public void setFieldUser(JTextField fieldUser) {
        this.fieldUser = fieldUser;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JDateChooser getBirthdayChooser() {
        return birthdayChooser;
    }

    public void setBirthdayChooser(JDateChooser birthdayChooser) {
        this.birthdayChooser = birthdayChooser;
    }
}
