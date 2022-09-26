package com.test.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ImageItemDTO {
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
