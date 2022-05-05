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
import model.Classe;
import model.Matiere;
import model.Note;
import model.NoteMatiere;
import model.queries;
import user.Enseignant;
import user.Etudiant;
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

public class espace_enseignant2 extends JFrame implements ActionListener  { //to be deleted
	private static final long serialVersionUID = 9L;
	private boolean locked;
	private JButton valider,deconnection,retour;
	private Enseignant ens;
	private JTable table=new JTable();
	private Classe classe;
	private Matiere mat;
	private boolean lock; //
	
	//arraylist etudiants
	//arraylist init notes (initial notes)
	
	public espace_enseignant2(Classe classe, Matiere mat,int sem,boolean lock) { 
		super("Vos Notes");
		this.classe=classe;
		this.mat=mat;
		this.lock=lock;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(550, 550);
		this.setResizable(false);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		//contentPane.setLayout(new GridLayout(3,1,100,10));
		setLayout(new FlowLayout());
		
		get_data();
		
		JLabel classe_label=new JLabel("Classe: "+classe.getName());
		classe_label.setFont(new Font("Serif", Font.BOLD, 18));
		
		contentPane.add(classe_label);
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

		int idmatiere;
		idmatiere=0;///to be deleted:
		
		ArrayList<Etudiant> etudiants= classe.getListeEtudiants();
		Object[][] data = new Object[etudiants.size()][4];
		for (int i = 0; i < data.length; i++) {
			Etudiant e=etudiants.get(i);
			Note n=new NoteMatiere().get_note(mat.getId(),e.getId()); ///todo: notematiere(matiere,id etudiant)
			
			if (n!=null)data[i]=new Object[]{e.getNom()+" "+e.getPrenom(), n.getDs(), n.getTp(), n.getExam()};
			else data[i]=new Object[]{e.getNom()+" "+e.getPrenom(), "", "", ""};
		}
		///check if marks already assigned here
		locked=lock;
		
		if (locked) {
	        table =new JTable(data, columns)
	        {
				private static final long serialVersionUID = 1L;
				
				public boolean isCellEditable(int row, int column) {       //fingers crossed         
						if (column==0) return false;
						else if (data[row][column]!="") return false; 
						else return true;
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
			save_notes();
			//refresh for easy lock
		}
	}
	private void save_notes() {
		// save only changed notes
		double[] notes=get_notes(); //get notes from table (check gestion entit�)
		//compare notes with init notes;
		//if not the same save note and refresh table ( easy lock )
	}

	///todo:
//	set data based on semester (actionlistener)
//	enseignant should be assigned matiere (important)
	public static void main(String[] args) throws Exception {
		new espace_enseignant2("MI2-A",1,null);
	}

}
