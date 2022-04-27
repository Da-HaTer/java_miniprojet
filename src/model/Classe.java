package model;

import java.sql.*;
import java.util.ArrayList;

import user.Etudiant;

public class Classe { //like struct
	private int idClasse;
	public int getIdClasse() {
		return idClasse;
	}
	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	String name;
	public ArrayList<Matiere> listeMatiereS1 = new ArrayList<>();
	public ArrayList<Matiere> listeMatiereS2 = new ArrayList<>();
	public ArrayList<Etudiant> listeEtudiant = new ArrayList<>();
	
	public Classe(String s) {
		name=s;
		// TODO Auto-generated constructor stub
	}
	public Classe(String s,ArrayList<Matiere> s1, ArrayList<Matiere> s2, ArrayList<Etudiant> e){
		name=s;
		listeMatiereS1=s1;
		listeMatiereS2=s2;
		listeEtudiant=e;
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Matiere> getListMatieresDB(int sem) {
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
			
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
			PreparedStatement preparedStmt = connection.prepareStatement(query);
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
	
	public boolean student_in_class(Etudiant e){ //check if a student is in a class
		 for (Etudiant i: listeEtudiant) if (i.getId()==e.getId()) return true;
		 return false;	 	
	}

	public static void main(String[] args) {

		Classe mi2 = new Classe("mi2");
		mi2.setIdClasse(1);
		mi2.getListMatieresDB(2);
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

}
