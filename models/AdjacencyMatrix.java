package models;

import java.util.LinkedList;
import java.util.List;

import gui.GraphDriver;

public class AdjacencyMatrix {
	private Graph graph;
	private int n;
	private int[][] adjMatrix;
	private boolean weighted = GraphDriver.weighted, unweighted = GraphDriver.unweighted, directed = GraphDriver.directed,
			undirected = GraphDriver.undirected;

	public AdjacencyMatrix(Graph graph) {
		this.graph = graph;
		n = graph.getCount() - 1;
		adjMatrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjMatrix[i][j] = 0;
			}
		}
	}

	public int[][] get() {
		for (Node node : graph.getNodes()) {
			for (Edge edge : graph.getEdges()) {
				if (edge.getNodeOne() == node || edge.getNodeTwo() == node) {
					if (undirected) {
						adjMatrix[edge.getNodeOne().getId() - 1][edge.getNodeTwo().getId() - 1] = edge.getWeight();
						adjMatrix[edge.getNodeTwo().getId() - 1][edge.getNodeOne().getId() - 1] = edge.getWeight();
					} else if (directed) {
						adjMatrix[edge.getNodeOne().getId() - 1][edge.getNodeTwo().getId() - 1] = edge.getWeight();
					}
				}
			}
		}
		return adjMatrix;
	}

	public String print() {
		String s = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s = s + get()[i][j] + "          ";
			}
			s += "\n";
		}
		return s;
	}

}
