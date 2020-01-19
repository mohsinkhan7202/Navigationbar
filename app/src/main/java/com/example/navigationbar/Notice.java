package com.example.navigationbar;

public class Notice {
    private String name;
    private String imageUrl;


    public Notice() {

    }
    public Notice(String name,String imageUrl){

        this.name=name;
        this.imageUrl=imageUrl;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
