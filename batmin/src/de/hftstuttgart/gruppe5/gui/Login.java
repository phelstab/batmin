package de.hftstuttgart.gruppe5.gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.binary.Hex;

import de.hftstuttgart.gruppe5.data.Dozent;
import de.hftstuttgart.gruppe5.data.Student;
import de.hftstuttgart.gruppe5.dbAccess.DBConnector;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessRead;
import de.hftstuttgart.gruppe5.logic.Hashing;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * @author Lukas Ringle
 * @author Stefan Binninger
 * @author Sven Turowski
 * @author Paul Helstab <
 * @author Lino Schmidt
 * @author Sebastian Mueller
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txfUsername;
	private JPasswordField passwordField;

	// set standard Look And Feel
	public static String lookAndFeel = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
	public static boolean lookAndFeelBool;
	private JLabel lblDarkMode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					DBConnector.init(args[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * @param pString
	 * @return
	 */
	public static String lookAndFeelM(String pString) {

		pString = lookAndFeel;
		return pString;

	}

	/**
	 * 
	 * @param pBool
	 * @return
	 */
	public static boolean lookAndFeelBool(boolean pBool) {

		pBool = lookAndFeelBool;
		return pBool;

	}

	/**
	 * 
	 * @return the look and feel selected in this screen
	 */
	public static boolean getLookandFeelBool() {
		return Login.lookAndFeelBool;
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/jtattoo/demo/images/batmin3d.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txfUsername = new JTextField();
		txfUsername.setBounds(120, 93, 271, 20);
		contentPane.add(txfUsername);
		txfUsername.setColumns(10);

		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(35, 96, 75, 14);
		contentPane.add(lblUsername);

		passwordField = new JPasswordField();
		passwordField.setBounds(120, 136, 271, 20);
		contentPane.add(passwordField);

		JLabel lblPassword = new JLabel("password: ");
		lblPassword.setBounds(35, 139, 75, 14);
		contentPane.add(lblPassword);

		JRadioButton radioLookAndFeel = new JRadioButton("Dark Mode");
		radioLookAndFeel.setBounds(330, 249, 100, 23);
		contentPane.add(radioLookAndFeel);
		lblDarkMode = new JLabel("Dark Mode");
		// set opening look and feel
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			SwingUtilities.updateComponentTreeUI(Login.this);
			lookAndFeelBool = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		GridBagConstraints gbc_lblDarkMode = new GridBagConstraints();
		gbc_lblDarkMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblDarkMode.gridx = 2;
		gbc_lblDarkMode.gridy = 6;
		contentPane.add(lblDarkMode, gbc_lblDarkMode);

		radioLookAndFeel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioLookAndFeel.isSelected()) {
					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
						lookAndFeelM("com.jtattoo.plaf.noire.NoireLookAndFeel");
						lookAndFeelBool = true;
						SwingUtilities.updateComponentTreeUI(Login.this);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {

					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
						lookAndFeelM("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
						lookAndFeelBool = false;
						SwingUtilities.updateComponentTreeUI(Login.this);
					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

			}
		});

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get username from textfield
				int username = Integer.parseInt(txfUsername.getText());
				// store the password as byte array
				byte[] password = passwordField.getText().getBytes();
				// generate a hashcode for the byte array above
				String hash = Hashing.generateHash(password);

				// check if user is a student
				if (DBaccessRead.getStudentbyMNR(username) != null
						&& DBaccessRead.getStudentbyMNR(username).getPassword().equals(hash)) {
					Student student = DBaccessRead.getStudentbyMNR(username);

					// open the GUI for the Student
					// proof if student already got an accepted bachelorthesis topic
					if (DBaccessRead.getBachelorTopic(username) != null) {
						Student_GUI sHome = new Student_GUI(student);
						sHome.setVisible(true);
					}
					// if not open Student_Formular
					else {
						Student_Formular sForm = new Student_Formular(student);
						sForm.setVisible(true);

					}

					dispose();

					// check if user not student than proof if he is a betreuer
				} else if (DBaccessRead.getBetreuerbyPNR(username) != null
						&& DBaccessRead.getBetreuerbyPNR(username).getSdekan() == 0
						&& DBaccessRead.getBetreuerbyPNR(username).getPassword().equals(hash)) {

					Dozent betreuer = DBaccessRead.getBetreuerbyPNR(username);
					System.out.println(betreuer.getVorname());
					// open the GUI for the Betreuer
					SelectStudent ausAnn = new SelectStudent(betreuer);
					ausAnn.setVisible(true);
					dispose();

					// if both checks failed proof if he is a studiendekan
				} else if (DBaccessRead.getSdekanbyPNR(username) != null
						&& DBaccessRead.getSdekanbyPNR(username).getSdekan() == 1
						&& DBaccessRead.getSdekanbyPNR(username).getPassword().equals(hash)) {

					Dozent sdekan = DBaccessRead.getSdekanbyPNR(username);

					// open the GUI for the Studiendekan
					SelectStudent selStud = new SelectStudent(sdekan);
					selStud.setVisible(true);
					dispose();

				} else if (DBaccessRead.getFarchivEmployee(username) != null
						&& DBaccessRead.getFarchivEmployee(username).getPassword().equals(hash)) {

					// open the GUI for the fArchiv employee
					Fak_archiv farchiv = new Fak_archiv();
					farchiv.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Check username and password combination");
				}
			}
		});
		btnLogin.setBounds(274, 203, 117, 23);
		contentPane.add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StudentReg windowReg = new StudentReg();
				windowReg.setVisible(true);
				dispose();

			}
		});
		btnRegister.setBounds(120, 203, 117, 23);
		contentPane.add(btnRegister);
	}
}
