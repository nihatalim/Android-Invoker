package com.nihatalim.androidinvoker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nihatalim.invoker.Invoker;
import com.nihatalim.invoker.InvokerCallback;

public class MainActivity extends AppCompatActivity {
    private static final String UNIQUE_1 = "UNIQUE_1";
    private static final String UNIQUE_2 = "UNIQUE_2";
    private static final String CHANNEL_ID = "HOME_SCREEN_STUFFS";

    private Button button1, button2, button3;
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button1 = findViewById(R.id.button1);
        this.button2 = findViewById(R.id.button2);
        this.button3 = findViewById(R.id.button3);

        this.textView = findViewById(R.id.textView);

        // REGISTER TO INVOKER
        Invoker.put(UNIQUE_1, CHANNEL_ID, new InvokerCallback() {
            @Override
            public void invoke(final Object response) {
                uiThreadWorks(((String) response + " " + UNIQUE_1));
            }
        });

        Invoker.put(UNIQUE_2, CHANNEL_ID, new InvokerCallback() {
            @Override
            public void invoke(Object response) {
                uiThreadWorks(((String) response + " " + UNIQUE_2));
            }
        });


        // INVOKE TRIGGER
        this.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is a message from button1.";
                Invoker.invoke(UNIQUE_1, CHANNEL_ID, message);
            }
        });

        this.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is a message from button2.";
                Invoker.invoke(UNIQUE_2, CHANNEL_ID, message);
            }
        });

        this.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is a message from button3.";
                Invoker.invoke(CHANNEL_ID, message);
            }
        });
    }

    // Other stuffs
    private void uiThreadWorks(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText( textView.getText() + "\n" +  message );
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
