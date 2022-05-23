package com.example.indeximplicit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText nWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    private ImageView img;

    Button btncamara;
    ImageView imgcamara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nWebsiteEditText = (EditText) findViewById(R.id.website_editext);
        mShareTextEditText = findViewById(R.id.share_edittext);
        btncamara = findViewById(R.id.btncamara);
        imgcamara = findViewById(R.id.imgcamara);

        btncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrircamara();

            }
        });
    }

    private void abrircamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // if (intent.resolveActivity(getPackageManager())!=null){
        startActivityForResult(intent, 1);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgcamara.setImageBitmap(imgBitmap);

        }

    }


    public void openWebsite(View view) {
        String url = nWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("implicitIntends", "Can't handle this!");
        }

    }


    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();


    }

}
