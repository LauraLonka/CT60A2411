package com.example.harjoitusty20;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ChartsFragment extends Fragment {
    private ArrayList<Log> mExampleList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_charts, container, false);

        TextView textView1 = v.findViewById(R.id.charts_textView);
        TextView textView2 = v.findViewById(R.id.charts_textView2);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("log list", null);
        Type type = new TypeToken<ArrayList<Log>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

       // for (int i=0 ; i<mExampleList.size(); i++) {
        Log currentItem1 = mExampleList.get(1);
        Log currentItem2 = mExampleList.get(2);
        Log currentItem3 = mExampleList.get(3);
        Log currentItem4 = mExampleList.get(4);
        textView1.setText(currentItem1.weekNumberToString() + currentItem2.weekNumberToString() +currentItem3.weekNumberToString()
                + currentItem4.weekNumberToString());
        textView2.setText(currentItem1.toString() + currentItem2.toString() + currentItem3.toString());


        return v;
    }
}