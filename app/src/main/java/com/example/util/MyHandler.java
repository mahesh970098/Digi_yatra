package com.example.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import org.hyperledger.aries.api.Handler;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class MyHandler implements Handler {
    // Push notification implementation
    String lastTopic, lastMessage;
    Context context;

    public MyHandler(Context context) {
        this.context = context;
    }

    public String getLastNotification() {
        return lastTopic+"\n"+lastMessage;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("LongLogTag")
    @Override
    public void handle(String topic, byte[] message) {
        lastTopic = topic;
        lastMessage = new String(message, StandardCharsets.UTF_8);

        Log.d("received notification topic: ", lastTopic);
        Log.d("received notification message: ", lastMessage);
        if (topic.equals("issuecredential_actions")) {
            try {
                JSONObject jsonObject = new JSONObject(lastMessage);
                String type = jsonObject.getJSONObject("message").getJSONObject("Message").getString("@type");
                if (type.equals("https://didcomm.org/issue-credential/2.0/offer-credential")) {

                }
                else if (type.equals("https://didcomm.org/issue-credential/2.0/issue-credential")) {
                    Log.d("isHandlerSuccess","teue");
                    //show screen no 19
                   /* { show this data in screen number 19


                        "id":"e5d05ef4-9090-414f-8659-1d9235ee1127",
                            "topic":"issue-credential_actions",
                            "message":{
                        "ProtocolName":"issue-credential",
                                "Message":{
                            "@id":"4461660a-49c8-4c42-94df-742c7b181b84",
                                    "@type":"https://didcomm.org/issue-credential/2.0/issue-credential",
                                    "credentials~attach":[
                            {
                                "data":{
                                "json":{  store it room dtabase first column,
                                    "@context":[
                                    "https://www.w3.org/2018/credentials/v1",
                                            "https://dyce-context.s3.us-west-2.amazonaws.com/v1.json",
                                            "https://w3id.org/security/bbs/v1"
                     ],
                                    "credentialSubject":{
                                        "faceB64": "",                                              //content to show in screen 20
                                                "givenName":"Vemula Gourav",
                                                "id":"https://digiyatrafoundation.com/credentialid",  //exclude it
                                                "idNumber":"xxxxxxxx4234", //aadhar number
                                                "idType":"aadhar"
                                    },
                                    "expirationDate":"2100-01-01T19:23:24Z",
                                            "id":"https://digiyatrafoundation.com/credentialid",
                                            "issuanceDate":"2022-02-07T08:43:24Z",
                                            "issuer":{
                                        "id":"did:dataevolve:EiCgQLVtTs-0LuZ8lkMj3ZrJmx7u8H2MYqsLWPotMTbJBA", //second is issuerDID
                                                "name":" DigiYataraFoundation"                              //third column of roomdatabase issuerNAme
                                    },
                                    "proof":{
                                        "created":"2022-02-07T08:43:26.370042705Z",
                                                "proofPurpose":"assertionMethod",
                                                "proofValue":"iqtVcSn6KWWD09a0hYI_5oeDHjAjKjifDjcsxfSvZIkmX7Qwa6VZq2P7Qaf9fcoAD_t9O6KrVHWAtx4DmDX_o9v1ulFgdorKvdPBkFT7U0dJFhNsmclZTA7W7FldR8c8CbFN9FFubz6dkWvGpr4ffw",
                                                "type":"BbsBlsSignature2020",
                                                "verificationMethod":"did:dataevolve:EiCgQLVtTs-0LuZ8lkMj3ZrJmx7u8H2MYqsLWPotMTbJBA#r6wEVzYf6S0MC4X-Dcc6pTuvEtwcF9CfbcFqbMZmTl0"
                                    },
                                    "type":"VerifiableCredential"                       //fourth column of roomdatabase
                                }
                            }
                            }
         ],
                            "~thread":{
                                "thid":"20709086-6970-4a53-aa30-4285b184729e"
                            }
                        },
                        "Properties":{
                            "myDID":"did:peer:1zQmd5s1XgfnB5Thop8R6UTBMKzKb9TXJiuugC4a1AXUSYVY",
                                    "piid":"20709086-6970-4a53-aa30-4285b184729e",
                                    "theirDID":"did:peer:1zQmYSFZSz15hz3puTjLnbdLc8pACnUFnDrT5bVQE9UVNmkf"
                        }
                    }
                    }*/
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
