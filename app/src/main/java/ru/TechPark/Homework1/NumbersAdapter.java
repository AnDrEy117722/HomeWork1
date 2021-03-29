package ru.TechPark.Homework1;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class NumbersAdapter extends RecyclerView.Adapter<NumbersViewHolder> {

    List<NumbersModel> number = NumberSource.getInstance().getRemoteData();
    private final CallBackListener callBackListener;

    public NumbersAdapter(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.numbers_item, parent, false);
        return new NumbersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
        NumbersModel model = number.get(position);
        Log.d("test", callBackListener + "2");
        holder.bind(model, this.callBackListener);

    }

    @Override
    public int getItemCount() {
        return number.size();
    }

    public static void Add(View v, RecyclerView recyclerView, List<NumbersModel> number) {
        int color;
        if ((number.size() + 1) % 2 == 0) {
            color = Color.BLUE;
        } else {
            color = Color.RED;
        }
        number.add(new NumbersModel(number.size() + 1, color));
        if (recyclerView == null) {
            recyclerView = v.findViewById(R.id.numbers_feed);
        }
        recyclerView.getAdapter().notifyItemInserted(number.size()-1);
        recyclerView.smoothScrollToPosition(number.size()-1);
    }
}
