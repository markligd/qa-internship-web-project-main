package com.test.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ImageItemDTO {
    public ImageItemDTO(String author, Set<Long> categoryIds, String description, String pathToImage, double price, int rating, List<String> tags, String title) {
        this.author = author;
        this.categoryIds = categoryIds;
        this.description = description;
        this.pathToImage = pathToImage;
        this.price = price;
        this.rating = rating;
        this.tags = tags;
        this.title = title;
    }

    public ImageItemDTO() {

    }


    private String author;
    private Set<Long> categoryIds;
    private String description;
    private Long id;
    private String pathToImage;
    private double price;
    private int rating;
    private List<String> tags;
    private String title;

}
