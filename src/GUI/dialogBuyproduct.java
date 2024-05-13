package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.productBUS;
import BUS.validateBUS;
import DAO.productDao;
import DTO.particularBill;
import DTO.particularProduct;
import DTO.productDTO;

public class dialogBuyproduct extends JFrame {
    private JPanel showView;
    private int quantitybuy;
    private int sizeBuy;
    private float sums;

    public JPanel formBuySelection(productDTO product, DefaultTableModel modelTable,
            ArrayList<particularBill> listbuy) {
        showView = new JPanel();
        showView.setSize(400, 500);
        // showView.setTitle("Nhập số lượng");
        showView.setLayout(new BorderLayout());

        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.setBackground(Color.PINK);
        showView.add(header, BorderLayout.NORTH);

        JLabel title = new JLabel("Nhập số lượng");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("arial", Font.BOLD, 20));
        header.add(title);

        JPanel formInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        formInfo.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        showView.add(formInfo, BorderLayout.CENTER);

        JPanel panel1 = new JPanel(new GridLayout(2, 1));
        panel1.setPreferredSize(new Dimension(300, 80));
        formInfo.add(panel1);

        JPanel panel2 = new JPanel(new GridLayout(2, 1, 20, 20));
        panel2.setPreferredSize(new Dimension(300, 100));
        formInfo.add(panel2);

        JLabel lbquantity = new JLabel("Nhập số lượng");
        lbquantity.setFont(new Font("arial", Font.BOLD, 18));
        lbquantity.setForeground(Color.lightGray);
        panel1.add(lbquantity);

        JTextField inputQuantity = new JTextField(15);
        inputQuantity.setFont(new Font("arial", Font.BOLD, 18));
        panel1.add(inputQuantity);

        JLabel lbsize = new JLabel("Chọn size");
        lbsize.setForeground(Color.lightGray);
        lbsize.setFont(new Font("arial", Font.BOLD, 18));
        panel2.add(lbsize);

        ArrayList<Integer> listSize = new ArrayList<>();
        for (particularProduct item : product.getParticularProducts()) {
            listSize.add(item.getSize());
        }

        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        model.addAll(listSize);
        model.setSelectedItem(listSize.get(0));

        JComboBox<Integer> sizeCollection = new JComboBox<Integer>(model);
        sizeCollection.setPreferredSize(new Dimension(100, 20));
        sizeCollection.setFont(new Font("arial", Font.BOLD, 18));
        panel2.add(sizeCollection);

        JPanel btnAccess = new JPanel(new FlowLayout(FlowLayout.CENTER));
        showView.add(btnAccess, BorderLayout.SOUTH);

        JButton btn = new JButton("Xác Nhận");
        btn.setPreferredSize(new Dimension(150, 50));
        btn.setBackground(Color.PINK);
        btnAccess.add(btn);

        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = false;
                check = checkNumber(inputQuantity.getText());
                if (check) {

                    sizeBuy = Integer.valueOf(sizeCollection.getSelectedItem().toString());
                    quantitybuy = Integer.valueOf(inputQuantity.getText());
                    int i = modelTable.getRowCount();
                    float price = new productBUS().getPrice(product, sizeBuy);
                    if (price < 0) {
                        JOptionPane.showMessageDialog(null, "Error", "Lỗi số lượng", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    particularBill tmp = new particularBill(product, sizeBuy, quantitybuy, price);
                    sums += (price * quantitybuy);
                    listbuy.add(tmp);
                    
                    modelTable.insertRow(i, new Object[] {
                        ++i, product.getName(), quantitybuy
                    });
                    
                    // showView.dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ!!!", "Cảnh báo",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }
       

        });
        showView.setVisible(true);
        return showView;
    }

    public boolean checkNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public int getQuantitybuy() {
        return quantitybuy;
    }

    public int getSizeBuy() {
        return sizeBuy;
    }

    public float getSums() {
        return sums;
    }

    public void setSums(float sums) {
        this.sums = sums;
    } 

    
}
