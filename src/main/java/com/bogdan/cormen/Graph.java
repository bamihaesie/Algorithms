package com.bogdan.cormen;

import sun.net.idn.StringPrep;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.fail;

/**
 * @author bogdan
 */
public class Graph {

    private boolean directed;
    public List<Integer> V;
    public Map<Integer, List<Integer>> E;

    public Graph(File inputFile, boolean directed) {
        this.directed = directed;
        try {
            buildFromFile(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getAdjListForNode(Integer id) {
        return E.get(id);
    }

    public boolean isEdge(Integer u, Integer v) {
        List<Integer> adjListForU = getAdjListForNode(u);
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

        V = new ArrayList<Integer>();
        V.add(null);
        for (int i=1; i <= numberOfVertices; i++) {
            V.add(new Integer(i));
        }

        E = new HashMap<Integer, List<Integer>>();
        while ((line = br.readLine()) != null ) {
            String[] tokens = line.split(" ");
            Integer u = Integer.valueOf(tokens[0]);
            Integer v = Integer.valueOf(tokens[1]);

            if (E.containsKey(V.get(u))) {
                E.get(V.get(u)).add(v);
            } else {
                List<Integer> adjList = new ArrayList<Integer>();
                adjList.add(v);
                E.put(u, adjList);
            }

            if (directed == false) {
                if (E.containsKey(V.get(v))) {
                    E.get(V.get(v)).add(u);
                } else {
                    List<Integer> adjList = new ArrayList<Integer>();
                    adjList.add(u);
                    E.put(v, adjList);
                }
            }
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("V = [");
        if (V != null) {
            for (Integer v : V) {
                if (v != null) {
                    sb.append(v);
                    if (v != V.size() - 1) {
                        sb.append(", ");
                    }
                }
            }
        }
        sb.append("]\n");

        sb.append("E = [");
        if (E != null) {
            for (Integer key : E.keySet()) {
                List<Integer> adjList = E.get(key);
                for (Integer adj : adjList) {
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
