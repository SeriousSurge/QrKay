package com.qrkay.www.qrkay.customviews;

import java.util.ArrayList;
import java.util.List;



public class BusinessVoucherModel {

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
    private float h;
    private float s;
    private float v;
    private int backgroundStyle;

    public BusinessVoucherModel(){

    }

    public String getVoucherName(){ return voucherName; }
    public String getContactNo(){ return contactNo; }
    public String getContactEmail(){ return contactEmail; }
    public String getLogo(){ return logo; }
    public String gettAndCs(){ return tAndCs; }

    public int getCardType(){ return cardType; }
    public int getStampStyle(){ return stampStyle; }

    public ArrayList<Float> getHSV(){
        ArrayList<Float> ret = new ArrayList<>();
        ret.add(h);
        ret.add(s);
        ret.add(v);
        return ret;
    }

    public int getBackgroundStyle(){ return backgroundStyle; }
}
