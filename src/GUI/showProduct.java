package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.*;
import javax.swing.*;

public class showProduct extends JFrame {

	private static final long serialVersionUID = 1L;

	public JDialog showSanPham(String img, String category, String material, String name, String price,
			int quantityProduct, int quantityRemain) {
		JDialog contentPane;
		contentPane = new JDialog(this, "Chi tiết");
		contentPane.setSize(500, 850);
		contentPane.setLocationRelativeTo(null);
		// setUndecorated(true);
		contentPane.setBackground(new Color(255, 255, 255));
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);

		JLabel showProductImage = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(img);
		Image scaledImage = imageIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		showProductImage.setIcon(new ImageIcon(scaledImage));
		showProductImage.setHorizontalAlignment(SwingConstants.CENTER);
		showProductImage.setBounds(50, 100, 400, 400);

		contentPane.add(showProductImage);

		JPanel infoProduct = new JPanel();
		infoProduct.setBackground(new Color(255, 255, 255));
		infoProduct.setBounds(50, 600, 400, 100);
		contentPane.add(infoProduct);
		infoProduct.setLayout(new GridLayout(1, 2, 50, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		infoProduct.add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Loại: " + category);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Đã bán: " + quantityProduct);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		panel.add(lblNewLabel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		infoProduct.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Chất liệu: " + material);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_4 = new JLabel("Còn lại: " + quantityRemain);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel = new JLabel(price + "₫");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(50, 732, 400, 50);
		contentPane.add(lblNewLabel);

		JTextArea nameProduct = new JTextArea(name);
		nameProduct.setMargin(new Insets(2, 20, 2, 20));
		nameProduct.setFont(new Font("Times New Roman", Font.BOLD, 25));
		nameProduct.setBounds(50, 511, 400, 78);
		nameProduct.setLineWrap(true);
		nameProduct.setWrapStyleWord(true);
		nameProduct.setEnabled(false);
		nameProduct.setDisabledTextColor(Color.black);
		contentPane.add(nameProduct);
		contentPane.setModal(true);
		contentPane.setVisible(true);
		return contentPane;

	}
}
