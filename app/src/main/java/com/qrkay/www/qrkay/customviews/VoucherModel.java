package com.qrkay.www.qrkay.customviews;

import java.util.ArrayList;

/**
 * Created by eoin2 on 21/01/2018.
 *
 */

public class VoucherModel{
    private String voucherName;
    private String contactNo;
    private String contactEmail;
    private String logo;
    private String reward;
    private String lastStamped;
    private int allStampsCollected;
    private int maxStampsCard;
    private int currentStamps;
    private String tAndCs;
    private ArrayList<String> stampDates;

    public VoucherModel(){

    }

    private VoucherModel(VoucherBuilder builder){
        this.voucherName = builder.voucherName;
        this.contactNo = builder.contactNo;
        this.contactEmail = builder.contactEmail;
        this.logo = builder.logo;
        this.reward = builder.reward;
        this.lastStamped = builder.lastStamped;
        this.allStampsCollected = builder.allStampsCollected;
        this.maxStampsCard = builder.maxStampsCard;
        this.currentStamps = builder.currentStamps;
        this.tAndCs = builder.tAndCs;
        this.stampDates = builder.stampDates;
    }

    public String getVoucherName(){
        return voucherName;
    }

    public String getContactNo(){
        return contactNo;
    }

    public String getContactEmail(){
        return contactEmail;
    }

    public String getReward(){
        return reward;
    }

    public String getLogo(){
        return logo;
    }

    public String getLastStamped(){
        return lastStamped;
    }

    public String gettAndCs(){
        return tAndCs;
    }

    public ArrayList<String> getStampDates(){
        return stampDates;
    }

    public int getAllStampsCollected(){
        return allStampsCollected;
    }

    public int getMaxStampsCard(){
        return maxStampsCard;
    }

    public int getCurrentStamps(){
        return currentStamps;
    }

    public static class VoucherBuilder{
        // TODO add "final" to vars that are required by default
        private String voucherName = "tempName";
        private String contactNo = "123456789";
        private String contactEmail = "contact@business.name";
        private String logo = "http://imagePath";
        private String reward = "REWARD!!";
        private String lastStamped = "Yesterday, all my troubles seeemed so far away...";
        private final int allStampsCollected;
        private final int maxStampsCard;
        private final int currentStamps;
        private String tAndCs = "Terms and conditions. Apply daily, with accompanying exfoliating lotion";
        private ArrayList<String> stampDates = new ArrayList<String>(){{add("Partridge in a pear tree");add("Two turtle doves");add("Three French hens");}};

        // TODO add the default required stuff to here
        public VoucherBuilder(int allStampsCollected, int maxStampsCard){
            this.allStampsCollected = allStampsCollected;
            this.maxStampsCard = maxStampsCard;
            // Think this'll work, don't necessarily need to save the currentStamps in the DB
            this.currentStamps = allStampsCollected % maxStampsCard;
        }

        public VoucherBuilder voucherName(String voucherName){
            this.voucherName = voucherName;
            return this;
        }

        public VoucherBuilder contactNo(String contactNo){
            this.contactNo = contactNo;
            return this;
        }

        public VoucherBuilder contactEmail(String contactEmail){
            this.contactEmail = contactEmail;
            return this;
        }

        public VoucherBuilder logo(String logo){
            this.logo = logo;
            return this;
        }

        public VoucherBuilder reward(String reward){
            this.reward = reward;
            return this;
        }

        public VoucherBuilder lastStamped(String lastStamped){
            this.lastStamped = lastStamped;
            return this;
        }

        public VoucherBuilder tAndCs(String tAndCs){
            this.tAndCs = tAndCs;
            return this;
        }

        public VoucherBuilder stampDates(ArrayList<String> stampDates){
            this.stampDates = stampDates;
            return this;
        }

        public VoucherModel build(){
            return new VoucherModel(this);
        }
    }
}
