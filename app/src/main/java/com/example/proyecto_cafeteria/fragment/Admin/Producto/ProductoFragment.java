package com.example.proyecto_cafeteria.fragment.Admin.Producto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.Main.Producto.AddProductoPanelActivity;
import com.example.proyecto_cafeteria.Main.Producto.ModificarProductoPanelActivity;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Adapter.ProductoAdaptador;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;
import com.example.proyecto_cafeteria.Utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class ProductoFragment extends Fragment {

    private Button button_delete, button_modify, button_add, button_buscar;
    private EditText edit_buscar;
    private ListView listView_producto;
    private ProductoAdaptador productoAdaptador;
    private List<ProductoEntity> listProducto = new ArrayList<>();
    private CardView cardView;
    private TextView text_producto_title;


    private ProductoViewModel productoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productoViewModel =
                ViewModelProviders.of(this).get(ProductoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_producto, container, false);
        final TextView textView = root.findViewById(R.id.text_admin_producto);
        productoViewModel.getText().observe(this, new Observer<String>() {
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
        listView_producto = (ListView) getActivity().findViewById(R.id.listView_producto);
        button_delete = getActivity().findViewById(R.id.button_producto_delete);
        button_add = getActivity().findViewById(R.id.button_producto_add);
        button_modify = getActivity().findViewById(R.id.button_producto_modify);
        edit_buscar = getActivity().findViewById(R.id.edit_producto_nombre);
        button_buscar = getActivity().findViewById(R.id.button_producto_buscar);
        cardView = getActivity().findViewById(R.id.card_producto);
        text_producto_title = getActivity().findViewById(R.id.text_producto_title);


        //Botón de Añadir los productos.
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), AddProductoPanelActivity.class);
                startActivity(intent);

            }
        });

        //Botón de Buscar el contenido.
        button_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listProducto.removeAll(listProducto);

                String contenido = edit_buscar.getText().toString();

                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(getContext());
                SQLiteDatabase db = SQLiteDBHelper.getWritableDatabase();
                //Si el contenido es vacío... aparece un toast..
                if (contenido.isEmpty()) {
                    Toast.makeText(getContext(), "El campo de buscar es required", Toast.LENGTH_LONG).show();
                } else {
                    //si el contenido es un númerio = id;
                    if (Utilidades.isNumero(contenido)) {
                        String[] args = new String[]{contenido};
                        Cursor cursor = db.rawQuery("Select * from " + Producto.TABLE_NAME + " Where " + Producto.ID_PRODUCTO + " = ?", args);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            ProductoEntity producto = new ProductoEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), Float.parseFloat(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), R.mipmap.cafeicon);
                            listProducto.add(producto);
                            Toast.makeText(getContext(), listProducto.toString(), Toast.LENGTH_LONG).show();
                        }
                        productoAdaptador = new ProductoAdaptador(getContext(), listProducto);
                        listView_producto.setAdapter(productoAdaptador);

                    } else {
                        //si el contenido es un nombre.
                        contenido = contenido.toLowerCase();
                        String[] args = new String[]{"%" + contenido + "%"};

                        String[] columns = new String[]{Producto.ID_PRODUCTO, Producto.NOMBRE_PRODUCTO, Producto.DESCRIPCION_PRODUCTO, Producto.PRECIO_PRODUCTO, Producto.STOCK_PRODUCTO};

                        Cursor cursor = db.query(Producto.TABLE_NAME, columns, Producto.NOMBRE_PRODUCTO + " LIKE ?", args, null, null, null);
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                ProductoEntity producto = new ProductoEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), Float.parseFloat(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), R.mipmap.cafeicon);
                                listProducto.add(producto);
                                System.out.println(producto.toString());
                            }
                            productoAdaptador = new ProductoAdaptador(getContext(), listProducto);
                            listView_producto.setAdapter(productoAdaptador);
                        }
                    }
                }
            }
        });


        listView_producto.setOnItemClickListener(onClickListView);
    }

    /**
     * Selecciona el item de la lista, y depués hacer la modificación o eliminación.
     */
    private AdapterView.OnItemClickListener onClickListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            Toast.makeText(getActivity().getApplicationContext(), "Click " + listProducto.get(position), Toast.LENGTH_LONG).show();

            button_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductoEntity productoEntity = listProducto.get(position);
                    SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(getContext());
                    SQLiteDatabase db = SQLiteDBHelper.getWritableDatabase();
                    if (productoEntity != null) {
                        String[] args = new String[]{String.valueOf(productoEntity.getIdProducto())};
                        int idProducto = db.delete(Producto.TABLE_NAME, Producto.ID_PRODUCTO + " = ? ", args);
                        if (idProducto > 0) {
                            listProducto.remove(position);
                            Toast.makeText(getContext(), "Eliminado " + idProducto, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "No ha sido eliminado " + idProducto, Toast.LENGTH_SHORT).show();

                        }
                        productoAdaptador = new ProductoAdaptador(getContext(), listProducto);
                        listView_producto.setAdapter(productoAdaptador);

                    }
                }
            });

            button_modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductoEntity productoEntity = listProducto.get(position);
                    Toast.makeText(getContext(), productoEntity.toString(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(getContext(), ModificarProductoPanelActivity.class);
                    intent.putExtra("idProducto", productoEntity.getIdProducto());
                    intent.putExtra("nombreProducto", productoEntity.getNombre());
                    intent.putExtra("descripcionProducto", productoEntity.getDescripcion());
                    intent.putExtra("precioProducto", productoEntity.getPrecio());
                    intent.putExtra("stockProducto", productoEntity.getStock());
                    intent.putExtra("imagenProducto", productoEntity.getIdImagen());
                    startActivity(intent);

                }
            });
        }
    };

}