package UI;
import java.util.* ;

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

import gestion.Etudiant;

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

public class espace_etudiant2 extends JFrame implements ActionListener  {
	Etudiant u;
	JButton deconnection,reclamation;
	ButtonGroup radioButtonGroup = new ButtonGroup();
	JRadioButton sem1,sem2;
	JTable table;
	
	public espace_etudiant2(Etudiant u, int sem) {
		super("Vos Notes");
		this.u=u;
//		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(900, 700);
//		this.setResizable(false);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		//contentPane.setLayout(new GridLayout(3,1,100,10));
		setLayout(new FlowLayout());
		init_table(sem);
        contentPane.add(table);
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        contentPane.add(scroll);
        /*JLabel labelHead = new JLabel("Liste des notes");
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
        contentPane.add(labelHead);*/
        
        JPanel ui = new JPanel();
        ui.setLayout(new GridLayout(3,5,20, 25));
        add(ui);
        JLabel moylabel = new JLabel("moyenne semestre:");
		moylabel.setFont(new Font("Serif", Font.PLAIN, 18));
//		ui.add(new Panel());
		ui.add(new JLabel());
		ui.add(moylabel);
		
		JTextField moy= new JTextField();
//		moy.setPreferredSize(new Dimension(100,20));
//		ui.add(new Panel());
		ui.add(moy);
		
		ui.add(new JLabel());
		JLabel label = new JLabel("selectionner semestre:");
		label.setFont(new Font("Serif", Font.PLAIN, 18));
//		label.setSize(150,50);
//		label.setPreferredSize(new Dimension(250, 100));
//		label.setBackground(new Color(255,255,255));
		ui.add(new JLabel());
		ui.add(label);
		
		sem1=new JRadioButton("Semestre1");
//		else sem2.setSelected(false);
		
		sem2=new JRadioButton("Semestre2");
		
		if (sem==1) {
			if(u!=null) moy.setText(Double.toString(u.moyenneS1()));
			moy.setText("10");///temp
			sem1.setSelected(true);
		}
		else{
			sem2.setSelected(true);
			moy.setText("20"); ///temp
			if(u!=null) moy.setText(Double.toString(u.moyenneS2()));
		}
		
		radioButtonGroup.add(sem1);
		radioButtonGroup.add(sem2);
		sem1.addActionListener(this);
		sem2.addActionListener(this);
		ui.add(sem1);
		ui.add(sem2);
		
		deconnection =new JButton ("deconnection");
		deconnection.addActionListener(this);
//		deconnection.setBounds(150,170,160,30);
		ui.add(new Panel());
		ui.add(new Panel());
		ui.add(deconnection);
		
		reclamation =new JButton ("reclamation");
		reclamation.addActionListener(this);
//		reclamation.setBounds(150,170,160,30);
		ui.add(reclamation);
		setVisible(true);
	}

	public void init_table(int c){
		if (c==1) {
			String[] columns = new String[] {"Matiere", "Note S1", "Note S2", "Moyenne" };
			Object[][] data = new Object[][] {{"BD", 10, 10, 10 },{"C++", 15, 8, 20.0 }};
			
	        table= new JTable(data, columns){
//				private static final long serialVersionUID = 1L;
		
				public boolean isCellEditable(int row, int column) {                
						return false;               
				};
			};
		}
		if (c==2) {
			String[] columns = new String[] {"Matiere", "Note S1", "Note S2", "Moyenne" };
			Object[][] data = new Object[][] {{"proba", 10, 10, 10 },{"JAVA", 15, 8, 10.0 }};
			
	        table= new JTable(data, columns){
				private static final long serialVersionUID = 1L;
		
				public boolean isCellEditable(int row, int column) {                
						return false;               
				};
			};
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source==sem1) {
			dispose();
			new espace_etudiant2(u,1);
			//semester1
		}
		if (source==sem2) {
			//semester2
			dispose();
			new espace_etudiant2(u,2);
		}
		if (source==deconnection) {
			System.out.println("discoennected");
			dispose();
			new login_form();
		}
		if (source==reclamation) {
			System.out.println("reclamation");
			dispose();
			new reclamation();
		}
	}
	///todo:
//	set data based on semester (actionlistener)
	public static void main(String[] args){
		new espace_etudiant2(null,1);
	}

}
