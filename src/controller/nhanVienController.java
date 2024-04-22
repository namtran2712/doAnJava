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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.staffDao;
import DTO.staff;
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
        int i = view1.getTableDataKh().getSelectedRow();
        JLabel btn = (JLabel) e.getComponent();
        if (btn.getText().equals("Xóa")) {
            if (i >= 0) {
                i += 1;
                int result = JOptionPane.showConfirmDialog(view1,
                        "Bạn có chắc muốn xóa không?",
                        "Xóa nhân viên", JOptionPane.YES_NO_CANCEL_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    int id = (int) view1.getTableDataKh().getValueAt(i - 1, 1);
                    new staffDao().delete(new staff(id, null, null, null, id, null));
                    view1.deleteModel(i - 1);
                }
            }
        } else if (btn.getText().equals("Sửa")) {
            if (i >= 0) {
                int id = (int) view1.getTableDataKh().getValueAt(i, 1);
                staff s = view1.getListStaff().findByID(id);
                JDialog a = new ViewUpdateStaff().view(s);
                a.setVisible(true);
                a.addWindowListener(new WindowAdapter() {
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
            if (checkEmpty(view2.textFieldBirthday) ||
                    checkEmpty(view2.textFieldFullName) ||
                    checkEmpty(view2.textFieldPhone) ||
                    checkEmpty(view2.textFieldSalary)) {
                JOptionPane.showMessageDialog(view2, "Vui lòng không để trống trường nào!!!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (checkName(view2.textFieldFullName.getText()) == false ||
                    checkPhone(view2.textFieldPhone.getText()) == false ||
                    checkAge(view2.textFieldBirthday.getText()) == false) {
                JOptionPane.showMessageDialog(view2, "Vui lòng kiểm tra lại dữ liệu!!!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String arr[] = view2.textFieldBirthday.getText().split("-");
                int year = Integer.parseInt(arr[0]);
                int month = Integer.parseInt(arr[1]) - 1;
                int day = Integer.parseInt(arr[2]);

                @SuppressWarnings("deprecation")
                Date dateOfBirth = new Date(year - 1900, month, day);

                staff s = new staff(view2.id,
                        view2.textFieldFullName.getText(),
                        view2.textFieldPhone.getText(),
                        dateOfBirth,
                        Float.parseFloat(view2.textFieldSalary.getText()), "");

                int result = JOptionPane.showConfirmDialog(view1, "Bạn chắn chắn muốn sửa?", "Sửa thông tin",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    new staffDao().update(s);
                    view2.dispose();
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            String src = (String) view1.chucnang.comboBox.getSelectedItem();
            if (src.equals("Tất cả")) {
                view1.showInfo(view1.getListStaff().getListStaff());
            }
        }
    }

    public boolean checkEmpty(JTextField s) {
        return s.getText().isEmpty();
    }

    public boolean checkPhone(String phoneNumber) {
        if (phoneNumber.length() != 10) {
            return false;
        } else {
            char[] phone = phoneNumber.toCharArray();
            if (phone[0] != '0') {
                return false;
            }
            for (int i = 1; i < phone.length; i++) {
                if (!Character.isDigit(phone[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkName(String name) {
        String regex = "[\\p{L}\\s]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean checkAge(String birthday) {
        String[] arr = birthday.split("-");
        int age = Integer.parseInt(arr[0]);
        LocalDateTime now = LocalDateTime.now();
        return now.getYear() - age >= 17;
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
            view1.showInfo(view1.getListStaff().findByName(src.toLowerCase()));
        } else if (select.equals("Theo id")) {
            if (src.equals("")) {
                // view1.reloadData();
                view1.showInfo(view1.getListStaff().getListStaff());
            } else {
                ArrayList<staff> tmp = new ArrayList<staff>();
                tmp.add(view1.getListStaff().findByID(Integer.parseInt(src)));
                view1.showInfo(tmp);
            }
        }
    }

}
