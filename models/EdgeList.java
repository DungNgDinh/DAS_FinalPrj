package models;

import java.util.ArrayList;

import gui.GraphDriver;

public class EdgeList {
	private Graph graph;
	private int[][] a;

	private boolean weighted = GraphDriver.weighted, unweighted = GraphDriver.unweighted, directed = GraphDriver.directed,
			undirected = GraphDriver.undirected;

	public EdgeList(Graph graph) {
		this.graph = graph;
		AdjacencyMatrix am = new AdjacencyMatrix(graph);
		a = am.get();
	}

	public String print() {
		String s = "";
		int count = 1;
		for (int i = 0; i < convert().size(); i++) {
			if (weighted) {
				for (Integer u : convert().get(i)) {
					s = s + count++ +":     ";
					s = s + (i + 1) + "          " + (u + 1) + "          " + a[i][u] + "\n";
				}
			} else if (unweighted) {
				for (Integer u : convert().get(i)) {
					s = s + count++ +":     ";
					s = s + (i + 1) + "          " + (u + 1) + "\n";
				}
			}
			//s += "\n";
		}
		return s;
	}

	public ArrayList<ArrayList<Integer>> convert() {
		int l = a[0].length;
		ArrayList<ArrayList<Integer>> adjListArray = new ArrayList<ArrayList<Integer>>(l);

		for (int i = 0; i < l; i++) {
			adjListArray.add(new ArrayList<Integer>());
		}

		int i, j;
		for (i = 0; i < a[0].length; i++) {
			for (j = 0; j < a.length; j++) {
				if (a[i][j] != 0) {
					adjListArray.get(i).add(j);
				}
			}
		}

		return adjListArray;
	}
}
