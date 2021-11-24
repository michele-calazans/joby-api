package br.com.jobem.api.dtos.requests;

public class SaveImage {
    private String image;
    private String imageType;

    public SaveImage(String image, String imageType) {
        this.image = image;
        this.imageType = imageType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
