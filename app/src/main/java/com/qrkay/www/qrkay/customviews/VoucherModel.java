package com.qrkay.www.qrkay.customviews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eoin2 on 07/02/2018.
 *
 */

public class VoucherModel {

    public static final int DEFAULT_STYLE = 0;
    public static final int BGIMG_STYLE = 1;
    public static final int NOIMG_STYLE = 2;

    private int cardType;
    private String voucherName;
    private String contactNo;
    private String contactEmail;
    private String logo;
    private int stampStyle;
    private String tAndCs;
    private ArrayList<Float> hsv;
    //private float h;
    //private float s;
    //private float v;
    private int backgroundStyle;

    private int allStampsCollected;
    private int maxStampsCard;
    private int currentStamps;
    private String lastStamped;

    public VoucherModel(){

    }

    private VoucherModel(VoucherBuilder builder){
        this.voucherName = builder.voucherName;
        this.contactNo = builder.contactNo;
        this.contactEmail = builder.contactEmail;
        this.logo = builder.logo;
        this.stampStyle = builder.stampStyle;
        this.tAndCs = builder.tAndCs;
        this.cardType = builder.cardType;
        this.hsv = builder.hsv;
        //this.h = builder.h;
        //this.s = builder.s;
        //this.v = builder.v;
        this.backgroundStyle = builder.backgroundStyle;
        this.allStampsCollected = builder.allStampsCollected;
        this.maxStampsCard = builder.maxStampsCard;
        this.currentStamps = builder.currentStamps;
        this.lastStamped = builder.lastStamped;
    }

    public String getVoucherName(){ return voucherName; }
    public String getContactNo(){ return contactNo; }
    public String getContactEmail(){ return contactEmail; }
    public String getLogo(){ return logo; }
    public String gettAndCs(){ return tAndCs; }
    public String getLastStamped(){ return lastStamped; }

    public int getCardType(){ return cardType; }
    public int getStampStyle(){ return stampStyle; }
    public int getAllStampsCollected(){ return allStampsCollected; }
    public int getMaxStampsCard(){ return maxStampsCard; }
    public int getCurrentStamps(){ return currentStamps; }

    public ArrayList<Float> getHSV(){
        return hsv;
    }

    public int getBackgroundStyle(){ return backgroundStyle; }

    public static class VoucherBuilder{
        // TODO add "final" to vars that are required by default
        private String voucherName = "tempName";
        private String contactNo = "1234567890";
        private String contactEmail = "contact@email.com";
        private String logo = "http://imagePath";
        private String tAndCs = "Terms and conditions. Apply daily, with accompanying exfoliating lotion";
        private int stampStyle = 0;
        private int cardType = VoucherModel.DEFAULT_STYLE;
        private ArrayList<Float> hsv;
        //private float h = 180f;
        //private float s = 0.8f;
        //private float v = 0.2f;
        private int backgroundStyle = 0;

        private final int allStampsCollected;
        private final int maxStampsCard;
        private final int currentStamps;
        private final String lastStamped;

        public VoucherBuilder(UserCardDetails userCardDetails){
            this.allStampsCollected = userCardDetails.getAllStampsCollected();
            this.maxStampsCard = userCardDetails.getMaxStampsCard();
            this.currentStamps = userCardDetails.getCurrentStamps();
            this.lastStamped = userCardDetails.getLastStamped();
        }

        public VoucherBuilder businessVoucherModel(VoucherModel businessModel){
            this.voucherName = businessModel.getVoucherName();
            this.contactNo = businessModel.getContactNo();
            this.contactEmail = businessModel.getContactEmail();
            this.logo = businessModel.getLogo();
            this.tAndCs = businessModel.gettAndCs();
            this.stampStyle = businessModel.getStampStyle();
            this.cardType = businessModel.getCardType();
            this.hsv = businessModel.getHSV();
            this.backgroundStyle = businessModel.getBackgroundStyle();
            return this;
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

        public VoucherBuilder tAndCs(String tAndCs){
            this.tAndCs = tAndCs;
            return this;
        }

        public VoucherBuilder stampStyle(int stampStyle){
            this.stampStyle = stampStyle;
            return this;
        }

        public VoucherBuilder cardType(int cardType){
            this.cardType = cardType;
            return this;
        }

        public VoucherBuilder hsv(ArrayList<Float> hsv){
            this.hsv = hsv;
            return this;
        }

        public VoucherBuilder backgroundStyle(int style){
            this.backgroundStyle = style;
            return this;
        }

        public VoucherModel build(){return new VoucherModel(this);}
    }
}
