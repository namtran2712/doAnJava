package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import BUS.productBUS;
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
        int id = (int) this.view.getTbListProducts().getValueAt(i, 1);
        productBUS tmp1 = new productBUS();
        productDTO tmp = tmp1.getProductByID(id);
        ArrayList<particularProduct> detail = tmp.getParticularProducts();
        for (int j = 0; j < detail.size(); j++)
            if (src.equals(detail.get(j).getSize() + "")) {
                float price = detail.get(j).getPrice();
                int quantityRemain = detail.get(j).getQuantityRemain();
                this.view.getTfPrice().setText(item.price((float) (price * (0.95))));
                this.view.getTfQuantityRemain().setText(quantityRemain + "");
            }

        String btn = e.getActionCommand();
        if (btn.equals("Nhập sản phẩm")) {
            if (view.getTfQuantityReceipt().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Không được để trống số lượng", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean checkNumber = new validateBUS().checkNumber(view.getTfQuantityReceipt().getText());
            if (checkNumber) {
                try {
                    int quantity = Integer.parseInt(view.getTfQuantityReceipt().getText());
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
                                    Integer.parseInt(this.view.getTfQuantityReceipt().getText()),
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
                                    Integer.parseInt(this.view.getTfQuantityReceipt().getText()),
                                    item.convertPrice(this.view.getTfPrice().getText()));
                            if (!this.view.getReceipt().add(
                                    Integer.parseInt(this.view.getTfId().getText()),
                                    Integer.parseInt((String) this.view.comboSize.getSelectedItem()),
                                    Integer.parseInt(this.view.getTfQuantityReceipt().getText()),
                                    item.convertPrice(this.view.getLbTotal().getText()))) {
                                this.view.getReceipt().getParticular().add(particular);
                            }
                        }

                        this.view.loadDataReceipt();

                        float price = 0;
                        for (int k = 0; k < view.getModelNhapSp().getRowCount(); k++) {
                            String strValue = view.getModelNhapSp().getValueAt(k, 6) + "";

                            System.out.println(strValue);
                            price += item.convertPrice(strValue)
                                    * Integer.parseInt(this.view.getTfQuantityReceipt().getText());
                            System.out.println(price);
                        }
                        this.view.getLbTotal().setText(item.price(price));
                        this.view.getReceipt().setTotalPrice(price);
                        this.view.getTfId().setText("");
                        this.view.getTfName().setText("");
                        this.view.getTfCateogry().setText("");
                        this.view.getTfMaterial().setText("");
                        // this.view.comboSize.removeAll();
                        this.view.comboSize.removeAllItems();
                        this.view.getTfPrice().setText("");
                        this.view.getTfQuantityReceipt().setText("");
                        this.view.getTfQuantityRemain().setText("");
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên", "Cảnh báo",
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
        int i = this.view.getTbListProducts().getSelectedRow();
        int id = (int) this.view.getTbListProducts().getValueAt(i, 1);
        productBUS tmp = new productBUS();
        productDTO tmp2 = tmp.getProductByID(id);
        String[] sizes = new String[tmp2.getParticularProducts().size()];
        for (int j = 0; j < tmp2.getParticularProducts().size(); j++) {
            sizes[j] = tmp2.getParticularProducts().get(j).getSize() + "";
        }
        this.view.getTfName().setText(tmp2.getName() + "");
        this.view.getTfId().setText(tmp2.getIdProduct() + "");
        this.view.getTfCateogry().setText(tmp.getCategoryProduct(tmp2.getIdCategory()) + "");
        this.view.getTfMaterial().setText(tmp.getMaterialProduct(tmp2.getIdMaterial()) + "");
        this.view.getTfQuantityRemain().setText(tmp2.getParticularProducts().get(0).getQuantityRemain() + "");
        this.view.getTfPrice().setText(item.price((float) ((tmp2.getParticularProducts().get(0).getPrice()) * (0.95))));
        this.view.getComboSize().setModel(new DefaultComboBoxModel<>(sizes));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
