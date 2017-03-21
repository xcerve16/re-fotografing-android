package com.delaroystudios.camera;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by acervenka2 on 20.03.2017.
 */

public class SelectPointsActivity extends ActionBarActivity {


    Bitmap ref_frame, first_frame;
    private float x1, x2;
    static final int MIN_DISTANCE = 100;
    ImageView imageView;

    boolean isRefImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_points);

        imageView = (ImageView) findViewById(R.id.imageView);

        isRefImage = true;

        ref_frame = BitmapFactory.decodeResource(getResources(), R.drawable.ref_biskupsky_palac);
        first_frame = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_biskupsky_palac_4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_view, menu);
        return true;
    }

    public void changeView(MenuItem item) {
        if (!isRefImage) {
            imageView.setImageBitmap(ref_frame);
            isRefImage = true;
        } else {
            imageView.setImageBitmap(first_frame);
            isRefImage = false;
        }
    }

    public void finishRegister(MenuItem item) {
        ActivityCompat.finishAffinity(this);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    public void exitApplication(MenuItem item) {
        System.exit(0);
    }
}
