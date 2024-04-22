package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GUI.ViewaddProduct;

public class addProductController implements ActionListener {
    private JDialog view;
    private ViewaddProduct viewAdd;

    public addProductController(JDialog view, ViewaddProduct viewAdd) {
        this.view = view;
        this.viewAdd = viewAdd;
    }

    @SuppressWarnings("static-access")
    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();

        if (src.equals("Hủy bỏ")) {
            view.setVisible(false);
        } else if (src.equals("Thêm sản phẩm")) {
            boolean check = true;

            while (check) {
                if (checkEmpty(this.viewAdd.fieldProductName.getText()) ||
                        checkEmpty(this.viewAdd.fieldQuantitySize.getText())) {
                    check = false;
                }

                for (JTextField s : this.viewAdd.listPrice) {
                    if (checkEmpty(s.getText())) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    for (JTextField s : this.viewAdd.listQuantity) {
                        if (checkEmpty(s.getText())) {
                            check = false;
                            break;
                        }
                    }
                }
                if (check) {
                    for (JTextField s : this.viewAdd.listSize) {
                        if (checkEmpty(s.getText())) {
                            check = false;
                            break;
                        }
                    }
                }
            }

            if (check == false) {
                JOptionPane.showMessageDialog(this.viewAdd, "Không được để trống ô nào", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        } else if (src.equals("Hình ảnh minh họa")) {
            JFileChooser filecChooser = new JFileChooser();
            filecChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = filecChooser.showOpenDialog(filecChooser);

            if (result == filecChooser.APPROVE_OPTION) {
                File selectedFile = filecChooser.getSelectedFile();
                String file;
                try {
                    file = selectedFile.getCanonicalPath();

                    if (file.endsWith(".png") ||
                            file.endsWith(".jpg")) {
                        ImageIcon imageIcon = new ImageIcon(new File(file).toURI().toURL());
                        this.viewAdd.changeImage(imageIcon);
                    } else {
                        JOptionPane.showMessageDialog(viewAdd, "Đường dẫn không hợp lệ", "Lỗi tệp",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public boolean checkEmpty(String s) {
        return s.isEmpty();
    }
}
