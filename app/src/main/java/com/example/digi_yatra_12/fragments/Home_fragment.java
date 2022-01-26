package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digi_yatra_12.MainActivity;
import com.example.digi_yatra_12.R;

import com.example.digi_yatra_12.databinding.FragmentHomeFragmentBinding;
import com.example.digi_yatra_12.databinding.FragmentWalletFragmentBinding;
import com.example.digi_yatra_12.signup.Signup;

public class Home_fragment extends Fragment {
FragmentHomeFragmentBinding binding;
    Button add;
    Layout add1;
    Wallet_fragment firstfragment;
    Home_fragment2 secondFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        binding = FragmentHomeFragmentBinding.inflate(getLayoutInflater());
//        add = (Button) view.findViewById(R.id.exampleBtn);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment secondfragment=new Wallet_fragment();
//                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.activity_main_nav_host_fragment,secondfragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//
//            }
//        });

        return view;


    }
    public  void onDestroy(){
        super.onDestroy();
        binding=null;
    }
//    private void changeFragment(Fragment fr){
//        FrameLayout fl = (FrameLayout) findViewById(R.id.mainframe);
//        fl.removeAllViews();
//        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
//        transaction1.add(R.id.mainframe, fr);
//        transaction1.commit();
//    }
}

