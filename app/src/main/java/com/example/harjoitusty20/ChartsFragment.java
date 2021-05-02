/* Charts Fragment: */
/* Fragment for displaying the weekly emission and weight data as line graphs */

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
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ChartsFragment extends Fragment {

    // Including Log arraylist
    private ArrayList<Log> logList;

    // Including entry list, line chart and line data for weight line graph
    private List<Entry> entryListWeight = new ArrayList<>();
    private LineChart lineChartWeight;
    private LineData lineDataWeight;

    // Including entry list, line chart and line data for total emissions line graph
    private List<Entry> entryListEmissions = new ArrayList<>();
    private LineChart lineChartEmissions;
    private LineData lineDataEmissions;

    // Including entry list, line chart and line data for meat emissions line graph
    private List<Entry> entryListMeatEmissions = new ArrayList<>();
    private LineChart lineChartMeatEmissions;
    private LineData lineDataMeatEmissions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_charts, container, false);

        // Pointing the line chart attributes to the correct UI items
        lineChartWeight = v.findViewById(R.id.lineChartWeight);
        lineChartEmissions = v.findViewById(R.id.lineChartEmissions);
        lineChartMeatEmissions = v.findViewById(R.id.lineChartMeatEmissions);

        // Accessing the Log JSON data utilizing SharesPreferences
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("log list", null);
        Type type = new TypeToken<ArrayList<Log>>() {}.getType();

        // Connecting the Log arraylist to the JSON data
        logList = gson.fromJson(json, type);

        // Assigning entry list attributes related to weight data
        for (int i=0 ; i<logList.size(); i++) {
            Log currentItem = logList.get(i);
            entryListWeight.add(new Entry((float)currentItem.getWeekNumber(),(float)currentItem.getWeight()));
        }

        // Assigning entry list attributes related to total emissions data
        for (int i=0 ; i<logList.size(); i++) {
            Log currentItem = logList.get(i);
            entryListEmissions.add(new Entry((float)currentItem.getWeekNumber(),(float)currentItem.getTotalEmissions()));
        }

        // Assigning entry list attributes related to meat emissions data
        for (int i=0 ; i<logList.size(); i++) {
            Log currentItem = logList.get(i);
            entryListMeatEmissions.add(new Entry((float)currentItem.getWeekNumber(),(float)currentItem.getMeatEmissions()));
        }

        // Further defining the weight line graph settings
        LineDataSet lineDataSetWeight = new LineDataSet(entryListWeight,"Weight");
        lineDataSetWeight.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSetWeight.setFillAlpha(100);
        lineDataWeight = new LineData(lineDataSetWeight);
        lineChartWeight.setData(lineDataWeight);
        lineChartWeight.setVisibleXRangeMaximum(300);
        lineChartWeight.invalidate();

        // Further defining the total emissions line graph settings
        LineDataSet lineDataSetEmissions = new LineDataSet(entryListEmissions,"Total emissions");
        lineDataSetEmissions.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSetEmissions.setFillAlpha(100);
        lineDataEmissions = new LineData(lineDataSetEmissions);
        lineChartEmissions.setData(lineDataEmissions);
        lineChartEmissions.setVisibleXRangeMaximum(3000);
        lineChartEmissions.invalidate();

        // Further defining the meat emissions line graph settings
        LineDataSet lineDataSetMeatEmissions = new LineDataSet(entryListMeatEmissions,"Meat emissions");
        lineDataSetMeatEmissions.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSetMeatEmissions.setFillAlpha(100);
        lineDataMeatEmissions = new LineData(lineDataSetMeatEmissions);
        lineChartMeatEmissions.setData(lineDataMeatEmissions);
        lineChartMeatEmissions.setVisibleXRangeMaximum(3000);
        lineChartMeatEmissions.invalidate();

        return v;
    }

}