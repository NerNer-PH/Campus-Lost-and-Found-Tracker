/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campusLostAndFoundTracker;


public class Recordnode {
    
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
