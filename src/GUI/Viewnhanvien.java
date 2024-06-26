package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BUS.staffBUS;
import DAO.staffDao;
import DTO.staffDTO;
import controller.nhanVienController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Viewnhanvien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel viewPanel;
	@SuppressWarnings("unused")
	private JTextField textField;
	private DefaultTableModel model;
	private JTable tableDataNV;
	private staffBUS listStaff;
	public menuChucNang chucnang;

	public Viewnhanvien() {
		listStaff = new staffBUS();
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

		tableDataNV = new JTable();
		tableDataNV.setDefaultRenderer(Object.class, new defaulttablemode());
		tableDataNV.setShowGrid(false);
		tableDataNV.setRowHeight(30);
		tableDataNV.setShowGrid(false);
		tableDataNV.setRowHeight(30);
		tableDataNV.addMouseListener(new nhanVienController(this));

		tableDataNV.setDefaultRenderer(Object.class, new defaulttablemode());

		model = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		tableDataNV.setModel(model);
		model.addColumn("Stt");
		model.addColumn("Id");
		model.addColumn("Tên");
		model.addColumn("Ngày sinh");
		model.addColumn("Lương");
		model.addColumn("Số điện thoại");
		panel.setLayout(new GridLayout(1, 1, 20, 20));
		model.setRowCount(25);
		JScrollPane pane = new JScrollPane(tableDataNV);
		pane.setPreferredSize(new Dimension(1020, 583));
		JTableHeader header = tableDataNV.getTableHeader();

		header.setFont(new Font("Arial", Font.ITALIC, 18));
		header.setBackground(Color.pink);
		header.setForeground(new Color(255, 0, 127));

		TableColumnModel tableColumnModel = tableDataNV.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(70);
		tableColumnModel.getColumn(1).setPreferredWidth(70);
		tableColumnModel.getColumn(2).setPreferredWidth(400);
		tableColumnModel.getColumn(3).setPreferredWidth(200);
		tableColumnModel.getColumn(4).setPreferredWidth(200);
		tableColumnModel.getColumn(5).setPreferredWidth(300);

		panel.add(pane);

		chucnang = new menuChucNang();
		viewPanel.add(chucnang.createmenuChucNang(), BorderLayout.NORTH);
		viewPanel.add(panel, BorderLayout.CENTER);

		chucnang.bin_btn.addMouseListener(new nhanVienController(this));
		chucnang.add_btn.setEnabled(false);
		chucnang.search_btn.addMouseListener(new nhanVienController(this));
		chucnang.textField.addKeyListener(new nhanVienController(this));
		chucnang.comboBox.addActionListener(new nhanVienController(this));

		showInfo(listStaff.getListStaff());
		return viewPanel;
	}

	public JTable getTableDataNV() {
		return tableDataNV;
	}

	public void showInfo(ArrayList<staffDTO> list) {
		model.setRowCount(0);
		int i = 0;
		for (staffDTO s : list) {

			String formattedPrice = item.price(s.getSalary());

			model.addRow(new Object[] {
					++i, s.getId(),
					s.getName(),
					s.getBirthday(),
					formattedPrice,
					s.getPhoneNumber()
			});
		}
	}

	public staffBUS getListStaff() {
		return listStaff;
	}

	public void setListStaff(staffBUS listStaff) {
		this.listStaff = listStaff;
	}

	public void reloadData() {
		listStaff.setListStaff(new staffDao().selectAll());
	}
}
