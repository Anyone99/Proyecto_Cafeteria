package com.example.proyecto_cafeteria.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_cafeteria.Entity.AdminEntity;
import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.Entry.Admin;
import com.example.proyecto_cafeteria.Entry.ListaPedido;
import com.example.proyecto_cafeteria.Entry.Pedido;
import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.Main.Cliente.RegistrarPanelActivity;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class MainActivity extends AppCompatActivity {

    private Button button_Registrar, button_Login;
    private RadioGroup radio;
    private RadioButton usuarioRadio;
    private RadioButton adminRadio;

    private EditText email, password;
    private TextView coordinatorLayout;


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

                SharedPreferences sharedPreferences = getSharedPreferences("loginSession", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (usuarioRadio.isChecked()) {
                    UserEntity userEntity = User.findByEmailPassword(email.getText().toString(), password.getText().toString(), getApplicationContext());
                    if (userEntity != null) {
                        editor.putString("loginUser", email.getText().toString());
                        editor.commit();

                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), UserPanelActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "El usuario o la contraseña no es válido o no existe ", Toast.LENGTH_SHORT).show();
                    }
                }

                if (adminRadio.isChecked()) {
                    AdminEntity adminEntity = Admin.findByEmailPassword(email.getText().toString(), password.getText().toString(), getApplicationContext());
                    if (adminEntity != null) {
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), AdminPanelActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "El usuario o la contraseña no es válido o no existe", Toast.LENGTH_SHORT).show();
                    }

                }
                email.setText("");
                password.setText("");
                usuarioRadio.setChecked(false);
                adminRadio.setChecked(false);

            }
        });

    }

}
