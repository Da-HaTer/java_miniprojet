package UI;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import main.login_form;
import model.Matiere;
import model.Note;
import model.NoteMatiere;
import user.Etudiant;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class espace_etudiant extends JFrame implements ActionListener {
	Etudiant etudiant;
	JButton deconnection, reclamation;
	ButtonGroup radioButtonGroup = new ButtonGroup();
	JRadioButton sem1, sem2;
	String[] columns = new String[] { "Matiere", "Note DS", "Note TP", "Note EXAM", "Moyenne" };
//	private DefaultTableModel model = new DefaultTableModel(columns, 0);
    // Create the table
    private JTable table = new JTable(){
	 	private static final long serialVersionUID = 1L;
		
	 	public boolean isCellEditable(int row, int column) {                
	 			return false;               
	 	};
	 };
	 
    private Object[][] data ;
    private JLabel moysem= new JLabel();
    
	public espace_etudiant(Etudiant e, int sem) {
		super("Vos Notes");
		etudiant = e;
		etudiant.init_notes();
		System.out.println("moyenne s1: "+etudiant.moyenneS1());
		System.out.println("moyenne s2: "+etudiant.moyenneS2());
//		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(900, 700);
//		this.setResizable(false);
		// this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		JPanel contentPane = (JPanel) this.getContentPane();
		// contentPane.setLayout(new GridLayout(3,1,100,10));
		
		
		contentPane.add(table);
		init_table(sem);
		
		JScrollPane scroll = new JScrollPane(table);
		contentPane.add(scroll);

		JPanel ui = new JPanel();
		ui.setLayout(new GridLayout(4, 5, 20, 25));
		add(ui);
		JLabel moylabel = new JLabel("moyenne semestre:");
		moylabel.setFont(new Font("Serif", Font.PLAIN, 18));
		moysem.setFont(new Font("Serif", Font.BOLD, 18));
		
		JLabel moylabel2 = new JLabel("moyenne Annee:");
		moylabel2.setFont(new Font("Serif", Font.PLAIN, 18));
		JLabel moyann=new JLabel(etudiant.moyenne()+"");
		moyann.setFont(new Font("Serif", Font.BOLD, 18));

		ui.add(new JLabel());
		ui.add(moylabel);
		
		ui.add(moysem);
		ui.add(new JLabel());ui.add(new JLabel());
		ui.add(moylabel2);
		ui.add(moyann);
		ui.add(new JLabel());
		JLabel label = new JLabel("selectionner semestre:");
		label.setFont(new Font("Serif", Font.PLAIN, 18));

		ui.add(new JLabel());
		ui.add(label);

		sem1 = new JRadioButton("Semestre1");
		sem2 = new JRadioButton("Semestre2");

		if (sem == 1) { //intialize selection and moyenne 
			if (etudiant != null)
			sem1.setSelected(true);
		} else {
			sem2.setSelected(true);

			if (etudiant != null)
//				moy.setText(etudiant.moyenneS2()+"");
				moysem.setText("20"); /// temp
		}

		radioButtonGroup.add(sem1);
		radioButtonGroup.add(sem2);
		sem1.addActionListener(this);
		sem2.addActionListener(this);
		ui.add(sem1);
		ui.add(sem2);

		deconnection = new JButton("deconnection");
		deconnection.addActionListener(this);
//		deconnection.setBounds(150,170,160,30);
		ui.add(new Panel());
		ui.add(new Panel());
		ui.add(deconnection);

		reclamation = new JButton("reclamation");
		reclamation.addActionListener(this);
//		reclamation.setBounds(150,170,160,30);
		ui.add(reclamation);
		setVisible(true);
	}

//	ArrayList<Matiere> sem1= etudiant.getListMatieresDB(1);
//	ArrayList<Matiere> sem2= etudiant.getListMatieresDB(2);
	/*public void init_table(int c) {
		ArrayList<Matiere> mats;
		if (c == 1)
			
			mats = etudiant.getListMatieresDB(1);
		else
			mats = etudiant.getListMatieresDB(2);

//			Object[][] data = new Object[][] {{"BD", 10, 10, 10 },{"C++", 15, 8, 20.0 }};
		data = new Object[mats.size()][5];
		for (int i = 0; i < data.length; i++) {
			Matiere mat = mats.get(i);
//			System.out.println("matiere:");
//			System.out.println(mat.toString());
			int idmat = mat.getId();
			int idnote = etudiant.fetch_id_note(idmat);
			System.out.println("idnote: " + idnote);
			Note n = new Note();
			n=n.fetch_note(idnote);
			NoteMatiere notematiere = new NoteMatiere(mat, n);
			Object[] row = new Object[] { mat.getNomMatiere(), n.getDs(), n.getTp(), n.getExam(),
					notematiere.moyenne() };
			data[i] = row;
		}
		table.setModel(new DefaultTableModel(data,columns));
	}*/
	
	public void init_table(int semester) {
		ArrayList<NoteMatiere> mats;
		if (semester == 1) {
			mats = etudiant.getNotesS1();
			moysem.setText(etudiant.moyenneS1()+""); ///note working ??
//			moy.setText("10");
		}
		else {
			mats = etudiant.getNotesS2();
			moysem.setText(etudiant.moyenneS2()+""); ///note working ??
//			moy.setText("20");
		}
//			Object[][] data = new Object[][] {{"BD", 10, 10, 10 },{"C++", 15, 8, 20.0 }};
		data = new Object[mats.size()][5];
		for (int i = 0; i < data.length; i++) {
			NoteMatiere notematiere = mats.get(i);
			Matiere mat = notematiere.getMatiere();
			Note n =notematiere.getNote();
			Object[] row = new Object[] { mat.getNomMatiere(), n.getDs(), n.getTp(), n.getExam(),
					notematiere.moyenne() };
			data[i] = row;
		}
		table.setModel(new DefaultTableModel(data,columns)); //update table
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == sem1) {
			init_table(1);
//			dispose();
//			new espace_etudiant(u, 1);
			// semester1
		}
		if (source == sem2) {
			// semester2
			init_table(2);
//			dispose();
//			new espace_etudiant(u, 2);
		}
		if (source == deconnection) {
			System.out.println("discoennected");
			dispose();
			new login_form();
		}
		if (source == reclamation) {
			System.out.println("reclamation");
//			dispose();
			new reclamation();
		}
	}

	/// todo:
//	set data based on semester (actionlistener)
//	fetch data from ??
//	pass class by reference
//	display info (name fname, class)
	public static void main(String[] args) {
		Etudiant e = new Etudiant().fetch_etudiant(1);
		new espace_etudiant(e, 1);
	}

}
