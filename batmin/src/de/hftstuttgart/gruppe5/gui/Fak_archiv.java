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
 * 
 */
public class Fak_archiv extends JFrame {

	private JPanel contentPane;
	private JTable tblStAn;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			

			public void run() {
				try {
					Fak_archiv frame = new Fak_archiv();
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
	public Fak_archiv() {
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
	

		JPanel StAnsicht = new JPanel();
		tabbedPane.addTab("Fakultätsarchiv", null, StAnsicht, null);

	
		StAnsicht.setLayout(null);

		JScrollPane spStAn = new JScrollPane();
		spStAn.setBounds(75, 76, 742, 342);
		StAnsicht.add(spStAn);

		this.tblStAn = new JTable();
		spStAn.setViewportView(tblStAn);
		
		JButton btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.setBounds(415, 449, 89, 23);
		btnBesttigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Select a Student from table
				int columnSel = 0;
				int row = tblStAn.getSelectedRow();
				int userid = Integer.parseInt(tblStAn.getModel().getValueAt(row, columnSel).toString());

				// Set attribute bbetreuer for this student to 1
				DBaccessWrite.setConfirmFarchiv(userid);
				// revalidate tables
				showStudentWithTopNotConf(tblStAn);
				
				JOptionPane.showConfirmDialog(null, "Studenten wurden in der Datenbank empfangen und vermerkt!");
				
				
			}
		});
		StAnsicht.add(btnBesttigen);
		
		JLabel lblFakulttsarchiv = new JLabel("Fakult\u00E4tsarchiv");
		lblFakulttsarchiv.setBounds(75, 37, 169, 28);
		lblFakulttsarchiv.setFont(new Font("Tahoma", Font.PLAIN, 20));
		StAnsicht.add(lblFakulttsarchiv);
		
		JRadioButton rbnDarkMode = new JRadioButton("Dark Mode");
		rbnDarkMode.setBounds(792, 431, 102, 58);
		StAnsicht.add(rbnDarkMode);
		rbnDarkMode.setVisible(true);
		
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
		
								SwingUtilities.updateComponentTreeUI(Fak_archiv.this);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
		
						} else {
		
							try {
								Login.lookAndFeel = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
								Login.lookAndFeelBool = false;
								rbnDarkMode.setSelected(false);
								UIManager.setLookAndFeel(Login.lookAndFeel);
								SwingUtilities.updateComponentTreeUI(Fak_archiv.this);
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
		
		//every 20 seconds get show table model to see if something in the database changed
		//caution swing isnt thread save dont modify anything in another thread
		new Thread() {
			public void run() {
			
			try {
				while (true) {
					
					showStudentWithTopNotConf(tblStAn);
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
	 * create table model of students and farchiv has to confirm that their bachelor thesis arrived
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

		for (int i = 0; i < DBaccessRead.getStudentWithTopAndBeNoFa().size(); i++) {
			int mnr = DBaccessRead.getStudentWithTopAndBeNoFa().get(i).getMatrikelnummer();
			rowData[0] = mnr;
			rowData[1] = DBaccessRead.getStudentWithTopAndBeNoFa().get(i).getVorname();
			rowData[2] = DBaccessRead.getStudentWithTopAndBeNoFa().get(i).getNachname();
			rowData[3] = DBaccessRead.getBachelorTopic(mnr);
			rowData[4] = DBaccessRead.getStudentWithTopAndBeNoFa().get(i).getFirma();

			model.addRow(rowData);
		}
		// show table
		tblStAn.setModel(model);
	}

}
