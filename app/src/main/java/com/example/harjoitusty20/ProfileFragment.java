/* Profile fragment: */
/* Fragment for displaying user information */

package com.example.harjoitusty20;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView name_textView = v.findViewById(R.id.profile_name_textView);
        TextView info_textView = v.findViewById(R.id.profile_info_textVieW);

        // Adding Mr. Piipponen as a new User-object
        User user = new User("Mr. Piipponen", 79, "53cm", "Korso, Finland", "Gold member");

        // Displaying Mr. Piipponen's personal information
        name_textView.setText(user.getName());
        info_textView.setText(user.toString());

        return v;
    }
}