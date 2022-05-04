package gestion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Classe;
import model.Matiere;
import user.Enseignant;
import user.Etudiant;

public class gestion_classe_attributes {
	public gestion_classe_attributes(Integer id ,String classename) {
		// TODO Auto-generated constructor stub
    	Vector<String> mat_s1=new Vector<>();
    	mat_s1.add("id matiere");
    	Vector<String> mat_s2=new Vector<>();
    	mat_s2.add("id matiere");
    	Vector<String> Etudiants=new Vector<>();
    	Etudiants.add("id etudiant");
    	
		String[][] data1= classe_data_fromarraylist(new Classe(id).getListMatieresDB(1)) ; ///get matieres of semestre 3
		String[][] data2= classe_data_fromarraylist(new Classe(id).getListMatieresDB(2)) ; ///Get matieres of semestre 2
		String[][] data3= student_data_fromarraylist(new Etudiant(id).getListEtudiants()) ; ///Get matieres of semestre 2
//    	String[][] data3= init_etudiants(); ///get etudiants of this class
//    	String[][] data3= {};
    	JPanel buttonpane=new JPanel();
        JFrame f = new JFrame("Gestion classe "+classename);//        f.setLayout(new FlowLayout()); doesn't work with scrollpane..
        gestion_entite p1=new gestion_entite("Matieres semestre 1",mat_s1,data1);
        gestion_entite p2=new gestion_entite("Matieres semestre 2",mat_s2,data2);
        gestion_entite p3=new gestion_entite("Etudiants",Etudiants,data3);
        JPanel p4=new JPanel(new BorderLayout());
        JScrollPane scroll = new JScrollPane( //scroll bar in case of small screen 
        		p4,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        f.add(scroll);
        p4.add(p1,BorderLayout.WEST);
        p4.add(p2,BorderLayout.CENTER);
        p4.add(p3,BorderLayout.EAST);
        p4.add(buttonpane,BorderLayout.SOUTH);
        
        
        //buttons & actions
        p1.valider.setVisible(false);
        p2.valider.setVisible(false);
        p3.valider.setVisible(false);
        JButton validate=new JButton();///change to a button class with color properties
        JButton restore=new JButton(); //reload this page
        buttonpane.add(validate);
        buttonpane.add(restore);
        validate.setBackground(new Color(255,220,128));//experimenting with rgb
        restore.setBackground(new Color(255,220,128));//experimenting with rgb
        validate.setText("Tout Enregistrer");
        validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				String[][][] data= {data1,data2,data3};
				String[][][] new_data= {p1.get_data(),p2.get_data(),p3.get_data()};
//				System.out.println(Arrays.deepToString(data));
//				System.out.println(Arrays.deepToString(new_data));
				//check for something new locally
				for (int k = 0; k < data.length; k++) {
	
					for (int i = 0; i < new_data[k].length; i++) {
						boolean isnew=true;
						for (int j = 0; j < data[k].length && isnew; j++) {
							if (data[k][j][0].equals(new_data[k][i][0])) {
								isnew=false;
								if (!arrayequals(new_data[k][i],(data[k][j]))) new Etudiant(new_data[k][i]).save_Etudiant();
									//System.out.println(Arrays.deepToString(new_data[i])); //update()
							}
						}
						
						if (isnew) new Etudiant(new_data[k][i]).save_Etudiant();
	//						System.out.println(Arrays.deepToString(new_data[i])); //add new
	//					}
	
					}
					//check if something is deleted locally
					for (int i = 0; i < data[k].length; i++) {
						boolean iskept=false;
						for (int j = 0; (j < new_data[k].length) && (!iskept); j++) {
							
							if(data[k][i][0].equals(new_data[k][j][0])) {
	
								iskept=true;}
						}
						if (!iskept) {
							System.out.println("matiere of id "+data[k][i][0]+" is deleted");
							new Etudiant().delete_Etudiant((Integer.parseInt(data[k][i][0])));
						}
					}
				//check if deleted
				
				}
				
			f.dispose();
			new gestion_etudiant();
			}
		});
        restore.setText("Tout restaurer");
        restore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new gestion_classe_attributes(id,classename);
			}
		});
        
        
        
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
	public boolean arrayequals(String[] a1,String[]a2) {
		boolean equals=true;
		for (int i = 0; i < a2.length && equals; i++) {
			if (!(a1[i].equals(a2[i]))) equals=false;
		}
		return equals;
	}

	
    private String[][] classe_data_fromarraylist(ArrayList<Matiere> mat) {
		// TODO Auto-generated method stub
    	String[][] data=new String[mat.size()][5];
    	for (int i = 0; i < data.length; i++) {
    		data[i]=new String[] {mat.get(i).getId().toString()};
		}
		return data;
	}
    private String[][] student_data_fromarraylist(ArrayList<Etudiant> e) {
		// TODO Auto-generated method stub
    	String[][] data=new String[e.size()][5];
    	for (int i = 0; i < data.length; i++) {
    		data[i]=new String[] {e.get(i).getId().toString()};
		}
		return data;
	}
    public static void main(String[] args) {
//    	Classe classe=new Classe();
//    	classe.setName("MI2-A");
//        new gestion_classe_attributes(classe);
    }
}
