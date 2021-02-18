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
public class FarchivMitarbeiter extends Person{
	int id;
	public FarchivMitarbeiter(String vorname, String nachname, String password, int id) {
		super(vorname, nachname, password);
		this.id = id;
	}

}
