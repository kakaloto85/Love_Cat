package com.example.myapplication;

public class User {
    private String id;
    private String phone;
    private String email;
    private Integer favorite;
    private Boolean hasSNS;
    private Gallery gallery;

    // Constructor
    public User(String id, String email, String phone, Integer favorite, Boolean hasSNS){
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.favorite = favorite;
        this.gallery = new Gallery(id);
        this.hasSNS = hasSNS;
    }

    // Getter
    public String getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhone(){
        return this.phone;
    }

    public Integer getFavorite(){
        return this.favorite;
    }

    public Boolean getHasSNS(){
        return this.hasSNS;
    }

    // Setter
    public void setHasSNS(Boolean hasSNS){
        this.hasSNS = hasSNS;
    }

}
