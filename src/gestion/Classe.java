package gestion;

import java.util.ArrayList;

import model.Matiere;

public class Classe { //like struct
	public ArrayList<Matiere> listeMatiereS1 = new ArrayList<>();
	public ArrayList<Matiere> listeMatiereS2 = new ArrayList<>();
	public ArrayList<Etudiant> listeEtudiant = new ArrayList<>();

	public static void main(String[] args) {

		Classe mi2 = new Classe();

		//mi2.listeMatiereS1.add(new Matiere(0.7, 0.3, 0, 2, "Algo"));
		mi2.listeMatiereS1.add(new Matiere(0.7, 0.3, 0, 1, "math"));
		mi2.listeMatiereS1.add(new Matiere(0.7, 0, 0.3, 1, "c++"));

		mi2.listeMatiereS2.add(new Matiere(0.7, 0, 0.3, 1, "JAVA"));
		mi2.listeMatiereS2.add(new Matiere(0.7, 0.3, 0, 1, "proba"));
		//mi2.listeMatiereS2.add(new Matiere(0.7, 0, 0.3, 1, "Python"));
		
		mi2.listeEtudiant.add(new Etudiant("01-1", "00000000", "Ahmed"));
		mi2.listeEtudiant.add(new Etudiant("01-2", "00000022", "Sami"));
		//mi2.listeEtudiant.add(new Etudiant("01-3", "00000033", "Sana"));
		//mi2.listeEtudiant.add(new Etudiant("01-4", "00000044", "Rim"));
		
		Calcule c = new Calcule(mi2);
		//c.saisirLesNoteParEtudiant();
		c.saisirLesNoteParMatiere();

	}

}
