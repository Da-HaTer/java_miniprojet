package gestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Matiere;
import user.Etudiant;

public class gestion_etudiant {
	public gestion_etudiant() {
		// TODO Auto-generated constructor stub
		Vector<String> cols=new Vector<>();
		cols.add("id Etudiant"); ///maybe automate this
		cols.add("cin");
		cols.add("nom");
		cols.add("prenom");
    	cols.add("idclasse");
        JFrame f = new JFrame("Gestion Etudiants");
        f.setLayout(new FlowLayout());
        String[][] data= data_fromarraylist(new Etudiant().getListEtudiants());
        gestion_entite p1=new gestion_entite("Etudiants",cols,data);
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
							if (!arrayequals(new_data[i],(data[j]))) new Etudiant(new_data[i]).save_Etudiant();
								//System.out.println(Arrays.deepToString(new_data[i])); //update()
						}
					}
					
					if (isnew) new Etudiant(new_data[i]).save_Etudiant();
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
						new Etudiant().delete_Etudiant((Integer.parseInt(data[i][0])));
					}
				}
				//check if deleted
				
			f.dispose();
			new gestion_etudiant();
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
    private String[][] data_fromarraylist(ArrayList<Etudiant> etudiants) {
		// TODO Auto-generated method stub
    	String[][] data=new String[etudiants.size()][5];
    	for (int i = 0; i < data.length; i++) {
    		Etudiant mat=etudiants.get(i);
    		data[i]=mat.toString().split(",");
		}
		return data;
	}
    public static void main(String[] args) {
        new gestion_etudiant();
    }
}
