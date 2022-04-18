package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class espace_enseignant extends JFrame{
	private JLabel welcome;
	private JLabel lorem;
	private JPanel panel;
	private JPanel panel2;
	
	public espace_enseignant() {
		setTitle("Title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setLayout(new GridLayout(2,0));
        JButton button1 = new JButton("Button 1");
        button1.setBounds(0, 0, 10, 20);
        JButton button2 = new JButton("Button 2");
        add(button1);
        add(button2);
        setVisible(true);
		// TODO Auto-generated constructor stub
    	
	}
		
	public static void main(String[] args) {
		new espace_enseignant();
	}
}
