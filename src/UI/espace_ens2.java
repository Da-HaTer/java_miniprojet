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
	JButton deconnection,retour;
	
	public espace_ens2() { 
		super("Vos Notes");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 500);
		this.setResizable(false);
		//this.setLocation(350, 200);
		this.setLocationRelativeTo(null);
		JPanel contentPane =(JPanel) this.getContentPane() ;
		//contentPane.setLayout(new GridLayout(3,1,100,10));
		setLayout(new FlowLayout());
		String[] columns = new String[] {"Etudiant", "Note DS", "Note TP", "Note Exam" };
		Object[][] data = new Object[50][4];
		for (int i = 0; i < data.length; i++) {
			data[i]=new Object[]{"Etudiant "+Integer.toString(i), 10, 10, 10};
		}
		
		///check if marks already assigned here
		boolean locked=false;
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

        JPanel ui = new JPanel();
        add(ui);
        ui.setLayout(new GridLayout(1,5));
		retour =new JButton ("Retour");
		retour.addActionListener(this);
//		deconnection.setBounds(150,170,160,30);
		ui.add(new Panel()); ui.add(new Panel()); ///automate with number??
		ui.add(retour);
		
		deconnection =new JButton ("Deconnection");
		deconnection.addActionListener(this);
//		reclamation.setBounds(150,170,160,30);
		ui.add(deconnection);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if (source==retour){
				System.out.println("retour");
		}
		else if (source==deconnection){
			System.out.println("deconnection");
	}
		
		
	}
	///todo:
//	set data based on semester (actionlistener)
//	enseignant should be assigned matiere (important)
	public static void main(String[] args) throws Exception {
		new espace_ens2() ;		
	}

}
