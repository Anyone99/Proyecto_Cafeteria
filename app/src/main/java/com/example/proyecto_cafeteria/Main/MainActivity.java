package com.example.proyecto_cafeteria.Main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.proyecto_cafeteria.Entry.Admin;
import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button_Registrar, button_Login;
    private RadioGroup radio;
    private RadioButton usuarioRadio;
    private RadioButton adminRadio;

    private EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        handleRegistry();
        handleLogin();
    }

    public void handleRegistry() {
        button_Registrar = findViewById(R.id.button_login_registrar);
        button_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), RegistrarPanelActivity.class);
                startActivity(intent);
            }
        });

    }

    public void handleLogin() {
        button_Login = findViewById(R.id.button_login);
        email = findViewById(R.id.edit_login_usuario);
        password = findViewById(R.id.edit_login_password);
        usuarioRadio = findViewById(R.id.radio_login_usuario);
        adminRadio = findViewById(R.id.radio_login_admin);
        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(getApplicationContext());
                SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

                if (usuarioRadio.isChecked()){
                    String[] args = new String[]{email.getText().toString(), password.getText().toString()};

                    Cursor cursor = db.rawQuery("Select * From " + User.TABLE_NAME + " Where " + User.EMAIL + " = ? AND " + User.PASSWORD + " = ? ", args);
                    if (cursor.moveToFirst()) {
                        Toast.makeText(getApplicationContext(), "Existe el usuario ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), UserPanelActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Existe el usuario ", Toast.LENGTH_LONG).show();

                    }
                    cursor.close();
                }
                if (adminRadio.isChecked()){
                    String[] args = new String[]{email.getText().toString(), password.getText().toString()};

                    Cursor cursor = db.rawQuery("Select * From " + Admin.TABLE_NAME + " Where " + Admin.USUARIO + " = ? AND " + Admin.PASSWORD + " = ? ", args);

                    if (cursor.moveToFirst()) {
                        Toast.makeText(getApplicationContext(), "Existe el usuario ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), AdminPanelActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Existe el usuario ", Toast.LENGTH_LONG).show();
                    }
                    cursor.close();


                }

            }
        });

    }

}
