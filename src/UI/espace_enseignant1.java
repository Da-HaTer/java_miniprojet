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

public class espace_enseignant1 extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 9L;
	JButton deconnection,validate;
	JComboBox<String> class_selection=new JComboBox<String>();
	ArrayList<Classe> classes;
	ButtonGroup radioButtonGroup = new ButtonGroup();
	JRadioButton sem1,sem2;
	Enseignant ens;
	public espace_enseignant1(Enseignant e) {
		super("Espace Enseignant");
		ens=e;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 250);
		this.setResizable(false);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		setLayout(new FlowLayout());
        JPanel ui = new JPanel();
        ui.setLayout(new GridLayout(4,5,20, 25));
        ///TODO
//        change to gridbaglayout 
        
		JLabel l = new JLabel("Selectionner classe");
		l.setFont(new Font("Serif", Font.PLAIN, 18));
		JLabel welcome=new JLabel("BIENVENUE "+ens.getName().toUpperCase());
		ui.add(new JLabel());
		ui.add(welcome);
		ui.add(new JLabel());
		ui.add(new JLabel());
		ui.add(new JLabel());
		ui.add(new JLabel());
		ui.add(l);
		getclasses(1);
		class_selection.addActionListener(this);
		ui.add(class_selection);
		ui.add(new JLabel());
        
        add(ui);
		ui.add(new JLabel());
		JLabel label = new JLabel("selectionner semestre:");
		label.setFont(new Font("Serif", Font.PLAIN, 18));
		ui.add(new JLabel());
		ui.add(label);
		sem1=new JRadioButton("Semestre1");
		sem2=new JRadioButton("Semestre2");
		radioButtonGroup.add(sem1);
		radioButtonGroup.add(sem2);
		sem1.addActionListener(this);
		sem2.addActionListener(this);
		ui.add(sem1);
		ui.add(sem2);
		
		deconnection=new JButton ("Deconnection");
		deconnection.addActionListener(this);
		validate=new JButton ("Valider");
		validate.addActionListener(this);
		ui.add(new Panel());
		ui.add(new Panel());
		ui.add(validate);
		ui.add(deconnection);
		setVisible(true);
	}
	public void getclasses(int sem) { ///make this external
		// TODO Auto-generated method stub
		///TODO
		//auto import classes here
		classes=ens.getListeclasses(sem);
		String[] data=new String[classes.size()];
		for (int i = 0; i < classes.size(); i++) {
			data[i]=classes.get(i).getName();
		}
		class_selection.setModel(new DefaultComboBoxModel<String>(data));
//		String[] data= {"MI2A","MI2B","PI"};
//		JComboBox<String> c=new JComboBox<String>(data);
//		return c;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		Classe classe=new Classe();
		if (class_selection.getSelectedIndex()!=-1)
			classe=classes.get(class_selection.getSelectedIndex());
			
		
		int sem = 1 ;
		
		if (source==validate) {
//			System.out.println("validate");
			if (class_selection.getSelectedIndex()==-1) JOptionPane.showMessageDialog(null, "No classe selected"); //replace classe name by class classe
			else {
				if (sem2.isSelected()) sem=2;
//				dispose();
				new gestion_note(classe,ens.get_matiere(),false); ///get class from classname, get matiere from enseignant
			}
		}
		else if (source==deconnection) {
			System.out.println("deconnection");
			dispose();
			new login_form();
		}
		else if (source==sem1) {
			getclasses(1);
		}
		else if (source==sem2) {
			getclasses(2);
		}
	}
	///todo:
//	set data based on semester (actionlistener)
//	enseignant should be assigned matiere (important)
//	bouton sauvergarder (confirmation (optionon)) ..
	public static void main(String[] args) throws Exception {
		Enseignant e=new Enseignant("imen", "12345");
		e.setName("test");
		new espace_enseignant1(e) ;		
	}
}
