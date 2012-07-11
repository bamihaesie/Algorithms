package com.bogdan.cormen.graphs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GraphOperationsTest extends BaseFileTest {

    @Test
    public void testComputeSCCs() throws Exception {
        Graph g = new Graph(loadResourceFile("/graph2.txt"), true);
        List<Integer> top5 = GraphOperations.computeSCCs(g);
        List<Node> nodes = g.getVertices();
        assertNodeAreInSameScc(nodes.get(1), nodes.get(2), nodes.get(5));
        assertNodeAreInSameScc(nodes.get(3), nodes.get(4), nodes.get(8));
        assertNodeAreInSameScc(nodes.get(6), nodes.get(7));
        int[] expectedTop5Components = new int[] {3, 3, 2, 0, 0};
        for (int i=0; i<expectedTop5Components.length; i++) {
            assertEquals(expectedTop5Components[i], top5.get(i).intValue());
        }
    }

    private void assertNodeAreInSameScc(Node... nodes) {
        assertNotNull(nodes);
        if (nodes.length > 1) {
            assertNotNull("Node 0 does not belong to any SCC!", nodes[0].getParent());
            Node parent = nodes[0].getParent();
            for (int i=1; i<nodes.length; i++) {
                assertNotNull("Node " + nodes[i].getId() + " does not belong to any SCC!", nodes[i].getParent());
                assertEquals("Nodes don't belong to the same SCC!", parent, nodes[i].getParent());
            }
        }
    }
}
