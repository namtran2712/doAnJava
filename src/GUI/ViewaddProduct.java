package GUI;

import java.awt.EventQueue;
import javax.swing.*;

import DAO.categoryDAO;
import DAO.getDataForAddProduct;
import DAO.materialDAO;
import DTO.categoryDTO;
import controller.addProductController;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ViewaddProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private JComboBox comboMaterial;
	@SuppressWarnings("rawtypes")
	private JComboBox comboCategory;
	public JTextField fieldProductName;
	public ArrayList<JTextField> listSize;
	public ArrayList<JTextField> listPrice;
	public ArrayList<JTextField> listQuantity;
	public JTextField fieldQuantitySize;
	private JLabel showProductImage;
	public int idcate;
	public int idmate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewaddProduct a = new ViewaddProduct();
					JDialog view = a.addProduct();
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JDialog addProduct() {
		JDialog view = new JDialog(this, "Thêm sản phẩm");

		ActionListener actButton = new addProductController(view, this);

		view.setSize(1100, 500);
		view.setLocationRelativeTo(null);
		view.setBackground(Color.white);

		view.setLayout(new BorderLayout(0, 0));

		JPanel panelTitle = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTitle.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		panelTitle.setBackground(new Color(255, 128, 192));
		view.add(panelTitle, BorderLayout.NORTH);

		JLabel labelTitle = new JLabel("Thêm mới một sản phẩm");
		labelTitle.setForeground(new Color(255, 255, 255));
		labelTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		panelTitle.add(labelTitle);

		JPanel panelContent = new JPanel();
		view.add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new GridLayout(0, 3, 0, 0));

		JPanel panelImage = new JPanel();
		panelContent.add(panelImage);
		panelImage.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton addImage = new JButton("Hình ảnh minh họa");
		addImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addImage.setForeground(new Color(255, 255, 255));
		addImage.setBackground(new Color(192, 192, 192));
		addImage.setOpaque(true);
		addImage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panelImage.add(addImage);
		addImage.addActionListener(actButton);

		showProductImage = new JLabel("");
		panelImage.add(showProductImage);

		JPanel panelInfo1 = new JPanel();
		panelContent.add(panelInfo1);
		FlowLayout fl_panelInfo1 = new FlowLayout(FlowLayout.CENTER, 15, 15);
		panelInfo1.setLayout(fl_panelInfo1);

		JLabel labelCategory = new JLabel("Loại");
		labelCategory.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labelCategory.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfo1.add(labelCategory);
		labelCategory.setPreferredSize(new Dimension(150, 30));

		String[] categories = getDataForAddProduct.getCategory();

		comboCategory = new JComboBox<String>(categories);
		panelInfo1.add(comboCategory);
		comboCategory.setFont(new Font("Times New Roman", Font.BOLD, 20));
		comboCategory.setPreferredSize(new Dimension(185, 30));
		comboCategory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboCategory.setBackground(Color.white);
		
		JLabel labelMaterial = new JLabel("Chất liệu");
		labelMaterial.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labelMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfo1.add(labelMaterial);
		labelMaterial.setPreferredSize(new Dimension(150, 30));
		
		String[] materials = getDataForAddProduct.getMaterial();
		
		comboMaterial = new JComboBox<String>(materials);
		comboMaterial.setSelectedItem(materials);
		panelInfo1.add(comboMaterial);
		comboMaterial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboMaterial.setFont(new Font("Times New Roman", Font.BOLD, 20));
		comboMaterial.setPreferredSize(new Dimension(185, 30));
		comboMaterial.setBackground(Color.white);
		
		JLabel labelProductName = new JLabel("Tên sản phẩm");
		labelProductName.setPreferredSize(new Dimension(150, 30));
		labelProductName.setHorizontalAlignment(SwingConstants.CENTER);
		labelProductName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panelInfo1.add(labelProductName);

		fieldProductName = new JTextField();
		fieldProductName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		fieldProductName.setPreferredSize(new Dimension(10, 30));
		fieldProductName.setHorizontalAlignment(SwingConstants.CENTER);
		fieldProductName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		fieldProductName.setMargin(new Insets(5, 5, 5, 5));
		panelInfo1.add(fieldProductName);
		fieldProductName.setColumns(14);

		JPanel panelInfo2 = new JPanel(new GridLayout(3, 1, 10, 0));
		panelContent.add(panelInfo2);

		JPanel panelQuantitySize = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
		panelInfo2.add(panelQuantitySize);

		JLabel labelQuantitySize = new JLabel("Số lượng size: ");
		panelQuantitySize.add(labelQuantitySize);
		labelQuantitySize.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuantitySize.setFont(new Font("Times New Roman", Font.BOLD, 20));

		fieldQuantitySize = new JTextField();
		panelQuantitySize.add(fieldQuantitySize);
		fieldQuantitySize.setColumns(5);
		fieldQuantitySize.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		fieldQuantitySize.setHorizontalAlignment(SwingConstants.CENTER);
		fieldQuantitySize.setMargin(new Insets(5, 5, 5, 5));

		JPanel panelSize = new JPanel(new GridLayout(1, 3, 10, 10));
		JScrollPane scrollPaneSize = new JScrollPane(panelSize);

		listSize = new ArrayList<JTextField>();
		listPrice = new ArrayList<JTextField>();
		listQuantity = new ArrayList<JTextField>();

		fieldQuantitySize.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					JTextField src = (JTextField) e.getComponent();
					try {
						int quantity1 = Integer.parseInt(src.getText());

						listPrice.clear();
						listQuantity.clear();
						listSize.clear();

						int quantityComponents = panelSize.getComponentCount();

						while (panelSize.getComponentCount() != 0) {
							panelSize.remove(0);
						}

						JLabel labelSize = new JLabel("Size", JLabel.CENTER);
						panelSize.add(labelSize);
						labelSize.setFont(new Font("Times New Roman", Font.BOLD, 20));

						JLabel labelPrice = new JLabel("Giá", JLabel.CENTER);
						panelSize.add(labelPrice);
						labelPrice.setFont(new Font("Times New Roman", Font.BOLD, 20));

						JLabel labelQuantity = new JLabel("Số lượng", JLabel.CENTER);
						panelSize.add(labelQuantity);
						labelQuantity.setFont(new Font("Times New Roman", Font.BOLD, 20));

						panelSize.setLayout(new GridLayout(quantity1 + 1, 3, 5, 5));

						scrollPaneSize.setVisible(true);
						for (int i = 0; i < quantity1; i++) {
							JTextField size = new JTextField();
							size.setFont(new Font("Times New Roman", Font.PLAIN, 17));
							size.setBorder(BorderFactory.createLineBorder(Color.gray));

							JTextField price = new JTextField();
							price.setFont(new Font("Times New Roman", Font.PLAIN, 17));
							price.setBorder(BorderFactory.createLineBorder(Color.gray));

							JTextField quantity2 = new JTextField();
							quantity2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
							quantity2.setBorder(BorderFactory.createLineBorder(Color.gray));

							listSize.add(size);
							listPrice.add(price);
							listQuantity.add(quantity2);
							panelSize.add(size);
							panelSize.add(price);
							panelSize.add(quantity2);
						}

					} catch (Exception warning) {
						JOptionPane.showMessageDialog(view, "Vui lòng điền vào là số!!!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						warning.printStackTrace();
					}
					src.transferFocus();
				}
			}
		});

		panelInfo2.add(scrollPaneSize);
		scrollPaneSize.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneSize.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		scrollPaneSize.setVisible(false);

		JPanel panelButton = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelButton.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		view.add(panelButton, BorderLayout.SOUTH);

		JButton createProduct = new JButton("Thêm sản phẩm");
		createProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createProduct.setForeground(new Color(255, 255, 255));
		createProduct.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		createProduct.setBackground(new Color(116, 185, 255));
		createProduct.setOpaque(true);
		panelButton.add(createProduct);
		createProduct.setPreferredSize(new Dimension(150, 30));
		createProduct.addActionListener(actButton);

		JButton cancel = new JButton("Hủy bỏ");
		cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancel.setForeground(new Color(255, 255, 255));
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cancel.setBackground(new Color(214, 48, 49));
		cancel.setOpaque(true);
		panelButton.add(cancel);
		cancel.setPreferredSize(new Dimension(150, 30));
		cancel.addActionListener(actButton);

		view.setModal(true);

		return view;
	}

	public void changeImage(ImageIcon imageIcon) {
		Image scaledImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		showProductImage.setIcon(scaledImageIcon);
		showProductImage.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public int getIdCategory ()
    {
		return  new categoryDAO().selectByName((String) comboCategory.getSelectedItem()).getIdProduct();
	}
	
	public int getIdMaterial ()
	{
		return  new materialDAO().selectByName((String) comboMaterial.getSelectedItem()).getId();
	}
}
