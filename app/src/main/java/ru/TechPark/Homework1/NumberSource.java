package ru.TechPark.Homework1;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NumberSource extends AppCompatActivity {

    private final List<NumbersModel> mValues;
    private static NumberSource sInstance;

    public NumberSource() {
        mValues = new ArrayList<>();
        int[] mColors = new int[]{
                Color.BLUE,
                Color.RED
        };
        for (int i = 1; i <= 100; i++) {
            mValues.add(new NumbersModel(i, mColors[i % 2]));
        }
    }

    public List<NumbersModel> getRemoteData() { return mValues; }
    public synchronized static NumberSource getInstance() {
        if (sInstance == null) {
            sInstance = new NumberSource();
        }
        return sInstance;
    }
}
