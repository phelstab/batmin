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
public class Dozent extends Person{
	
	private int sdekan;
	private int pnr;
	
	/**
	 * 
	 * @param pnr
	 * @param vorname
	 * @param nachname
	 * @param password
	 * @param sdekan
	 */
	public Dozent(int pnr, String vorname, String nachname, String password, int sdekan) {
		// TODO Auto-generated constructor stub
		super(vorname, nachname, password);
		this.pnr = pnr;
		this.sdekan = sdekan;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPnr() {
		return pnr;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSdekan() {
		return sdekan;
	}

}
