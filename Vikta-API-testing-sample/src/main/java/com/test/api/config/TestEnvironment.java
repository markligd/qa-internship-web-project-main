package com.test.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public final class TestEnvironment {

    @Value("${baseURI}")
    private String baseURI;

    @Value("${port}")
    private int port;

    @Value("${basePath}")
    private String basePath;

    @Value("${categoryPath}")
    private String categoryPath;

    @Value("${categoriesPath}")
    private String categoriesPath;

    @Value("${categoriesSearchPath}")
    private String categoriesSearchPath;

    @Value("${addressPath}")
    private String addressPath;

    @Value("${addressesPath}")
    private String addressesPath;

    @Value("${addressesSearchPath}")
    private String addressesSearchPath;

    @Value("${imageItemPath}")
    private String imageItemPath;

    @Value("${imageItemLinkPath}")
    private String imageItemLinkPath;

    @Value("${imageItemListPath}")
    private String imageItemListPath;

    @Value("${imageItemSearchPath}")
    private String imageItemSearchPath;

    @Value("${imageItemAdvancedSearchPath}")
    private String imageItemAdvancedSearchPath;

    @Value("${imageItemUnlinkPath}")
    private String imageItemUnlinkPath;


}
