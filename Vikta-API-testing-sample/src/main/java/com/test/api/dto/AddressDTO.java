package com.test.api.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private Long userId;
    private String cityName;
    private String addressNickname;
    private String postalCode;
    private String regionName;
    private String street;
    private String streetAdditional;
}
