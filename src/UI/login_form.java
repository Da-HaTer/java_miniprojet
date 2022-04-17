package UI;
//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

//import java.awt.GridLayout;
//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import espace_etudiant.Utilisateur;
import gestion.Admin;
import gestion.Enseignant;
import gestion.Etudiant;

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
	Vector <Utilisateur> users=new Vector<Utilisateur>();
	
	private void initUserList() {
		users.add(new Etudiant("user1", "Ahmed", "Ben Ahmed", "ahmed", "1234"));
		users.add(new Enseignant("Imene", "12345"));
		users.add(new Admin("admin", "*****"));
	}

    public login_form(){
    	initUserList();
    	panel = new JPanel();
//        frame = new JFrame();
       
        setSize(400, 200);
    	
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
        message.setBounds(10,120,100,25);
        panel.add(message);
        setVisible(true);
    }
    public static void main(String[] args) {
        new login_form();
    }
    
    private Utilisateur connect(String l,String p){
    	for(Utilisateur u:users) {
			if (u.seConnecter(l, p)){
				return u;
			}
		}
    	return null ;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String l= this.login.getText();
		String p= this.Passwordtext.getText();
		Utilisateur user=connect(l,p);
		if(user!=null){
//			message.setText("user found !!");
//			System.out.println(user.getClass());
			System.out.print("login Successful as ");
			if (user instanceof Etudiant) {
				System.out.println("Etudiant");
			}
			if (user instanceof Enseignant){
				System.out.println("Enseignant");
			}
			if (user instanceof Admin) {
				System.out.println("Admin");
			}
		}
		else {
			message.setText("user not found !!"); ///temp, make this alert instead of message
		}
		
	}
}
  