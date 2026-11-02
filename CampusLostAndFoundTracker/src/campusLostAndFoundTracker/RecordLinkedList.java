package campusLostAndFoundTracker;

import java.io.BufferedReader;

public class RecordLinkedList {

    static class Node {

        int id;
        String itemName;
        String location;
        String date;
        String Status;
        String claimedBy;
        Node next;

        Node(int id, String itemName, String location, String date, String status, String claimedBy) {

            this.id = id;
            this.itemName = itemName;
            this.location = location;
            this.date = date;
            this.date = date;
            this.claimedBy = claimedBy;
            this.next = null;

        }

    }

    Node head;
    private int size;
    private int nextId = 1;

    public int getSize() {
        return size;
    }

    public int getNextId() {
        return nextId;
    }
    //ADD

    Node add(String itemNum, String location, String date) {
        Node newNode = new Node(nextId, sanitize(itemNum), sanitize(location),
                sanitize(date), "Unclaimed", "-");
        addNode(newNode);
        return newNode;
    }

    static String sanitize(String s) {
        return s.trim().replace('|', '/');
    }

    private void addNode(Node newNode) {
        newNode.next = null;
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        if (newNode.id >= nextId) {
            nextId = newNode.id + 1;
        }
    }

    //removes the record with the given ID
    boolean removeById(int id) {
        if (head == null) {
            return false;
        }
        if (head.id == id) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.id == id) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    //FIND
    Node findById(int id){
        Node current = head;
        while (current !=null){
            if (current.id == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    int countByStatus(String status) {
        int count = 0;
        Node current = head;
        while (current != null) {
        if (current.Status.equals(status)) {
            count++;
                    
        }
        current = current.next;
    }
        return count;
    }
}


