package com.example.harjoitusty20;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

public class LogFragment extends Fragment {

    ArrayList<Log> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int weight;
    private double dairy_em;
    private double meat_em;
    private double plant_em;
    private double restaurant_em;
    private double total_em;

    public LogFragment(Log new_log) {
        weight = new_log.getWeight();
        dairy_em = new_log.getDairyEmissions();
        meat_em = new_log.getMeatEmissions();
        plant_em = new_log.getPlantEmissions();
        restaurant_em = new_log.getRestaurantEmissions();
        total_em = new_log.getTotalEmissions();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);

        loadData();
        buildRecyclerView(v);
        setInsertButton(v);

        Button buttonSave = v.findViewById(R.id.log_save_button);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        return v;
    }

    private void saveData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("log list", json);
        editor.apply();
    }


    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("log list", null);
        Type type = new TypeToken<ArrayList<Log>>() {}.getType();
        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    private void buildRecyclerView(View v) {
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setInsertButton(View v) {
        Button buttonInsert = v.findViewById(R.id.log_add_button);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItem(weight, dairy_em, meat_em, plant_em, restaurant_em, total_em);
            }
        });
    }

    private void insertItem(int weight, double dairy_em, double meat_em, double plant_em, double restaurant_em, double total_em) {
        mExampleList.add(new Log(weight, dairy_em, meat_em, plant_em, restaurant_em, total_em, mExampleList.size()));
        mAdapter.notifyItemInserted(mExampleList.size());
    }

}