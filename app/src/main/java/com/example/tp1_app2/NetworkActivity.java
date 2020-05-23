package com.example.tp1_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {

    GlobalState gs;
    private Button refBtnEnvoiReq;
    private Button refBtnRecupConv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        gs = (GlobalState) getApplication();
        gs.alerter("OnCreate");

        refBtnEnvoiReq = findViewById(R.id.btnEnvoiRequete);
        refBtnRecupConv = findViewById(R.id.btnGetConversations);
        refBtnEnvoiReq.setOnClickListener(this);
        refBtnRecupConv.setOnClickListener(this);
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
        // String s = gs.requete("action=connexion&login=user&passe=user");
        // android.os.NetworkOnMainThreadException
        // Il n'est pas autorisé de déclencher des traitements longs (requete HTTP, parsing JSON)
        // dans le thread de l'interface graphique 'UI thread' 'Main thread'
        // Ce thread a pour role de dépiler aussitôt que possible les interactions utilisateurs
        // Problèmes possibles "ANR" Application Not Responding
        // SOlution : utiliser des asynctask
        // manière rapide de déclencher des threads
        switch (v.getId()) {

            case R.id.btnEnvoiRequete :
                JSONAsyncTask tache1 = new JSONAsyncTask();
                tache1.execute("connexion");
            break;

            case R.id.btnGetConversations :
                JSONAsyncTask tache2 = new JSONAsyncTask();
                tache2.execute("getConversations");
            break;
        }

    }

    // Une classe interne est déclarée à l'intérieur d'une autre classe.
// Elle peut donc accéder aux membres de la classe externe.
// http://fr.wikibooks.org/wiki/Programmation_Java/Classes_internes

    class JSONAsyncTask extends AsyncTask<String, Void, String> {
        // Params, Progress, Result

        // Premier param : nature des éléments passés en paramètre lors de l'exécution du thread
        // Second param : nature des notifications déclenchées pour représenter l'avancement du thread
        // 3ème param : nature de la réponse

        private String mode;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            NetworkActivity.this.gs.alerter("onPreExecute");
        }

        @Override
        protected String doInBackground(String... qs) {
            // pas d'interaction avec l'UI Thread ici
            //NetworkActivity.this.gs.alerter("doInBackground");
            // Can't toast on a thread that has not called Looper.prepare()
            Log.i(NetworkActivity.this.gs.CAT,"doInBackground");

            // String... qs est une ELLIPSE : cela signifie que les arguments
            // passés à la méthode execute (en nombre quelconque) seront récupérés
            // sous la forme d'un tableau baptisé "qs"
            Log.i(NetworkActivity.this.gs.CAT,"arg1 : " + qs[0]);
            //Log.i(NetworkActivity.this.gs.CAT,"arg2 : " + qs[1]);
            mode =qs[0];

            switch (qs[0]) {
                case "connexion" :
                    return NetworkActivity.this.gs.requete("action=connexion&login=user&passe=user");
                case "getConversations" :
                    return NetworkActivity.this.gs.requete("action=getConversations");
                default:
                    return "{}";
            }

        }

        protected void onPostExecute(String result) {
            Log.i(NetworkActivity.this.gs.CAT,"onPostExecute");
            if (mode == "connexion") refBtnRecupConv.setEnabled(true);

            NetworkActivity.this.gs.alerter("resultat : " + result);
        }
    }
}
