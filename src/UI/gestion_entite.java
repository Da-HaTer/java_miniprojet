package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import javax.swing.event.*;
public class gestion_entite 
{  
    //TextField
    private Vector<JTextField> tfields; // text1, text2, text3, text4;
    private Vector<JLabel> labels; //label1, label2, label3, label4;
    private int selectedrow=0;
    // JTable Header
    private String[] columns = {
        "Name", "Age","sex","Address",
    };
    // Create the table model
    private DefaultTableModel model;
    // Create the table
    private JTable table = new JTable(model);
    // Create the main panel
    private JPanel mainPanel = new JPanel(new BorderLayout());
    public gestion_entite(Vector<String> colnames){
    	if (colnames!=null){
    		columns=(String[]) colnames.toArray(new String[colnames.size()]);
    	}
    	model= new DefaultTableModel(columns, 0);
    	table.setModel(model);
    	tfields=new Vector<JTextField>();
    	labels=new Vector<JLabel>();
        //Add button
        JButton addButton = new JButton("+ Add");
        //Update button
        JButton updateButton = new JButton("Update");
        JButton delete = new JButton("delete");
        //Button panel
        JPanel buttonPanel = new JPanel();
        //Add buttons to panel
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
                for (int j = 0; j < tfields.size(); j++) {
                	tfields.get(j).setText((String)model.getValueAt(i, j));
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
               int i = table.getSelectedRow();
               for (int j = 0; j < tfields.size(); j++) {
            	   model.setValueAt("", i, j);
				}               
            }
        });
        
        
        //Create the JTextFields panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        init_labels();
        init_fields();


//        text1 = new JTextField();
//        text2 = new JTextField();
//        text3 = new JTextField();
        
        //Add the JTextFields to the panel
        init_input_ui(textPanel);
//        textPanel.add(label1);
//        textPanel.add(text1);
//        textPanel.add(label2);
//        textPanel.add(text2);
//        textPanel.add(label3);
//        textPanel.add(text3);

//        textPanel.add(text1, BorderLayout.NORTH);
//        textPanel.add(text2, BorderLayout.CENTER);
//        textPanel.add(text3, BorderLayout.SOUTH);
//        textPanel.add(labelPanel, BorderLayout.WEST);
        //Add the panels and the table to the main panel
        mainPanel.add(textPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    private void remove_empties() {
		// TODO Auto-generated method stub
    	for (int i = 0; i < table.getRowCount(); i++) {
//    		if (model.getValueAt(0, i)=="") System.out.println(i);
    		System.out.println(model.getValueAt(0, i));
    		
		}
    	
		
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
	//Get the main panel
    public JComponent getComponent() {
        return mainPanel;
    }
    // start the application in thread-safe
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	Vector<String> cols=new Vector<>();
            	cols.add("nom");
            	cols.add("prenom");
//            	cols.add("addresse");
//            	cols.add("cin");
//            	cols.add("sex");
            	
                JFrame f = new JFrame("Update a Row in JTable");
                f.getContentPane().add(new gestion_entite(cols).getComponent());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//              f.setSize(340,250);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}