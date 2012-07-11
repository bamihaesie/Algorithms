package com.bogdan.cormen.graphs;

public class Edge {

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
