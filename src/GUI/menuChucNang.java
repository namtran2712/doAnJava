package GUI;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class menuChucNang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField;
	public JLabel bin_btn;
	public JLabel see_btn;
	public JLabel search_btn;
	public JComboBox<String> comboBox;

	public JPanel createmenuChucNang() {
		JPanel menu_chucnang = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		menu_chucnang.setBounds(0, 10, 1010, 110);
		menu_chucnang.setLayout(new GridLayout(1, 2));
		contentPane.add(menu_chucnang);

		JPanel chucNang = new JPanel();
		chucNang.setLayout(new GridLayout(1, 5, 10, 20));
		chucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		menu_chucnang.add(chucNang);

		bin_btn = new JLabel("Xóa");
		bin_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		bin_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		bin_btn.setIcon(new ImageIcon("src\\icon\\bin.png"));
		bin_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		bin_btn.setHorizontalTextPosition(SwingConstants.CENTER);
		chucNang.add(bin_btn);

		search_btn = new JLabel("Sửa");
		search_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		search_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		search_btn.setIcon(new ImageIcon("src\\icon\\pem.png"));
		search_btn.setHorizontalTextPosition(SwingConstants.CENTER);
		search_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		chucNang.add(search_btn);

		see_btn = new JLabel("Xem chi tiết");
		see_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		see_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		see_btn.setIcon(new ImageIcon("src\\icon\\see.png"));
		see_btn.setHorizontalTextPosition(SwingConstants.CENTER);
		see_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		chucNang.add(see_btn);

		JLabel excel_btn = new JLabel("Xuất excel");
		excel_btn.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		excel_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		excel_btn.setIcon(new ImageIcon("src\\icon\\excel.png"));
		excel_btn.setHorizontalTextPosition(SwingConstants.CENTER);
		excel_btn.setVerticalTextPosition(SwingConstants.BOTTOM);
		chucNang.add(excel_btn);

		JPanel filter = new JPanel();
		menu_chucnang.add(filter);
		FlowLayout fl_filter = new FlowLayout(FlowLayout.CENTER, 0, 30);
		filter.setLayout(fl_filter);
		filter.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));

		JPanel filter_contain = new JPanel();
		filter.add(filter_contain);
		filter_contain.setLayout(new GridLayout(1, 2, 20, 0));

		comboBox = new JComboBox<String>();
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

		return menu_chucnang;
	}

}
