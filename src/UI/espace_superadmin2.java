package UI;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import gestion.gestion_note;
import main.login_form;
import model.Classe;
import model.Matiere;
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

public class espace_superadmin2 extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 9L;
	JButton deconnection,validate;
	JComboBox<String> class_selection=new JComboBox<String>();
	JComboBox<String> matiere_selection=new JComboBox<String>();
	ArrayList<Classe> classes;
	ArrayList<Matiere> matieres;
	public espace_superadmin2() {
		super("Gestion notes");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 200);
		this.setResizable(false);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		setLayout(new FlowLayout());
        JPanel ui = new JPanel();
        ui.setLayout(new GridLayout(3,2,20, 25));
        ///TODO
//        change to gridbaglayout 
        
		JLabel l = new JLabel("Selectionner classe");
		l.setFont(new Font("Serif", Font.PLAIN, 18));
//		ui.add(new JLabel());
		ui.add(l);
		getclasses();
		getmatieres();
		
		class_selection.addActionListener(this);
		ui.add(class_selection);
//		ui.add(new JLabel());
        
        add(ui);
//		ui.add(new JLabel());
		JLabel label = new JLabel("Selectionner matiere:");
		label.setFont(new Font("Serif", Font.PLAIN, 18));
//		ui.add(new JLabel());
		ui.add(label);
		ui.add(matiere_selection);
		
		deconnection=new JButton ("Fermer");
		deconnection.addActionListener(this);
		validate=new JButton ("Valider");
		validate.addActionListener(this);
//		ui.add(new Panel());
//		ui.add(new Panel());
		ui.add(validate);
		ui.add(deconnection);
		setVisible(true);
	}
	public void getclasses() { ///make this external
		classes=new Classe().getListClasse();
		String[] data=new String[classes.size()];
		for (int i = 0; i < classes.size(); i++) {
			data[i]=classes.get(i).getName();
		}
		class_selection.setModel(new DefaultComboBoxModel<String>(data));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		
		if (source==validate) {
//			System.out.println("validate");
			if (class_selection.getSelectedIndex()==-1 || matiere_selection.getSelectedIndex()==-1) JOptionPane.showMessageDialog(null, "Erreur\nSelectioner classe et matiere"); //replace classe name by class classe
			else {
				Classe classe=classes.get(class_selection.getSelectedIndex());
				Matiere mat=matieres.get(matiere_selection.getSelectedIndex());
				dispose();
				new gestion_note(classe,mat,true); ///get class from classname, get matiere from enseignant
			}
		}
		else if (source==deconnection) {
			System.out.println("deconnection");
			dispose();
			new login_form();
		}
		else if (source==class_selection) {
			System.out.println("selected class");
			getmatieres();
		}
	}
	private void getmatieres() {
		Classe classe=classes.get(class_selection.getSelectedIndex());
		matieres=classe.getListMatieresDB(1);
		matieres.addAll(classe.getListMatieresDB(2)); //list of all matieres
		String[] data=new String[matieres.size()];
		for (int i = 0; i < data.length; i++) {
			data[i]=matieres.get(i).getNomMatiere();
		}
		matiere_selection.setModel(new DefaultComboBoxModel<String>(data));
		
	}

	public static void main(String[] args) throws Exception {
		Enseignant e=new Enseignant("imen", "12345");
		e.setName("test");
		new espace_superadmin2() ;		
	}
}
