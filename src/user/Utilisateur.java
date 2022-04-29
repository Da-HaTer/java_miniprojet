package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.*;

public class Utilisateur {
	public int idUser;
	private String login;
	private String motDePasse;	
	public int idref;
	private int type;
	
	public Utilisateur(String login, String motDePasse) {
		this.login = login;
		this.motDePasse = cryptPass(motDePasse);
	}
	public Utilisateur(int id,String login, String motDePasse, int idref, int type) {
		this.idUser=id;
		this.idref=idref;
		this.type=type;
		this.login = login;
		this.motDePasse = cryptPass(motDePasse);
	}
	
	public int getIdUser() {
		return idUser;
	}

	public int getIdref() {
		return idref;
	}

	public void setIdref(int idref) {
		this.idref = idref;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public Utilisateur() {
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}
	private void setType(int t) {
		// TODO Auto-generated method stub
		type=t;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
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
	
    public Utilisateur getUserFromDB() { ///todo: create instances of user
        try{ 
            String query = "SELECT * FROM User WHERE Login=? and Pwd=?";
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
    			preparedStmt.setString(1, login);
    			preparedStmt.setString(2, motDePasse);
            ResultSet resultSet = preparedStmt.executeQuery();
            Utilisateur utilisateur = null;
            while (resultSet.next()) {
    				utilisateur = new Utilisateur();
    				utilisateur.setIdUser(Integer.parseInt(resultSet.getString(1)));
    				utilisateur.setLogin(resultSet.getString(2));
    				utilisateur.setIdref(Integer.parseInt(resultSet.getString(4)));
    				utilisateur.setMotDePasse(resultSet.getString(3));
    				utilisateur.setType(Integer.parseInt(resultSet.getString(5)));
//                System.out.println(resultSet.getString(2));
//                System.out.println(resultSet.getString(3));
            }
            connection.close();
            return utilisateur;
        }
        
        catch (SQLException e) {e.printStackTrace();}
        return null;
    }
    
	public Boolean seConnecter(String login, String motDePasse) {
		return (login.equals(this.login) && cryptPass(motDePasse).equals(this.motDePasse));
	}

	public static void main(String[] args) {
		Utilisateur user = new Utilisateur("ahmed", "ahmed");
		System.out.println(user.toString());
//		System.out.println(user.getMotDePasse());
//		if(user.getMotDePasse().equals(cryptPass("test:)"))) {
//			System.out.println("ok");
//		}
//		if (user.getUserFromDB()==null) {
//			System.out.println("error");
//		}
//		System.out.println(user.getMotDePasse());
	}


}
