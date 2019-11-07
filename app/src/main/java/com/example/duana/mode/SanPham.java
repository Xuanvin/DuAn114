package com.example.duana.mode;

public class SanPham {
    private int Laptop_id;
  private   String Name_Product;
   private String Price_product;
   private String Characteristics;
   private String diaChi;
   private  float Ratingbar;
   private String Img1;
    private String Img2;
    private String Img3;
   private String Comment;

    public SanPham(int laptop_id, String name_Product, String price_product, String characteristics, String diaChi, float ratingbar, String img1, String img2, String img3, String comment) {
        this.Laptop_id = laptop_id;
        this.Name_Product = name_Product;
        this. Price_product = price_product;
        this.Characteristics = characteristics;
        this.diaChi = diaChi;
        this.Ratingbar = ratingbar;
        this.Img1 = img1;
        this.Img2 = img2;
        this.Img3 = img3;
        this.Comment = comment;
    }

    public int getLaptop_id() {
        return Laptop_id;
    }

    public void setLaptop_id(int laptop_id) {
        Laptop_id = laptop_id;
    }

    public String getName_Product() {
        return Name_Product;
    }

    public void setName_Product(String name_Product) {
        Name_Product = name_Product;
    }

    public String getPrice_product() {
        return Price_product;
    }

    public void setPrice_product(String price_product) {
        Price_product = price_product;
    }

    public String getCharacteristics() {
        return Characteristics;
    }

    public void setCharacteristics(String characteristics) {
        Characteristics = characteristics;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public float getRatingbar() {
        return Ratingbar;
    }

    public void setRatingbar(float ratingbar) {
        Ratingbar = ratingbar;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }

    public String getImg2() {
        return Img2;
    }

    public void setImg2(String img2) {
        Img2 = img2;
    }

    public String getImg3() {
        return Img3;
    }

    public void setImg3(String img3) {
        Img3 = img3;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "Laptop_id=" + Laptop_id +
                ", Name_Product='" + Name_Product + '\'' +
                ", Price_product='" + Price_product + '\'' +
                ", Characteristics='" + Characteristics + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", Ratingbar=" + Ratingbar +
                ", Img1='" + Img1 + '\'' +
                ", Img2='" + Img2 + '\'' +
                ", Img3='" + Img3 + '\'' +
                ", Comment='" + Comment + '\'' +
                '}';
    }
}
