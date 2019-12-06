package com.example.duana.model;

public class SanPham {
    public int Laptop_id;
    public String KhaNangLuuTru;
    public String Name_Product;
    public int Price_product;
    public String Characteristics;
    public String diaChi;
    public float Ratingbar;
    public String Img1;
    public String Img2;
    public String Img3;
    public String Comment;
    public String name1_information;
    public String name2_information;
    public String name3_information;
    public String name4_information;
    public String name5_information;
    public String Information;
    public String ram;
    public String cpu;
    public String bonhotrong;
    public String dungluongping;
    public int Id_Category;

    public SanPham() {
    }

    public SanPham(int laptop_id, int Id_Category, String KhaNangLuuTru,
                   String name_Product, int price_product, String characteristics,
                   String diaChi, float ratingbar, String img1, String img2, String img3,
                   String comment, String name1_information, String name2_information,String name3_information,String name4_information,String name5_information,
                   String Information, String ram, String cpu, String bonhotrong, String dungluongping) {

        this.KhaNangLuuTru = KhaNangLuuTru;
        this.Laptop_id = laptop_id;
        this.Id_Category = Id_Category;
        this.Name_Product = name_Product;
        this.Price_product = price_product;
        this.Characteristics = characteristics;
        this.diaChi = diaChi;
        this.Ratingbar = ratingbar;
        this.Img1 = img1;
        this.Img2 = img2;
        this.Img3 = img3;
        this.Comment = comment;
        this.name1_information = name1_information;
        this.name2_information = name2_information;
        this.name3_information = name3_information;
        this.name4_information = name4_information;
        this.name5_information = name5_information;
        this.Information = Information;
        this.ram = ram;
        this.cpu = cpu;
        this.bonhotrong = bonhotrong;
        this.dungluongping = dungluongping;

    }

    public String getRam() {
        return ram;
    }

    public String getName3_information() {
        return name3_information;
    }

    public void setName3_information(String name3_information) {
        this.name3_information = name3_information;
    }

    public String getName4_information() {
        return name4_information;
    }

    public void setName4_information(String name4_information) {
        this.name4_information = name4_information;
    }

    public String getName5_information() {
        return name5_information;
    }

    public void setName5_information(String name5_information) {
        this.name5_information = name5_information;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getBonhotrong() {
        return bonhotrong;
    }

    public void setBonhotrong(String bonhotrong) {
        this.bonhotrong = bonhotrong;
    }

    public String getDungluongping() {
        return dungluongping;
    }

    public void setDungluongping(String dungluongping) {
        this.dungluongping = dungluongping;
    }

    public int getId_Category() {
        return Id_Category;
    }

    public void setId_Category(int id_Category) {
        Id_Category = id_Category;
    }

    public String getKhaNangLuuTru() {
        return KhaNangLuuTru;
    }

    public void setKhaNangLuuTru(String khaNangLuuTru) {
        KhaNangLuuTru = khaNangLuuTru;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getName1_information() {
        return name1_information;
    }

    public void setName1_information(String name1_information) {
        this.name1_information = name1_information;
    }

    public String getName2_information() {
        return name2_information;
    }

    public void setName2_information(String name2_information) {
        this.name2_information = name2_information;
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

    public int getPrice_product() {
        return Price_product;
    }

    public void setPrice_product(int price_product) {
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
