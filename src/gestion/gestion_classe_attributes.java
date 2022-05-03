package gestion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Classe;

public class gestion_classe_attributes {
	public gestion_classe_attributes(Classe classe) {
		// TODO Auto-generated constructor stub
    	Vector<String> mat_s1=new Vector<>();
    	mat_s1.add("id matiere");
    	Vector<String> mat_s2=new Vector<>();
    	mat_s2.add("id matiere");
    	Vector<String> Etudiants=new Vector<>();
    	Etudiants.add("id etudiant");
    	
		String[][] data1= classe.init_semestre(1); ///get matieres of semestre 3
		String[][] data2= classe.init_semestre(2); ///Get matieres of semestre 2
//    	String[][] data3= init_etudiants(); ///get etudiants of this class
    	String[][] data= {};
    	JPanel buttonpane=new JPanel();
        JFrame f = new JFrame("Gestion classe "+classe.getName());//        f.setLayout(new FlowLayout()); doesn't work with scrollpane..
        gestion_entite p1=new gestion_entite("Matieres semestre 1",mat_s1,data);
        gestion_entite p2=new gestion_entite("Matieres semestre 2",mat_s2,data);
        gestion_entite p3=new gestion_entite("Etudiants",Etudiants,data);
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
				
			}
		});
        restore.setText("Tout restaurer");
        restore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new gestion_classe_attributes(classe);
			}
		});
        
        
        
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
    public static void main(String[] args) {
    	Classe classe=new Classe();
    	classe.setName("MI2-A");
        new gestion_classe_attributes(classe);
    }
}
