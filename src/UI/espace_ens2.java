package UI ;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class espace_ens2 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 19L;
	public espace_ens2() {
		super("Consulter les notes");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(900, 600);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		//contentPane.setLayout(new GridLayout(3,1,100,10));
		setLayout(new FlowLayout());
		 String[] columns = new String[] {
		            "Etudinat", "Note S1", "Note S2"};
		        
		         
		       
		        Object[][] data = new Object[][] {
		            {"chedi", 10, 10},
		            {"wajih", 15, 8 }
		         
		        };
		 
		        //crée un JTable avec des données
		        JTable table = new JTable(data, columns);
		        contentPane.add(table);
		        JScrollPane scroll = new JScrollPane(table);
		        table.setFillsViewportHeight(true);
		        contentPane.add(scroll);
		        /*JLabel labelHead = new JLabel("Liste des notes");
		        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
		        contentPane.add(labelHead);*/
		        JButton deconnection =new JButton ("deconnection");
				deconnection.setBounds(150,170,160,30);
				contentPane.add(deconnection);
				JButton sauv =new JButton ("Suvegarder");
				sauv.setBounds(150,170,160,30);
				contentPane.add(sauv);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws Exception {
	UIManager.setLookAndFeel(new NimbusLookAndFeel());
	espace_ens2 login =new espace_ens2() ;
	login.setVisible(true);
	login.setAlwaysOnTop(true)	;		
	

}

}