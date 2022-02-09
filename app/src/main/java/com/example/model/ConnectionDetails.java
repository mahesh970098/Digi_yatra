package com.example.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ConnectionDetails {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("conn_record")
    @Expose
    private List<ConnRecord> connRecord = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ConnRecord> getConnRecord() {
        return connRecord;
    }

    public void setConnRecord(List<ConnRecord> connRecord) {
        this.connRecord = connRecord;
    }

    public class ConnRecord {

        @SerializedName("ConnectionID")
        @Expose
        private String connectionID;
        @SerializedName("State")
        @Expose
        private String state;
        @SerializedName("ThreadID")
        @Expose
        private String threadID;
        @SerializedName("ParentThreadID")
        @Expose
        private String parentThreadID;
        @SerializedName("TheirLabel")
        @Expose
        private String theirLabel;
        @SerializedName("TheirDID")
        @Expose
        private String theirDID;
        @SerializedName("MyDID")
        @Expose
        private String myDID;
        @SerializedName("ServiceEndPoint")
        @Expose
        private String serviceEndPoint;
        @SerializedName("RecipientKeys")
        @Expose
        private List<String> recipientKeys = null;
        @SerializedName("RoutingKeys")
        @Expose
        private Object routingKeys;
        @SerializedName("InvitationID")
        @Expose
        private String invitationID;
        @SerializedName("InvitationDID")
        @Expose
        private String invitationDID;
        @SerializedName("Implicit")
        @Expose
        private boolean implicit;
        @SerializedName("Namespace")
        @Expose
        private String namespace;
        @SerializedName("MediaTypeProfiles")
        @Expose
        private Object mediaTypeProfiles;
        @SerializedName("DIDCommVersion")
        @Expose
        private String dIDCommVersion;
        @SerializedName("PeerDIDInitialState")
        @Expose
        private String peerDIDInitialState;

        public String getConnectionID() {
            return connectionID;
        }

        public void setConnectionID(String connectionID) {
            this.connectionID = connectionID;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getThreadID() {
            return threadID;
        }

        public void setThreadID(String threadID) {
            this.threadID = threadID;
        }

        public String getParentThreadID() {
            return parentThreadID;
        }

        public void setParentThreadID(String parentThreadID) {
            this.parentThreadID = parentThreadID;
        }

        public String getTheirLabel() {
            return theirLabel;
        }

        public void setTheirLabel(String theirLabel) {
            this.theirLabel = theirLabel;
        }

        public String getTheirDID() {
            return theirDID;
        }

        public void setTheirDID(String theirDID) {
            this.theirDID = theirDID;
        }

        public String getMyDID() {
            return myDID;
        }

        public void setMyDID(String myDID) {
            this.myDID = myDID;
        }

        public String getServiceEndPoint() {
            return serviceEndPoint;
        }

        public void setServiceEndPoint(String serviceEndPoint) {
            this.serviceEndPoint = serviceEndPoint;
        }

        public List<String> getRecipientKeys() {
            return recipientKeys;
        }

        public void setRecipientKeys(List<String> recipientKeys) {
            this.recipientKeys = recipientKeys;
        }

        public Object getRoutingKeys() {
            return routingKeys;
        }

        public void setRoutingKeys(Object routingKeys) {
            this.routingKeys = routingKeys;
        }

        public String getInvitationID() {
            return invitationID;
        }

        public void setInvitationID(String invitationID) {
            this.invitationID = invitationID;
        }

        public String getInvitationDID() {
            return invitationDID;
        }

        public void setInvitationDID(String invitationDID) {
            this.invitationDID = invitationDID;
        }

        public boolean isImplicit() {
            return implicit;
        }

        public void setImplicit(boolean implicit) {
            this.implicit = implicit;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public Object getMediaTypeProfiles() {
            return mediaTypeProfiles;
        }

        public void setMediaTypeProfiles(Object mediaTypeProfiles) {
            this.mediaTypeProfiles = mediaTypeProfiles;
        }

        public String getDIDCommVersion() {
            return dIDCommVersion;
        }

        public void setDIDCommVersion(String dIDCommVersion) {
            this.dIDCommVersion = dIDCommVersion;
        }

        public String getPeerDIDInitialState() {
            return peerDIDInitialState;
        }

        public void setPeerDIDInitialState(String peerDIDInitialState) {
            this.peerDIDInitialState = peerDIDInitialState;
        }

    }

}