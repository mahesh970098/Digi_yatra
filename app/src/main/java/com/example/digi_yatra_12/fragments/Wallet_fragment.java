package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.databinding.FragmentWalletFragmentBinding;

public class Wallet_fragment extends Fragment {
    Button add;
    ImageButton add1;
    Wallet_fragment firstfragment;

    FragmentWalletFragmentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet_fragment, container, false);

        binding = FragmentWalletFragmentBinding.inflate(getLayoutInflater());
        add = (Button) view.findViewById(R.id.HomeBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Fragment secondfragment=new Home_fragment2();
//                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.activity_main_nav_host_fragment,secondfragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                Intent intent =new Intent(getActivity(),Credential_Choose.class);
                startActivity(intent);


            }
        });

       add1 = (ImageButton)view. findViewById(R.id.imageButton5);
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getActivity(),Credentital_details.class);
                startActivity(intent);


            }
        });



//        add1 = (ImageButton) view.findViewById(R.id.backBtn1);
//        add1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(getActivity(),Navbar_main.class);
//                startActivity(intent);
//            }
//        });


//        ImageButton back =(ImageButton) view.findViewById(R.id.backBtn1);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment secondfragment=new Home_fragment();
//                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container,secondfragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//
//            }
//        });


        return view;


    }
//    public  void onDestroy(){
//        super.onDestroy();
//        binding=null;
//    }
}