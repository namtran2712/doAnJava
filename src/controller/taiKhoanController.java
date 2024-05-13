package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BUS.accountBUS;
import DTO.accountDTO;
import GUI.ViewUpdateAccount;
import GUI.Viewtaikhoan;

public class taiKhoanController implements MouseListener, ActionListener, KeyListener {
    private Viewtaikhoan view1;
    private ViewUpdateAccount view2;

    public taiKhoanController(Viewtaikhoan view) {
        this.view1 = view;
    }

    public taiKhoanController(ViewUpdateAccount view) {
        this.view2 = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel btn = (JLabel) e.getComponent();
        int i = view1.getTableAccount().getSelectedRow();
        if (btn.getText().equals("Xóa")) {
            if (i == -1) {
                JOptionPane.showMessageDialog(view1, "Bạn chưa chọn dòng để xóa", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int id = (int) view1.getTableAccount().getValueAt(i, 1);
                int result = JOptionPane.showConfirmDialog(view1,
                        "Bạn có chắc muốn xóa không?",
                        "Xóa nhân viên", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    boolean check = view1.getListAccount().deleteAccount(id);
                    if (check == true) {
                        JOptionPane.showMessageDialog(view1, "Xóa thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                        view1.reloadData();
                        view1.showInfo(view1.getListAccount().getListAccount());
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
                int id = (int) view1.getTableAccount().getValueAt(i, 1);
                accountDTO a = view1.getListAccount().getInfo(id);
                JDialog showUpdateDialog = new ViewUpdateAccount().view(a);
                showUpdateDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        view1.reloadData();
                        view1.showInfo(view1.getListAccount().getListAccount());
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
        String src = e.getActionCommand();
        if (src.equals("Sửa")) {
            if (checkEmpty(view2.textFieldPassword) ||
                    checkEmpty(view2.textFieldUsername)) {
                JOptionPane.showMessageDialog(view2, "Không thể để trống các trường!!!", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                accountDTO acc = new accountBUS().getInfo(view2.id);
                acc.setUsername(view2.textFieldUsername.getText());
                acc.setPassword(view2.textFieldPassword.getText());
                String status = (String) view2.comboBoxStatus.getSelectedItem();
                int stt = status == "Được hoạt động" ? 1 : 0;
                acc.setStatus(stt);

                int result = JOptionPane.showConfirmDialog(view2, "Bạn chắn chắn muốn sửa?", "Sửa thông tin",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    boolean check = new accountBUS().updateAccount(acc);
                    if (check == true) {
                        JOptionPane.showMessageDialog(view2, "Sửa thành công", "Thông báo",
                                JOptionPane.PLAIN_MESSAGE);
                        view2.viewUpdate.dispose();
                    } else {
                        JOptionPane.showMessageDialog(view2, "Vui lòng kiểm tra lại thông tin cần sửa!!!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public boolean checkEmpty(JTextField s) {
        return s.getText().isEmpty();
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
        if (select.equals("Theo username")) {
            view1.showInfo(view1.getListAccount().getInfoByName(src.toLowerCase()));
        } else if (select.equals("Theo id")) {
            if (src.equals("")) {
                // view1.reloadData();
                view1.showInfo(view1.getListAccount().getListAccount());
            } else {
                ArrayList<accountDTO> tmp = new ArrayList<accountDTO>();
                tmp.add(view1.getListAccount().getInfo(Integer.parseInt(src)));
                view1.showInfo(tmp);
            }
        }
    }
}
