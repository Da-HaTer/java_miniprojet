package espace_etudiant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilisateur {
	private String login;
	private String motDePasse;
	
	public Utilisateur() {
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = cryptPass(motDePasse);
	}

	public Utilisateur(String login, String motDePasse) {
		this.login = login;
		this.motDePasse = cryptPass(motDePasse);
	}

	private static String cryptPass(String motDePasse) {
		String Crypted = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(motDePasse.getBytes());

			byte byteData[] = md.digest();

			// convertir le tableau de bits en format hexadécimal
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			Crypted = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return Crypted;

	}

	public static void main(String[] args) {
		Utilisateur user = new Utilisateur("test", "test:)");
		System.out.println(user.getMotDePasse());
		if(user.getMotDePasse().equals(cryptPass("test:)"))) {
			System.out.println("ok");
		}
	}
}
