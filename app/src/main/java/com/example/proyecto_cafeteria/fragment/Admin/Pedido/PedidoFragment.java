package com.example.proyecto_cafeteria.fragment.Admin.Pedido;

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
import com.example.proyecto_cafeteria.Entry.Pedido;
import com.example.proyecto_cafeteria.R;

import java.util.ArrayList;
import java.util.List;

public class PedidoFragment extends Fragment {

    private PedidoViewModel pedidoViewModel;

    private ListView listView;
    private TextView text_email, text_idPedido, text_precio;
    private List<Pedido> pedidos = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pedidoViewModel =
                ViewModelProviders.of(this).get(PedidoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_pedido, container, false);
        final TextView textView = root.findViewById(R.id.text_admin_pedido);
        pedidoViewModel.getText().observe(this, new Observer<String>() {
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
        listView = (ListView) getActivity().findViewById(R.id.list_usuario_pedido_historico);
        List<PedidoEntity> list = Pedido.getAll(getContext());
        PedidoAdapter pedidoAdapter = new PedidoAdapter(getContext(), list);
        listView.setAdapter(pedidoAdapter);

    }
}