package com.example.tp1_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private SharedPreferences prefs;

    private final String TAG = "PMR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refBtnOK = findViewById(R.id.btnOK);
        refEdtPseudo = findViewById(R.id.edtPseudo);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

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
    protected void onStart() {
        super.onStart();

        // Lire la valeur du pseudo dans les préférences de l'activité

        String pseudoPrefs = prefs.getString("pseudo","Toto");
        alerter(pseudoPrefs);
        // Ecrire cette valeur dans le champ pseudo
        refEdtPseudo.setText(pseudoPrefs);
    }

    @Override
    public void onClick(View v) {
        // appelée lors du click sur btnOk, OU edtPseudo
        String s = refEdtPseudo.getText().toString();
        Bundle b = new Bundle();
        b.putString("pseudo",s);

        switch(v.getId()) {
            case R.id.btnOK :

                // enregistrer dans les préférences la valeur actuelle du pseudo

                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.putString("pseudo",s);
                editor.commit();


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
                Intent afficherPrefs = new Intent(this,PrefsActivity.class);
                startActivity(afficherPrefs);
                break;

            case R.id.menu_json:
                alerter("click sur json");
                Intent afficherJson = new Intent(this,JsonActivity.class);
                startActivity(afficherJson);
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
