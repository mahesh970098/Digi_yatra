package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.digi_yatra_12.MainActivity;
import com.example.digi_yatra_12.R;

import com.example.digi_yatra_12.signup.Signup;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Home_fragment extends Fragment {
//    FragmentHomeFragmentBinding binding;

    ImageButton add ,popup;
    Layout add1;

    Wallet_fragment firstfragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ImageSwitcher imageslider;

    Intent intent;
    String url2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCqZAnX28YoyZnR0KUGsq9eVAeRzBbnfibng&usqp=CAU";
    String url1 = "https://cdn03.collinson.cn/blog/2019/aug/header-airport-family-travel-vacation-5537204d-e09c-45b7-8db2-e4f2f58437b5.png?h=380&la=en&w=1280";
    String url3 = "https://cdn03.collinson.cn/blog/2019/aug/header-airport-family-travel-vacation-5537204d-e09c-45b7-8db2-e4f2f58437b5.png?h=380&la=en&w=1280";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

//
//        intent = new Intent(getActivity(), popup_Addheer.class);
//        ImageButton button = (ImageButton) rootView.findViewById(R.id.upadateBtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(intent);
//            }
//        });


            View rootView = inflater.inflate(R.layout.fragment_home_fragment, container, false);
            intent = new Intent(getActivity(), Pop_acknowledgement.class);

// we are creating array list for storing our image urls.
            ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

            // initializing the slider view.
            SliderView sliderView = rootView.findViewById(R.id.slider);

            // adding the urls inside array list
            sliderDataArrayList.add(new SliderData(url1));
            sliderDataArrayList.add(new SliderData(url2));
            sliderDataArrayList.add(new SliderData(url3));

            // passing this array list inside our adapter class.
            SliderAdapter adapter = new SliderAdapter(Home_fragment.this, sliderDataArrayList);

            // below method is used to set auto cycle direction in left to
            // right direction you can change according to requirement.
            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

            // below method is used to
            // setadapter to sliderview.
            sliderView.setSliderAdapter(adapter);

            // below method is use to set
            // scroll time in seconds.
            sliderView.setScrollTimeInSec(3);

            // to set it scrollable automatically
            // we use below method.
            sliderView.setAutoCycle(true);

            // to start autocycle below method is used.
            sliderView.startAutoCycle();

            return rootView;
        }


        public void onViewCreated(View view,  Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);


            add=(ImageButton) view.findViewById(R.id.imageButton15);

            add.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    intent = new Intent(getActivity(), Update_your_travel.class);
                    startActivity(intent);
                }
            });

            popup=(ImageButton) view.findViewById(R.id.dotBTN);
            popup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent =new Intent(getActivity(),Pop_Share_home.class);
                    startActivity(intent);
                }
            });

        }

    }


