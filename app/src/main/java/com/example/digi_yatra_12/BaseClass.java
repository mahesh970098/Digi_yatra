package com.example.digi_yatra_12;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.biometric.BiometricPrompt;       //commented by ankush

///import com.dataevolve.digiyathra.databinding.LoginMainBinding;  //commented by ankush
//import com.dataevolve.digiyathra.utils.Cognito;   //commented by ankush

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.hyperledger.aries.api.AriesController;
import org.hyperledger.aries.api.IssueCredentialController;
import org.hyperledger.aries.api.MediatorController;
import org.hyperledger.aries.api.PresentProofController;
import org.hyperledger.aries.api.VCWalletController;
import org.hyperledger.aries.api.DIDExchangeController;
import org.hyperledger.aries.models.RequestEnvelope;
import org.hyperledger.aries.models.ResponseEnvelope;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.digi_yatra_12.retrofit.RetrofitService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class BaseClass {

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    //Use static userid and passphrase CD
    public static JSONObject createProfile(String userid, String localKMSPassphrase, AriesController agent) {

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
    public static JSONObject openWallet(String userid, String localKMSPassphrase,long expiry, AriesController agent) { ///pass static userid, static localKMSPassphrase, static expiry
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        expiry = 999999999999999999L;
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
    public static  void createInvitation(String senderUrl, BaseClassInterface baseClassInterface) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(senderUrl).addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService rssapi = retrofit.create(RetrofitService.class);
        retrofit2.Call<ResponseBody> call = rssapi.createInvitation();
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    ResponseBody responseBody = response.body();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    String test1 = responseBody.string();
                    //String test1 = "{\"invitation\":{\"serviceEndpoint\":\"ws://a6cbae1edbe5846e5971e397af45ccb4-1345780168.us-west-2.elb.amazonaws.com:10092\",\"recipientKeys\":[\"did:key:z6MkvCwLhAdeMZsaGVHd1SRxrfMkY5fYyxSonvqoP62DwnbL\"],\"@id\":\"c7402ab3-e09e-47cc-b992-68d140c2f9ca\",\"label\":\"issuer-carl\",\"@type\":\"https://didcomm.org/didexchange/1.0/invitation\"},\"alias\":\"\",\"invitation_url\":\"\"}";
                    Log.i("data", responseBody.string());
                    JsonObject responseobject = new JsonParser().parse(test1.trim()).getAsJsonObject();
                    responseobject.addProperty("status",1);
                    responseobject.addProperty("message","invitation created successfully");
                    baseClassInterface.createInvitationResponse(responseobject);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("createInvitationError", e.getMessage());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
       /* OkHttpClient client = new OkHttpClient();
        Request post = new Request.Builder()
                .url(senderUrl+"/connections/create-invitation?public="+senderDID)
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
                    baseClassInterface.createInvitationResponse(responseobject);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("createInvitationError", e.getMessage());
                }
            }
        });*/
       // return responseobject[0];

    }
    //RECEIVE INVITATION
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONObject receiveInvitation(JsonObject invitation, AriesController agent) {
        JSONObject req_object = new JSONObject();
        JSONObject res_object = new JSONObject();
        try {
            req_object = new JSONObject(invitation.get("invitation").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
          //  req_object.put("invitation",invitation.get("invitation"));
            res_object.put("status",0);
            res_object.put("message","");
            Log.d("check",invitation.get("invitation").toString());

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("recieveInvitation",e.getMessage());
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
                res_object.put("connection_id",responseobject.get("connection_id"));	//Use this connId to parse and verify identification from issuer

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res_object;
    }
    //ACCEPT INVITATION
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    //router_conn_id will receive from medicator that will be cerated with agent in Application
    public static JSONObject acceptInvitation(String connection_id, String router_conn_id, AriesController agent) {
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
                                       String receiveInvitationResponse = new String(res.getPayload(), StandardCharsets.UTF_8);

                Log.d("acceptInvitation",receiveInvitationResponse);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res_object;
    }
    //REGISTER ROUTE
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONObject registerRouter(String connection_id, AriesController agent) {
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
            MediatorController mediator = agent.getMediatorController();
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
    //commented by bl
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONObject getConnection(String connection_id, AriesController agent) {
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
            res = didex.queryConnections(requestEnvelope);

            if (res.getError() != null && !res.getError().getMessage().isEmpty()) {
//                        Log.d("failed to register with router: ", res.getError().getMessage());
                res_object.put("status",0);
                res_object.put("message",res.getError().getMessage());
//                        String receiveInvitationResponse = res.toString();//new String(res.getPayload(), StandardCharsets.UTF_8)
            } else {

                res_object.put("status",1);
                res_object.put("message","Connection successfully established");
                String ResponsePayload=new String(res.getPayload(), StandardCharsets.UTF_8);
                Log.d("getConnectionResponse1", ResponsePayload);
                JSONObject responseobject = new JSONObject(ResponsePayload);
                res_object.put("conn_record",responseobject.get("results"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("getConnectionResponse", res_object.toString());
        return res_object;
    }
    //ADD CONN_RECORD TO WALLET
    //commented by bl
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONObject addContent(String auth, String userID, String contentType, JSONObject content, AriesController agent) {
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
            res = vcwallet.add(requestEnvelope);     //change by bl

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
//Proposal will have aadhar_details and based64_selfie in json string
    public static JSONObject proposeCredential(String myDID, String theirDID, JSONObject proposal, AriesController agent) {
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
            IssueCredentialController issue = agent.getIssueCredentialController();
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
    public static JSONObject requestCredential(String myDID, String theirDID, JSONObject request, AriesController agent) {
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
            IssueCredentialController issue = agent.getIssueCredentialController();
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
    public static JSONObject acceptCredential(List<String> name, String piid, AriesController agent) {
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
            IssueCredentialController issue = agent.getIssueCredentialController();
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
    //commented by bl
    /*@SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONObject addProof(String did, JSONObject credential, AriesController agent) {
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
    }*/
    //PROPOSE PRESENTATION
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONObject proposePresentation(String myDID, String theirDID, JSONObject presproposal, AriesController agent) {
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
            PresentProofController present = agent.getPresentProofController();
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
    public static JSONObject sendPresentation(String piid, JSONObject presentation, AriesController agent) {
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
            PresentProofController  present = agent.getPresentProofController();
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



    // POST HTTP //commented by bl
   /* @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static JSONObject postHTTP(String myDID, String theirDID, JSONObject proposal) {
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
*/


}

