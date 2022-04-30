package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class Note {
	private int id; ///should not be used manually ...
	private double exam;
	private double ds;
	private double tp;

	public Note(int id,double exam, double ds, double tp) {
		this.id=id;
		this.exam = exam;
		this.ds = ds;
		this.tp = tp;
	}
	public Note(double exam, double ds, double tp) {
		this.exam = exam;
		this.ds = ds;
		this.tp = tp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getExam() {
		return exam;
	}

	public void setExam(double exam) {
		this.exam = exam;
	}

	public double getDs() {
		return ds;
	}

	public void setDs(double ds) {
		this.ds = ds;
	}

	public double getTp() {
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
	}

	@Override
	public String toString() {
		return "Note [exam=" + exam + ", ds=" + ds + ", tp=" + tp + "]";
	}
	
	public void delete_note(int id) {
    	try {
    	String query="delete from notes where idNote=?;";
    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
        PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt1.setInt(1, id);
        int rowsaffected1 = preparedStmt1.executeUpdate();
        System.out.println(rowsaffected1);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
	
    public void save_note() { ///todo: remove manual id ( fetched by knowing etudiant id and matiere id
    	if (fetch_note(this.id)==null) {
	        try{ 
	            String query = "insert into notes values (?,?,?,?);"; // WHERE Login=? and Pwd=?";
	            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
	            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	            
	            preparedStmt.setInt(1, id);
	            preparedStmt.setDouble(2, exam);
	            preparedStmt.setDouble(3, ds);
	            preparedStmt.setDouble(4, tp);

	            int rowsaffected = preparedStmt.executeUpdate();
	            System.out.println(rowsaffected);
	
	            connection.close();
	        }
	        
	        catch (SQLException e) {e.printStackTrace();}
    	}
    }
    
    public Note fetch_note(int id){
    	try {
    	
    	String query="select * from notes where idNote=?;";
    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet r = preparedStmt.executeQuery();
        Note Note=null;
        while(r.next()) {
        	int idm=r.getInt(1);
        	double exam=r.getDouble(2);
        	double ds=r.getDouble(3);
        	double tp=r.getDouble(4);
        		
        	Note=new Note(idm,exam,ds,tp);
        }
        connection.close();
    	return Note;
    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
    	return null;
    }
    
    public static void main(String[] args) {
		Note note=new Note(5,10,11,15);
		note.delete_note(5);
	}	
}
