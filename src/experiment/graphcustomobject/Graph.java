package experiment.graphcustomobject;

import java.util.*;

public class Graph {
    private Map<MetroStation, List<MetroStation>> adj;
    
    public Graph() {
        adj = new HashMap<>();
    }
    
    public void addVertex(MetroStation ms) {
        if (ms == null) {
            return;
        }
        adj.putIfAbsent(ms, new ArrayList<>());
        adj.get(ms).add(ms);
    }
    
    public MetroStation getVertexByName(String name) {
        for (MetroStation ms : adj.keySet()) {
            if (ms.getStationName().equals(name)) {
                return ms;
            }
        }
        return null;
    }
    
    public void addAndLink(String line, String[] stations) {
        MetroStation previous = null;
        for (String station : stations) {
            MetroStation ms = new MetroStation(line, station);
            addVertex(ms);
            link(previous, ms);
            previous = ms;
        }
    }
    
    private boolean notValidVertex(MetroStation ms) {
        return ms == null || !adj.containsKey(ms);
    }
    
    public void oneWayLink(MetroStation ms1, MetroStation ms2) {
        if (notValidVertex(ms1) || notValidVertex(ms2)) {
            return;
        }
        ms1 = adj.get(ms1).get(0);
        ms2 = adj.get(ms2).get(0);
        adj.get(ms1).add(ms2);
    }
    
    public void link(MetroStation ms1, MetroStation ms2) {
        if (notValidVertex(ms1) || notValidVertex(ms2)) {
            return;
        }
        ms1 = adj.get(ms1).get(0);
        ms2 = adj.get(ms2).get(0);
        adj.get(ms1).add(ms2);
        adj.get(ms2).add(ms1);
    }
    
    public LinkedList<MetroStation> findPath(MetroStation start, String dest) {
        // Performs BFS and backtraces using parent Mapping
        
        LinkedList<MetroStation> path = new LinkedList<>();
        LinkedList<MetroStation> queue = new LinkedList<>();
        Map<MetroStation, MetroStation> parents = new HashMap<>();
        Set<MetroStation> visited = new HashSet<>();
        queue.addLast(start);
        
        // BFS
        while (!queue.isEmpty()) {
            MetroStation parent = queue.removeFirst();
            visited.add(parent);
            for (MetroStation vertex : adj.get(parent)) {
                if (visited.contains(vertex)) {
                    continue;
                }
                queue.addLast(vertex);
                parents.put(vertex, parent);
                if (vertex.getStationName().equals(dest)) {
                    path.addFirst(vertex);
                    queue.clear();
                    break;
                }
            }
        }
        
        // Backtrace
        MetroStation vertex = parents.getOrDefault(path.getFirst(), null);
        do {
            path.addFirst(vertex);
            vertex = parents.getOrDefault(vertex, null);
        } while (vertex != null && !start.getStationName()
                .equals(path.getFirst().getStationName()));
        
        return path;
    }
    
    public static void printPath(List<MetroStation> list) {
        String currLine = list.get(0).getLineName();
        for (MetroStation ms : list) {
            if (!ms.getLineName().equals(currLine)) {
                currLine = ms.getLineName();
                System.out.println("> Transition to " + currLine + " line.");
            }
            System.out.println(" - - - " + ms.getStationName());
        }
    }
}
