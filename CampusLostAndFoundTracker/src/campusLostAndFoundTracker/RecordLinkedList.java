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
    
    

}
