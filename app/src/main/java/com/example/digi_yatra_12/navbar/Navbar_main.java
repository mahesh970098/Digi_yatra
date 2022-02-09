package com.example.digi_yatra_12.navbar;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.digi_yatra_12.BaseClass;
import com.example.digi_yatra_12.GlobalApplication;
import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.fragments.Add_creditial_wallet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tomergoldst.tooltips.ToolTip;
import com.tomergoldst.tooltips.ToolTipsManager;

import org.hyperledger.aries.api.AriesController;
import org.hyperledger.aries.api.VCWalletController;
import org.hyperledger.aries.models.RequestEnvelope;
import org.hyperledger.aries.models.ResponseEnvelope;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class Navbar_main extends AppCompatActivity {
    ToolTipsManager toolTipsManager;
    private View walletButton;
    private View homeButton;
    private ConstraintLayout constraintLayout, semiTransparent;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar_main);

//        NavController navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment);
//        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container,new Fragment());
//        fragmentTransaction.commit();

        initViews();
        try {
            int status = profileExists("ankush", GlobalApplication.agent).getInt("status");
            if (status == 1) {
                JSONObject createProfileStatus = BaseClass.createProfile("ankush","5351",GlobalApplication.agent); //exicuted only fisrt time
                if (createProfileStatus.getInt("status") == 1) {
                    JSONObject responseOpenWallet = BaseClass.openWallet("ankush","5351",999999999999999999L, GlobalApplication.agent);
                    String authToken = responseOpenWallet.getString("token"); //save this token in database we will use it all the time it's created only once
                    //redirected it to 28th screen
                    semiTransparent.setVisibility(View.VISIBLE);
                    showToolTip();
                    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            if (item == bottomNavigationView.getMenu().getItem(0)) {
                                semiTransparent.setVisibility(View.GONE);
                                toolTipsManager.dismissAll();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.activity_main_nav_host_fragment, new Add_creditial_wallet()).commit();
                                bottomNavigationView.setVisibility(View.GONE);
                            }
                            return true;
                        }
                    });





                }

                //check profile created or not

                //go to screen no 6 , this code is for screen no 6.
/*
                        createInvitation(senderUrl, new BaseClassInterface() {
                            @Override
                            public void createInvitationResponse(JsonObject invitation) {
                                Log.d("createInvitation","created");
                                if (invitation != null) {
                                    JSONObject connectionJsonObject = receiveInvitation(invitation, GlobalApplication.agent);
                                    JSONObject acceptInvitationReturn = null;
                                    try {
                                        acceptInvitationReturn = acceptInvitation(connectionJsonObject.getString("connection_id"),"", GlobalApplication.agent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    JSONObject finalAcceptInvitationReturn = acceptInvitationReturn;
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                if (finalAcceptInvitationReturn.getInt("status") == 1) {
                                                    // getConnection(connectionJsonObject.getString("connection_id"), GlobalApplication.agent);

                                                    JSONObject jsonObjectRegisterRouter = registerRouter(connectionJsonObject.getString("connection_id"), GlobalApplication.agent);
                                                    //save connection id in local database
                                                    // go to screen no 6
                                                    if (jsonObjectRegisterRouter.getInt("status") == 1) {
                                                        JSONObject getConnectionJsonObject = getConnection(connectionJsonObject.getString("connection_id"),GlobalApplication.agent);
                                                        Log.d("getConnectionJsonObject", getConnectionJsonObject.getString("message"));
                                                        ConnectionDetails connectionDetails  = new Gson().fromJson(getConnectionJsonObject.toString(), ConnectionDetails.class);
                                                        String myConnectionId = connectionDetails.getConnRecord().get(0).getConnectionID();
                                                        String myDID = connectionDetails.getConnRecord().get(0).getMyDID();
                                                        String theirDid = connectionDetails.getConnRecord().get(0).getTheirDID();

                                                        proposeCredential(myDID, theirDid, new JSONObject(),GlobalApplication.agent);
                                                    }
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    },4000);

                                }
                            }
                        });
*/
            } else if(status == 1) {
                //   startActivity(new Intent(Otp_page.this, Navbar_main.class));

                Log.d("check my status", "1.getMessage()");
                //fetch the connection id from database
                ///  reconnectRouter(JSONObject jsonObject,)
                  /*  {
                            "connectionID": "string"
                        }
                        pass it in this format
                        if response is 1 it means connection got established
                        show screen no 27
*/
                semiTransparent.setVisibility(View.VISIBLE);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("check my status", e.getMessage());

        }
        //  Intent intent =new Intent(Otp_page.this, Navbar_main.class);
        //startActivity(intent);
    }

    private void initViews() {
        toolTipsManager=new ToolTipsManager();
        constraintLayout = findViewById(R.id.fragment_container);
        semiTransparent = findViewById(R.id.constraint_semitransparent);
        NavController navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment);
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        walletButton = findViewById(bottomNavigationView.getMenu().getItem(0).getItemId());
    }

    public JSONObject profileExists(String userId, AriesController agent) {
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        try {
            req_object.put("userId", userId);
            res_object.put("status", 0);
            res_object.put("message", "");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("check my status", e.getMessage());

        }
        String req = req_object.toString();
        ResponseEnvelope res = new ResponseEnvelope();
        byte[] data = req.getBytes( StandardCharsets.UTF_8);
        RequestEnvelope requestEnvelope = new RequestEnvelope(data);
        requestEnvelope.setPayload(data);
        try {
            VCWalletController walletController = agent.getVCWalletController();
            res = walletController.profileExists(requestEnvelope);
            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
                res_object.put("status", 1);
                res_object.put("message", "Profile already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("check my status", e.getMessage());

        }
        return (res_object);
    }

    private void showToolTip() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toolTipsManager.findAndDismiss(walletButton);
                ToolTip.Builder builder=new ToolTip.Builder(Navbar_main.this,walletButton,constraintLayout,"Click here to get started to create your ID's",ToolTip.POSITION_ABOVE);
                builder.setAlign(ToolTip.ALIGN_RIGHT);
                builder.setBackgroundColor( Color.BLUE);
                toolTipsManager.show(builder.build());
            }
        },100);
    }



    }






//

//
//
//    }
//
//}