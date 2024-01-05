package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt = (TextView) findViewById(R.id.txt_label);
        Button button = (Button) findViewById(R.id.json_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://lampvideo.ru")
                        .addConverterFactory( GsonConverterFactory.create() )
                        .build();
                SystemSettingsAPI myCall = retrofit.create(SystemSettingsAPI.class);
                Call<SystemSettings> call  = myCall.getSystemSettings(183);
                System.out.println( call.request().url().toString() );
                call.enqueue(new Callback<SystemSettings>() {
                    @Override
                    public void onResponse(Call<SystemSettings> call, Response<SystemSettings> response) {
                        if (response.code() != 200){
                            txt.setText("Check the connection");
                            return;
                        }
                        System.out.println("RESPONSE OK!");
                        String jsony = "";
                        jsony = "ID: " + response.body().getId() + "\nPlayer revision: " +
                                response.body().getMusicboxRevision() + "\nServer revision: " +
                                response.body().getServerRevision();
                        ;
                        txt.setText( jsony);

                    }

                    @Override
                    public void onFailure(Call<SystemSettings> call, Throwable t) {
                        System.out.println("In catch of login " + t.getMessage());
                        System.out.println("RESPONSE FAIL!");
                    }
                });
            }
        });
    }
}