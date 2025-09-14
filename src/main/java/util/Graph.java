package util;

import java.util.*;

public class Graph {
    private final Set<String> nodes = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();
    private final Map<String, Set<Edge>> outgoing = new HashMap<>();
    private final Map<String, Set<Edge>> incoming = new HashMap<>();
    public Graph() {

    }
    public Graph(int nodeCount) {
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(String.valueOf(i));
        }
    }
    public Graph(int[][] edgeMatrix) {
        for (int i = 0; i < edgeMatrix.length; i++) {
            nodes.add(String.valueOf(i));
        }

        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix[i].length; j++) {
                if (edgeMatrix[i][j] >= 0) {
                    addEdge(String.valueOf(i), String.valueOf(j), edgeMatrix[i][j]);
                }
            }
        }
    }
    public boolean addNode(String node) {
        return nodes.add(node);
    }
    public boolean addEdge(String from, String to, int weight) {
        if (weight < 0) throw new IllegalArgumentException("Weight can't be negative");
        if (!nodes.contains(from) || !nodes.contains(to)) return false;

        Edge e = new Edge(from, to, weight);
        edges.add(e);

        outgoing.computeIfAbsent(from, _ -> new HashSet<>()).add(e);
        incoming.computeIfAbsent(to, _ -> new HashSet<>()).add(e);

        return true;
    }
    public int nodeCount() {
        return nodes.size();
    }
    Set<Edge> outgoingEdges(String node) {
        return outgoing.getOrDefault(node, Collections.emptySet());
    }
    Set<Edge> incomingEdges(String node) {
        return incoming.getOrDefault(node, Collections.emptySet());
    }
    public int djikstra(String origin, String destination) {
        Map<String, Integer> distances = new HashMap<>();
        for (String node : nodes) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(origin, 0);

        record NodeDist(String node, int distance) {}
        PriorityQueue<NodeDist> pq = new PriorityQueue<>(Comparator.comparingInt(NodeDist::distance));
        pq.add(new NodeDist(origin, 0));

        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            NodeDist current = pq.poll();
            if (visited.contains(current.node())) continue;
            visited.add(current.node());

            if (current.node().equals(destination)) {
                return current.distance();
            }

            for (Edge edge : outgoingEdges(current.node())) {
                if (visited.contains(edge.to)) continue;
                int newDist = current.distance() + edge.weight;
                if (newDist < distances.get(edge.to)) {
                    distances.put(edge.to, newDist);
                    pq.add(new NodeDist(edge.to, newDist));
                }
            }
        }

        return distances.get(destination);
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        return this.equals((Edge) o);
    }

    public boolean equals(Edge edge) {
        return from.equals(edge.from) && to.equals(edge.to) && weight == edge.weight;
    }
}
