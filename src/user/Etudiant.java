package user;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.Matiere;
import model.Note;
import model.NoteMatiere;

public class Etudiant extends Utilisateur {
	private int id;
	private String cin;
	private String nom;
	private String prenom;
	private int idClasse;
	private ArrayList<NoteMatiere> notesS1 = new ArrayList<>();
	private ArrayList<NoteMatiere> notesS2 = new ArrayList<>();

	public Etudiant(int id, String cin, String nom, String prenom, int idClasse, ArrayList<NoteMatiere> notesS1,
			ArrayList<NoteMatiere> notesS2) { //w/o login & passwd  (for superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.notesS1 = notesS1;
		this.notesS2 = notesS2;
	}
	
	public Etudiant(int id, String nom, String prenom, String cin, int idc) { //only id cin name (superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.idClasse = idc;
	}

	public Etudiant(int id, String cin, String nom, String prenom, String login, String pwd) { // w/o notes (user)
		super(login, pwd);
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
	}
	public Etudiant(int id) {
		this.id=id;
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
		return "Etudiant [id=" + id + ", cin=" + cin + ", nom=" + nom +", prenom=" + prenom +", idClasse="+ idClasse+", notesS1=" + notesS1.toString()
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
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setInt(1, id);
				ResultSet resultSet = preparedStmt.executeQuery();
				Etudiant etudiant = null;
				while (resultSet.next()) {
				// Etudiant(int id, String cin, String name, String lastName)
				etudiant = new Etudiant(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getInt(5));
				}
				connection.close();
				return etudiant;
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

	public ArrayList<Matiere> getListMatieresDB(int sem) {///todo : complete this (should be similar to class (different query) 
		// TODO Auto-generated method stub
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
	
	public int fetch_note(int idMatiere) {
		try {
			String query="select idNote from NoteMatiere\n"
					+ "where idMatiere=? and idEtudiant=?;";
			java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, idMatiere);
			preparedStmt.setInt(2, this.id);
			ResultSet resultSet = preparedStmt.executeQuery();
			int idnote=-1;
			while (resultSet.next()) idnote=resultSet.getInt(1);
			connection.close();
			return idnote;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void save_note(int idMatiere,Note note) {
		try {
			String query="select idNote from NoteMatiere\n"
					+ "where idMatiere=? and idEtudiant=?;";
			java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			
			preparedStmt.setInt(1, idMatiere);
			preparedStmt.setInt(1, this.id);
			ResultSet resultSet = preparedStmt.executeQuery();
			int idnote=-1;
			while (resultSet.next()) idnote=resultSet.getInt(1);
			if (idnote!=-1) { //update if already exists
				query = "update notes "
						+ "set exam=?, ds=?, tp=?"
						+ "where id=?;"; // WHERE Login=? and Pwd=?";
				
				preparedStmt.setInt(2, idnote);
				preparedStmt.setDouble(2, note.getExam());
	            preparedStmt.setDouble(3, note.getDs());
	            preparedStmt.setDouble(4, note.getTp());

	            int rowsaffected = preparedStmt.executeUpdate();
	            System.out.println(rowsaffected);
			}
			else {//insert note if doesn't already exist
				query = "insert into notes(exam,ds,tp) values (?,?,?);"; // WHERE Login=? and Pwd=?";
			 	preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			 	
	            preparedStmt.setDouble(1, note.getExam());
	            preparedStmt.setDouble(2, note.getDs());
	            preparedStmt.setDouble(3, note.getTp());

	            int rowsaffected = preparedStmt.executeUpdate();
	            System.out.println(rowsaffected);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Etudiant u=new Etudiant(1);
		u=u.getEtudiantFromDB(1);
		System.out.println(u.toString());
	}
}
