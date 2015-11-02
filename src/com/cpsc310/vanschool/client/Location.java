package com.cpsc310.vanschool.client;

/**
 * Created by jorgeluis on 10/31/15.
 */
public class Location {
    private Double LAT;
    private Double LONG;
    public Double getLAT() {
        return LAT;
    }
    public void setLAT(Double lAT) {
        LAT = lAT;
    }
    public Double getLONG() {
        return LONG;
    }
    public void setLONG(Double lONG) {
        LONG = lONG;
    }
    public Location(Double lAT, Double lONG) {
        super();
        LAT = lAT;
        LONG = lONG;
    }


}
