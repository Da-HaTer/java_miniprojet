package model;

public class Matiere {

	private int id;
	private String nomMatiere;
	private double coefExam;
	private double coefDs;
	private double coefTp;
	private double coefMatiere;

//	public Matiere() {}
	public Matiere(double coefExam, double coefds, double coefTp, double coefMatiere, String nomMatiere) {
		this.coefExam = coefExam;
		this.coefDs = coefds;
		this.coefTp = coefTp;
		this.coefMatiere = coefMatiere;
		this.nomMatiere = nomMatiere;
	}

	public Matiere() {
		// TODO Auto-generated constructor stub
	}

	public double getCoefExam() {
		return coefExam;
	}

	public void setCoefExam(double coefExam) {
		this.coefExam = coefExam;
	}

	public double getCoefds() {
		return coefDs;
	}

	public void setCoefds(double coefds) {
		this.coefDs = coefds;
	}

	public double getCoefTp() {
		return coefTp;
	}

	public void setCoefTp(double coefTp) {
		this.coefTp = coefTp;
	}

	public double getCoefMatiere() {
		return coefMatiere;
	}

	public void setCoefMatiere(double coefMatiere) {
		this.coefMatiere = coefMatiere;
	}

	public String getNomMatiere() {
		return nomMatiere;
	}

	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Matiere [coefExam=" + coefExam + ", coefDs=" + coefDs + ", coefTp=" + coefTp + ", coefMatiere="
				+ coefMatiere + ", nomMatiere=" + nomMatiere + "]";
	}

}
