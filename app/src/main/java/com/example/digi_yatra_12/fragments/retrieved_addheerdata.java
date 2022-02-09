package com.example.digi_yatra_12.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.R;
import com.example.digi_yatra_12.retrofit.Const;
import com.example.digi_yatra_12.retrofit.RetrofitBuilder;
import com.example.digi_yatra_12.retrofit.RetrofitService;
import com.example.model.AccessTokenRoot;
import com.example.util.CustomProgressDialog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class retrieved_addheerdata extends AppCompatActivity {
    private String code;
    private TextView name, lastName, aadharID, phone;
    private ImageView aadharPhoto;
    private Button popup;
    private CustomProgressDialog customProgressDialog;
    private String aadharData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieved_addheerdata);


        initViews();
        if (getIntent().hasExtra("code")) {
            code = getIntent().getStringExtra("code");
            getAccessToken(code);
        }
        else {
            finish();
        }

        Button popup = (Button) findViewById(R.id.ProcedBtn);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(retrieved_addheerdata.this, Alomst_done.class);

                startActivity(i);

            }
        });
        ImageButton ib = (ImageButton)findViewById(R.id.backBtn1);
        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initViews() {
        popup = findViewById(R.id.ProcedBtn);
        name = findViewById(R.id.txt_name);
        lastName = findViewById(R.id.txt_last_name);
        aadharID = findViewById(R.id.txt_aadhar_id);
        phone = findViewById(R.id.txt_phone);
        aadharPhoto = findViewById(R.id.img_aadhar);
        customProgressDialog = new CustomProgressDialog(this);
        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(retrieved_addheerdata    .this, Alomst_done.class);
                i.putExtra("aadharData", aadharData);
                startActivity(i);
            }
        });
    }
    private void getAccessToken(String code) {
        customProgressDialog.show();
        Call<AccessTokenRoot> call = RetrofitBuilder.create().getAccessToken(code, Const.GRANT_TYPE, Const.CLIENT_ID, Const.CLIENT_SECRETE, Const.REDIRECT_URL);
        call.enqueue(new Callback<AccessTokenRoot>() {
            @Override
            public void onResponse(Call<AccessTokenRoot> call, Response<AccessTokenRoot> response) {
                customProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    Log.d("accessToken", response.body().getAccessToken());
                    if (response.body().getMobile() != null) {
                        phone.setText(response.body().getMobile());
                    }
                    if (response.body().getAccessToken() != null && !response.body().getAccessToken().isEmpty()) {
                        getEAadhar(response.body().getAccessToken());
                    }
                    else {
                        handleError("something went wrong");
                    }
                }
                else {
                    handleError("something went wrong");
                }
            }

            @Override
            public void onFailure(Call<AccessTokenRoot> call, Throwable t) {
                Log.d("accessTokenError", "getAccessTokenError");
                handleError(t.getMessage());
            }
        });
    }

    private void getEAadhar(String accessToken) {
        customProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.BASE_URL_Get_ACCESS_TOKEN).addConverterFactory( ScalarsConverterFactory.create())
                .build();
        RetrofitService rssapi = retrofit.create(RetrofitService.class);
        Call<String> calla = rssapi.getEAadhar("Bearer "+accessToken);
        calla.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                customProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    aadharData = response.body();
                    //Log.d("responceString",response.body());
                    Document doc = convertStringToDocument(response.body());
                    doc.getDocumentElement().normalize();
                    NodeList nList = doc.getElementsByTagName("UidData");
                    Log.d("responceString", nList.toString());
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;

                            String getPht = eElement.getElementsByTagName("Pht").item(0).getTextContent();
                            if (!getPht.isEmpty()) {
                                Bitmap bm = StringToBitMap(getPht);
                                aadharPhoto.setImageBitmap(bm);
                            }
                        }
                    }
                    NodeList nList1 = doc.getElementsByTagName("Poi");
                    for (int temp = 0; temp < nList1.getLength(); temp++) {
                        Node nNode = nList1.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            if (!eElement.getAttribute("name").isEmpty()) {
                                name.setText(eElement.getAttribute("name"));
                            }
                        }
                    }



                  /*  Log.d("responceString",response.body().toString());
                    try {
                        EAadharRoot.UidData uidData = response.body().getCertificateData().getKycRes().getUidData();
                        if (uidData != null) {
                            Bitmap bm = StringToBitMap(uidData.getPht());
                            aadharPhoto.setImageBitmap(bm);
                            name.setText(uidData.getLData().getName());
                        } else {
                            handleError("something went wrong");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        handleError(e.getMessage());
                    }*/
                }
                else {
                    handleError("something went wrong");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("eAadharError", t.getMessage());
                handleError(t.getMessage());
            }
        });
    }

    private void handleError(String errorMsg) {
        customProgressDialog.dismiss();
        Toast.makeText(retrieved_addheerdata.this, errorMsg,Toast.LENGTH_LONG).show();
        finish();
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    private Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) );
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
