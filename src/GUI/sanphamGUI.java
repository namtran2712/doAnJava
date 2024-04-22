package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.productBUS;
import DTO.listProductCategory;
import DTO.categoryDTO;
import DTO.productDTO;
// import controller.productcontroller;
import DAO.categoryDAO;
import DAO.materialDAO;
import DAO.productDao;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class sanphamGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel sanphamFrame;
	productDao pDao = new productDao();
	categoryDAO cateDao = new categoryDAO();

	public JPanel View() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 750);
		sanphamFrame = new JPanel();
		sanphamFrame.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(sanphamFrame);
		sanphamFrame.setLayout(null);
		sanphamFrame.add(new menuChucNang().createmenuChucNang());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.setBounds(0, 118, 1086, 600);
		sanphamFrame.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 4, 10, 30));
		panel.setBackground(Color.white);

		productBUS listProduct = new productBUS();
		int index = 0;
		while (index < listProduct.getQuantityProducts()) {
			productDTO product = listProduct.getProduct(index);
			String name = product.getName();
			String material = listProduct.getMaterialProduct(product.getIdMaterial() - 1);
			String img = product.getLinkImg();
			String price = listProduct.getDefaultPrice(index);
			String category = listProduct.getCategoryProduct(product.getIdCategory() - 1);
			int quantitySold = product.getQuantitySold();
			int quantityRemain = product.getParticularProducts().get(0).getQuantityRemain();
			JPanel itempanel = new item().createItem(name, price, category, img);

			itempanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					System.out.println(1);
					System.out.println(e);
					showProduct sw = new showProduct();
					sw.showSanPham(img, category, material, name, price, quantitySold, quantityRemain);

				}

			});
			panel.add(itempanel);
			index++;
		}

		return sanphamFrame;

	}

}
