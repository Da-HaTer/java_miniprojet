package model;

public class NoteMatiere {
	private Matiere matiere;
	private Note note;

	public NoteMatiere(Matiere matiere, Note notes) {
		this.matiere = matiere;
		this.note = notes;
	}

	public NoteMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public double moyenne() {
		return (note.getExam() * matiere.getCoefExam() + note.getTp() * matiere.getCoefTp()
				+ note.getDs() * matiere.getCoefds());
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note notes) {
		this.note = notes;
	}
	
	
	@Override
	public String toString() {
		return "NoteMatiere [matiere=" + matiere.toString() + ", notes=" + note.toString() + "]";
	}

	public static void main(String[] args) {
		Matiere m = new Matiere(0.7, 0, 0.3, 1, "JAVA");
		Note n = new Note(14, 0, 17);
		NoteMatiere note = new NoteMatiere(m, n);
		double x = note.moyenne();
		System.out.println(x);
	}

}
