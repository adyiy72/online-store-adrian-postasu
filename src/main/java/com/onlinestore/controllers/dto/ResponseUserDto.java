package com.onlinestore.controllers.dto;

import com.onlinestore.entities.Address;
import com.onlinestore.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ResponseUserDto {

    private String name;
    private String email;
    private List<AddressDto> addressDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AddressDto> getAddressDtos() {
        return addressDtos;
    }

    public void setAddressDtos(List<AddressDto> addressDtos) {
        this.addressDtos = addressDtos;
    }

    public static ResponseUserDto toResponseUser(User user){
        ResponseUserDto response = new ResponseUserDto();
        response.setName(String.format("%s %s", user.getFirstName(), user.getLastName()));
        response.setEmail(user.getEmail());

        List<Address> addresses  = user.getUserAddress();
        List<AddressDto> addressDtoList = new ArrayList<>();

        // construim addressDto bazat pe address
        addresses.forEach(el -> addressDtoList.add(AddressDto.toAddressDto(el)));

        response.setAddressDtos(addressDtoList);
        return response;
    }
}
