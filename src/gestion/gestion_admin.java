package gestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Matiere;
import user.Admin;

public class gestion_admin {
	public gestion_admin() {
		// TODO Auto-generated constructor stub
		Vector<String> cols=new Vector<>();
		cols.add("idadmin");
    	cols.add("isSuper");
        JFrame f = new JFrame("Gestion Admins");
        f.setLayout(new FlowLayout());
        String[][] data= data_fromarraylist(new Admin().getListAdmin());
        gestion_entite p1=new gestion_entite("Admins",cols,data);///external action listener
//        p1.delete.setVisible(true);
        JButton restore=p1.restore;
        restore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new gestion_admin();
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
							if (!arrayequals(new_data[i],(data[j]))) new Admin(new_data[i]).save_Admin();
								
						}
					}
					
					if (isnew) new Admin(new_data[i]).save_Admin();

				}
				//check if something is deleted locally
				for (int i = 0; i < data.length; i++) {
					boolean iskept=false;
					for (int j = 0; (j < new_data.length) && (!iskept); j++) {
						
						if(data[i][0].equals(new_data[j][0])) {

							iskept=true;}
					}
					if (!iskept) {
						System.out.println("matiere of id "+data[i][0]+" is deleted");
						new Admin().delete_Admin(Integer.parseInt(data[i][0]));
					}
				}
				//check if deleted
				
			f.dispose();
			new gestion_admin();
			}
		});

        
        f.getContentPane().add(p1);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
	
	public boolean arrayequals(String[] a1,String[]a2) {
		boolean equals=true;
		for (int i = 0; i < a2.length && equals; i++) {
			if (!(a1[i].equals(a2[i]))) equals=false;
		}
		return equals;
	}

    private String[][] data_fromarraylist(ArrayList<Admin> listMatieresDB) {
		// TODO Auto-generated method stub
    	String[][] data=new String[listMatieresDB.size()][7];
    	for (int i = 0; i < data.length; i++) {
    		Admin admins=listMatieresDB.get(i);
    		data[i]=admins.toString().split(",");
		}
		return data;
	}
    public static void main(String[] args) {
        new gestion_admin();
    }
}
