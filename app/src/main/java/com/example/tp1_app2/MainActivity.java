package com.example.tp1_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "PMR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    void alerter(String s) {
        Log.i(TAG,s);
        Toast t = Toast.makeText(this,s, Toast.LENGTH_LONG);
        t.show();
    }


    public void foo(View view) {
        alerter("Click sur OK");
    }
}
