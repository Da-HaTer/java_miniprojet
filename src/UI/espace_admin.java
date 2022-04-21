package UI;

 import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gestion.gestion_classe;
import gestion.gestion_enseignant;
import gestion.gestion_etudiant;
import gestion.gestion_matiere;
import main.login_form;

 public class espace_admin extends JFrame implements ActionListener{
 JComboBox<String> entity_selection;//,action_selection;
 JButton validate,deconnection;
     public espace_admin(){
    	 setSize(350, 160);
     	 setLocationRelativeTo(null);
     	 setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         setFont(new Font("SansSerif", Font.PLAIN, 14));
         setLayout(null);
         
         JPanel contentpane= new JPanel();
         contentpane.setBounds(10, 10,100,200);
         contentpane.setLayout(new GridLayout(3,2,30,10));
         contentpane.setSize(300,100);
         add(contentpane);
         
         validate = new JButton("Suivant");
         deconnection = new JButton("deconnection");
         validate.addActionListener(this);
         deconnection.addActionListener(this);
         contentpane.add(new JLabel("Selectionner entite"));
         entity_selection=get_entities();
         contentpane.add(entity_selection);
//         contentpane.add(new JLabel("Selection action"));
//         action_selection=get_actions();
//         contentpane.add(action_selection);
         contentpane.add(validate);
         contentpane.add(deconnection);
         setVisible(true);
     }
 	public JComboBox<String> get_entities() { ///make this external
		// TODO Auto-generated method stub
		///TODO
		//auto import classes here
		String[] data= {"Etudiant","Enseignant","Classe","Matiere"};
		JComboBox<String> c=new JComboBox<String>(data);
		return c;
	}

     public static void main(String args[]) {
         new espace_admin();
     }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		if (source==validate) {
			String choix=entity_selection.getSelectedItem().toString();
			
			switch (choix) {
				case "Etudiant": {
//					System.out.println("etudiant");
					new gestion_etudiant();
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
			}
			
//			espace_admin2();
			///don't close (needed to be able to list attributes and have multiple windows)
			//case of classe (3 tables) (mats1,mats2(id,nom),students(id,nom);
			//case of matiere (1 table)
			//case of (admin/enseignant) (1 table);
			//case of etudiant (1 table) (id,cin,name);
			//view notes call espace
		}
		else if (source==deconnection) {
			dispose();
			new login_form();
		}
	}
 }