package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import DAO.formRegisterValidate;
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
		String regex = "[\\p{L}\\s]+"; // Phù hợp với tên tiếng Việt có dấu và không dấu
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);

		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean checkUsername(String userName) {
		String regex = "^[a-z][a-z0-9]+$";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(userName);

		if (matcher.matches()) {
			return true;
		}

		return false;
	}

	public boolean checkAge(int age) {
		LocalDateTime now = LocalDateTime.now();
		return now.getYear() - age >= 17;
	}

	public void register() {
		String fullName = this.view.getFieldFullName().getText();
		String phoneNumber = this.view.getFieldPhoneNumber().getText();
		String username = this.view.getFieldUser().getText();
		String pass = String.valueOf(this.view.getPasswordField().getPassword());
		if (checkEmpty(fullName, phoneNumber, username, pass)) {
			JOptionPane.showMessageDialog(view, "Vui lòng điền đủ thông tin", "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
		} else {
			if (checkPhone(phoneNumber) && checkName(fullName)) {
				if (checkUsername(username)) {
					Calendar calendar = this.view.getBirthdayChooser().getCalendar();
					int year = calendar.get(Calendar.YEAR);
					if (checkAge(year)) {
						String birthday = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DAY_OF_MONTH);
						if (!formRegisterValidate.checkRegister(fullName, username, phoneNumber, pass, birthday)) {
							JOptionPane.showMessageDialog(view, "Số điện thoại hoặc username đã tồn tại !!!",
									"Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);

						} else {
							formRegisterValidate.checkRegister(fullName, username, phoneNumber, pass, birthday);
							JOptionPane.showMessageDialog(view, "Đăng ký thành công", "Success",
									JOptionPane.PLAIN_MESSAGE);
							view.dispose();
							new formLoginView(username, pass);
						}
					} else {
						JOptionPane.showMessageDialog(view, "Bạn chưa đủ 17 tuổi !!!", "Lỗi đăng ký",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(view, "Username không chứa ký tự đặc biệt hoặc dấu !!!",
							"Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(view, "Kiểm tra lại thông tin đăng ký !!!", "Lỗi đăng ký",
						JOptionPane.ERROR_MESSAGE);
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
