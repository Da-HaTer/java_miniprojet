package model;

import java.util.ArrayList;
import java.util.Scanner;

import user.Etudiant;

public class Calcule {

	private Classe classe;

	public Calcule(Classe classe) {
		this.classe = classe;
	}

	int y = 0;

	/// deprecated
	public Note saisirUneNote(double coefExam, double coefds, double coefTp) { //params needed only to avoid zero coef marks
		Scanner scanner = new Scanner(System.in);
		double ds = 0.0;

		if (coefds > 0) {
			try {
				System.out.print("\tDonner un la note du ds: ");
				ds = scanner.nextDouble();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		double tp = 0.0;
		try {
			if (coefTp > 0) {
				System.out.print("\tDonner un la note du tp: ");
				tp = scanner.nextDouble();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		double exam = 0.0;
		try {
			if (coefExam > 0) {
				System.out.print("\tDonner un la note du exam: ");
				exam = scanner.nextDouble();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
		return new Note(exam, ds, tp);

	}

	private ArrayList<NoteMatiere> saisirNotesSemestre(ArrayList<Matiere> listeMatiere) {
		ArrayList<NoteMatiere> notes = new ArrayList<>();
		for (Matiere matiere : listeMatiere) {
			System.out.println("  ** " + matiere.getNomMatiere() + ":");
			Note n = saisirUneNote(matiere.getCoefExam(), matiere.getCoefds(), matiere.getCoefTp()); //user input
			NoteMatiere note = new NoteMatiere(matiere, n);
			notes.add(note);
		}
		return notes;
	}

	
	public void saisirLesNoteParEtudiant() { //notes input for each student for each module + AVG output
		for (Etudiant etudiant : classe.listeEtudiant) {
			System.out.println("Les notes du l'etudiant " + etudiant.getNom());
			System.out.println("S1:");
			etudiant.setNotesS1(saisirNotesSemestre(classe.listeMatiereS1));
			System.out.println("S2:");
			etudiant.setNotesS2(saisirNotesSemestre(classe.listeMatiereS2));
		}

		for (Etudiant etudiant : classe.listeEtudiant) {
			System.out.println();
			System.out.println(etudiant.getNom() + " => " + etudiant.moyenneS1() + "//" + etudiant.moyenneS2() + "==>"
					+ etudiant.moyenne());
		}
	}


	//can be used to set Notes from a JTable 
	public void saisirLesNoteParMatiere(){ //input marks per module per student  ///deprecated
		for (Matiere matiere : classe.listeMatiereS1) { 
			System.out.println("****Les notes de la matiere: " + matiere.getNomMatiere());
			for (Etudiant etudiant : classe.listeEtudiant) {
				System.out.println("Les notes du l'etudiant " + etudiant.getNom());
				Note n = saisirUneNote(matiere.getCoefExam(), matiere.getCoefds(), matiere.getCoefTp()); //input
				etudiant.ajouterNote(new NoteMatiere(matiere,n));
			}
		}
		System.out.println("___________________________ ");
		for (Matiere matiere : classe.listeMatiereS2) {
			System.out.println("****Les notes de la matiere: " + matiere.getNomMatiere());
			for (Etudiant etudiant : classe.listeEtudiant) {
				System.out.println("Les notes du l'etudiant " + etudiant.getNom());
				Note n = saisirUneNote(matiere.getCoefExam(), matiere.getCoefds(), matiere.getCoefTp());
				etudiant.ajouterNote(new NoteMatiere(matiere,n));
			}
		}
		
		//print out the data it's only for test
		for (Etudiant etudiant : classe.listeEtudiant) {
			System.out.println();
			System.out.println(etudiant.getNom() + " => " + etudiant.moyenneS1() + "//" + etudiant.moyenneS2() + "==>"
					+ etudiant.moyenne());
		}
	}
}
