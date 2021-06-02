package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    int result;
    EditText e1;
    String text;

    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.et_data);

        b1=findViewById(R.id.btn_spc_scrn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        textToSpeech=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    result=textToSpeech.setLanguage(Locale.UK);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void TTS(View view)
    {
        switch (view.getId())
        {
            case R.id.bt_spk:
                if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(MainActivity.this, "not supported", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    text=e1.getText().toString();

                    textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                }

                break;

            case R.id.bt_stp:
                if(textToSpeech!=null)
                {
                    textToSpeech.stop();
                }

                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
