import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Viewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0 ; i < lafInfo.length ; i++ ) {
			System.out.println(lafInfo[i]);
		}

		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame mainFrame = new ViewerFrame("Simple Viewer");
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setSize(800, 600);
				mainFrame.setVisible(true);
			}});
		
		
	}

}
