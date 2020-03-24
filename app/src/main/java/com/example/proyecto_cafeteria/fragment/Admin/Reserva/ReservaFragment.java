package com.example.proyecto_cafeteria.fragment.Admin.Reserva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto_cafeteria.R;

public class ReservaFragment extends Fragment {

    private com.example.proyecto_cafeteria.fragment.Admin.Reserva.ReservaViewModel ReservaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReservaViewModel =
                ViewModelProviders.of(this).get(com.example.proyecto_cafeteria.fragment.Admin.Reserva.ReservaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_reserva, container, false);
        final TextView textView = root.findViewById(R.id.text_admin_reserva);
        ReservaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}