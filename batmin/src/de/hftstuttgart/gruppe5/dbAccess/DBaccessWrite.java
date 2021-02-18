package de.hftstuttgart.gruppe5.dbAccess;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

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
public class DBaccessWrite {
	private static Connection conn = DBConnector.getCon();

	/**
	 * 
	 * 
	 * @param titel
	 * @param mnr
	 * @param firma
	 * @param firmaBe
	 * @param dateVon
	 * @param dateBis
	 * @param bePnr
	 */
	public static void setBA(String titel, int mnr, String firma, String dateVon,
			String dateBis, int bePnr, String firmenbetreuer) {

		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
						"INSERT INTO bachelorarbeit(titel, anfang, ende , firma, fk_student_matrikelnr_id , fk_hft_angestellte_angestellte_id, firmenbetreuer) "
								+ "VALUES (?,?,?,?,?,?,?)");
				// fill preparedStatement with data
				ps.setString(1, titel);
				ps.setString(2, dateVon);
				ps.setString(3, dateBis);
				ps.setString(4, firma);
				ps.setInt(5, mnr);
				ps.setInt(6, bePnr);
				ps.setString(7, firmenbetreuer);

				ps.execute();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}

	}

	/**
	 * Studendekan confirm topic
	 * 
	 * @param mnr matrikelnummer of student
	 */
	public static void setConfirmTopic(int mnr) {
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("UPDATE bachelorarbeit SET bDekan= 1 WHERE fk_student_matrikelnr_id= ?");
				ps.setInt(1, mnr);

				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * 
	 * Confirm that Betreuer for student
	 * 
	 * @param mnr matrikelnumer of student
	 */
	public static void setConfirmBetreuer(int mnr) {
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("UPDATE student SET bBetreuer= 1 WHERE matrikelnr_id= ?");
				ps.setInt(1, mnr);

				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// insert a new student into database
	public static void setStudent(int mnr, String vorname, String nachname, int plz, String stadt, String fakultaet,
			String password) {
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
						"INSERT INTO student(matrikelnr_id, vorname, nachname , plz, stadt, fakultaet, passwort) "
								+ "VALUES (?,?,?,?,?,?,?)");
				// fill preparedStatement with data
				ps.setInt(1, mnr);
				ps.setString(2, vorname);
				ps.setString(3, nachname);
				ps.setInt(4, plz);
				ps.setString(5, stadt);
				ps.setString(6, fakultaet);
				ps.setString(7, password);

				ps.execute();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * sets mark of presentation
	 * 
	 * @param mnr
	 * @param note
	 */
	public static void setNoteVortrag(int mnr, double note) {
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("UPDATE bachelorarbeit SET vortrag= ? WHERE fk_student_matrikelnr_id= ?");
				ps.setDouble(1, note);
				ps.setInt(2, mnr);

				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * sets mark of thesis
	 * 
	 * @param mnr
	 * @param note
	 */
	public static void setNoteArbeit(int mnr, double note) {
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("UPDATE bachelorarbeit SET arbeit= ? WHERE fk_student_matrikelnr_id= ?");
				ps.setDouble(1, note);
				ps.setInt(2, mnr);

				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * sets overall mark
	 * 
	 * @param mnr
	 * @param note
	 */
	public static void setNoteGesamt(int mnr, double note) {
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("UPDATE bachelorarbeit SET gnote= ? WHERE fk_student_matrikelnr_id= ?");
				ps.setDouble(1, note);
				ps.setInt(2, mnr);

				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * set confirmation of arrived thesis at farchiv
	 * 
	 * @param mnr
	 */
	public static void setConfirmFarchiv(int mnr) {
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement("UPDATE bachelorarbeit SET bfarchiv= 1 WHERE fk_student_matrikelnr_id= ?");
				ps.setInt(1, mnr);

				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
