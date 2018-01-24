package com.qrkay.www.qrkay.customviews;

/**
 * Created by eoin2 on 21/01/2018.
 *
 */

public class VoucherModel {
    private String imagePath;
    private String tAndCs;
    int maxStamps;
    // TODO when setting usedStamps, use Math.min so not bigger than maxStamps
    int usedStamps;

    public VoucherModel(String imagePath, int max, int used){
        this.imagePath = imagePath;
        maxStamps = max;
        usedStamps = used;
        tAndCs = "<Terms and conditions>";
    }

    public VoucherModel(String imagePath, int max, int used, String TCs){
        this.imagePath = imagePath;
        maxStamps = max;
        usedStamps = used;
        tAndCs = TCs;
    }

    public String getImagePath(){
        return imagePath;
    }

    public int getMaxStamps(){
        return maxStamps;
    }

    public int getUsedStamps(){
        return usedStamps;
    }

    public String gettAndCs(){
        return tAndCs;
    }
}
