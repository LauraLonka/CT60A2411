/* Adapter class: */
/* Adapter for running Log Fragment's recycler view */

package com.example.harjoitusty20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {


    private ArrayList<Log> logList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {


        public TextView textViewLine1;
        public TextView textViewLine2;

        public ExampleViewHolder(View itemView) {

            super(itemView);

            // Pointing the text view attributes to the correct UI items
            textViewLine1 = itemView.findViewById(R.id.textview_line1);
            textViewLine2 = itemView.findViewById(R.id.textview_line_2);
        }
    }

    // Creating Log-arraylist to be able to use Log-object attributes
    public Adapter(ArrayList<Log> exampleList) {
        logList = exampleList;
    }

    // Creating and returning a view holder
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    // Putting data into the view holder
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Log currentItem = logList.get(position);
        holder.textViewLine1.setText(currentItem.weekNumberToString());
        holder.textViewLine2.setText(currentItem.toString());
    }

    // Operation for returning the size of the Log arraylist to be able to count Log-objects
    @Override
    public int getItemCount() {
        return logList.size();
    }
}
