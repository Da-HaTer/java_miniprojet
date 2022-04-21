package gestion;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class gestion_enseignant {
	public gestion_enseignant() {
		// TODO Auto-generated constructor stub
		Vector<String> cols=new Vector<>();
    	cols.add("id");
    	cols.add("cin");
    	cols.add("name");
        JFrame f = new JFrame("Gestion Enseignants");
        f.setLayout(new FlowLayout());
        JPanel p1=new gestion_entite("Enseignants",cols);
        f.getContentPane().add(p1);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
    public static void main(String[] args) {
        new gestion_enseignant();
    }
}
