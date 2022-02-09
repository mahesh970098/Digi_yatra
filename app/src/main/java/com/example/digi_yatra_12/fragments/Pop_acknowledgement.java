package com.example.digi_yatra_12.fragments;

import static com.couchbase.lite.internal.CouchbaseLiteInternal.getContext;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.R;

public class Pop_acknowledgement extends AppCompatActivity {
    String senderUrl = "http://ab6b1f47c653a4ad9be235211726df19-222719493.ap-south-1.elb.amazonaws.com:10093/";
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_acknowledgement);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .91), (int) (height * .5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.x = 0;
        params.y = 50;

        getWindow().setAttributes(params);


       btn1= (Button) findViewById(R.id.OkBtn2);
        btn2= (Button) findViewById(R.id.cancel_action);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    Intent i = new Intent(getContext(), Add_data_url.class);
                    startActivity(i);


                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    btn1.setBackgroundTintList( ColorStateList.valueOf(getResources().getColor(R.color.btn_enable, getResources().newTheme())));
                }
                else {
                    btn1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_disable, getResources().newTheme())));
                }
            }
        });


//        Button btn=(Button) findViewById(R.id.OkBtn2);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =new Intent(Pop_acknowledgement.this,Add_data_url.class);
//                startActivity(i);
//            }
//        });
//        Button btn1=(Button) findViewById(R.id.cancel_action);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i =new Intent(Pop_acknowledgement.this,Credential_Choose.class);
//                startActivity(i);
//            }
//        });

    }


//
//
//    private void showPopUP(View view, View v) {
//        Dialog builder = new Dialog(getContext());
//        ViewGroup viewGroup = view.findViewById(android.R.id.content);
//        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.activity_pop_acknowledgement, viewGroup, false);
//        builder.getWindow().setBackgroundDrawable(new ColorDrawable( Color.TRANSPARENT));
//        Button btn= dialogView.findViewById(R.id.OkBtn2);
//        Button cancelButton = dialogView.findViewById(R.id.cameraBtn);
//        CheckBox checkBox = dialogView.findViewById(R.id.checkBox2);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (checkBox.isChecked()) {
//                    Intent i = new Intent(getContext(), Add_data_url.class);
//                    startActivity(i);
//                    builder.dismiss();
//
//                }
//            }
//        });
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                builder.dismiss();
//            }
//        });
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (checkBox.isChecked()) {
//                    btn.setBackgroundTintList( ColorStateList.valueOf(getResources().getColor(R.color.btn_enable, getResources().newTheme())));
//                }
//                else {
//                    btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_disable, getResources().newTheme())));
//                }
//            }
//        });
//        builder.setContentView(dialogView);
//        builder.create();
//        builder.show();
//    }


//    private  void createInvitation() {
//        BaseClass.createInvitation(senderUrl, new BaseClassInterface() {
//            @Override
//            public void createInvitationResponse(JsonObject invitation) {
//                Log.d("createInvitation","created");
//                if (invitation != null) {
//                    JSONObject connectionJsonObject = BaseClass.receiveInvitation(invitation, GlobalApplication.agent);
//                    JSONObject acceptInvitationReturn = null;
//                    try {
//                        acceptInvitationReturn = BaseClass.acceptInvitation(connectionJsonObject.getString("connection_id"),"", GlobalApplication.agent);
//                        // save connectionID
//                        SharedPreferences sharedPreferences = requireContext.getSharedPreferences("digiyatra", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                        myEdit.putString("connection_id", connectionJsonObject.getString("connection_id"));
//                        myEdit.apply();
//                        myEdit.commit();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }




                   /* JSONObject finalAcceptInvitationReturn = acceptInvitationReturn;

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (finalAcceptInvitationReturn.getInt("status") == 1) {
                                    // getConnection(connectionJsonObject.getString("connection_id"), GlobalApplication.agent);

                                    JSONObject jsonObjectRegisterRouter = BaseClass.registerRouter(connectionJsonObject.getString("connection_id"), GlobalApplication.agent);
                                    //save connection id in local database
                                    // go to screen no 6
                                    if (jsonObjectRegisterRouter.getInt("status") == 1) {
                                        JSONObject getConnectionJsonObject = BaseClass.getConnection(connectionJsonObject.getString("connection_id"),GlobalApplication.agent);
                                        Log.d("getConnectionJsonObject", getConnectionJsonObject.getString("message"));
                                        ConnectionDetails connectionDetails  = new Gson().fromJson(getConnectionJsonObject.toString(), ConnectionDetails.class);
                                        String myConnectionId = connectionDetails.getConnRecord().get(0).getConnectionID();
                                        String myDID = connectionDetails.getConnRecord().get(0).getMyDID();
                                        String theirDid = connectionDetails.getConnRecord().get(0).getTheirDID();
                                        JSONObject resultProposeCredentials = BaseClass.proposeCredential(myDID, theirDid, new JSONObject(),GlobalApplication.agent);
                                        if (resultProposeCredentials.getInt("status") == 0) {
                                            Toast.makeText(getContext(), resultProposeCredentials.getInt("message"), Toast.LENGTH_SHORT).show();
                                        }
                                        else  if (resultProposeCredentials.getInt("status") == 1)
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },4000);*/

//                }
//            }
//        });
//    }
}
