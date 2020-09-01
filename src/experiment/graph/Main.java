package experiment.graph;

import java.util.Map;

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
    }
}
