package de.hftstuttgart.gruppe5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.hftstuttgart.gruppe5.data.Student;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessRead;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.List;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Window.Type;
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
public class Student_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txfZwischenV1;
	private JTextField txfZwischenV2;
	private JTextField txfZwischenV3;
	private JTextField txfUploadZwischenV;
	private JTextField txfCommentZv1;
	private JTextField txfCommentZv2;
	private JTextField txfCommentZv3;

	Student student;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			Student student;

			public void run() {
				try {
					Student_GUI frame = new Student_GUI(this.student);
					frame.addComponentListener(new ComponentAdapter() {
						public void componentResized(ComponentEvent e) {
							titleAlign(frame);
						}

					});

					frame.pack();
					frame.setLocationRelativeTo(null);
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
	 * @param string
	 */
	public Student_GUI(Student student) {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/jtattoo/demo/images/batmin3d.png")));
		setFont(new Font("Dialog", Font.PLAIN, 19));
		setTitle("Student");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblBetreuer = new JLabel("Betreuer:");
		lblBetreuer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblBetreuer = new GridBagConstraints();
		gbc_lblBetreuer.gridwidth = 3;
		gbc_lblBetreuer.anchor = GridBagConstraints.WEST;
		gbc_lblBetreuer.insets = new Insets(0, 0, 5, 5);
		gbc_lblBetreuer.gridx = 0;
		gbc_lblBetreuer.gridy = 0;
		contentPane.add(lblBetreuer, gbc_lblBetreuer);

		JLabel lblThema = new JLabel("Thema:");
		lblThema.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblThema = new GridBagConstraints();
		gbc_lblThema.anchor = GridBagConstraints.WEST;
		gbc_lblThema.gridwidth = 6;
		gbc_lblThema.insets = new Insets(0, 0, 5, 5);
		gbc_lblThema.gridx = 3;
		gbc_lblThema.gridy = 0;
		contentPane.add(lblThema, gbc_lblThema);

		JLabel lblFirma = new JLabel("Firma:");
		lblFirma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFirma = new GridBagConstraints();
		gbc_lblFirma.gridwidth = 4;
		gbc_lblFirma.anchor = GridBagConstraints.WEST;
		gbc_lblFirma.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirma.gridx = 9;
		gbc_lblFirma.gridy = 0;
		contentPane.add(lblFirma, gbc_lblFirma);

		JLabel lblFirmenbetreuer = new JLabel("Firmenbetreuer:");
		lblFirmenbetreuer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFirmenbetreuer = new GridBagConstraints();
		gbc_lblFirmenbetreuer.anchor = GridBagConstraints.WEST;
		gbc_lblFirmenbetreuer.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirmenbetreuer.gridx = 13;
		gbc_lblFirmenbetreuer.gridy = 0;
		contentPane.add(lblFirmenbetreuer, gbc_lblFirmenbetreuer);

		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDatum = new GridBagConstraints();
		gbc_lblDatum.anchor = GridBagConstraints.WEST;
		gbc_lblDatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatum.gridx = 18;
		gbc_lblDatum.gridy = 0;
		contentPane.add(lblDatum, gbc_lblDatum);

		JLabel lblBetreuerX = new JLabel(DBaccessRead.getBetreuerofStudent(student.getMatrikelnummer()).getVorname()
				+ " " + DBaccessRead.getBetreuerofStudent(student.getMatrikelnummer()).getNachname());
		GridBagConstraints gbc_lblBetreuerX = new GridBagConstraints();
		gbc_lblBetreuerX.gridwidth = 3;
		gbc_lblBetreuerX.anchor = GridBagConstraints.WEST;
		gbc_lblBetreuerX.insets = new Insets(0, 0, 5, 5);
		gbc_lblBetreuerX.gridx = 0;
		gbc_lblBetreuerX.gridy = 1;
		contentPane.add(lblBetreuerX, gbc_lblBetreuerX);

		JLabel lblThemaX = new JLabel(DBaccessRead.getBachelorTopic(student.getMatrikelnummer()));
		GridBagConstraints gbc_lblThemaX = new GridBagConstraints();
		gbc_lblThemaX.anchor = GridBagConstraints.WEST;
		gbc_lblThemaX.gridwidth = 6;
		gbc_lblThemaX.insets = new Insets(0, 0, 5, 5);
		gbc_lblThemaX.gridx = 3;
		gbc_lblThemaX.gridy = 1;
		contentPane.add(lblThemaX, gbc_lblThemaX);

		JLabel lblFirmaX = new JLabel(DBaccessRead.getFirma(student.getMatrikelnummer()));
		GridBagConstraints gbc_lblFirmaX = new GridBagConstraints();
		gbc_lblFirmaX.anchor = GridBagConstraints.WEST;
		gbc_lblFirmaX.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirmaX.gridx = 9;
		gbc_lblFirmaX.gridy = 1;
		contentPane.add(lblFirmaX, gbc_lblFirmaX);

		JLabel lblFirmenbetreuerX = new JLabel(DBaccessRead.getFirmenBetreuer(student.getMatrikelnummer()));
		GridBagConstraints gbc_lblFirmenbetreuerX = new GridBagConstraints();
		gbc_lblFirmenbetreuerX.anchor = GridBagConstraints.WEST;
		gbc_lblFirmenbetreuerX.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirmenbetreuerX.gridx = 13;
		gbc_lblFirmenbetreuerX.gridy = 1;
		contentPane.add(lblFirmenbetreuerX, gbc_lblFirmenbetreuerX);

		JLabel lblVonX = new JLabel(DBaccessRead.getDateFrom(student.getMatrikelnummer()));
		GridBagConstraints gbc_lblVonX = new GridBagConstraints();
		gbc_lblVonX.anchor = GridBagConstraints.WEST;
		gbc_lblVonX.insets = new Insets(0, 0, 5, 5);
		gbc_lblVonX.gridx = 18;
		gbc_lblVonX.gridy = 1;
		contentPane.add(lblVonX, gbc_lblVonX);

		JLabel lblBisY = new JLabel(DBaccessRead.getDateUntil(student.getMatrikelnummer()));
		GridBagConstraints gbc_lblBisY = new GridBagConstraints();
		gbc_lblBisY.anchor = GridBagConstraints.WEST;
		gbc_lblBisY.insets = new Insets(0, 0, 5, 5);
		gbc_lblBisY.gridx = 18;
		gbc_lblBisY.gridy = 2;
		contentPane.add(lblBisY, gbc_lblBisY);

		JLabel lblZwischenversion_1 = new JLabel("Zwischenversion 1:");
		lblZwischenversion_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblZwischenversion_1 = new GridBagConstraints();
		gbc_lblZwischenversion_1.anchor = GridBagConstraints.WEST;
		gbc_lblZwischenversion_1.gridwidth = 4;
		gbc_lblZwischenversion_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblZwischenversion_1.gridx = 0;
		gbc_lblZwischenversion_1.gridy = 6;
		contentPane.add(lblZwischenversion_1, gbc_lblZwischenversion_1);

		JLabel lblKommentar1 = new JLabel("Kommentar:");
		lblKommentar1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblKommentar1 = new GridBagConstraints();
		gbc_lblKommentar1.anchor = GridBagConstraints.WEST;
		gbc_lblKommentar1.gridwidth = 3;
		gbc_lblKommentar1.insets = new Insets(0, 0, 5, 5);
		gbc_lblKommentar1.gridx = 9;
		gbc_lblKommentar1.gridy = 6;
		contentPane.add(lblKommentar1, gbc_lblKommentar1);

		JLabel lblNoten = new JLabel("Noten:");
		lblNoten.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNoten = new GridBagConstraints();
		gbc_lblNoten.anchor = GridBagConstraints.WEST;
		gbc_lblNoten.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoten.gridx = 18;
		gbc_lblNoten.gridy = 6;
		contentPane.add(lblNoten, gbc_lblNoten);

		txfZwischenV1 = new JTextField();
		GridBagConstraints gbc_txfZwischenV1 = new GridBagConstraints();
		gbc_txfZwischenV1.gridwidth = 3;
		gbc_txfZwischenV1.insets = new Insets(0, 0, 5, 5);
		gbc_txfZwischenV1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfZwischenV1.gridx = 0;
		gbc_txfZwischenV1.gridy = 7;
		contentPane.add(txfZwischenV1, gbc_txfZwischenV1);
		txfZwischenV1.setColumns(10);

		txfCommentZv1 = new JTextField();
		GridBagConstraints gbc_txfCommentZv1 = new GridBagConstraints();
		gbc_txfCommentZv1.anchor = GridBagConstraints.NORTH;
		gbc_txfCommentZv1.gridwidth = 7;
		gbc_txfCommentZv1.insets = new Insets(0, 0, 5, 5);
		gbc_txfCommentZv1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfCommentZv1.gridx = 9;
		gbc_txfCommentZv1.gridy = 7;
		contentPane.add(txfCommentZv1, gbc_txfCommentZv1);
		txfCommentZv1.setColumns(10);

		JLabel lblVortrag = new JLabel("Vortrag:");
		GridBagConstraints gbc_lblVortrag = new GridBagConstraints();
		gbc_lblVortrag.anchor = GridBagConstraints.WEST;
		gbc_lblVortrag.insets = new Insets(0, 0, 5, 5);
		gbc_lblVortrag.gridx = 18;
		gbc_lblVortrag.gridy = 7;
		contentPane.add(lblVortrag, gbc_lblVortrag);

		JLabel lblNoteVor = new JLabel(Double.toString(DBaccessRead.getNoteVortrag(student.getMatrikelnummer())));
		GridBagConstraints gbc_lblNoteVor = new GridBagConstraints();
		gbc_lblNoteVor.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoteVor.gridx = 21;
		gbc_lblNoteVor.gridy = 7;
		contentPane.add(lblNoteVor, gbc_lblNoteVor);

		JLabel lblArbeit = new JLabel("Arbeit:");
		GridBagConstraints gbc_lblArbeit = new GridBagConstraints();
		gbc_lblArbeit.anchor = GridBagConstraints.WEST;
		gbc_lblArbeit.insets = new Insets(0, 0, 5, 5);
		gbc_lblArbeit.gridx = 18;
		gbc_lblArbeit.gridy = 8;
		contentPane.add(lblArbeit, gbc_lblArbeit);

		JLabel lblNoteAr = new JLabel(Double.toString(DBaccessRead.getNoteArbeit(student.getMatrikelnummer())));
		GridBagConstraints gbc_lblNoteAr = new GridBagConstraints();
		gbc_lblNoteAr.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoteAr.gridx = 21;
		gbc_lblNoteAr.gridy = 8;
		contentPane.add(lblNoteAr, gbc_lblNoteAr);

		JLabel lblZwischenversion_2 = new JLabel("Zwischenversion 2:");
		lblZwischenversion_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblZwischenversion_2 = new GridBagConstraints();
		gbc_lblZwischenversion_2.anchor = GridBagConstraints.WEST;
		gbc_lblZwischenversion_2.gridwidth = 4;
		gbc_lblZwischenversion_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblZwischenversion_2.gridx = 0;
		gbc_lblZwischenversion_2.gridy = 9;
		contentPane.add(lblZwischenversion_2, gbc_lblZwischenversion_2);

		JLabel lblKommentar2 = new JLabel("Kommentar:");
		lblKommentar2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblKommentar2 = new GridBagConstraints();
		gbc_lblKommentar2.anchor = GridBagConstraints.WEST;
		gbc_lblKommentar2.gridwidth = 3;
		gbc_lblKommentar2.insets = new Insets(0, 0, 5, 5);
		gbc_lblKommentar2.gridx = 9;
		gbc_lblKommentar2.gridy = 9;
		contentPane.add(lblKommentar2, gbc_lblKommentar2);

		JLabel lblGesamtnote = new JLabel("Gesamtnote (12:3):");
		GridBagConstraints gbc_lblGesamtnote = new GridBagConstraints();
		gbc_lblGesamtnote.anchor = GridBagConstraints.WEST;
		gbc_lblGesamtnote.insets = new Insets(0, 0, 5, 5);
		gbc_lblGesamtnote.gridx = 18;
		gbc_lblGesamtnote.gridy = 9;
		contentPane.add(lblGesamtnote, gbc_lblGesamtnote);

		JLabel lblNoteGes = new JLabel(Double.toString(DBaccessRead.getNoteGesamt(student.getMatrikelnummer())));
		GridBagConstraints gbc_lblNoteGes = new GridBagConstraints();
		gbc_lblNoteGes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoteGes.gridx = 21;
		gbc_lblNoteGes.gridy = 9;
		contentPane.add(lblNoteGes, gbc_lblNoteGes);

		txfZwischenV2 = new JTextField();
		txfZwischenV2.setColumns(10);
		GridBagConstraints gbc_txfZwischenV2 = new GridBagConstraints();
		gbc_txfZwischenV2.anchor = GridBagConstraints.NORTH;
		gbc_txfZwischenV2.gridwidth = 3;
		gbc_txfZwischenV2.insets = new Insets(0, 0, 5, 5);
		gbc_txfZwischenV2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfZwischenV2.gridx = 0;
		gbc_txfZwischenV2.gridy = 10;
		contentPane.add(txfZwischenV2, gbc_txfZwischenV2);

		txfCommentZv2 = new JTextField();
		txfCommentZv2.setColumns(10);
		GridBagConstraints gbc_txfCommentZv2 = new GridBagConstraints();
		gbc_txfCommentZv2.gridwidth = 7;
		gbc_txfCommentZv2.insets = new Insets(0, 0, 5, 5);
		gbc_txfCommentZv2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfCommentZv2.gridx = 9;
		gbc_txfCommentZv2.gridy = 10;
		contentPane.add(txfCommentZv2, gbc_txfCommentZv2);

		JLabel lblZwischenversion_3 = new JLabel("Zwischenversion 3:");
		lblZwischenversion_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblZwischenversion_3 = new GridBagConstraints();
		gbc_lblZwischenversion_3.anchor = GridBagConstraints.WEST;
		gbc_lblZwischenversion_3.gridwidth = 4;
		gbc_lblZwischenversion_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblZwischenversion_3.gridx = 0;
		gbc_lblZwischenversion_3.gridy = 12;
		contentPane.add(lblZwischenversion_3, gbc_lblZwischenversion_3);

		JLabel lblKommentar3 = new JLabel("Kommentar:");
		lblKommentar3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblKommentar3 = new GridBagConstraints();
		gbc_lblKommentar3.anchor = GridBagConstraints.WEST;
		gbc_lblKommentar3.gridwidth = 3;
		gbc_lblKommentar3.insets = new Insets(0, 0, 5, 5);
		gbc_lblKommentar3.gridx = 9;
		gbc_lblKommentar3.gridy = 12;
		contentPane.add(lblKommentar3, gbc_lblKommentar3);

		txfZwischenV3 = new JTextField();
		txfZwischenV3.setColumns(10);
		GridBagConstraints gbc_txfZwischenV3 = new GridBagConstraints();
		gbc_txfZwischenV3.gridwidth = 3;
		gbc_txfZwischenV3.insets = new Insets(0, 0, 5, 5);
		gbc_txfZwischenV3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfZwischenV3.gridx = 0;
		gbc_txfZwischenV3.gridy = 13;
		contentPane.add(txfZwischenV3, gbc_txfZwischenV3);

		txfCommentZv3 = new JTextField();
		txfCommentZv3.setColumns(10);
		GridBagConstraints gbc_txfCommentZv3 = new GridBagConstraints();
		gbc_txfCommentZv3.gridwidth = 7;
		gbc_txfCommentZv3.insets = new Insets(0, 0, 5, 5);
		gbc_txfCommentZv3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfCommentZv3.gridx = 9;
		gbc_txfCommentZv3.gridy = 13;
		contentPane.add(txfCommentZv3, gbc_txfCommentZv3);

		JButton btnNewButton = new JButton("Hochladen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Not implemented yet!");
			}
		});

		JLabel lblZwischenversionHochladen = new JLabel("Zwischenversion hochladen:");
		lblZwischenversionHochladen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblZwischenversionHochladen = new GridBagConstraints();
		gbc_lblZwischenversionHochladen.anchor = GridBagConstraints.WEST;
		gbc_lblZwischenversionHochladen.gridwidth = 6;
		gbc_lblZwischenversionHochladen.insets = new Insets(0, 0, 5, 5);
		gbc_lblZwischenversionHochladen.gridx = 0;
		gbc_lblZwischenversionHochladen.gridy = 16;
		contentPane.add(lblZwischenversionHochladen, gbc_lblZwischenversionHochladen);

		JLabel lblRichtigkeitDer = new JLabel("Richtigkeit der");
		lblRichtigkeitDer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblRichtigkeitDer = new GridBagConstraints();
		gbc_lblRichtigkeitDer.gridwidth = 4;
		gbc_lblRichtigkeitDer.insets = new Insets(0, 0, 5, 5);
		gbc_lblRichtigkeitDer.gridx = 18;
		gbc_lblRichtigkeitDer.gridy = 16;
		contentPane.add(lblRichtigkeitDer, gbc_lblRichtigkeitDer);

		txfUploadZwischenV = new JTextField();
		txfUploadZwischenV.setColumns(10);
		GridBagConstraints gbc_txfUploadZwischenV = new GridBagConstraints();
		gbc_txfUploadZwischenV.gridwidth = 3;
		gbc_txfUploadZwischenV.insets = new Insets(0, 0, 5, 5);
		gbc_txfUploadZwischenV.fill = GridBagConstraints.HORIZONTAL;
		gbc_txfUploadZwischenV.gridx = 0;
		gbc_txfUploadZwischenV.gridy = 17;
		contentPane.add(txfUploadZwischenV, gbc_txfUploadZwischenV);

		JLabel lblAngabenbe = new JLabel("Angaben best\u00E4tigen");
		lblAngabenbe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblAngabenbe = new GridBagConstraints();
		gbc_lblAngabenbe.gridwidth = 4;
		gbc_lblAngabenbe.insets = new Insets(0, 0, 5, 5);
		gbc_lblAngabenbe.gridx = 18;
		gbc_lblAngabenbe.gridy = 17;
		contentPane.add(lblAngabenbe, gbc_lblAngabenbe);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 19;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		//this will close the system further implementation has to be done
		JButton btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnBesttigen = new GridBagConstraints();
		gbc_btnBesttigen.gridwidth = 4;
		gbc_btnBesttigen.insets = new Insets(0, 0, 5, 5);
		gbc_btnBesttigen.gridx = 18;
		gbc_btnBesttigen.gridy = 19;
		contentPane.add(btnBesttigen, gbc_btnBesttigen);

		JRadioButton rbnDarkMode = new JRadioButton("Dark Mode");

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

						SwingUtilities.updateComponentTreeUI(Student_GUI.this);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {

					try {
						Login.lookAndFeel = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
						Login.lookAndFeelBool = false;
						rbnDarkMode.setSelected(false);
						UIManager.setLookAndFeel(Login.lookAndFeel);
						SwingUtilities.updateComponentTreeUI(Student_GUI.this);
					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

			}
		});
		GridBagConstraints gbc_rbnDarkMode = new GridBagConstraints();
		gbc_rbnDarkMode.gridwidth = 4;
		gbc_rbnDarkMode.insets = new Insets(0, 0, 0, 5);
		gbc_rbnDarkMode.gridx = 18;
		gbc_rbnDarkMode.gridy = 20;
		contentPane.add(rbnDarkMode, gbc_rbnDarkMode);

		// thread to automaticlly refresh the labels for the marks of the student
		new Thread() {
			public void run() {
				while (true) {
					try {
						lblNoteAr.setText(Double.toString(DBaccessRead.getNoteArbeit(student.getMatrikelnummer())));
						lblNoteVor.setText(Double.toString(DBaccessRead.getNoteVortrag(student.getMatrikelnummer())));
						lblNoteGes.setText(Double.toString(DBaccessRead.getNoteGesamt(student.getMatrikelnummer())));
						sleep(20000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}.start();
	}

	private static void titleAlign(JFrame frame) {

		Font font = frame.getFont();

		String currentTitle = frame.getTitle().trim();
		FontMetrics fm = frame.getFontMetrics(font);
		int frameWidth = frame.getWidth();
		int titleWidth = fm.stringWidth(currentTitle);
		int spaceWidth = fm.stringWidth(" ");
		int centerPos = (frameWidth / 2) - (titleWidth / 2);
		int spaceCount = centerPos / spaceWidth;
		String pad = "";
		pad = String.format("%" + (spaceCount - 14) + "s", pad);
		frame.setTitle(pad + currentTitle);

	}

}
