package com.bogdan.cormen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bogdan
 */
public class Graph {

    private boolean directed;
    public List<Node> V;
    public Map<Node, List<Node>> E;

    public Graph(File inputFile, boolean directed) {
        this.directed = directed;
        try {
            buildFromFile(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Node> getAdjListForNode(Node node) {
        return E.get(node);
    }

    public boolean isEdge(Node u, Node v) {
        List<Node> adjListForU = getAdjListForNode(u);
        if (adjListForU.contains(v)) {
            return true;
        }
        return false;
    }

    public void buildFromFile(File inputFile) throws IOException {

        int numberOfVertices;
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        String line = br.readLine();
        numberOfVertices = Integer.parseInt(line.trim());

        V = new ArrayList<Node>();
        V.add(null);
        for (int i=1; i <= numberOfVertices; i++) {
            V.add(new Node(i));
        }

        E = new HashMap<Node, List<Node>>();
        while ((line = br.readLine()) != null ) {
            String[] tokens = line.split(" ");
            Integer uId = Integer.valueOf(tokens[0]);
            Integer vId = Integer.valueOf(tokens[1]);

            Node u = V.get(uId);
            Node v = V.get(vId);

            if (E.containsKey(u)) {
                E.get(u).add(v);
            } else {
                List<Node> adjList = new ArrayList<Node>();
                adjList.add(v);
                E.put(u, adjList);
            }

            if (directed == false) {
                if (E.containsKey(v)) {
                    E.get(v).add(u);
                } else {
                    List<Node> adjList = new ArrayList<Node>();
                    adjList.add(u);
                    E.put(v, adjList);
                }
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("V = [");
        if (V != null) {
            for (Node v : V) {
                if (v != null) {
                    sb.append(v);
                    if (v.id != V.size() - 1) {
                        sb.append(", ");
                    }
                }
            }
        }
        sb.append("]\n");

        sb.append("E = [");
        if (E != null) {
            for (Node key : E.keySet()) {
                List<Node> adjList = E.get(key);
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

}

class Node {
    Integer id;
    NodeColor color;
    Integer distance;
    Node parent;

    public Node(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" + id;
    }


}

enum NodeColor {
    WHITE,
    GREY,
    BLACK
}

