package BUS;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class validateBUS {

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

    public boolean checkSalary(Float salary) {
        try {
            String salaryString = String.valueOf(salary);
            char[] s = salaryString.toCharArray();
            for (int i = 1; i < s.length; i++) {
                if (!Character.isDigit(s[i])) {
                    return false;
                }
            }
            return true;

        } catch (Exception e) {
            return false;
        }
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

    public boolean checkNumber(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }

        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
