package de.hftstuttgart.gruppe5.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.hftstuttgart.gruppe5.dbAccess.DBaccessRead;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessWrite;
import de.hftstuttgart.gruppe5.logic.Hashing;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

/**
 *
 * @author Lukas Ringle
 * @author Stefan Binninger
 * @author Sven Turowski
 * @author Paul Helstab
 * @author Lino Schmidt
 * @author Sebastian Mueller
 */
public class StudentReg extends JFrame {

	private JPanel contentPane;
	private JTextField txfVorname;
	private JTextField txfNachname;
	private JTextField txfMatrikelnummer;
	private JPasswordField pfPassword;
	private JPasswordField pfPasswordConf;
	private JTextField txfPlz;
	private JTextField txfStadt;
	private JTextField txfFakultaet;
	private JRadioButton rbnDarkMode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentReg frame = new StudentReg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentReg() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/jtattoo/demo/images/batmin3d.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVorname = new JLabel("Vorname :");
		lblVorname.setBounds(10, 20, 100, 14);
		contentPane.add(lblVorname);

		JLabel lblNachname = new JLabel("Nachname :");
		lblNachname.setBounds(10, 51, 100, 14);
		contentPane.add(lblNachname);

		JLabel lblMatrikelnummer = new JLabel("Matrikelnummer :");
		lblMatrikelnummer.setBounds(10, 82, 125, 14);
		contentPane.add(lblMatrikelnummer);

		JLabel lblPasswort = new JLabel("Passwort :");
		lblPasswort.setBounds(10, 113, 100, 14);
		contentPane.add(lblPasswort);

		JLabel lblPasswortBestaetigen = new JLabel("Passwort bestaetigen :");
		lblPasswortBestaetigen.setBounds(10, 144, 159, 14);
		contentPane.add(lblPasswortBestaetigen);

		JLabel lblPlz = new JLabel("PLZ :");
		lblPlz.setBounds(10, 175, 100, 14);
		contentPane.add(lblPlz);

		JLabel lblStadt = new JLabel("Stadt :");
		lblStadt.setBounds(10, 206, 100, 14);
		contentPane.add(lblStadt);

		JLabel lblFakultaet = new JLabel("Fakultaet :");
		lblFakultaet.setBounds(10, 240, 100, 14);
		contentPane.add(lblFakultaet);

		txfVorname = new JTextField();
		txfVorname.setBounds(206, 17, 250, 20);
		contentPane.add(txfVorname);
		txfVorname.setColumns(10);

		txfNachname = new JTextField();
		txfNachname.setBounds(206, 48, 250, 20);
		contentPane.add(txfNachname);
		txfNachname.setColumns(10);

		txfMatrikelnummer = new JTextField();
		txfMatrikelnummer.setBounds(206, 79, 250, 20);
		contentPane.add(txfMatrikelnummer);
		txfMatrikelnummer.setColumns(10);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(206, 110, 250, 20);
		contentPane.add(pfPassword);

		pfPasswordConf = new JPasswordField();
		pfPasswordConf.setBounds(206, 141, 250, 20);
		contentPane.add(pfPasswordConf);

		txfPlz = new JTextField();
		txfPlz.setBounds(206, 172, 250, 20);
		contentPane.add(txfPlz);
		txfPlz.setColumns(10);

		txfStadt = new JTextField();
		txfStadt.setBounds(206, 203, 250, 20);
		contentPane.add(txfStadt);
		txfStadt.setColumns(10);

		txfFakultaet = new JTextField();
		txfFakultaet.setBounds(206, 234, 250, 20);
		contentPane.add(txfFakultaet);
		txfFakultaet.setColumns(10);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String passwort = Hashing.generateHash(pfPassword.getText().getBytes());
				String passwortConf = Hashing.generateHash(pfPasswordConf.getText().getBytes());

				if (passwort.equals(passwortConf)) {

					// String passwort = Hashing.generateHash(pfPassword.getText().getBytes());

					DBaccessWrite.setStudent(Integer.parseInt(txfMatrikelnummer.getText()), txfVorname.getText(),
							txfNachname.getText(), Integer.parseInt(txfPlz.getText()), txfStadt.getText(),
							txfFakultaet.getText(), passwort);

					Login windowLogin = new Login();
					windowLogin.setVisible(true);
					dispose();
				} else {

					JOptionPane.showMessageDialog(null, "Die eingegebenen Passwörter stimmen nicht überein.");
				}

			}
		});
		btnConfirm.setBounds(331, 276, 125, 23);
		contentPane.add(btnConfirm);

		rbnDarkMode = new JRadioButton("Dark Mode");
		rbnDarkMode.setBounds(1, 276, 109, 23);
		contentPane.add(rbnDarkMode);

		// add a actionlistener to switch between look and feel modes
		rbnDarkMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rbnDarkMode.isSelected()) {

					try {

						Login.lookAndFeel = "com.jtattoo.plaf.noire.NoireLookAndFeel";
						Login.lookAndFeelBool = true;
						rbnDarkMode.setSelected(true);
						UIManager.setLookAndFeel(Login.lookAndFeel);

						SwingUtilities.updateComponentTreeUI(StudentReg.this);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {

					try {
						Login.lookAndFeel = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
						Login.lookAndFeelBool = false;
						rbnDarkMode.setSelected(false);
						UIManager.setLookAndFeel(Login.lookAndFeel);
						SwingUtilities.updateComponentTreeUI(StudentReg.this);
					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

			}
		});
	}
}
