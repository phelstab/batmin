package de.hftstuttgart.gruppe5.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import de.hftstuttgart.gruppe5.data.Dozent;
import de.hftstuttgart.gruppe5.data.Student;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessRead;
import de.hftstuttgart.gruppe5.dbAccess.DBaccessWrite;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Lukas Ringle
 * @author Stefan Binninger
 * @author Sven Turowski
 * @author Paul Helstab
 * @author Lino Schmidt
 * @author Sebastian Mueller
 */
public class SelectStudent extends JFrame {

	private JPanel contentPane;
	private JTable tblStudent;
	private JTable tblStAn;

	Dozent dozent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			Dozent betreuer;

			public void run() {
				try {
					SelectStudent frame = new SelectStudent(this.betreuer);
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
	public SelectStudent(Dozent currentDozent) {
		this.dozent = currentDozent;
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/jtattoo/demo/images/batmin3d.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 884, 511);
		contentPane.add(tabbedPane);

		JPanel Eintraege = new JPanel();
		tabbedPane.addTab("Eintraege", null, Eintraege, null);
		Eintraege.setLayout(null);

		JScrollPane spEin = new JScrollPane();
		spEin.setBounds(193, 34, 489, 389);
		Eintraege.add(spEin);

		this.tblStudent = new JTable();
		spEin.setViewportView(tblStudent);

		JButton btnGetStudent = new JButton("Get Student");
		btnGetStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblStudent.getSelectedRow() >= 0) {
					// Select student from table and show data of Student in a new frame
					int columnSel = 0;
					int row = tblStudent.getSelectedRow();
					int userid = (int) tblStudent.getModel().getValueAt(row, columnSel);

					Betreuer_ShowStudent bSelectedStudent = new Betreuer_ShowStudent(
							DBaccessRead.getStudentbyMNR(userid));
					bSelectedStudent.setVisible(true);

				} else if (tblStudent.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row");
				}
			}
		});

		btnGetStudent.setBounds(395, 449, 130, 23);
		Eintraege.add(btnGetStudent);

		JPanel StAnsicht = new JPanel();
		tabbedPane.addTab("Studentenansicht", null, StAnsicht, null);
		StAnsicht.setLayout(null);

		// set label for current user
		if (currentDozent.getSdekan() == 1) {

			JLabel lblUeberschrift = new JLabel("Thema fuer Student bestaetigen:");
			lblUeberschrift.setBounds(34, 6, 308, 22);
			lblUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 18));
			StAnsicht.add(lblUeberschrift);
			
			JLabel lblUeberschriftEintrag = new JLabel("Liste aller Studenten mit Betreuer");
			lblUeberschriftEintrag.setBounds(34, 9, 408, 14);
			lblUeberschriftEintrag.setFont(new Font("Tahoma", Font.PLAIN, 16));
			Eintraege.add(lblUeberschriftEintrag);

		} else {

			JLabel lblUeberschrift = new JLabel("Als Betreuer bei Projekten eingetragen:");
			lblUeberschrift.setBounds(34, 6, 308, 22);
			lblUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 18));
			StAnsicht.add(lblUeberschrift);
			
			JLabel lblUeberschriftEintrag = new JLabel("Liste aller zu betreuenden Studenten");
			lblUeberschriftEintrag.setBounds(34, 9, 408, 14);
			lblUeberschriftEintrag.setFont(new Font("Tahoma", Font.PLAIN, 16));
			Eintraege.add(lblUeberschriftEintrag);

		}

		JRadioButton rbnDarkMode = new JRadioButton("Dark Mode");
		rbnDarkMode.setBounds(760, 450, 113, 21);
		rbnDarkMode.setSelected(Login.getLookandFeelBool());
		Eintraege.add(rbnDarkMode);

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

						SwingUtilities.updateComponentTreeUI(SelectStudent.this);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {

					try {
						Login.lookAndFeel = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
						Login.lookAndFeelBool = false;
						rbnDarkMode.setSelected(false);
						UIManager.setLookAndFeel(Login.lookAndFeel);
						SwingUtilities.updateComponentTreeUI(SelectStudent.this);
					} catch (Exception ex) {

						ex.printStackTrace();
					}
				}

			}
		});

		JLabel lblTextConf = new JLabel("Auswahl bestaetigen :");
		lblTextConf.setBounds(446, 449, 185, 19);
		lblTextConf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		StAnsicht.add(lblTextConf);

		JScrollPane spStAn = new JScrollPane();
		spStAn.setBounds(75, 76, 742, 342);
		StAnsicht.add(spStAn);

		this.tblStAn = new JTable();
		spStAn.setViewportView(tblStAn);

		JButton btnconfirm = new JButton("confirm");
		btnconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check if row selected

				if (tblStAn.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row");
				} else {
					if (currentDozent.getSdekan() == 0) {
						// Select a Student from table
						int columnSel = 0;
						int row = tblStAn.getSelectedRow();
						int userid = Integer.parseInt(tblStAn.getModel().getValueAt(row, columnSel).toString());

						// Set attribute bbetreuer for this student to 1
						DBaccessWrite.setConfirmBetreuer(userid);
						// revalidate tables
						showstudentsNoBe(tblStAn);
						showStudentWithBe(tblStudent, currentDozent.getPnr());
					} else if (currentDozent.getSdekan() == 1) {
						// Select a Student from table
						int columnSel = 0;
						int row = tblStAn.getSelectedRow();
						int userid = Integer.parseInt(tblStAn.getModel().getValueAt(row, columnSel).toString());

						// Set attribute bbetreuer for this student to 1
						DBaccessWrite.setConfirmTopic(userid);
						// revalidate tables
						showStudentWithTopNotConf(tblStAn);
						showStudentTopicConf(tblStudent);
					}
				}

			}
		});

		btnconfirm.setBounds(641, 445, 122, 27);
		btnconfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		StAnsicht.add(btnconfirm);

		// every 20 seconds get show table model to see if something in the database
		// changed
		// caution swing isnt thread save dont modify anything in another thread
		new Thread() {
			public void run() {
				try {
					while (true) {

						// check type of user to show the right table
						if (currentDozent.getSdekan() == 0) {
							showstudentsNoBe(tblStAn);
							showStudentWithBe(tblStudent, currentDozent.getPnr());

						} else {
							showStudentWithTopNotConf(tblStAn);
							showStudentTopicConf(tblStudent);
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

	/**
	 * show students with confirmed topic but betreuer isnt confirmed yet creat
	 * table model for that
	 * 
	 * @param tblStAn
	 */
	public void showstudentsNoBe(JTable tblStAn) {
		// create new table in tab StudentenAnsicht
		DefaultTableModel modelStNoBe = new DefaultTableModel();

		Object[] columnsNameStAn = new Object[5];
		columnsNameStAn[0] = "Matrikelnumer";
		columnsNameStAn[1] = "Vorname";
		columnsNameStAn[2] = "Nachname";
		columnsNameStAn[3] = "Thema";
		columnsNameStAn[4] = "Firma";

		modelStNoBe.setColumnIdentifiers(columnsNameStAn);
		// fill table with students without betreuer
		// or who choose the betreuer logged in
		Object[] rowDataStAn = new Object[5];
		for (int i = 0; i < DBaccessRead.getStudentWithTopicNoBe().size(); i++) {
			rowDataStAn[0] = DBaccessRead.getStudentWithTopicNoBe().get(i).getMatrikelnummer();
			rowDataStAn[1] = DBaccessRead.getStudentWithTopicNoBe().get(i).getVorname();
			rowDataStAn[2] = DBaccessRead.getStudentWithTopicNoBe().get(i).getNachname();
			rowDataStAn[3] = DBaccessRead.getStudentWithTopicNoBe().get(i).getThema();
			rowDataStAn[4] = DBaccessRead.getStudentWithTopicNoBe().get(i).getFirma();
			System.out.println(DBaccessRead.getStudentWithTopicNoBe().get(i).getFirma());
			modelStNoBe.addRow(rowDataStAn);

		}
		tblStAn.setModel(modelStNoBe);

	}

	/**
	 * get table model for students with Betreuer confirmed
	 * 
	 * @param tblStudent
	 * @param pnr
	 */
	public void showStudentWithBe(JTable tblStudent, int pnr) {
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[5];
		columnsName[0] = "Matrikelnummer";
		columnsName[1] = "Vorname";
		columnsName[2] = "Nachname";
		columnsName[3] = "Titel";
		columnsName[4] = "Firma";

		model.setColumnIdentifiers(columnsName);

		// fill table here
		Object[] rowData = new Object[5];

		for (int i = 0; i < DBaccessRead.getStudentsofBe(pnr).size(); i++) {
			int mnr = DBaccessRead.getStudentsofBe(pnr).get(i).getMatrikelnummer();
			rowData[0] = mnr;
			rowData[1] = DBaccessRead.getStudentsofBe(pnr).get(i).getVorname();
			rowData[2] = DBaccessRead.getStudentsofBe(pnr).get(i).getNachname();
			rowData[3] = DBaccessRead.getBachelorTopic(mnr);
			rowData[4] = DBaccessRead.getStudentsofBe(pnr).get(i).getFirma();

			model.addRow(rowData);
		}
		// show table
		tblStudent.setModel(model);
	}

	/**
	 * create table model for students with topic not confirmed
	 * 
	 * @param tblStAn
	 */
	public void showStudentWithTopNotConf(JTable tblStAn) {
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[5];
		columnsName[0] = "Matrikelnummer";
		columnsName[1] = "Vorname";
		columnsName[2] = "Nachname";
		columnsName[3] = "Titel";
		columnsName[4] = "Firma";

		model.setColumnIdentifiers(columnsName);

		// fill table here
		Object[] rowData = new Object[5];

		for (int i = 0; i < DBaccessRead.getStudentWithTopicNotConf().size(); i++) {
			int mnr = DBaccessRead.getStudentWithTopicNotConf().get(i).getMatrikelnummer();
			rowData[0] = mnr;
			rowData[1] = DBaccessRead.getStudentWithTopicNotConf().get(i).getVorname();
			rowData[2] = DBaccessRead.getStudentWithTopicNotConf().get(i).getNachname();
			rowData[3] = DBaccessRead.getBachelorTopic(mnr);
			rowData[4] = DBaccessRead.getStudentWithTopicNotConf().get(i).getFirma();

			model.addRow(rowData);
		}
		// show table
		tblStAn.setModel(model);
	}

	/**
	 * show students with confirmed topic and betreuer
	 * 
	 * @param tblStudent
	 */
	public void showStudentTopicConf(JTable tblStudent) {
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[5];
		columnsName[0] = "Matrikelnummer";
		columnsName[1] = "Vorname";
		columnsName[2] = "Nachname";
		columnsName[3] = "Titel";

		model.setColumnIdentifiers(columnsName);

		// fill table here
		Object[] rowData = new Object[5];

		for (int i = 0; i < DBaccessRead.getStudentWithTopAndBe().size(); i++) {
			int mnr = DBaccessRead.getStudentWithTopAndBe().get(i).getMatrikelnummer();
			rowData[0] = mnr;
			rowData[1] = DBaccessRead.getStudentWithTopAndBe().get(i).getVorname();
			rowData[2] = DBaccessRead.getStudentWithTopAndBe().get(i).getNachname();
			rowData[3] = DBaccessRead.getBachelorTopic(mnr);
			rowData[4] = DBaccessRead.getStudentWithTopAndBe().get(i).getFirma();
			System.out.println(DBaccessRead.getStudentWithTopAndBe().get(i).getVorname());
			model.addRow(rowData);
		}
		// show table
		tblStudent.setModel(model);
	}
}
