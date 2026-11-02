
package campusLostAndFoundTracker;


public class RecordNode {
    
    int id;
    String itemName;
    String location;
    String date;
    String status;
    String claimedBy;
    RecordNode next;
    
    
    public RecordNode(int id, String itemName, String location, String date, String status, String claimedBy) {
        
        this.id = id;
        this.itemName = itemName;
        this.location = location;
        this.date = date;
        this.status = status;
        this.claimedBy = claimedBy;
        this.next = null;
        
    }
    
}
