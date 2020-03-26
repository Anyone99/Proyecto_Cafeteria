package com.example.proyecto_cafeteria.Fragment.User.Pedido;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto_cafeteria.Adapter.PedidoAdapter;
import com.example.proyecto_cafeteria.Entity.PedidoEntity;
import com.example.proyecto_cafeteria.Entity.UserEntity;
import com.example.proyecto_cafeteria.Entry.Pedido;
import com.example.proyecto_cafeteria.Entry.User;
import com.example.proyecto_cafeteria.R;

import java.util.List;

public class PedidoFragment extends Fragment {

    private PedidoViewModel pedidoViewModel;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pedidoViewModel =
                ViewModelProviders.of(this).get(PedidoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pedido, container, false);
        final TextView textView = root.findViewById(R.id.text_pedido);
        pedidoViewModel.getText().observe(this, new Observer<String>() {
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
        init();
    }

    public void init() {

        listView = (ListView) getActivity().findViewById(R.id.list_usuario_pedido_historico);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("loginUser", "null");

        UserEntity userEntity = User.findByEmail(email, getContext());

        List<PedidoEntity> list = Pedido.findByUser(userEntity, getContext());

        PedidoAdapter pedidoAdapter = new PedidoAdapter(getContext(), list);

        listView.setAdapter(pedidoAdapter);

    }
}