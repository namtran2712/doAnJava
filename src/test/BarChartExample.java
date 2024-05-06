package test;
import javax.swing.*;
import java.awt.*;

public class BarChartExample extends JPanel {

    private int[] values;
    private String[] labels;
    private String title;

    public BarChartExample(int[] values, String[] labels, String title) {
        this.values = values;
        this.labels = labels;
        this.title = title;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (values == null || values.length == 0)
            return;

        // Tìm giá trị lớn nhất trong dữ liệu
        int max = 0;
        for (int value : values) {
            if (value > max)
                max = value;
        }

        // Kích thước của Panel
        int width = getWidth();
        int height = getHeight();

        // Kích thước của mỗi cột
        int barWidth = width / values.length;

        // Vẽ các cột
        int x = 0;
        for (int i = 0; i < values.length; i++) {
            int barHeight = (int) ((double) values[i] / max * (height - 30));
            g.setColor(Color.blue);
            g.fillRect(x, height - barHeight, barWidth - 5, barHeight);
            g.setColor(Color.black);
            g.drawString(labels[i], x, height - 10);
            x += barWidth;
        }

        // Vẽ tiêu đề
        g.setColor(Color.black);
        g.drawString(title, (width - g.getFontMetrics().stringWidth(title)) / 2, height - 10);
    }

    public static void main(String[] args) {
        int[] values = {1, 4, 3, 5, 5};
        String[] labels = {"Category1", "Category2", "Category3", "Category4", "Category5"};
        String title = "Bar Chart Example";

        JFrame frame = new JFrame();
        frame.getContentPane().add(new BarChartExample(values, labels, title));
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
