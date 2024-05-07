package GUI;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BUS.productBUS;
import DAO.categoryDAO;
import DAO.materialDAO;
import DTO.productDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class Viewtonkho extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel viewPanel;
	@SuppressWarnings("unused")
	private JTextField textField;
	private JTable tbListSp;
	private DefaultTableModel modelListSp;
	private productBUS list;

	public Viewtonkho() {
		list = new productBUS();
	}

	public JPanel View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 750);

		viewPanel = new JPanel();
		viewPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(viewPanel);
		viewPanel.setSize(1100, 750);
		viewPanel.setLayout(new BorderLayout());
		JPanel search = new JPanel();

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(17);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		search.add(comboBox);
		comboBox.addItem("Tất cả");
		comboBox.addItem("Theo tên");
		comboBox.addItem("Theo id");
		comboBox.addItem("Sắp hết hàng");
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) comboBox.getSelectedItem();
				if (selected.equals("Tất cả")) {
					loadData(list.getListProduct());
				} else if (selected.equals("Sắp hết hàng")) {
					loadData(list.selectByHetHang());
				}
			}
		});
		comboBox.setSelectedItem(0);
		comboBox.setPreferredSize(new Dimension(150, 30));
		JTextField tfSearch = new JTextField(25);
		tfSearch.setFont(new Font("search", ABORT, 20));
		tfSearch.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String cbb = comboBox.getSelectedItem() + "";
				if (cbb.equals("Theo tên")) {
					loadData(list.selectByName(tfSearch.getText()));
				} else if (cbb.equals("Theo id")) {
					loadData(list.selectById(Integer.parseInt(tfSearch.getText())));
				}
			}

		});
		search.add(tfSearch);
		JPanel panel = new JPanel();
		panel.setLocation(0, 130);
		panel.setSize(1086, 583);
		viewPanel.add(panel);

		tbListSp = new JTable();

		JScrollPane spListSp = new JScrollPane(tbListSp);

		spListSp.setPreferredSize(new Dimension(1020, 600));
		modelListSp = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		tbListSp.setModel(modelListSp);
		tbListSp.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() == 2) {
					int i = tbListSp.getSelectedRow();
					if (i >= 0) {
						int id = (int) tbListSp.getValueAt(i, 1);
						productDTO pr = list.getProductByID(id);
						JDialog vii = new viewParticularProduct().view(pr);
					}
				}

			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {

			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
			}

		});

		tbListSp.setShowGrid(false);
		tbListSp.setRowHeight(30);
		tbListSp.setDefaultRenderer(Object.class, new defaulttablemode());

		modelListSp.addColumn("Stt");
		modelListSp.addColumn("Mã SP");
		modelListSp.addColumn("Tên sản phẩm");
		modelListSp.addColumn("Loại");
		modelListSp.addColumn("Chất liệu");
		modelListSp.addColumn("Số lượng bán");

		JTableHeader headerThemSp = tbListSp.getTableHeader();
		Font fontHeader = new Font("header", Font.BOLD, 15);
		headerThemSp.setFont(fontHeader);
		headerThemSp.setBackground(Color.pink);
		headerThemSp.setForeground(new Color(255, 0, 127));

		TableColumnModel tableColumnModel1 = tbListSp.getColumnModel();
		tableColumnModel1.getColumn(0).setPreferredWidth(15);
		tableColumnModel1.getColumn(1).setPreferredWidth(20);
		tableColumnModel1.getColumn(2).setPreferredWidth(300);

		panel.add(search, BorderLayout.NORTH);
		panel.add(spListSp, BorderLayout.CENTER);
		loadData(list.getListProduct());
		return viewPanel;

	}

	public void loadData(ArrayList<productDTO> list) {
		modelListSp.setRowCount(0);
		int i = 0;
		for (productDTO productDTO : list) {
			String loai = new categoryDAO().selectByID(productDTO.getIdCategory()).getCategoryName();
			String mar = new materialDAO().selectByID(productDTO.getIdMaterial()).getMaterial();
			modelListSp.addRow(new Object[] {
					++i,
					productDTO.getIdProduct(),
					productDTO.getName(),
					loai,
					mar,
					productDTO.getQuantitySold()
			});
		}
	}
}
