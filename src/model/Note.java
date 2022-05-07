package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import lib.MYSQL_Connection;

public class Note {
	private int id; ///should not be used manually ...
	private Double exam;
	private Double ds;
	private Double tp;
	
	public Note() {	
		exam=null;
		ds=null;
		tp=null;
	}
	public Note(int id,Double exam, Double ds, Double tp) {
		this.id=id;
		this.exam = exam;
		this.ds = ds;
		this.tp = tp;
	}
	public Note(Double exam, Double ds, Double tp) {
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

	public Double getExam() {
		return exam;
	}

	public void setExam(double exam) {
		this.exam = exam;
	}

	public Double getDs() {
		return ds;
	}

	public void setDs(double ds) {
		this.ds = ds;
	}

	public Double getTp() {
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
	}
	
	public boolean isvalid() {
		boolean valid=true;
		Double[] notes={tp,exam,ds};
		for(Double note:notes) if (note>20 || note<0) valid=false;
		return valid;
	}

	@Override
	public String toString() {
		return "Note [exam=" + exam + ", ds=" + ds + ", tp=" + tp + "]";
	}
	
	public void delete_note(int id) {
    	try {
    	String query="delete from notes where idNote=?;";
    	java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt1.setInt(1, id);
        int rowsaffected1 = preparedStmt1.executeUpdate();
        System.out.println(rowsaffected1);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
	
    public void save_note(boolean previlege) { ///todo: remove manual id ( fetched by knowing etudiant id and matiere id
//    	Note note=fetch_note(id);
//    	if (note!=null) System.out.println(note.ds);
    	if (fetch_note(this.id).getExam()==null || previlege) {
	        try{ 
	            String query = "update notes set exam=?,ds=?,tp=? where idNote=?;"; // WHERE Login=? and Pwd=?";
	            java.sql.Connection connection=MYSQL_Connection.getconnection();
	            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	            
	            preparedStmt.setDouble(1, exam);
	            preparedStmt.setDouble(2, ds);
	            preparedStmt.setDouble(3, tp);
	            preparedStmt.setInt(4, id);
	            
	            int rowsaffected = preparedStmt.executeUpdate();
	            System.out.println("note saved"+" rows affected: "+rowsaffected);
	
	            connection.close();
	        }
	        
	        catch (SQLException e) {e.printStackTrace();}
    	}
    }
    
    public Note fetch_note(int id){
    	try {
    	
    	String query="select * from notes where idNote=?;";
    	java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet r = preparedStmt.executeQuery();
        Note Note=null;
        while(r.next()) {
        	int idm=r.getInt(1);
        	Double exam=r.getDouble(2); //check if null
        	if (r.wasNull()) exam=null;
        	Double ds=r.getDouble(3);
        	if (r.wasNull()) ds=null;
        	Double tp=r.getDouble(4);
        	if (r.wasNull()) tp=null;
        		
        	Note=new Note(idm,exam,ds,tp);
        }
        connection.close();
    	return Note;
    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
    	return null;
    }
    
    public static void main(String[] args) {
//		Note note=new Note(5,10,11,15);
//		note.delete_note(5);
    	Note note=new Note().fetch_note(8);
    	System.out.println(note.tp);
	}	
}
