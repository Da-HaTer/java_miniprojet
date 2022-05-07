package model;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import lib.MYSQL_Connection;
import user.Enseignant;
import user.Etudiant;

public class Classe { //like struct
	

	private int idClasse;
	String name;
	Integer sem1;
	Integer sem2;
	public ArrayList<Matiere> listeMatiereS1 = new ArrayList<>();
	public ArrayList<Matiere> listeMatiereS2 = new ArrayList<>();
	public ArrayList<Etudiant> listeEtudiant = new ArrayList<>();
	
	public Classe() {
		// TODO Auto-generated constructor stub
	}
	public Classe(Integer id) {
		this.idClasse=id;
		// TODO Auto-generated constructor stub
	}
	public Classe(int id ,String s,ArrayList<Matiere> s1, ArrayList<Matiere> s2, ArrayList<Etudiant> e){
		idClasse=id;
		name=s;
		listeMatiereS1=s1;
		listeMatiereS2=s2;
		listeEtudiant=e;
		// TODO Auto-generated constructor stub
	}
	
	public Classe(int id, String string,int sem1, int sem2) {
		// TODO Auto-generated constructor stub
		this.idClasse=id;
		this.name=string;
		this.sem1=sem1;
		this.sem2=sem2;
	}
	public Classe(String[] s) {
		if (s[0].length()!=0) this.idClasse=Integer.parseInt(s[0]);
		else this.idClasse=-1;
		this.name = s[1];
		if (s[2].length()!=0) this.sem1=Integer.parseInt(s[2]);
		else this.sem1=-1;
		if (s[3].length()!=0) this.sem2=Integer.parseInt(s[3]);
		else this.sem2=-1;
		// TODO Auto-generated constructor stub
	}
	
	public Integer getSem1() {
		return sem1;
	}
	public void setSem1(Integer sem1) {
		this.sem1 = sem1;
	}
	public Integer getSem2() {
		return sem2;
	}
	public void setSem2(Integer sem2) {
		this.sem2 = sem2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdClasse() {
		return idClasse;
	}
	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}
	
	public ArrayList<Matiere> getListMatieresDB(int sem) { ///returns matieres of this class for a given smester
		try {
			String query ="SELECT\n"
			+ "Matiere.idMatiere,Matiere.MatiereName,Matiere.coefDS,"
			+ "Matiere.coefExam,Matiere.coefTP,"
			+ "Matiere.coefMatiere\n"
			+ "FROM Matiere\n"
			+ "JOIN semestre\n"
			+ " ON Matiere.idSemestre = semestre.idsemestre\n"
			+ "JOIN Classe\n"
			+ String.format(" ON Classe.idS%d = semestre.idsemestre\n", sem)
			+ " where Classe.idClasse = ?;"; 
			
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, this.idClasse);
			ResultSet resultSet = preparedStmt.executeQuery();
			ArrayList<Matiere> matieres = new ArrayList<Matiere>();
			while (resultSet.next()) {
				Matiere matiere = new Matiere();
				matiere.setId(resultSet.getInt(1));
				matiere.setNomMatiere(resultSet.getString(2));
				matiere.setCoefds(resultSet.getDouble(3));
				matiere.setCoefExam(resultSet.getDouble(4));
				matiere.setCoefTp(resultSet.getDouble(5));
				matiere.setCoefMatiere(resultSet.getDouble(6));
				matieres.add(matiere);
//				System.out.println(matiere.toString());
			}
			connection.close();
			return matieres;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void delete_Classe(int id) {
    	try {
    	String query="delete from classe where idClasse=?;";
    	java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        int rowsaffected = preparedStmt.executeUpdate();
        System.out.println(rowsaffected);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}

	
    public void save_Classe() { //save or udpate
    	if (new Semestre().fetch_semestre(sem1)==null) { //avoid foreign key error create if doesn't exist
    		new Semestre(sem1,"semestre 1").save_Semestre();
    	}
    	if (new Semestre().fetch_semestre(sem2)==null) {
    		new Semestre(sem2,"semestre 2").save_Semestre();
    	}
        try{
        	String query=String.format("update classe set idClasse=?,nomClasse=?,idS1=?,idS2=?\r\n"
        			+ "where idClasse=%d;",this.idClasse);
	    	if (fetch_Classe(idClasse)==null) {
	    		query = "insert into classe values (?,?,?,?);"; // WHERE Login=? and Pwd=?";
	    	}
            java.sql.Connection connection=MYSQL_Connection.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            if (idClasse<=0) preparedStmt.setNull(1, idClasse);
            else preparedStmt.setInt(1, idClasse);
            preparedStmt.setString(2, name);
            
            if (sem1<=0) preparedStmt.setNull(3, sem1);
            else preparedStmt.setInt(3, sem1);
            
            if (sem2<=0) preparedStmt.setNull(4, sem2);
            else preparedStmt.setInt(4, sem2);
            int rowsaffected = preparedStmt.executeUpdate();
            System.out.println(rowsaffected);

            connection.close();
        }
        
        catch (SQLException e) {e.printStackTrace();}
	}
    
    public Classe fetch_Classe(int id){
    	try {
    	
    	String query="select * from classe where idClasse=?;";
    	java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet r = preparedStmt.executeQuery();
        Classe classe=null;
        while(r.next()) {
        	classe=new Classe(r.getInt(1),r.getString(2),r.getInt(3),r.getInt(4));
        }
        connection.close();
    	return classe;
    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
    	return null;
    }
	
    public ArrayList<Classe> getListClasse() { ///returns matieres of this class for a given smester
		try {
			String query ="select * from classe";
			
			java.sql.Connection connection=MYSQL_Connection.getconnection();
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			ResultSet resultSet = preparedStmt.executeQuery();
			ArrayList<Classe> classes = new ArrayList<Classe>();
			ResultSet r=resultSet;
			while (r.next()) {
//				int id,String login, String pwd,int ide, String cin, String nom, String prenom,id
				classes.add(new Classe(resultSet.getInt(1),
						resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4)));
			}
			connection.close();
			return classes;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    @Override
    	public String toString() {
    		// TODO Auto-generated method stub
    		return idClasse+","+name+","+sem1+","+sem2;
    	}
	public static void main(String[] args) {

//		Classe mi2 = new Classe("mi2");
//		mi2.setIdClasse(1);
//		mi2.getListMatieresDB(1);

	}
	public ArrayList<Etudiant> getListeEtudiants() {
		try {
			String query ="select * from etudiant where idClasse=?";
			
			java.sql.Connection connection=MYSQL_Connection.getconnection();
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, idClasse);
			ResultSet resultSet = preparedStmt.executeQuery();
			ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
			ResultSet r=resultSet;
			while (r.next()) {
//				int id,String login, String pwd,int ide, String cin, String nom, String prenom,id
				etudiants.add(new Etudiant(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getInt(5)));
			}
			connection.close();
			return etudiants;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
