package com.example.blocnotev3.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blocnotev3.CreateNote;
import com.example.blocnotev3.Helper.FileHelper;
import com.example.blocnotev3.MainActivity;
import com.example.blocnotev3.R;
import com.google.firebase.auth.FirebaseAuth;

public class NotesListe extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ListView listView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_liste);

        listView = findViewById(R.id.listView);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesListe.this, CreateNote.class);
                startActivity(intent);
            }
        });
    }
}

