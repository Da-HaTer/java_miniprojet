package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class NoteMatiere {
	private Matiere matiere;
	private Note note;

	public NoteMatiere(Matiere matiere, Note notes) {
		this.matiere = matiere;
		this.note = notes;
	}

	public NoteMatiere() {
	}


//	public NoteMatiere(Matiere m) {
//		// TODO Auto-generated constructor stub
//		this.matiere=m;
//	}

	public double moyenne() {
		Double exam,ds,tp; 
		if(note.getExam()==null)exam=(double) 0;
		else exam=note.getExam();
		if(note.getTp()==null)tp=(double) 0;
		else tp=note.getTp();
		if(note.getDs()==null)ds=(double) 0;
		else ds=note.getDs();
		return ( exam* matiere.getCoefExam() + tp * matiere.getCoefTp()
				+ ds * matiere.getCoefds());
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note notes) {
		this.note = notes;
	}
	
	public Note get_note(int idmat, int idetudiant) {//get notematiere where etudiant
    	try {
    	String query="select notes.*"
    			+ " from notematiere join notes where "
    			+ "notes.idNote=notematiere.idNote and "
    			+ "notematiere.idEtudiant=? and "
    			+ "notematiere.idMatiere=?;";
    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, idetudiant);
        preparedStmt.setInt(2, idmat);
        ResultSet r = preparedStmt.executeQuery();
        Note Note=null;
        while(r.next()) {
        	int idm=r.getInt(1);
        	Double exam=r.getDouble(2);
        	if(r.wasNull()) exam=null;
        	Double ds=r.getDouble(3);
        	if(r.wasNull()) ds=null;
        	Double tp=r.getDouble(4);
        	if(r.wasNull()) tp=null;
        		
        	Note=new Note(idm,exam,ds,tp);
        }
        connection.close();
    	return Note;
    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
    	return null;
	}
	
	public void init_notematiere(int ide,int idm){ //initialize matiere for a student if not already set
		if (get_note(idm,ide)==null) {
			try {
			    	String query1="insert into notes values()";
			    	String query2="SELECT * FROM notematiere where idnote_matiere=(select max(idnote_matiere) from notematiere);";
			    	String query3="insert into notematiere values(null,?,?,?)";
			    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
			        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query1);
		            preparedStmt.executeUpdate();
		            preparedStmt = (PreparedStatement) connection.prepareStatement(query2);
			        ResultSet r = preparedStmt.executeQuery();
			        int idn=-1;
			        while(r.next()) {
			        	idn=r.getInt(1);
			        }
			        preparedStmt = (PreparedStatement) connection.prepareStatement(query3);
			        preparedStmt.setInt(1, idn);
			        preparedStmt.setInt(2, idm);
			        preparedStmt.setInt(3, ide);
			        preparedStmt.executeUpdate();
			        connection.close();
		    	}
	    	
	    	catch (SQLException e) {e.printStackTrace();}
		}
	}
		
	
	@Override
	public String toString() {
		return "NoteMatiere [matiere=" + matiere.toString() + ", notes=" + note.toString() + "]";
	}
	
	
	public static void main(String[] args) {
		Matiere m = new Matiere(0.7, 0, 0.3, 1, "JAVA");
		Note n = new Note(14, 0, 17);
		NoteMatiere note = new NoteMatiere(m, n);
		double x = note.moyenne();
		System.out.println(x);
	}



}
