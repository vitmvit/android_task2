package com.clevertec.task2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertec.task2.adapter.MyAdapter;
import com.clevertec.task2.interfaces.OnBackPressedListener;
import com.clevertec.task2.model.RecyclerItem;
import com.clevertec.task_2.R;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements OnBackPressedListener {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<RecyclerItem> listItems;
    private TextView txtTitle;
    private TextView txtDescription;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        listItems = generationData();

        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public ArrayList generationData() {

        ArrayList listItems = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            listItems.add(new RecyclerItem("Title " + (i + 1), "Description " + (i + 1)));
        }
        return listItems;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void toSecondFragment(View view) {

        txtTitle = view.findViewById(R.id.textViewTitle);
        txtDescription = view.findViewById(R.id.textViewDescription);

        Bundle result = new Bundle();
        result.putString("argTitle", String.valueOf(txtTitle.getText()));
        result.putString("argDescription", String.valueOf(txtDescription.getText()));
        getParentFragmentManager().setFragmentResult("key", result);

        getParentFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container_fragments, SecondFragment.newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}