package GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BUS.billBUS;
import DTO.billDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Viewphieuxuat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel viewPanel;
	private billBUS listBill;
	private DefaultTableModel modelPhieuXuat;
	private JTable tbPhieuXuat;

	public Viewphieuxuat() {
		listBill = new billBUS();
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
		tbPhieuXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int i = tbPhieuXuat.getSelectedRow();
					int id = (int) tbPhieuXuat.getValueAt(i, 1);
					billDTO bill = listBill.getBill(id);
					JDialog viewParticular = new viewParticularBill().view(bill);
				}
			}
		});

		tbPhieuXuat.setShowGrid(false);
		tbPhieuXuat.setRowHeight(30);
		tbPhieuXuat.setDefaultRenderer(Object.class, new defaulttablemode());

		modelPhieuXuat.addColumn("Stt");
		modelPhieuXuat.addColumn("Mã PX");
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

		loadData(listBill.getListBill());

		panel.add(spPhieuXuat);

		menuChucNang chucnang = new menuChucNang();
		viewPanel.add(chucnang.createmenuChucNang(), BorderLayout.NORTH);

		chucnang.bin_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tbPhieuXuat.getSelectedRow();
				if (i >= 0) {
					int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xóa bill",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						int id = (int) tbPhieuXuat.getValueAt(i, 1);
						listBill.deleteBill(id);
						loadData(listBill.getListBill());
					}
				}
			}
		});

		chucnang.add_btn.setEnabled(false);
		chucnang.search_btn.setEnabled(false);

		chucnang.comboBox.removeAllItems();
		chucnang.comboBox.addItem("Tất cả");
		chucnang.comboBox.addItem("Theo tên nhân viên");
		chucnang.comboBox.addItem("Theo tên khách hàng");
		chucnang.comboBox.addItem("Theo id");

		chucnang.comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = chucnang.comboBox.getSelectedItem() + "";
				if (selected.equals("Tất cả")) {
					loadData(listBill.getListBill());
				}
			}
		});

		chucnang.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String selected = chucnang.comboBox.getSelectedItem() + "";
				if (selected.equals("Theo tên nhân viên")) {
					loadData(listBill.getBillByNameStaff(chucnang.textField.getText()));
				} else if (selected.equals("Theo tên khách hàng")) {
					loadData(listBill.getBillByNameCustomer(chucnang.textField.getText()));
				} else if (selected.equals("Theo id")) {
					loadData(listBill.getBillByID(Integer.parseInt(chucnang.textField.getText())));
				}
				super.keyReleased(e);
			}
		});

		viewPanel.add(panel, BorderLayout.CENTER);
		return viewPanel;
	}

	public void loadData(ArrayList<billDTO> listBill) {
		modelPhieuXuat.setRowCount(0);
		int i = 0;
		for (billDTO billDTO : listBill) {

			String price = item.price(billDTO.getTotal());
			modelPhieuXuat.insertRow(i,
					new Object[] { i + 1,
							billDTO.getIdBill(),
							billDTO.getStaff().getName(),
							billDTO.getCustomer().getName(),
							billDTO.getDateBill(),
							price });
		}
	}

}
