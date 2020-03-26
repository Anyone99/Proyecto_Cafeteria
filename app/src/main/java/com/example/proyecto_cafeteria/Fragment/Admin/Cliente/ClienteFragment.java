package com.example.proyecto_cafeteria.Fragment.Admin.Cliente;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.Main.Cliente.ModificarPanelActivity;
import com.example.proyecto_cafeteria.Main.Cliente.RegistrarPanelActivity;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

import java.util.ArrayList;

public class ClienteFragment extends Fragment {

    private ClienteViewModel clienteViewModel;
    private EditText edit_buscar;
    private Button button_buscar, button_add, button_modificar, button_eliminar;
    private ListView listView;
    private ArrayList<UserEntity> listaCliente = new ArrayList<>();
    String[] args;

    private SQLiteDatabase db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        clienteViewModel =
                ViewModelProviders.of(this).get(ClienteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_cliente, container, false);
        final TextView textView = root.findViewById(R.id.text_admin_cliente);
        clienteViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("onActivityCreated******************************");
        init();
    }

    public void init() {
        button_buscar = getActivity().findViewById(R.id.button_cliente_buscar);
        button_add = getActivity().findViewById(R.id.button_cliente_add);
        button_eliminar = getActivity().findViewById(R.id.button_cliente_eliminar);
        button_modificar = getActivity().findViewById(R.id.button_cliente_modificar);
        edit_buscar = getActivity().findViewById(R.id.edit_cliente_buscar);

        listView = (ListView) getActivity().findViewById(R.id.listView_Cliente);


        button_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_buscar.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "El campo de busqueda no puede ser vacío", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(getActivity().getApplicationContext());
                    SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();
                    String[] args = new String[]{edit_buscar.getText().toString()};
                    String[] columna = new String[]{User.ID_USUARIO, User.NOMBRE, User.APELLIDO, User.FNACIMIENTO, User.PASSWORD, User.TELEFONO};
                    Cursor cursor = db.query(User.TABLE_NAME, columna, "email = ?", args, null, null, null);
                    UserEntity user = new UserEntity();

                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            user = new UserEntity(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(5)), edit_buscar.getText().toString(), cursor.getString(4));
                            listaCliente.add(user);
                            ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.item_cliente, listaCliente);
                            listView.setAdapter(adapter);

                        } else {
                            Toast.makeText(getContext(), "No existe el correo ", Toast.LENGTH_SHORT).show();;
                        }
                    }
                }
            }
        });


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), RegistrarPanelActivity.class);
                getActivity().startActivity(intent);

            }
        });

        listView.setOnItemClickListener(onClickListView);


    }

    /**
     * Selecciona la lista. y modificar o eliminar.
     */
    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            button_modificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserEntity user = listaCliente.get(position);
                    Intent intent = new Intent();
                    intent.setClass(getContext().getApplicationContext(), ModificarPanelActivity.class);

                    intent.putExtra("nombreUsuario", user.getNombre());
                    intent.putExtra("apellidoUsuario", user.getApellido());
                    intent.putExtra("emailUsuario", user.getEmail());
                    intent.putExtra("passwordUsuario", user.getPassword());
                    intent.putExtra("telefonoUsuario", user.getTelefono());
                    intent.putExtra("fnacimientoUsuario", user.getFnacimiento());
                    startActivity(intent);

                }
            });


            button_eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserEntity user = listaCliente.get(position);
                    SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(getContext());
                    db = sqLiteDBHelper.getWritableDatabase();
                    args = new String[]{user.getEmail()};

                    int idDel = db.delete(User.TABLE_NAME, "email = ? ", args);
                    if (idDel > 0) {
                        Toast.makeText(getContext(), "Eliminado", Toast.LENGTH_LONG).show();
                        listaCliente.remove(position);
                        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.item_cliente, listaCliente);
                        listView.setAdapter(adapter);
                    }
                }
            });
        }
    };


}