package gestion;

import java.util.ArrayList;
import espace_etudiant.Utilisateur;
import model.Matiere;
import model.NoteMatiere;

public class Etudiant extends Utilisateur {
	private String id;
	private String cin;
	private String name;
	private ArrayList<NoteMatiere> notesS1 = new ArrayList<>();
	private ArrayList<NoteMatiere> notesS2 = new ArrayList<>();

	public Etudiant(String id, String cin, String name, ArrayList<NoteMatiere> notesS1,
			ArrayList<NoteMatiere> notesS2) { //w/o login & passwd  (for superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.name = name;
		this.notesS1 = notesS1;
		this.notesS2 = notesS2;
	}

	public Etudiant(String id, String cin, String name) { //only id cin name (superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.name = name;
	}

	public Etudiant(String id, String cin, String name, String login, String pwd) { // w/o notes (user)
		super(login, pwd);
		this.id = id;
		this.cin = cin;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<NoteMatiere> getNotesS1() {
		return notesS1;
	}

	protected void setNotesS1(ArrayList<NoteMatiere> notesS1) {
		this.notesS1 = notesS1;
	}

	public ArrayList<NoteMatiere> getNotesS2() {
		return notesS2;
	}

	protected void setNotesS2(ArrayList<NoteMatiere> notesS2) {
		this.notesS2 = notesS2;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", cin=" + cin + ", name=" + name + ", notesS1=" + notesS1.toString()
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

}
