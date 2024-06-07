import java.util.*;

public class Graph {
    private final Set<String> nodes = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();
    public Graph(int[][] edgeMatrix) {
        for (int i = 0; i < edgeMatrix.length; i++) {
            nodes.add(String.valueOf(i));
        }

        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix[i].length; j++) {
                if (edgeMatrix[i][j] >= 0) {
                    edges.add(new Edge(String.valueOf(i), String.valueOf(j), edgeMatrix[i][j]));
                }
            }
        }
    }
    public Graph(int nodeCount) {
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(String.valueOf(i));
        }
    }
    public int nodeCount() {
        return nodes.size();
    }
    Set<Edge> outgoingEdges(String node) {
        Set<Edge> outgoingEdges = new HashSet<>();

        for (Edge currentEdge : edges) {
            if (currentEdge.from.equals(node))
                outgoingEdges.add(currentEdge);
        }

        return outgoingEdges;
    }
    Set<Edge> incomingEdges(String node) {
        Set<Edge> incomingEdges = new HashSet<>();

        for (Edge currentEdge : edges) {
            if (currentEdge.to.equals(node))
                incomingEdges.add(currentEdge);
        }

        return incomingEdges;
    }

    public int djikstra(String origin, String destination) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : nodes) {
            distances.put(node, -1);
        }
        distances.put(origin, 0);

        Set<String> visited = new HashSet<>();

        while (visited.size() < nodes.size()) {
            String node = nextClosestNode(distances, visited);
            if (node == null) break;
            if (node.equals(destination))
                break;
            visited.add(node);
            updateDistances(distances, visited, node);
        }

        return distances.get(destination);
    }

    private String nextClosestNode(Map<String, Integer> distances, Set<String> visited) {
        String closestNode = null;

        for (String node : distances.keySet()) {
            if (distances.get(node) < 0) continue;
            if (visited.contains(node)) continue;

            if (closestNode == null || distances.get(closestNode) > distances.get(node))
                closestNode = node;
        }

        return closestNode;
    }

    private void updateDistances(Map<String, Integer> distances, Set<String> visited, String node) {
        int distance = distances.get(node);
        Set<Edge> edges = outgoingEdges(node);

        for (Edge edge : edges) {
            if (visited.contains(edge.to)) continue;

            int neighborDistance = distance + edge.weight;
            if (distances.get(edge.to) == -1 || distances.get(edge.to) > neighborDistance)
                distances.put(edge.to, neighborDistance);
        }
    }
}

class Edge {
    String  from;
    String  to;
    int weight;
    public Edge(String  from, String  to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
