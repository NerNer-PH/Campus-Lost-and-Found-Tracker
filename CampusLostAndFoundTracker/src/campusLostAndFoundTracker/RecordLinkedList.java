package campusLostAndFoundTracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RecordLinkedList {

    static class Node {

        int id;
        String itemName;
        String location;
        String date;
        String status;
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

    public Node getHead() {
        return head;
    }

    public int getSize() {
        return size;
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
    Node findById(int id) {
        Node current = head;
        while (current != null) {
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
            if (current.status.equals(status)) {
                count++;

            }
            current = current.next;
        }
        return count;
    }

    //SEARCH
    RecordLinkedList search(String keyword) {
        RecordLinkedList result = new RecordLinkedList();
        Node current = head;
        while (current != null) {
            if (keyword.length() == 0
                    || containsIgnoreCase(String.valueOf(current.id), keyword)
                    || containsIgnoreCase(current.itemName, keyword)
                    || containsIgnoreCase(current.location, keyword)
                    || containsIgnoreCase(current.status, keyword)) {
                result.addNode(new Node(current.id, current.itemName, current.location, current.date, current.status, current.claimedBy));

            }
            current = current.next;
        }
        return result;
    }

    private static boolean containsIgnoreCase(String text, String key) {
        int n = text.length();
        int m = text.length();
        if (m == 0) {
            return true;
        }
        if (m > n) {
            return false;
        }
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && Character.toLowerCase(text.charAt(i + j)) == Character.toLowerCase(key.charAt(j))) {
                j++;
            }
            if (j == m) {
                return true;
            }
        }
        return false;
    }

    //DISPLAY
    void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.id + ":" + current.itemName + "->");
            current = current.next;
        }
        System.out.println("null");
    }

    //File Handling
    void saveToFile(String path) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(path));
        try {
            Node current = head;
            while (current != null) {
                out.write(current.id + "|" + current.itemName + "|" + current.location + "|" + current.date + "|" + current.status + "|" + current.claimedBy);
                out.newLine();
                current = current.next;
            }
        } finally {
            out.close();
        }
    }

    void loadFromFile(String path) throws IOException {
        File f = new File(path);
        if (!f.exists()) {
            return;
        }
        BufferedReader in = new BufferedReader(new FileReader(f));
        try {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.trim().length() == 0) {
                    continue;
                }
                String[] p = splitLine(line);
                if (p == null) {
                    continue;
                }
                try {
                    int id = Integer.parseInt(p[0]);
                    addNode(new Node(id, p[1], p[2], p[3], p[4], p[5]));
                } catch (NumberFormatException e) {
                }
            }
        } finally {
            in.close();
        }
    }

    private static String[] splitLine(String line) {
        String[] parts = new String[6];
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '|') {
                if (count >= 5) {
                    return null;
                }
                parts[count++] = sb.toString();
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        parts[count++] = sb.toString();
        return count == 6 ? parts : null;
    }

}
