package GUI;

import DTO.particularProduct;
import DTO.productDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import BUS.productBUS;

public class showProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_4;
	private JPanel SizeMenu;
	private ArrayList<JButton> listSize;
	productBUS pbus = new productBUS();

	public JDialog showSanPham(productDTO product, int index) {
		String name = product.getName();
		String material = pbus.getMaterialProduct(product.getIdMaterial());
		String img = product.getLinkImg();
		String price = pbus.getDefaultPrice(product);
		String category = pbus.getCategoryProduct(product.getIdCategory());
		int quantitySold = product.getQuantitySold();
		int quantityRemain = product.getParticularProducts().get(0).getQuantityRemain();

		JDialog contentPane;

		contentPane = new JDialog(this, "Chi tiết");
		contentPane.setSize(500, 850);
		contentPane.setLocationRelativeTo(null);
		contentPane.setBackground(new Color(255, 255, 255));

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

		JLabel lblNewLabel_3 = new JLabel("Đã bán: " + quantitySold);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		panel.add(lblNewLabel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		infoProduct.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("Chất liệu: " + material);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		panel_1.add(lblNewLabel_2);

		lblNewLabel_4 = new JLabel("Còn lại: " + quantityRemain);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		panel_1.add(lblNewLabel_4);

		lblNewLabel = new JLabel(price + "₫");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(50, 770, 400, 50);
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

		SizeMenu = new JPanel(new GridLayout(1, 0, 5, 0));
		SizeMenu.setBounds(50, 710, 400, 50);
		listSize = new ArrayList<>();

		int sizecnt = 0;
		ArrayList<particularProduct> particularProducts = product.getParticularProducts();
		while (sizecnt < particularProducts.size()) {
			JButton tmp = new JButton(particularProducts.get(sizecnt).getSize() + "");
			tmp.setCursor(new Cursor(12));
			tmp.setBackground(Color.white);
			tmp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int size = Integer.parseInt(e.getActionCommand());
					String price = pbus.getPrice(index, size);
					int quantityRemain = pbus.quantitySize(product, size);
					changeInfor(size, price, quantityRemain);
				}
			});
			listSize.add(tmp);
			sizecnt++;
		}
		listSize.get(0).setBackground(Color.decode("#FFCAD4"));
		for (JButton tmp : listSize) {
			SizeMenu.add(tmp);
		}
		contentPane.add(SizeMenu);

		contentPane.setModal(true);
		contentPane.setVisible(true);

		return contentPane;

	}

	void changeInfor(int size, String price, int quantityRemain) {
		for (JButton tmp : listSize) {
			tmp.setBackground(Color.white);
			if (tmp.getText().equals(String.valueOf(size))) {
				tmp.setBackground(Color.decode("#FFCAD4"));
				lblNewLabel.setText(price + "₫");
				lblNewLabel_4.setText("Còn lại: " + quantityRemain);

			}

		}

	}
}
