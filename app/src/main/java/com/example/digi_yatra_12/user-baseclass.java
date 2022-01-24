package com.dataevolve.digiyathra;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import com.dataevolve.digiyathra.databinding.LoginMainBinding;
import com.dataevolve.digiyathra.utils.Cognito;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.hyperledger.aries.api.AriesController;
import org.hyperledger.aries.api.VCWalletController;
import org.hyperledger.aries.api.VerifiableController;
import org.hyperledger.aries.api.DIDExchangeController;
import org.hyperledger.aries.ariesagent.Ariesagent;
import org.hyperledger.aries.config.Options;
import org.hyperledger.aries.models.RequestEnvelope;
import org.hyperledger.aries.models.ResponseEnvelope;
import org.hyperledger.aries.api.Handler;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



class BaseClass {

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject createProfile(String userid, String localKMSPassphrase, AriesController agent) {

        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        try {
            req_object.put("userID", userid);
            req_object.put("localKMSPassphrase", localKMSPassphrase);
            res_object.put("status",0);
            res_object.put("message","");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req = req_object.toString();
        System.out.println("request: " + req);

        ResponseEnvelope res = new ResponseEnvelope();
        byte[] data = req.getBytes(StandardCharsets.UTF_8);
        RequestEnvelope requestEnvelope = new RequestEnvelope(data);
        requestEnvelope.setPayload(data);

        try {
            VCWalletController walletController = agent.getVCWalletController();
            res = walletController.createProfile(requestEnvelope);
            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to Create Profile: ", res.getError().getMessage());
                res_object.put("status",0);
                res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
            } else {
                res_object.put("status",1);
                res_object.put("message","Profile Created Sucessfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    return(res_object);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject openWallet(String userid, String localKMSPassphrase,int expiry, AriesController agent) {
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        expiry = 2147483647;
        try {
            req_object.put("userID", userid);
            req_object.put("localKMSPassphrase", localKMSPassphrase);
            req_object.put("expiry", expiry);
            res_object.put("status",0);
            res_object.put("message","");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req = req_object.toString();
        System.out.println("request: " + req);

        ResponseEnvelope res = new ResponseEnvelope();
        byte[] data = req.getBytes(StandardCharsets.UTF_8);
        RequestEnvelope requestEnvelope = new RequestEnvelope(data);
        requestEnvelope.setPayload(data);

        try {
            VCWalletController walletController = agent.getVCWalletController();
            res = walletController.open(requestEnvelope);
            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to Create Profile: ", res.getError().getMessage());
                res_object.put("status",0);
                res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
            } else {
                res_object.put("status",1);
                String ResponsePayload=new String(res.getPayload(), StandardCharsets.UTF_8);
                JSONObject responseobject = new JSONObject(ResponsePayload);
                res_object.put("token",responseobject.get("token"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(req_object);
        return res_object;


    }
//CREATE INVITATION

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject createInvitation(String senderDID, String senderUrl) {


            OkHttpClient client = new OkHttpClient();
            Request post = new Request.Builder()
                    .url("http://44.234.85.195:32506/connections/create-invitation?public=did:sidetree:EiChDtv0himJqCI8OY0r-V79zGAgKDh0p2J5YSmlmlCKqA")
                    .build();
        client.newCall(post).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Log.i("data", responseBody.string());
                    JSONObject responseobject = new JSONObject(responseBody.string());
                    responseobject.put("status",1);
                    responseobject.put("message","invitation created successfully");

                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return responseobject;

    }
//RECEIVE INVITATION
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject receiveInvitation(JSONObject invitation, AriesController agent) {
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        try {
            req_object.put("invitation",invitation.get("invitation"));
            res_object.put("status",0);
            res_object.put("message","");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req = req_object.toString();
        System.out.println("request: " + req);

        ResponseEnvelope res = new ResponseEnvelope();
        byte[] data = req.getBytes(StandardCharsets.UTF_8);
        RequestEnvelope requestEnvelope = new RequestEnvelope(data);
        requestEnvelope.setPayload(data);
        try {
            DIDExchangeController didex = agent.getDIDExchangeController();
            res = didex.receiveInvitation(requestEnvelope);

            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to receive invitation: ", res.getError().getMessage());
                res_object.put("status",0);
                res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
            } else {
                res_object.put("status",1);
                String ResponsePayload=new String(res.getPayload(), StandardCharsets.UTF_8);
                JSONObject responseobject = new JSONObject(ResponsePayload);
                res_object.put("connection_id",responseobject.get("connection_id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       return res_object;
    }
//ACCEPT INVITATION
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject acceptInvitation(String connection_id, String router_conn_id, AriesController agent) {
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        try {
            req_object.put("id",connection_id);
            req_object.put("router_connections",router_conn_id);

            res_object.put("status",0);
            res_object.put("message","");



        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req = req_object.toString();
        System.out.println("request: " + req);

        ResponseEnvelope res = new ResponseEnvelope();
        byte[] data = req.getBytes(StandardCharsets.UTF_8);
        RequestEnvelope requestEnvelope = new RequestEnvelope(data);
        requestEnvelope.setPayload(data);

        try {
            DIDExchangeController didex = agent.getDIDExchangeController();
            res = didex.acceptInvitation(requestEnvelope);

            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to accept invitation: ", res.getError().getMessage());
                res_object.put("status",0);
                res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
            } else {
                res_object.put("status",1);
                res_object.put("message","invitation accepted");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res_object;
    }
//REGISTER ROUTE
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject registerRouter(String connection_id, AriesController agent) {
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        try {
            req_object.put("connectionID",connection_id);
            res_object.put("status",0);
            res_object.put("message","");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req = req_object.toString();
        System.out.println("request: " + req);

        ResponseEnvelope res = new ResponseEnvelope();
        byte[] data = req.getBytes(StandardCharsets.UTF_8);
        RequestEnvelope requestEnvelope = new RequestEnvelope(data);
        requestEnvelope.setPayload(data);

        try {
            MediatorController mediator = agent.getMediatorController()
            res = mediator.register(requestEnvelope);

            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to register with router: ", res.getError().getMessage());
                res_object.put("status",0);
                res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
            } else {
                res_object.put("status",1);
                res_object.put("message","Connection successfully established");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res_object;
    }

    //GET CONNECTION RECORD
@SuppressLint("SetTextI18n")
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public JSONObject getConnection(String connection_id, AriesController agent) {
    JSONObject req_object = new JSONObject();
    JSONObject res_object = new JSONObject();
    try {
        req_object.put("id",connection_id);
        res_object.put("status",0);
        res_object.put("message","");
        res_object.put("conn_record","");

    } catch (JSONException e) {
        e.printStackTrace();
    }
    String req = req_object.toString();
    System.out.println("request: " + req);

    ResponseEnvelope res = new ResponseEnvelope();
    byte[] data = req.getBytes(StandardCharsets.UTF_8);
    RequestEnvelope requestEnvelope = new RequestEnvelope(data);
    requestEnvelope.setPayload(data);

    try {
        DIDExchangeController didex = agent.getDIDExchangeController();
        res = didex.getConnection(requestEnvelope);

        if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to register with router: ", res.getError().getMessage());
            res_object.put("status",0);
            res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
        } else {
            res_object.put("status",1);
            res_object.put("message","Connection successfully established");
            String ResponsePayload=new String(res.getPayload(), StandardCharsets.UTF_8);
            JSONObject responseobject = new JSONObject(ResponsePayload);
            res_object.put("conn_record",responseobject.get("result"));


        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return res_object;
    }

//ADD CONN_RECORD TO WALLET
@SuppressLint("SetTextI18n")
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public JSONObject addContent(String auth, String userID, String contentType, JSONObject content, AriesController agent) {
    JSONObject req_object = new JSONObject();
    JSONObject res_object = new JSONObject();
    try {
        req_object.put("auth",auth);
        req_object.put("userID",userID);
        req_object.put("contentType",contentType);
        req_object.put("content",content);


        res_object.put("status",0);
        res_object.put("message","");

    } catch (JSONException e) {
        e.printStackTrace();
    }
    String req = req_object.toString();
    System.out.println("request: " + req);

    ResponseEnvelope res = new ResponseEnvelope();
    byte[] data = req.getBytes(StandardCharsets.UTF_8);
    RequestEnvelope requestEnvelope = new RequestEnvelope(data);
    requestEnvelope.setPayload(data);

    try {
        VCWalletController vcwallet = agent.getVCWalletController();
        vcwallet = vcwallet.add(requestEnvelope);

        if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to add content: ", res.getError().getMessage());
            res_object.put("status",0);
            res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
        } else {
            res_object.put("status",1);
            res_object.put("message","content successfully added to wallet");

        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return res_object;
}

//PROPOSE CREDENTIAL
@SuppressLint("SetTextI18n")
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public JSONObject proposeCredential(String myDID, String theirDID, JSONObject proposal, AriesController agent) {
    JSONObject req_object = new JSONObject();
    JSONObject res_object = new JSONObject();
    try {
        req_object.put("their_did",theirDID);
        req_object.put("my_did",myDID);
        req_object.put("propose_credential", proposal);
        res_object.put("status",0);
        res_object.put("piid","");
        res_object.put("message","");

    } catch (JSONException e) {
        e.printStackTrace();
    }
    String req = req_object.toString();
    System.out.println("request: " + req);

    ResponseEnvelope res = new ResponseEnvelope();
    byte[] data = req.getBytes(StandardCharsets.UTF_8);
    RequestEnvelope requestEnvelope = new RequestEnvelope(data);
    requestEnvelope.setPayload(data);

    try {
        IssuecredentialController issue = agent.getIssuecredentialController();
        res = issue.sendProposal(requestEnvelope);

        if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to add content: ", res.getError().getMessage());
            res_object.put("status",0);
            res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
        } else {
            res_object.put("status",1);
            res_object.put("message","proposal sent successfully");
            String ResponsePayload=new String(res.getPayload(), StandardCharsets.UTF_8);
            JSONObject responseobject = new JSONObject(ResponsePayload);
            res_object.put("piid",responseobject.get("piid"));


        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return res_object;
    }

//REQUEST CREDENTIAL
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject requestCredential(String myDID, String theirDID, JSONObject request, AriesController agent) {
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        try {
            req_object.put("their_did",theirDID);
            req_object.put("my_did",myDID);
            req_object.put("request_credential", request);
            res_object.put("status",0);
            res_object.put("piid","");
            res_object.put("message","");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req = req_object.toString();
        System.out.println("request: " + req);

        ResponseEnvelope res = new ResponseEnvelope();
        byte[] data = req.getBytes(StandardCharsets.UTF_8);
        RequestEnvelope requestEnvelope = new RequestEnvelope(data);
        requestEnvelope.setPayload(data);

        try {
            IssuecredentialController issue = agent.getIssuecredentialController();
            res = issue.sendRequest(requestEnvelope);

            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to add content: ", res.getError().getMessage());
                res_object.put("status",0);
                res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
            } else {
                res_object.put("status",1);
                res_object.put("message","request sent successfully");
                String ResponsePayload=new String(res.getPayload(), StandardCharsets.UTF_8);
                JSONObject responseobject = new JSONObject(ResponsePayload);
                res_object.put("piid",responseobject.get("piid"));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res_object;
    }
//ACCEPT CREDENTIAL
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject acceptCredential(List<String> name, String piid, AriesController agent) {
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        try {
            req_object.put("names",name);
            req_object.put("piid",piid);
            res_object.put("status",0);
            res_object.put("message","");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String req = req_object.toString();
        System.out.println("request: " + req);

        ResponseEnvelope res = new ResponseEnvelope();
        byte[] data = req.getBytes(StandardCharsets.UTF_8);
        RequestEnvelope requestEnvelope = new RequestEnvelope(data);
        requestEnvelope.setPayload(data);

        try {
            IssuecredentialController issue = agent.getIssuecredentialController();
            res = issue.acceptCredential(requestEnvelope);

            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to add content: ", res.getError().getMessage());
                res_object.put("status",0);
                res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
            } else {
                res_object.put("status",1);
                res_object.put("message","credential accepted");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res_object;
    }
//ADD PROOF TO CREDENTIAL
@SuppressLint("SetTextI18n")
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public JSONObject addProof(String did, JSONObject credential, AriesController agent) {
    JSONObject req_object = new JSONObject();
    JSONObject res_object = new JSONObject();
    try {
        req_object.put("did",did);
        req_object.put("signatureType", "Ed25519Signature2018");
        req_object.put("verifiableCredential",credential);
        res_object.put("status",0);
        res_object.put("message","");
        res_object.put("verifiablePresentation",);

    } catch (JSONException e) {
        e.printStackTrace();
    }
    String req = req_object.toString();
    System.out.println("request: " + req);

    ResponseEnvelope res = new ResponseEnvelope();
    byte[] data = req.getBytes(StandardCharsets.UTF_8);
    RequestEnvelope requestEnvelope = new RequestEnvelope(data);
    requestEnvelope.setPayload(data);

    try {
        VerifiableController verify = agent.getVerifiableController();
        res = verify.generatePresentation(requestEnvelope);

        if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to add content: ", res.getError().getMessage());
            res_object.put("status",0);
            res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
        } else {
            res_object.put("status",1);
            res_object.put("message","credential signed successfully");
            String ResponsePayload=new String(res.getPayload(), StandardCharsets.UTF_8);
            JSONObject responseobject = new JSONObject(ResponsePayload);
            res_object.put("verifiablePresentation",responseobject.get("verifiablePresentation"));


        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return res_object;
    }
//PROPOSE PRESENTATION
@SuppressLint("SetTextI18n")
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public JSONObject proposePresentation(String myDID, String theirDID, JSONObject presproposal, AriesController agent) {
    JSONObject req_object = new JSONObject();
    JSONObject res_object = new JSONObject();
    try {
        req_object.put("their_did",theirDID);
        req_object.put("my_did",myDID);
        req_object.put("propose_presentation", presproposal);
        res_object.put("status",0);
        res_object.put("piid","");
        res_object.put("message","");

    } catch (JSONException e) {
        e.printStackTrace();
    }
    String req = req_object.toString();
    System.out.println("request: " + req);

    ResponseEnvelope res = new ResponseEnvelope();
    byte[] data = req.getBytes(StandardCharsets.UTF_8);
    RequestEnvelope requestEnvelope = new RequestEnvelope(data);
    requestEnvelope.setPayload(data);

    try {
        PresentproofController present = agent.getPresentproofController();
        res = present.sendProposePresentation(requestEnvelope);

        if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to add content: ", res.getError().getMessage());
            res_object.put("status",0);
            res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
        } else {
            res_object.put("status",1);
            res_object.put("message","proposal sent successfully");
            String ResponsePayload=new String(res.getPayload(), StandardCharsets.UTF_8);
            JSONObject responseobject = new JSONObject(ResponsePayload);
            res_object.put("piid",responseobject.get("piid"));


        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return res_object;
    }
//SEND PRESENTATION
@SuppressLint("SetTextI18n")
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public JSONObject sendPresentation(String piid, JSONObject presentation, AriesController agent) {
    JSONObject req_object = new JSONObject();
    JSONObject res_object = new JSONObject();
    try {
        req_object.put("piid",piid);
        req_object.put("presentation", presentation);
        res_object.put("status",0);
        res_object.put("message","");

    } catch (JSONException e) {
        e.printStackTrace();
    }
    String req = req_object.toString();
    System.out.println("request: " + req);

    ResponseEnvelope res = new ResponseEnvelope();
    byte[] data = req.getBytes(StandardCharsets.UTF_8);
    RequestEnvelope requestEnvelope = new RequestEnvelope(data);
    requestEnvelope.setPayload(data);

    try {
        PresentproofController present = agent.getPresentproofController();
        res = present.sendProposePresentation(requestEnvelope);

        if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to add content: ", res.getError().getMessage());
            res_object.put("status",0);
            res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
        } else {
            res_object.put("status",1);
            res_object.put("message","presentation sent successfully");

        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return res_object;
    }



// POST HTTP
@SuppressLint("SetTextI18n")
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public JSONObject postHTTP(String myDID, String theirDID, JSONObject proposal) {
    OkHttpClient client = new OkHttpClient();

    JsonObject postData = new JsonObject();
    postData.addProperty("name", "morpheus");
    postData.addProperty("job", "leader");

    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    RequestBody postBody = RequestBody.create(JSON, postData.toString());
    Request post = new Request.Builder()
            .url("https://reqres.in/api/users")
            .post(postBody)
            .build();

    client.newCall(post).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                ResponseBody responseBody = response.body();
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                Log.i("data", responseBody.string());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
    }



}

