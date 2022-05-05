package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class Matiere {

	private Integer id;
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

	public Matiere(String[] s) {
		if (s[0].length()!=0) this.id=Integer.parseInt(s[0]);
		else this.id=-1;
		this.nomMatiere=s[1];
		this.coefDs = Double.parseDouble(s[2]);
		this.coefTp = Double.parseDouble(s[3]);
		this.coefExam = Double.parseDouble(s[4]);
		this.coefMatiere = Double.parseDouble(s[5]);
		if (s[6].length()!=0) this.idSemestre=Integer.parseInt(s[6]);
		else this.idSemestre=null;
		if (s[7].length()!=0) this.idEnseignant=Integer.parseInt(s[7]);
		else this.idEnseignant=null;
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdSemestre() {
		return idSemestre;
	}

	public void setIdSemestre(Integer idSemestre) {
		this.idSemestre = idSemestre;
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
	
	public Integer getIdEnseignant() {
		return idEnseignant;
	}

	public void setIdEnseignant(Integer idEnseignant) {
		this.idEnseignant = idEnseignant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return getId()+","+getNomMatiere()+","+getCoefds()+","+getCoefTp()+","+getCoefExam()+","+getCoefMatiere()+","+getIdSemestre()+","+getIdEnseignant();
	}
	public String toString_verbose() {
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
	
	
    public void save_matiere() { //save or udpate
    	
        try{
        	String query=String.format("update matiere set idMatiere=?,MatiereName=?,coefDS=?,coefExam=?,coefTP=?,CoefMatiere=?,idSemestre=?,idEnseignant=?\r\n"
        			+ "where idMatiere=%d;",this.id);
	    	if (fetch_matiere(this.id)==null) {
	    		query = "insert into matiere values (?,?,?,?,?,?,?,?);"; // WHERE Login=? and Pwd=?";
	    	}
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            if (id<=0) preparedStmt.setNull(1, id);
            else preparedStmt.setInt(1, id);
            preparedStmt.setString(2, nomMatiere);
			preparedStmt.setDouble(3, coefDs);
			preparedStmt.setDouble(4, coefExam);
			preparedStmt.setDouble(5, coefTp);
			preparedStmt.setDouble(6, coefMatiere);
			if (idSemestre!=null && idSemestre>0) preparedStmt.setInt(7, idSemestre); //case of null added
			else preparedStmt.setNull(7,java.sql.Types.NULL);
			
			if (idEnseignant!=null && idEnseignant>0) preparedStmt.setInt(8, idEnseignant);  //case of null added
			else preparedStmt.setNull(8, java.sql.Types.NULL);
			
            int rowsaffected = preparedStmt.executeUpdate();
            System.out.println(rowsaffected);
            System.out.println("saved matiere");

            connection.close();
        }
        
        catch (SQLException e) {e.printStackTrace();}
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
    
    public ArrayList<Matiere> getListMatieresDB() {
		try {
			String query ="select* from matiere";
			
			java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			ResultSet resultSet = preparedStmt.executeQuery();
			ArrayList<Matiere> matieres = new ArrayList<Matiere>();
			ResultSet r=resultSet;
			while (r.next()) {
				Matiere matiere = new Matiere();
		       	int idm=r.getInt(1);
	        	String nomMatiere=r.getString(2);
	        	double coefDs=r.getDouble(3);
	        	double coefExam=r.getDouble(4);
	        	double coefTp=r.getDouble(5);
	        	double coefMatiere=r.getDouble(6);
	        	int idSemestre=r.getInt(7);
	        	int idEnseignant=r.getInt(8);
	        	matiere=new Matiere(idm,nomMatiere,coefDs,coefExam,coefTp,coefMatiere,idSemestre,idEnseignant);
				matieres.add(matiere);
			}
			connection.close();
			return matieres;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    public boolean equals(Matiere mat) {
    	// TODO Auto-generated method stub
    	return (this.getId()==mat.getId());
    }
    public static void main(String[] args) {
//		Matiere transmission=new Matiere(4, "transmission signal", 0.25, 0.5, 0.25, 1,1,1); 
//		transmission.save_matiere();
//		transmission.delete_matiere(transmission.getId());
    	Matiere mat1=new Matiere(5, "transmission signal v2", 0.3, 0.7, 0, 1,null,1);
    	mat1.save_matiere();
//    	mat1.delete_matiere(5);
//    	Matiere mat2=new Matiere(4, "transmission signal", 0.25, 0.5, 0.25, 1,1,1);
//    	System.out.println(mat1.equals(mat2));
//    	ArrayList<Matiere> matieres=new Matiere().getListMatieresDB();
//    	for (Matiere mat:matieres) System.out.println(mat);
		
	}
}
