package com.example.customadaptor;

/**
 * Created by Genjitsu on 20/03/2020.
 */

public class ExampleItem {

    String photoLink;
    String videoLink;
    String objText;

    //Constructor olusturuyoruz
    public ExampleItem(String photoLink, String videoLink, String objText) {
        this.photoLink = photoLink;
        this.videoLink = videoLink;
        this.objText = objText;
    }

    // getter setter olusturuyoruz
    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getObjText() {
        return objText;
    }

    public void setObjText(String objText) {
        this.objText = objText;
    }
}
