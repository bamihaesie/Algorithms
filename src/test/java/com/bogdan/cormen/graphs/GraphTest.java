package com.bogdan.cormen.graphs;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * @author bogdan
 */
public class GraphTest extends BaseFileTest {

    File graphFile = null;

    @Before
    public void setUp() {
        graphFile = loadResourceFile("/graph1.txt");
    }

    @Test
    public void testBuildUndirectedGraphFromFile() {
        Graph g = new Graph(graphFile, false);
        assertNotNull(g);
        assertVertexCount(g, 5);
        assertAdjList(g, 5, new int[]{ -1, 2, 4, 2, 3, 3 });
    }

    @Test
    public void testBuildDirectedGraphFromFile() {
        Graph g = new Graph(graphFile, true);
        assertNotNull(g);
        assertVertexCount(g, 5);
        assertAdjList(g, 5, new int[]{ -1, 2, 3, 1, 1, 0 });
    }

    @Test
    public void testBuildDirectedGraphSccFromFile() {
        graphFile = loadResourceFile("/graph2.txt");
        Graph g = new Graph(graphFile, true);
        assertNotNull(g);
        assertVertexCount(g, 8);
        assertAdjList(g, 8, new int[]{ -1, 1, 3, 1, 2, 2 });
    }

    private void assertVertexCount(Graph g, int count) {
        assertNotNull(g.getVertices());
        assertEquals(g.getVertices().size(), 1+count);
        assertTrue(g.getVertices().get(0) == null);
        for (int i=1; i<=count; i++) {
            assertEquals(new Integer(i), g.getVertices().get(i).getId());
        }
    }

    private void assertAdjList(Graph g, int count, int[] expectedSizes) {
        assertNotNull(g.getAdjList());
        assertEquals(1+count, g.getAdjList().size());
        for (int i=1; i<expectedSizes.length; i++) {
            List<Node> adjList = g.getAdjList().get(g.getVertices().get(i));
            assertNotNull("No node should have null adj list!", adjList);
            assertEquals(expectedSizes[i], adjList.size());
        }
    }

}
