package de.hftstuttgart.gruppe5.data;
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
public class Person {
	private String vorname;
	private String nachname;
	private String password;

	/**
	 * 
	 * @param vorname
	 * @param nachname
	 */
	public Person(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}
	/**
	 * 
	 * @param vorname
	 * @param nachname
	 * @param password
	 */
	public Person(String vorname, String nachname, String password) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.password = password;
	}
	/**
	 * 
	 * @return
	 */
	public String getVorname() {
		return this.vorname;
	}
	/**
	 * 
	 * @return
	 */
	public String getNachname() {
		return this.nachname;
	}
	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}
}
