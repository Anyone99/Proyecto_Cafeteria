package com.example.proyecto_cafeteria.Main;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_cafeteria.Adapter.CarritoAdapter;
import com.example.proyecto_cafeteria.Entity.ProductoEntity;
import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.Entry.ListaPedido;
import com.example.proyecto_cafeteria.Entry.Pedido;
import com.example.proyecto_cafeteria.Entry.Producto;
import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.R;
import com.example.proyecto_cafeteria.Utilidades.SQLiteDBHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Carrito extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Set<String> list_carrito = new HashSet<>();
    private List<ProductoEntity> listaProducto = new ArrayList<>();
    private List<Integer> listaCantidad = new ArrayList<>();

    private Button button_terminar;
    private TextView text_precioTotal;
    private ListView listView;
    private Float precioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrito);
        init();

    }

    public void init() {
        precioTotal = 0f;

        text_precioTotal = findViewById(R.id.text_carrito_precio);
        listView = (ListView) findViewById(R.id.list_carrito);
        button_terminar = (Button) findViewById(R.id.button_carrito_terminar);

        sharedPreferences = getSharedPreferences("carritos", Context.MODE_PRIVATE);
        list_carrito = sharedPreferences.getStringSet("lista_carrito", null);

        //Iterator de lista de idProducto y lista de cantidad
        Iterator<String> iterator = list_carrito.iterator();

        final SQLiteDBHelper sqLiteDBHelper = new SQLiteDBHelper(this);
        final SQLiteDatabase db = sqLiteDBHelper.getWritableDatabase();

        while (iterator.hasNext()) {
            String[] parts = iterator.next().split("-");
            String idProducto = parts[0]; // id
            String cantidad = parts[1]; // cantidad

            //Obtener datos de Producto por id de Producto;
            String[] args = new String[]{idProducto};
            Cursor cursor = db.rawQuery("Select * from Producto Where " + Producto.ID_PRODUCTO + " = ? ", args);


            //comprobar el datos es nulo o no.
            if (cursor != null) {
                cursor.moveToFirst();
                ProductoEntity productoEntity = new ProductoEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getInt(4), R.mipmap.cafeicon);
                if (productoEntity != null) {
                    listaCantidad.add(Integer.parseInt(cantidad));
                    listaProducto.add(productoEntity);
                    precioTotal = precioTotal + productoEntity.getPrecio() * Integer.parseInt(cantidad);
                }
                cursor.close();

                text_precioTotal.setText(String.valueOf(precioTotal));
                CarritoAdapter carritoAdapter = new CarritoAdapter(this, listaCantidad, listaProducto);
                listView.setAdapter(carritoAdapter);

            }
        }

        //Button de terminar el pedido.
        button_terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtener login desde shared.
                SharedPreferences sharedPreferences = getSharedPreferences("loginSession", MODE_PRIVATE);

                String email = sharedPreferences.getString("loginUser", "No existe");

                UserEntity userEntity = User.findByEmail(email, getApplicationContext());

                if (userEntity != null) {
                    //Pasar String a Date
                    Date now = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    ContentValues values = new ContentValues();
                    values.put(Pedido.ID_USUARIO, userEntity.getIdUsuario());
                    values.put(Pedido.FECHA_PEDIDO, dateFormat.format(now));

                    //Guardar el pedido.
                    long idPedido = Pedido.save(values, getApplicationContext());
                    if (idPedido > 0) {
                        Iterator<String> iterator = list_carrito.iterator();
                        while (iterator.hasNext()) {
                            String[] parts = iterator.next().split("-");
                            String idProducto = parts[0]; // id
                            String cantidad = parts[1]; // cantidad

                            ContentValues val = new ContentValues();
                            val.put(ListaPedido.ID_PEDIDO, idPedido);
                            val.put(ListaPedido.ID_PRODUCTO, Integer.parseInt(idProducto));
                            val.put(ListaPedido.CANTIDAD_PEDIDO, Integer.parseInt(cantidad));

                            //Guardar Lista de Pedido dentro del base de datos
                            long idListaPedido = ListaPedido.save(val, getApplicationContext());

                            if (idListaPedido > 0) {
                                Toast.makeText(getApplicationContext(), "El pedido ya está hecho !", Toast.LENGTH_SHORT).show();

                                //Después del carrito, eliminar todos los contenidos.
                                list_carrito.removeAll(list_carrito);
                                listaCantidad.clear();
                                listaProducto.clear();

                                sharedPreferences = getSharedPreferences("carritos", Context.MODE_PRIVATE);
                                sharedPreferences.edit().clear();

                                Intent intent = new Intent();
                                intent.setClass(getApplicationContext(), UserPanelActivity.class);
                                startActivity(intent);

                            }
                        }

                    }
                }
            }
        });


    }


}
