package com.bogdan.cormen.graphs;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author bogdan
 */
public class NodeTest {

    @Test
    public void createTest() {
        Node node = new Node(1);
        assertNotNull(node);
        assertEquals("Wrong id was set on the node!", 1L, (long)node.getId());
    }
    
    @Test
    public void isNotEqualTest() {
        Node node1 = createNode(1);
        Node node2 = createNode(2);
        assertFalse("Nodes should not be equal!", node1.equals(node2));
    }

    @Test
    public void isEqualTest() {
        Node node1 = createNode(1);
        Node node2 = createNode(1);
        assertTrue("Nodes should be equal!", node1.equals(node2));
    }
    
    private Node createNode(int id) {
        return new Node(id);
    }

}
