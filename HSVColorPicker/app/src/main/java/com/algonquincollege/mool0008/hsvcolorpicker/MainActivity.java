package com.algonquincollege.mool0008.hsvcolorpicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Observable;
import java.util.Observer;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import model.HSVModel;

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {

    // instance var
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueBrightnessSB;
    private TextView mHueTV;
    private TextView mSaturationTV;
    private TextView mValueBrightnessTV;
    private TextView mColorSwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create new hsv model
        mModel = new HSVModel();

        mModel.isBlack();

        // model observer
        mModel.addObserver(this);

        // find views with id
        mHueTV = findViewById(R.id.hue);
        mSaturationTV = findViewById(R.id.saturation);
        mValueBrightnessTV = findViewById(R.id.valueBrightness);
        mColorSwatch = findViewById(R.id.colorSwatch);
        mHueSB = findViewById(R.id.hueSB);
        mSaturationSB = findViewById(R.id.saturationSB);
        mValueBrightnessSB = findViewById(R.id.valueBrightnessSB);

        // set the max
        mHueSB.setMax(HSVModel.MAX_Hue);
        mSaturationSB.setMax(HSVModel.MAX_HSV);
        mValueBrightnessSB.setMax(HSVModel.MAX_HSV);


        // register the event handler for each <SeekBar>
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueBrightnessSB.setOnSeekBarChangeListener(this);

        // long click displays toast of hsv
        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {

                Float mySaturation = mModel.getSaturation();
                Float myBrightness = mModel.getValueBrightness();
                Toast.makeText(
                        view.getContext(),

                        "H: " + mModel.getHue() + "\u00B0" + "  S: " + mySaturation * 100 + "%" + "  V: " + myBrightness * 100 + "%",
                        Toast.LENGTH_SHORT
                ).show();

                return true;
            }
        });
        this.updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Author:");
            builder.setMessage("Eric Moolenbeek - mool0008");
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.cancel();
                }
            });
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    // seek bar event handler
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean change) {

        if (change == false) {
            return;
        }

        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue(mHueSB.getProgress());
                String hueString = getResources().getString(R.string.hueProgress, progress).toUpperCase() + "\u00B0";
                mHueTV.setText(hueString);
                break;

            case R.id.saturationSB:
                float saturationFloat = mSaturationSB.getProgress();
                saturationFloat = saturationFloat / 100;
                mModel.setSaturation(saturationFloat);
                String saturationString = getResources().getString(R.string.saturationProgress, progress).toUpperCase() + "%";
                mSaturationTV.setText(saturationString);
                break;

            case R.id.valueBrightnessSB:
                float valueBrightnessFloat = mValueBrightnessSB.getProgress();
                valueBrightnessFloat = valueBrightnessFloat / 100;
                mModel.setBrightness(valueBrightnessFloat);
                String brightnessString = getResources().getString(R.string.valueBrightnessProgress, progress).toUpperCase() + "%";
                mValueBrightnessTV.setText(brightnessString);
                break;

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText(getResources().getString(R.string.hue));
                break;

            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.saturation));
                break;

            case R.id.valueBrightnessSB:
                mValueBrightnessTV.setText(getResources().getString(R.string.valueBrightness));
                break;

        }

    }

    // update view
    @Override
    public void update(Observable observable, Object data) {

        this.updateView();
    }

    private void updateHueSB() {
        mHueSB.setProgress(mModel.getHue());
    }

    private void updateSaturationSB() {
        float saturation = mModel.getSaturation();
        saturation = saturation * 100;
        mSaturationSB.setProgress((int) saturation);
    }

    private void updateBrightnessSB() {
        float valueBrightness = mModel.getValueBrightness();
        valueBrightness = valueBrightness * 100;
        mValueBrightnessSB.setProgress((int) valueBrightness);
    }

    private void updateColorSwatch() {
        float[] HSVCOLOR = {mModel.getHue(), mModel.getSaturation(), mModel.getValueBrightness()};

        mColorSwatch.setBackgroundColor(Color.HSVToColor(HSVCOLOR));
    }

    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateBrightnessSB();
    }

    public void changeColor(View view) {

        // switch statement for color buttons
        switch (view.getId()) {
            case R.id.button_black:
                mModel.isBlack();
                break;

            case R.id.button_red:
                mModel.isRed();
                break;

            case R.id.button_lime:
                mModel.isLime();
                break;

            case R.id.button_blue:
                mModel.isBlue();
                break;

            case R.id.button_yellow:
                mModel.isYellow();
                break;

            case R.id.button_cyan:
                mModel.isCyan();
                break;

            case R.id.button_magenta:
                mModel.isMagenta();
                break;

            case R.id.button_silver:
                mModel.isSilver();
                break;

            case R.id.button_gray:
                mModel.isGray();
                break;

            case R.id.button_maroon:
                mModel.isMaroon();
                break;

            case R.id.button_olive:
                mModel.isOlive();
                break;

            case R.id.button_green:
                mModel.isGreen();
                break;

            case R.id.button_purple:
                mModel.isPurple();
                break;

            case R.id.button_teal:
                mModel.isTeal();
                break;

            case R.id.button_navy:
                mModel.isNavy();
                break;

        }

        Float mySaturation = mModel.getSaturation();
        Float myValueBrightness = mModel.getValueBrightness();

        // toast displays hsv after selecting color
        Toast.makeText(
                view.getContext(),
                "H: " + mModel.getHue() + "\u00B0" + "  S: " + mySaturation * 100 + "%" + "  V: " + myValueBrightness * 100 + "%",
                Toast.LENGTH_SHORT
        ).show();

    }
}