package gestion;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import javax.swing.event.*;
public class gestion_entite extends JPanel
{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TextField
    private Vector<JTextField> tfields; // text1, text2, text3, text4;
    private Vector<JLabel> labels; //label1, label2, label3, label4;
    // JTable Header
    private String[] columns = {
        "Name", "Age","sex","Address",
    };
    // Create the table model
    private DefaultTableModel model;
    // Create the table
    private JTable table = new JTable(model);
    private JPanel buttonPanel;
    // Create the main panel
    public gestion_entite(Vector<String> colnames){
    	if (colnames!=null){
    		columns=(String[]) colnames.toArray(new String[colnames.size()]);
    	}
    	setLayout(new BorderLayout());
    	model= new DefaultTableModel(columns, 0);
    	table.setModel(model);
    	tfields=new Vector<JTextField>();
    	labels=new Vector<JLabel>();
        //Add button
        JButton addButton = new JButton("+ Add");
        //Update button
        JButton updateButton = new JButton("Update");
        JButton delete = new JButton("delete");
        JButton valider = new JButton("valider");
        //Button panel
        buttonPanel = new JPanel();
        //Add buttons to panel
        buttonPanel.add(valider);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(delete);
        
    
        // This code is called when the Add button is clicked
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //Add form data
              model.addRow(getrow());
        
              //Delete form after adding data
              reinit_fields();
            }
			private void reinit_fields() {
				// TODO Auto-generated method stub
				for (JTextField jft:tfields) {
					jft.setText("");
				}
			}

			private Vector<String> getrow() {
				// TODO Auto-generated method stub
				Vector<String> row = new Vector<String>();
				for (JTextField jtf:tfields) {
					row.add(jtf.getText());
				}
				return row; //fill row from inputs
			}
        });
          // Get the selected row from JTable and put the data into JTextfields
          table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
          @Override
          public void valueChanged(ListSelectionEvent e) {
                int i = table.getSelectedRow();
                if (i!=-1) {
	                for (int j = 0; j < tfields.size(); j++) {
	                	tfields.get(j).setText((String)model.getValueAt(i, j));
					}
                }
//                text1.setText((String)model.getValueAt(i, 0));
//                text2.setText((String)model.getValueAt(i, 1));
//                text3.setText((String)model.getValueAt(i, 2));
            }
        });
    
        // This code is called when the Update button is clicked.
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //Update the form
               int i = table.getSelectedRow();
               for (int j = 0; j < tfields.size(); j++) {
            	   model.setValueAt(tfields.get(j).getText(), i, j);
				}
//               model.setValueAt(text1.getText(), i, 0);
//               model.setValueAt(text2.getText(), i, 1);
//               model.setValueAt(text3.getText(), i, 2);
            }
        });
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //Update the form
//            	model=(DefaultTableModel) e.getSource();
            	int selectedrow=table.getSelectedRow();
	        	if(selectedrow != -1) {
	                // remove selected row from the model
//	        		table.setRowSelectionInterval(0, 0);
//	        		table.clearSelection();
//	        		model.permutrow()
	                model.removeRow(selectedrow);
	                JOptionPane.showMessageDialog(null, "Selected row deleted successfully"); //alert
	        	}             
            }
        });
        
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "Donnes sauvegardes"); //alert
            }
        });
        
        //Create the JTextFields panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        init_labels();
        init_fields();
        init_input_ui(textPanel);
        //Add the panels and the table to the main panel
        add(textPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private void init_input_ui(JPanel textPanel) {
		// TODO Auto-generated method stub
		for (int i = 0; i < columns.length; i++) {
	        textPanel.add(labels.get(i));
	        textPanel.add(tfields.get(i));

		}
	}
	private void init_fields() {
		// TODO Auto-generated method stub
    	for (int i = 0; i < columns.length; i++) {
    		tfields.add(new JTextField());
		}
	}
	private void init_labels() {
		// TODO Auto-generated method stub
		for (String s:columns) {
			labels.add(new JLabel(s));
		}
	}
	public JPanel get_button_panel() {
		return buttonPanel;
	}
	public Vector<JTextField> get_textfields(){
		return tfields;
	}
	//Get the main panel
//    public JComponent getComponent() {
//        return mainPanel;
//    }
    // start the application in thread-safe
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	Vector<String> cols=new Vector<>();
            	cols.add("nom");
            	cols.add("prenom");
            	cols.add("addresse");
            	cols.add("cin");
            	cols.add("sex");
                JFrame f = new JFrame("Gestion");
                f.setLayout(new FlowLayout());
                JPanel p1=new gestion_entite(cols);
                JPanel p2=new gestion_entite(cols);
                JPanel p3=new gestion_entite(cols);
                f.getContentPane().add(p1);
                f.getContentPane().add(p2);
                f.getContentPane().add(p3);
                
//                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
//              f.setSize(340,250);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}