package mk.ssanta.ui;

import javax.swing.JFrame;

import mk.ssanta.data.SecretSantaDataStore;

/*
 * Basic UI For the Secret Santa App
 */
public class SecretSantaApp extends JFrame {
	private static final long serialVersionUID = 3657360632748334602L;

	public SecretSantaApp() {
		super("SecretSantaApp");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().add(new SecretSantaPanel(new SecretSantaDataStore()));
		pack();
	}

	public static void main(String[] args) {
		new SecretSantaApp().setVisible(true);
	}

}
