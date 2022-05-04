package gestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Classe;
import user.Enseignant;

public class gestion_classe implements ActionListener{
	private JButton define;
	private gestion_entite p1;
	public gestion_classe() {
		// TODO Auto-generated constructor stub
    	Vector<String> cols=new Vector<>();
    	cols.add("ID classe");
    	cols.add("Nom classe");
        JFrame f = new JFrame("Gestion classes");

        f.setLayout(new FlowLayout());
//        String[][] data= get_classes(); //name classe
        String[][] data= data_fromarraylist(new Classe().getListClasse());
        p1=new gestion_entite("classes",cols,data);
        JButton restore=p1.restore;
        restore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new gestion_classe();
			}
			
		});
        JButton validate=p1.valider;
        validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[][] new_data=p1.get_data();
//				System.out.println(Arrays.deepToString(data));
//				System.out.println(Arrays.deepToString(new_data));
				//check for something new locally
				for (int i = 0; i < new_data.length; i++) {
					boolean isnew=true;
					for (int j = 0; j < data.length && isnew; j++) {
						if (data[j][0].equals(new_data[i][0])) {
							isnew=false;
							if (!arrayequals(new_data[i],(data[j]))) new Classe(new_data[i]).save_Classe(); ///not updating if last column is empty
//								System.out.println(Arrays.deepToString(new_data[i]));} //update()
						}
					}
					
					if (isnew) new Classe(new_data[i]).save_Classe();
//						System.out.println(Arrays.deepToString(new_data[i])); //add new
//					}

				}
				//check if something is deleted locally
				for (int i = 0; i < data.length; i++) {
					boolean iskept=false;
					for (int j = 0; (j < new_data.length) && (!iskept); j++) {
						
						if(data[i][0].equals(new_data[j][0])) {

							iskept=true;}
					}
					if (!iskept) {
						new Classe().delete_Classe((Integer.parseInt(data[i][0])));
					}
				}
				//check if deleted
				
			f.dispose();
			new gestion_classe();
			}
		});
        
        
        
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
	public boolean arrayequals(String[] a1,String[]a2) {///duplicate (make imported
		boolean equals=true;
		for (int i = 0; i < a2.length && equals; i++) {
			if (!(a1[i].equals(a2[i]))) equals=false;
		}
		return equals;
	}
    private String[][] data_fromarraylist(ArrayList<Classe> classes) {
		// TODO Auto-generated method stub
    	String[][] data=new String[classes.size()][5];
    	for (int i = 0; i < data.length; i++) {
    		Classe classe=classes.get(i);
    		data[i]=classe.toString().split(",");
		}
		return data;
	}
    public static void main(String[] args) {
        new gestion_classe();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		if (source==define) {
			Integer classid=Integer.parseInt(p1.get_textfields().get(0).getText()) ; 
			String classname=p1.get_textfields().get(1).getText(); //selected class
			new gestion_classe_attributes(classid,classname);
		}
		// TODO Auto-generated method stub
	}
	
}
