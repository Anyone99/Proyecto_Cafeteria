package com.example.proyecto_cafeteria.Main.Cliente;

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

public class ModificarPanelActivity extends AppCompatActivity {

    private EditText edit_nombre, edit_apellido, edit_fnacimiento, edit_telefono, edit_email, edit_password;

    private Button button_modificar;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_user);

        init();


    }

    public void init() {

        edit_nombre = (EditText) findViewById(R.id.edit_modify_nombre);
        edit_apellido = (EditText) findViewById(R.id.edit_modify_apellido);
        edit_email = (EditText) findViewById(R.id.edit_modify_email);
        edit_fnacimiento = (EditText) findViewById(R.id.edit_modify_fnacimiento);
        edit_password = (EditText) findViewById(R.id.edit_modify_password);
        edit_telefono = (EditText) findViewById(R.id.edit_modify_telefono);

        button_modificar = (Button) findViewById(R.id.button_modify_modificar);


        String nombre = getIntent().getExtras().getString("nombreUsuario");
        String apellido = getIntent().getExtras().getString("apellidoUsuario");
        String fnacimiento = getIntent().getExtras().getString("fnacimientoUsuario");
        int telefono = getIntent().getExtras().getInt("telefonoUsuario");
        email = getIntent().getExtras().getString("emailUsuario");
        String password = getIntent().getExtras().getString("passwordUsuario");

        edit_nombre.setText(nombre);
        edit_apellido.setText(apellido);
        edit_telefono.setText(String.valueOf(telefono));
        edit_email.setText(email);
        edit_fnacimiento.setText(fnacimiento);
        edit_password.setText(password);

        button_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(getApplicationContext().getApplicationContext());
                SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(User.APELLIDO, edit_apellido.getText().toString());
                contentValues.put(User.NOMBRE, edit_nombre.getText().toString());
                contentValues.put(User.EMAIL, edit_email.getText().toString());
                contentValues.put(User.FNACIMIENTO, edit_fnacimiento.getText().toString());
                contentValues.put(User.PASSWORD, edit_password.getText().toString());
                contentValues.put(User.TELEFONO, Integer.parseInt(edit_telefono.getText().toString()));

                String[] args = new String[]{email};

                int id = db.update(User.TABLE_NAME, contentValues, "email = ? ", args);

                if (id > 0) {
                    Toast.makeText(getApplicationContext(), "Modificado ! ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No Modificado ! ", Toast.LENGTH_LONG).show();
                }
                db.close();
            }
        });


    }
}
