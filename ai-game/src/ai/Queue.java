package ai;

import java.util.LinkedList;

public class Queue {
    private LinkedList<Node>  nodeList = new LinkedList<Node>();

    public void enqueue(Node e) {
        nodeList.addLast(e);
    }

    public Node dequeue() {
        return nodeList.pollFirst();
    }

    public int size() {
        return nodeList.size();
    }

    public boolean isEmpty() {
        return nodeList.isEmpty();
    }

    public void removeAll() {
        nodeList.clear();
    }
}
