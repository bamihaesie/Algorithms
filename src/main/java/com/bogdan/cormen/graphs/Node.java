package com.bogdan.cormen.graphs;

/**
 * @author bogdan
 */
class Node implements Comparable<Node> {

    private Integer id;
    private NodeColor color;
    private Integer distance;
    private Node parent;
    private boolean explored;

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public Node(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int compareTo(Node otherNode) {
        return this.getId().compareTo(otherNode.getId());
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (anotherObject instanceof Node) {
            Node otherNode = (Node)anotherObject;
            if (this.getId().equals(otherNode.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "" + getId();
    }
}


