/* Calculate CO2 Fragment: */
/* A fragment for taking users weekly eating input, adding the input into a Weekly Input -object,
   passing the object to the Results Fragment and opening the Results Fragment. */

package com.example.harjoitusty20;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculateCO2Fragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private CalculateCO2FragmentListener listener;

    private CheckBox checkBox;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private String diet;
    private Boolean lowCarbonPreference;
    private int beefLevel;
    private int fishLevel;
    private int porkPoultryLevel;
    private int dairyLevel;
    private int cheeseLevel;
    private int riceLevel;
    private int eggLevel;
    private int winterSaladLevel;
    private int restaurantSpending;
    private int weight;

    // Fragment management
    public interface CalculateCO2FragmentListener {
        void onInputASent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calculate_co2, container, false);

        // Widgets for getting users weekly food consumption input, which will be used for emission calculations
        dietSpinner(v);
        beefSeekBar(v);
        porkSeekBar(v);
        fishSeekBar(v);
        cheeseSeekBar(v);
        dairySeekBar(v);
        riceSeekBar(v);
        winterSaladSeekBar(v);
        eggSeekBar(v);
        restaurantSeekBar(v);
        weightSeekBar(v);

        // Check box to be ticked if user prefers low emission foods
        checkBox = v.findViewById(R.id.low_carbon_checkBox);

        // Button that triggers the calculation process and switches to the Results Fragment
        Button button = v.findViewById(R.id.calculate_emissions_button);
        button.setOnClickListener(this);

        return v;
    }

    // Spinner for selecting the diet (vegan, vegetarian or omnivore)
    public void dietSpinner(View v) {
        Spinner spinner = v.findViewById(R.id.diet_spinner);
        // List of diets can be found from values --> strings.xml --> diets
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.diets, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        diet = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    // Seek bar for selecting beef and lamb consumption
    public void beefSeekBar(View v) {

        SeekBar beef_lamb_seekBar = (SeekBar) v.findViewById(R.id.beef_lamb_seekBar);
        TextView beef_lamb_textView = (TextView) v.findViewById(R.id.beef_lamb_textView);

        // Setting seek bar maximum value to 200, as it is in the FoodCalculator API
        beef_lamb_seekBar.setMax(200);

        beef_lamb_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            double consumption_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                consumption_value = (double) progress_value * 0.004;
                beef_lamb_textView.setText(df2.format(consumption_value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                consumption_value = (double) progress_value * 0.004;
                beef_lamb_textView.setText(df2.format(consumption_value));
                beefLevel = progress_value;
            }
        });
    }

    // Seek bar for selecting pork and poultry consumption
    public void porkSeekBar(View v) {

        SeekBar pork_poultry_seekBar = (SeekBar) v.findViewById(R.id.pork_poultry_seekBar);
        TextView pork_poultry_textView = (TextView) v.findViewById(R.id.pork_poultry_textView);

        // Setting seek bar maximum value to 200, as it is in the FoodCalculator API
        pork_poultry_seekBar.setMax(200);

        pork_poultry_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            double consumption_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                consumption_value = (double) progress_value * 0.01;
                pork_poultry_textView.setText(df2.format(consumption_value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                consumption_value = (double) progress_value * 0.01;
                pork_poultry_textView.setText(df2.format(consumption_value));
                porkPoultryLevel = progress_value;
            }
        });
    }

    // Seek bar for selecting seafood consumption
    public void fishSeekBar(View v) {

        SeekBar fish_seafood_seekBar = (SeekBar) v.findViewById(R.id.fish_seafood_seekBar);
        TextView fish_seafood_textView = (TextView) v.findViewById(R.id.fish_seafood_textView);

        // Setting seek bar maximum value to 200, as it is in the FoodCalculator API
        fish_seafood_seekBar.setMax(200);

        fish_seafood_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            double consumption_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                consumption_value = (double) progress_value * 0.006;
                fish_seafood_textView.setText(df2.format(consumption_value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                consumption_value = (double) progress_value * 0.006;
                fish_seafood_textView.setText(df2.format(consumption_value));
                fishLevel = progress_value;
            }
        });
    }

    // Seek bar for selecting cheese consumption
    public void cheeseSeekBar(View v) {

        SeekBar cheese_seekBar = (SeekBar) v.findViewById(R.id.cheese_seekBar);
        TextView cheese_textView = (TextView) v.findViewById(R.id.cheese_textView);

        // Setting seek bar maximum value to 200, as it is in the FoodCalculator API
        cheese_seekBar.setMax(200);

        cheese_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            double consumption_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                consumption_value = (double) progress_value * 0.003;
                cheese_textView.setText(df2.format(consumption_value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                consumption_value = (double) progress_value * 0.003;
                cheese_textView.setText(df2.format(consumption_value));
                cheeseLevel = progress_value;
            }
        });
    }

    // Seek bar for selecting dairy consumption
    public void dairySeekBar(View v) {

        SeekBar dairy_seekBar = (SeekBar) v.findViewById(R.id.dairy_seekBar);
        TextView dairy_textView = (TextView) v.findViewById(R.id.dairy_textView);

        // Setting seek bar maximum value to 200, as it is in the FoodCalculator API
        dairy_seekBar.setMax(200);

        dairy_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            double consumption_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                consumption_value = (double) progress_value * 0.038;
                dairy_textView.setText(df2.format(consumption_value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                consumption_value = (double) progress_value * 0.038;
                dairy_textView.setText(df2.format(consumption_value));
                dairyLevel = progress_value;
            }
        });
    }

    // Seek bar for selecting rice consumption
    public void riceSeekBar(View v) {

        SeekBar rice_seekBar = (SeekBar) v.findViewById(R.id.rice_seekBar);
        TextView rice_textView = (TextView) v.findViewById(R.id.rice_textView);

        // Setting seek bar maximum value to 200, as it is in the FoodCalculator API
        rice_seekBar.setMax(200);

        rice_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            double consumption_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                consumption_value = (double) progress_value * 0.0009;
                rice_textView.setText(df2.format(consumption_value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                consumption_value = (double) progress_value * 0.0009;
                rice_textView.setText(df2.format(consumption_value));
                riceLevel = progress_value;
            }
        });
    }

    // Seek bar for selecting winter salad consumption
    public void winterSaladSeekBar(View v) {

        SeekBar winter_vegetables_seekBar = (SeekBar) v.findViewById(R.id.winter_vegetables_seekBar);
        TextView winter_vegetables_textView = (TextView) v.findViewById(R.id.winter_vegetables_textView);

        // Setting seek bar maximum value to 200, as it is in the FoodCalculator API
        winter_vegetables_seekBar.setMax(200);

        winter_vegetables_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            double consumption_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                consumption_value = (double) progress_value * 0.014;
                winter_vegetables_textView.setText(df2.format(consumption_value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                consumption_value = (double) progress_value * 0.014;
                winter_vegetables_textView.setText(df2.format(consumption_value));
                winterSaladLevel = progress_value;
            }
        });
    }

    // Seek bar for selecting egg consumption
    public void eggSeekBar(View v) {

        SeekBar eggs_seekBar = (SeekBar) v.findViewById(R.id.eggs_seekBar);
        TextView eggs_textView = (TextView) v.findViewById(R.id.eggs_textView);

        // Setting seek bar maximum value to 1000, as it is in the FoodCalculator API
        eggs_seekBar.setMax(1000);

        eggs_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            double consumption_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                consumption_value = (double) progress_value * 0.03;
                eggs_textView.setText(df2.format(consumption_value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                consumption_value = (double) progress_value * 0.03;
                eggs_textView.setText(df2.format(consumption_value));
                eggLevel = progress_value;
            }
        });
    }

    // Seek bar for selecting restaurant and cafe spending
    public void restaurantSeekBar(View v) {

        SeekBar restaurant_seekBar = (SeekBar) v.findViewById(R.id.restaurant_seekBar);
        TextView restaurant_textView = (TextView) v.findViewById(R.id.restaurant_textView);

        // Setting seek bar maximum value to 800, as it is in the FoodCalculator API
        restaurant_seekBar.setMax(800);

        restaurant_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                restaurant_textView.setText("" + progress_value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                restaurant_textView.setText("" + progress_value);
                restaurantSpending = progress_value;
            }
        });
    }

    // Seek bar for selecting weight
    public void weightSeekBar(View v) {

        SeekBar weight_seekBar = (SeekBar) v.findViewById(R.id.weight_seekBar);
        TextView weight_textView = (TextView) v.findViewById(R.id.weight_textView);

        // Setting seek bar maximum value to 300
        weight_seekBar.setMax(300);

        weight_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                weight_textView.setText("" + progress_value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                weight_textView.setText("" + progress_value);
                weight = progress_value;
            }
        });
    }

    // When clicking the Calculate Emissions -button:
    @Override
    public void onClick(View v) {
        // Checking, if the Low Carbon -check box is ticked and assigning boolean accordingly
        if(checkBox.isChecked()) {
            lowCarbonPreference = true;
        } else {
            lowCarbonPreference = false;
        }

        // Creating a new Weekly Input -object, which stores the data given by the user on that calculation round
        WeeklyInput new_week = new WeeklyInput();
        new_week.setDiet(diet);
        new_week.setLowCarbonPreference(lowCarbonPreference);
        new_week.setBeefLevel(beefLevel);
        new_week.setPorkPoultryLevel(porkPoultryLevel);
        new_week.setFishLevel(fishLevel);
        new_week.setCheeseLevel(cheeseLevel);
        new_week.setDairyLevel(dairyLevel);
        new_week.setRiceLevel(riceLevel);
        new_week.setWinterSaladLevel(winterSaladLevel);
        new_week.setEggLevel(eggLevel);
        new_week.setRestaurantSpending(restaurantSpending);
        new_week.setWeight(weight);

        // Opening the Results Fragment to calculate emissions and display the calculation results
        Fragment results_fragment = null;
        // Transferring the new Weekly Input -object to the Results Fragment
        results_fragment = new ResultsFragment(new_week);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, results_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Fragment management
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CalculateCO2FragmentListener) {
            listener = (CalculateCO2FragmentListener) context;
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