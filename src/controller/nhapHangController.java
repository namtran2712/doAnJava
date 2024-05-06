package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import BUS.validateBUS;
import DAO.categoryDAO;
import DAO.materialDAO;
import DAO.productDao;
import DTO.categoryDTO;
import DTO.materialDTO;
import DTO.particularProduct;
import DTO.particularReceiptDTO;
import DTO.productDTO;
import DTO.receiptDTO;
import DTO.staffDTO;
import GUI.Viewnhaphang;
import GUI.item;

public class nhapHangController implements MouseListener, ActionListener {

    private Viewnhaphang view;

    public nhapHangController(Viewnhaphang view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = view.comboSize.getSelectedItem() + "";
        JTable listProduct = this.view.getTbListProducts();
        int i = this.view.getTbListProducts().getSelectedRow();
        productDTO tmp = new productDao().selectById((int) listProduct.getValueAt(i, 1));
        ArrayList<particularProduct> detail = tmp.getParticularProducts();

        for (particularProduct particularProduct : detail)
            if (src.equals(particularProduct.getSize() + "")) {
                float price = (float) (particularProduct.getPrice() * 0.95);
                this.view.getTfPrice().setText(item.price(price));
            }

        String btn = e.getActionCommand();
        if (btn.equals("Nhập sản phẩm")) {
            if (view.getTfQuantity().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Không được để trống số lượng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean checkNumber = new validateBUS().checkNumber(view.getTfQuantity().getText());
            if (checkNumber) {
                try {
                    int quantity = Integer.parseInt(view.getTfQuantity().getText());
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập số lớn hơn 0", "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        int idMaterial = new materialDAO().selectByName(this.view.getTfMaterial().getText()).getId();
                        int idCategory = new categoryDAO().selectByName(this.view.getTfCateogry().getText())
                                .getIdProduct();

                        if (this.view.getReceipt().getStaff() == null) {
                            staffDTO staff = new staffDTO(this.view.getAcc().getNhanVien().getId(),
                                    this.view.getTfName().getText(),
                                    null,
                                    null,
                                    0,
                                    null);
                            productDTO product = new productDTO(
                                    Integer.parseInt(this.view.getTfId().getText()),
                                    idCategory,
                                    idMaterial,
                                    this.view.getTfName().getText(),
                                    0,
                                    null,
                                    null);
                            particularReceiptDTO particular = new particularReceiptDTO(
                                    0,
                                    product,
                                    Integer.parseInt((String) this.view.comboSize.getSelectedItem()),
                                    Integer.parseInt(this.view.getTfQuantity().getText()),
                                    item.convertPrice(this.view.getTfPrice().getText()));
                            receiptDTO receipt = new receiptDTO(0,
                                    staff,
                                    item.convertPrice(this.view.getLbTotal().getText()),
                                    null,
                                    null);
                            receipt.getParticular().add(particular);
                            this.view.setReceipt(receipt);
                        } else {
                            productDTO product = new productDTO(
                                    Integer.parseInt(this.view.getTfId().getText()),
                                    idCategory,
                                    idMaterial,
                                    this.view.getTfName().getText(),
                                    0,
                                    null,
                                    null);
                            particularReceiptDTO particular = new particularReceiptDTO(
                                    0,
                                    product,
                                    Integer.parseInt((String) this.view.comboSize.getSelectedItem()),
                                    Integer.parseInt(this.view.getTfQuantity().getText()),
                                    item.convertPrice(this.view.getTfPrice().getText()));
                            if (!this.view.getReceipt().add(
                                    Integer.parseInt(this.view.getTfId().getText()),
                                    Integer.parseInt((String) this.view.comboSize.getSelectedItem()),
                                    Integer.parseInt(this.view.getTfQuantity().getText()),
                                    item.convertPrice(this.view.getLbTotal().getText()))) {
                                this.view.getReceipt().getParticular().add(particular);
                            }
                        }

                        this.view.loadDataReceipt();

                        float price = 0;
                        for (int k = 0; k < view.getModelNhapSp().getRowCount(); k++) {
                            String strValue = view.getModelNhapSp().getValueAt(k, 6) + "";
                            strValue = strValue.substring(0, strValue.length() - 1);
                            String[] arr = strValue.split(",");
                            strValue = "";
                            for (String string : arr) {
                                strValue += string;
                            }
                            price += Float.parseFloat(strValue)
                                    * Integer.parseInt(view.getModelNhapSp().getValueAt(k, 7) + "");
                        }
                        this.view.getLbTotal().setText(item.price(price));
                        this.view.getReceipt().setTotalPrice(price);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập nguyên", "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable listProduct = this.view.getTbListProducts();
        int i = this.view.getTbListProducts().getSelectedRow();

        productDTO tmp = new productDao().selectById((int) listProduct.getValueAt(i, 1));

        categoryDTO category = new categoryDAO().selectByID(tmp.getIdCategory());
        materialDTO material = new materialDAO().selectByID(tmp.getIdMaterial());

        String[] sizes = new String[tmp.getParticularProducts().size()];
        for (int j = 0; j < tmp.getParticularProducts().size(); j++) {
            sizes[j] = tmp.getParticularProducts().get(j).getSize() + "";
        }
        this.view.getTfName().setText(tmp.getName() + "");
        this.view.getTfId().setText(tmp.getIdProduct() + "");
        this.view.getTfCateogry().setText(category.getCategoryName());
        this.view.getTfMaterial().setText(material.getMaterial());
        this.view.getTfPrice().setText(item.price(tmp.getParticularProducts().get(0).getPrice()));
        this.view.getComboSize().setModel(new DefaultComboBoxModel<>(sizes));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
