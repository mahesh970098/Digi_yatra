package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestJson {
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("formats")
    @Expose
    private List<Format> formats = null;
    @SerializedName("requests~attach")
    @Expose
    private List<RequestsAttach> requestsAttach = null;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public List<RequestsAttach> getRequestsAttach() {
        return requestsAttach;
    }

    public void setRequestsAttach(List<RequestsAttach> requestsAttach) {
        this.requestsAttach = requestsAttach;
    }

    public class RequestsAttach {

        @SerializedName("@id")
        @Expose
        private String id;
        @SerializedName("mime-type")
        @Expose
        private String mimeType;
        @SerializedName("data")
        @Expose
        private Data data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }

    public class Options {

        @SerializedName("proofPurpose")
        @Expose
        private String proofPurpose;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("proofType")
        @Expose
        private String proofType;

        public String getProofPurpose() {
            return proofPurpose;
        }

        public void setProofPurpose(String proofPurpose) {
            this.proofPurpose = proofPurpose;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getProofType() {
            return proofType;
        }

        public void setProofType(String proofType) {
            this.proofType = proofType;
        }

    }

    public class Json {

        @SerializedName("credential")
        @Expose
        private Credential credential;
        @SerializedName("options")
        @Expose
        private Options options;

        public Credential getCredential() {
            return credential;
        }

        public void setCredential(Credential credential) {
            this.credential = credential;
        }

        public Options getOptions() {
            return options;
        }

        public void setOptions(Options options) {
            this.options = options;
        }
    }

    public class Issuer {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class IdJson {


    }

    public class Format {

        @SerializedName("attach_id")
        @Expose
        private String attachId;
        @SerializedName("format")
        @Expose
        private String format;

        public String getAttachId() {
            return attachId;
        }

        public void setAttachId(String attachId) {
            this.attachId = attachId;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

    }

    public class Data {

        @SerializedName("json")
        @Expose
        private Json json;

        public Json getJson() {
            return json;
        }

        public void setJson(Json json) {
            this.json = json;
        }

    }

    public class CredentialSubject {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("selfie")
        @Expose
        private String selfie;
        @SerializedName("idType")
        @Expose
        private String idType;
        @SerializedName("idJson")
        @Expose
        private IdJson idJson;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSelfie() {
            return selfie;
        }

        public void setSelfie(String selfie) {
            this.selfie = selfie;
        }

        public String getIdType() {
            return idType;
        }

        public void setIdType(String idType) {
            this.idType = idType;
        }

        public IdJson getIdJson() {
            return idJson;
        }

        public void setIdJson(IdJson idJson) {
            this.idJson = idJson;
        }

    }

    public class Credential {

        @SerializedName("@context")
        @Expose
        private List<String> context = null;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private List<String> type = null;
        @SerializedName("issuer")
        @Expose
        private Issuer issuer;
        @SerializedName("issuanceDate")
        @Expose
        private String issuanceDate;
        @SerializedName("expirationDate")
        @Expose
        private String expirationDate;
        @SerializedName("credentialSubject")
        @Expose
        private CredentialSubject credentialSubject;

        public List<String> getContext() {
            return context;
        }

        public void setContext(List<String> context) {
            this.context = context;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }

        public Issuer getIssuer() {
            return issuer;
        }

        public void setIssuer(Issuer issuer) {
            this.issuer = issuer;
        }

        public String getIssuanceDate() {
            return issuanceDate;
        }

        public void setIssuanceDate(String issuanceDate) {
            this.issuanceDate = issuanceDate;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        public CredentialSubject getCredentialSubject() {
            return credentialSubject;
        }

        public void setCredentialSubject(CredentialSubject credentialSubject) {
            this.credentialSubject = credentialSubject;
        }

    }
}
