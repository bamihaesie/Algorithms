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
    private Map<Node, List<Node>> reverseAdjList;

    public Graph(File inputFile, boolean directed) {
        vertices = new ArrayList<Node>();
        adjList = new HashMap<Node, List<Node>>();
        reverseAdjList = new HashMap<Node, List<Node>>();
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
        addEmptyListForSinkNodes();
    }

    private void addEmptyListForSinkNodes() {
        List<Node> emptyNodeList = new ArrayList<Node>();
        for (Node vertex : vertices) {
            if (adjList.get(vertex) == null) {
                adjList.put(vertex, emptyNodeList);
            }
            if (reverseAdjList.get(vertex) == null) {
                reverseAdjList.put(vertex, emptyNodeList);
            }
        }
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
        String line;
        while ((line = bufferedReader.readLine()) != null ) {
            Edge edge = readEdgeFromFileLine(line);
            if (directed) {
                addEdgeToAdjList(adjList, edge.getU(), edge.getV());
                addEdgeToAdjList(reverseAdjList, edge.getV(), edge.getU());
            } else {
                addEdgeToAdjList(adjList, edge.getV(), edge.getU());
                addEdgeToAdjList(adjList, edge.getU(), edge.getV());
            }
        }
    }
    
    private Edge readEdgeFromFileLine(String fileLine) {
        String[] tokens = fileLine.split(" ");
        Node u = vertices.get(Integer.valueOf(tokens[0]));
        Node v = vertices.get(Integer.valueOf(tokens[1]));
        return new Edge(u, v);
    }
    
    private void addEdgeToAdjList(Map<Node, List<Node>> adjList, Node u, Node v) {
        if (adjList.containsKey(u)) {
            adjList.get(u).add(v);
        } else {
            List<Node> neighbors = new ArrayList<Node>();
            neighbors.add(v);
            adjList.put(u, neighbors);
        }
    }

    public List<Node> getAdjListForNode(Node node) {
        return adjList.get(node);
    }

    public List<Node> getAdjListForNode(Node node, boolean reverseEdges) {
        if (reverseEdges) {
            return reverseAdjList.get(node);
        }
        return adjList.get(node);
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
                    sb.append(key).append("->").append(adj).append(", ");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");

        return sb.toString();
    }

}
