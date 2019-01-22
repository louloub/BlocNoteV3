package com.example.blocnotev3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Création des bouttons
    EditText editText;
    TextView textView;

    // FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lien bouttons code
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        // Création des listener
        editText.setOnClickListener(this);
        textView.setOnClickListener(this);

        // Interprétation du "textView" avec le "editView" avec la méthode "addTextChangedListener"
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            // "CharSequence s" etc sont les paramètres de la méthode, ce qu'on peut utiliser dedans
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Que faire au moment où le texte change ?
                // inserer le "s" dans le textview

                textView.setText(s);
                // ---------------------
                // TEXT TO FIREBASE
                // ---------------------

                // convertir le "CharSequence" (s) en "String" (ss) pour l'objet "Note"
                // String ss = s.toString();
                // Modifier "Message" dans "Note" avec "ss"
                // db.collection("notes").add(ss);
            }

            @Override
            /**
             * @param s La chaîne qui a été modifiée
             * @param count Le nombre de caractères concernés
             * @param start L'endroit où commence la modification dans la chaîne
             * @param after La nouvelle taille du texte
             */
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            /**
             * @param s L'endroit où le changement a été effectué
             */
            public void afterTextChanged(Editable s) {
                // Que faire juste après que le changement de texte a été pris en compte ?

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
