package experiments.implement.graph;

public class Main {
    public static void main(String[] args) {
        DirectedGraph<String> dGraph = new DirectedGraph<>();
        dGraph.addEdge("Zero", "One", 1.5);
        dGraph.addEdge("One", "Zero", 1.5);
        dGraph.addEdge("One", "Two", 0.7);
        dGraph.addEdge("Two", "Three", 1.9);
        dGraph.addEdge("Three", "Four", 1.1);
        dGraph.addEdge("Four", "One", 1.7);
        dGraph.addEdge("Four", "Five", 0.9);
        dGraph.addEdge("Five", "Four", 1.1);
        dGraph.addEdge("Five", "Zero", 2.0);
        System.out.println(dGraph);
        System.out.println("Nodes: " + dGraph.getNodeCount() + ", Edges: " + dGraph.getEdgeCount());
        System.out.println(dGraph.inDegreeOf("Zero"));
        System.out.println(dGraph.outDegreeOf("Zero"));
    
        UndirectedGraph<String> uGraph = new UndirectedGraph<>();
        uGraph.addEdge("Zero", "One");
        uGraph.addEdge("One", "Zero");
        uGraph.addEdge("One", "Two");
        uGraph.addEdge("Two", "Three");
        uGraph.addEdge("Three", "Four");
        uGraph.addEdge("Four", "One");
        uGraph.addEdge("Four", "Five");
        uGraph.addEdge("Five", "Four");
        uGraph.addEdge("Five", "Zero");
        System.out.println(uGraph);
        System.out.println("Nodes: " + uGraph.getNodeCount() + ", Edges: " + uGraph.getEdgeCount());
        System.out.println(uGraph.inDegreeOf("Zero"));
        System.out.println(uGraph.outDegreeOf("Zero"));
    }
}
