package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class Matiere {

	private int id;
	private String nomMatiere;
	private double coefExam;
	private double coefDs;
	private double coefTp;
	private double coefMatiere;
	private Integer idSemestre;
	private Integer idEnseignant;
//	public Matiere() {}
	public Matiere(int id, String nomMatiere, double coefds,double coefExam, double coefTp, double coefMatiere, Integer ids, Integer ide) {
		this.id=id;
		this.coefExam = coefExam;
		this.coefDs = coefds;
		this.coefTp = coefTp;
		this.coefMatiere = coefMatiere;
		this.nomMatiere = nomMatiere;
		this.idSemestre=ids;
		this.idEnseignant=ide;
	}

	public Matiere() {
		// TODO Auto-generated constructor stub
	}

	public double getCoefExam() {
		return coefExam;
	}

	public void setCoefExam(double coefExam) {
		this.coefExam = coefExam;
	}

	public double getCoefds() {
		return coefDs;
	}

	public void setCoefds(double coefds) {
		this.coefDs = coefds;
	}

	public double getCoefTp() {
		return coefTp;
	}

	public void setCoefTp(double coefTp) {
		this.coefTp = coefTp;
	}

	public double getCoefMatiere() {
		return coefMatiere;
	}

	public void setCoefMatiere(double coefMatiere) {
		this.coefMatiere = coefMatiere;
	}

	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Matiere [coefExam=" + coefExam + ", coefDs=" + coefDs + ", coefTp=" + coefTp + ", coefMatiere="
				+ coefMatiere + ", nomMatiere=" + nomMatiere + "]";
	}
	
	public void delete_matiere(int id) {
    	try {
    	String query="delete from notematiere where idMatiere=?;";
    	String query1="delete from matiere where idMatiere=?;";
    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        int rowsaffected = preparedStmt.executeUpdate();
        System.out.println(rowsaffected);
        
        PreparedStatement preparedStmt1 = (PreparedStatement) connection.prepareStatement(query1);
        preparedStmt1.setInt(1, id);
        int rowsaffected1 = preparedStmt1.executeUpdate();
        System.out.println(rowsaffected1);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
	
    public void save_matiere() { ///todo: remove manual id 
    	if (fetch_matiere(this.id)==null) {
	        try{ 
	            String query = "insert into matiere values (?,?,?,?,?,?,?,?);"; // WHERE Login=? and Pwd=?";
	            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
	            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	            
	            preparedStmt.setInt(1, id);
	            preparedStmt.setString(2, nomMatiere);
				preparedStmt.setDouble(3, coefDs);
				preparedStmt.setDouble(4, coefExam);
				preparedStmt.setDouble(5, coefTp);
				preparedStmt.setDouble(6, coefMatiere);
				if (idSemestre!=null) preparedStmt.setInt(7, idSemestre); //case of null added
				else preparedStmt.setNull(7,java.sql.Types.NULL);
				
				if (idEnseignant!=null) preparedStmt.setInt(8, idEnseignant);  //case of null added
				else preparedStmt.setNull(8, java.sql.Types.NULL);
				
	            int rowsaffected = preparedStmt.executeUpdate();
	            System.out.println(rowsaffected);
	
	            connection.close();
	        }
	        
	        catch (SQLException e) {e.printStackTrace();}
    	}
    }
    
    public Matiere fetch_matiere(int id){
    	try {
    	
    	String query="select * from matiere where idMatiere=?;";
    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet r = preparedStmt.executeQuery();
        Matiere matiere=null;
        while(r.next()) {
        	int idm=r.getInt(1);
        	String nomMatiere=r.getString(2);
        	double coefDs=r.getDouble(3);
        	double coefExam=r.getDouble(4);
        	double coefTp=r.getDouble(5);
        	double coefMatiere=r.getDouble(6);
        	int idSemestre=r.getInt(6);
        	int idEnseignant=r.getInt(6);
        	matiere=new Matiere(idm,nomMatiere,coefDs,coefExam,coefTp,coefMatiere,idSemestre,idEnseignant);
        }
        connection.close();
    	return matiere;
    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
    	return null;
    }
    
    public static void main(String[] args) {
		Matiere transmission=new Matiere(4, "transmission signal", 0.25, 0.5, 0.25, 1,1,1); 
//		transmission.save_matiere();
		transmission.delete_matiere(transmission.getId());
		
	}
}
