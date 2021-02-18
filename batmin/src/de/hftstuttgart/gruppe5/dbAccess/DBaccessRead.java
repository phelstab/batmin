package de.hftstuttgart.gruppe5.dbAccess;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import de.hftstuttgart.gruppe5.data.Dozent;
import de.hftstuttgart.gruppe5.data.FarchivMitarbeiter;
import de.hftstuttgart.gruppe5.data.Student;

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
public class DBaccessRead {

	private static Connection conn = DBConnector.getCon();

	/**
	 * Used to get a specifc student from the database
	 * 
	 * @param mnr is the matrikelnummer of the student
	 * @return it will return an object of a student
	 */
	public static Student getStudentbyMNR(int mnr) {
		Student student = null;
		// check if there is no connection to the database
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
			// Student student = null;
		} else {
			/**
			 * try to execute a SQL statement than create a object of Student and return it
			 */
			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE matrikelnr_id=" + mnr);

				while (rs.next()) {
					student = new Student(mnr, rs.getString("vorname"), rs.getString("nachname"),
							rs.getString("passwort"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return student;
	}

	/**
	 * A method to get all Students in the database
	 * 
	 * @return all students in the database
	 */
	public static ArrayList<Student> getAllStudents() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				Student student;
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM student ORDER BY vorname ASC");

				while (rs.next()) {
					student = new Student(rs.getInt("matrikelnr_id"), rs.getString("vorname"), rs.getString("nachname"),
							rs.getString("passwort"));
					studentList.add(student);
				}
				rs.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return studentList;
	}

	/**
	 * Used to get a specific Betreuer from the database
	 * 
	 * @param pnr ist die personalnummer
	 * @return an object of Betreuer
	 */
	public static Dozent getBetreuerbyPNR(int pnr) {
		Dozent dozent = null;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
			// betreuer = null;
		} else {

			try {
				// Betreuer betreuer
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM hft_angestellte WHERE hft_angestellte_id=" + pnr);

				while (rs.next()) {
					dozent = new Dozent(pnr, rs.getString("vorname"), rs.getString("nachname"),
							rs.getString("passwort"), rs.getInt("studiendekan"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return dozent;
	}

	/**
	 * A method to get all Betreuer in the database
	 * 
	 * @return a List of all Betreuer in the database
	 */
	public static ArrayList<Dozent> getAllBetreuer() {
		ArrayList<Dozent> betreuerListe = new ArrayList<Dozent>();
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
			// betreuer = null;
		} else {
			try {
				Dozent betreuer;
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM hft_angestellte WHERE studiendekan = 0");

				while (rs.next()) {
					betreuer = new Dozent(rs.getInt("hft_angestellte_id"), rs.getString("vorname"),
							rs.getString("nachname"), rs.getString("passwort"), rs.getInt("studiendekan"));
					betreuerListe.add(betreuer);

				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return betreuerListe;
	}

	/**
	 * Used to get a specific Studiendekan from the datbase
	 * 
	 * @param pnr ist die personalnummer
	 * @return an object of Studiendekan
	 */
	public static Dozent getSdekanbyPNR(int pnr) {
		Dozent sdekan = null;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT * FROM hft_angestellte WHERE hft_angestellte_id=" + pnr + " AND studiendekan = 1");

				while (rs.next()) {
					sdekan = new Dozent(pnr, rs.getString("vorname"), rs.getString("nachname"),
							rs.getString("passwort"), rs.getInt("studiendekan"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return sdekan;
	}

	/**
	 * returns topic of the bachelor thesis
	 * 
	 * @param username
	 * @return
	 */
	public static String getBachelorTopic(int username) {
		String bachelortopic = null;

		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT bachelorarbeit.titel FROM student LEFT JOIN bachelorarbeit ON bachelorarbeit.fk_student_matrikelnr_id = student.matrikelnr_id WHERE matrikelnr_id = "
								+ username);

				while (rs.next()) {
					bachelortopic = rs.getString("titel");
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return bachelortopic;
	}

	/**
	 * Selects student from database with confirmed topic but no Betreuer or the who
	 * choosed this betreuer but is not confirmed yet
	 * 
	 * @return
	 */
	public static ArrayList<Student> getStudentWithTopicNoBe() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				// execute sql stmt here
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT matrikelnr_id, vorname, nachname, bachelorarbeit.titel, bachelorarbeit.firma "
								+ "FROM bachelorarbeit "
								+ "LEFT JOIN student ON bachelorarbeit.fk_student_matrikelnr_id = student.matrikelnr_id "
								+ "WHERE bbetreuer IS NULL AND bdekan IS NOT NULL");

				while (rs.next()) {
					Student student = new Student(rs.getInt("matrikelnr_id"), rs.getString("vorname"),
							rs.getString("nachname"), rs.getString("titel"), rs.getString("firma"));
					studentList.add(student);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return studentList;
	}

	/**
	 * Selects all students with a topic which is not confirmed yet
	 * 
	 * @return
	 */
	public static ArrayList<Student> getStudentWithTopicNotConf() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				// execute sql stmt here
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT matrikelnr_id, vorname, nachname, titel, firma "
						+ "FROM bachelorarbeit LEFT JOIN student ON bachelorarbeit.fk_student_matrikelnr_id = student.matrikelnr_id "
						+ "WHERE bdekan IS NULL");

				while (rs.next()) {
					studentList.add(new Student(rs.getInt("matrikelnr_id"), rs.getString("vorname"),
							rs.getString("nachname"), rs.getString("titel"), rs.getString("firma")));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return studentList;
	}

	/**
	 * returns name of the company where the student does his thesis
	 * 
	 * @return
	 */
	public static String getFirma(int username) {
		String firma = null;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			// execute sql stmt here
			// SELECT firma FROM `bachelorarbeit` WHERE fk_student_matrikelnr_id = 853276
			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT firma FROM bachelorarbeit WHERE fk_student_matrikelnr_id = " + username);

				while (rs.next()) {
					firma = rs.getString("firma");
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return firma;
	}

	/**
	 * returns list of students from the current betreuer
	 * 
	 * @param pnr id of the current betreuer
	 * @return
	 */
	public static ArrayList<Student> getStudentsofBe(int pnr) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				// execute sql stmt here
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT matrikelnr_id, vorname, nachname, titel, firma "
						+ "FROM bachelorarbeit LEFT JOIN student ON bachelorarbeit.fk_student_matrikelnr_id = student.matrikelnr_id "
						+ "WHERE fk_hft_angestellte_angestellte_id = " + pnr
						+ " AND bdekan IS NOT NULL AND bbetreuer IS NOT NULL");

				while (rs.next()) {
					studentList.add(new Student(rs.getInt("matrikelnr_id"), rs.getString("vorname"),
							rs.getString("nachname"), rs.getString("titel"), rs.getString("firma")));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return studentList;
	}

	/**
	 * returns list of students with topic and betreuer
	 * 
	 * @return
	 */
	public static ArrayList<Student> getStudentWithTopAndBe() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				// execute sql stmt here
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT matrikelnr_id, vorname, nachname, titel, firma "
						+ "FROM bachelorarbeit LEFT JOIN student ON bachelorarbeit.fk_student_matrikelnr_id = student.matrikelnr_id "
						+ "WHERE bdekan IS NOT NULL AND bbetreuer IS NOT NULL");

				while (rs.next()) {
					studentList.add(new Student(rs.getInt("matrikelnr_id"), rs.getString("vorname"),
							rs.getString("nachname"), rs.getString("titel"), rs.getString("firma")));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return studentList;
	}

	/**
	 * returns a String of a date when the thesis begin
	 * 
	 * @param username matrikelnummer from current student
	 * @return
	 */
	public static String getDateFrom(int username) {
		String date = null;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			// execute sql stmt here

			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT anfang FROM bachelorarbeit WHERE fk_student_matrikelnr_id = " + username);

				while (rs.next()) {
					date = rs.getString("anfang");
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return date;
	}

	/**
	 * returns a String of a date when the thesis must be done
	 * 
	 * @param username matrikelnummer from current student
	 * @return
	 */
	public static String getDateUntil(int username) {
		String date = null;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			// execute sql stmt here

			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT ende FROM bachelorarbeit WHERE fk_student_matrikelnr_id = " + username);

				while (rs.next()) {
					date = rs.getString("ende");
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return date;
	}

	/**
	 * returns mark of presentation
	 * 
	 * @param username matrikelnummer from current student
	 * @return
	 */
	public static double getNoteVortrag(int username) {
		double note = 0;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			// execute sql stmt here

			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT vortrag FROM bachelorarbeit WHERE fk_student_matrikelnr_id = " + username);

				while (rs.next()) {
					note = Double.parseDouble(rs.getString("vortrag"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return note;
	}

	/**
	 * returns mark of thesis
	 * 
	 * @param username
	 * @return
	 */
	public static double getNoteArbeit(int username) {
		double note = 0.0;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			// execute sql stmt here

			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT arbeit FROM bachelorarbeit WHERE fk_student_matrikelnr_id = " + username);

				while (rs.next()) {
					note = Double.parseDouble(rs.getString("arbeit"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return note;
	}

	/**
	 * return overall mark of current student
	 * 
	 * @param username
	 * @return
	 */
	public static double getNoteGesamt(int username) {
		double note = 0.0;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			// execute sql stmt here

			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("SELECT gnote FROM bachelorarbeit WHERE fk_student_matrikelnr_id = " + username);

				while (rs.next()) {
					note = Double.parseDouble(rs.getString("gnote"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return note;
	}

	/**
	 * returns object of the betreuer from current student
	 * 
	 * @param mnr
	 * @return
	 */
	public static Dozent getBetreuerofStudent(int mnr) {
		Dozent betreuer = null;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {

				// Betreuer betreuer
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT hft_angestellte_id, vorname, nachname, passwort, studiendekan "
						+ "FROM bachelorarbeit LEFT JOIN hft_angestellte ON bachelorarbeit.fk_hft_angestellte_angestellte_id = hft_angestellte.hft_angestellte_id "
						+ "WHERE fk_student_matrikelnr_id = " + mnr);

				while (rs.next()) {
					betreuer = new Dozent(rs.getInt("hft_angestellte_id"), rs.getString("vorname"),
							rs.getString("nachname"), rs.getString("passwort"), rs.getInt("studiendekan"));
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return betreuer;
	}

	/**
	 * returns object of an employee of the fakultaetsarchiv
	 * 
	 * @param id
	 * @return
	 */
	public static FarchivMitarbeiter getFarchivEmployee(int id) {
		FarchivMitarbeiter employee = null;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM fakultaetsarchiv WHERE farchiv_id=" + id);

				while (rs.next()) {
					employee = new FarchivMitarbeiter(rs.getString("vorname"), rs.getString("nachname"),
							rs.getString("passwort"), id);
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return employee;
	}

	/**
	 * returns an arraylist of students with a betreuer and topic but not confirmed
	 * arrival of their thesis at the fakultaetsarchiv
	 * 
	 * @return
	 */
	public static ArrayList<Student> getStudentWithTopAndBeNoFa() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			try {
				// execute sql stmt here
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT matrikelnr_id, vorname, nachname, titel, firma "
						+ "FROM bachelorarbeit LEFT JOIN student ON bachelorarbeit.fk_student_matrikelnr_id = student.matrikelnr_id "
						+ "WHERE bdekan IS NOT NULL AND bbetreuer IS NOT NULL AND bfarchiv IS NULL");

				while (rs.next()) {
					studentList.add(new Student(rs.getInt("matrikelnr_id"), rs.getString("vorname"),
							rs.getString("nachname"), rs.getString("titel"), rs.getString("firma")));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return studentList;
	}

	/**
	 * returns the firmenbetreuer of the current student
	 * 
	 * @param mnr
	 * @return
	 */
	public static String getFirmenBetreuer(int mnr) {
		String name = null;
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "Please connect to database");
		} else {
			// execute sql stmt here
			try {
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT firmenbetreuer FROM bachelorarbeit WHERE fk_student_matrikelnr_id = " + mnr);

				while (rs.next()) {
					name = rs.getString("firmenbetreuer");
				}
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return name;
	}
}
