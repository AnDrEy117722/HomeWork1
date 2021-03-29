package ru.TechPark.Homework1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBackListener {

    List<NumbersModel> number = NumberSource.getInstance().getRemoteData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportFragmentManager().findFragmentById(R.id.numbers_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.numbers_container, new FragmentListNumber())
                    .commit();
        }
    }

    public void showNextActivity(View view) {
        Button button = (Button) view;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment numbers_container = fragmentManager.findFragmentById(R.id.numbers_container);

        if (numbers_container != null && numbers_container.isAdded()){
            transaction.remove(numbers_container);
            transaction.replace(R.id.numbers_container, FragmentBigNumber.newInstance(
                    String.valueOf(button.getText()),
                    button.getTextColors().getDefaultColor()));
        }

        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onCallBack(RecyclerView recyclerView, Button addNumber) {
        int color;
        if ((number.size() + 1) % 2 == 0) {
            color = Color.BLUE;
        } else {
            color = Color.RED;
        }
        number.add(new NumbersModel(number.size() + 1, color));
        recyclerView.getAdapter().notifyItemInserted(number.size()-1);
        recyclerView.smoothScrollToPosition(number.size()-1);
    }
}