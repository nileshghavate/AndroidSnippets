package com.example.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;


public class SecondFragment extends Fragment implements View.OnClickListener {

    AppCompatButton btnPrev;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_second_fragment, container, false);
        initializeResourse(view);
        setEvent();
        return view;

    }

    private void initializeResourse(View view){

        btnPrev = view.findViewById(R.id.btn_prev);
    }

    private void setEvent(){
        btnPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_prev:
                
                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                fm.popBackStack();
               
                //ft.replace(R.id.content_frameLayout,new FirstFragment());
                break;
        }
    }
}
