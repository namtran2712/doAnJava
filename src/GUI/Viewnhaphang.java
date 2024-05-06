package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import BUS.productBUS;
import DAO.accountDao;
import DAO.categoryDAO;
import DAO.materialDAO;
import DAO.receiptDao;
import DTO.accountDTO;
import DTO.databaseProduct;
import DTO.particularReceiptDTO;
import DTO.productDTO;
import DTO.receiptDTO;
import controller.nhapHangController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * nhaphang
 */
public class Viewnhaphang extends JFrame {
        private JPanel nhapHang;
        productBUS list;
        private DefaultTableModel modelListProducts;
        private JTable tbListProducts;
        private JTable tbNhapSp;
        private JTextField tfName;
        private JTextField tfId;
        private JTextField tfCateogry;
        private JTextField tfMaterial;
        private JTextField tfPrice;
        private JTextField tfQuantity;
        private JTextField tfNameStaff;
        private JTextField tfIdPn;
        public JComboBox<String> comboSize;
        private DefaultTableModel modelNhapSp;
        private JLabel lbTotal;
        private receiptDTO receipt;
        private accountDTO acc;
        private JTextField tfQuantityRemain;
        private JTextField tfQuantityReceipt;

        public JTextField getTfQuantityRemain() {
                return tfQuantityRemain;
        }

        public void setTfQuantityRemain(JTextField tfQuantityRemain) {
                this.tfQuantityRemain = tfQuantityRemain;
        }

        public JTextField getTfQuantityReceipt() {
                return tfQuantityReceipt;
        }

        public void setTfQuantityReceipt(JTextField tfQuantityReceipt) {
                this.tfQuantityReceipt = tfQuantityReceipt;
        }

        public JComboBox<String> getComboSize() {
                return comboSize;
        }

        public Viewnhaphang() {
                list = new productBUS();
                receipt = new receiptDTO();
        }

        public JTextField getTfName() {
                return tfName;
        }

        public JTextField getTfId() {
                return tfId;
        }

        public JTextField getTfCateogry() {
                return tfCateogry;
        }

        public JTextField getTfMaterial() {
                return tfMaterial;
        }

        public JTextField getTfPrice() {
                return tfPrice;
        }

        public JTextField getTfQuantity() {
                return tfQuantity;
        }

        public DefaultTableModel getModelListProducts() {
                return modelListProducts;
        }

        public JTextField getTfNameStaff() {
                return tfNameStaff;
        }

        public JTextField getTfIdPn() {
                return tfIdPn;
        }

        public DefaultTableModel getModelNhapSp() {
                return modelNhapSp;
        }

        public JLabel getLbTotal() {
                return lbTotal;
        }

        public JTable getTbListProducts() {
                return tbListProducts;
        }

        public receiptDTO getReceipt() {
                return receipt;
        }

        public void setReceipt(receiptDTO receipt) {
                this.receipt = receipt;
        }

        public accountDTO getAcc() {
                return acc;
        }

        public void setAcc(accountDTO acc) {
                this.acc = acc;
        }

