package com.example.vaibhav.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;
    RadioButton btnHindi,btnEnglish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this, this);

        btnSpeak = (Button) findViewById(R.id.SpeakBtn);

        txtText = (EditText) findViewById(R.id.EditTxt);

        btnHindi=(RadioButton)findViewById(R.id.Hindi);
        btnEnglish=(RadioButton)findViewById(R.id.English);

        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });
    }


    @Override
    protected void onDestroy() {
        if (tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if(status == TextToSpeech.SUCCESS){
            int result=0;
            result = tts.setLanguage(new Locale("hi"));
            //result = tts.setLanguage(Locale.US);
            //tts.setPitch((float) 0.6);
            //tts.setSpeechRate(2);

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","This language is not supported");
            }else{
                btnSpeak.setEnabled(true);
                speakOut();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void speakOut() {
        String text = txtText.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

}
