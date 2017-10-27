package model;

import java.util.Observable;

import android.graphics.Color;

/**
 * The model holds the data.
 *
 * Model color.
 * Based on red, green, blue and alpha (transparency).
 *
 * RGB: integer values in the domain range of 0 to 255 (inclusive).
 * Alpha: integer value in the domain range of 0 to 255 (inclusive).
 *
 * Josh Saikaly
 * Saik0116
 */
public class RGBAmodel extends Observable {

    // CLASS VARIABLES
    public static final Integer MAX_ALPHA = 255;
    public static final Integer MAX_RGB   = 255;
    public static final Integer MIN_ALPHA = 0;
    public static final Integer MIN_RGB   = 0;

    // INSTANCE VARIABLES
    private Integer alpha;
    private Integer blue;
    private Integer green;
    private Integer red;

    /**
     * No argument constructor.
     *
     * Instantiate a new instance of this class, and
     * initialize red, green, blue, and alpha to max values.
     */
    public RGBAmodel() {
        this( MAX_RGB, MAX_RGB, MAX_RGB, MAX_ALPHA );
    }


    public RGBAmodel( Integer red, Integer green, Integer blue, Integer alpha ) {
        super();

        this.alpha = alpha;
        this.blue  = blue;
        this.green = green;
        this.red   = red;
    }


    // SETTERS
    public void setAlpha( Integer alpha ) {
        this.alpha = alpha;

        this.updateObservers();
    }

    public void setBlue(Integer blue) {
        this.blue = blue;

        this.updateObservers();
    }

    public void setGreen(Integer green) {
        this.green = green;

        this.updateObservers();
    }

    public void setRed(Integer red) {
        this.red = red;

        this.updateObservers();
    }



    // the model has changed!
    // broadcast the update method to all registered observers
    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }

  }