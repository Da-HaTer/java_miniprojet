package user;

public class Enseignant extends Utilisateur{
	private String name;
	private String cin;
	private String id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Enseignant(String login, String passwd) {
		super(login,passwd);
		// TODO Auto-generated constructor stub
	}
	public Enseignant(String id, String cin, String name) { //only id cin name (superadmin)
		super();
		this.id = id;
		this.cin = cin;
		this.name = name;
	}
	public Enseignant(String id, String cin, String name, String login, String pwd) {
		super(login,pwd);
		this.id = id;
		this.cin = cin;
		this.name = name;
		// TODO Auto-generated constructor stub
	}
}
