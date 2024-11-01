package com.nhomA.mockproject.dto;

import java.util.HashMap;
import java.util.Map;

public class ItemGHNDTO {
    private String name;
    private String code;
    private int quantity;
    private Long price;
    private int length;
    private int width;
    private int height;
    private int weight;
    private Map<String, String> category = new HashMap<>();

    public ItemGHNDTO(String name, String code, int quantity, Long price) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.price = price;
        this.length = 12;
        this.width = 12;
        this.height = 12;
        this.weight = 1200;
        this.category.put("level1", "điện thoại");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Map<String, String> getCategory() {
        return category;
    }

    public void setCategory(Map<String, String> category) {
        this.category = category;
    }
}
