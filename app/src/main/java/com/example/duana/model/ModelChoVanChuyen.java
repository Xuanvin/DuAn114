package com.example.duana.model;

public class ModelChoVanChuyen {
    private  int id;
    private int id_cartuser;
    private  String name;
    private  int price;
    private int number1;
    private int prinumber2;
    private  String img;
    private int price2;

    public ModelChoVanChuyen(int id,int id_cartuser, String name,  int price, int number1, int prinumber2, String img, int price2) {
        this.id = id;
        this.id_cartuser=id_cartuser;
        this.name = name;
        this.price = price;
        this.number1 = number1;
        this.prinumber2 = prinumber2;
        this.img = img;
        this.price2 = price2;
    }

    public int getId_cartuser() {
        return id_cartuser;
    }

    public void setId_cartuser(int id_cartuser) {
        this.id_cartuser = id_cartuser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getPrinumber2() {
        return prinumber2;
    }

    public void setPrinumber2(int prinumber2) {
        this.prinumber2 = prinumber2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice2() {
        return price2;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }
}
