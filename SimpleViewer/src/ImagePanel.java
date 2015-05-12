import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BufferedImage offscreen = null;

	LookupOp luo = null;

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (offscreen != null) {
			//super.paintComponent(g2);
		    g2.drawImage(offscreen, null, 0, 0);
		}
		else
			super.paintComponents(g2);
	}

	public BufferedImage getOffscreen() {
		return offscreen;
	}

	public void setOffscreen(BufferedImage offscreen) {
		this.offscreen = offscreen;
	}

	public void loadFile(final File file) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					offscreen = ImageIO.read(file);
//					setSize(new Dimension(getWidth(), getHeight()));
					setPreferredSize(new Dimension(offscreen.getWidth(), offscreen.getHeight()));
					revalidate();
					repaint();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	public void closeFile() {
		offscreen = null;
		setSize(new Dimension(1, 1));
		setPreferredSize(new Dimension(1, 1));
		revalidate();
		repaint();
	}

}
