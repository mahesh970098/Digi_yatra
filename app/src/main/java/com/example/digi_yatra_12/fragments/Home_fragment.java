package com.example.digi_yatra_12.fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.digi_yatra_12.MainActivity;
import com.example.digi_yatra_12.R;

import com.example.digi_yatra_12.databinding.FragmentHomeFragmentBinding;
import com.example.digi_yatra_12.signup.Signup;

public class Home_fragment extends Fragment {
FragmentHomeFragmentBinding binding;
    public Home_fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    binding =FragmentHomeFragmentBinding.inflate(inflater,container,false);
    binding.example.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Home_fragment2 fragment2 =new Home_fragment2();
            FragmentTransaction transaction =getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_con,fragment2);

            transaction.commit();

            //            getFragmentManager().beginTransaction().replace(R.id.container,new Home_fragment2()).commit();


        }
    });
    return binding.getRoot();
    }
}
