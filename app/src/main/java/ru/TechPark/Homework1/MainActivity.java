package ru.TechPark.Homework1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<NumbersModel> number = NumberSource.getInstance().getRemoteData();
    private final String KEY_RECYCLER_STATE = "recycler_state";
    private final String REAL_SIZE = "real_size";
    private RecyclerView recyclerView;
    private static Bundle mBundleRecyclerViewState;

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

        Button addNumber = findViewById(R.id.add_number);
        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color;
                if ((number.size() + 1) % 2 == 0) {
                    color = Color.parseColor(getString(R.string.BLUE));
                } else {
                    color = Color.parseColor(getString(R.string.RED));
                }
                number.add(new NumbersModel(number.size() + 1, color));
                if (recyclerView == null) {
                    recyclerView = findViewById(R.id.numbers_feed);
                }
                recyclerView.getAdapter().notifyItemInserted(number.size()-1);
                recyclerView.smoothScrollToPosition(number.size()-1);
            }
        });
    }

    public void showNextActivity(View view) {
        Button button = (Button) view;
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("big_number", button.getText());
        intent.putExtra("color_big_number", button.getTextColors().getDefaultColor());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (recyclerView == null) {
            recyclerView = findViewById(R.id.numbers_feed);
        }
        final GridLayoutManager layoutManager = new GridLayoutManager(this,
                getResources().getInteger(R.integer.orient));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new NumbersAdapter());

        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
        outState.putParcelable(KEY_RECYCLER_STATE, listState);
        outState.putInt(REAL_SIZE, number.size());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable listState = savedInstanceState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);

            int size = savedInstanceState.getInt(REAL_SIZE);
            if (size != number.size()) {
                for (int i = 101; i <= size; i++) {
                    int color;
                    if ((number.size() + 1) % 2 == 0) {
                        color = Color.parseColor(getString(R.string.BLUE));
                    } else {
                        color = Color.parseColor(getString(R.string.RED));
                    }
                    number.add(new NumbersModel(number.size() + 1, color));
                    recyclerView.getAdapter().notifyItemInserted(number.size() - 1);
                }
            }
        }
    }
}