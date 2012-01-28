package com.bogdan.cormen.graphs;

class Node implements Comparable<Node>{
    Integer id;
    NodeColor color;
    Integer distance;
    Node parent;

    public Node(Integer id) {
        this.id = id;
    }

    public int compareTo(Node otherNode) {
        return this.id.compareTo(otherNode.id);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Node) {
            Node otherNode = (Node)object;
            if (this.id == otherNode.id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
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
