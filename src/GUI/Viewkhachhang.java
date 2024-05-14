package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BUS.customerBUS;
import DTO.customerDTO;
import controller.khachHangController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Viewkhachhang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel viewPanel;
	private DefaultTableModel model;
	private JTable tableDataKh;
	private customerBUS listCustomer;
	public menuChucNang chucnang;

	public Viewkhachhang() {
		listCustomer = new customerBUS();
		// change by nam
		model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
	}

	public JPanel View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 750);

		viewPanel = new JPanel(new BorderLayout());

		setContentPane(viewPanel);
		viewPanel.setSize(1100, 750);
		JPanel panel = new JPanel();
		panel.setLocation(0, 130);
		panel.setSize(1020, 583);

		tableDataKh = new JTable();
		tableDataKh.setDefaultRenderer(Object.class, new defaulttablemode());
		tableDataKh.setShowGrid(false);
		tableDataKh.setRowHeight(30);
		tableDataKh.setShowGrid(false);
		tableDataKh.setRowHeight(30);

		tableDataKh.setModel(model);
		model.addColumn("Stt");
		model.addColumn("Id");
		model.addColumn("Tên");
		model.addColumn("Số điện thoại");
		model.addColumn("Ngày sinh");
		panel.setLayout(new GridLayout(1, 1, 20, 20));
		model.setRowCount(25);
		JScrollPane pane = new JScrollPane(tableDataKh);
		pane.setPreferredSize(new Dimension(1020, 583));
		JTableHeader header = tableDataKh.getTableHeader();

		header.setFont(new Font("Arial", Font.ITALIC, 18));
		header.setBackground(Color.pink);
		header.setForeground(new Color(255, 0, 127));

		TableColumnModel tableColumnModel = tableDataKh.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(90);
		tableColumnModel.getColumn(1).setPreferredWidth(90);
		tableColumnModel.getColumn(2).setPreferredWidth(450);
		tableColumnModel.getColumn(3).setPreferredWidth(300);
		tableColumnModel.getColumn(4).setPreferredWidth(200);

		panel.add(pane);

		chucnang = new menuChucNang();
		viewPanel.add(chucnang.createmenuChucNang(), BorderLayout.NORTH);
		chucnang.add_btn.setEnabled(false);
		chucnang.bin_btn.addMouseListener(new khachHangController(this));
		chucnang.search_btn.addMouseListener(new khachHangController(this));
		chucnang.textField.addKeyListener(new khachHangController(this));
		chucnang.comboBox.addActionListener(new khachHangController(this));

		viewPanel.add(panel, BorderLayout.CENTER);

		showInfo(listCustomer.getListCustomer());
		return viewPanel;

	}

	public JTable getTableDataKh() {
		return tableDataKh;
	}

	public void showInfo(ArrayList<customerDTO> list) {
		model.setRowCount(0);
		int i = 0;
		for (customerDTO c : list) {
			model.addRow(new Object[] {
					++i, c.getId(),
					c.getName(),
					c.getPhoneNumber(),
					c.getBirthday()
			});
		}
	}

	public customerBUS getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(customerBUS listCustomer) {
		this.listCustomer = listCustomer;
	}

	public void reloadData() {
		listCustomer = new customerBUS();
	}

	// them moi by nnam
	public void insertCol(customerDTO cus) {
		int i = model.getRowCount();
		System.out.println(cus.getName());
		model.insertRow(i, new Object[] {
				++i,
				cus.getId(),
				cus.getName(),
				cus.getPhoneNumber(),
				cus.getBirthday(),
		});
		listCustomer.getListCustomer().add(cus);
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
}
