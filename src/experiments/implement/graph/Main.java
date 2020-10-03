package experiments.implement.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        testDjirkaWithDestination();
    }
    
    public static void testDjirkaWithDestination() {
        String[] lines = null;
        try (BufferedReader br = new BufferedReader(new FileReader(new File("box/g2.txt")))) {
            lines = br.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        for (String line : lines) {
            graph.addEdge(line);
        }
        System.out.println(graph);
        GraphUtil<String> util = new GraphUtil<>(graph);
        util.breadthFirstSearch("A");
        System.out.println(util.getPathTo("E"));
    }
    
    public static void testDirected() {
        String[] lines = null;
        try (BufferedReader br = new BufferedReader(new FileReader(new File("box/g1.txt")))) {
            lines = br.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DirectedGraph<String> graph = new DirectedGraph<>();
        for (String line : lines) {
            graph.addEdge(line);
        }
        graph.addEdge("F", "J", 1.0);
        
        System.out.println(graph + "\n-------------------");
        GraphUtil<String> util = new GraphUtil<>(graph);
        util.dijkstraSearch("A");
        System.out.println("Source: A");
        System.out.println(util.getPathTo("F"));
        System.out.println(util.getPathTo("H"));
        System.out.println(util.getPathTo("I"));
        util.dijkstraSearch("G");
        System.out.println("Source: G");
        System.out.println(util.getPathTo("F"));
        System.out.println(util.getPathTo("H"));
        System.out.println(util.getPathTo("I"));
        util.dijkstraSearch("J");
        System.out.println(util.getPathTo("A"));
    }
    
    public static void testDijkstra() {
        UndirectedGraph<String> graph = new UndirectedGraph<>();
        graph.addEdge("A", "B", 9.2);
        graph.addEdge("A", "D", 0.8);
        graph.addEdge("A", "I", 0.1);
        graph.addEdge("B", "C", 0.1);
        graph.addEdge("B", "E", 0.1);
        graph.addEdge("C", "H", 0.1);
        graph.addEdge("D", "E", 0.3);
        graph.addEdge("E", "F", 0.8);
        graph.addEdge("E", "K", 0.1);
        graph.addEdge("F", "G", 0.3);
        graph.addEdge("G", "H", 0.3);
        graph.addEdge("H", "K", 2.5);
        graph.addEdge("I", "J", 0.1);
        graph.addEdge("J", "K", 0.1);
        //System.out.println(graph);
        GraphUtil<String> util = new GraphUtil<>(graph);
        util.dijkstraSearch("A");
        System.out.println(util.getPathTo("G"));
    }
    
    public static void simpleTest() {
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
        System.out.println("In degree: " + dGraph.inDegreeOf("Zero"));
        System.out.println("Out degree: " + dGraph.outDegreeOf("Zero"));
    
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
        System.out.println("In degree: " + uGraph.inDegreeOf("Zero"));
        System.out.println("Out degree: " + uGraph.outDegreeOf("Zero"));
        uGraph.removeEdge("Five", "Zero");
        System.out.println(uGraph);
        System.out.println("Nodes: " + uGraph.getNodeCount() + ", Edges: " + uGraph.getEdgeCount());
        System.out.println("In degree: " + uGraph.inDegreeOf("Zero"));
    }
}
