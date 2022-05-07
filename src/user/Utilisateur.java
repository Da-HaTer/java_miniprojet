package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.*;

import lib.MYSQL_Connection;


public class Utilisateur {
	public int idUser;
	private String login;
	private String motDePasse;	
	public int idref; //remove this ?
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
	public Utilisateur() {}
	
	public Utilisateur(String id, String login, String pwd, String idref, int i) {
		// TODO Auto-generated constructor stub
		if (id.length()!=0) idUser=Integer.parseInt(id);
		else this.idUser=-1;
		this.login=login;
		this.motDePasse=pwd;
	}
	public Utilisateur(String[] s) {
		// TODO Auto-generated constructor stub
		if (s[0].length()!=0) this.idUser=Integer.parseInt(s[0]);
		else this.idUser=-1;
		
		this.login=s[1];
		if (pwd_in_db(s[2])) this.motDePasse=s[2]; //encrypt if not already encrypted 
		else this.motDePasse=cryptPass(s[2]);
		
		if (s[3].length()!=0) this.idref=Integer.parseInt(s[3]);
		else this.idref=-1;

		if (s[4].length()!=0) {
			this.type=Integer.parseInt(s[4]); 
			if (this.type <0 && this.type > 3) this.type=0;//not set
		} 
		else this.type=0;
		
		
		
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

	private void copy_MotDePasse(String motDePasse) { //unencrypted
		this.motDePasse = motDePasse;
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
	
	private boolean pwd_in_db(String pwd) {
	       try{ 
	            String query = "select * from user where Pwd=?;"; // WHERE Login=? and Pwd=?";
	            java.sql.Connection connection=MYSQL_Connection.getconnection();
	            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	            
	            preparedStmt.setString(1, pwd);
	            
	            ResultSet resultSet = preparedStmt.executeQuery();
	            while (resultSet.next()) {
	    			return true;
	            }
	            connection.close();
	            return false;
	        }
	        catch (SQLException e) {e.printStackTrace();}
	        return false;
	}
	
	
	public ArrayList<Utilisateur> getListeUtilisateur(){
        try{ 
            String query = "SELECT * FROM User";
            java.sql.Connection connection=MYSQL_Connection.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
            while (resultSet.next()) {
            		Utilisateur utilisateur = new Utilisateur();
    				utilisateur.setIdUser(resultSet.getInt(1));
    				utilisateur.setLogin(resultSet.getString(2));
    				utilisateur.copy_MotDePasse(resultSet.getString(3));
    				utilisateur.setIdref(resultSet.getInt(4));
    				utilisateur.setType(resultSet.getInt(5));
    				utilisateurs.add(utilisateur);
            }
            connection.close();
            return utilisateurs;
        }
        
        catch (SQLException e) {e.printStackTrace();}
        return null;
	}
	
    public Utilisateur getUserFromDB() { //fetch user
        try{ 
            String query = "SELECT * FROM User WHERE Login=? and Pwd=?";
            java.sql.Connection connection=MYSQL_Connection.getconnection();
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
    				utilisateur.copy_MotDePasse(resultSet.getString(3));
    				utilisateur.setType(Integer.parseInt(resultSet.getString(5)));
            }
            connection.close();
            return utilisateur;
        }
        
        catch (SQLException e) {e.printStackTrace();}
        return null;
    }
	public void delete_Utilisateur(int id,boolean previlege) {
		if (fetch_Utilisateur(id).type==3 && !previlege) JOptionPane.showMessageDialog(null, String.format("You don't have the previlege for this action"));
		else {
			try {
		    	String query="delete from user where idUser=?;";
		    	java.sql.Connection connection=MYSQL_Connection.getconnection();
		        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
		        preparedStmt.setInt(1, id);
		        int rowsaffected = preparedStmt.executeUpdate();
		        System.out.println(rowsaffected);
		        connection.close();
			}
    	catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	
    public void save_Utilisateur(boolean previlege) { //save or udpate
    	if (type==3 && !previlege) JOptionPane.showMessageDialog(null, String.format("You don't have the previlege for this action"));
    	else{ 
    		try{
	        	String query=String.format("update user set idUser=?,login=?, Pwd=?, idRef=?, userType=? \r\n"
	        			+ "where idUser=%d;",this.idUser);
		    	if (fetch_Utilisateur(idUser)==null) query = "insert into user values (?,?,?,?,?);";
		    	
	            java.sql.Connection connection=MYSQL_Connection.getconnection();
	            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	            
	            if (idUser<=0) preparedStmt.setNull(1,idUser);
	            else preparedStmt.setInt(1, idUser);
	            
	            preparedStmt.setString(2, login);
	            preparedStmt.setString(3, motDePasse);
	            
	            if (idref<=0) preparedStmt.setNull(4,idref);
	            else preparedStmt.setInt(4, idref);
	            
	            if (type<=0) preparedStmt.setNull(5,type);
	            else preparedStmt.setInt(5, type);
	
	            int rowsaffected = preparedStmt.executeUpdate();
	            System.out.println(rowsaffected);
	
	            connection.close();
	        }
	        
	        catch (SQLException e) {e.printStackTrace();}
		}
    }
	
    public static Utilisateur fetch_Utilisateur(int id) { ///change type to admin
        try{ 
            String query = "select * from user where idUser=?;"; // WHERE Login=? and Pwd=?";
            java.sql.Connection connection=MYSQL_Connection.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            
            preparedStmt.setInt(1, id);
            
            ResultSet resultSet = preparedStmt.executeQuery();
            Utilisateur utilisateur = null;
            while (resultSet.next()) {
    				utilisateur = new Utilisateur();
    				utilisateur.setIdUser(resultSet.getInt(1));
    				utilisateur.setLogin(resultSet.getString(2));
    				utilisateur.setIdref(resultSet.getInt(4));
    				utilisateur.copy_MotDePasse(resultSet.getString(3));
    				utilisateur.setType(resultSet.getInt(5));
            }
            connection.close();
            return utilisateur;
        }
        catch (SQLException e) {e.printStackTrace();}
        return null;
    }
    
    @Override
    public String toString() {
    	return String.format("%d,%s,%s,%d,%d",idUser,login,motDePasse,idref,type);
    }
    public String toString_verbose() {
    	return String.format("userid : %d login: %s passwd: %s\n",this.idUser,login,this.motDePasse);
    }
//	public Boolean seConnecter(String login, String motDePasse) { (deprecated) 
//		return (login.equals(this.login) && cryptPass(motDePasse).equals(this.motDePasse));
//	}

	public static void main(String[] args) {
		Utilisateur user = new Utilisateur();
		user.setMotDePasse("toor");
		System.out.println(user.getMotDePasse());
//		int r=user.fetch_user(user.getLogin());
//		System.out.println(r);
//		user.save_to_DB();
//		user.DBdelete_by_id(4);
//		user.DBdelete_by_login("admin");
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
