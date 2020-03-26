package com.example.proyecto_cafeteria.Main.Producto;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.Main.AdminPanelActivity;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

public class ModificarProductoPanelActivity extends AppCompatActivity {

    private EditText edit_nombre, edit_descripcion, edit_precio, edit_stock;
    private Button limpiar, modificar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_producto);

        init();

    }

    public void init() {

        edit_nombre = (EditText) findViewById(R.id.edit_modify_producto_nombre);
        edit_descripcion = (EditText) findViewById(R.id.edit_modify_producto_descripcion);
        edit_precio = (EditText) findViewById(R.id.edit_modify_producto_precio);
        edit_stock = (EditText) findViewById(R.id.edit_modify_producto_stock);
        modificar = (Button) findViewById(R.id.button_modify_producto_modify);
        limpiar = (Button) findViewById(R.id.button_modify_producto_limpiar);


        final int idProducto = getIntent().getExtras().getInt("idProducto");
        String nombre = getIntent().getExtras().getString("nombreProducto");
        String descripcion = getIntent().getExtras().getString("descripcionProducto");
        Float precio = getIntent().getExtras().getFloat("precioProducto");
        int stock = getIntent().getExtras().getInt("stockProducto");
        int imagen = getIntent().getExtras().getInt("imagenProducto");

        edit_nombre.setText(nombre);
        edit_precio.setText(String.valueOf(precio));
        edit_descripcion.setText(descripcion);
        edit_stock.setText(String.valueOf(stock));

        //Butón de modificar el producto
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDBHelper SQLiteDBHelper = new SQLiteDBHelper(getApplicationContext());
                SQLiteDatabase db = SQLiteDBHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(Producto.ID_PRODUCTO, idProducto);
                contentValues.put(Producto.NOMBRE_PRODUCTO, edit_nombre.getText().toString());
                contentValues.put(Producto.DESCRIPCION_PRODUCTO, edit_descripcion.getText().toString());
                contentValues.put(Producto.PRECIO_PRODUCTO, Float.parseFloat(edit_precio.getText().toString()));
                contentValues.put(Producto.STOCK_PRODUCTO, Integer.parseInt(edit_stock.getText().toString()));

                String[] args = new String[]{String.valueOf(idProducto)};
                int id = db.update(Producto.TABLE_NAME, contentValues, Producto.ID_PRODUCTO + " = ? ", args);
                if (id > 0) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), AdminPanelActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Modificado " + id, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No ha sido Modificado " + id, Toast.LENGTH_SHORT).show();
                }
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_nombre.setText("");
                edit_precio.setText("");
                edit_stock.setText("");
                edit_descripcion.setText("");
            }
        });


    }

}
