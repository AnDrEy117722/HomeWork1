package ru.TechPark.Homework1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    public void bind(NumbersModel model, CallBackListener callBackListener) {
        number.setText(String.valueOf(model.mNumber));
        number.setTextColor(model.mColor);
        number.setId(model.mNumber);
        Log.d("test", callBackListener + "3");
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                if(callBackListener != null) {
                    Log.d("test", String.valueOf(model.mNumber));
                    callBackListener.onCallBack(view1);
                }
            }
        });
    }
}
