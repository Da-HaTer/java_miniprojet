package user;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import lib.MYSQL_Connection;
import model.Classe;

public class Admin extends Utilisateur{
	private int idadmin;
	private int issuper=0;
	
	public Admin(String login, String passwd) { ///to be removed
		super(login,passwd);
		// TODO Auto-generated constructor stub
	}
	public Admin(int id,String login, String motDePasse ,int ida, int issuper) { ///to be removed: setting id manually, instead delete where login= and then insert new
		super(id,login,motDePasse,ida,3);
		this.idadmin=ida;
		this.issuper=issuper;
		// TODO Auto-generated constructor stub
	}
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public Admin(Integer id, int issuper) {
		// TODO Auto-generated constructor stub
		this.idadmin=id;
		this.issuper=issuper;
	}
	
	public Admin(String[] s) {
		if (s[0].length()!=0) this.idadmin=Integer.parseInt(s[0]);
		else this.idadmin=-1;
		
		if (s[1].length()!=0) {
			this.issuper=Integer.parseInt(s[1]); 
			if (this.issuper!=1 && this.issuper!=0) this.issuper=0;
		} 
		else this.issuper=0;
	} 	
		// TODO Auto-generated constructor stub
	public int getIdadmin() {
		return idadmin;
	}
	public void setIdadmin(int idadmin) {
		this.idadmin = idadmin;
	}
	public int getIssuper() {
		return issuper;
	}
	public void setIssuper(int issuper) {
		this.issuper = issuper;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return idadmin+","+issuper;
	}
	
    public ArrayList<Admin> getListAdmin() { ///returns matieres of this class for a given smester
		try {
			String query ="select * from admin";
			
			java.sql.Connection connection=MYSQL_Connection.getconnection();
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			ResultSet resultSet = preparedStmt.executeQuery();
			ArrayList<Admin> admins = new ArrayList<Admin>();
			ResultSet r=resultSet;
			while (r.next()) {
//				int id,String login, String pwd,int ide, String cin, String nom, String prenom,id
				admins.add(new Admin(resultSet.getInt(1),
						resultSet.getInt(2)));
			}
			connection.close();
			return admins;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public void delete_Admin(int id) {
    	try {
    	String query="delete from admin where idAdmin=?;";
    	java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        int rowsaffected = preparedStmt.executeUpdate();
        System.out.println(rowsaffected);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
	
    public void save_Admin() { //save or udpate
        try{
        	String query=String.format("update admin set idAdmin=?,isSuper=? \r\n"
        			+ "where idAdmin=%d;",this.idadmin);
	    	if (fetch_Admin(idadmin)==null) {
	    		
	    		query = "insert into admin values (?,?);"; // WHERE Login=? and Pwd=?";
	    	}
            java.sql.Connection connection=MYSQL_Connection.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            if (idadmin<=0) preparedStmt.setNull(1,idadmin);
            else preparedStmt.setInt(1, idadmin);
            preparedStmt.setInt(2, issuper);
            int rowsaffected = preparedStmt.executeUpdate();
            System.out.println(rowsaffected);

            connection.close();
        }
        
        catch (SQLException e) {e.printStackTrace();}
	}
	
    public Admin fetch_Admin(int id) { ///change type to admin
        try{ 
            String query = "select * from admin where idAdmin=?;"; // WHERE Login=? and Pwd=?";
            java.sql.Connection connection=MYSQL_Connection.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            
            preparedStmt.setInt(1, id);
            
            Admin admin=new Admin();
            ResultSet r = preparedStmt.executeQuery();
            while(r.next()) {
            	admin.setIdadmin(id);
            	admin.setIssuper(r.getInt(2));
            	return admin;
            }
            connection.close();
            return null;
        }
        catch (SQLException e) {e.printStackTrace();}
        return null;
    }
	

	public static void main(String[] args) {
		Admin admin=new Admin(4, "admin2", "admin", 2, 0);
//		System.out.println(admin.fetch_admin(admin.idadmin));
//		admin.save_admin_db();
//		admin.delete_db(1);
	}

}

