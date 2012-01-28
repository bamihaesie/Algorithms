package com.bogdan.cormen.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author bogdan
 */
public class Graph {

    private boolean directed;
    private List<Node> vertices;
    private Map<Node, List<Node>> adjList;

    public Graph(File inputFile, boolean directed) {
        vertices = new ArrayList<Node>();
        adjList = new HashMap<Node, List<Node>>();
        this.setDirected(directed);
        try {
            buildGraphFromFile(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildGraphFromFile(File inputFile) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new FileReader(inputFile));
        buildVerticesArray(bufferedReader);
        buildAdjList(bufferedReader);
    }

    private void buildVerticesArray(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        int numberOfVertices = Integer.parseInt(line.trim());
        vertices.add(null);
        for (int i=1; i <= numberOfVertices; i++) {
            vertices.add(new Node(i));
        }
    }

    private void buildAdjList(BufferedReader bufferedReader) throws IOException {
        String line = "";
        while ((line = bufferedReader.readLine()) != null ) {
            Edge edge = readEdgeFromFileLine(line);
            addEdgeToAdjList(edge.getU(), edge.getV());
            if (directed == false) {
                addEdgeToAdjList(edge.getV(), edge.getU());
            }
        }
    }
    
    private Edge readEdgeFromFileLine(String fileLine) {
        String[] tokens = fileLine.split(" ");
        Node u = vertices.get(Integer.valueOf(tokens[0]));
        Node v = vertices.get(Integer.valueOf(tokens[1]));
        return new Edge(u, v);
    }
    
    private void addEdgeToAdjList(Node u, Node v) {
        if (adjList.containsKey(u)) {
            adjList.get(u).add(v);
        } else {
            List<Node> neighbors = new ArrayList<Node>();
            neighbors.add(v);
            this.adjList.put(u, neighbors);
        }
    }

    public List<Node> getAdjListForNode(Node node) {
        return adjList.get(node);
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public List<Node> getVertices() {
        return vertices;
    }

    public Map<Node, List<Node>> getAdjList() {
        return adjList;
    }

    public boolean isEdge(Node u, Node v) {
        List<Node> adjListForU = getAdjListForNode(u);
        if (adjListForU.contains(v)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("V = [");
        if (vertices != null) {
            for (Node v : vertices) {
                if (v != null) {
                    sb.append(v);
                    if (v.getId() != vertices.size() - 1) {
                        sb.append(", ");
                    }
                }
            }
        }
        sb.append("]\n");

        sb.append("adjList = [");
        if (adjList != null) {
            for (Node key : adjList.keySet()) {
                List<Node> adjList = this.adjList.get(key);
                for (Node adj : adjList) {
                    sb.append(key + "->" + adj);
                    sb.append(", ");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");

        return sb.toString();
    }

    public static void BFS(Graph g, Node s) {
        for (Node n : g.vertices) {
            if (!n.equals(s)) {
                n.setColor(NodeColor.WHITE);
                n.setDistance(Integer.MAX_VALUE);
                n.setParent(null);
            }
        }

        s.setColor(NodeColor.GREY);
        s.setDistance(0);
        s.setParent(null);

        Queue<Node> Q = new LinkedList<Node>();
        Q.offer(s);

        while (!Q.isEmpty()) {
            Node u = Q.element();
            for(Node v : g.getAdjListForNode(u)) {
                if (v.getColor() == NodeColor.WHITE) {
                    v.setColor(NodeColor.GREY);
                    v.setDistance(u.getDistance() + 1);
                    v.setParent(u);
                    Q.offer(v);
                }
            }
            u.setColor(NodeColor.BLACK);
        }
    }
}

class Edge {

    private Node u, v;

    Edge (Node u, Node v) {
        this.u = u;
        this.v = v;
    }

    public Node getU() {
        return u;
    }

    public Node getV() {
        return v;
    }
}
