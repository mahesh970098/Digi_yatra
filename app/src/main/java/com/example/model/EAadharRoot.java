package com.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Certificate", strict = false)
public class EAadharRoot
{
    @Element(name = "CertificateData")
    private CertificateData certificateData;

    public CertificateData getCertificateData() {
        return certificateData;
    }

    public static class Poi {
        @Attribute(name = "dob")
        private String dob;
        @Attribute(name = "gender")
        private String gender;
        @Attribute(name = "name")
        private String name;

        public String getDob() {
            return dob;
        }

        public String getGender() {
            return gender;
        }

        public String getName() {
            return name;
        }
    }

    public static class Poa {
        @Attribute(name = "co")
        private String co;
        @Attribute(name = "country")
        private String country;
        @Attribute(name = "dist")
        private String dist;
        @Attribute(name = "house")
        private String house;
        @Attribute(name = "pc")
        private int pc;
        @Attribute(name = "state")
        private String state;
        @Attribute(name = "street")
        private String street;
        @Attribute(name = "vtc")
        private String vtc;

        public String getCo() {
            return co;
        }

        public String getCountry() {
            return country;
        }

        public String getDist() {
            return dist;
        }

        public String getHouse() {
            return house;
        }

        public int getPc() {
            return pc;
        }

        public String getState() {
            return state;
        }

        public String getStreet() {
            return street;
        }

        public String getVtc() {
            return vtc;
        }
    }

    public static class LData {
        @Attribute(name = "co")
        private String co;
        @Attribute(name = "country")
        private String country;
        @Attribute(name = "dist")
        private String dist;
        @Attribute(name = "house")
        private String house;
        @Attribute(name = "lang")
        private int lang;
        @Attribute(name = "name")
        private String name;
        @Attribute(name = "pc")
        private int pc;
        @Attribute(name = "state")
        private String state;
        @Attribute(name = "street")
        private String street;
        @Attribute(name = "vtc")
        private String vtc;

        public String getCo() {
            return co;
        }

        public String getCountry() {
            return country;
        }

        public String getDist() {
            return dist;
        }

        public String getHouse() {
            return house;
        }

        public int getLang() {
            return lang;
        }

        public String getName() {
            return name;
        }

        public int getPc() {
            return pc;
        }

        public String getState() {
            return state;
        }

        public String getStreet() {
            return street;
        }

        public String getVtc() {
            return vtc;
        }
    }

    public static class UidData {
        @Element(name = "Poi")
        private Poi Poi;
        @Element(name = "Poa")
        private Poa Poa;
        @Element(name = "LData")
        private LData LData;
        @Element(name = "Pht")
        private String Pht;
        @Attribute(name = "tkn")
        private String tkn;
        @Attribute(name = "uid")
        private String uid;


        public Poi getPoi() {
            return Poi;
        }

        public Poa getPoa() {
            return Poa;
        }

        public LData getLData() {
            return LData;
        }

        public String getPht() {
            return Pht;
        }

        public String getTkn() {
            return tkn;
        }

        public String getUid() {
            return uid;
        }
    }

    public static class KycRes {
        @Element(name = "UidData")
        private UidData UidData;
        @Attribute(name = "code")
        private String code;
        @Attribute(name = "ret")
        private String ret;
        @Attribute(name = "ts")
        private String ts;
        @Attribute(name = "ttl")
        private String ttl;
        @Attribute(name = "txn")
        private String txn;

        public UidData getUidData() {
            return UidData;
        }

        public String getCode() {
            return code;
        }

        public String getRet() {
            return ret;
        }

        public String getTs() {
            return ts;
        }

        public String getTtl() {
            return ttl;
        }

        public String getTxn() {
            return txn;
        }

    }

    public static class CertificateData {
        private KycRes KycRes;

        public KycRes getKycRes() {
            return KycRes;
        }
    }


}

