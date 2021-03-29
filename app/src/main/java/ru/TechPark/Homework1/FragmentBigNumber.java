package ru.TechPark.Homework1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentBigNumber extends Fragment {
    private static final String VALUE = "value";
    private static final String COLOR = "color";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String text = null;
        int color = Color.WHITE;
        Bundle arguments = getArguments();
        if (arguments != null) {
            text = arguments.getString(VALUE);
            color = arguments.getInt(COLOR);
        }
        TextView textView = view.findViewById(R.id.big_number_label);
        textView.setText(text);
        textView.setTextColor(color);
    }

    public static FragmentBigNumber newInstance(String big_number, int color_big_number) {
        FragmentBigNumber fragment = new FragmentBigNumber();
        Bundle bundle = new Bundle();
        bundle.putString(VALUE, big_number);
        bundle.putInt(COLOR, color_big_number);
        fragment.setArguments(bundle);
        return fragment;
    }
}
