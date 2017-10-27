package com.saik0116algonquinlive.hsvcolorpicker;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Observer;
import java.util.Observable;

import model.HSVModel;
import model.RGBAmodel;

/**
 *  Purpose/Description of your app
 *  Joshua Saikaly (saik0116@algonquinlive.com)
 */

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {


  //   private AboutDialogFragment mAboutDialog;
    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHue;
    private SeekBar mSat;
    private SeekBar mBright;
     private TextView mHueTV;
     private TextView mSatTV;
     private TextView mBrightTV;
    private RGBAmodel nModel;

    private static final String ABOUT_DIALOG_TAG = "About Dialog";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nModel = new RGBAmodel();
        nModel.setRed( RGBAmodel.MAX_RGB );
        nModel.setGreen( RGBAmodel.MIN_RGB );
        nModel.setBlue( RGBAmodel.MIN_RGB );
        nModel.setAlpha( RGBAmodel.MAX_ALPHA );
        // The Model is observing this Controller (class MainActivity implements Observer)
        nModel.addObserver( this );

        mModel = new HSVModel(0,0,0);
        mModel.setHue(HSVModel.MIN_HSV);
        mModel.setBright(HSVModel.MIN_HSV);
        mModel.setSat(HSVModel.MIN_HSV);

        mModel.addObserver( this );


         mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHue = (SeekBar) findViewById(R.id.hueSB);
        mSat = (SeekBar) findViewById(R.id.satSB);
        mBright = (SeekBar) findViewById(R.id.brightSB);

        mHueTV = (TextView) findViewById(R.id.HueTV);
        mSatTV = (TextView) findViewById(R.id.SaturationTV);
        mBrightTV = (TextView) findViewById(R.id.BrightnessTV);

        mHue.setMax(HSVModel.MAX_HUE);
        mSat.setMax(HSVModel.MAX_SAT);
        mBright.setMax(HSVModel.MAX_BR);

        mHue.setOnSeekBarChangeListener(this);
        mSat.setOnSeekBarChangeListener(this);
        mBright.setOnSeekBarChangeListener(this);

               this.updateView();

    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        if(fromUser == false){
            return;
        }
        switch ( seekBar.getId() ) {
            case R.id.hueSB:
                mModel.setHue(mHue.getProgress());
                mHueTV.setText(getResources().getString(R.string.hueProgress, progress).toUpperCase());
                break;
            case R.id.satSB:
                mModel.setSat(mSat.getProgress());
                mSatTV.setText(getResources().getString(R.string.satProgress, progress).toUpperCase());
                break;
            case R.id.brightSB:
                mModel.setBright(mBright.getProgress());
                mBrightTV.setText(getResources().getString(R.string.brProgress, progress).toUpperCase());


        }


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText("Hue");
                break;
            case R.id.satSB:
                mSatTV.setText("Saturation");
                break;
            case R.id.brightSB:
                mBrightTV.setText("Value/Brightness");
                break;
        }
    }

    private void updateHueSB() {
        mHue.setProgress( mModel.getHue() );
    }
    private void updateSatSB() {
        mSat.setProgress( mModel.getSat() );
    }
    private void updateBrightSB() {
        mBright.setProgress( mModel.getBright() );
    }
    private void updateColorSwatch() {

            mColorSwatch.setBackgroundColor(Color.HSVToColor(new float[]{(float)mModel.getHue(), (float)mModel.getSat()/100
                    , (float)mModel.getBright()/100}
            ));


    }




    public void updateView() {
        this.updateColorSwatch();
        this.updateBrightSB();
        this.updateHueSB();
        this.updateSatSB();
    }


    @Override
    public void update(Observable observable, Object data) {
this.updateView();
    }
//    final Button rollBtn = (Button) findViewById(R.id.rollBtn);

    public void onClickedColorButton(View view){
        Button but = (Button) view;
        final float[] currentHSV = new float[3];
        ColorDrawable buttonColor = (ColorDrawable)but.getBackground();
        int colorId = buttonColor.getColor();

        Color.colorToHSV(colorId,currentHSV);


        mModel.setHSV((int)currentHSV[0],(int)(currentHSV[1]*100),(int)(currentHSV[2]*100));
        updateColorSwatch();




        but.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "Hue: " + ((int)currentHSV[0])+"\u00B0" +
                                " Saturation: " + ((int)currentHSV[1]*100) + "%" + " Brightness: "
                                + (int)(currentHSV[2]*100) + "%" ,
                        Toast.LENGTH_SHORT).show();

                return true;
            } });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
