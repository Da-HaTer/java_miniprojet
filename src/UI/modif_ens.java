package UI;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class modif_ens extends JFrame implements ActionListener {
	private static final long serialVersionUID = 11L;
	public modif_ens() {
		super("Bienvenue dans Gestion_enseignant");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450, 300);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		contentPane.setLayout(new GridLayout(5,1,10,10));
		
		JTextField CIN =new JTextField ("CIN enseigant") ;
		//login.setBounds(150, 100,160, 30);
		JTextField nom =new JTextField ("Nom enseignat") ;
		//login.setBounds(150, 100,160, 30);
		JTextField module =new JTextField ("Module") ;
		//login.setBounds(150, 100,160, 30);
		contentPane.add(CIN);
		contentPane.add(nom);
		contentPane.add(module);
		JButton ajout =new JButton ("Ajouter");
		JButton supp =new JButton ("Supprimer");
		contentPane.add(ajout);
		contentPane.add(supp);
	}
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		modif_ens login =new modif_ens() ;
		login.setVisible(true);
		login.setAlwaysOnTop(true)	;	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}}