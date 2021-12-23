package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class GraphDriver extends JFrame {

	private JPanel contentPane;
	private JButton btnOk;
	private JComboBox<String> directionBox, weightBox;
	public static boolean directed = false, undirected = false, weighted = false, unweighted = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphDriver frame = new GraphDriver();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GraphDriver() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		
		JLabel graphProperties = new JLabel("Graph Properties");
		sl_contentPane.putConstraint(SpringLayout.NORTH, graphProperties, 93, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, graphProperties, 293, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, graphProperties, -227, SpringLayout.EAST, contentPane);
		graphProperties.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(graphProperties);

		String [] directionStrings = {"Directed", "Undirected"};
		
		String [] weightStrings = {"Weighted", "Unweighted"};
		
		//directionBox = new JComboBox<>(directionStrings);
		//contentPane.add(directionBox);
		//weightBox = new JComboBox<>(weightStrings);
		//contentPane.add(weightBox);
		//ButtonGroup bg1 = new ButtonGroup();

		//ButtonGroup bg2 = new ButtonGroup();
		
		
		btnOk = new JButton("OK");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOk, 213, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, graphProperties, -64, SpringLayout.NORTH, btnOk);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOk, 476, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnOk, -95, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnOk, -56, SpringLayout.EAST, contentPane);
		contentPane.add(btnOk);
		
		JComboBox directionBox = new JComboBox(directionStrings);
		sl_contentPane.putConstraint(SpringLayout.NORTH, directionBox, 0, SpringLayout.NORTH, btnOk);
		sl_contentPane.putConstraint(SpringLayout.WEST, directionBox, 60, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, directionBox, -144, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, directionBox, -150, SpringLayout.WEST, btnOk);
		contentPane.add(directionBox);
		
		JComboBox weightBox = new JComboBox(weightStrings);
		sl_contentPane.putConstraint(SpringLayout.NORTH, weightBox, 16, SpringLayout.SOUTH, directionBox);
		sl_contentPane.putConstraint(SpringLayout.WEST, weightBox, 0, SpringLayout.WEST, directionBox);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, weightBox, 0, SpringLayout.SOUTH, btnOk);
		sl_contentPane.putConstraint(SpringLayout.EAST, weightBox, 0, SpringLayout.EAST, directionBox);
		contentPane.add(weightBox);
		
		
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String directionSettings= String.valueOf(directionBox.getSelectedItem());
				String weightSettings= String.valueOf(weightBox.getSelectedItem());

				if (directionSettings == directionStrings[0] && weightSettings == weightStrings[0]) {
					directed = true;
					undirected = false;
					weighted = true;
					unweighted = false;
				} else if (directionSettings == directionStrings[0] && weightSettings == weightStrings[1]) {
					directed = true;
					undirected = false;
					weighted = false;
					unweighted = true;
				} else if (directionSettings == directionStrings[1] && weightSettings == weightStrings[0]) {
					directed = false;
					undirected = true;
					weighted = true;
					unweighted = false;
				} else if (directionSettings == directionStrings[1] && weightSettings == weightStrings[1]) {
					directed = false;
					undirected = true;
					weighted = false;
					unweighted = true;
				}
				GraphADT gd = new GraphADT();
				if (weightSettings == weightStrings[0]) {
					gd.btnDijkstra.setVisible(true);
				} else if (weightSettings == weightStrings[1]) {
					gd.btnDijkstra.setVisible(false);
				}
				gd.setVisible(true);
				setVisible(false);
		
				

			}
		});
	}
}
