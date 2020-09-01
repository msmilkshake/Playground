package simplegraph;

import java.util.*;

public class Graph<T> {
    List<LinkedList<T>> nodes;
    
    public Graph() {
        nodes = new ArrayList<>();
    }
    
    public T getNode(T node) {
        for (LinkedList<T> list : nodes) {
            if (Objects.equals(list.getFirst(), node)) {
                return list.getFirst();
            }
        }
        return null;
    }
    
    public int size() {
        return nodes.size();
    }
    
    
    public boolean add(T node) {
        if (getNode(node) != null) {
            return false;
        }
        LinkedList<T> newNode = new LinkedList<>();
        newNode.addLast(node);
        return nodes.add(newNode);
    }
    
    public LinkedList<T> getList(T node) {
        for (LinkedList<T> list : nodes) {
            if (list.getFirst().equals(node)) {
                return list;
            }
        }
        return null;
    }
    
    public void addAndConnect(T node, T connectTo) {
        add(node);
        add(connectTo);
        safeConnect(node, connectTo);
    }
    
    private void safeConnect(T node1, T node2) {
        LinkedList<T> list1 = getList(node1);
        LinkedList<T> list2 = getList(node2);
        node1 = list1.getFirst();
        node2 = list2.getFirst();
        list1.addLast(node2);
        list2.addLast(node1);
    }
    
    public boolean connect(T node1, T node2) {
        if (getNode(node1) == null
                || getNode(node2) == null) {
            return false;
        }
        safeConnect(node1, node2);
        return true;
    }
    
    public LinkedList<T> findPath(T start, T dest) {
        LinkedList<T> queue = new LinkedList<>();
        queue.addLast(start);
        HashMap<T, Boolean> visited = new HashMap<>();
        HashMap<T, T> parentsMap = new HashMap<>();
        LinkedList<T> path = new LinkedList<>();
        while (!queue.isEmpty()) {
            T parent = queue.removeFirst();
            visited.put(parent, true);
            LinkedList<T> adj = getList(parent);
            for (T node : adj) {
                if (visited.getOrDefault(node, false)) {
                    continue;
                }
                queue.addLast(node);
                parentsMap.put(node, parent);
                visited.put(node, true);
                if (node.equals(dest)) {
                    path.addFirst(node);
                    node = parentsMap.getOrDefault(node, null);
                    while (node != null) {
                        path.addFirst(node);
                        node = parentsMap.getOrDefault(node, null);
                    }
                    break;
                }
            }
        }
        return path;
    }
}
