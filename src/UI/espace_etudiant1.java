package UI;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.plaf.nimbus.NimbusLookAndFeel;
public class espace_etudiant extends JFrame  {
	private static final long serialVersionUID = 5L;
	public espace_etudiant() {
		 super("Bienvenue dens l'espace etudiant");
	       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        this.setSize(450, 300);
		     //this.setLocation(350, 200);
		     this.setLocationRelativeTo(null);
		     JPanel contentPane =(JPanel) this.getContentPane() ;
		     contentPane.setLayout(new GridLayout(2,2,20,20));
		     Label l = new Label("Sélectionner votre classe");
		     contentPane.add(l);
		
	   final Choice c = new Choice();    
		  
	   
	   c.setBounds(100, 100, 75, 75);   

	      
	   c.add("MI2A");    
	   c.add("MI2B");    
	   c.add("PI");   
	   contentPane.add(c);
	   JButton Recherche =new JButton ("Recherche");
		Recherche.setBounds(150,170,160,30);
		contentPane.add(Recherche);
		
		
		 //setLayout(new GridLayout(1, 2));
		 CheckboxGroup sem = new CheckboxGroup();
		 add(new Checkbox("Semestre1", sem, true));
		 add(new Checkbox("Semestre2", sem, false));
	  // Recherche.addActionListener(this); 
		 JButton Reclamation =new JButton ("Reclamation");
		Reclamation.setBounds(150,170,160,30);
		contentPane.add(Reclamation);
	}
	



public static void main(String[] args) throws Exception {
	UIManager.setLookAndFeel(new NimbusLookAndFeel());
	 espace_etudiant log =new espace_etudiant() ;
	log.setVisible(true);
	log.setAlwaysOnTop(true)	;
}}