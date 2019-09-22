package com.aiczone.apiwithoutlibrary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.aiczone.apiwithoutlibrary.models.Users;
import com.aiczone.apiwithoutlibrary.utils.Api;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button bnGetData, bnPostData;

    Users users = new Users();

    Api api = new Api("https://reqres.in"); //Must end without "/"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnGetData = findViewById(R.id.bnGetData);
        bnPostData = findViewById(R.id.bnPostData);

        bnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        JsonReader jsonR = api.get("/api/users"); //must begin with "/"
                        try {
                            users.readResponse(jsonR);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        bnPostData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        api.post("name=erlang,job=anak", "/api/users");
                    }
                });
            }
        });

    }
}
