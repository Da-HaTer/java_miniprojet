package user;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class Enseignant extends Utilisateur{
	private int ide;
	private String prenom;
	private String nom;
	
	public String getName() {
		return nom;
	}
	public void setName(String name) {
		this.nom = name;
	}
	public String getCin() {
		return prenom;
	}
	public void setCin(String cin) {
		this.prenom = cin;
	}
	public int getId() {
		return ide;
	}
	public void setId(int id) {
		this.ide = id;
	}
	public Enseignant(String login, String passwd) {
		super(login,passwd);
		// TODO Auto-generated constructor stub
	}
	public Enseignant(int id, String prenom, String nom) { //only id cin name (superadmin)
		super();
		this.ide = id;
		this.prenom = prenom;
		this.nom = nom;
	}
	public Enseignant(int id,String login, String pwd, int ide, String prenom , String nom ) {
		super(id,login,pwd,ide,2);
		this.ide = ide;
		this.prenom = prenom;
		this.nom = nom;
		// TODO Auto-generated constructor stub
	}
	
	public void save_ens_db() {
		this.save_user_DB();
        int exist=fetch_enseignant(this.ide);
        if (exist==0) {
			try{ 
	            String query = "insert into enseignant values (?,?,?);"; // WHERE Login=? and Pwd=?";
	            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
	            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	            
	            preparedStmt.setInt(1, ide); preparedStmt.setString(2, nom); preparedStmt.setString(3, prenom);
				
	            int rowsaffected = preparedStmt.executeUpdate();
	            System.out.println(rowsaffected);
	
	            connection.close();
	        }
	        catch (SQLException e) {e.printStackTrace();}
        }
	}
	
	private void delete_db(int id) {
		// TODO Auto-generated method stub
		
        try{
        	String query1 = "select Login from user where idRef=? and userType=2;";
        	String query2 = "delete from enseignant where idEnseignant=?;"; // 
        	
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query1);
            preparedStmt.setInt(1, ide);
            ResultSet r = preparedStmt.executeQuery();
            while (r.next()) {
            	String id_user= r.getString(1);
            	DBdelete_by_login(id_user);
            }
        	
            PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query2);
            
            preparedStmt1.setInt(1, ide);
            int rowsaffected = preparedStmt1.executeUpdate();
            System.out.println(rowsaffected);
            
            connection.close();
        } 
        
        catch (SQLException e) {e.printStackTrace();}
	}
	
    public int fetch_enseignant(int id) {
        try{ 
            String query = "select count(*) from enseignant where idEnseignant=?;"; // WHERE Login=? and Pwd=?";
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            
            preparedStmt.setInt(1, id);
            
            int res=0;
            ResultSet r = preparedStmt.executeQuery();
            r.next();
            res= r.getInt(1);
            connection.close();
            return res;
        }
        catch (SQLException e) {e.printStackTrace();}
        return -1;
    }
	
	public static void main(String[] args) {
		Enseignant ens=new Enseignant(3,"khalil","raboudi",2,"khalil","gargouri");
//		ens.save_ens_db();
		ens.delete_db(2);
	}
	
}
