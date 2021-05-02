/* Results Chart fragment: */
/* Fragment for displaying the emission calculation results in a pie chart */

package com.example.harjoitusty20;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;

public class ResultsChartFragment extends Fragment implements View.OnClickListener{

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private int weight;
    private double dairyEmissions;
    private double meatEmissions;
    private double plantEmissions;
    private double restaurantEmissions;
    private double totalEmissions;

    Log new_log = new Log();

    public ResultsChartFragment (Log new_log) {
        weight = new_log.getWeight();
        dairyEmissions = new_log.getDairyEmissions();
        meatEmissions = new_log.getMeatEmissions();
        plantEmissions = new_log.getPlantEmissions();
        restaurantEmissions = new_log.getRestaurantEmissions();
        totalEmissions = new_log.getTotalEmissions();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_results_chart, container, false);

        int dairy_percentage = 100 * (int) dairyEmissions / (int) totalEmissions;
        int meat_percentage = 100 * (int) meatEmissions / (int) totalEmissions;
        int plant_percentage = 100 * (int) plantEmissions / (int) totalEmissions;
        int restaurant_percentage = 100 * (int) restaurantEmissions / (int) totalEmissions;

        // Creating the object of TextView and PieChart class
        TextView tvR, tvPython, tvCPP, tvJava;
        PieChart pieChart;

        // Linking those objects with their id's that are given in .XML file
        tvR = v.findViewById(R.id.tvR);
        tvPython = v.findViewById(R.id.tvPython);
        tvCPP = v.findViewById(R.id.tvCPP);
        tvJava = v.findViewById(R.id.tvJava);
        pieChart = v.findViewById(R.id.piechart);

        // Setting the percentage of emission sources
        tvR.setText(Integer.toString(meat_percentage));
        tvPython.setText(Integer.toString(plant_percentage));
        tvCPP.setText(Integer.toString(dairy_percentage));
        tvJava.setText(Integer.toString(restaurant_percentage));

        // Setting the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Meat and fish",
                        Integer.parseInt(tvR.getText().toString()),
                        Color.parseColor("#AA6A09")));
        pieChart.addPieSlice(
                new PieModel(
                        "Plant based foods",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#EC8F04")));
        pieChart.addPieSlice(
                new PieModel(
                        "Dairy products",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#F9AA33")));
        pieChart.addPieSlice(
                new PieModel(
                        "Cafes and restaurants",
                        Integer.parseInt(tvJava.getText().toString()),
                        Color.parseColor("#FBCE89")));

        // To animate the pie chart
        pieChart.startAnimation();

        // Button that saves results to Log
        Button button = v.findViewById(R.id.add_to_log_button);
        button.setOnClickListener(this);


        return v;
    }

    // When clicking the Add to Log -button:
    @Override
    public void onClick(View v) {
        new_log.setWeight(weight);
        new_log.setDairyEmissions(dairyEmissions);
        new_log.setMeatEmissions(meatEmissions);
        new_log.setPlantEmissions(plantEmissions);
        new_log.setRestaurantEmissions(restaurantEmissions);
        new_log.setTotalEmissions(totalEmissions);

        Fragment log_fragment = null;
        log_fragment = new LogFragment(new_log);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, log_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}