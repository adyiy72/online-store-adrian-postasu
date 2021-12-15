package com.onlinestore.services;

import com.onlinestore.entities.Address;
import com.onlinestore.entities.ShoppingCart;

import java.util.List;

public interface AddressService {

    Address save(Address address);
    void delete(Long id);
    Address update(Address address);
    List<Address> getAddressesOfLoggedUser();

}
