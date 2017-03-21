package com.delaroystudios.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by acervenka2 on 20.03.2017.
 */

public class CameraActivity extends ActionBarActivity {

    private ImageView capturedImage;

    private ArrayList<Bitmap> images;

    private double focal_length;
    private double optical_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        Button btnCamera = (Button) findViewById(R.id.btnCamera);

        capturedImage= (ImageView) findViewById(R.id.capturedImage);

        btnCamera.setTypeface(font);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        String delims = "[;]";
        String[] value = message.split(delims);

        images = new ArrayList<>();
    }

    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            capturedImage.setImageBitmap(bp);
            images.add(bp);
            if(images.size() == 2){
                ActivityCompat.finishAffinity(this);
                Intent intent = new Intent(this, SelectPointsActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    public void exitApplication(MenuItem item) {
        System.exit(0);
    }
}
