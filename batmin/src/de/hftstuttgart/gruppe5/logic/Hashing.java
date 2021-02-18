package de.hftstuttgart.gruppe5.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
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
public class Hashing {
	private final String algorithm = "MD5";
	
	/**
	 * This method is used to generate a hash code
	 * 
	 * @param toHash byte array of the String entered in the passwordField
	 * @return the hash code of the password entered in the passwordField
	 */
	public static String generateHash(byte[] toHash) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();

			byte[] thedigest = md.digest(toHash);
			String result = new String(Hex.encodeHex(thedigest));
			System.out.println(result);
			return result;
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
