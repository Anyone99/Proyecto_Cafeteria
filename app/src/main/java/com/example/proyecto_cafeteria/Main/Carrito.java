package com.example.proyecto_cafeteria.Main;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cafeteria.Adapter.CarritoAdapter;
import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Carrito extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Set<String> list_idProducto = new HashSet<>();
    private Set<String> list_cantidad = new HashSet<>();
    private HashMap<ProductoEntity, Integer> carrito;

    private Button button_pagar, button_eliminar;
    private CheckBox checkBox_selectAll;
    private TextView precioTotal;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrito);
        init();
    }

    public void init() {
        carrito = obtenerCarrito();
        listView = (ListView) findViewById(R.id.list_carrito);
        CarritoAdapter carritoAdapter = new CarritoAdapter(this,carrito);
        listView.setAdapter(carritoAdapter);



        if (carrito != null) {

        }

    }

    public HashMap<ProductoEntity, Integer> obtenerCarrito() {

        if (carrito == null) {
            carrito = new HashMap<>();
        }

        sharedPreferences = getSharedPreferences("carritos", Context.MODE_PRIVATE);
        list_idProducto = sharedPreferences.getStringSet("list_carrito_idProducto", null);
        list_cantidad = sharedPreferences.getStringSet("list_carrito_cantidad", null);

        SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(this);
        SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

        //Iterator de lista de idProducto y lista de cantidad
        Iterator<String> iterator = list_idProducto.iterator();
        Iterator<String> iterator_cantidad = list_cantidad.iterator();
        //Comprobar si los dos hay datos
        while (iterator.hasNext() && iterator_cantidad.hasNext()) {
            String idProducto = iterator.next();
            String cantidad = iterator_cantidad.next();

            //Obtener datos de Producto por id de Producto;
            String[] args = new String[]{idProducto};
            Cursor cursor = db.rawQuery("Select * from Producto Where " + Producto.ID_PRODUCTO + " = ? ", args);

            //comprobar el datos es nulo o no.
            if (cursor != null) {
                cursor.moveToFirst();

                ProductoEntity productoEntity = new ProductoEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getInt(4), R.mipmap.cafeicon);
                if (productoEntity != null) {
                    carrito.put(productoEntity, Integer.parseInt(cantidad));
                    System.out.println("Carrito Total " + carrito);
                }
            }
        }
        return carrito;
    }
}
