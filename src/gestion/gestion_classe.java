package gestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class gestion_classe implements ActionListener{
	private JButton define;
	private gestion_entite p1;
	public gestion_classe() {
		// TODO Auto-generated constructor stub
    	Vector<String> cols=new Vector<>();
    	cols.add("Nom classe");
        JFrame f = new JFrame("Gestion classes");
        f.setLayout(new FlowLayout());
        p1=new gestion_entite("classes",cols);
        JPanel p2=p1.get_button_panel();
        define=new JButton("Definir");
        define.addActionListener(this);
        p2.add(define);
        f.getContentPane().add(p1);
        
//        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        
//        f.addWindowListener((WindowListener) new WindowAdapter() { ///read data on close ??
//            public void windowClosing(WindowEvent e) {
//            	System.out.println("Hi");
//            	f.dispose();
//                // call terminate
//            }
//        });  
	}
    public static void main(String[] args) {
        new gestion_classe();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		if (source==define) {
			String classe=p1.get_textfields().get(0).getText();
			new gestion_classe_attributes(classe);
		}
		// TODO Auto-generated method stub
	}
	
}
