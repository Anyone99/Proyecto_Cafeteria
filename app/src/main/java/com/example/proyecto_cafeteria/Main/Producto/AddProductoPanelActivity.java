package com.example.proyecto_cafeteria.Main.Producto;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddProductoPanelActivity extends AppCompatActivity {

    private EditText edit_nombre, edit_precio, edit_stock, edit_descripcion;
    private Button button_add, button_limpiar;
    private FloatingActionButton button_volver;

    private ListView listView;
    private ArrayList<ProductoEntity> listProducto = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_producto);

        init();

    }

    public void init() {

        edit_nombre = (EditText) findViewById(R.id.edit_add_producto_nombre);
        edit_descripcion = (EditText) findViewById(R.id.edit_add_producto_descripcion);
        edit_precio = (EditText) findViewById(R.id.edit_add_producto_precio);
        edit_stock = (EditText) findViewById(R.id.edit_add_producto_stock);
        button_add = (Button) findViewById(R.id.button_add_producto_add);
        button_limpiar = (Button) findViewById(R.id.button_add_producto_limpiar);
        button_volver = (FloatingActionButton) findViewById(R.id.button_add_producto_volver);
        listView = (ListView) findViewById(R.id.listView_producto);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(getApplicationContext());
                SQLiteDatabase db = SQLiteDBHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(Producto.NOMBRE_PRODUCTO, edit_nombre.getText().toString());
                values.put(Producto.PRECIO_PRODUCTO, Float.parseFloat(edit_precio.getText().toString()));
                values.put(Producto.STOCK_PRODUCTO, Integer.parseInt(edit_stock.getText().toString()));
                values.put(Producto.DESCRIPCION_PRODUCTO, edit_descripcion.getText().toString());

                long idProducto = db.insert(Producto.TABLE_NAME, null, values);

                if (idProducto > 0) {
                    Toast.makeText(getApplicationContext(), "Insertado " + idProducto, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "No Insertado " + idProducto, Toast.LENGTH_LONG).show();
                }

            }
        });

        button_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_nombre.setText("");
                edit_descripcion.setText("");
                edit_precio.setText("");
                edit_stock.setText("");
            }
        });


    }


}
