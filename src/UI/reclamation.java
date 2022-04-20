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

public class reclamation extends JFrame implements ActionListener{
	private static final long serialVersionUID = 7L;
	JButton depot;
	JTextArea rec;
	
	public reclamation() {
		super("Reclamation");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(450, 200);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		contentPane.setLayout(new GridLayout(2,1,10,10));
		
		rec =new JTextArea("Inserer votre reclamation ici ") ;
//		rec.setCaretPosition(rec.getText().length()-1); //cursor at end
		rec.selectAll();
		rec.setLineWrap(true);
		//login.setBounds(150, 100,160, 30);
		contentPane.add(rec);
		JPanel buttonpanel= new JPanel();
		buttonpanel.setLayout(null); 
		
		depot=new JButton ("Depot");
		depot.addActionListener(this);
		depot.setBounds(40, 0, 80, 30);
		buttonpanel.add(depot);
		//connection.setBounds(150,170,160,30);
		contentPane.add(buttonpanel);
		setVisible(true);
	   //connection.addActionListener(t&his);
	}
	///todo: add actionlistener + icon
	public static void main(String[] args) throws Exception {
		new reclamation() ;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(rec.getText());
	}

}