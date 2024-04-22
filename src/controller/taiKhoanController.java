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

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.accountDao;
import DAO.authorizeDao;
import DTO.accountDTO;
import DTO.authorize;
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
        int i = view1.getTableAccount().getSelectedRow();
        JLabel btn = (JLabel) e.getComponent();
        if (btn.getText().equals("Xóa")) {
            if (i >= 0) {
                i += 1;
                int result = JOptionPane.showConfirmDialog(view1,
                        "Bạn có chắc muốn xóa không?",
                        "Xóa nhân viên", JOptionPane.YES_NO_CANCEL_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    int id = (int) view1.getTableAccount().getValueAt(i - 1, 1);
                    new accountDao().delete(new accountDTO(id, null, null, null, null));
                    view1.deleteModel(i - 1);
                }
            }
        } else if (btn.getText().equals("Sửa")) {
            if (i >= 0) {
                int id = (int) view1.getTableAccount().getValueAt(i, 1);
                accountDTO acc = view1.getListAccount().findByID(id);
                JDialog a = new ViewUpdateAccount().view(acc);
                a.setVisible(true);
                a.addWindowListener(new WindowAdapter() {
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
                accountDTO acc = new accountDao().selectByID(view2.id);
                acc.setUsername(view2.textFieldUsername.getText());
                acc.setPassword(view2.textFieldPassword.getText());
                String vaiTro = (String) view2.comboBoxAuthorize.getSelectedItem();

                authorize auth = new authorizeDao().selectByName(vaiTro);

                acc.setVaiTro(auth);

                int result = JOptionPane.showConfirmDialog(view2, "Bạn chắn chắn muốn sửa?", "Sửa thông tin",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    new accountDao().update(acc);
                    view2.viewUpdate.dispose();
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
        if (select.equals("Theo tên")) {
            view1.showInfo(view1.getListAccount().findByName(src.toLowerCase()));
        } else if (select.equals("Theo id")) {
            if (src.equals("")) {
                // view1.reloadData();
                view1.showInfo(view1.getListAccount().getListAccount());
            } else {
                ArrayList<accountDTO> tmp = new ArrayList<accountDTO>();
                tmp.add(view1.getListAccount().findByID(Integer.parseInt(src)));
                view1.showInfo(tmp);
            }
        }
    }
}
