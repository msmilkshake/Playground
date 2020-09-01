package experiment.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<MetroStation, List<MetroStation>> adj;
    
    public Graph() {
        adj = new HashMap<>();
    }
    
    public void addVertice(MetroStation ms) {
        if (ms == null) {
            return;
        }
        adj.putIfAbsent(ms, new ArrayList<>());
        adj.get(ms).add(ms);
    }
    
    public void addAndLink(String line, String[] stations) {
        MetroStation previous = null;
        for (String station : stations) {
            MetroStation ms = new MetroStation(line, station);
            addVertice(ms);
            link(previous, ms);
            previous = ms;
        }
    }
    
    private boolean isValidVertice(MetroStation ms) {
        return ms != null && adj.containsKey(ms);
    }
    
    public void link(MetroStation ms1, MetroStation ms2) {
        if (!isValidVertice(ms1) || !isValidVertice(ms2)) {
            return;
        }
        ms1 = adj.get(ms1).get(0);
        ms2 = adj.get(ms2).get(0);
        adj.get(ms1).add(ms2);
        adj.get(ms2).add(ms1);
    }
}
