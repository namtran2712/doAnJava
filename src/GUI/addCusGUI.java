package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BUS.validateBUS;
import DAO.customerDao;
import DTO.customerDTO;

public class addCusGUI extends JFrame {

    private JDialog showView;
    private JTextField textFieldFullName;
    private JTextField textFieldPhone;
    private JTextField textFieldBirthday;
    public boolean status;

    public addCusGUI() {
        this.status = false;
    }

    public JDialog view(String sdt) {
        showView = new JDialog();
        showView.setSize(500, 400);
        showView.setTitle("Sửa tài khoản");
        showView.setLocationRelativeTo(null);
        showView.setLayout(new BorderLayout(20, 20));
        showView.setResizable(false);
        showView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelTitle = new JPanel(new FlowLayout());
        panelTitle.setBackground(Color.PINK);

        JLabel title = new JLabel("Thông tin cần thêm");
        panelTitle.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        JPanel panelContent = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel labelFullname = new JLabel("Tên đầy đủ:");
        labelFullname.setFont(new Font("Arial", Font.BOLD, 20));
        labelFullname.setPreferredSize(new Dimension(150, 50));
        panelContent.add(labelFullname);

        textFieldFullName = new JTextField(15);
        textFieldFullName.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldFullName.setMargin(new Insets(5, 5, 5, 5));
        panelContent.add(textFieldFullName);

        JLabel labelPhone = new JLabel("Số điện thoại:");
        labelPhone.setFont(new Font("Arial", Font.BOLD, 20));
        labelPhone.setPreferredSize(new Dimension(150, 50));
        panelContent.add(labelPhone);

        textFieldPhone = new JTextField(15);
        textFieldPhone.setText(sdt);
        textFieldPhone.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldPhone.setMargin(new Insets(5, 5, 5, 5));
        panelContent.add(textFieldPhone);
        textFieldPhone.setEditable(false);
        textFieldPhone.setBackground(Color.white);

        JLabel labelBirthday = new JLabel("Ngày sinh:");
        labelBirthday.setFont(new Font("Arial", Font.BOLD, 20));
        labelBirthday.setPreferredSize(new Dimension(150, 50));
        panelContent.add(labelBirthday);

        textFieldBirthday = new JTextField(15);
        textFieldBirthday.setText("yyyy-mm-dd");
        textFieldBirthday.setForeground(Color.LIGHT_GRAY);
        textFieldBirthday.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldBirthday.setMargin(new Insets(5, 5, 5, 5));
        panelContent.add(textFieldBirthday);

        textFieldBirthday.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldBirthday.getText().equals("yyyy-mm-dd")) {
                    textFieldBirthday.setText("");
                    textFieldBirthday.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldBirthday.getText().equals("")) {
                    textFieldBirthday.setText("yyyy-mm-dd");
                    textFieldBirthday.setForeground(Color.LIGHT_GRAY);
                }
            }

        });

        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JButton btnAdd = new JButton("Thêm khách hàng");
        btnAdd.setPreferredSize(new Dimension(150, 40));
        btnAdd.setBackground(new Color(85, 239, 196, 1));
        btnAdd.setOpaque(false);
        btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelFooter.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = new validateBUS().checkEmpty(textFieldFullName) &&
                        new validateBUS().checkEmpty(textFieldBirthday);
                if (check) {
                    JOptionPane.showMessageDialog(null, "Không được bỏ trống trường nào!!!", "Cảnh báo",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String phoneNum = sdt;
                    String nameCus = textFieldFullName.getText();
                    String bod = textFieldBirthday.getText();
                    Date bodCus;
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        java.util.Date dateformat = format.parse(bod);
                        bodCus = new Date(dateformat.getTime());
                        customerDTO customer = new customerDTO(0, nameCus, phoneNum, bodCus);
                        customerDao cusDao = new customerDao();
                        check = cusDao.insert(customer);
                        if (check) {
                            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công", "Notify",
                                    JOptionPane.INFORMATION_MESSAGE);
                            status = true;
                            close();
                        }

                    } catch (ParseException e1) {

                        e1.printStackTrace();
                    }

                }
            }
        });

        showView.add(panelTitle, BorderLayout.NORTH);
        showView.add(panelContent, BorderLayout.CENTER);
        showView.add(panelFooter, BorderLayout.SOUTH);

        showView.setModalityType(ModalityType.MODELESS);
        showView.setModal(true);
        showView.setVisible(true);
        
        return showView;
    }
    public void close() {
        if (status == true)
        {
            showView.dispose();
        }
    }
}
