package com.example.proyecto_cafeteria.Main.Cliente;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.Main.MainActivity;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

public class RegistrarPanelActivity extends AppCompatActivity {

    private EditText nombre, apellido, email, password;
    private EditText telefono;
    private EditText fnacimiento;
    private TextView text_error;

    private Button buttonRegistrar;
    private Button buttonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        handleRegisrar();

    }

    private void handleRegisrar() {
        nombre = (EditText) findViewById(R.id.edit_modify_nombre);
        apellido = (EditText) findViewById(R.id.edit_modify_apellido);
        email = (EditText) findViewById(R.id.edit_modify_email);
        password = (EditText) findViewById(R.id.edit_modify_password);
        telefono = (EditText) findViewById(R.id.edit_modify_telefono);
        fnacimiento = (EditText) findViewById(R.id.edit_modify_fnacimiento);
        buttonRegistrar = (Button) findViewById(R.id.button_modify_modificar);
        buttonCancelar = (Button) findViewById(R.id.button_modify_cancelar);
        text_error = (TextView) findViewById(R.id.text_add_usuario_errors);


        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputValid() == null) {
                    ContentValues values = new ContentValues();

                    values.put(User.NOMBRE, nombre.getText().toString());
                    values.put(User.APELLIDO, apellido.getText().toString());
                    values.put(User.EMAIL, email.getText().toString());
                    values.put(User.PASSWORD, password.getText().toString());
                    values.put(User.FNACIMIENTO, fnacimiento.getText().toString());
                    values.put(User.TELEFONO, Integer.parseInt(telefono.getText().toString()));

                    long idCliente = User.save(values, getApplicationContext());

                    if (idCliente > 0) {
                        Toast.makeText(getApplicationContext(), "Registrado : tu id :  " + idCliente, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "No Registrado ", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), inputValid(), Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public String inputValid() {
        nombre = (EditText) findViewById(R.id.edit_modify_nombre);
        apellido = (EditText) findViewById(R.id.edit_modify_apellido);
        email = (EditText) findViewById(R.id.edit_modify_email);
        password = (EditText) findViewById(R.id.edit_modify_password);
        telefono = (EditText) findViewById(R.id.edit_modify_telefono);
        fnacimiento = (EditText) findViewById(R.id.edit_modify_fnacimiento);

        String mensaje = "";

        if (nombre.getText().length() == 0 || nombre.getText() == null) {
            mensaje += "El campo de nombre está vacío" + "\n";
        }
        if (apellido.getText().length() == 0) {
            mensaje += "El campo de apellido está vacío" + "\n";
        }
        if (email.getText().length() == 0) {
            mensaje += "El campo de email está vacío" + "\n";
        }
        if (password.getText().length() == 0) {
            mensaje += "El campo de password está vacío" + "\n";
        }
        if (telefono.getText().length() == 0) {
            mensaje += "El campo de telefono está vacío" + "\n";
        }else{
            if (telefono.getText().length() < 9 ){
                mensaje += "La longitud de telefono debe ser 9.";
            }
        }
        if (fnacimiento.getText().length() == 0) {
            mensaje += "El campo de fecha de Nacimiento está vacío" + "\n";
        }

        if (mensaje.length() == 0) {
            return null;
        } else {
            return mensaje;
        }

    }
}
