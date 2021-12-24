package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import algo.DijkstraAlgorithm;
import models.AdjacencyList;
import models.AdjacencyMatrix;
import models.Edge;
import models.EdgeList;
import models.Node;
import models.Graph;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GraphADT extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxGraph;
	private JButton btnReset;
	private JPanel panel_1, graphPanel;


	private DrawUtils drawUtils;
	private Graph graph = new Graph();

	private boolean weighted = GraphDriver.weighted, unweighted = GraphDriver.unweighted, directed = GraphDriver.directed,
			undirected = GraphDriver.undirected;
	JButton btnDijkstra;
	private JMenuBar menuBar;
	private JMenu representationsMenu;
	private JMenuItem adjacencyListMenuItem;
	private JMenuItem adjacencyMatricesMenuItem;
	private JMenuItem edgeListMenuItem;
	private JMenu controlMenu;
	private JMenuItem resetMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem changePropertiesMenuItem;
	private JMenu infoMenu;
	private JMenuItem helpMenuItem;
	private JMenuItem aboutMenuItem;

	/**
	 * Create the frame.
	 */
	public GraphADT() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 496);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		controlMenu = new JMenu("File");
		menuBar.add(controlMenu);
		
		resetMenuItem = new JMenuItem("Reset");
		resetMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((PanelGraph) graphPanel).reset();
			}
		});
		controlMenu.add(resetMenuItem);
		
		changePropertiesMenuItem = new JMenuItem("Change properties");
		changePropertiesMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GraphDriver main = new GraphDriver();
				main.setVisible(true);
				setVisible(false);
			}
		});
		controlMenu.add(changePropertiesMenuItem);
		
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		controlMenu.add(exitMenuItem);
		
		representationsMenu = new JMenu("Graph Representation");
		menuBar.add(representationsMenu);
		
		adjacencyListMenuItem = new JMenuItem("Adjacency Lists");
		representationsMenu.add(adjacencyListMenuItem);
		adjacencyListMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

					AdjacencyList al = new AdjacencyList(graph);
					JOptionPane.showMessageDialog(null, al.print(), "Adjacency List", JOptionPane.INFORMATION_MESSAGE);


			}
		});
		
		adjacencyMatricesMenuItem = new JMenuItem("Adjacency Matrices");
		adjacencyMatricesMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdjacencyMatrix am = new AdjacencyMatrix(graph);
				JOptionPane.showMessageDialog(null, am.print(), "Adjacency Matrix",
				JOptionPane.INFORMATION_MESSAGE);
			}
		});
		representationsMenu.add(adjacencyMatricesMenuItem);
		
		edgeListMenuItem = new JMenuItem("Edge List");
		edgeListMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EdgeList el = new EdgeList(graph);
				JOptionPane.showMessageDialog(null, el.print(), "Egde List", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		representationsMenu.add(edgeListMenuItem);
		
		infoMenu = new JMenu("Info");
		menuBar.add(infoMenu);
		
		helpMenuItem = new JMenuItem("Help");
		helpMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Click on empty space to create new node\n" + "Drag from node to node to create an edge\n"
								+ "Click on edges to set the weight\n\n" + "Combinations:\n"
								+ "Shift + Left Click       :    Set node as source\n"
								+ "Shift + Right Click     :    Set node as destination\n"
								+ "Ctrl  + Drag                :    Reposition Node\n"
								+ "Ctrl  + Click                :    Get Path of Node\n"
								+ "Ctrl  + Shift + Click   :    Delete Node/Edge\n");

			}
		});
		infoMenu.add(helpMenuItem);
		
		aboutMenuItem = new JMenuItem("About us");
		aboutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"This a group project made by us:\n" + "Vu Minh Huy - Designer\n"
								+ "Nam -\n" + "Duy - \n");

			}
		});
		infoMenu.add(aboutMenuItem);

		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		graphPanel = new PanelGraph(graph);
		contentPane.add(graphPanel, BorderLayout.CENTER);
		
				btnDijkstra = new JButton("Dijkstra");
				contentPane.add(btnDijkstra, BorderLayout.SOUTH);

		btnDijkstra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph);
				try {
					dijkstraAlgorithm.run();
					((PanelGraph) graphPanel).setPath(dijkstraAlgorithm.getDestinationPath());
				} catch (IllegalStateException ise) {
					JOptionPane.showMessageDialog(null, ise.getMessage());
				}

			}
		});

	}

	/*
	 * public void init() {
	 * 
	 * DefaultListSelectionModel model = new DefaultListSelectionModel();
	 * EnabledJComboBoxRenderer enableRenderer = new
	 * EnabledJComboBoxRenderer(model);
	 * 
	 * if (weighted) { model.addSelectionInterval(0, 0);
	 * comboBoxAlgorithm.setRenderer(enableRenderer);
	 * comboBoxAlgorithm.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub if (((JComboBox) e.getSource()).getSelectedIndex() != 0) {
	 * JOptionPane.showMessageDialog(null, "You can't select that item", "ERROR",
	 * JOptionPane.ERROR_MESSAGE); } else { System.out.println(((JComboBox)
	 * e.getSource()).getSelectedItem()); } } }); } else if (unweighted) {
	 * model.addSelectionInterval(1, 2);
	 * comboBoxAlgorithm.setRenderer(enableRenderer);
	 * comboBoxAlgorithm.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { if (((JComboBox)
	 * e.getSource()).getSelectedIndex() != 1 && ((JComboBox)
	 * e.getSource()).getSelectedIndex() != 2) { JOptionPane.showMessageDialog(null,
	 * "You can't select that item", "ERROR", JOptionPane.ERROR_MESSAGE); } else {
	 * System.out.println(((JComboBox) e.getSource()).getSelectedItem()); } } }); }
	 * }
	 */
	class PanelGraph extends JPanel implements MouseListener, MouseMotionListener {
		private DrawUtils drawUtils;

		private Graph graph;

		private Node selectedNode = null;
		private Node hoveredNode = null;
		private Edge hoveredEdge = null;

		private List<Node> path = null;

		private Point cursor;

		public PanelGraph(Graph graph) {
			this.graph = graph;

			addMouseListener(this);
			addMouseMotionListener(this);
		}

		public void setPath(List<Node> path) {
			this.path = path;
			hoveredEdge = null;
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D graphics2d = (Graphics2D) g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			drawUtils = new DrawUtils(graphics2d);

			if (graph.isSolved()) {
				drawUtils.drawPath(path);
			}

			if (selectedNode != null && cursor != null) {
				Edge e = new Edge(selectedNode, new Node(cursor));
				drawUtils.drawWeightEdge(e);
			}

			for (Edge edge : graph.getEdges()) {
				if (weighted) {
					if (edge == hoveredEdge) {
						drawUtils.drawWeightHoveredEdge(edge);
					}
					drawUtils.drawWeightEdge(edge);
				} else {
					if (edge == hoveredEdge) {
						drawUtils.drawUnweightHoveredEdge(edge);
					}
					drawUtils.drawUnweightEdge(edge);
				}

			}

			for (Node node : graph.getNodes()) {
				if (node == selectedNode || node == hoveredNode)
					drawUtils.drawHalo(node);
				if (graph.isSource(node))
					drawUtils.drawSourceNode(node);
				else if (graph.isDestination(node))
					drawUtils.drawDestinationNode(node);
				else
					drawUtils.drawNode(node);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			Node selected = null;
			for (Node node : graph.getNodes()) {
				if (DrawUtils.isWithinBounds(e, node.getCoord())) {
					selected = node;
					break;
				}
			}
			if (selected != null) {

				if (e.isControlDown() && e.isShiftDown()) {
					graph.deleteNode(selected);
					graph.setSolved(false);
					repaint();
					return;
				} else if (e.isControlDown() && graph.isSolved()) {
					path = selected.getPath();
					repaint();
					return;
				} else if (e.isShiftDown()) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						if (!graph.isDestination(selected))
							graph.setSource(selected);
						else
							JOptionPane.showMessageDialog(null, "Destination can't be set as Source");
					} else if (SwingUtilities.isRightMouseButton(e)) {
						if (!graph.isSource(selected))
							graph.setDestination(selected);
						else
							JOptionPane.showMessageDialog(null, "Source can't be set as Destination");
					} else
						return;

					graph.setSolved(false);
					repaint();
					return;

				}
			}

			if (hoveredEdge != null) {
				if (e.isControlDown() && e.isShiftDown()) {
					graph.getEdges().remove(hoveredEdge);
					hoveredEdge = null;
					graph.setSolved(false);
					repaint();
					return;
				}
				if (weighted) {
					if (directed) {
						String input = JOptionPane
								.showInputDialog("Enter weight for " + hoveredEdge.toString() + " : ");
						try {
							int weight = Integer.parseInt(input);
							if (weight > 0) {
								hoveredEdge.setWeight(weight);
								graph.setSolved(false);
								repaint();
							} else {
								JOptionPane.showMessageDialog(null, "Weight should be positive");
							}
						} catch (NumberFormatException nfe) {
						}
						return;
					} else {
						String input = JOptionPane.showInputDialog("Enter weight : ");
						try {
							int weight = Integer.parseInt(input);
							if (weight > 0) {
								hoveredEdge.setWeight(weight);
								graph.setSolved(false);
								repaint();
							} else {
								JOptionPane.showMessageDialog(null, "Weight should be positive");
							}
						} catch (NumberFormatException nfe) {
						}
						return;
					}
				} else {
					graph.setSolved(false);
					repaint();
				}
			}

			for (Node node : graph.getNodes()) {
				if (DrawUtils.isOverlapping(e, node.getCoord())) {
					JOptionPane.showMessageDialog(null, "Overlapping Node can't be created");
					return;
				}
			}

			graph.addNode(e.getPoint());
			graph.setSolved(false);
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			for (Node node : graph.getNodes()) {
				if (selectedNode != null && node != selectedNode && DrawUtils.isWithinBounds(e, node.getCoord())) {
					Edge new_edge = new Edge(selectedNode, node);
					graph.addEdge(new_edge);
					graph.setSolved(false);

				}
			}
			selectedNode = null;
			hoveredNode = null;
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			hoveredNode = null;

			for (Node node : graph.getNodes()) {
				if (selectedNode == null && DrawUtils.isWithinBounds(e, node.getCoord())) {
					selectedNode = node;
				} else if (DrawUtils.isWithinBounds(e, node.getCoord())) {
					hoveredNode = node;
				}
			}

			if (selectedNode != null) {
				if (e.isControlDown()) {
					selectedNode.setCoord(e.getX(), e.getY());
					cursor = null;
					repaint();
					return;
				}

				cursor = new Point(e.getX(), e.getY());
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {

			if (e.isControlDown()) {
				hoveredNode = null;
				for (Node node : graph.getNodes()) {
					if (DrawUtils.isWithinBounds(e, node.getCoord())) {
						hoveredNode = node;
					}
				}
			}

			hoveredEdge = null;

			for (Edge edge : graph.getEdges()) {
				if (DrawUtils.isOnEdge(e, edge)) {
					hoveredEdge = edge;
				}
			}

			repaint();
		}

		public void reset() {
			graph.clear();
			selectedNode = null;
			hoveredNode = null;
			hoveredEdge = null;
			repaint();
		}
	}

}
