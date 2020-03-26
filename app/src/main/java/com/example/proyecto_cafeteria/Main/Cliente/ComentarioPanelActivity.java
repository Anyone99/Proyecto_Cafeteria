package com.example.proyecto_cafeteria.Main.Cliente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cafeteria.R;

public class ComentarioPanelActivity extends AppCompatActivity {

    private EditText body;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comentario);

        init();


    }

    public void init() {

        body = (EditText) findViewById(R.id.edit_comentario_body);
        enviar = (Button) findViewById(R.id.button_comentario_enviar);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("mailto:yjrememberme@outlook.com");
                SharedPreferences sharedLogin = getApplicationContext().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
                String emails = sharedLogin.getString("loginUser", "No existe");
                String[] email = {emails};
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra(Intent.EXTRA_CC, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Opinión");
                intent.putExtra(Intent.EXTRA_TEXT, body.getText().toString()); // body
                startActivity(Intent.createChooser(intent, "Selecciona la aplicacion"));

            }
        });
    }
}
