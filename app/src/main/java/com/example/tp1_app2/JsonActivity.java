package com.example.tp1_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonActivity extends AppCompatActivity {

    private final String chaine_json = "{\"promo\": 2020,\"electif\": \"PMR\",\"enseignants\": [{\"prenom\": \"Isabelle\",\"nom\": \"Le Glaz\"},{\"prenom\": \"Thomas\",\"nom\": \"Bourdeaud'huy\"},{\"prenom\": \"Mohamed\",\"nom\": \"Boukadir\"}]}";
    private final String TAG = "PMR";

    void alerter(String s) {
        Log.i(TAG,s);
        Toast t = Toast.makeText(this,s, Toast.LENGTH_LONG);
        t.show();
    }

    public static String jsonToPrettyFormat(String jsonString) {
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .create();

            return gson.toJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    void dumpJson2(String s_json) {
        alerter(jsonToPrettyFormat(s_json));
    }


    void dumpJson(String s_json) {
        try {
            JSONObject o = new JSONObject(s_json);
            int promo = o.getInt("promo");
            String electif = o.getString("electif");
            alerter("promo="+promo);
            alerter("electif="+electif);


            JSONArray tabEnseignants = o.getJSONArray("enseignants");
            JSONObject nextEnseignant;

            alerter(tabEnseignants.toString());

            String prenom;
            String nom;
            for(int i=0;i<tabEnseignants.length();i++) {
                nextEnseignant = tabEnseignants.getJSONObject(i);
                prenom = nextEnseignant.getString("prenom");
                nom = nextEnseignant.getString("nom");
                alerter( String.valueOf(i) + ": " + prenom + " " + nom);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        alerter(chaine_json);
        dumpJson(chaine_json);
        dumpJson2(chaine_json);

        Promo p = new Promo(2020,"Android");
        Enseignant i = new Enseignant("Isabelle", "Le Glaz");
        Enseignant t = new Enseignant("Thomas", "Bourdeaud'huy");
        p.addEnseignant(i);
        p.addEnseignant(t);

        Gson gson1 = new Gson();
        String s1 = gson1.toJson(p);

        Gson gson2 = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        String s2 = gson2.toJson(p);

        alerter("s1 = " + s1);
        alerter("s2 = " + s2);

        String chaine_json = "{\"annee\": 2020,\"label\": \"Objet2\",\"enseignants\": [{\"pseudo\": \"Isabelle\",\"nom\": \"Le Glaz\"},{\"pseudo\": \"Thomas\",\"nom\": \"Bourdeaud'huy\"},{\"pseudo\": \"Mohamed\",\"nom\": \"Boukadir\"}]}";

        Promo p2 = gson1.fromJson(chaine_json,Promo.class);
        alerter(p2.toString());

    }
}
