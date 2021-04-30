/* Results Fragment: */
/* A fragment for sending users weekly eating input to the FoodCalculator API,
   receiving back the calculated CO2 emissions and displaying the results. The user
   can also open the Charts Fragment from this fragment.*/

package com.example.harjoitusty20;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class ResultsFragment extends Fragment implements View.OnClickListener {

    private ResultsFragment.ResultsFragmentListener listener;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private String diet;
    private Boolean lowCarbonPreference;
    private int beefLevel;
    private int fishLevel;
    private int porkPoultryLevel;
    private int dairyLevel;
    private int cheeseLevel;
    private  int riceLevel;
    private int eggLevel;
    private int winterSaladLevel;
    private int restaurantSpending;

    private int weight;

    private double dairyEmissions;
    private double meatEmissions;
    private double plantEmissions;
    private double restaurantEmissions;
    private double totalEmissions;

    Log new_log = new Log();

    // Getting values from a Weekly Input -object
    public ResultsFragment(WeeklyInput weeklyInput) {
        diet = weeklyInput.getDiet();
        lowCarbonPreference = weeklyInput.getLowCarbonPreference();
        beefLevel = weeklyInput.getBeefLevel();
        fishLevel = weeklyInput.getFishLevel();
        porkPoultryLevel = weeklyInput.getPorkPoultryLevel();
        dairyLevel = weeklyInput.getDairyLevel();
        cheeseLevel = weeklyInput.getCheeseLevel();
        riceLevel = weeklyInput.getRiceLevel();
        eggLevel = weeklyInput.getEggLevel();
        winterSaladLevel = weeklyInput.getWinterSaladLevel();
        restaurantSpending = weeklyInput.getRestaurantSpending();
        weight = weeklyInput.getWeight();
    }

    // Fragment management
    public interface ResultsFragmentListener {
        void onInputASent(CharSequence input);
    }

    // Fragment management
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_results, container, false);

        final TextView total_textView = v.findViewById(R.id.results_total_textView);

        // Instantiating the Request Queue to make a contact request to the FoodCalculator API
        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        // Defining the url that will be used to contact the API.
        // Url includes the values from the Weekly Input -object,
        // which need to be sent to the API in order to calculate the weekly emissions.
        String url ="https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet="
                +diet+"&query.lowCarbonPreference="+lowCarbonPreference+"&query.beefLevel="+beefLevel+"&query.fishLevel="
                +fishLevel+"&query.porkPoultryLevel="+porkPoultryLevel+"&query.dairyLevel="+dairyLevel+"&query.cheeseLevel="
                +cheeseLevel+"&query.riceLevel="+riceLevel+"&query.eggLevel="+eggLevel+"&query.winterSaladLevel="
                +winterSaladLevel+"&query.restaurantSpending="+restaurantSpending;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            // Separating the value titled 'Dairy' from the API response object
                            String dairy = response.getString("Dairy");
                            dairyEmissions = Double.parseDouble(dairy);
                            new_log.setDairyEmissions(dairyEmissions);

                            // Separating the value titled 'Meat' from the API response object
                            String meat = response.getString("Meat");
                            meatEmissions = Double.parseDouble(meat);
                            new_log.setMeatEmissions(meatEmissions);

                            // Separating the value titled 'Plant' from the API response object
                            String plant = response.getString("Plant");
                            plantEmissions = Double.parseDouble(plant);
                            new_log.setPlantEmissions(plantEmissions);

                            // Separating the value titled 'Plant' from the API response object
                            String restaurant = response.getString("Restaurant");
                            restaurantEmissions = Double.parseDouble(restaurant);
                            new_log.setRestaurantEmissions(restaurantEmissions);

                            // Separating the value titled 'Total' from the API response object
                            String total = response.getString("Total");
                            totalEmissions = Double.parseDouble(total);
                            new_log.setTotalEmissions(totalEmissions);

                            // Displaying the total weekly emissions with two decimals
                            total_textView.setText(df2.format(totalEmissions) + " kg CO2e");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                total_textView.setText("Error occured.");
            }
        });
        // Adding the API request to the Request Queue
        queue.add(request);

        // Button that switches to the Result Charts Fragment
        Button button = v.findViewById(R.id.charts_button);
        button.setOnClickListener(this);

        return v;
    }

    // When clicking the "Look at some cool charts" -button:
    @Override
    public void onClick(View v) {
        new_log.setWeight(weight);

        // Opening the Charts Fragment
        Fragment results_chart_fragment = null;
        results_chart_fragment = new ResultsChartFragment(new_log);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, results_chart_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Fragment management
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ResultsFragment.ResultsFragmentListener) {
            listener = (ResultsFragment.ResultsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CalculateCO2FragmentListener");
        }
    }

    // Fragment management
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}