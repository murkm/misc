package mk.ssanta.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import mk.ssanta.data.SecretSantaDataStore;

public class SecretSantaPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6091997197344064609L;
	private SecretSantaDataStore ssds;
	private JButton addSantaButton;
	private JButton removeSantaButton;
	private JButton shuffleButton;
	private JButton getRecipientButton;
	private JList<String> santas;
	private JLabel statusLabel;
	private JScrollPane scrollPane;

	public SecretSantaPanel(SecretSantaDataStore ssds) {
		this.ssds = ssds;
		
		JTextField textField;
		textField = new JTextField();
		textField.setText("");
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				String text = textField.getText();
				updateAddButtonState(text);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				String text = textField.getText();
				updateAddButtonState(text);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				String text = textField.getText();
				updateAddButtonState(text);
			}
		});
		
		addSantaButton = new JButton("Add Santa");
		addSantaButton.setEnabled(false);
		addSantaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = textField.getText();
				if (ssds.isOpen() && (! ssds.present(name))) {
					ssds.addFamilyMember(name);
					updateSantasList();
					removeSantaButton.setEnabled(true);
				} else {
					String message = ssds.isOpen() ? (name + " already entered.") : "Secret Santa already closed.";
					JOptionPane.showMessageDialog(SecretSantaPanel.this, message, "Add Santa", JOptionPane.OK_OPTION);
				}
				textField.setText("");
			}
		});
		
		JButton saveAndExitButton = new JButton("Save & Exit");
		saveAndExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (! ssds.isOpen()) {
					DateFormat df = new SimpleDateFormat("yyyyMMdd.HHmmss");
					String dumpFileName = "ssdump_" + df.format(new Date()) + ".txt";
					try {
						FileOutputStream fos = new FileOutputStream(dumpFileName);
						ssds.dumpResults(fos);
						fos.flush();
						fos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.exit(0);
			}
		});
		
		shuffleButton = new JButton("Shuffle");
		shuffleButton.setEnabled(false);
		shuffleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setEnabled(false);
				addSantaButton.setEnabled(false);
				removeSantaButton.setEnabled(false);
				shuffleButton.setEnabled(false);
				ssds.shuffle();
				getRecipientButton.setEnabled(true);
			}
		});
		
		getRecipientButton = new JButton("Get Recipient");
		getRecipientButton.setEnabled(false);
		getRecipientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = santas.getSelectedIndex();
				if (index >= 0) {
					String name = santas.getSelectedValue();
					String recipient = ssds.getRecipient(name);
					String message = "Congratulations " + name + "!\nYou are a Secret Santa for:\n" + recipient;
					JOptionPane.showMessageDialog(SecretSantaPanel.this, message, "Your lucky friend", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		statusLabel = new JLabel("");
		
		removeSantaButton = new JButton("Remove Santa");
		removeSantaButton.setEnabled(false);
		removeSantaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ssds.isOpen() && (santas.getSelectedIndex() >= 0)) {
					String name = santas.getSelectedValue();
					ssds.removeFamilyMember(name);
					updateSantasList();
				}
				
				if (ssds.size() == 0) {
					removeSantaButton.setEnabled(false);
				}
			}
		});
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(statusLabel, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(addSantaButton, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(removeSantaButton, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(shuffleButton, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(getRecipientButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(saveAndExitButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(addSantaButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(removeSantaButton)
							.addGap(29)
							.addComponent(shuffleButton)
							.addGap(9)
							.addComponent(getRecipientButton))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(saveAndExitButton)
						.addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(13))
		);
		
		santas = new JList<String>(); //data has type Object[]
		scrollPane.setViewportView(santas);
		santas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		santas.setLayoutOrientation(JList.VERTICAL);
		setLayout(groupLayout);
		setPreferredSize(new Dimension(450, 300));
	}

	protected void updateSantasList() {
		santas.setListData(ssds.getSantaList());
		statusLabel.setText("Number of Participants: " + ssds.size());
		if (ssds.size() > 1) {
			shuffleButton.setEnabled(true);
		} else {
			shuffleButton.setEnabled(false);
		}
	}

	protected void updateAddButtonState(String text) {
		if ((text == null) || text.trim().equals("")) {
			addSantaButton.setEnabled(false);
		} else {
			addSantaButton.setEnabled(true);
		}
	}
}
