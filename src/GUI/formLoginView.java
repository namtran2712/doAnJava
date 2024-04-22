package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.formButtonLoginController;

import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class formLoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel formLogin;
	private JTextField fieldUsername;
	private JPasswordField password;

	formButtonLoginController actformLogin = new formButtonLoginController(this);
	MouseListener formLoginClick = (MouseListener) new formButtonLoginController(this);
	private JLabel labelForget;
	private JLabel labelRegister;

	public formLoginView(String username, String pass) {

		setTitle("Đăng nhập");
		setIconImage(Toolkit.getDefaultToolkit().getImage(formLoginView.class.getResource("/icon/iconLogin.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(1, 2));

		this.setResizable(false);

		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(formLoginView.class.getResource("/image/imageBackground.jpg")));
		contentPane.add(lblNewLabel);
		formLogin = new JPanel();
		formLogin.setBackground(Color.WHITE);
		formLogin.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel labelImage = new JLabel("");
		labelImage.setIcon(new ImageIcon(formLoginView.class.getResource("/image/imagelogin.png")));
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		formLogin.add(labelImage);

		JPanel panelField = new JPanel();
		panelField.setBackground(Color.WHITE);
		formLogin.add(panelField);
		panelField.setLayout(new GridLayout(4, 1, 0, 0));

		JPanel panelUsername = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelUsername.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(25);
		panelUsername.setBackground(Color.WHITE);
		panelField.add(panelUsername);

		JLabel iconUser = new JLabel("");
		iconUser.setIcon(new ImageIcon(formLoginView.class.getResource("/icon/iconAccount.png")));
		panelUsername.add(iconUser);

		fieldUsername = new JTextField();
		fieldUsername.setText(username);
		fieldUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panelUsername.add(fieldUsername);
		fieldUsername.setColumns(15);

		JPanel panelPass = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelPass.getLayout();
		flowLayout.setHgap(25);
		flowLayout.setVgap(20);
		panelPass.setBackground(Color.WHITE);
		panelField.add(panelPass);

		JLabel iconPass = new JLabel("");
		iconPass.setIcon(new ImageIcon(formLoginView.class.getResource("/icon/iconPassword.png")));
		panelPass.add(iconPass);

		password = new JPasswordField();
		password.setText(pass);
		password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		password.setColumns(15);
		panelPass.add(password);

		JPanel panelTask = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelTask.getLayout();
		flowLayout_2.setVgap(30);
		flowLayout_2.setHgap(100);
		panelTask.setBackground(Color.WHITE);
		panelField.add(panelTask);

		labelForget = new JLabel("<html><u>Quên mật khẩu?</u></html>");
		labelForget.setForeground(Color.BLUE);
		labelForget.setHorizontalAlignment(SwingConstants.CENTER);
		labelForget.setFont(new Font("Times New Roman", Font.BOLD, 18));
		labelForget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelTask.add(labelForget);

		labelRegister = new JLabel("<html><u>Đăng ký</u></html>");
		labelRegister.setForeground(Color.BLUE);
		labelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		labelRegister.setFont(new Font("Times New Roman", Font.BOLD, 18));
		labelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelTask.add(labelRegister);
		labelRegister.addMouseListener(formLoginClick);

		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) panelButton.getLayout();
		flowLayout_3.setVgap(10);
		panelField.add(panelButton);

		JButton buttonLogin = new JButton("Đăng nhập");
		buttonLogin.setForeground(Color.WHITE);
		buttonLogin.setBackground(Color.PINK);
		buttonLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelButton.add(buttonLogin);
		buttonLogin.addActionListener(actformLogin);
		buttonLogin.addKeyListener(new formButtonLoginController(this));

		getContentPane().add(formLogin);

		setVisible(true);
	}

	public JTextField getFieldUsername() {
		return fieldUsername;
	}

	public void setFieldUsername(JTextField fieldUsername) {
		this.fieldUsername = fieldUsername;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JLabel getLabelForget() {
		return labelForget;
	}

	public void setLabelForget(JLabel labelForget) {
		this.labelForget = labelForget;
	}

	public JLabel getLabelRegister() {
		return labelRegister;
	}

	public void setLabelRegister(JLabel labelRegister) {
		this.labelRegister = labelRegister;
	}

}
