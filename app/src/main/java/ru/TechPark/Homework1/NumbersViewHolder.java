package ru.TechPark.Homework1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersViewHolder extends RecyclerView.ViewHolder {

    private final TextView number;

    public NumbersViewHolder(@NonNull View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.number);
    }

    @SuppressLint("ResourceAsColor")
    public void bind(NumbersModel model) {
        number.setText(String.valueOf(model.mNumber));
        number.setTextColor(model.mColor);
        number.setId(model.mNumber);
    }
}
