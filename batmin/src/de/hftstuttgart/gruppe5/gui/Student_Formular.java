package de.hftstuttgart.gruppe5.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.hftstuttgart.gruppe5.data.Dozent;
import de.hftstuttgart.gruppe5.data.Student;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessRead;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessWrite;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
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
 * 
 */
public class Student_Formular extends JFrame {

	private JPanel contentPane;
	private JTextField txfName;
	private JTextField txfMnr;
	private JTextField txfFirma;
	private JTextField txfThema;
	private JTextField txfBetreuer;
	private JTextField txfNDA;
	private JTextField txfVorname;

	Student student;
	private int betreuerIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			Student student;

			public void run() {
				try {
					Student_Formular frame = new Student_Formular(this.student);
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
	public Student_Formular(Student student) {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/jtattoo/demo/images/batmin3d.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(226, 16, 69, 20);
		contentPane.add(lblName);

		txfName = new JTextField(student.getNachname());
		txfName.setEditable(false);
		txfName.setBounds(226, 33, 146, 26);
		contentPane.add(txfName);
		txfName.setColumns(10);

		JLabel lblMatrnr = new JLabel("Matrikel Nr.:");
		lblMatrnr.setBounds(15, 70, 127, 20);
		contentPane.add(lblMatrnr);

		txfMnr = new JTextField(Integer.toString(student.getMatrikelnummer()));
		txfMnr.setEditable(false);
		txfMnr.setBounds(15, 89, 146, 26);
		contentPane.add(txfMnr);
		txfMnr.setColumns(10);

		JLabel lblBearbeitungszeitraum = new JLabel("Bearbeitungszeitraum:");
		lblBearbeitungszeitraum.setBounds(415, 16, 205, 20);
		contentPane.add(lblBearbeitungszeitraum);

		JLabel lblFrima = new JLabel("Frima:");
		lblFrima.setBounds(15, 131, 69, 20);
		contentPane.add(lblFrima);

		txfFirma = new JTextField();
		txfFirma.setBounds(15, 151, 146, 26);
		contentPane.add(txfFirma);
		txfFirma.setColumns(10);

		JLabel lblThema = new JLabel("Thema:");
		lblThema.setBounds(15, 193, 69, 20);
		contentPane.add(lblThema);

		txfThema = new JTextField();
		txfThema.setBounds(15, 214, 146, 26);
		contentPane.add(txfThema);
		txfThema.setColumns(10);

		JLabel lblBetreuer = new JLabel("Betreuer:");
		lblBetreuer.setBounds(15, 252, 69, 20);
		contentPane.add(lblBetreuer);

		txfBetreuer = new JTextField();
		txfBetreuer.setBounds(15, 273, 146, 26);
		contentPane.add(txfBetreuer);
		txfBetreuer.setColumns(10);

		JLabel lblBetreuerWhlen = new JLabel("Betreuer w\u00E4hlen:");
		lblBetreuerWhlen.setBounds(15, 332, 127, 20);
		contentPane.add(lblBetreuerWhlen);

		JLabel lblTextKopieNDA = new JLabel("Kopie von unterschriebener NDA:");
		lblTextKopieNDA.setBounds(283, 353, 283, 20);
		contentPane.add(lblTextKopieNDA);

		txfNDA = new JTextField();
		txfNDA.setBounds(281, 378, 158, 26);
		contentPane.add(txfNDA);
		txfNDA.setColumns(10);

		JLabel lblVon = new JLabel("Von:");
		lblVon.setBounds(303, 70, 69, 20);
		contentPane.add(lblVon);

		JLabel lblBis = new JLabel("Bis:");
		lblBis.setBounds(551, 70, 69, 20);
		contentPane.add(lblBis);

		JDateChooser dCVon = new JDateChooser();
		dCVon.setBounds(303, 89, 136, 20);
		contentPane.add(dCVon);

		JDateChooser dCBis = new JDateChooser();
		dCBis.setBounds(551, 95, 136, 20);
		contentPane.add(dCBis);

		JButton btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateVon = sdf.format(dCVon.getDate());
				String dateBis = sdf.format(dCBis.getDate());

				// Get Selected Betreuer and write him into database
				int bePnr = DBaccessRead.getAllBetreuer().get(betreuerIndex).getPnr();
				String titel = txfThema.getText();
				String firma = txfFirma.getText();
				String firmaBe = txfBetreuer.getText();

				DBaccessWrite.setBA(titel, student.getMatrikelnummer(), firma, dateVon, dateBis, bePnr,
						firmaBe);

				Student_GUI stGui = new Student_GUI(student);
				stGui.setVisible(true);
				dispose();

			}
		});
		btnBesttigen.setBounds(572, 415, 115, 29);
		contentPane.add(btnBesttigen);

		// display all available Betreuer in the comboBox
		JComboBox comboBox = new JComboBox();
		for (int i = 0; i < DBaccessRead.getAllBetreuer().size(); i++) {
			comboBox.addItem(DBaccessRead.getAllBetreuer().get(i).getVorname() + " "
					+ DBaccessRead.getAllBetreuer().get(i).getNachname());
		}
		comboBox.setToolTipText("Men\u00FC");
		comboBox.setBounds(15, 368, 146, 26);
		contentPane.add(comboBox);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				betreuerIndex = comboBox.getSelectedIndex();
			}
		});

		JButton btnHochladen = new JButton("Hochladen");
		btnHochladen.setBounds(303, 415, 115, 29);
		contentPane.add(btnHochladen);

		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(15, 19, 74, 14);
		contentPane.add(lblVorname);

		txfVorname = new JTextField(student.getVorname());
		txfVorname.setEditable(false);
		txfVorname.setBounds(15, 39, 146, 20);
		contentPane.add(txfVorname);
		txfVorname.setColumns(10);

		JRadioButton rbnDarkMode = new JRadioButton("Dark Mode");
		rbnDarkMode.setBounds(572, 459, 109, 23);
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

						SwingUtilities.updateComponentTreeUI(Student_Formular.this);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {

					try {
						Login.lookAndFeel = "com.jtattoo.plaf.aero.AeroLookAndFeel";
						Login.lookAndFeelBool = false;
						rbnDarkMode.setSelected(false);
						UIManager.setLookAndFeel(Login.lookAndFeel);
						SwingUtilities.updateComponentTreeUI(Student_Formular.this);
					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

			}
		});
	}
}
