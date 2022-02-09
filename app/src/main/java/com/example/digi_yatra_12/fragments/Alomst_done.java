package com.example.digi_yatra_12.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digi_yatra_12.BaseClass;
import com.example.digi_yatra_12.GlobalApplication;
import com.example.digi_yatra_12.R;
import com.example.model.ConnectionDetails;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class Alomst_done extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    String aadharData;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alomst_done);
        if (getIntent().hasExtra("aadharData")) {
            aadharData = getIntent().getStringExtra("aadharData");
        }
//        this.imageView = (ImageView)this.findViewById(R.id.imageView5);
        Button photoButton = (Button) this.findViewById(R.id.cameraBtn);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Intent intent =new Intent(Alomst_done.this, Camera_Profile.class);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] b = baos.toByteArray();
            String encImage = Base64.encodeToString(b, Base64.NO_WRAP);
            Log.d("popopopopopopopopo", encImage);
            requestCredential(encImage);

            intent.putExtra("data",photo);
            // startActivity(intent);
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
////            imageView.setImageBitmap(photo);

        }
//        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        // if the intentResult is null then
//        // toast a message as "cancelled"
//        if (intentResult != null) {
//            if (intentResult.getContents() == null) {
//                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//            } else {
//
//                String scan_result = intentResult.getContents();
//                Intent intent =new Intent(Alomst_done.this, Camera_Profile.class);
//                intent.putExtra("values", scan_result);
//
//                startActivity(intent);
//
//
//
//
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }


    }
    private void requestCredential(String selfie) {
        String json = "{\"comment\":\"string\",\"formats\":[{\"attach_id\":\"string\",\"format\":\"aries/ld-proof-vc-detail@v1.0\"}],\"requests~attach\":[{\"@id\":\"string\",\"mime-type\":\"application/json\",\"data\":{\"json\":{\"credential\":{\"@context\":[\"https://www.w3.org/2018/credentials/v1\",\"https://dyce-context.s3.us-west-2.amazonaws.com/v1.json\",\"https://w3id.org/security/bbs/v1\"],\"id\":\"https://digiyatafoundation.com/credentialid\",\"type\":[\"VerifiableCredential\"],\"issuer\":{\"id\":\"did:dataevolve:1234\",\"name\":\"DigiYataraFoundation\"}, \"issuanceDate\":\"2020-01-01T19:23:24Z\",\"expirationDate\":\"2100-01-01T19:23:24Z\",\"credentialSubject\":{\"id\":\"https://digiyatrafoundation.com/credentialsubjectid\",\"selfie\":\"Base64 selfie image\",\"idType\":\"aadhar\",\"idJson\":{}}},\"options\":{\"proofPurpose\":\"assertionMethod\",\"created\":\"2020-04-02T18:48:36Z\",\"proofType\":\"BbsBlsSignature2020\"}}}}]}";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json.trim());
            jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").put("idJson", aadharData);
            jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").put("selfie", selfie);
            String  fgfg = jsonObject.getJSONArray("requests~attach").getJSONObject(0).getJSONObject("data").getJSONObject("json").getJSONObject("credential")
                    .getJSONObject("credentialSubject").getString("selfie");
            Log.d("popopopo", fgfg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("digiyatra", Context.MODE_PRIVATE);
        String connectionId = sharedPreferences.getString("connection_id","");
        JSONObject getConnectionJsonObject = BaseClass.getConnection(connectionId, GlobalApplication.agent);
        ConnectionDetails connectionDetails  = new Gson().fromJson(getConnectionJsonObject.toString(), ConnectionDetails.class);
        String myConnectionId = connectionDetails.getConnRecord().get(0).getConnectionID();
        String myDID = connectionDetails.getConnRecord().get(0).getMyDID();
        String theirDid = connectionDetails.getConnRecord().get(0).getTheirDID();
        BaseClass.requestCredential(myDID, theirDid,jsonObject, GlobalApplication.agent);

    }
}



















//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_alomst_done);
//
////        this.imageView = (ImageView)this.findViewById(R.id.imageView5);
//        Button photoButton = (Button) this.findViewById(R.id.cameraBtn);
//        photoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//                } else {
//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//
//                }
//            }
//        });
//        ImageButton ib = (ImageButton)findViewById(R.id.backBtn1);
//        ib.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//
//            } else {
//                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
//
//            Intent intent =new Intent(Alomst_done.this, Camera_Profile.class);
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            intent.putExtra("data",photo);
//            startActivity(intent);
////            Bitmap photo = (Bitmap) data.getExtras().get("data");
//////            imageView.setImageBitmap(photo);
//
//        }
////        super.onActivityResult(requestCode, resultCode, data);
////        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
////        // if the intentResult is null then
////        // toast a message as "cancelled"
////        if (intentResult != null) {
////            if (intentResult.getContents() == null) {
////                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
////            } else {
////
////                String scan_result = intentResult.getContents();
////                Intent intent =new Intent(Alomst_done.this, Camera_Profile.class);
////                intent.putExtra("values", scan_result);
////
////                startActivity(intent);
////
////
////
////
////            }
////        } else {
////            super.onActivityResult(requestCode, resultCode, data);
////        }
//
//
//
//    }
//}