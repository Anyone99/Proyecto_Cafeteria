package com.example.proyecto_cafeteria.Main;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.List;

public class RegistrarPanelActivity extends AppCompatActivity {

    private EditText nombre, apellido, email, password;
    private EditText telefono;
    private EditText fnacimiento;

    private Button buttonRegistrar;
    private Button buttonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_user);
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

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(getApplicationContext());
                SQLiteDatabase db = SQLiteDBHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(User.NOMBRE, nombre.getText().toString());
                values.put(User.APELLIDO, apellido.getText().toString());
                values.put(User.EMAIL, email.getText().toString());
                values.put(User.PASSWORD, password.getText().toString());
                values.put(User.FNACIMIENTO, fnacimiento.getText().toString());
                values.put(User.TELEFONO, Integer.parseInt(telefono.getText().toString()));

                long idCliente = db.insert(User.TABLE_NAME, null, values);

                if (idCliente > 0) {
                    Toast.makeText(getApplicationContext(), "Insertado " + idCliente, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No Insertado " + idCliente, Toast.LENGTH_LONG).show();
                }

                db.close();
            }
        });

    }

    public boolean inputValid() {
        nombre = (EditText) findViewById(R.id.edit_modify_nombre);
        apellido = (EditText) findViewById(R.id.edit_modify_apellido);
        email = (EditText) findViewById(R.id.edit_modify_email);
        password = (EditText) findViewById(R.id.edit_modify_password);
        telefono = (EditText) findViewById(R.id.edit_modify_telefono);
        fnacimiento = (EditText) findViewById(R.id.edit_modify_fnacimiento);

        List<String> mensaje = new ArrayList<>();

        if (nombre.getText().length() == 0 || nombre.getText() == null) {
            mensaje.add("El campo de nombre está vacío");
        }
        if (apellido.getText().length() == 0) {
            mensaje.add("El campo de apellido está vacío");
        }
        if (email.getText().length() == 0) {
            mensaje.add("El campo de email está vacío");
        }
        if (password.getText().length() == 0) {
            mensaje.add("El campo de password está vacío");
        }
        if (telefono.getText().length() == 0) {
            mensaje.add("El campo de telefono está vacío");
        }
        if (fnacimiento.getText().length() == 0) {
            mensaje.add("El campo de fecha de Nacimiento está vacío");
        }

        if (mensaje.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
}
