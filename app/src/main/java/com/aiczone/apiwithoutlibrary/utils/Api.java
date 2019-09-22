package com.aiczone.apiwithoutlibrary.utils;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Api {
    private URL myURL = null;
    private  String myURI = "https://reqres.in"; //Must end without "/"

    public Api(String url){
        this.myURI = url;
    }

    public JsonReader get(String uri){
        // Create connection
        try {
            myURL = new URL(myURI + uri);
            HttpsURLConnection myCon =
                    (HttpsURLConnection) myURL.openConnection();
            myCon.setRequestProperty("User-Agent", "goeroeku-v2019");
            if (myCon.getResponseCode() == 200) {
                InputStream responseBody = myCon.getInputStream();
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);

                return jsonReader;
            } else {
                // Error handling code goes here
            }
            myCon.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void post(String data, String uri){
        // Create connection
        try {
            myURL = new URL(myURI + uri);
            HttpsURLConnection myCon =
                    (HttpsURLConnection) myURL.openConnection();

            myCon.setRequestProperty("User-Agent", "goeroeku-v2019");
            myCon.setRequestMethod("POST");
            myCon.setRequestProperty("Content-Type", "application/json; utf-8");
            myCon.setDoOutput(true);

            myCon.getOutputStream().write(data.getBytes());

            //-- Optional, just for LOG
            if (myCon.getResponseCode() >= 200 && myCon.getResponseCode() <= 205) {
                InputStream responseBody = myCon.getInputStream();
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");
                JsonReader jsonR = new JsonReader(responseBodyReader);

                jsonR.beginObject(); // Start processing the JSON object
                Log.d("result:", jsonR.nextName());
            } else {
                // Error handling code goes here
            }
            //-- End optional

            myCon.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
