package user;

import java.sql.DriverManager;
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
	
	void sava_admin_db() {
		save_user_DB();
		
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
	public static void main(String[] args) {
		Admin admin=new Admin(3, "admin", "admin", 1, 0);
		admin.sava_admin_db();
	}
}

