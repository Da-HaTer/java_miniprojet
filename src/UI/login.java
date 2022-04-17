package UI;
//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.GridLayout;
//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login implements ActionListener{
//	private int count=0;
	private JFrame frame;
	private JLabel userlabel;
	private JButton button;
	private JTextField usertext;
	private JLabel passwordlabel;
	private JPasswordField Passwordtext;
	private JPanel panel;
	private JLabel message;
	
    public login(){
    	panel = new JPanel();
        frame = new JFrame();
       
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); //centered
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        
        userlabel= new JLabel("User");
        userlabel.setBounds(10,20,80,25);
        panel.add(userlabel);
        
        usertext=new JTextField(20);
        usertext.setBounds(100,20,165,25);
        panel.add(usertext);
        
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
        message.setBounds(10,120,80,25);
        panel.add(message);
        
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new login();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		this.message.setText("success");
	}
}
  