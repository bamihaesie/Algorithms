package com.bogdan.cormen.graphs;

import java.util.*;

public class GraphOperations {

    private static boolean firstPass = true;
    private static Node s = null;
    private static Stack<Node> nodeStack = new Stack<Node>();

    public static void BFS(Graph g, Node s) {
        for (Node n : g.getVertices()) {
            if (n != null && !n.equals(s)) {
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

    public static List<Integer> computeSCCs(Graph g) {
        DFSLoop(g);
        firstPass = false;
        DFSLoop(g);
        Map<Node, Integer> sccMap = buildSccMap(g);
        return extractTopNSccs(sccMap, 5);
    }

    private static List<Integer> extractTopNSccs(Map<Node, Integer> sccMap, int howMany) {
        List<Integer> top = new ArrayList<Integer>();
        for (int i=0; i<howMany; i++) {
            int currentMax = 0;
            Node currentMaxNode = null;
            for (Node n : sccMap.keySet()) {
                Integer count = sccMap.get(n);
                if (count > currentMax) {
                    currentMax = count;
                    currentMaxNode = n;
                }
            }
            top.add(currentMax);
            sccMap.remove(currentMaxNode);
        }
        return top;
    }

    private static Map<Node, Integer> buildSccMap(Graph g) {
        Map<Node, Integer> sccMap = new HashMap<Node, Integer>();
        for (Node n : g.getVertices()) {
            if (n != null) {
                if (sccMap.containsKey(n.getParent())) {
                    Integer currentCount = sccMap.get(n.getParent());
                    sccMap.put(n.getParent(), currentCount+1    );
                } else {
                    sccMap.put(n.getParent(), 1);
                }
            }
        }
        return sccMap;
    }

    public static void DFSLoop(Graph g) {
        for (Node n : g.getVertices()) {
            if (n != null) {
                n.setExplored(false);
                n.setParent(null);
            }
        }
        if (firstPass) {
            for (Node v : g.getVertices()) {
                if (v != null && !v.isExplored()) {
                    DFS(g, v);
                }
            }
        } else {
            while (!nodeStack.empty()) {
                Node v = nodeStack.pop();
                if (!v.isExplored()) {
                    s = v;
                    DFS(g, v);
                }
            }
        }
    }

    public static void DFS(Graph g, Node u) {
        u.setExplored(true);
        if (!firstPass) {
            u.setParent(s);
        }
        for (Node v : g.getAdjListForNode(u, firstPass)) {
            if (!v.isExplored()) {
                DFS(g, v);
            }
        }
        if (firstPass) {
            nodeStack.push(u);
        }
    }

}
