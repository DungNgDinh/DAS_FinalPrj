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

public class GraphDriver extends JFrame {

	private JPanel contentPane;
	private JRadioButton btnDirected;
	private JRadioButton btnUndirected;
	private JRadioButton btnWeighted;
	private JRadioButton btnUnweighted;
	private JButton btnOk;
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
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JLabel graphProperties = new JLabel("Graph Properties");
		sl_contentPane.putConstraint(SpringLayout.NORTH, graphProperties, 108, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, graphProperties, -307, SpringLayout.EAST, contentPane);
		graphProperties.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(graphProperties);

		btnDirected = new JRadioButton("Directed");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnDirected, 168, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnDirected, -209, SpringLayout.SOUTH, contentPane);
		contentPane.add(btnDirected);

		btnUndirected = new JRadioButton("Undirected");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnUndirected, 38, SpringLayout.SOUTH, btnDirected);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnUndirected, 0, SpringLayout.WEST, btnDirected);
		contentPane.add(btnUndirected);

		btnWeighted = new JRadioButton("Weighted");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnWeighted, 0, SpringLayout.SOUTH, btnDirected);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnWeighted, -199, SpringLayout.EAST, contentPane);
		contentPane.add(btnWeighted);

		btnUnweighted = new JRadioButton("Unweighted");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnUnweighted, 0, SpringLayout.WEST, btnWeighted);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnUnweighted, 0, SpringLayout.SOUTH, btnUndirected);
		contentPane.add(btnUnweighted);

		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(btnDirected);
		bg1.add(btnUndirected);

		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(btnWeighted);
		bg2.add(btnUnweighted);

		btnOk = new JButton("OK");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOk, 236, SpringLayout.SOUTH, graphProperties);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOk, 334, SpringLayout.EAST, btnUndirected);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnOk, -59, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnOk, -95, SpringLayout.EAST, contentPane);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (btnDirected.isSelected() && btnWeighted.isSelected()) {
					directed = true;
					undirected = false;
					weighted = true;
					unweighted = false;
				} else if (btnDirected.isSelected() && btnUnweighted.isSelected()) {
					directed = true;
					undirected = false;
					weighted = false;
					unweighted = true;
				} else if (btnUndirected.isSelected() && btnWeighted.isSelected()) {
					directed = false;
					undirected = true;
					weighted = true;
					unweighted = false;
				} else if (btnUndirected.isSelected() && btnUnweighted.isSelected()) {
					directed = false;
					undirected = true;
					weighted = false;
					unweighted = true;
				}
				GraphADT gd = new GraphADT();
				if (btnWeighted.isSelected()) {
					gd.btnDijkstra.setVisible(true);
				} else if (btnUnweighted.isSelected()) {
					gd.btnDijkstra.setVisible(false);
				}
				gd.setVisible(true);
				setVisible(false);
			}
		});
	}
}
