/* Log fragment: */
/* Fragment for displaying all of the log entries in text format */

package com.example.harjoitusty20;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class LogFragment extends Fragment {

    ArrayList<Log> logList;

    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int weight;
    private double dairy_em;
    private double meat_em;
    private double plant_em;
    private double restaurant_em;
    private double total_em;

    // Getting data from the Log object and assigning it to the attribute in the fragment
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

        // Calling the operations defined below
        loadData();
        buildRecyclerView(v);
        setInsertButton(v);

        // Assigning "Save to Log" -button
        Button buttonSave = v.findViewById(R.id.log_save_button);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Calling save data operation that is defined below
                saveData();

                // Display toast message when pressing the "Save to Log" -button
                Toast.makeText(getContext(), "Log state saved", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void saveData() {

        // Saving the Log JSON data utilizing SharesPreferences
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(logList);
        editor.putString("log list", json);
        editor.apply();
    }


    private void loadData() {

        // Loading the Log JSON data utilizing SharesPreferences
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("log list", null);
        Type type = new TypeToken<ArrayList<Log>>() {}.getType();
        logList = gson.fromJson(json, type);

        if (logList == null) {
            logList = new ArrayList<>();
        }
    }

    // Building recycler view for displaying the Log entries in a scroll-down list
    private void buildRecyclerView(View v) {

        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(v.getContext());
        adapter = new Adapter(logList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    // Operation for adding a new entry to the Log entry list
    private void setInsertButton(View v) {

        Button buttonInsert = v.findViewById(R.id.log_add_button);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItem(weight, dairy_em, meat_em, plant_em, restaurant_em, total_em);
                Toast.makeText(getContext(), "Results added to Log", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Operation for adding new entry to the Log array list and calling the adapter
    private void insertItem(int weight, double dairy_em, double meat_em, double plant_em, double restaurant_em, double total_em) {

        logList.add(new Log(weight, dairy_em, meat_em, plant_em, restaurant_em, total_em, logList.size()));
        adapter.notifyItemInserted(logList.size());
    }
}