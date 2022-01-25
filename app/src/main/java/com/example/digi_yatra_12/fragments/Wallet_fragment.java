package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digi_yatra_12.R;

public class Wallet_fragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_wallet_fragment, container, false);
return view;

    }



    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         add = (Button) view.findViewById(R.id.addBtn);
        add.setOnClickListener(this::onClick);
    }


        public void onClick(View v) {

//            if(v.getId()==R.id.home_fragment2){
//            getFragmentManager().beginTransaction().replace(R.id.container,new Home_fragment2()).commit();

//                Home_fragment2 fragment2 =new Home_fragment2();
//                FragmentTransaction transaction =getFragmentManager().beginTransaction();
//                transaction.replace(R.id.walletfragment,fragment2);
//
//                transaction.commit();
//                add.setVisibility(getView().GONE);
//
//

            FragmentTransaction fragmentTransaction = getActivity()
                    .getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.walletfragment, new Home_fragment2());

            fragmentTransaction.hide(firstfragment);
            fragmentTransaction.show(secondFragment);

            fragmentTransaction.commit();






        }

}
