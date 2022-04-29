package user;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

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
	
	public void save_admin_db() {
		this.save_user_DB();
        int exist=fetch_admin(this.idadmin);
        if (exist==0) {
			try{ 
	            String query = "insert into admin values (?,?);"; // WHERE Login=? and Pwd=?";
	            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
	            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	            
	            preparedStmt.setInt(1, idadmin);	preparedStmt.setInt(2, issuper);
				
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
        	String query1 = "select Login from user where idRef=? and userType=3;";
        	String query2 = "delete from admin where idAdmin=?;"; // 
        	
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query1);
            preparedStmt.setInt(1, id);
            ResultSet r = preparedStmt.executeQuery();
            while (r.next()) {
            	String id_user= r.getString(1);
            	DBdelete_by_login(id_user);
            }
        	
            PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query2);
            
            preparedStmt1.setInt(1, id);
            int rowsaffected = preparedStmt1.executeUpdate();
            System.out.println(rowsaffected);
            
            connection.close();
        } 
        
        catch (SQLException e) {e.printStackTrace();}
	}
	
    public int fetch_admin(int id) {
        try{ 
            String query = "select count(*) from admin where idAdmin=?;"; // WHERE Login=? and Pwd=?";
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
		Admin admin=new Admin(4, "admin2", "admin", 2, 0);
//		System.out.println(admin.fetch_admin(admin.idadmin));
//		admin.save_admin_db();
		admin.delete_db(1);
	}

}

