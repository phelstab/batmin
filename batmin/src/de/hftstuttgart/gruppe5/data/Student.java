package de.hftstuttgart.gruppe5.data;

/**
 * 
 * @author Lukas Ringle
 * @author Stefan Binninger
 * @author Sven Turowski
 * @author Paul Helstab
 * @author Lino Schmidt
 * @author Sebastian Mueller
 */
public class Student extends Person{
	private int matrikelnr;
	private String topic;
	private String firma;
	
	public Student(int mnr, String vorname, String nachname) {
		super(vorname,nachname);
		this.matrikelnr = mnr;


	}
	/**
	 * constructor of Student
	 * 
	 * @param mnr
	 * @param vorname
	 * @param nachname
	 * @param passowrd
	 */
	public Student(int mnr, String vorname, String nachname, String password) {
		super(vorname,nachname, password);
		this.matrikelnr = mnr;


	}
	public Student(int mnr, String vorname, String nachname, String topic, String firma) {
		super(vorname,nachname);
		this.matrikelnr = mnr;
		this.topic = topic;
		this.firma = firma;
	}
	/**
	 * overloaded constructor to create a student with a bachelor thesis topic
	 * @param mnr
	 * @param vorname
	 * @param nachname
	 * @param password
	 * @param topic
	 */
	public Student(int mnr, String vorname, String nachname, String password, String topic, String firma) {
		super(vorname,nachname, password);
		this.matrikelnr = mnr;
		this.topic = topic;
		this.firma = firma;
	}

	public int getMatrikelnummer() {
		return this.matrikelnr;
	}

	public String getThema() {
		return this.topic;
	}
	
	public String getFirma() {
		return this.firma;
	}
}
