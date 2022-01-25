package com.example.digi_yatra_12.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digi_yatra_12.MainActivity;
import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.signup.Signup;

public class Home_fragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Button button = null;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);




        Button btnFragment = (Button) view.findViewById(R.id.example);
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Extra_fragment fragment2=new Extra_fragment();
//                FragmentManager fragmentManager=getActivity().getFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_con,fragment2, "tag");
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//                Intent intent =new Intent(Home_fragment.this, Extra_fragment.class);
//                startActivity(intent);
            }
        });
        return view;
    }



}
