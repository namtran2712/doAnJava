package test;

import javax.swing.UIManager;

import GUI.formLoginView;

public class test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			new formLoginView("", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
