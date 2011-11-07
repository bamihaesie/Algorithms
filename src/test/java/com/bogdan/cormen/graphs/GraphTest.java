package com.bogdan.cormen.graphs;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Unit test for Graph
 */
public class GraphTest {

    File graphFile1 = null;

    @Before
    public void setUp() {
        URL url = this.getClass().getResource("/graph1.txt");
        assertNotNull("Can't open input file!", url);
        graphFile1 = new File(url.getFile());
    }

    @Test
    public void testBuildUndirectedGraphFromFile() {
        Graph g = new Graph(graphFile1, false);
        assertNotNull(g);

        System.out.println(g);

        assertNotNull(g.V);
        assertEquals(g.V.size(), 1+5);
        assertTrue(g.V.get(0) == null);
        for (int i=1; i<=5; i++) {
            assertEquals(new Integer(i), g.V.get(i).id);
        }

        assertNotNull(g.E);
        assertEquals(5, g.E.size());
        int[] expectedSizes = { -1, 2, 4, 2, 3, 3 };
        for (int i=1; i<=5; i++) {
            List<Node> adjList = g.E.get(g.V.get(i));
            assertEquals(expectedSizes[i], adjList.size());
        }
    }

    @Test
    public void testBuildDirectedGraphFromFile() {
        Graph g = new Graph(graphFile1, true);
        assertNotNull(g);

        System.out.println(g);

        assertNotNull(g.V);
        assertEquals(g.V.size(), 1+5);
        assertTrue(g.V.get(0) == null);
        for (int i=1; i<=5; i++) {
            assertEquals(new Integer(i), g.V.get(i).id);
        }

        assertNotNull(g.E);
        assertEquals(4, g.E.size());
        int[] expectedSizes = { -1, 2, 3, 1, 1 };
        for (int i=1; i<=4; i++) {
            List<Node> adjList = g.E.get(g.V.get(i));
            assertEquals(expectedSizes[i], adjList.size());
        }
    }
}
