package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.border.MatteBorder;

import controller.mouseController;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

@SuppressWarnings("unused")
public class mainViewChild extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Wrapper;
	mouseController mc = new mouseController(this);
	private JPanel navMenu_container;
	private JButton openIcon;
	private CardLayout card;
	private JPanel mainView;
	private JLabel title_page;

	public mainViewChild() throws Exception {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1100, 750);
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Wrapper = new JPanel();
		Wrapper.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Wrapper);
		Wrapper.setLayout(null);
		Wrapper.setSize(1100, 750);
		navMenu_container = new JPanel();
		navMenu_container.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		navMenu_container.setBackground(new Color(255, 255, 255));
		navMenu_container.setBounds(0, 10, 1, 703);
		Wrapper.add(navMenu_container);
		navMenu_container.setLayout(null);

		JPanel menu_top = new JPanel();
		menu_top.setBorder(new MatteBorder(1, 0, 0, 1, (Color) new Color(0, 0, 0)));
		menu_top.setBounds(0, 0, 210, 190);
		navMenu_container.add(menu_top);
		menu_top.setLayout(null);

		JButton closeButton = new JButton("X");
		closeButton.setOpaque(false);
		closeButton.setBackground(new Color(255, 255, 255));
		closeButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu_top.add(closeButton);
		ActionListener ctrl;

		JPanel menu_center = new JPanel();
		menu_center.setBorder(new MatteBorder(2, 0, 1, 1, (Color) new Color(0, 0, 0)));
		menu_center.setBounds(0, 191, 210, 405);
		navMenu_container.add(menu_center);
		menu_center.setLayout(new GridLayout(0, 1, 20, 20));

		JLabel select_khachHang = new JLabel("Khách hàng");
		select_khachHang.setToolTipText("kh");
		select_khachHang.setHorizontalAlignment(SwingConstants.CENTER);
		select_khachHang.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menu_center.add(select_khachHang, "viewKh");
		select_khachHang.addMouseListener(mc);

		JLabel select_sanpham = new JLabel("Sản phẩm");
		select_sanpham.setHorizontalAlignment(SwingConstants.CENTER);
		select_sanpham.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menu_center.add(select_sanpham);
		select_sanpham.addMouseListener(mc);

		JLabel select_donhang = new JLabel("Đơn hàng");
		select_donhang.setHorizontalAlignment(SwingConstants.CENTER);
		select_donhang.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menu_center.add(select_donhang);
		select_donhang.addMouseListener(mc);

		JLabel select_thongke = new JLabel("Thống kê");
		select_thongke.setHorizontalAlignment(SwingConstants.CENTER);
		select_thongke.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menu_center.add(select_thongke);
		select_thongke.addMouseListener(mc);

		JLabel select_nhanvien = new JLabel("Nhân viên");
		select_nhanvien.setHorizontalAlignment(SwingConstants.CENTER);
		select_nhanvien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menu_center.add(select_nhanvien);
		select_nhanvien.addMouseListener(mc);

		
		JLabel select_tonkho =new JLabel("Tồn kho");
		select_tonkho.setHorizontalAlignment(SwingConstants.CENTER);
		select_tonkho.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menu_center.add(select_tonkho);
		select_tonkho.addMouseListener(mc);

		JPanel menu_bottom = new JPanel();
		menu_bottom.setBorder(new MatteBorder(2, 0, 1, 1, (Color) new Color(0, 0, 0)));
		menu_bottom.setBounds(0, 595, 210, 119);
		navMenu_container.add(menu_bottom);
		menu_bottom.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel select_thongtintaikhoan = new JLabel("Tài khoản");
		select_thongtintaikhoan.setHorizontalAlignment(SwingConstants.CENTER);
		select_thongtintaikhoan.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menu_bottom.add(select_thongtintaikhoan);
		select_thongtintaikhoan.addMouseListener(mc);

		JLabel select_exit = new JLabel("Thoát");
		select_exit.setHorizontalAlignment(SwingConstants.CENTER);
		select_exit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		menu_bottom.add(select_exit);


		JPanel navIcon = new JPanel();
		navIcon.setBounds(10, 10, 49, 45);
		Wrapper.add(navIcon);
		navIcon.setLayout(new GridLayout(1, 0, 0, 0));
		navIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		openIcon = new JButton("");
		navIcon.add(openIcon);
		openIcon.setFont(new Font("Tahoma", Font.PLAIN, 5));
		openIcon.setIcon(new ImageIcon("E:\\Code\\JavaNew\\eclipse\\doan1\\src\\view\\Icon\\navIcon.jpg"));

		card = new CardLayout();
		mainView = new JPanel();
		mainView.setBackground(new Color(255, 255, 255));
		mainView.setBounds(0, 65, 1086, 648);
		Wrapper.add(mainView);
		mainView.setLayout(card);

		JPanel khachhangFrame = new JPanel();
		Viewkhachhang kh = new Viewkhachhang();
		khachhangFrame = kh.View();
		mainView.add(khachhangFrame, "Khách hàng");
		khachhangFrame.setLayout(null) 	;

		JPanel sanphamFrame = new JPanel();
		sanphamGUI spframe =new sanphamGUI();
		sanphamFrame =spframe.View();
		mainView.add(sanphamFrame, "Sản phẩm");
		// sanphamFrame.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel donhangview = new JPanel();
		mainView.add(donhangview, "Đơn hàng");

		JPanel thongkeview = new JPanel();
		mainView.add(thongkeview, "Thống kê");

		JPanel nhanvienview = new JPanel();
		mainView.add(nhanvienview, "Nhân viên");

		JPanel phieunhapview = new JPanel();
		mainView.add(phieunhapview, "Phiếu nhập");

		JPanel tonkhoFrame =new JPanel();
		Viewtonkho sView = new Viewtonkho();
		tonkhoFrame = sView.View();
		mainView.add(tonkhoFrame,"Tồn kho");
	
		title_page = new JLabel("");
		title_page.setHorizontalAlignment(SwingConstants.CENTER);
		title_page.setFont(new Font("Segoe UI", Font.BOLD, 25));
		title_page.setBounds(259, 10, 568, 45);
		Wrapper.add(title_page);
		Wrapper.setComponentZOrder(mainView, 2);
		
	}

	public void openMenuBar() {
		int width = 210, height = 713;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= width; i++) {
					navMenu_container.setSize(i, height);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		Wrapper.setComponentZOrder(navMenu_container, 0);
		openIcon.setVisible(false);
	}

	public void closeMenu(int time) {
		int width = 210, height = 713;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = width; i > 0; i--) {
					navMenu_container.setSize(i, height);
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		openIcon.setVisible(true);

	}
	public void changePage(String page) {
		card.show(mainView, page);
		title_page.setText("Quản lí " + page.toLowerCase());
		Wrapper.setComponentZOrder(navMenu_container, 0);
		closeMenu(0);
	}

}
