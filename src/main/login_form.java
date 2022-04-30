package main;
//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

//import java.awt.GridLayout;
//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import UI.espace_admin;
import UI.espace_enseignant1;
import UI.espace_etudiant;
import UI.espace_superadmin;
import user.Utilisateur;
import user.Admin;
import model.Classe;
import model.Matiere;
import user.Enseignant;
import user.Etudiant;
import user.Super_Admin;

public class login_form extends JFrame implements ActionListener{
//	private int count=0;
//	private JFrame frame;
	private JLabel userlabel;
	private JButton button;
	private JTextField login;
	private JLabel passwordlabel;
	private JPasswordField Passwordtext;
	private JPanel panel;
	private JLabel message;
	
	public static final int etudiantTypeCode = 1;
	public static final int ensegnantTypeCode = 2;
	public static final int adminypeCode = 3;
	
	Vector <Utilisateur> users=new Vector<Utilisateur>();
	Vector <Classe> classes=new Vector<Classe>();
	
//	private void initUserList() {
//		users.add(new Etudiant("user1", "Ahmed", "Ben Ahmed", "ahmed", "1234"));
//		users.add(new Enseignant("Imene", "12345"));
//		users.add(new Admin("admin", "*****"));
//		users.add(new Super_Admin("root", "toor"));
//	}

    public login_form(){
//    	initUserList();
    	panel = new JPanel();
//        frame = new JFrame();
       
        setSize(400, 180);
    	
        setLocationRelativeTo(null); //centered
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        panel.setLayout(null);
        
        userlabel= new JLabel("User");
        userlabel.setBounds(10,20,80,25);
        panel.add(userlabel);
        
        login=new JTextField(20);
        login.setBounds(100,20,165,25);
        panel.add(login);
        
        passwordlabel= new JLabel("Password");
        passwordlabel.setBounds(10,50,80,25);
        panel.add(passwordlabel);
        
        Passwordtext=new JPasswordField(20);
        Passwordtext.setBounds(100,50,165,25);
        panel.add(Passwordtext);
        
        button = new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(this);
        panel.add(button);
        
        message=new JLabel("");
        message.setBounds(10,110,100,25);
        panel.add(message);
        setVisible(true);
    }
    public static void main(String[] args) {
        new login_form();
    }
    
//    private Utilisateur connect(String l,String p){ deprecated after integration of getUserFromDB (user class)
//    	for(Utilisateur u:users) {
//			if (u.seConnecter(l, p)){
//				return u;
//			}
//		}
//    	return null ;
//    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String l= this.login.getText();
		String p= this.Passwordtext.getText();
		Utilisateur user = new Utilisateur(l, p);
		user = user.getUserFromDB(); //null if not found
//		Utilisateur user=connect(l,p); deprecated 
		if(user!=null){
//			System.out.println(user.getClass());
			dispose();
			
			switch (user.getType()) {
				case etudiantTypeCode:{
					
					Etudiant etudiant = Etudiant.getEtudiantFromDB(user.idref);
//					System.err.println(etudiant.toString());
					new espace_etudiant(etudiant,1);
					// start the user main screen
					break;
				}
				case ensegnantTypeCode: { /// complete these
					break;
				}
				case adminypeCode: {
					break;
				}
			}
			
//			System.out.print("login Successful as ");
//			if (user instanceof Etudiant) {
////				dispose();
//				System.out.println("Etudiant "+l);
//				new espace_etudiant((Etudiant) user,1);
//			}
//			if (user instanceof Enseignant){
//				System.out.println("Enseignant "+l);
//				new espace_enseignant1(user);
//			}
//			if (user instanceof Admin) {
//				System.out.println("Admin "+l);
//				new espace_admin();
//			}
//			if (user instanceof Super_Admin) {
//				System.out.println("SuperAdmin "+l);
//				new espace_superadmin(user);
//			}
		}
		else {
			message.setText("user not found !!"); ///temp, make this alert instead of message
		}
		
	}
}
  