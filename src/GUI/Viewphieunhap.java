package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Viewphieunhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel viewPanel;
	private DefaultTableModel modelPhieuNhap;

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

		tbPhieuNhap.setShowGrid(false);
		tbPhieuNhap.setRowHeight(30);
		tbPhieuNhap.setDefaultRenderer(Object.class, new defaulttablemode());

		modelPhieuNhap.addColumn("Stt");
		modelPhieuNhap.addColumn("Mã PX");
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

		panel.add(spPhieuNhap);

		menuChucNang chucnang = new menuChucNang();

		viewPanel.add(chucnang.createmenuChucNang(), BorderLayout.NORTH);
		viewPanel.add(panel, BorderLayout.CENTER);
		return viewPanel;

	}
}
