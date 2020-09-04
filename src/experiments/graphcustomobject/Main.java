package experiments.graphcustomobject;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Map<Integer, String>> rawNetwork = FileParser.readFile();
        Graph graph = new Graph();
        for (String line : rawNetwork.keySet()) {
            if ("#connections#".equals(line)) {
                continue;
            }
            String[] stations = new String[rawNetwork.get(line).size()];
            for (int i = 0; i < rawNetwork.get(line).size(); ++i) {
                stations[i] = rawNetwork.get(line).get(i + 1);
            }
            graph.addAndLink(line, stations);
            
        }
        addDataConnections(graph, rawNetwork.get("#connections#"));
    
        Scanner scn = new Scanner(System.in);
        
        while (true) {
            System.out.println("-------------------\nEnter two stations:");
            String s1 = scn.nextLine();
            String s2 = scn.nextLine();
            MetroStation origin = graph.getVertexByName(s1);
            if (origin == null) {
                System.out.println("Invalid station");
                continue;
            }
            LinkedList<MetroStation> path = graph.findPath(origin, s2);
            Graph.printPath(path);
        }
    }
    
    public static void addDataConnections(Graph g, Map<Integer, String> map) {
        for (Integer i : map.keySet()) {
            String[] data = map.get(i).split(":");
            MetroStation ms1 = new MetroStation(data[0], data[1]);
            MetroStation ms2 = new MetroStation(data[2], data[3]);
            g.oneWayLink(ms1, ms2);
        }
    }
}
