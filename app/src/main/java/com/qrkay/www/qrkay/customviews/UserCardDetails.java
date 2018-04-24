package com.qrkay.www.qrkay.customviews;


public class UserCardDetails {

    private String rid;
    private int allStampsCollected;
    private int maxStampsCard;
    private int currentStamps;
    private String lastStamped;

    public UserCardDetails(){

    }

    public UserCardDetails(String RID, int allStampsCollected, int maxStampsCard, int currentStamps, String lastStamped){
        this.rid = RID;
        this.allStampsCollected =  allStampsCollected;
        this.maxStampsCard = maxStampsCard;
        this.currentStamps = currentStamps;
        this.lastStamped = lastStamped;
    }

    public String getRID(){ return rid; }
    public String getLastStamped(){ return lastStamped; }

    public int getMaxStampsCard(){ return maxStampsCard; }
    public int getAllStampsCollected(){ return allStampsCollected; }
    public int getCurrentStamps(){ return currentStamps; }
}
