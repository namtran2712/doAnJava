package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;

import javax.swing.JTextField;

public class Viewtonkho extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel viewPanel;
	@SuppressWarnings("unused")
	private JTextField textField;

	
	public JPanel View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize (1100,750);
		
		viewPanel = new JPanel();
		viewPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(viewPanel);
		viewPanel.setSize(1100,750);
		viewPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setLocation(0, 130);
		panel.setSize(1086,583);
		viewPanel.add(panel);

		JTable tableDataKh =new JTable();
		tableDataKh.setDefaultRenderer(Object.class, new defaulttablemode()); 
		tableDataKh.setShowGrid(false);
		
		tableDataKh.setRowHeight(30);

		DefaultTableModel model =new DefaultTableModel();
		tableDataKh.setModel(model);
		model.addColumn("Stt");
		model.addColumn("Id");
		model.addColumn("Tên");
		model.addColumn("Ngày sinh");
		model.addColumn("Số điện thoại");
		panel.setLayout(new GridLayout(1, 1, 20, 20));
		model.setRowCount(25);
		JScrollPane pane =new JScrollPane(tableDataKh);
		
		panel.add(pane);

			
		menuChucNang chucnang =new menuChucNang();
		viewPanel.add(chucnang.createmenuChucNang());
		return viewPanel;
		 	
		
	}
}
