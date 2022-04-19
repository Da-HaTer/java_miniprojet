package UI;

 import java.awt.*;
 import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

 public class espace_admin extends JFrame {
 JComboBox<String> entity_selection,action_selection;
     public espace_admin(){
    	 setSize(350, 150);
     	 setLocationRelativeTo(null);
     	 setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         setFont(new Font("SansSerif", Font.PLAIN, 14));
         setLayout(null);
         
         JPanel contentpane= new JPanel();
         contentpane.setBounds(10, 10,100,200);
         contentpane.setLayout(new GridLayout(3,2,30,10));
         contentpane.setSize(300,100);
         add(contentpane);
         
         JButton validate = new JButton("valider");
         JButton deconnection = new JButton("deconnection");
         
         contentpane.add(new JLabel("Selection entite"));
         entity_selection=get_entities();
         contentpane.add(entity_selection);
         contentpane.add(new JLabel("Selection action"));
         action_selection=get_actions();
         contentpane.add(action_selection);
         contentpane.add(validate);
         contentpane.add(deconnection);
         setVisible(true);
         
     }
 	public JComboBox<String> get_entities() { ///make this external
		// TODO Auto-generated method stub
		///TODO
		//auto import classes here
		String[] data= {"etudiant","enseignant","classes","matieres"};
		JComboBox<String> c=new JComboBox<String>(data);
		return c;
	}
 	public JComboBox<String> get_actions() { ///make this external
		// TODO Auto-generated method stub
		///TODO
		//auto import classes here
		String[] data= {"create","List","update","delete"};
		JComboBox<String> c=new JComboBox<String>(data);
		return c;
	}

     public static void main(String args[]) {
         new espace_admin();
     }
 }