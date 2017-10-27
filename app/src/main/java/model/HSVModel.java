package model;

import android.graphics.Color;

import java.util.Observable;


/**
 * Created by joshsaikaly on 2017-10-24.
 */

public class HSVModel extends Observable {

    public static final Integer MAX_HUE = 359;
    public static final Integer MIN_HSV = 0;
    public static final Integer MAX_SAT = 100;
    public static final Integer MAX_BR = 100;


    // Instance Variables

    private Integer hue;
    private Integer sat;
    private Integer bright;
    private Integer min;



    public HSVModel(Integer hue, Integer sat, Integer bright) {
        super();

        this.hue = hue;
        this.sat = sat;
        this.bright = bright;
    }


    // GETTERS
    public Integer getHue() {
        return hue;
    }
    public Integer getSat() {
        return sat;
    }
    public Integer getBright() {
        return bright;
    }

    // Convenient setter: set H, S, B
    public void setHSV( Integer hue, Integer sat, Integer bright ) {
        this.hue   = hue;
        this.sat = sat;
        this.bright  = bright;

        this.updateObservers();
    }

    public void setHue(Integer hue) {
        this.hue = hue;

        this.updateObservers();
    }

    public void setSat(Integer sat) {
        this.sat = sat;

        this.updateObservers();
    }

    public void setBright(Integer bright) {
        this.bright = bright;

        this.updateObservers();
    }


    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }




    public static void main( String[] args ) {
        HSVModel model = new HSVModel( 359, 100, 100 );

        System.out.println( model );
    }

}