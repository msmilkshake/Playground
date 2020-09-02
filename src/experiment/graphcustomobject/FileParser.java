package experiment.graphcustomobject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileParser {
    public static Map<String, Map<Integer, String>> readFile() {
        
        File file = new File("box/lisbon_v2.txt");
        Map<String, Map<Integer, String>> metrolines = new HashMap<>();
        List<String> rawConnections = new ArrayList<>();
        int connectionsCount = 0;
        String conStr = "#connections#";
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            String line;
            String currMetroLine = "";
            while ((line = br.readLine()) != null) {
                String[] data = line.split(":");
                if ("0".equals(data[0])) {
                    currMetroLine = data[1];
                    metrolines.put(currMetroLine, new HashMap<>());
                    continue;
                }
                metrolines.get(currMetroLine)
                        .put(Integer.parseInt(data[0]), data[1]);
                if (data.length > 2) {
                    metrolines.putIfAbsent(conStr, new HashMap<>());
                    metrolines.get(conStr).put(connectionsCount++,
                            new StringBuilder(currMetroLine)
                                    .append(":").append(data[1])
                                    .append(":").append(data[2])
                                    .append(":").append(data[3])
                                    .toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metrolines;
    }
}
