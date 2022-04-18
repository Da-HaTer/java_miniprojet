package UI;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class reclamation extends JFrame   {
	private static final long serialVersionUID = 7L;
	public reclamation() {
		super("Reclamation");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(450, 200);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		contentPane.setLayout(new GridLayout(2,1,10,10));
		
		JTextArea rec =new JTextArea("Inserer votre reclamation ici ") ;
//		rec.setCaretPosition(rec.getText().length()-1); //cursor at end
		rec.selectAll();
		rec.setLineWrap(true);
		//login.setBounds(150, 100,160, 30);
		contentPane.add(rec);
		JPanel buttonpanel= new JPanel();
		buttonpanel.setLayout(null); 
		
		JButton connection =new JButton ("Depot");
		connection.setBounds(30, 0, 80, 50);
		buttonpanel.add(connection);
		//connection.setBounds(150,170,160,30);
		contentPane.add(buttonpanel);
	   //connection.addActionListener(t&his);
	}
	///todo: add actionlistener + icon + reclamation object
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		reclamation login =new reclamation() ;
		login.setVisible(true);
		login.setAlwaysOnTop(true);		
	}

}