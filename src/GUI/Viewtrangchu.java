package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import BUS.accountBUS;
import BUS.productBUS;
import BUS.validateBUS;
import DTO.accountDTO;
import DTO.billDTO;
import DTO.categoryDTO;
import DTO.customerDTO;
import DTO.productDTO;
import DTO.materialDTO;
import DTO.particularBill;
import DTO.particularProduct;
import controller.menuItem_mouseController;
import DAO.accountDao;
import DAO.billDao;
import DAO.categoryDAO;
import DAO.customerDao;
import DAO.materialDAO;
import DAO.productDao;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("unused")
public class Viewtrangchu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel home_selected_1;
	private JPanel menuTrangchu;
	menuItem_mouseController mnuctrl = new menuItem_mouseController(this);
	private JTextField textField;
	private JTextField searchbarKh;
	private JTextField textField_1;
	public JPanel container;
	CardLayout card;
	private JButton btn_thanhtoan;
	private JButton conform;
	private validateBUS valid;
	private JLabel showcustomer_phone;
	private JLabel showcustomer_name;
	private JLabel showcustomer_id;
	private JLabel showcustomer_bod;
	public JPanel khachhangView;
	private JPanel customer_selected;
	private Viewkhachhang kh;
	int idCurrentShow = -1;
	private DefaultTableModel modelDataproduct;
	private JTable listproductsJTable;
	private float sum = 0;
	private accountDao accountDAO = new accountDao();
	private accountDTO account;
	private JLabel pr;
	private billDao billdao;
	private customerDTO cus;
	private ArrayList<particularBill> listbuy;
	public JPanel phieunhapView;
	public JPanel phieuxuatView;
	public JPanel tonkhoView;
	private productBUS listProduct;
	public JPanel thongkeView;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewtrangchu frame = new Viewtrangchu("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Viewtrangchu(String username) throws Exception {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		account = accountDAO.selectByUsername(username);
		listbuy = new ArrayList<>();
		menuTrangchu = new JPanel() {
			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				Graphics2D g2d = (Graphics2D) grphcs;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0,
						Color.decode("#ffc0cb"), 0, getHeight(),
						Color.decode("#800080"));
				g2d.setPaint(gp);
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
			}
		};

		menuTrangchu.setBounds(0, 0, 250, 713);
		menuTrangchu.setPreferredSize(new Dimension(250, 600));
		contentPane.add(menuTrangchu);
		int row = 11;
		if (account.getVaiTro().getIdAuthorize() == 2)
			row = 12;
		menuTrangchu.setLayout(new GridLayout(row, 2, 9, 8));
		int menuItemsIndex = 1;
		JPanel home_selected = new JPanel();
		home_selected = new menuItems("src/icon/home.png", "Trang chủ", menuItemsIndex++);
		menuTrangchu.add(home_selected);
		home_selected.addMouseListener(mnuctrl);
		home_selected.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);

			}

		});

		customer_selected = new JPanel();
		customer_selected = new menuItems("src/icon/home.png", "Khách hàng", menuItemsIndex++);
		menuTrangchu.add(customer_selected, "khách hàng");
		customer_selected.addMouseListener(mnuctrl);

		JPanel product_selected = new JPanel();
		product_selected = new menuItems("src/icon/home.png", "Sản phẩm", menuItemsIndex++);
		menuTrangchu.add(product_selected, "sản phẩm");
		product_selected.addMouseListener(mnuctrl);

		menuItems employee_selected = new menuItems("src/icon/home.png", "Nhân viên", menuItemsIndex++);
		employee_selected.setForeground(new Color(255, 255, 255));
		menuTrangchu.add(employee_selected, "nhân viên");
		employee_selected.addMouseListener(mnuctrl);

		menuItems nhaphang_selected = new menuItems("src/icon/home.png", "Nhập hàng", menuItemsIndex++);
		nhaphang_selected.setForeground(new Color(255, 255, 255));
		menuTrangchu.add(nhaphang_selected, "nhập hàng");
		nhaphang_selected.addMouseListener(mnuctrl);

		menuItems bill_selected = new menuItems("src/icon/home.png", "Phiếu xuất", menuItemsIndex++);
		bill_selected.setForeground(new Color(255, 255, 255));
		menuTrangchu.add(bill_selected, "phiếu xuất");
		bill_selected.addMouseListener(mnuctrl);

		menuItems nhaphangBill_selected = new menuItems("src/icon/home.png", "Phiếu nhập", menuItemsIndex++);
		nhaphangBill_selected.setForeground(new Color(255, 255, 255));
		menuTrangchu.add(nhaphangBill_selected, "phiếu nhập");
		nhaphangBill_selected.addMouseListener(mnuctrl);

		menuItems tonkho_selected = new menuItems("src/icon/home.png", "Tồn kho", menuItemsIndex++);
		tonkho_selected.setForeground(new Color(255, 255, 255));
		menuTrangchu.add(tonkho_selected, "tồn kho");
		tonkho_selected.addMouseListener(mnuctrl);

		menuItems thongke_selected = new menuItems("src/icon/home.png", "Thống kê", menuItemsIndex++);
		thongke_selected.setForeground(new Color(255, 255, 255));
		menuTrangchu.add(thongke_selected, "thống kê");
		thongke_selected.addMouseListener(mnuctrl);
		// moi
		if (account.getVaiTro().getIdAuthorize() == 2) {
			menuItems quanlitaikhoan_selected = new menuItems("src/icon/home.png", "Quản lí tài khoản",
					menuItemsIndex++);
			quanlitaikhoan_selected.setForeground(new Color(255, 255, 255));
			menuTrangchu.add(quanlitaikhoan_selected, "quản lí tài khoản");
			quanlitaikhoan_selected.addMouseListener(mnuctrl);
		}

		menuItems taikhoan_selected = new menuItems("src/icon/home.png", "Tài khoản", menuItemsIndex++);
		taikhoan_selected.setForeground(new Color(255, 255, 255));
		menuTrangchu.add(taikhoan_selected, "tài khoản");
		taikhoan_selected.addMouseListener(mnuctrl);

		menuItems exit = new menuItems("src/icon/home.png", "Đăng xuất", 11);
		exit.setForeground(new Color(255, 255, 255));
		menuTrangchu.add(exit);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new formLoginView("", "");
			}
		});
		container = new JPanel();
		container.setBackground(new Color(255, 255, 255));
		container.setBounds(250, 4, 1040, 712);
		contentPane.add(container);
		card = new CardLayout(10, 0);
		card = new CardLayout(10, 0);
		container.setLayout(card);

		JPanel banhang = new JPanel();
		banhang.setBackground(new Color(255, 255, 255));
		container.add(banhang, "trang chủ");
		banhang.setLayout(new BorderLayout(10, 10));

		JPanel search = new JPanel();
		search.setPreferredSize(new Dimension(500, 40));
		banhang.add(search, BorderLayout.NORTH);
		search.setLayout(new GridLayout(0, 1, 0, 0));

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		search.add(textField);
		textField.setColumns(60);

		JPanel showsanpham = new JPanel();
		showsanpham.setBackground(new Color(255, 255, 255));
		banhang.add(showsanpham);
		showsanpham.setLayout(new GridLayout(0, 1, 10, 10));

		JScrollPane showitem_Product = new JScrollPane();
		showitem_Product.getVerticalScrollBar().setUnitIncrement(15);
		showsanpham.add(showitem_Product);

		JPanel item = new JPanel(new GridLayout(0, 3));
		showitem_Product.setViewportView(item);
		listProduct = new productBUS();
		int index = 0;
		while (index < listProduct.getQuantityProducts()) {
			productDTO product = listProduct.getProduct(index);
			String name = product.getName();
			String img = product.getLinkImg();
			String price = listProduct.getDefaultPrice(product);
			String category = listProduct.getCategoryProduct(product.getIdCategory());
			JPanel itempanel = new item().createItem(name, price, category, img);
			int id = index;
			itempanel.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					Font defaultFont = new Font("arial", Font.BOLD, 18);
					JPanel formInfoProductbuy = new JPanel(new BorderLayout(20, 20));
					formInfoProductbuy.setSize(500, 400);
					JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
					header.setBackground(Color.PINK);
					header.setPreferredSize(new Dimension(250, 50));
					formInfoProductbuy.add(header, BorderLayout.NORTH);

					JLabel titleForm = new JLabel("Nhập số lượng");
					titleForm.setForeground(Color.WHITE);
					titleForm.setFont(new Font("Arial", Font.BOLD, 25));
					header.add(titleForm);

					JPanel mainForm = new JPanel(new GridLayout(2, 1, 20, 10));
					formInfoProductbuy.add(mainForm);

					JPanel mainFormTop = new JPanel(new GridLayout(2, 1, 10, 10));
					mainForm.add(mainFormTop);

					JLabel title1 = new JLabel("Nhập số lượng");
					title1.setForeground(Color.lightGray);
					title1.setFont(defaultFont);
					mainFormTop.add(title1);

					JTextField inputQuantityProductBuy = new JTextField(20);
					inputQuantityProductBuy.setFont(defaultFont);
					mainFormTop.add(inputQuantityProductBuy);

					JPanel mainFormBottom = new JPanel(new GridLayout(2, 1));
					mainForm.add(mainFormBottom, BorderLayout.CENTER);

					JLabel title2 = new JLabel("Nhập size");
					title2.setForeground(Color.lightGray);
					title2.setFont(defaultFont);
					mainFormBottom.add(title2);

					DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
					ArrayList<Integer> listsize = new ArrayList<>();
					for (particularProduct pt : product.getParticularProducts()) {
						listsize.add(pt.getSize());

					}
					model.setSelectedItem(listsize.get(0));
					model.addAll(listsize);

					JComboBox<Integer> jboxproduct = new JComboBox<>(model);
					jboxproduct.setSize(new Dimension(200, 30));
					mainFormBottom.add(jboxproduct);

					int result = JOptionPane.showConfirmDialog(null, formInfoProductbuy, "Nhập giá trị",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
					if (result == JOptionPane.OK_OPTION) {
						String num = inputQuantityProductBuy.getText();
						System.out.println(num);
						boolean check = new validateBUS().checkNumber(num);

						if (check) {
							int size = (int) model.getSelectedItem();

							int quantitybuy = Integer.valueOf(inputQuantityProductBuy.getText());
							if (quantitybuy > new productBUS().quantitySize(product, size)) {
								JOptionPane.showMessageDialog(null, this, "Số lượng vượt quá giới hạn cho phép",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							float price = new productBUS().getPrice(product, size);
							sum += price * quantitybuy;
							pr.setText(GUI.item.price(sum));
							int i = modelDataproduct.getRowCount();
							modelDataproduct.insertRow(i, new Object[] {
									++i,
									product.getName(),
									quantitybuy,
									size
							});
							particularBill particularBill = new particularBill(product, size, quantitybuy, price);
							listbuy.add(particularBill);
						} else {
							JOptionPane.showMessageDialog(null, "Lỗi", "error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}

			});
			item.add(itempanel);
			index++;
		}
		JPanel formthongtin = new JPanel();
		formthongtin.setPreferredSize(new Dimension(300, 10));
		banhang.add(formthongtin, BorderLayout.EAST);
		formthongtin.setLayout(new BorderLayout(0, 0));

		JPanel showthongtin = new JPanel();
		formthongtin.add(showthongtin, BorderLayout.CENTER);
		showthongtin.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel thongtinkhachhang = new JPanel();
		showthongtin.add(thongtinkhachhang);
		thongtinkhachhang.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		panel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(20);
		thongtinkhachhang.add(panel);

		JLabel labelForsearchbarkh = new JLabel("Nhập số điện thoại khách hàng");
		labelForsearchbarkh.setHorizontalTextPosition(SwingConstants.CENTER);
		labelForsearchbarkh.setHorizontalAlignment(SwingConstants.CENTER);
		labelForsearchbarkh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel.add(labelForsearchbarkh);

		searchbarKh = new JTextField();
		searchbarKh.setPreferredSize(new Dimension(7, 35));
		searchbarKh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchbarKh.setColumns(20);
		panel.add(searchbarKh);

		searchbarKh.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				conform.setEnabled(true);
				idCurrentShow = -1;
			}

		});

		conform = new JButton("Xác nhận");
		conform.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel.add(conform);

		conform.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eventConform();

			}

		});

		JPanel panel_12 = new JPanel();
		thongtinkhachhang.add(panel_12);
		panel_12.setLayout(new GridLayout(2, 2, 0, 0));

		showcustomer_id = new JLabel();
		showcustomer_id.setHorizontalAlignment(SwingConstants.CENTER);
		showcustomer_id.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_12.add(showcustomer_id);

		showcustomer_name = new JLabel();
		showcustomer_name.setHorizontalAlignment(SwingConstants.CENTER);
		showcustomer_name.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_12.add(showcustomer_name);

		showcustomer_phone = new JLabel();
		showcustomer_phone.setHorizontalAlignment(SwingConstants.CENTER);
		showcustomer_phone.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_12.add(showcustomer_phone);

		showcustomer_bod = new JLabel();
		showcustomer_bod.setHorizontalAlignment(SwingConstants.CENTER);
		showcustomer_bod.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_12.add(showcustomer_bod);

		JPanel thongtinsanpham = new JPanel();
		showthongtin.add(thongtinsanpham);
		thongtinsanpham.setLayout(new BorderLayout());

		JPanel del = new JPanel(new GridLayout());
		thongtinsanpham.add(del, BorderLayout.NORTH);

		JLabel btnDel = new JLabel("Delete");
		btnDel.setFont(new Font("arial", Font.ITALIC, 16));
		btnDel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 20));
		btnDel.setHorizontalAlignment(SwingConstants.RIGHT);
		del.add(btnDel);
		btnDel.addMouseListener(new MouseAdapter() {

			private float pricedelete;
			private int quantity;

			@Override
			public void mousePressed(MouseEvent e) {
				if (listproductsJTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "vui lòng chọn dòng cần xóa");
				} else {
					int index = listproductsJTable.getSelectedRow();
					quantity = (int) modelDataproduct.getValueAt(index, 2);
					int size = (int) modelDataproduct.getValueAt(index, 3);
					productDao pdao = new productDao();
					productDTO product = pdao.selectByName(((String) modelDataproduct.getValueAt(index, 1)));
					modelDataproduct.removeRow(listproductsJTable.getSelectedRow());
					productBUS pubs = new productBUS();
					pricedelete = pubs.getPrice(product, size);

				}

				sum = sum - (pricedelete * quantity);

				pr.setText(GUI.item.price(sum));
			}

		});
		JScrollPane scrollPane = new JScrollPane();
		thongtinsanpham.add(scrollPane, BorderLayout.CENTER);

		modelDataproduct = new DefaultTableModel();

		modelDataproduct.addColumn("STT");
		modelDataproduct.addColumn("Product");
		modelDataproduct.addColumn("Quantity");
		modelDataproduct.addColumn("Size");

		listproductsJTable = new JTable(modelDataproduct);
		// System.out.println(listproductsJTable);
		scrollPane.setViewportView(listproductsJTable);

		TableColumnModel tableColumnModel = listproductsJTable.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(30);
		tableColumnModel.getColumn(1).setPreferredWidth(70);
		tableColumnModel.getColumn(2).setPreferredWidth(50);
		tableColumnModel.getColumn(3).setPreferredWidth(50);

		JPanel totalPrice = new JPanel(new FlowLayout());
		thongtinsanpham.add(totalPrice, BorderLayout.SOUTH);

		pr = new JLabel();
		pr.setForeground(Color.red);
		pr.setFont(new Font("arial", Font.BOLD, 18));
		totalPrice.add(pr);

		JPanel thanhtoan = new JPanel();
		thanhtoan.setPreferredSize(new Dimension(10, 50));
		formthongtin.add(thanhtoan, BorderLayout.SOUTH);
		thanhtoan.setLayout(new GridLayout(0, 1, 0, 0));

		btn_thanhtoan = new JButton("Thanh toán");
		btn_thanhtoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		thanhtoan.add(btn_thanhtoan);
		btn_thanhtoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cus == null) {
					JOptionPane.showMessageDialog(null, "error", "Vui lòng nhập khách hàng", JOptionPane.ERROR_MESSAGE);
					return;
				}
				billdao = new billDao();
				billDTO billdto = new billDTO(0, account.getNhanVien(), cus, sum, null, listbuy.get(0));
				for (int i = 1; i < listbuy.size(); i++) {
					billdto.getDetail().add(listbuy.get(i));
				}
				billdao.insert(billdto);
			}

		});
		thanhtoan.add(btn_thanhtoan);
		kh = new Viewkhachhang();
		khachhangView = new JPanel();
		khachhangView = kh.View();
		container.add(khachhangView, "khách hàng");

		JPanel sanphamView = new JPanel();
		sanphamView = new sanphamGUI().View();
		container.add(sanphamView, "sản phẩm");

		JPanel nhanvienView = new JPanel();
		nhanvienView = new Viewnhanvien().View();
		container.add(nhanvienView, "nhân viên");

		JPanel nhaphangView = new JPanel();
		nhaphangView = new Viewnhaphang().nhaphang(username);
		container.add(nhaphangView, "nhập hàng");

		phieuxuatView = new JPanel();
		phieuxuatView = new Viewphieuxuat().View();
		container.add(phieuxuatView, "phiếu xuất");

		phieunhapView = new JPanel();
		phieunhapView = new Viewphieunhap().View();
		container.add(phieunhapView, "phiếu nhập");

		tonkhoView = new JPanel();
		tonkhoView = new Viewtonkho().View();
		container.add(tonkhoView, "tồn kho");

		thongkeView = new JPanel(new GridLayout());
		thongkeView = new Viewthongke().View();
		container.add(thongkeView, "thống kê");

		JPanel taikhoanView = new JPanel();
		taikhoanView = new Viewtaikhoan().view();
		container.add(taikhoanView, "tài khoản");

		JPanel quanlitaikhoanView = new JPanel();
		quanlitaikhoanView = new Viewtaikhoan().view();
		container.add(quanlitaikhoanView, "quản lí tài khoản");

		setVisible(true);
	}

	public void changePage(String page) {
		container.revalidate();
		container.repaint();
		card.show(container, page.toLowerCase());
	}

	public void eventConform() {
		String phoneNum = searchbarKh.getText();
		valid = new validateBUS();
		if (!valid.checkEmpty(searchbarKh) && valid.checkPhone(phoneNum)) {
			customerDao cusD = new customerDao();
			cus = cusD.getCustomerByPhone(phoneNum);
			if (cus == null) {
				int check = JOptionPane.showConfirmDialog(this, "Khách hàng không tồn tại có muốn tạo mới khách hàng?");
				if (check == JOptionPane.YES_OPTION) {
					addCusGUI gui = new addCusGUI();
					JDialog viewAdd = gui.view(phoneNum);

					viewAdd.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							if (gui.status == true) {
								conform.setEnabled(false);

							}
						}
					});

					cus = cusD.getCustomerByPhone(phoneNum);
					showInfoCus(cus);
					kh.insertCol(cus);

				}
			} else {
				conform.setEnabled(false);
				showInfoCus(cus);

			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void showInfoCus(customerDTO cus) {
		showcustomer_id.setText("Id customer: " + cus.getId());
		showcustomer_name.setText("Name: " + cus.getName());
		showcustomer_phone.setText("Phone: " + cus.getPhoneNumber());
		showcustomer_bod.setText("DOB: " + cus.getBirthday());
		idCurrentShow = cus.getId();

	}

	public void loadViewAll() {
		// container.remove(phieunhapView);
		// container.remove(khachhangView);
		// container.remove(phieuxuatView);
		// phieuxuatView = new Viewphieuxuat().View();
		// khachhangView = new Viewkhachhang().View();
		// phieunhapView = new Viewphieunhap().View();
		// container.add(phieunhapView, "phiếu nhập");
		// container.add(phieuxuatView, "phiếu xuất");
		// container.add(khachhangView, "khách hàng");
	}

	public float unformatPrice(String price) {
		price = price.replace(".", "");
		// System.out.println(price);
		float priceUnformat = Float.parseFloat(price);
		return priceUnformat;
	}

	// public float tongbill() {
	// for (int i = 0; i < modelDataproduct.getRowCount(); i++) {
	// Object[] rowData = new Object[2];
	// sum = (int) modelDataproduct.getValueAt(i,0 ) ;

	// // Bây giờ bạn có thể làm bất kỳ điều gì với dữ liệu trong rowData
	// // Ví dụ: in ra giá trị của từng dòng
	// System.out.println("Row " + i + ": " + Arrays.toString(rowData));
	// }
	// }
}
