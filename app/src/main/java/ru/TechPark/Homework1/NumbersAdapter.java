package ru.TechPark.Homework1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class NumbersAdapter extends RecyclerView.Adapter<NumbersViewHolder> {

    List<NumbersModel> number = NumberSource.getInstance().getRemoteData();

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.numbers_item, parent, false);
        return new NumbersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
        NumbersModel model = number.get(position);
        holder.bind(model);

    }

    @Override
    public int getItemCount() {
        return number.size();
    }
}
