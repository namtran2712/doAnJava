package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import BUS.accountBUS;
import DTO.accountDTO;
import GUI.Viewtrangchu;
import GUI.formLoginView;
import GUI.formRegisterView;

public class formButtonLoginController implements ActionListener, MouseListener, KeyListener {
	private formLoginView view;

	public void login() {
		if (checkEmpty(this.view.getPassword())) {

			JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin", "Lỗi đăng nhập",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String username = this.view.getFieldUsername().getText();
			char pwd[] = this.view.getPassword().getPassword();
			String password = "";
			for (char c : pwd) {
				password += c;
			}
			accountDTO acc = new accountDTO(0, null, null, username, password, 1);
			int check = new accountBUS().checkLogin(acc);
			if (check == 1) {
				view.dispose();
				try {
					new Viewtrangchu(username);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (check == 2) {
				JOptionPane.showMessageDialog(view, "Sai tên đăng nhập hoặc mật khẩu", "Lỗi đăng nhập",
						JOptionPane.ERROR_MESSAGE);
			} else if (check == 0) {
				JOptionPane.showMessageDialog(view, "Tài khoản đã bị khóa", "Lỗi đăng nhập",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public formButtonLoginController(formLoginView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		login();
	}

	public boolean checkEmpty(JPasswordField password) {
		char s2[] = this.view.getPassword().getPassword();
		String s2Tmp = "";
		for (char c : s2) {
			s2Tmp += c;
		}
		if (this.view.getFieldUsername().getText().trim().equals("") || s2Tmp.trim().equals("")) {
			return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		if (label.getText().equals("<html><u>Đăng ký</u></html>")) {
			this.view.dispose();
			new formRegisterView();
		} else if (label.getText().equals("<html><u>Quên mật khẩu?</u></html>")) {

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			login();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
