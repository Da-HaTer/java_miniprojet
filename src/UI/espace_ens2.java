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

public class espace_ens2 extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 9L;
	
	public espace_ens2() { 
		super("Vos Notes");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(900, 700);
//		this.setResizable(false);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		//contentPane.setLayout(new GridLayout(3,1,100,10));
		setLayout(new FlowLayout());
		String[] columns = new String[] {"Etudiant", "Note DS", "Note TP", "Note Exam" };
		Object[][] data = new Object[][] {{"BD", 10, 10, 10 },{"JAVA", 15, 8, 50.0 }};
		///check if marks already asigned here
		boolean locked=true;
		JTable table;
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
        contentPane.add(table);
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        contentPane.add(scroll);
        /*JLabel labelHead = new JLabel("Liste des notes");
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
        contentPane.add(labelHead);*/
        
        JPanel ui = new JPanel();
        ui.setLayout(new GridLayout(3,5,20, 25));
        
		Label l = new Label("Selectionner classe");
		l.setFont(new Font("Serif", Font.PLAIN, 18));
		ui.add(new JLabel());
		ui.add(l);
		
		final Choice c = new Choice();    
		c.setBounds(100, 100, 75, 75);   
		///todo
		//auto import classes;
		c.add("MI2A");    
		c.add("MI2B");    
		c.add("PI");   
		ui.add(c);
		ui.add(new JLabel());
        
        add(ui);
		ui.add(new JLabel());
		JLabel label = new JLabel("selectionner semestre:");
		label.setFont(new Font("Serif", Font.PLAIN, 18));
//		label.setSize(150,50);
//		label.setPreferredSize(new Dimension(250, 100));
//		label.setBackground(new Color(255,255,255));
		ui.add(new JLabel());
		ui.add(label);
		CheckboxGroup sem = new CheckboxGroup();
		
		ui.add(new Checkbox("Semestre1", sem, true));
//		
		ui.add(new Checkbox("Semestre2", sem, false));
		
		JButton deconnection =new JButton ("Retour");
//		deconnection.setBounds(150,170,160,30);
		ui.add(new Panel());
		ui.add(new Panel());
		ui.add(deconnection);
		
		JButton reclamation =new JButton ("Deconnection");
//		reclamation.setBounds(150,170,160,30);
		ui.add(reclamation);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	///todo:
//	set data based on semester (actionlistener)
//	enseignant should be assigned matiere (important)
	public static void main(String[] args) throws Exception {
	UIManager.setLookAndFeel(new NimbusLookAndFeel());
	espace_ens2 login =new espace_ens2() ;
	login.setVisible(true);
	login.setAlwaysOnTop(true)	;		
	

}

}
