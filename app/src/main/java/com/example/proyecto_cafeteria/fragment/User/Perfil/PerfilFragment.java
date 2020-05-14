package com.example.proyecto_cafeteria.fragment.User.Perfil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.Main.Cliente.ComentarioPanelActivity;
import com.example.proyecto_cafeteria.Main.Cliente.ModificarPanelActivity;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

public class PerfilFragment extends Fragment {

    private PerfilViewModel notificationsViewModel;

    private ImageButton button_editar;
    private TextView text_nombre, text_apellido, text_email;
    private Button button_exit, button_contactUs;
    private UserEntity userEntity = new UserEntity();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        final TextView textView = root.findViewById(R.id.text_perfil);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("onActivityCreated******************************");
        init();
    }

    public void init() {
        text_nombre = getActivity().findViewById(R.id.text_perfil_nombre);
        text_email = getActivity().findViewById(R.id.text_perfil_email);
        button_editar = getActivity().findViewById(R.id.imageButton_perfil_modificar);
        button_exit = getActivity().findViewById(R.id.button_salir_cuenta);
        button_contactUs = getActivity().findViewById(R.id.button_pefil_contactUs);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("loginUser", "user");

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(getContext());
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

        String[] args = new String[]{email};

        Cursor cursor = db.rawQuery("Select * from " + User.TABLE_NAME + " Where email = ? ", args);

        if (cursor != null) {
            cursor.moveToFirst();
            text_nombre.setText(cursor.getString(1));
            text_email.setText(email);
            userEntity = new UserEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(6), cursor.getInt(5), cursor.getString(3), cursor.getString(4));

        }


        button_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ModificarPanelActivity.class);
                intent.putExtra("nombreUsuario", userEntity.getNombre());
                intent.putExtra("apellidoUsuario", userEntity.getApellido());
                intent.putExtra("emailUsuario", userEntity.getEmail());
                intent.putExtra("passwordUsuario", userEntity.getPassword());
                intent.putExtra("telefonoUsuario", userEntity.getTelefono());
                intent.putExtra("fnacimientoUsuario", userEntity.getFnacimiento());
                startActivity(intent);
            }
        });


        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedLogin = getContext().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
                sharedLogin.edit().clear();

                SharedPreferences shareCarrito = getContext().getSharedPreferences("carritos", Context.MODE_PRIVATE);
                shareCarrito.edit().clear();

                getActivity().finish();
            }
        });


        button_contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ComentarioPanelActivity.class);
                startActivity(intent);

            }
        });


    }


}