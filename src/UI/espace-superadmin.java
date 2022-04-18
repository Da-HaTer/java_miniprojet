package UI;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class espace_superadmin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 5L;
	public espace_superadmin() {
		super("Bienvenue dans L'espace admin");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1366, 768);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		contentPane.setLayout(new GridLayout(6,1,20,20));
		JButton ajout_e =new JButton ("Ajouter étudiant");
		contentPane.add(ajout_e);
	    ajout_e.addActionListener(this);
	    
	    JButton supp_e =new JButton ("Supprimer étudiant");
		contentPane.add(supp_e);
	    supp_e.addActionListener(this);
	    
	    JButton ajout_p =new JButton ("Ajouter Enseignant");
		contentPane.add(ajout_p);
	    ajout_p.addActionListener(this);
	    
	    JButton supp_p =new JButton ("Spprimer étudiant");
		contentPane.add(supp_p);
	    supp_p.addActionListener(this);
	    
	    JButton cons =new JButton ("Consulter les notes");
		contentPane.add(cons);
	    cons.addActionListener(this);
	    JButton modif =new JButton ("Modifier les notes");
		contentPane.add(modif);
	    cons.addActionListener(this);
	    
	}
	



public static void main(String[] args) throws Exception {
	UIManager.setLookAndFeel(new NimbusLookAndFeel());
	espace_superadmin login =new espace_superadmin() ;
	login.setVisible(true);
	login.setAlwaysOnTop(true);
}


@Override
public void actionPerformed(ActionEvent e) {
	
	System.out.println("Suucess");
	
}
}