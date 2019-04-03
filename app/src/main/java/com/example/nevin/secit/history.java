package com.example.nevin.secit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class history extends Fragment {

    RecyclerView recyclerView;
    ArrayList<customAdapter> list;

    public history() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        list.add(new customAdapter("bow"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        loderclass adapter = new loderclass(list);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
