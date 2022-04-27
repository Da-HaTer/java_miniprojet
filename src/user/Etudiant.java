package user;

import java.sql.*;
import java.util.ArrayList;
import model.Matiere;
import model.NoteMatiere;

public class Etudiant extends Utilisateur {
	private int id;
	private String cin;
	private String nom;
	private String prenom;
	private ArrayList<NoteMatiere> notesS1 = new ArrayList<>();
	private ArrayList<NoteMatiere> notesS2 = new ArrayList<>();

	public Etudiant(int id, String cin, String nom, String prenom, ArrayList<NoteMatiere> notesS1,
			ArrayList<NoteMatiere> notesS2) { //w/o login & passwd  (for superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.notesS1 = notesS1;
		this.notesS2 = notesS2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Etudiant(int id, String cin, String nom, String prenom) { //only id cin name (superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Etudiant(int id, String cin, String nom, String prenom, String login, String pwd) { // w/o notes (user)
		super(login, pwd);
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public ArrayList<NoteMatiere> getNotesS1() {
		return notesS1;
	}

	public void setNotesS1(ArrayList<NoteMatiere> notesS1) {
		this.notesS1 = notesS1;
	}

	public ArrayList<NoteMatiere> getNotesS2() {
		return notesS2;
	}

	public void setNotesS2(ArrayList<NoteMatiere> notesS2) {
		this.notesS2 = notesS2;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", cin=" + cin + ", nom=" + nom +", prenom=" + prenom +", notesS1=" + notesS1.toString()
				+ ", notesS2=" + notesS2.toString() + "]";
	}

	private double moyenneSemestre(ArrayList<NoteMatiere> notesSemestre) {
		double sum = 0.0;
		double totalCoef = 0.0;
		for (NoteMatiere notes : notesSemestre) {
			sum += notes.moyenne() * notes.getMatiere().getCoefMatiere();
			totalCoef += notes.getMatiere().getCoefMatiere();
		}
		return Math.round(sum / totalCoef);

	}

	public double moyenneS1() {
		return moyenneSemestre(notesS1);
	}

	public double moyenneS2() {
		return moyenneSemestre(notesS2);
	}

	public double moyenne() {
		return (moyenneS1() + moyenneS2()) / 2;
	}

	public void ajouterNote(NoteMatiere item) { //for enseignant 
		notesS1.add(item);
	}

	// initialiser les matières
	public void setListeMatiereS1(ArrayList<Matiere> listeMatiere) { //set matieres only without notes
		if (notesS1.isEmpty()) {
			for (Matiere m : listeMatiere) {
				notesS1.add(new NoteMatiere(m));
			}
		} else {
			// TODO: make sure if you will add the list or to show an alert
			System.err.println("should be implemented add to list noteMatiere");
		}

	}

	public void setListeMatiereS2(ArrayList<Matiere> listeMatiere) { //same bro
		if (notesS2.isEmpty()) {
			for (Matiere m : listeMatiere) {
				notesS2.add(new NoteMatiere(m));
			}
		} else {
			// TODO: make sure if you will add the list or to show an alert
			System.err.println("should be implemented add to list noteMatiere");
		}

	}

	public static Etudiant getEtudiantFromDB(int id) {
			try {
				String query = "SELECT * FROM Etudiant WHERE idEtudiant=? ";
//				Connection connection = new DBUtils().getConnection();
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
				PreparedStatement preparedStmt = connection.prepareStatement(query);
				preparedStmt.setInt(1, id);
				ResultSet resultSet = preparedStmt.executeQuery();
				Etudiant etudiant = null;
				while (resultSet.next()) {
				// Etudiant(int id, String cin, String name, String lastName)
				etudiant = new Etudiant(resultSet.getInt(1),
						resultSet.getString(4),
						resultSet.getString(2),
						resultSet.getString(3));
				}
				connection.close();
				return etudiant;
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	public void getListMatieresDB(int idUser) {///todo : complete this (should be similar to class (different query) 
		// TODO Auto-generated method stub
		
	}

}
