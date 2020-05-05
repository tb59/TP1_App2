package com.example.tp1_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button refBtnOK;
    private EditText refEdtPseudo;

    private final String TAG = "PMR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refBtnOK = findViewById(R.id.btnOK);
        refEdtPseudo = findViewById(R.id.edtPseudo);

/*        refBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerter("click par écouteur spécifique");
            }
        });*/

        refBtnOK.setOnClickListener(this);
        refEdtPseudo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // appelée lors du click sur btnOk, OU edtPseudo
        String s = refEdtPseudo.getText().toString();
        Bundle b = new Bundle();
        b.putString("pseudo",s);

        switch(v.getId()) {
            case R.id.btnOK :
                alerter("Pseudo(bundle) = " + b.getString("pseudo"));
                Intent versSecondAct = new Intent(this,SecondActivity.class);
                versSecondAct.putExtras(b);
                startActivity(versSecondAct);
                break;
            case R.id.edtPseudo : alerter("click sur edtPseudo");break;

        }
    }

    void alerter(String s) {
        Log.i(TAG,s);
        Toast t = Toast.makeText(this,s, Toast.LENGTH_LONG);
        t.show();
    }

    public void foo(View view) {
        // Cette méthode n'est plus appelée lorsque l'on clique sur le bouton
        // Car une autre méthode a été déclarée de manière programmatique
        alerter("Click sur OK");
    }

    /* Gestion des menus */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_compte :
                alerter("click sur compte");
                break;
            case R.id.menu_prefs :
                alerter("click sur prefs");
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
