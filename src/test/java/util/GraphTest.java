package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    Graph[] graphs = new Graph[4];
    @BeforeEach
    void makeGraphs() {
        graphs[0] = new Graph();

        graphs[1] = new Graph(1);

        graphs[2] = new Graph(1);
        assertFalse(graphs[2].addNode("0"));
        assertTrue(graphs[2].addNode("1"));
        assertThrows(IllegalArgumentException.class, () -> graphs[2].addEdge("0", "0", -1));
        assertFalse(graphs[2].addEdge("0", "2", 1));
        assertFalse(graphs[2].addEdge("-1", "1", 1));
        assertTrue(graphs[2].addEdge("0", "1", 1));
        assertTrue(graphs[2].addEdge("1", "0", 1));

        int[][] edgeMatrix3 = {{-1, -1, 1}, {-1, -1, -1}, {1, 1, -1}};
        graphs[3] = new Graph(edgeMatrix3);
    }
    @Test
    void nodeCount() {
        int[] nodeCount = {0, 1, 2, 3};
        for (int i = 0; i < graphs.length; i++) {
            assertEquals(nodeCount[i], graphs[i].nodeCount());
        }
    }
    @Test
    void outgoingEdges() {
        int[] outgoingEdges = {0, 1, 2};
        for (int i = 1; i < graphs.length; i++) {
            assertEquals(outgoingEdges[i-1], graphs[i].outgoingEdges(String.valueOf(i-1)).size());
        }
    }
    @Test
    void incomingEdges() {
        int[] incomingEdges = {0, 1, 1};
        for (int i = 1; i < graphs.length; i++) {
            assertEquals(incomingEdges[i-1], graphs[i].incomingEdges(String.valueOf(i-1)).size());
        }
    }
    @Test
    void djikstra() {
        assertEquals(1, graphs[2].djikstra("0", "1"));
        assertEquals(2, graphs[3].djikstra("0", "1"));
        assertEquals(-1, graphs[3].djikstra("1", "0"));

        int[][] edgeMatrix = {{-1, 1, -1, -1, -1}, {-1, -1, 3, -1, -1}, {5, -1, -1, -1, 1}, {-1, -1, -1, -1, -1}, {2, 7, -1, -1, -1}};
        Graph bigGraph = new Graph(edgeMatrix);
        assertEquals(0, bigGraph.djikstra("0", "0"));
        assertEquals(4, bigGraph.djikstra("0", "2"));
        assertEquals(6, bigGraph.djikstra("1", "0"));
        assertEquals(-1, bigGraph.djikstra("3", "0"));
        assertEquals(3, bigGraph.djikstra("4", "1"));
    }
    @Test
    void edgeCompare() {
        Edge edge1 = new Edge("a", "b", 1);
        Edge edge2 = new Edge("a", "b", 1);
        assertEquals(edge1, edge2);
    }
}