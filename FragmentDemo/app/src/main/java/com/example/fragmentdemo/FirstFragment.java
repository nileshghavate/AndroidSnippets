package com.example.fragmentdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Fragment;



public class FirstFragment extends Fragment implements View.OnClickListener {

    AppCompatButton btnNext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_first_fragment, container, false);
        initializeResourse(view);
        setEvent();
        return view;

    }

    private void initializeResourse(View view){
        btnNext = view.findViewById(R.id.btn_next);
    }

    private void setEvent(){

        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_next:

                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content_frameLayout,new SecondFragment()).addToBackStack(null);
                ft.commit();
                ft.close();
                
                break;
        
        }
    }
}
