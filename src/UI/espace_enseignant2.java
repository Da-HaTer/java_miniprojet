package UI;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import main.login_form;
import user.Enseignant;
import user.Utilisateur;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class espace_enseignant2 extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 9L;
	private boolean locked;
	JButton valider,deconnection,retour;
	Utilisateur ens;
	JTable table;
	
	public espace_enseignant2(String classname, int sem,Utilisateur ens2) { 
		super("Vos Notes");
		ens=ens2;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(550, 550);
		this.setResizable(false);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		//contentPane.setLayout(new GridLayout(3,1,100,10));
		setLayout(new FlowLayout());
		
		get_data();
		
		JLabel classe=new JLabel("Classe: "+classname);
		classe.setFont(new Font("Serif", Font.BOLD, 18));
		
		contentPane.add(classe);
        contentPane.add(table);
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        contentPane.add(scroll);

        JPanel ui = new JPanel();
        add(ui);
        ui.setLayout(new GridLayout(1,5,5,5));
        valider  =new JButton ("valider");
		valider.addActionListener(this);
		
		retour =new JButton ("Retour");
		retour.addActionListener(this);
//		deconnection.setBounds(150,170,160,30);
		ui.add(new Panel()); ///automate with number??
		ui.add(valider);
		ui.add(retour);
		
		deconnection =new JButton ("Deconnection");
		deconnection.addActionListener(this);
//		reclamation.setBounds(150,170,160,30);
		ui.add(deconnection);
		setVisible(true);
	}

	private void get_data() { //fill table
		// TODO Auto-generated method stub
		String[] columns = new String[] {"Etudiant", "Note DS", "Note TP", "Note Exam" };
		Object[][] data = new Object[50][4];
		for (int i = 0; i < data.length; i++) {
			data[i]=new Object[]{"Etudiant "+Integer.toString(i), 10, 10, 10};
		}
		
		///check if marks already assigned here
		locked=false;
		if (ens instanceof Enseignant) {
			System.out.println("enseignant");
			locked=true;
		}
		
		if (locked) {
	        table =new JTable(data, columns)
	        {
				private static final long serialVersionUID = 1L;
		
				public boolean isCellEditable(int row, int column) {                
						return false;               
				};
			};
		}
		else {
			table = new JTable(data, columns);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if (source==retour){
				System.out.println("retour");
				dispose();
				new espace_enseignant1(ens);
		}
		else if (source==deconnection){
			dispose();
			new login_form();
		}
		else if (source==valider){
			System.out.println("valider");
		}
	}
	///todo:
//	set data based on semester (actionlistener)
//	enseignant should be assigned matiere (important)
	public static void main(String[] args) throws Exception {
		new espace_enseignant2("MI2-A",1,null);
	}

}
