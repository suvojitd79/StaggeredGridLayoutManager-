package com.example.StaggeredGridLayoutManager;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("urls")
    private ImageModel imageModel;

    @SerializedName("alt_description")
    private String alt_description;

    public void setAlt_description(String alt_description) {
        this.alt_description = alt_description;
    }

    public String getAlt_description() {
        return alt_description;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }
}
