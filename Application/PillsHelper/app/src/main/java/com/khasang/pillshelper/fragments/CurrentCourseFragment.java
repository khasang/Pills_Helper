package com.khasang.pillshelper.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khasang.pillshelper.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentCourseFragment extends android.support.v4.app.Fragment {


    public CurrentCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_course, container, false);
    }

}
