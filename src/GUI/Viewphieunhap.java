package GUI;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BUS.receiptBUS;
import DAO.receiptDao;
import DTO.receiptDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Viewphieunhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel viewPanel;
	private DefaultTableModel modelPhieuNhap;
	private receiptBUS list;

	public Viewphieunhap() {
		list = new receiptBUS();
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

		JTable tbPhieuNhap = new JTable();

		JScrollPane spPhieuNhap = new JScrollPane(tbPhieuNhap);

		spPhieuNhap.setPreferredSize(new Dimension(1020, 550));
		modelPhieuNhap = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		tbPhieuNhap.setModel(modelPhieuNhap);
		tbPhieuNhap.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int i = tbPhieuNhap.getSelectedRow();
					int id = (int) tbPhieuNhap.getValueAt(i, 1);
					receiptDTO receipt = list.findById(id);
					JDialog viewParticular = new viewParticularReceipt().view(receipt);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});

		tbPhieuNhap.setShowGrid(false);
		tbPhieuNhap.setRowHeight(30);
		tbPhieuNhap.setDefaultRenderer(Object.class, new defaulttablemode());

		modelPhieuNhap.addColumn("Stt");
		modelPhieuNhap.addColumn("Mã PN");
		modelPhieuNhap.addColumn("Tên Nhân viên");
		modelPhieuNhap.addColumn("Thời gian");
		modelPhieuNhap.addColumn("Tổng giá");

		JTableHeader headerThemSp = tbPhieuNhap.getTableHeader();
		Font fontHeader = new Font("header", Font.BOLD, 15);
		Font fontText = new Font("text", ABORT, 23);

		headerThemSp.setFont(fontHeader);
		headerThemSp.setBackground(Color.pink);
		headerThemSp.setForeground(new Color(255, 0, 127));

		TableColumnModel tableColumnModel1 = tbPhieuNhap.getColumnModel();
		tableColumnModel1.getColumn(0).setPreferredWidth(10);
		tableColumnModel1.getColumn(1).setPreferredWidth(10);
		tableColumnModel1.getColumn(2).setPreferredWidth(200);
		tableColumnModel1.getColumn(3).setPreferredWidth(50);
		tableColumnModel1.getColumn(4).setPreferredWidth(50);

		showInfo(list.getList());

		panel.add(spPhieuNhap);

		menuChucNang chucnang = new menuChucNang();

		viewPanel.add(chucnang.createmenuChucNang(), BorderLayout.NORTH);
		chucnang.bin_btn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tbPhieuNhap.getSelectedRow();
				System.out.println(i);
				if (i >= 0) {
					int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xóa phiếu nhập",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						int id = (int) tbPhieuNhap.getValueAt(i, 1);

						if (list.deleteById(id)) {
							JOptionPane.showMessageDialog(null, "Bạn đã xóa thành công!!", "Thông báo",
									JOptionPane.PLAIN_MESSAGE);
						}
						showInfo(list.getList());
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});

		chucnang.textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String check = chucnang.comboBox.getSelectedItem() + "";
				System.out.println(check);
				System.out.println(chucnang.textField.getText());
				if (check.equals("Theo tên")) {
					showInfo(list.selectByNameStaff(chucnang.textField.getText().toLowerCase()));
				} else if (check.equals("Theo id")) {
					try {

						showInfo(list.selectById((Integer.parseInt(chucnang.textField.getText()))));
					} catch (Exception eee) {
						eee.printStackTrace();
						// TODO: handle exception
					}
				}
			}

		});

		chucnang.comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) chucnang.comboBox.getSelectedItem();
				if (selected.equals("Tất cả")) {
					showInfo(list.getList());
				}
			}

		});
		viewPanel.add(panel, BorderLayout.CENTER);
		return viewPanel;
	}

	public void showInfo(ArrayList<receiptDTO> list) {
		modelPhieuNhap.setRowCount(0);
		int i = 0;
		for (receiptDTO receipt : list) {
			modelPhieuNhap.addRow(new Object[] {
					++i,
					receipt.getIdReceipt(),
					receipt.getStaff().getName(),
					receipt.getDateReceipt(),
					item.price(receipt.getTotalPrice())
			});
		}
	}

	public void reloadData() {
		list.setList(new receiptDao().selectAll());
	}
}
