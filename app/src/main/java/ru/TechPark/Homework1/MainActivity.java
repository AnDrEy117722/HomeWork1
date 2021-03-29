package ru.TechPark.Homework1;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

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

}