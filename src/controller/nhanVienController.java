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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BUS.staffBUS;
import BUS.validateBUS;
import DTO.staffDTO;
import GUI.ViewUpdateStaff;
import GUI.Viewnhanvien;

public class nhanVienController implements MouseListener, ActionListener, KeyListener {
    private Viewnhanvien view1;
    private ViewUpdateStaff view2;

    public nhanVienController(Viewnhanvien view) {
        this.view1 = view;
    }

    public nhanVienController(ViewUpdateStaff view) {
        this.view2 = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel btn = (JLabel) e.getComponent();
        int i = view1.getTableDataNV().getSelectedRow();
        if (btn.getText().equals("Xóa")) {
            if (i == -1) {
                JOptionPane.showMessageDialog(view1, "Bạn chưa chọn dòng để xóa", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int id = (int) view1.getTableDataNV().getValueAt(i, 1);
                int result = JOptionPane.showConfirmDialog(view1,
                        "Bạn có chắc muốn xóa không?",
                        "Xóa nhân viên", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    boolean check = view1.getListStaff().deleteStaff(id);
                    if (check == true) {
                        JOptionPane.showMessageDialog(view1, "Xóa thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                        view1.reloadData();
                        view1.showInfo(view1.getListStaff().getListStaff());
                    } else {
                        JOptionPane.showMessageDialog(view1, "Xóa thất bại", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        } else if (btn.getText().equals("Sửa")) {
            if (i == -1) {
                JOptionPane.showMessageDialog(view1, "Bạn chưa chọn dòng để sửa", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int id = (int) view1.getTableDataNV().getValueAt(i, 1);
                staffDTO a = view1.getListStaff().getInfo(id);
                JDialog showUpdateDialog = new ViewUpdateStaff().view(a);
                showUpdateDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        view1.reloadData();
                        view1.showInfo(view1.getListStaff().getListStaff());
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

            if (new validateBUS().checkEmpty(view2.textFieldBirthday) ||
                    new validateBUS().checkEmpty(view2.textFieldFullName) ||
                    new validateBUS().checkEmpty(view2.textFieldPhone) ||
                    new validateBUS().checkEmpty(view2.textFieldSalary)) {
                JOptionPane.showMessageDialog(view2, "Vui lòng không để trống trường nào!!!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            staffDTO staff;
            String fullname = view2.textFieldFullName.getText();
            String phoneNumber = view2.textFieldPhone.getText();
            String salaryString = view2.textFieldSalary.getText();
            try {
                Float salary = Float.parseFloat(salaryString);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(view2, "Vui lòng kiểm tra lại thông tin cần sửa!!!",
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            }
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
                staff = new staffDTO(
                        view2.id,
                        fullname,
                        phoneNumber,
                        dateOfBirth,
                        Float.parseFloat(salaryString),
                        null);
            } else {
                staff = new staffDTO(
                        view2.id,
                        fullname,
                        phoneNumber,
                        null,
                        Float.parseFloat(salaryString),
                        null);
            }

            int result = JOptionPane.showConfirmDialog(view1, "Bạn chắn chắn muốn sửa?", "Sửa thông tin",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                if (staff.getBirthday() == null) {
                    JOptionPane.showMessageDialog(view2, "Vui lòng nhập theo định dạng sau: year-month-day",
                            "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean check = new staffBUS().checkMatchPhone(staff);
                    if (check == true) {
                        JOptionPane.showMessageDialog(view2, "Số điện thoại đã tồn tại", "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        check = new staffBUS().updateStaff(staff);
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
                view1.showInfo(view1.getListStaff().getListStaff());
            }
        }
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
            view1.showInfo(view1.getListStaff().getInfoByName(src.toLowerCase()));
        } else if (select.equals("Theo id")) {
            if (src.equals("")) {
                // view1.reloadData();
                view1.showInfo(view1.getListStaff().getListStaff());
            } else {
                ArrayList<staffDTO> tmp = new ArrayList<staffDTO>();
                tmp.add(view1.getListStaff().getInfo(Integer.parseInt(src)));
                view1.showInfo(tmp);
            }
        }
    }
}
