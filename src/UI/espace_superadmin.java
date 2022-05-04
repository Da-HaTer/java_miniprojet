package UI;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import gestion.gestion_admin;
import gestion.gestion_classe;
import gestion.gestion_enseignant;
import gestion.gestion_etudiant;
import gestion.gestion_matiere;
import gestion.gestion_utilisateur;
import user.Admin;
import user.Super_Admin;
import user.Utilisateur;

public class espace_superadmin extends espace_admin{
	public Utilisateur admin;
	public espace_superadmin(Utilisateur user) {
		super();
		admin=user;
		// TODO Auto-generated constructor stub
	}
	
	@Override
 	public JComboBox<String> get_entities() { ///make this external
		// TODO Auto-generated method stub
		///TODO
		//auto import classes here
		String[] data= {"Utilisateur","Etudiant","Enseignant","Classe","Matiere","Admin","Note"};
		JComboBox<String> c=new JComboBox<String>(data);
		return c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		if (source==validate) {
			String choix=entity_selection.getSelectedItem().toString();
			
			switch (choix) {
				case "Admin": {
//					System.out.println("etudiant");
					new gestion_admin();
					break;
				}
				case "Etudiant": {
//					System.out.println("etudiant");
					new gestion_etudiant();
					break;
				}
				case "Utilisateur": {
					new gestion_utilisateur(true);
//					System.out.println("enseignant");
					break;
				}
				case "Enseignant": {
					new gestion_enseignant();
//					System.out.println("enseignant");
					break;
				}
				case "Matiere": {
					new gestion_matiere();
					System.out.println("matiere");
					break;
				}
				case "Classe": {
					new gestion_classe();
					System.out.println("calsse");
					break;
				}
				case "Note": {
					new espace_enseignant1(admin);
					System.out.println("calsse");
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new espace_superadmin(null);
	}
}