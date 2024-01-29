package com.cybersoft.cozastore.payload.request;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class ProductResquest {

    @NotNull(message = "File không được phép rỗng")
    private MultipartFile file;

    @NotBlank(message = "Tên không được rỗng")
    private String name;

    @DecimalMin(value="0.1")
    private double price;
    private String desc;
    private int quantity;
    private int sizeId;
    private int colorId;
    private int categoryId;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
