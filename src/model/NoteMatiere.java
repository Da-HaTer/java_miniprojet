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


	public double moyenne() {
		return (note.getExam() * matiere.getCoefExam() + note.getTp() * matiere.getCoefTp()
				+ note.getDs() * matiere.getCoefds());
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
