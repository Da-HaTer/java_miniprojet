package model;

public class Note {

	private double exam;
	private double ds;
	private double tp;

	public Note(double exam, double ds, double tp) {
		this.exam = exam;
		this.ds = ds;
		this.tp = tp;
	}

	public double getExam() {
		return exam;
	}

	public void setExam(double exam) {
		this.exam = exam;
	}

	public double getDs() {
		return ds;
	}

	public void setDs(double ds) {
		this.ds = ds;
	}

	public double getTp() {
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
	}

	@Override
	public String toString() {
		return "Note [exam=" + exam + ", ds=" + ds + ", tp=" + tp + "]";
	}

}
