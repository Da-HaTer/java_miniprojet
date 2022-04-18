package UI;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.accessibility.Accessible;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Choice;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.ItemSelectable;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class Espace_ens1  extends JFrame implements ActionListener {
	private static final long serialVersionUID = 2L;
	public  Espace_ens1() {
       super("Bienvenue dens l'espace enseignant");
       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 300);
	     //this.setLocation(350, 200);
	     this.setLocationRelativeTo(null);
	     JPanel contentPane =(JPanel) this.getContentPane() ;
	     contentPane.setLayout(new GridLayout(3,1,20,20));
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
   Recherche.addActionListener(this);  
	}

public static void main(String[] args) throws Exception {
	UIManager.setLookAndFeel(new NimbusLookAndFeel());
	 Espace_ens1 Espace_ens1 =new Espace_ens1() ;
	Espace_ens1.setVisible(true);
	Espace_ens1.setAlwaysOnTop(true)	;	
}
@Override
public void actionPerformed(ActionEvent e) {
	
	System.out.println("Suucess");
	
}
}