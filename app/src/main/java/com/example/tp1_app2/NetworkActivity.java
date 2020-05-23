package com.example.tp1_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {

    GlobalState gs;
    private Button refBtnEnvoiReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        gs = (GlobalState) getApplication();
        gs.alerter("OnCreate");

        refBtnEnvoiReq = findViewById(R.id.btnEnvoiRequete);
        refBtnEnvoiReq.setOnClickListener(this);
    }

    // Chaque fois que cette activité est redémarrée, on vérifier l'état du réseau



    @Override
    protected void onResume() {
        super.onResume();
        gs.alerter("onResume");
        refBtnEnvoiReq.setEnabled(gs.verifReseau());
    }

    @Override
    public void onClick(View v) {
        gs.alerter("Envoi d'une requete HTTP");
        String s = gs.requete("action=connexion&login=user&passe=user");
        // android.os.NetworkOnMainThreadException
        // Il n'est pas autorisé de déclencher des traitements longs (requete HTTP, parsing JSON)
        // dans le thread de l'interface graphique 'UI thread' 'Main thread'
        // Ce thread a pour role de dépiler aussitôt que possible les interactions utilisateurs
        // Problèmes possibles "ANR" Application Not Responding
    }
}
