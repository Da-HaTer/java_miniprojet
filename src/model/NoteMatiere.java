package model;

public class NoteMatiere {
	private Matiere matiere;
	private Note notes;

	public NoteMatiere(Matiere matiere, Note notes) {
		this.matiere = matiere;
		this.notes = notes;
	}

	public NoteMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public double moyenne() {
		return (notes.getExam() * matiere.getCoefExam() + notes.getTp() * matiere.getCoefTp()
				+ notes.getDs() * matiere.getCoefds());

	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Note getNotes() {
		return notes;
	}

	public void setNotes(Note notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "NoteMatiere [matiere=" + matiere.toString() + ", notes=" + notes.toString() + "]";
	}

	public static void main(String[] args) {
		Matiere m = new Matiere(0.7, 0, 0.3, 1, "JAVA");
		Note n = new Note(14, 0, 17);
		NoteMatiere note = new NoteMatiere(m, n);
		double x = note.moyenne();
		System.out.println(x);
	}

}
