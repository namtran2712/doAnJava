package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import DAO.billDao;
import DTO.bill;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

public class Viewphieuxuat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel viewPanel;
	ArrayList<bill> listBill;
	private DefaultTableModel modelPhieuXuat;
	private JTable tbPhieuXuat;

	public Viewphieuxuat() {
		listBill = new billDao().selectAll();
	}

	public JTable getTbPhieuXuat() {
		return tbPhieuXuat;
	}

	@SuppressWarnings("unused")
	public JPanel View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		viewPanel = new JPanel();
		viewPanel.setSize(1100, 750);
		viewPanel.setLayout(new BorderLayout());
		// viewPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(viewPanel);
		viewPanel.setPreferredSize(new Dimension(1100, 750));
		viewPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setLocation(0, 130);
		panel.setSize(1020, 583);

		tbPhieuXuat = new JTable();

		JScrollPane spPhieuXuat = new JScrollPane(tbPhieuXuat);

		spPhieuXuat.setPreferredSize(new Dimension(1020, 550));
		modelPhieuXuat = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		tbPhieuXuat.setModel(modelPhieuXuat);

		tbPhieuXuat.setShowGrid(false);
		tbPhieuXuat.setRowHeight(30);
		tbPhieuXuat.setDefaultRenderer(Object.class, new defaulttablemode());

		modelPhieuXuat.addColumn("Stt");
		modelPhieuXuat.addColumn("Mã PN");
		modelPhieuXuat.addColumn("Tên Nhân viên");
		modelPhieuXuat.addColumn("Tên khách hàng");
		modelPhieuXuat.addColumn("Thời gian");
		modelPhieuXuat.addColumn("Tổng giá");

		JTableHeader headerThemSp = tbPhieuXuat.getTableHeader();
		Font fontHeader = new Font("header", Font.BOLD, 15);
		Font fontText = new Font("text", ABORT, 23);

		headerThemSp.setFont(fontHeader);
		headerThemSp.setBackground(Color.pink);
		headerThemSp.setForeground(new Color(255, 0, 127));

		TableColumnModel tableColumnModel1 = tbPhieuXuat.getColumnModel();
		tableColumnModel1.getColumn(0).setPreferredWidth(10);
		tableColumnModel1.getColumn(1).setPreferredWidth(10);
		tableColumnModel1.getColumn(2).setPreferredWidth(100);
		tableColumnModel1.getColumn(3).setPreferredWidth(100);
		tableColumnModel1.getColumn(4).setPreferredWidth(50);
		tableColumnModel1.getColumn(5).setPreferredWidth(50);

		loadData();

		panel.add(spPhieuXuat);

		menuChucNang chucnang = new menuChucNang();

		viewPanel.add(chucnang.createmenuChucNang(), BorderLayout.NORTH);
		viewPanel.add(panel, BorderLayout.CENTER);
		return viewPanel;
	}

	public void loadData() {
		for (int i = 0; i < listBill.size(); i++) {
			String price = item.price(listBill.get(i).getTotal());
			modelPhieuXuat.insertRow(i,
					new Object[] { i + 1, listBill.get(i).getIdBill(), listBill.get(i).getStaff().getName(),
							listBill.get(i).getCustomer().getName(), listBill.get(i).getDateBill(), price });
		}
	}

}
