//우편함 목록 확인 화면이다.
package com.example.txt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.*;

public class Main2Activity extends AppCompatActivity {

    //기록을 배열에 넣기위해 선언
    ArrayList<String> mDatas= new ArrayList<String>();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String str = readTxt();
        String[] array = str.split("\n");

        for (int i=0;i<array.length;i++) {
            array[i] = array[i].replaceFirst("_", "년 ");
            array[i] = array[i].replaceFirst("_", "월 ");
            array[i] = array[i].replaceFirst("_", "일      ");
            array[i] = array[i].replaceFirst("_", "시 ");
            array[i] = array[i].replaceFirst("_", "분 ");

            mDatas.add(array[i]);
        }

        //기록을 차례대로 넣은 리스트를 보여주기위한 뷰 호출
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, mDatas);
        listview= (ListView)findViewById(R.id.listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(listener);

        Button statebutton = (Button) findViewById(R.id.statebutton);


        statebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResentActivity.class);
                startActivity(intent);
            }
        });
    }
    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), StateActivity.class);
            intent.putExtra("imagename",mDatas.get(position));
            startActivity(intent);
        }
    };

    private String readTxt() {
        String data = null;
        InputStream inputStream = getResources().openRawResource(R.raw.filename);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }

            data = new String(byteArrayOutputStream.toByteArray(), "MS949");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }}