        public JPanel nhaphang(String username) {
                acc = new accountDao().selectByUsername(username);

                nhapHang = new JPanel();
                setContentPane(nhapHang);
                nhapHang.setPreferredSize(new Dimension(990, 750));
                nhapHang.setLayout(new BorderLayout(10, 10));
                Font fontHeader = new Font("header", Font.BOLD, 15);
                Font fontText = new Font("text", ABORT, 15);

                ////////////////////////////////////////// TOP
                ////////////////////////////////////////// SEARCH////////////////////////////////////////////////

                JPanel jpTop = new JPanel();
                JPanel jpContainer = new JPanel(new GridLayout(1, 3, 10, 10));
                JPanel jpBottom = new JPanel();

                JComboBox<String> comboBox = new JComboBox<String>();
                comboBox.setMaximumRowCount(17);
                comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 17));
                jpTop.add(comboBox);
                comboBox.addItem("Tất cả");
                comboBox.addItem("Theo tên");
                comboBox.addItem("Theo id");
                comboBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String selected = (String) comboBox.getSelectedItem();
                                if (selected.equals("Tất cả")) {
                                        loadData(list.getListProduct());
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
                jpTop.add(tfSearch);

                ///////////////////////////////////////// CONTAINER//////////////////////////////////////////////////////

                /////////////// TABLE-PRODUCTS //////////////
                tbListProducts = new JTable();
                tbListProducts.setShowGrid(false);
                tbListProducts.setRowHeight(30);

                tbListProducts.setDefaultRenderer(Object.class, new defaulttablemode());

                JScrollPane jspListProducts = new JScrollPane(tbListProducts);
                modelListProducts = new DefaultTableModel() {

                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }

                };

                tbListProducts.setModel(modelListProducts);
                tbListProducts.addMouseListener(new nhapHangController(this));
                modelListProducts.addColumn("STT");
                modelListProducts.addColumn("Mã SP");
                modelListProducts.addColumn("Tên sản phẩm");

                JTableHeader header = tbListProducts.getTableHeader();

                tbListProducts.setBorder(BorderFactory.createEmptyBorder(150, 5, 50, 5));

                header.setFont(fontHeader);
                header.setBackground(Color.pink);
                header.setForeground(new Color(255, 0, 127));

                TableColumnModel tableColumnModel = tbListProducts.getColumnModel();
                tableColumnModel.getColumn(0).setPreferredWidth(70);
                tableColumnModel.getColumn(1).setPreferredWidth(80);
                tableColumnModel.getColumn(2).setPreferredWidth(400);

                loadData(list.getListProduct());

                /////////////////// INFOR-PRODUCT ///////////////////////////

                JPanel jpIn4Product = new JPanel(new GridLayout(5, 1));

                JPanel jp1 = new JPanel(new FlowLayout());
                JPanel jp2 = new JPanel(new FlowLayout());
                JPanel jp3 = new JPanel(new FlowLayout());
                JPanel jp4 = new JPanel(new FlowLayout());
                JPanel jp6 = new JPanel(new FlowLayout());

                JLabel lbName = new JLabel("Tên sản phẩm");
                JLabel lbId = new JLabel("Mã SP");
                JLabel lbCategory = new JLabel("Loại");
                JLabel lbMaterial = new JLabel("Chất liệu");
                JLabel lbSize = new JLabel("Size");
                JLabel lbPrice = new JLabel("Giá");
                JLabel lbQuantityRemain = new JLabel("SL còn");
                JLabel lbQuantityReceipt = new JLabel("SL nhập");
                JLabel lbEmty1 = new JLabel("                              ");
                JLabel lbEmty2 = new JLabel("                              ");
                // JLabel lbEmty3 = new JLabel(" ");
                JLabel lbEmty4 = new JLabel(
                                "                                                                                 ");

                lbName.setPreferredSize(new Dimension(320, 20));
                lbId.setPreferredSize(new Dimension(180, 20));
                lbCategory.setPreferredSize(new Dimension(140, 20));
                lbMaterial.setPreferredSize(new Dimension(220, 20));
                lbSize.setPreferredSize(new Dimension(100, 20));
                lbQuantityRemain.setPreferredSize(new Dimension(140, 20));
                lbPrice.setPreferredSize(new Dimension(100, 20));
                lbQuantityReceipt.setPreferredSize(new Dimension(60, 20));

                comboSize = new JComboBox<>();
                comboSize.setFont(new Font("Times New Roman", Font.BOLD, 20));
                comboSize.setPreferredSize(new Dimension(185, 50));
                comboSize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                comboSize.setBackground(Color.white);

                comboSize.addActionListener(new nhapHangController(this));

                tfName = new JTextField(25);
                tfId = new JTextField(6);
                tfCateogry = new JTextField(10);
                tfMaterial = new JTextField(9);
                comboSize.setPreferredSize(new Dimension(100, 25));
                tfPrice = new JTextField(11);
                tfQuantityRemain = new JTextField(6);
                tfQuantityReceipt = new JTextField(6);

                tfName.setEnabled(false);
                tfName.setDisabledTextColor(Color.BLACK);
                tfId.setEnabled(false);
                tfId.setDisabledTextColor(Color.BLACK);
                tfCateogry.setEnabled(false);
                tfCateogry.setDisabledTextColor(Color.BLACK);
                tfMaterial.setEnabled(false);
                tfMaterial.setDisabledTextColor(Color.BLACK);
                tfPrice.setEnabled(false);
                tfPrice.setDisabledTextColor(Color.BLACK);
                tfQuantityRemain.setEnabled(false);
                tfQuantityRemain.setDisabledTextColor(Color.BLACK);

                tfName.setHorizontalAlignment(SwingConstants.LEFT);
                tfId.setHorizontalAlignment(SwingConstants.CENTER);
                tfCateogry.setHorizontalAlignment(SwingConstants.CENTER);
                tfMaterial.setHorizontalAlignment(SwingConstants.CENTER);
                tfPrice.setHorizontalAlignment(SwingConstants.CENTER);
                tfQuantityRemain.setHorizontalAlignment(SwingConstants.CENTER);
                tfQuantityReceipt.setHorizontalAlignment(SwingConstants.CENTER);

                tfName.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfId.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfCateogry.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfMaterial.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfPrice.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfQuantityRemain.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfQuantityReceipt.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));

                tfName.setFont(fontText);
                tfId.setFont(fontText);
                tfCateogry.setFont(fontText);
                tfMaterial.setFont(fontText);
                comboSize.setFont(fontText);
                tfPrice.setFont(fontText);
                tfQuantityRemain.setFont(fontText);
                tfQuantityReceipt.setFont(fontText);

                jp1.add(lbName);
                jp1.add(tfName);

                jp2.add(lbId);
                jp2.add(lbCategory);
                jp2.add(tfId);
                jp2.add(lbEmty1);
                jp2.add(tfCateogry);

                jp3.add(lbMaterial);
                jp3.add(lbSize);
                jp3.add(tfMaterial);
                jp3.add(lbEmty2);
                jp3.add(comboSize);

                jp4.add(lbQuantityRemain);
                jp4.add(lbPrice);
                jp4.add(lbQuantityReceipt);
                jp4.add(tfQuantityRemain);
                jp4.add(tfPrice);
                jp4.add(tfQuantityReceipt);

                JButton btnAdd = new JButton("Nhập sản phẩm");
                btnAdd.addActionListener(new nhapHangController(this));
                btnAdd.setBackground(new Color(255, 0, 127));
                // btnAdd.setOpaque(true);
                btnAdd.setForeground(Color.white);
                btnAdd.setFont(fontText);
                jp6.add(lbEmty4);
                jp6.add(btnAdd);

                jpIn4Product.add(jp1);
                jpIn4Product.add(jp2);
                jpIn4Product.add(jp3);
                jpIn4Product.add(jp4);
                jpIn4Product.add(jp6);

                /////////////////// IN4-PHIEU-NHAP //////////////////////////////////

                JPanel jpIn4PhieuNhap = new JPanel(new GridLayout(2, 1, 10, 10));
                JPanel jpIdandStaff = new JPanel(new FlowLayout());
                JPanel jpPriceandBtn = new JPanel(new GridLayout(2, 1, 7, 7));
                JPanel jpBtnandPrice = new JPanel(new FlowLayout(FlowLayout.CENTER));

                Font fontIdandStaff = new Font("abcd", ABORT, 20);
                Font fontabdc = new Font("abcdef", ABORT, 18);

                JLabel lbIdPn = new JLabel("ID phiếu nhập:");
                JLabel lbNameStaff = new JLabel("Nhân viên nhập:");

                lbIdPn.setFont(fontabdc);
                lbNameStaff.setFont(fontabdc);

                lbIdPn.setPreferredSize(new Dimension(280, 30));
                lbNameStaff.setPreferredSize(new Dimension(280, 30));

                tfIdPn = new JTextField(15);
                tfNameStaff = new JTextField(15);
                tfNameStaff.setText(acc.getNhanVien().getName());
                tfNameStaff.setEditable(false);

                tfIdPn.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfNameStaff.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));

                tfIdPn.setFont(fontIdandStaff);
                tfNameStaff.setFont(fontIdandStaff);

                Font fontTotal = new Font("hihihehe", Font.BOLD, 20);
                Font fontPrice = new Font("hahahuhu", Font.BOLD, 15);

                JLabel lbTotalPrice = new JLabel("TỔNG TIỀN:", JLabel.CENTER);
                lbTotal = new JLabel("0đ", JLabel.CENTER);

                lbTotalPrice.setFont(fontTotal);
                lbTotalPrice.setForeground(Color.RED);

                lbTotal.setFont(fontPrice);

                lbTotalPrice.setPreferredSize(new Dimension(150, 30));
                lbTotal.setPreferredSize(new Dimension(150, 30));

                JButton btnNhapHang = new JButton("Nhập Hàng");
                btnNhapHang.setPreferredSize(new Dimension(150, 30));
                btnNhapHang.setBackground(new Color(255, 0, 127));
                // btnNhapHang.setOpaque(true);
                btnNhapHang.setForeground(Color.WHITE);
                btnNhapHang.setFont(new Font("hello", ABORT, 20));
                btnNhapHang.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                if (item.convertPrice(lbTotal.getText()) == 0) {
                                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập sản phẩm nào", "Thông báo",
                                                        JOptionPane.PLAIN_MESSAGE);
                                } else {
                                        new receiptDao().insert(receipt);
                                        receipt = new receiptDTO();
                                        lbTotal.setText("0đ");
                                        loadDataReceipt();
                                }
                        }
                });

                jpBtnandPrice.add(lbTotalPrice);
                jpBtnandPrice.add(lbTotal);
                jpBtnandPrice.add(btnNhapHang);

                jpPriceandBtn.add(new JLabel(""));
                jpPriceandBtn.add(jpBtnandPrice);

                // jpIdandStaff.add(lbIdPn);
                // jpIdandStaff.add(tfIdPn);
                jpIdandStaff.add(lbNameStaff);
                jpIdandStaff.add(tfNameStaff);

                jpIn4PhieuNhap.add(jpIdandStaff);
                jpIn4PhieuNhap.add(jpPriceandBtn);

                jpContainer.add(jspListProducts);
                jpContainer.add(jpIn4Product);
                jpContainer.add(jpIn4PhieuNhap);

                //////////////////////////////// TABLE-SAN-PHAM-NHAP
                //////////////////////////////// ///////////////////////////////

                tbNhapSp = new JTable();
                tbNhapSp.addMouseListener(new MouseListener() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                        int i = tbNhapSp.getSelectedRow();
                                        int id = (int) tbNhapSp.getValueAt(i, 1);
                                        System.out.println(id);
                                        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không",
                                                        "Xóa sản phẩm nhập", JOptionPane.YES_NO_CANCEL_OPTION);
                                        if (result == JOptionPane.YES_OPTION) {
                                                receipt.remove(id);
                                                lbTotal.setText(item.price(receipt.getTotalPrice()));
                                                loadDataReceipt();
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

                JScrollPane spNhapSp = new JScrollPane(tbNhapSp);

                spNhapSp.setPreferredSize(new Dimension(1020, 300));
                modelNhapSp = new DefaultTableModel() {

                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }

                };
                tbNhapSp.setModel(modelNhapSp);

                tbNhapSp.setShowGrid(false);
                tbNhapSp.setRowHeight(30);
                tbNhapSp.setDefaultRenderer(Object.class, new defaulttablemode());

                modelNhapSp.addColumn("Stt");
                modelNhapSp.addColumn("Mã SP");
                modelNhapSp.addColumn("Tên sản phẩm");
                modelNhapSp.addColumn("Loại");
                modelNhapSp.addColumn("Chất liệu");
                modelNhapSp.addColumn("Size");
                modelNhapSp.addColumn("Giá");
                modelNhapSp.addColumn("Sl");

                JTableHeader headerThemSp = tbNhapSp.getTableHeader();

                headerThemSp.setFont(fontHeader);
                headerThemSp.setBackground(Color.pink);
                headerThemSp.setForeground(new Color(255, 0, 127));

                TableColumnModel tableColumnModel1 = tbNhapSp.getColumnModel();
                tableColumnModel1.getColumn(0).setPreferredWidth(15);
                tableColumnModel1.getColumn(1).setPreferredWidth(20);
                tableColumnModel1.getColumn(2).setPreferredWidth(300);
                tableColumnModel1.getColumn(7).setPreferredWidth(20);

                jpBottom.add(spNhapSp);

                nhapHang.setBackground(Color.white);

                nhapHang.add(jpTop, BorderLayout.NORTH);
                nhapHang.add(jpContainer, BorderLayout.CENTER);
                nhapHang.add(jpBottom, BorderLayout.SOUTH);

                nhapHang.setVisible(true);

                return nhapHang;
        }

        public void loadData(ArrayList<productDTO> listProduct) {
                modelListProducts.setRowCount(0);
                int i = 0;
                for (productDTO productDTO : listProduct) {
                        modelListProducts.addRow(new Object[] { i + 1, productDTO.getIdProduct(),
                                        productDTO.getName()
                        });
                }
        }

        public void loadDataReceipt() {
                modelNhapSp.setRowCount(0);
                int i = 0;
                for (particularReceiptDTO p : receipt.getParticular()) {
                        String materialName = new materialDAO().selectByID(p.getProduct().getIdMaterial())
                                        .getMaterial();
                        String categoryName = new categoryDAO().selectByID(p.getProduct().getIdCategory())
                                        .getCategoryName();
                        modelNhapSp.addRow(new Object[] {
                                        ++i,
                                        p.getProduct().getIdProduct(),
                                        p.getProduct().getName(),
                                        categoryName,
                                        materialName,
                                        p.getSize(),
                                        item.price(p.getPrice()),
                                        p.getQuantity()
                        });
                }
        }

}