package com.aiczone.apiwithoutlibrary.models;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;

public class Users {
    public void readResponse(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String key = reader.nextName();
            if (key.equals("data")) {
                readListData(reader, key);
            }else if(key.equals("page")){
                String value = reader.nextString();
                Log.d("json utama:", value);
            }else if(key.equals("per_page")){
                String value = reader.nextString();
                Log.d("json utama:", value);
            } else {
                Log.d("skipkey utama: ", key);
                reader.skipValue();
            }
        }
    }

    private void readListData(JsonReader reader, String data) throws IOException {
        Log.d("call detail reader:", data);
        reader.beginArray();
        int i = 0;
        while (reader.hasNext()) {
            i++;
            Log.d("ListNomor:", String.valueOf(i));
            readData(reader);
        }
    }

    private void readData(JsonReader reader) throws IOException{
        reader.beginObject(); //base on output, if data => [] use array, if data => {} use object
        while (reader.hasNext()) {
            String key = reader.nextName();
            if (key.equals("id")) {
                String value = reader.nextString();
                Log.d("json:", value);
            }else if (key.equals("email")) {
                String value = reader.nextString();
                Log.d("json:", value);
            }else if (key.equals("first_name")) {
                String value = reader.nextString();
                Log.d("json:", value);
            }else if (key.equals("last_name")) {
                String value = reader.nextString();
                Log.d("json:", value);
            }else if (key.equals("avatar")) {
                String value = reader.nextString();
                Log.d("json:", value);
            } else {
                Log.d("skipkey: ", key);
                reader.skipValue();
            }
        }
        reader.endObject();
    }
}
