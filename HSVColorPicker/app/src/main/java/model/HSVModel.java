package model;

import java.util.Observable;

/**
 * Created by Eric Moolenbeek on 2017-11-26.
 */

// HSVModel class
public class HSVModel extends Observable {

    // class variables
    public static final Integer MAX_Hue = 360;
    public static final Integer MIN_Hue = 0;
    public static final int MAX_HSV = 100;
    public static final float MIN_HSV = 0;
    public static final float MAX_SATURATION = 1;
    public static final float MAX_BRIGHTNESS = 1;

    // instance variable
    private Integer hue;
    private float saturation;
    private float valueBrightness;

    public HSVModel() {
        this(MAX_Hue, MAX_HSV, MAX_HSV);
    }

    public HSVModel(Integer hue, float saturation, float valueBrightness) {
        super();

        this.hue = hue;
        this.saturation = saturation;
        this.valueBrightness = valueBrightness;
    }

    public void isBlack() {
        this.setHSV(MIN_Hue, MIN_HSV, MIN_HSV);
    }

    public void isRed() {
        this.setHSV(MIN_Hue, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void isLime() {
        this.setHSV(120, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void isBlue() {
        this.setHSV(240, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void isYellow() {
        this.setHSV(60, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void isCyan() {
        this.setHSV(180, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void isMagenta() {
        this.setHSV(300, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void isSilver() {
        this.setHSV(MIN_Hue, MIN_HSV, 0.75f);
    }

    public void isGray() {
        this.setHSV(MIN_Hue, MIN_HSV, .5f);
    }

    public void isMaroon() {
        this.setHSV(0, MAX_SATURATION, 0.5f);
    }

    public void isOlive() {
        this.setHSV(60, MAX_SATURATION, 0.0f);
    }

    public void isGreen() {
        this.setHSV(120, MAX_SATURATION, 0.5f);
    }

    public void isPurple() {
        this.setHSV(300, MAX_SATURATION, 0.5f);
    }

    public void isTeal() {
        this.setHSV(180, MAX_SATURATION, 0.5f);
    }

    public void isNavy() {
        this.setHSV(240, MAX_SATURATION, 0.5f);
    }

    // getters
    public Integer getHue() {
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getValueBrightness() {
        return valueBrightness;
    }

    // setters
    public void setHue(Integer hue) {
        this.hue = hue;

        this.updateObservers();
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;

        this.updateObservers();
    }

    public void setBrightness(float brightness) {
        this.valueBrightness = brightness;

        this.updateObservers();
    }

    // set hsv
    public void setHSV(Integer hue, float saturation, float brightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.valueBrightness = brightness;

        this.updateObservers();
    }

    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }
}



