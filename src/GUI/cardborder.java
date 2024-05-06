package GUI;

import javax.swing.*;
import java.awt.*;

public class cardborder extends JFrame {
    private Color color1;
    private Color color2;
    private String iconimg;
    private String title;
    private String data;
    private String description;
    private JPanel contentPane;

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public String getIconimg() {
        return iconimg;
    }

    public void setIconimg(String iconimg) {
        this.iconimg = iconimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public cardborder(Color color1, Color color2, String iconimg, String title, String data, String description) {
        this.color1 = color1;
        this.color2 = color2;
        this.iconimg = iconimg;
        this.title = title;
        this.data = data;
        this.description = description;
        // init();
    }

    public JPanel View () {
        this.setSize(300,200);
        this.setLayout(new GridLayout());
        Font fn = new Font("Arial", Font.BOLD, 14);

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2d.setColor(new Color(255,255,255,50));
                g2d.fillOval(getWidth()-(getHeight()/2),10, getHeight() , getHeight());
                g2d.fillOval(getWidth()-(getHeight()/2)-20 ,getHeight()/2+20, getHeight() , getHeight());
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4,1));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        JLabel liconImg = new JLabel();
        liconImg.setIcon(new ImageIcon(iconimg));
        contentPane.add(liconImg);

        JLabel ltitle = new JLabel(title);
        ltitle.setFont(fn);
        ltitle.setForeground(Color.white);
        contentPane.add(ltitle);

        JLabel value = new JLabel(data);
        value.setFont(fn);
        value.setForeground(Color.white);
        contentPane.add(value);

        JLabel ldescription = new JLabel(description);
        ldescription.setFont(fn);
        ldescription.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        ldescription.setForeground(Color.white);
        contentPane.add(ldescription);

        return contentPane ;
    }


 
}
