package com.onlinestore.controllers.dto;

import com.onlinestore.entities.Address;
import com.onlinestore.entities.User;
import com.onlinestore.services.UserService;

public class AddressDto {
    private Long id;
    private String county;
    private String city;
    private String street;
    private String zipcode;
    private int number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public static Address toAddress(AddressDto addressDto, UserService userService) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setZipcode(addressDto.getZipcode());
        address.setCounty(addressDto.getCounty());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        User user = userService.getLoggedUser();
        address.setUser(user);
        return address;
    }

    public static AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setCounty(address.getCounty());
        addressDto.setNumber(address.getNumber());
        addressDto.setZipcode(address.getZipcode());
        addressDto.setStreet(address.getStreet());
        return addressDto;
    }
}
