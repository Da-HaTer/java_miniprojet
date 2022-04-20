package UI;
import java.util.* ;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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

import gestion.Enseignant;

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

public class espace_enseignant extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 9L;
	JButton deconnection,validate;
	JComboBox<String> class_selection;
	ButtonGroup radioButtonGroup = new ButtonGroup();
	JRadioButton sem1,sem2;
	Enseignant ens;
	public espace_enseignant(Enseignant e) {
		super("Vos Notes");
		ens=e;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 300);
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
		JLabel welcome=new JLabel("BIENVENUE "+ens.getLogin().toUpperCase());
		ui.add(new JLabel());
		ui.add(welcome);
		ui.add(new JLabel());
		ui.add(new JLabel());
		ui.add(new JLabel());
		ui.add(new JLabel());
		ui.add(l);
		
		class_selection = getclasses();
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
	public JComboBox<String> getclasses() { ///make this external
		// TODO Auto-generated method stub
		///TODO
		//auto import classes here
		String[] data= {"MI2A","MI2B","PI"};
		JComboBox<String> c=new JComboBox<String>(data);
		return c;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		String classe_name =class_selection.getSelectedItem().toString();
		int sem = 1 ;
		
		if (source==validate) {
//			System.out.println("validate");
			if (sem2.isSelected()) sem=2;
			dispose();
			new espace_ens2(classe_name,sem);
		} 
		if (source==deconnection) {
			System.out.println("deconnection");
		}
		if (source==sem1) {
			System.out.println("sem1");
		}
		if (source==sem2) {
			System.out.println("sem2");
		}
		if (source==class_selection) {
//			String item =class_selection.getSelectedItem().toString();
			System.out.println(classe_name);
		}
	}
	///todo:
//	set data based on semester (actionlistener)
//	enseignant should be assigned matiere (important)
//	bouton sauvergarder (confirmation (optionon)) ..
	public static void main(String[] args) throws Exception {
		Enseignant e=new Enseignant("imen", "12345");
		new espace_enseignant(e) ;		
	}
}
