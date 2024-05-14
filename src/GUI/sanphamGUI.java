package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.productBUS;
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
		sanphamFrame.setLayout(new BorderLayout());
		menuChucNang chucnang =new menuChucNang();
		sanphamFrame.add(chucnang.createmenuChucNang(), BorderLayout.NORTH);
		
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.setBounds(0, 118, 1086, 600);
		sanphamFrame.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 4, 10, 30));
		panel.setBackground(Color.white);

		productBUS listProduct = new productBUS();
		int index = 0;
		while (index < listProduct.getQuantityProducts()) {
			productDTO product = listProduct.getProduct(index);
			String name = product.getName();
			String img = product.getLinkImg();
			String price = listProduct.getDefaultPrice(product);
			String category = listProduct.getCategoryProduct(product.getIdCategory());
			JPanel itempanel = new item().createItem(name, price, category, img);
			int id =index;
			itempanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mousePressed(e);
					if (e.getClickCount() ==2)
					{
						showProduct sw = new showProduct();
						sw.showSanPham(product,id);
					}

				}

			});
			panel.add(itempanel);
			index++;
		}


		chucnang.add_btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				ViewaddProduct view =new ViewaddProduct();
				JDialog showView =view.addProduct();
				showView.setVisible(true);
			}

			
			 
		});
		return sanphamFrame;

	}


}
