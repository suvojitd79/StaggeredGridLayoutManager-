package com.example.StaggeredGridLayoutManager;

import com.google.gson.annotations.SerializedName;

public class ImageModel {

    @SerializedName("raw")
    private String raw;
    @SerializedName("full")
    private String full;
    @SerializedName("small")
    private String small;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRaw() {
        return raw;
    }

    public String getFull() {
        return full;
    }

}

