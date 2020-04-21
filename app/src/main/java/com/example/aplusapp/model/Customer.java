package com.example.aplusapp.model;

public class Customer {
    private int id;
    private String fristname;
    private  String lastname;
    private  String email;
    private String nic;
    private int phone;

    public Customer(String fristname, String nic) {
        this.id = id;
        this.fristname = fristname;
        this.nic = nic;
    }

        public int getid() { return id; }

        public void setId (int id){ this.id = id; }

        public String getfristname(){ return fristname; }

        public void setfristname (String fristname){ this.fristname = fristname; }

        public String getlastname () { return lastname; }

        public void setlastname (String lastname){ this.lastname = lastname; }

        public String email () { return email; }

        public void setemail (String email){ this.email = email; }

        public String getnic () { return nic; }

        public void setnic (String nic){ this.nic = nic; }

        public int getphone () { return phone; }

        public void setphone (int phone){ this.phone = phone; }
    }