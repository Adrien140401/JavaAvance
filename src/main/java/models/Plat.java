package models;

public class Plat {
    private String image;
    private String plat;
    private String description;
    private Integer prix;
    private Integer stock;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Plat(String image, String plat, String description, Integer prix, Integer stock) {
        this.image = image;
        this.plat = plat;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }
}
