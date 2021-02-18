package de.hftstuttgart.gruppe5.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.hftstuttgart.gruppe5.data.Student;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessRead;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessWrite;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
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
public class Betreuer_ShowStudent extends JFrame {

	private JPanel contentPane;
	private JTextField txfNoteVor;
	private JTextField txfNoteAr;

	Student student;
	private JTextField txfNoteGes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			Student student;

			public void run() {
				try {
					Betreuer_ShowStudent frame = new Betreuer_ShowStudent(this.student);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param student
	 */
	public Betreuer_ShowStudent(Student student) {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/jtattoo/demo/images/batmin3d.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 0, 69, 20);
		contentPane.add(lblName);

		JLabel lblThema = new JLabel("Thema:");
		lblThema.setBounds(135, 0, 69, 20);
		contentPane.add(lblThema);

		JLabel lblNewLabel = new JLabel("Firma:");
		lblNewLabel.setBounds(265, 0, 69, 20);
		contentPane.add(lblNewLabel);

		JLabel lblFirmenbetreuer = new JLabel("Firmenbetreuer:");
		lblFirmenbetreuer.setBounds(390, 0, 160, 20);
		contentPane.add(lblFirmenbetreuer);

		JLabel lblStudent = new JLabel(student.getVorname() + " " + student.getNachname());
		lblStudent.setBounds(10, 16, 115, 20);
		contentPane.add(lblStudent);

		JLabel lblThema_1 = new JLabel(DBaccessRead.getBachelorTopic(student.getMatrikelnummer()));
		lblThema_1.setBounds(135, 16, 120, 20);
		contentPane.add(lblThema_1);

		JLabel lblFirma = new JLabel(DBaccessRead.getFirma(student.getMatrikelnummer()));
		lblFirma.setBounds(265, 16, 115, 20);
		contentPane.add(lblFirma);

		JLabel lblBetreuer = new JLabel(DBaccessRead.getFirmenBetreuer(student.getMatrikelnummer()));
		lblBetreuer.setBounds(390, 16, 140, 20);
		contentPane.add(lblBetreuer);

		JLabel lblVortragsnote = new JLabel("Vortragsnote:");
		lblVortragsnote.setBounds(10, 58, 120, 20);
		contentPane.add(lblVortragsnote);

		txfNoteVor = new JTextField();
		txfNoteVor.setBounds(10, 80, 146, 26);
		contentPane.add(txfNoteVor);
		txfNoteVor.setColumns(10);
		if (DBaccessRead.getNoteVortrag(student.getMatrikelnummer()) > 0) {
			txfNoteVor.setText(Double.toString(DBaccessRead.getNoteVortrag(student.getMatrikelnummer())));
			txfNoteVor.setEditable(false);
		}

		JLabel lblArbeitsnote = new JLabel("Arbeitsnote:");
		lblArbeitsnote.setBounds(10, 115, 94, 20);
		contentPane.add(lblArbeitsnote);

		txfNoteAr = new JTextField();
		txfNoteAr.setBounds(10, 135, 146, 26);
		contentPane.add(txfNoteAr);
		txfNoteAr.setColumns(10);
		if (DBaccessRead.getNoteArbeit(student.getMatrikelnummer()) > 0) {
			txfNoteAr.setText(Double.toString(DBaccessRead.getNoteArbeit(student.getMatrikelnummer())));
			txfNoteAr.setEditable(false);
		}
		System.out.println(DBaccessRead.getNoteArbeit(student.getMatrikelnummer()));

		JLabel lblGesamtnote = new JLabel("Gesamtnote (12:3):");
		lblGesamtnote.setBounds(10, 160, 160, 20);
		contentPane.add(lblGesamtnote);

		JLabel lblZwischenversion = new JLabel("Zwischenversion_1:");
		lblZwischenversion.setBounds(10, 217, 146, 20);
		contentPane.add(lblZwischenversion);

		JLabel lblKommentar = new JLabel("Kommentar:");
		lblKommentar.setBounds(281, 217, 111, 20);
		contentPane.add(lblKommentar);

		JLabel lblZwischenversion_1 = new JLabel("Zwischenversion_2:");
		lblZwischenversion_1.setBounds(10, 288, 160, 20);
		contentPane.add(lblZwischenversion_1);

		JLabel lblKommentar_1 = new JLabel("Kommentar:");
		lblKommentar_1.setBounds(281, 288, 115, 20);
		contentPane.add(lblKommentar_1);

		JLabel lblZwischenversion_2 = new JLabel("Zwischenversion_3:");
		lblZwischenversion_2.setBounds(10, 359, 140, 20);
		contentPane.add(lblZwischenversion_2);

		JLabel lblKommentar_2 = new JLabel("Kommentar:");
		lblKommentar_2.setBounds(281, 359, 111, 20);
		contentPane.add(lblKommentar_2);

		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(351, 397, 115, 29);
		contentPane.add(btnAbbrechen);

		JButton btnEingabe = new JButton("Eingabe");
		btnEingabe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					if (txfNoteAr.getText() != null) {
						Double noteAr = Double.parseDouble(txfNoteAr.getText());
						DBaccessWrite.setNoteArbeit(student.getMatrikelnummer(), noteAr);

					}
					if (txfNoteVor.getText() != null) {
						Double noteVor = Double.parseDouble(txfNoteVor.getText());
						DBaccessWrite.setNoteVortrag(student.getMatrikelnummer(), noteVor);

					}
					if (Double.parseDouble(txfNoteAr.getText()) > 0 && Double.parseDouble(txfNoteVor.getText()) > 0) {
						Double value = (Double.parseDouble(txfNoteAr.getText()) * 3
								+ Double.parseDouble(txfNoteVor.getText())) / 4;
						Double noteGes = Math.round(100.0 * value) / 100.0;
						DBaccessWrite.setNoteGesamt(student.getMatrikelnummer(), noteGes);

					}

				} catch (NumberFormatException ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Note nicht korrekt eingegeben!");
				}
			}
		});
		btnEingabe.setBounds(496, 397, 115, 29);
		contentPane.add(btnEingabe);

		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(565, 0, 69, 20);
		contentPane.add(lblDatum);

		JLabel lblVon = new JLabel(DBaccessRead.getDateFrom(student.getMatrikelnummer()));
		lblVon.setBounds(565, 16, 69, 20);
		contentPane.add(lblVon);

		JLabel lblBis = new JLabel(DBaccessRead.getDateUntil(student.getMatrikelnummer()));
		lblBis.setBounds(566, 33, 69, 20);
		contentPane.add(lblBis);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(384, 178, 227, 59);
		contentPane.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(384, 249, 227, 59);
		contentPane.add(textArea_1);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(384, 320, 227, 59);
		contentPane.add(textArea_2);

		txfNoteGes = new JTextField();
		txfNoteGes.setHorizontalAlignment(SwingConstants.CENTER);
		txfNoteGes.setEditable(false);
		txfNoteGes.setBounds(10, 186, 146, 20);
		contentPane.add(txfNoteGes);
		txfNoteGes.setColumns(10);
		txfNoteGes.setText(Double.toString(DBaccessRead.getNoteGesamt(student.getMatrikelnummer())));

		JRadioButton rbnDarkMode = new JRadioButton("Dark Mode");
		rbnDarkMode.setBounds(496, 433, 109, 23);
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

						SwingUtilities.updateComponentTreeUI(Betreuer_ShowStudent.this);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {

					try {
						Login.lookAndFeel = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
						Login.lookAndFeelBool = false;
						rbnDarkMode.setSelected(false);
						UIManager.setLookAndFeel(Login.lookAndFeel);
						SwingUtilities.updateComponentTreeUI(Betreuer_ShowStudent.this);
					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

			}
		});

		// every 20 seconds get show table model to see if something in the database
		// changed
		// caution swing isnt thread save dont modify anything in another thread
		new Thread() {
			public void run() {

				try {
					while (true) {

						// check type of user to show the right table

						if (DBaccessRead.getNoteArbeit(student.getMatrikelnummer()) > 0) {
							txfNoteAr.setText(Double.toString(DBaccessRead.getNoteArbeit(student.getMatrikelnummer())));
							txfNoteAr.setEditable(false);
						}
						if (DBaccessRead.getNoteVortrag(student.getMatrikelnummer()) > 0) {
							txfNoteVor
									.setText(Double.toString(DBaccessRead.getNoteVortrag(student.getMatrikelnummer())));
							txfNoteVor.setEditable(false);

						}
						if (DBaccessRead.getNoteGesamt(student.getMatrikelnummer()) > 0) {
							txfNoteGes
									.setText(Double.toString(DBaccessRead.getNoteGesamt(student.getMatrikelnummer())));

						}

						sleep(20000);
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
}
