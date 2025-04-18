package br.com.hexagonal_architecture.domain.model;

public class Pizza {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private int id;
    private String flavor;
    private String border;
    private double price;
    private String img;

    public Pizza(int id, String flavor, String border, double price, String img) {
        this.id = id;
        this.flavor = flavor;
        this.border = border;
        this.price = price;
        this.img = img;
    }

    public Pizza() {}

}
