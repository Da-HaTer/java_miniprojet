package gestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import user.Enseignant;
import user.Etudiant;

public class gestion_enseignant {
	public gestion_enseignant() {
		// TODO Auto-generated constructor stub
		Vector<String> cols=new Vector<>();
    	cols.add("id");
    	cols.add("nom");
    	cols.add("prenom");
        JFrame f = new JFrame("Gestion Enseignants");
        f.setLayout(new FlowLayout());
        String[][] data= data_fromarraylist(new Enseignant().getListEnseignant());
        gestion_entite p1=new gestion_entite("Enseignants",cols,data);
        JButton restore=p1.restore;
        restore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new gestion_enseignant();
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
							if (!arrayequals(new_data[i],(data[j]))) new Enseignant(new_data[i]).save_Enseignant(); ///not updating if last column is empty
//								System.out.println(Arrays.deepToString(new_data[i]));} //update()
						}
					}
					
					if (isnew) new Enseignant(new_data[i]).save_Enseignant();
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
						System.out.println("matiere of id "+data[i][0]+" is deleted");
						new Enseignant().delete_Enseignant((Integer.parseInt(data[i][0])));
					}
				}
				//check if deleted
				
			f.dispose();
			new gestion_enseignant();
			}
		});
        
        
        f.getContentPane().add(p1);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
	public boolean arrayequals(String[] a1,String[]a2) {///duplicate (make imported
		boolean equals=true;
		for (int i = 0; i < a2.length && equals; i++) {
			if (!(a1[i].equals(a2[i]))) equals=false;
		}
		return equals;
	}
    private String[][] data_fromarraylist(ArrayList<Enseignant> enseignants) {
		// TODO Auto-generated method stub
    	String[][] data=new String[enseignants.size()][5];
    	for (int i = 0; i < data.length; i++) {
    		Enseignant mat=enseignants.get(i);
    		data[i]=mat.toString().split(",");
		}
		return data;
	}
    public static void main(String[] args) {
        new gestion_enseignant();
    }
}
