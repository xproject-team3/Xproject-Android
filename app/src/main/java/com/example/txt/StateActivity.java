package com.example.txt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        Intent intent = getIntent();
        String image = intent.getExtras().getString("imagename");
        TextView textView = (TextView) findViewById(R.id.imageN);

        image = image.replace("년 ", "_");
        image = image.replace("월 ", "_");
        image = image.replace("일      ", "_");
        image = image.replace("시 ", "_");
        image = image.replace("분 ", "");
        image = image.replace(" ", "");

        new DownloadImageTask((ImageView) findViewById(R.id.imageView5))
                .execute("http://10.0.2.2/"+image+".jpg");

//        String file = "@drawable/textimage";
//        String packName = this.getPackageName();
//        int resID = getResources().getIdentifier(file, "drawable",packName);
//
//        ImageView imageView = (ImageView)findViewById(R.id.imageView5);
//        imageView.setImageResource(resID);


        Button listbutton = (Button) findViewById(R.id.listbutton);
        Button statebutton = (Button) findViewById(R.id.statebutton);

        textView.setText(image);

        listbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
            }
        });
        statebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ResentActivity.class);
                startActivity(intent);
            }
        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
