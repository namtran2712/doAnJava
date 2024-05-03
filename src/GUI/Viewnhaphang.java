package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import DAO.accountDao;
import DTO.accountDTO;
import DTO.databaseProduct;
import DTO.productDTO;
import controller.nhapHangController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * nhaphang
 */
public class Viewnhaphang extends JFrame {
        private JPanel nhapHang;
        ArrayList<productDTO> listProduct;
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

        public JComboBox<String> getComboSize() {
                return comboSize;
        }

        public Viewnhaphang() {
                listProduct = new databaseProduct().getDao().selectAll();
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

        public JPanel nhaphang(String username) {
                accountDTO acc = new accountDao().selectByUsername(username);

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

                JLabel lbTop = new JLabel("");
                @SuppressWarnings("unused")
                menuChucNang chucnang = new menuChucNang();
                // jpTop.add(chucnang.createmenuChucNang());
                lbTop.setFont(fontText);
                jpTop.add(lbTop);

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

                loadData();

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
                JLabel lbQuantity = new JLabel("Số lượng");
                JLabel lbEmty1 = new JLabel("                              ");
                JLabel lbEmty2 = new JLabel("                              ");
                JLabel lbEmty3 = new JLabel("                              ");
                JLabel lbEmty4 = new JLabel(
                                "                                                                                 ");

                lbName.setPreferredSize(new Dimension(320, 20));
                lbId.setPreferredSize(new Dimension(180, 20));
                lbCategory.setPreferredSize(new Dimension(140, 20));
                lbMaterial.setPreferredSize(new Dimension(220, 20));
                lbSize.setPreferredSize(new Dimension(100, 20));
                lbPrice.setPreferredSize(new Dimension(240, 20));
                lbQuantity.setPreferredSize(new Dimension(80, 20));

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
                tfPrice = new JTextField(10);
                tfQuantity = new JTextField(6);

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

                tfName.setHorizontalAlignment(SwingConstants.CENTER);
                tfId.setHorizontalAlignment(SwingConstants.CENTER);
                tfCateogry.setHorizontalAlignment(SwingConstants.CENTER);
                tfMaterial.setHorizontalAlignment(SwingConstants.CENTER);
                tfPrice.setHorizontalAlignment(SwingConstants.CENTER);
                tfQuantity.setHorizontalAlignment(SwingConstants.CENTER);

                tfName.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfId.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfCateogry.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfMaterial.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfPrice.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));
                tfQuantity.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 2));

                tfName.setFont(fontText);
                tfId.setFont(fontText);
                tfCateogry.setFont(fontText);
                tfMaterial.setFont(fontText);
                comboSize.setFont(fontText);
                tfPrice.setFont(fontText);
                tfQuantity.setFont(fontText);

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

                jp4.add(lbPrice);
                jp4.add(lbQuantity);
                jp4.add(tfPrice);
                jp4.add(lbEmty3);
                jp4.add(tfQuantity);

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
                lbTotal = new JLabel("0 đ", JLabel.CENTER);

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

        public void loadData() {
                for (int i = 0; i < listProduct.size(); i++) {
                        modelListProducts.insertRow(i, new Object[] { i + 1, listProduct.get(i).getIdProduct(),
                                        listProduct.get(i).getName()
                        });
                }
        }

}