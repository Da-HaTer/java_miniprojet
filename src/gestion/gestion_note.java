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

import model.Classe;
import model.Matiere;
import model.Note;
import model.NoteMatiere;
import user.Admin;
import user.Etudiant;

public class gestion_note {
	public gestion_note(Classe classe,Matiere mat,boolean previlege) {
		// TODO Auto-generated constructor stub
		Vector<String> cols=new Vector<>();
		cols.add("id note");
		cols.add("Etudiant");    	
    	cols.add("Note ds");
    	cols.add("Note TP");
    	cols.add("Note exam");
        JFrame f = new JFrame("Gestion Notes");
        f.setLayout(new FlowLayout());
//        String[][] data= data_fromarraylist(new Admin().getListAdmin());
        
        ArrayList<Etudiant> etudiants= classe.getListeEtudiants();
        ArrayList<Note> notes=new ArrayList<Note>();
		String[][] data = new String[etudiants.size()][4];
		for (int i = 0; i < data.length; i++) {
			Etudiant e=etudiants.get(i);
			Note n=new NoteMatiere().get_note(mat.getId(),e.getId()); ///todo: notematiere(matiere,id etudiant)
			notes.add(n);
			if (n!=null) {
				String ds,exam,tp;
				if (n.getDs()==null) ds="";
				else ds=Double.toString(n.getDs());
				if (n.getExam()==null) exam="";
				else exam=Double.toString(n.getExam());
				if (n.getTp()==null) tp="";
				else tp=Double.toString(n.getTp());
				data[i]=new String[]{Integer.toString(n.getId()) ,e.getNom()+" "+e.getPrenom(),ds,tp,exam};
			}
				
			else data[i]=new String[]{"",e.getNom()+" "+e.getPrenom(), "", "", ""};
		}
        
        gestion_entite p1=new gestion_entite("Notes",cols,data);///external action listener
        
        p1.get_textfields().get(0).setVisible(false);
        p1.getLabels().get(0).setVisible(false);
        p1.get_textfields().get(1).setVisible(false);
        p1.getLabels().get(1).setVisible(false);
        
        p1.delete.setVisible(false);
        p1.addButton.setVisible(false);
        JButton restore=p1.restore;
        restore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new gestion_note(classe,mat,previlege);
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
					if (!arrayequals(new_data[i],(data[i]))){
						Note note=notes.get(i);
						note.setDs(Double.parseDouble(0+new_data[i][2]));
						note.setTp(Double.parseDouble(0+new_data[i][3]));
						note.setExam(Double.parseDouble(0+new_data[i][4]));
						if (note.isvalid()) note.save_note(previlege);
						else JOptionPane.showMessageDialog(null, "Note invalide");
					}
				}
			f.dispose();
			new gestion_note(classe,mat,previlege);
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
    	Classe classe=new Classe().fetch_Classe(1);
    	Matiere mat=new Matiere().fetch_matiere(2);
        new gestion_note(classe,mat,true);
    }
}
