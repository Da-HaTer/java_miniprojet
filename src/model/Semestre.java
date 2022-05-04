package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class Semestre {
	private Integer idsem;
	private String name;
	
	
    public Semestre() {
	}
    
    public Semestre(Integer id, String name) {
		// TODO Auto-generated constructor stub
    	this.idsem=id;
    	this.name=name;
	}

	public void delete_Semestre(int id) {
    	try {
    	String query="delete from semestre where idsemestre=?;";
    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        int rowsaffected = preparedStmt.executeUpdate();
        System.out.println(rowsaffected);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
	
    public void save_Semestre() { //save or udpate
        try{
        	String query=String.format("update semestre set idsemestre=?,name=? \r\n"
        			+ "where idsemestre=%d;",this.idsem);
	    	if (fetch_semestre(idsem)==null) {
	    		
	    		query = "insert into semestre values (?,?);"; // WHERE Login=? and Pwd=?";
	    	}
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            if (idsem<=0) preparedStmt.setNull(1,idsem);
            else preparedStmt.setInt(1, idsem);
            preparedStmt.setString(2, name);
            int rowsaffected = preparedStmt.executeUpdate();
            System.out.println(rowsaffected);

            connection.close();
        }
        
        catch (SQLException e) {e.printStackTrace();}
	}
    
	public Semestre fetch_semestre(Integer id){
    	try {
	    	String query="select * from semestre where idsemestre=?;";
	    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
	        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	        preparedStmt.setInt(1, id);
	        ResultSet r = preparedStmt.executeQuery();
	        Semestre semestre=null;
	        while(r.next()) {
	        	semestre=new Semestre(r.getInt(1),r.getString(2));
	        }
	        connection.close();
	    	return semestre;
    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
    	return null;
    }
}
