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



public class login extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public login() {
		super("Bienvenue dans Gestion_Note");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450, 300);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		contentPane.setLayout(new GridLayout(3,1,20,20));
		
		JTextField login =new JTextField ("Login") ;
		//login.setBounds(150, 100,160, 30);
		contentPane.add(login);
		JTextField password= new JTextField ("Password");
		//password.setBounds(150, 130, 160, 30);
		contentPane.add(password);
		//JCheckBox check = new JCheckBox("Je ne suis pas un robot");
		//check.setBounds(150, 110, 160, 30);
		JButton connection =new JButton ("connection");
		//connection.setBounds(150,170,160,30);
		
		
		
		contentPane.add(connection);
	    connection.addActionListener(this);
			
		
		
	}
	

	



	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		login login =new login() ;
		login.setVisible(true);
		login.setAlwaysOnTop(true)	;		
		
	
}






	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Suucess");
		
	}
	}