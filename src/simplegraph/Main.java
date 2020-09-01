package simplegraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String[] lines = null;
    static Graph<String> network = new Graph<>();
    static Scanner scn = new Scanner(System.in);
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File("box/lisbon_v1.txt")))) {
            lines = br.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        processLines();
        System.out.println("Enter two stations:");
        while (true) {
            String s1 = scn.nextLine();
            String s2 = scn.nextLine();
            if ((s1 = network.getNode(s1)) == null
                    || (s2 = network.getNode(s2)) == null) {
                System.out.println("Invalid station");
                continue;
            }
            LinkedList<String> path = network.findPath(s1, s2);
            path.forEach(System.out::println);
        }
    }
    
    public static void processLines() {
        List<String> temp = new ArrayList<>();
        String prev = "";
        String currentLink = "";
        for (String line : lines) {
            String[] data = line.split("\\s*:\\s*");
            if (!currentLink.equals(data[0])) {
                currentLink = data[0];
                prev = data[1];
                network.add(prev);
            } else {
                network.addAndConnect(prev, data[1]);
                prev = data[1];
            }
        }
    }
}
