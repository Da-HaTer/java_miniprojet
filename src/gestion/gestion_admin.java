package gestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import user.Admin;

public class gestion_admin {
	public gestion_admin() {
		// TODO Auto-generated constructor stub
		Vector<String> cols=new Vector<>();
		cols.add("idUser");
		cols.add("username");
    	cols.add("password");
    	cols.add("idref");
    	cols.add("type");
    	cols.add("idAdmin");
    	cols.add("super");
        JFrame f = new JFrame("Gestion Admins");
        f.setLayout(new FlowLayout());
        
        
        gestion_entite p1=new gestion_entite("Admins",cols);///external action listener
        p1.valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "Donnes sauvegardes"); //alert
            	String[][] s=p1.get_data();
            	ArrayList<Admin> admins=new ArrayList<Admin>();
            	for (int i = 0; i < s.length; i++) {
					admins.add(new Admin(Integer.parseInt(s[i][0]),s[i][1], 
							s[i][2], Integer.parseInt(s[i][3]) ,
							Integer.parseInt(s[i][4]) ,
							Integer.parseInt(s[i][5]), 
							Integer.parseInt(s[i][6]))
							);
				}
            }
        });
        
        f.getContentPane().add(p1);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
    public static void main(String[] args) {
        new gestion_admin();
    }
}
