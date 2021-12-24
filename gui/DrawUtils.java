package gui;

import models.Edge;
import models.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class DrawUtils {
	private Graphics2D g;
	private static int radius = 20;
	private boolean weighted = GraphDriver.weighted, unweighted = GraphDriver.unweighted, directed = GraphDriver.directed,
			undirected = GraphDriver.undirected;
	

	public DrawUtils(Graphics2D graphics2D) {
		g = graphics2D;
	}

	// check gioi han cua 2 not
	public static boolean isWithinBounds(MouseEvent e, Point p) {
		int x = e.getX();
		int y = e.getY();

		int boundX = (int) p.getX();
		int boundY = (int) p.getY();

		return (x <= boundX + radius && x >= boundX - radius) && (y <= boundY + radius && y >= boundY - radius);
	}

	// check 2 not co trung nhau k
	public static boolean isOverlapping(MouseEvent e, Point p) {
		int x = e.getX();
		int y = e.getY();

		int boundX = (int) p.getX();
		int boundY = (int) p.getY();

		return (x <= boundX + 2.5 * radius && x >= boundX - 2.5 * radius) && (y <= boundY + 2.5 * radius && y >= boundY - 2.5 * radius);
	}

	// check canh
	public static boolean isOnEdge(MouseEvent e, Edge edge) {

		int dist = distToSegment(e.getPoint(), edge.getNodeOne().getCoord(), edge.getNodeTwo().getCoord());
		if (dist < 6)
			return true;
		return false;
	}

	public static Color parseColor(String colorStr) {
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16),
				Integer.valueOf(colorStr.substring(5, 7), 16));
	}

	// ve trong so
	public void drawWeight(Edge edge) {
		Point from = edge.getNodeOne().getCoord();
		Point to = edge.getNodeTwo().getCoord();
		int x = (from.x + to.x) / 2;
		int y = (from.y + to.y) / 2;

		int rad = radius / 2;
		g.fillOval(x - rad, y - rad, 2 * rad, 2 * rad);
		drawWeightText(String.valueOf(edge.getWeight()), x, y);
	}

	// ve duong di ngan nhat
	public void drawPath(List<Node> path) {
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < path.size() - 1; i++) {
			edges.add(new Edge(path.get(i), path.get(i + 1)));
		}

		for (Edge edge : edges) {
			drawPath(edge);
		}
	}

	// to mau duong di ngan nhat
	public void drawPath(Edge edge) {
		g.setColor(parseColor("#00BCD4"));// xanh
		drawWeightBoldEdge(edge);
	}

	public void drawWeightHoveredEdge(Edge edge) {
		g.setColor(parseColor("#E1BEE7"));// tim
		drawWeightBoldEdge(edge);
	}
	
	public void drawUnweightHoveredEdge(Edge edge) {
		g.setColor(parseColor("#E1BEE7"));// tim
		drawUnweightBoldEdge(edge);
	}

	// ve canh
	private void drawWeightBoldEdge(Edge edge) {
		Point from = edge.getNodeOne().getCoord();
		Point to = edge.getNodeTwo().getCoord();
		g.setStroke(new BasicStroke(8));
		g.drawLine(from.x, from.y, to.x, to.y);
		int x = (from.x + to.x) / 2;
		int y = (from.y + to.y) / 2;

		int rad = 13;
		g.fillOval(x - rad, y - rad, 2 * rad, 2 * rad);
	}

	private void drawUnweightBoldEdge(Edge edge) {
		Point from = edge.getNodeOne().getCoord();
		Point to = edge.getNodeTwo().getCoord();
		g.setStroke(new BasicStroke(8));
		g.drawLine(from.x, from.y, to.x, to.y);
		int x = (from.x + to.x) / 2;
		int y = (from.y + to.y) / 2;

		int rad = 13;
		g.fillOval(x - rad, y - rad, 0, 0);
	}

	// canh co trong so
	public void drawWeightEdge(Edge edge) {
		g.setColor(parseColor("#555555"));// xanh xam
		drawBaseEdge(edge);
		drawWeight(edge);
	}

	public void drawUnweightEdge(Edge edge) {
		g.setColor(parseColor("#555555"));// xanh xam
		drawBaseEdge(edge);
	}
	
	
	public void drawArrow(Point tip, Point tail) {
		double phi = Math.toRadians(40);;
		int barb = 10;

        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        //System.out.println("theta = " + Math.toDegrees(theta));
        double x, y, rho = theta + phi;
        for(int j = 0; j < 2; j++)
        {
            x = tip.x  - barb * Math.cos(rho);
            y = tip.y - barb * Math.sin(rho);
            g.draw(new Line2D.Double(tip.x, tip.y, x, y));
            rho = theta - phi;
        }
	}

	// ve canh
	public void drawBaseEdge(Edge edge) {
		Point from = edge.getNodeOne().getCoord();
		Point to = edge.getNodeTwo().getCoord();
		g.setStroke(new BasicStroke(3));
		g.drawLine(from.x, from.y, to.x, to.y);
		if(directed) {
			Point halfPoint = new Point((from.x + to.x) / 2, (from.y + to.y) / 2);
			Point quarterPoint = new Point((halfPoint.x + to.x) / 2, (halfPoint.y + to.y) / 2);
		
			drawArrow(quarterPoint, from);
		}
	}

	public void drawHalo(Node node) {
		g.setColor(parseColor("#E91E63"));// do
		radius += 5;
		g.fillOval(node.getX() - radius, node.getY() - radius, 2 * radius, 2 * radius);
		radius -= 5;
	}

	public void drawSourceNode(Node node) {
		g.setColor(parseColor("#00BCD4"));// xanh
		g.fillOval(node.getX() - radius, node.getY() - radius, 2 * radius, 2 * radius);

		radius -= 5;
		g.setColor(parseColor("#B2EBF2"));
		g.fillOval(node.getX() - radius, node.getY() - radius, 2 * radius, 2 * radius);

		radius += 5;
		g.setColor(parseColor("#00BCD4"));
		drawCentreText(String.valueOf(node.getId()), node.getX(), node.getY());
	}

	public void drawDestinationNode(Node node) {
		g.setColor(parseColor("#F44336"));// do
		g.fillOval(node.getX() - radius, node.getY() - radius, 2 * radius, 2 * radius);

		radius -= 5;
		g.setColor(parseColor("#FFCDD2"));
		g.fillOval(node.getX() - radius, node.getY() - radius, 2 * radius, 2 * radius);

		radius += 5;
		g.setColor(parseColor("#F44336"));
		drawCentreText(String.valueOf(node.getId()), node.getX(), node.getY());
	}

	public void drawNode(Node node) {
		g.setColor(parseColor("#9C27B0"));// tim
		g.fillOval(node.getX() - radius, node.getY() - radius, 2 * radius, 2 * radius);

		radius -= 5;
		g.setColor(parseColor("#E1BEE7"));
		g.fillOval(node.getX() - radius, node.getY() - radius, 2 * radius, 2 * radius);

		radius += 5;
		g.setColor(parseColor("#9C27B0"));
		drawCentreText(String.valueOf(node.getId()), node.getX(), node.getY());
	}

	public void drawWeightText(String text, int x, int y) {
		g.setColor(parseColor("#cccccc"));// xam
		FontMetrics fm = g.getFontMetrics();
		double t_width = fm.getStringBounds(text, g).getWidth();
		g.drawString(text, (int) (x - t_width / 2), (y + fm.getMaxAscent() / 2));
	}

	public void drawCentreText(String text, int x, int y) {
		FontMetrics fm = g.getFontMetrics();
		double t_width = fm.getStringBounds(text, g).getWidth();
		g.drawString(text, (int) (x - t_width / 2), (y + fm.getMaxAscent() / 2));
	}

	// Calculations
	private static int sqr(int x) {
		return x * x;
	}

	private static int dist2(Point v, Point w) {
		return sqr(v.x - w.x) + sqr(v.y - w.y);
	}

	private static int distToSegmentSquared(Point p, Point v, Point w) {
		double l2 = dist2(v, w);
		if (l2 == 0)
			return dist2(p, v);
		double t = ((p.x - v.x) * (w.x - v.x) + (p.y - v.y) * (w.y - v.y)) / l2;
		if (t < 0)
			return dist2(p, v);
		if (t > 1)
			return dist2(p, w);
		return dist2(p, new Point((int) (v.x + t * (w.x - v.x)), (int) (v.y + t * (w.y - v.y))));
	}

	private static int distToSegment(Point p, Point v, Point w) {
		return (int) Math.sqrt(distToSegmentSquared(p, v, w));
	}

}
