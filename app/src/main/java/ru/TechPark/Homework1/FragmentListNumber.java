package ru.TechPark.Homework1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragmentListNumber extends Fragment {

    List<NumbersModel> number = NumberSource.getInstance().getRemoteData();
    private RecyclerView recyclerView;
    private final String REAL_SIZE = "real_size";
    private static Bundle mBundleRecyclerViewState;

    private CallBackListener callBackListener;

    public FragmentListNumber() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_number_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof CallBackListener) {
            callBackListener = (CallBackListener) getActivity();
        }
        if (recyclerView == null) {
            recyclerView = getActivity().findViewById(R.id.numbers_feed);
        }
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),
                getResources().getInteger(R.integer.orient));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new NumbersAdapter());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button addNumber = (Button) view.findViewById(R.id.add_number);
        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBackListener != null)
                    callBackListener.onCallBack(recyclerView, addNumber);

            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundleRecyclerViewState == null) {
            mBundleRecyclerViewState = new Bundle();
        }
        mBundleRecyclerViewState.putInt(REAL_SIZE, number.size());
        outState.putInt(REAL_SIZE, number.size());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (mBundleRecyclerViewState != null && savedInstanceState == null) {
            savedInstanceState = mBundleRecyclerViewState;
        }
        if (savedInstanceState != null) {
            int size = savedInstanceState.getInt(REAL_SIZE);
            if (size != number.size()) {
                for (int i = 101; i <= size; i++) {
                    int color;
                    if ((number.size() + 1) % 2 == 0) {
                        color = Color.BLUE;
                    } else {
                        color = Color.RED;
                    }
                    number.add(new NumbersModel(number.size() + 1, color));
                    recyclerView.getAdapter().notifyItemInserted(number.size() - 1);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBundleRecyclerViewState == null) {
            mBundleRecyclerViewState = new Bundle();
        }
        if (recyclerView != null) {
            mBundleRecyclerViewState.putInt(REAL_SIZE, number.size());
            recyclerView = null;
        }
    }
}