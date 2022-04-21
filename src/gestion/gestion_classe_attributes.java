package gestion;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class gestion_classe_attributes {
	public gestion_classe_attributes(String classname) {
		// TODO Auto-generated constructor stub
    	Vector<String> mat_s1=new Vector<>();
    	mat_s1.add("id matiere");
    	Vector<String> mat_s2=new Vector<>();
    	mat_s2.add("id matiere");
    	Vector<String> Etudiants=new Vector<>();
    	Etudiants.add("id etudiant");
        JFrame f = new JFrame("Gestion classe "+classname);
        f.setLayout(new FlowLayout());
        JPanel p1=new gestion_entite(mat_s1);
        JPanel p2=new gestion_entite(mat_s2);
        JPanel p3=new gestion_entite(Etudiants);
        f.getContentPane().add(p1);
        f.getContentPane().add(p2);
        f.getContentPane().add(p3);
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
    public static void main(String[] args) {
        new gestion_classe_attributes("");
    }
}
