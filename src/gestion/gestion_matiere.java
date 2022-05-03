package gestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.sound.midi.Soundbank;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Matiere;

public class gestion_matiere {
	public gestion_matiere() {
		// TODO Auto-generated constructor stub
		Vector<String> cols=new Vector<>();
    	cols.add("id");
    	cols.add("name");
    	cols.add("coef-DS");
    	cols.add("coef-TP");
    	cols.add("coef-EX");
    	cols.add("coeficient Matiere");
    	cols.add("id Enseignant");
    	String[][] data= data_fromarraylist(new Matiere().getListMatieresDB());
//    	String[][] data= {};
        JFrame f = new JFrame("Gestion Matieres");
        f.setLayout(new FlowLayout());
        gestion_entite p1=new gestion_entite("Matieres",cols,data);
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
							if (!arrayequals(new_data[i],(data[j]))) new Matiere(new_data[i]).save_matiere();
								//System.out.println(Arrays.deepToString(new_data[i])); //update()
						}
					}
					
					if (isnew) new Matiere(new_data[i]).save_matiere();
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
						new Matiere().delete_matiere(Integer.parseInt(data[i][0]));
					}
				}
				//check if deleted
				
			f.dispose();
			new gestion_matiere();
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

    private String[][] data_fromarraylist(ArrayList<Matiere> listMatieresDB) {
		// TODO Auto-generated method stub
    	String[][] data=new String[listMatieresDB.size()][7];
    	for (int i = 0; i < data.length; i++) {
    		Matiere mat=listMatieresDB.get(i);
    		data[i]=mat.toString().split(",");
		}
		return data;
	}
    
	public static void main(String[] args) {
        new gestion_matiere();
    }
}
