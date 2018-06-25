package com.example.macstudio.photoappjava.networking.models;

public class PhotoImages {
    private ImageSpecs standard_resolution;
    private ImageSpecs thumbnail;
    private ImageSpecs low_resolution;

    public ImageSpecs getStandard_resolution() {
        return standard_resolution;
    }

    public ImageSpecs getThumbnail() {
        return thumbnail;
    }

    public ImageSpecs getLow_resolution() {
        return low_resolution;
    }
}
