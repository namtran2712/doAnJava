package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.border.MatteBorder;

// import controller.productcontroller;

@SuppressWarnings("unused")
public class item extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel container;
	private boolean isSelect;

	public static String price(float d) {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("###,###.##");
		String formattedPrice = df.format(d) + "đ";
		return formattedPrice;
	}

	public static float convertPrice(String s) {
		float price = Float.parseFloat(
				s.substring(0, s.length() - 1).replace(".", ""));
		return price;
	};

	public JPanel createItem(String nameProduct, String priceProduct, String catagogries, String imgProduct) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create border
		Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
		CompoundBorder compoundBorder = new CompoundBorder(emptyBorder, border);
		setSize(250, 350);
		container = new JPanel();
		container.setBackground(new Color(255, 255, 255));
		container.setSize(new Dimension(250, 350));
		container.setBorder(compoundBorder);

		setContentPane(container);
		container.setLayout(new BorderLayout(0, 10));

		JPanel bottom_itemContainer = new JPanel();
		bottom_itemContainer.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		bottom_itemContainer.setBackground(new Color(255, 255, 255));
		container.add(bottom_itemContainer, BorderLayout.SOUTH);
		bottom_itemContainer.setLayout(new GridLayout(1, 2, 0, 0));

		bottom_itemContainer.setPreferredSize(new Dimension(200, 50));

		JLabel price = new JLabel(priceProduct);
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Times New Roman", Font.BOLD, 18));
		price.setOpaque(true);
		price.setAlignmentY(bottom_itemContainer.getHeight() / 2);
		price.setBackground(new Color(255, 202, 212));
		bottom_itemContainer.add(price);

		JLabel loai = new JLabel(catagogries);
		loai.setHorizontalTextPosition(SwingConstants.CENTER);
		loai.setHorizontalAlignment(SwingConstants.CENTER);
		loai.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bottom_itemContainer.add(loai);
		loai.setAlignmentY(bottom_itemContainer.getHeight() / 2);

		JPanel item_wrapper = new JPanel();
		container.add(item_wrapper, BorderLayout.CENTER);
		item_wrapper.setLayout(new GridLayout(0, 1, 0, 0));
		item_wrapper.setPreferredSize(new Dimension(200, 250));

		JLabel main_img = new JLabel("");
		main_img.setHorizontalAlignment(SwingConstants.CENTER);
		main_img.setBackground(Color.WHITE);

		ImageIcon icon = new ImageIcon(imgProduct);
		// Image image = new ImageIcon(imgProduct).getImage();
		Image image = icon.getImage();
		Image scImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		main_img.setIcon(new ImageIcon(scImage));
		item_wrapper.add(main_img);

		JPanel top = new JPanel();
		container.add(top, BorderLayout.NORTH);
		top.setPreferredSize(new Dimension(200, 50));
		top.setLayout(new GridLayout(0, 1, 0, 0));

		JTextArea tensp = new JTextArea(nameProduct);
		tensp.setWrapStyleWord(true);
		tensp.setRows(2);
		tensp.setPreferredSize(new Dimension(200, 50));
		tensp.setMargin(new Insets(0, 15, 10, 15));
		tensp.setLineWrap(true);
		tensp.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		tensp.setMaximumSize(new Dimension(200, 50));
		tensp.setColumns(20);
		tensp.setEnabled(false);
		tensp.setDisabledTextColor(Color.BLACK);

		top.add(tensp);

		return container;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public boolean isSelect() {
		return isSelect;
	}
	

	
}
