package user;

public class Admin extends Utilisateur{
	private int idadmin;
	private int issuper=0;
	
	public Admin(String login, String passwd) {
		super(login,passwd);
		// TODO Auto-generated constructor stub
	}
	public Admin(int id,String login, String motDePasse, int idref, int type,int ida, int issuper) {
		super(id,login,motDePasse,idref,type);
		this.idadmin=ida;
		this.issuper=issuper;
		// TODO Auto-generated constructor stub
	}
	
}
