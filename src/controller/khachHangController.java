package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import BUS.customerBUS;
import BUS.validateBUS;
import DTO.customerDTO;
import GUI.ViewUpdateCustomer;
import GUI.Viewkhachhang;
import GUI.Viewtrangchu;

public class khachHangController implements ActionListener, MouseListener, KeyListener {

    private Viewkhachhang view1;
    private ViewUpdateCustomer view2;

    public khachHangController(Viewkhachhang view) {
        this.view1 = view;
    }

    public khachHangController(ViewUpdateCustomer view) {
        this.view2 = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String src = view1.chucnang.textField.getText();
        String select = (String) view1.chucnang.comboBox.getSelectedItem();
        if (select.equals("Theo tên")) {
            view1.showInfo(view1.getListCustomer().getInfoByName(src.toLowerCase()));
        } else if (select.equals("Theo id")) {
            if (src.equals("")) {
                view1.showInfo(view1.getListCustomer().getListCustomer());
            } else {
                ArrayList<customerDTO> tmp = new ArrayList<customerDTO>();
                tmp.add(view1.getListCustomer().getInfo(Integer.parseInt(src)));
                view1.showInfo(tmp);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int i = view1.getTableDataKh().getSelectedRow();
        JLabel btn = (JLabel) e.getComponent();
        if (btn.getText().equals("Xóa")) {
            if (i == -1) {
                JOptionPane.showMessageDialog(view1, "Bạn chưa chọn dòng để xóa", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int id = (int) view1.getTableDataKh().getValueAt(i, 1);
                int result = JOptionPane.showConfirmDialog(view1,
                        "Bạn có chắc muốn xóa không?",
                        "Xóa khách hàng", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    boolean check = view1.getListCustomer().deleteCustomer(id);
                    if (check == true) {
                        JOptionPane.showMessageDialog(view1, "Xóa thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                        view1.reloadData();
                        view1.showInfo(view1.getListCustomer().getListCustomer());
                      
                    } else {
                        JOptionPane.showMessageDialog(view1, "Xóa thất bại", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        } else if (btn.getText().equals("Sửa")) {
            System.out.println(i);
            if (i == -1) {
                JOptionPane.showMessageDialog(view1, "Bạn chưa chọn dòng để sửa", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int id = (int) view1.getTableDataKh().getValueAt(i, 1);
                customerDTO a = view1.getListCustomer().getInfo(id);
                JDialog showUpdateDialog = new ViewUpdateCustomer().view(a);
                showUpdateDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        view1.reloadData();
                        view1.showInfo(view1.getListCustomer().getListCustomer());
                    }
                });
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        if (btn.equals("Sửa")) {
            if (new validateBUS().checkEmpty(view2.textFieldFullName) ||
                    new validateBUS().checkEmpty(view2.textFieldPhone) ||
                    new validateBUS().checkEmpty(view2.textFieldBirthday)) {
                JOptionPane.showMessageDialog(view2, "Vui lòng không để trống trường nào!!!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            customerDTO customer;
            String fullname = view2.textFieldFullName.getText();
            String phoneNumber = view2.textFieldPhone.getText();
            String arr[] = view2.textFieldBirthday.getText().split("-");
            if (arr.length == 3) {
                boolean check = Arrays.stream(arr).anyMatch(String::isEmpty);
                if (check == true) {
                    JOptionPane.showMessageDialog(view2, "Vui lòng nhập theo định dạng sau: year-month-day",
                            "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int year = Integer.parseInt(arr[0]);
                int month = Integer.parseInt(arr[1]) - 1;
                int day = Integer.parseInt(arr[2]);
                @SuppressWarnings("deprecation")
                Date dateOfBirth = new Date(year - 1900, month, day);
                customer = new customerDTO(view2.id,
                        fullname,
                        phoneNumber,
                        dateOfBirth);
            } else {
                customer = new customerDTO(view2.id,
                        fullname,
                        phoneNumber,
                        null);
            }

            int result = JOptionPane.showConfirmDialog(view1, "Bạn chắn chắn muốn sửa?", "Sửa thông tin",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                if (customer.getBirthday() == null) {
                    JOptionPane.showMessageDialog(view2, "Vui lòng nhập theo định dạng sau: year-month-day",
                            "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean check = new customerBUS().checkMatchPhone(customer);
                    if (check == true) {
                        JOptionPane.showMessageDialog(view2, "Số điện thoại đã tồn tại", "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        check = new customerBUS().updateCustomer(customer);
                        if (check == false) {
                            JOptionPane.showMessageDialog(view2, "Vui lòng kiểm tra lại thông tin cần sửa!!!",
                                    "Cảnh báo",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(view2, "Sửa thành công", "Thông báo",
                                    JOptionPane.PLAIN_MESSAGE);
                            view2.dispose();
                        }
                    }
                }
            }
        } else {
            String src = (String) view1.chucnang.comboBox.getSelectedItem();
            if (src.equals("Tất cả")) {
                view1.showInfo(view1.getListCustomer().getListCustomer());
            }
        }
    }

}
