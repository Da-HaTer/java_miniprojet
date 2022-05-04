package model;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import user.Enseignant;
import user.Etudiant;

public class Classe { //like struct
	

	private int idClasse;
	String name;
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
	
	public Classe(int int1, String string) {
		// TODO Auto-generated constructor stub
		this.idClasse=int1;
		this.name=string;
	}
	public Classe(String[] s) {
		if (s[0].length()!=0) this.idClasse=Integer.parseInt(s[0]);
		else this.idClasse=-1;
		this.name = s[1];
		// TODO Auto-generated constructor stub
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
				System.out.println(matiere.toString());
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
    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        int rowsaffected = preparedStmt.executeUpdate();
        System.out.println(rowsaffected);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
	
    public void save_Classe() { //save or udpate
    	
        try{
        	String query=String.format("update classe set idClasse=?,nomClasse=?\r\n"
        			+ "where idEnseignant=%d;",this.idClasse);
	    	if (fetch_Enseignant(idClasse)==null) {
	    		query = "insert into enseignant values (?,?,?);"; // WHERE Login=? and Pwd=?";
	    	}
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            if (idClasse<=0) preparedStmt.setNull(1, idClasse);
            else preparedStmt.setInt(1, idClasse);
            preparedStmt.setString(2, name);

            int rowsaffected = preparedStmt.executeUpdate();
            System.out.println(rowsaffected);

            connection.close();
        }
        
        catch (SQLException e) {e.printStackTrace();}
	}
    
    public Classe fetch_Enseignant(int id){
    	try {
    	
    	String query="select * from classe where idClasse=?;";
    	java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet r = preparedStmt.executeQuery();
        Classe classe=null;
        while(r.next()) {
        	classe=new Classe(r.getInt(1),r.getString(2));
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
			
			java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			ResultSet resultSet = preparedStmt.executeQuery();
			ArrayList<Classe> classes = new ArrayList<Classe>();
			ResultSet r=resultSet;
			while (r.next()) {
//				int id,String login, String pwd,int ide, String cin, String nom, String prenom,id
				classes.add(new Classe(resultSet.getInt(1),
						resultSet.getString(2)));
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
    		return idClasse+","+name;
    	}
	public static void main(String[] args) {

		Classe mi2 = new Classe("mi2");
		mi2.setIdClasse(1);
		mi2.getListMatieresDB(1);
//		for(Matiere m:matieres) {
//			System.out.println(m.getNomMatiere());
//		}
//		//mi2.listeMatiereS1.add(new Matiere(0.7, 0.3, 0, 2, "Algo"));
//		mi2.listeMatiereS1.add(new Matiere(0.7, 0.3, 0, 1, "math"));
//		mi2.listeMatiereS1.add(new Matiere(0.7, 0, 0.3, 1, "c++"));
//
//		mi2.listeMatiereS2.add(new Matiere(0.7, 0, 0.3, 1, "JAVA"));
//		mi2.listeMatiereS2.add(new Matiere(0.7, 0.3, 0, 1, "proba"));
//		//mi2.listeMatiereS2.add(new Matiere(0.7, 0, 0.3, 1, "Python"));
//		
//		mi2.listeEtudiant.add(new Etudiant("01-1", "00000000", "Ahmed"));
//		mi2.listeEtudiant.add(new Etudiant("01-2", "00000022", "Sami"));
//		//mi2.listeEtudiant.add(new Etudiant("01-3", "00000033", "Sana"));
//		//mi2.listeEtudiant.add(new Etudiant("01-4", "00000044", "Rim"));
//		
//		Calcule c = new Calcule(mi2);
//		//c.saisirLesNoteParEtudiant();
//		c.saisirLesNoteParMatiere();

	}
	public ArrayList<Etudiant> getListeEtudiants() {
		// TODO Auto-generated method stub
		return null;
	}

}
