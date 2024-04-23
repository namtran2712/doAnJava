package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import BUS.accountBUS;
import BUS.staffBUS;
import BUS.validateBUS;
import DTO.accountDTO;
import DTO.authorizeDTO;
import DTO.staffDTO;
import GUI.formLoginView;
import GUI.formRegisterView;

public class formButtonRegisterController implements ActionListener, KeyListener {
	private formRegisterView view;

	public formButtonRegisterController(formRegisterView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đăng nhập")) {
			this.view.dispose();
			new formLoginView("", "");
		}
		if (src.equals("Đăng ký")) {
			register();
		}
	}

	public boolean checkEmpty(String fullName, String phoneNumber, String username, String pass) {
		if (fullName.trim().equals("") || phoneNumber.trim().equals("") || username.trim().equals("")
				|| pass.trim().equals("")) {
			return true;
		}
		return false;
	}

	public void register() {
		String fullName = this.view.getFieldFullName().getText();
		String phoneNumber = this.view.getFieldPhoneNumber().getText();
		String username = this.view.getFieldUser().getText();
		String pass = String.valueOf(this.view.getPasswordField().getPassword());
		Calendar calendar = this.view.getBirthdayChooser().getCalendar();
		String birthday = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH);
		if (checkEmpty(fullName, phoneNumber, username, pass)) {
			JOptionPane.showMessageDialog(view, "Vui lòng điền đủ thông tin", "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
		} else {
			authorizeDTO auth = new authorizeDTO(1, "STAFF");
			Date date = Date.valueOf(birthday);
			staffDTO staff = new staffDTO(0, fullName, phoneNumber, date, 0, "");
			accountDTO acc = new accountDTO(0,
					auth, staff, username, pass);
			boolean check = new staffBUS().checkMatchPhone(acc.getNhanVien());
			if (check) {
				JOptionPane.showMessageDialog(view, "Số điện thoại đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				if (new validateBUS().checkName(acc.getNhanVien().getName()) == false ||
						new validateBUS().checkPhone(acc.getNhanVien().getPhoneNumber()) == false ||
						new validateBUS().checkAge(acc.getNhanVien().getBirthday().toString()) == false) {
					JOptionPane.showMessageDialog(view, "Vui lòng kiểm tra lại thông tin đăng kí", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				check = new accountBUS().checkRegister(acc);
				if (check) {
					JOptionPane.showMessageDialog(view, "Đăng kí thành công", "Thông báo",
							JOptionPane.PLAIN_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(view, "Tên đăng kí đã tồn tại hoặc password không hợp lệ!!!",
							"Cảnh báo",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			register();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			register();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
