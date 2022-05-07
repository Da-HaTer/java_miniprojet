package gestion;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import javax.swing.event.*;

public class gestion_entite extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// TextField
	private Vector<JTextField> tfields; // text1, text2, text3, text4;
	private Vector<JLabel> labels; // label1, label2, label3, label4;
	// JTable Header
	private String[] columns = { "Name", "Age", "sex", "Address", };

	// Create the table model
	private DefaultTableModel model;
	// Create the table
		private JTable table = new JTable(model){
				private static final long serialVersionUID = 1L;
		
				public boolean isCellEditable(int row, int column) {                
						return false;               
				};
			};
		
	private JPanel buttonPanel;
	public JButton valider,restore,delete,addButton,updateButton;

	// Create the main panel
	public gestion_entite(String tablename, Vector<String> colnames, String[][] data) {
		if (colnames != null) {
			columns = (String[]) colnames.toArray(new String[colnames.size()]);
		}
		int x=(colnames.size()-1)*40;
		setPreferredSize(new Dimension(400+x,600));
		setLayout(new BorderLayout());
		model = new DefaultTableModel(data, columns);
		table.setModel(model);
		tfields = new Vector<JTextField>();
		labels = new Vector<JLabel>();
		// Add button
		addButton = new JButton("+ Add");
		// Update button
		updateButton = new JButton("Update");
		restore = new JButton("Restaurer");
		delete = new JButton("delete");
		valider = new JButton("Enregistrer");
		// Button panel
		buttonPanel = new JPanel();
		// Add buttons to panel
		buttonPanel.add(restore);
		buttonPanel.add(valider);
		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(delete);

		
		// This code is called when the Add button is clicked
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check_unique_id(-1)) {
					model.addRow(getrow());
	
					// Delete form after adding data
					reinit_fields();
				}
				else JOptionPane.showMessageDialog(null, String.format("id of %s must be unique", tablename)); //alert
			}

			private void reinit_fields() {
				// TODO Auto-generated method stub
				for (JTextField jft : tfields) {
					jft.setText("");
				}
			}

			private Vector<String> getrow() {
				// TODO Auto-generated method stub
				Vector<String> row = new Vector<String>();
				for (JTextField jtf : tfields) {
					row.add(jtf.getText());
				}
				return row; // fill row from inputs
			}
		});
		// Get the selected row from JTable and put the data into JTextfields
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = table.getSelectedRow();
				if (i != -1) {
					for (int j = 0; j < tfields.size(); j++) {
						tfields.get(j).setText((String) model.getValueAt(i, j));
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
				// Update the form
				
				int i = table.getSelectedRow();
				if (check_unique_id(i)) {
					if (i != -1)
						for (int j = 0; j < tfields.size(); j++) {
							model.setValueAt(tfields.get(j).getText(), i, j);
						}
		//               model.setValueAt(text1.getText(), i, 0);
		//               model.setValueAt(text2.getText(), i, 1);
		//               model.setValueAt(text3.getText(), i, 2);
					}
				else JOptionPane.showMessageDialog(null, String.format("id of %s must be unique", tablename)); //alert
				}
		});

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Update the form
//            	model=(DefaultTableModel) e.getSource();
				int selectedrow = table.getSelectedRow();
				if (selectedrow != -1) {
					// remove selected row from the model
//	        		table.setRowSelectionInterval(0, 0);
//	        		table.clearSelection();
//	        		model.permutrow()
					model.removeRow(selectedrow);
					JOptionPane.showMessageDialog(null, "Selected row deleted successfully"); // alert
				}
			}
		});

//        valider.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	JOptionPane.showMessageDialog(null, "Donnes sauvegardes"); //alert
//            	System.out.println(Arrays.deepToString(get_data())); 
//            }
//        });

		// Create the JTextFields panel
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		JLabel title = new JLabel(tablename);
		title.setFont(new Font("Serif", Font.BOLD, 18));
		init_labels();
		init_fields();
		init_input_ui(textPanel);
		textPanel.add(title);
		// Add the panels and the table to the main panel
		add(textPanel, BorderLayout.NORTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	protected boolean check_unique_id(int line) { //checks if inserted id is unique across table before adding/updating
		String[][]data=get_data();
		boolean unique=true;
		try {
			if (Integer.parseInt(tfields.get(0).getText())<=0) return true; //permit auto index (0,-1 etc ..) (auto increment)
			for (int j = 0; j < data.length; j++) {
				if ((j != line) && data[j][0].length()!=0 && (Integer.parseInt(data[j][0]) == Integer.parseInt(tfields.get(0).getText()))) unique=false;
			}
		} catch (Exception e) {
			return true;
		}
		
		return unique;
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
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			tfields.add(new JTextField());
		}
	}

	public String[][] get_data() {
		int L = table.getRowCount();
		int C = table.getColumnCount();
		String[][] data = new String[L][C];
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < C; j++) {
				data[i][j]="";
				if (table.getValueAt(i, j)!=null)
				data[i][j] = table.getValueAt(i, j).toString();
			}
		}
		return data;
	}

	private void init_labels() {
		// TODO Auto-generated method stub
		for (String s : columns) {
			labels.add(new JLabel(s));
		}
	}

	public JPanel get_button_panel() {
		return buttonPanel;
	}

	public Vector<JTextField> get_textfields() {
		return tfields;
	}

	public Vector<JLabel> getLabels() {
		return labels;
	}

	public void setLabels(Vector<JLabel> labels) {
		this.labels = labels;
	}

	// Get the main panel
//    public JComponent getComponent() {
//        return mainPanel;
//    }
	// start the application in thread-safe
	public static void main(String[] args) {
		Vector<String> cols = new Vector<>();
		cols.add("nom");
		cols.add("prenom");

		JFrame f = new JFrame("Gestion");
		String[][] data = {{"zied","zaafrani"},{"oussema","haboubi"}}; 
//                f.setLayout(new FlowLayout());
		JPanel p1 = new gestion_entite("Table 1", cols, data);
		JPanel p4 = new JPanel();

		JScrollPane scroll = new JScrollPane( /// added scroll option for small screens
				p4, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		f.add(scroll);

		p4.add(p1);
//		p4.add(p2);
//		p4.add(p3);

//                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//              f.setSize(340,250);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}