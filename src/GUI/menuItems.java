package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.BorderLayout;

public class menuItems extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int index;
	boolean iselected = false;
	String iconImgae;

	public void setIselected(boolean iselected) {
		this.iselected = iselected;
		repaint();
	}
	String title;
	private JLabel title_1;
	public menuItems(String iconImgae, String title,int index) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.iconImgae = iconImgae;
		this.title = title;
		this.index =index;
		setOpaque(false);
		createPanel();

	}

	public int getIndex() {
		return index;
	}

	public JPanel createPanel() {
		setLayout(new BorderLayout(0, 0));

		title_1 = new JLabel(title);
		title_1.setHorizontalTextPosition(SwingConstants.CENTER);
		title_1.setHorizontalAlignment(SwingConstants.LEFT);
		title_1.setForeground(Color.WHITE);
		title_1.setFont(new Font("Arial", Font.PLAIN, 18));
		add(title_1, BorderLayout.CENTER);

		JPanel iconHolder = new JPanel();
		iconHolder.setOpaque(false);
		add(iconHolder, BorderLayout.WEST);
		iconHolder.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));

		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(iconImgae));
		icon.setHorizontalAlignment(SwingConstants.LEFT);
		iconHolder.add(icon);

		return this;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (iselected) {
			g2d.setColor(new Color(255, 255, 255, 80));
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
			repaint();
			 
		}


	}
	public String getText() {
	
		return title_1.getText();
	}	

}
