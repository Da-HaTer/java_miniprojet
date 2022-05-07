package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.PreparedStatement;

import lib.MYSQL_Connection;
import model.Classe;
import model.Matiere;
import model.Note;
import model.NoteMatiere;

public class Etudiant extends Utilisateur {
	private Integer id;
	private String cin;
	private String nom;
	private String prenom;
	private Integer idClasse;

	private ArrayList<NoteMatiere> notesS1 = new ArrayList<>();//deprecated
	private ArrayList<NoteMatiere> notesS2 = new ArrayList<>();

	public Etudiant(Integer id, String cin, String nom, String prenom, int idClasse, ArrayList<NoteMatiere> notesS1,
			ArrayList<NoteMatiere> notesS2) { //w/o login & passwd  (for superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.notesS1 = notesS1;
		this.notesS2 = notesS2;
	}
	
	public Etudiant(int id, String nom, String prenom, String cin, Integer idc) { //only id cin name (superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.idClasse = idc;
	}

	public Etudiant(Integer id,String login, String pwd,int ide, String cin, String nom, String prenom,int idc) { // w/o notes (user)
		super(id,login, pwd,ide,1);
		this.id = ide;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.idClasse=idc;
	}
	
	public Etudiant(int id) {
		this.id=id;
	}
	
	public Etudiant() {
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String[] s) {
		if (s[0].length()!=0) this.id=Integer.parseInt(s[0]);
		else this.id=-1;
		this.cin = s[1];
		this.nom = s[2];
		this.prenom = s[3];
		if (s[4].length()!=0) this.idClasse=Integer.parseInt(s[4]);
		else this.idClasse=null;
		
		// TODO Auto-generated constructor stub
	}

	public Integer getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Integer idClasse) {
		this.idClasse = idClasse;
	}
	
	public Integer getId() {
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
		return id + "," + cin + "," + nom +"," + prenom +","+ idClasse+",";
//				+ ", notesS2=" + notesS2.toString() + "]";
	}
	
	public String toString_verbose() {
		return super.toString_verbose()+"Etudiant [id=" + id + ", cin=" + cin + ", nom=" + nom +", prenom=" + prenom +", idClasse="+ idClasse+", notesS1=" + notesS1.toString()
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

	// initialiser les matieres
	
	public void init_notes() {
		init_notes_sem(1);
		init_notes_sem(2);
	}
	
	public Classe get_classe() {
    	try {
			String query="select idClasse from Etudiant where idEtudiant=?;";
			java.sql.Connection connection=MYSQL_Connection.getconnection();
		    PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
		    preparedStmt.setInt(1, id);
		    ResultSet r = preparedStmt.executeQuery();
		    Classe classe=null;
		    while(r.next()) {
		    	classe=new Classe().fetch_Classe(r.getInt(1));
		    }
		    connection.close();
		    return classe;
		}
			
		catch (SQLException e) {e.printStackTrace();}
    	return null;
	}
	
	private void init_notes_sem(int sem) {
    	Classe classe=get_classe(); //get class of this student
    	if (classe!=null) {
    		ArrayList<Matiere> matieres=classe.getListMatieresDB(sem); //get matieres of this class for given semesters
    		ArrayList<NoteMatiere> notes=new ArrayList<NoteMatiere>();
    		for(Matiere matiere:matieres) {
    			NoteMatiere notematiere=new NoteMatiere();
    			Note note=notematiere.get_note(matiere.getId(), id); //for each matiere get notes of this student
    			notematiere.setNote(note);
    			notematiere.setMatiere(matiere);
    			notes.add(notematiere);
    		}
    		if (sem==1){
    			setNotesS1(notes);
    		}
    		else if(sem==2) {
    			setNotesS2(notes);
    		}
    	}
		
		/*try {
			String query="select * from Etudiant where idEtudiant=?;";
			java.sql.Connection connection=MYSQL_Connection.getconnection();
		    PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
		    preparedStmt.setInt(1, id);
		    ResultSet r = preparedStmt.executeQuery();
		    Etudiant etudiant=null;
		    while(r.next()) {
		    	etudiant=new Etudiant(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getInt(5));
		    }
		    connection.close();

			}
			
			catch (SQLException e) {e.printStackTrace();}*/
	}
	
	public void delete_Etudiant(int id) {
    	try {
    	String query="delete from etudiant where idEtudiant=?;";
    	java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        int rowsaffected = preparedStmt.executeUpdate();
        System.out.println(rowsaffected);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
    public void save_Etudiant() { //save or udpate
    	
        try{
        	String query=String.format("update etudiant set idEtudiant=?,nom=?,prenom=?,cin=?,idClasse=?\r\n"
        			+ "where idEtudiant=%d;",this.id);
	    	if (fetch_etudiant(id)==null) {
	    		query = "insert into etudiant values (?,?,?,?,?);"; // WHERE Login=? and Pwd=?";
	    	}
            java.sql.Connection connection=MYSQL_Connection.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            if (id<=0) preparedStmt.setNull(1, id);
            else preparedStmt.setInt(1, id);
            preparedStmt.setString(2, nom);
			preparedStmt.setString(3, prenom);
			preparedStmt.setString(4, cin);
			if (idClasse!=null && idClasse>0) preparedStmt.setInt(5, idClasse); //case of null added
			else preparedStmt.setNull(5,java.sql.Types.NULL);

            int rowsaffected = preparedStmt.executeUpdate();
            System.out.println(rowsaffected);

            connection.close();
        }
        
        catch (SQLException e) {e.printStackTrace();}
	}
    
    public Etudiant fetch_etudiant(int id){
    	try {
    	
    	String query="select * from Etudiant where idEtudiant=?;";
    	java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet r = preparedStmt.executeQuery();
        Etudiant etudiant=null;
        while(r.next()) {
        	etudiant=new Etudiant(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getInt(5));
        }
        connection.close();
    	return etudiant;
    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
    	return null;
    }
	
    public ArrayList<Etudiant> getListEtudiants() { ///returns matieres of this class for a given smester
		try {
			String query ="select * from etudiant";
			
			java.sql.Connection connection=MYSQL_Connection.getconnection();
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			ResultSet resultSet = preparedStmt.executeQuery();
			ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
			ResultSet r=resultSet;
			while (r.next()) {
//				int id,String login, String pwd,int ide, String cin, String nom, String prenom,id
				etudiants.add(new Etudiant(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getInt(5)));
			}
			connection.close();
			return etudiants;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
	/*public int fetch_id_note(int idMatiere) {///to be deleted
		try {
			String query="select idNote from NoteMatiere\n"
					+ "where idMatiere=? and idEtudiant=?;";
			java.sql.Connection connection=MYSQL_Connection.getconnection();
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
	}*/
	
	public static void main(String[] args) {
//		ArrayList<Etudiant> etudiants=new Etudiant().getListEtudiants();
//		for (Etudiant e:etudiants) {
//			System.out.println(e);
//		}
		Etudiant e=new Etudiant(2,"oussema","haboubi","1666666",1);
//		e.save_Etudiant();
		e.delete_Etudiant(2);
//		u=u.getEtudiantFromDB(1);
//		System.out.println(u.toString());
	}

}
