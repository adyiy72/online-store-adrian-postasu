package com.onlinestore.services;

import com.onlinestore.entities.Address;
import com.onlinestore.entities.ShoppingCart;
import com.onlinestore.entities.User;
import com.onlinestore.exceptions.ResourceMissingInDatabase;
import com.onlinestore.exceptions.UnauthorizedException;
import com.onlinestore.repositories.AddressRepository;
import com.onlinestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService{

    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public AddressServiceImplementation(AddressRepository addressRepository, UserRepository userRepository
    , UserService userService) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }


    // TODO: check delete relation
    @Override
    public void delete(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            throw new ResourceMissingInDatabase(String.format("Address with id %d does not exist", id));
        }

        Address address = optionalAddress.get();
        User user = address.getUser();
        List<Address> userAddressList = user.getUserAddress();
        boolean addressFound = false;
        for (Address address1 : userAddressList) {
            if (address1.getId().equals(id)) {
                addressFound = true;
            }
        }
        if (addressFound) {
            User updated = user.removeAddress(address);
            userRepository.save(updated);
            address.removeUserLink();
            addressRepository.delete(address);
        } else {
            throw new UnauthorizedException("You are not authorized to modify this resource");
        }
    }

    @Override
    public Address update(Address address) {
        Optional<Address> addressInDatabase = addressRepository.findById(address.getId());
        if (addressInDatabase.isPresent()) {
            Address oldAddress = updateAddress(address, addressInDatabase);
            return addressRepository.save(oldAddress);
        }
        throw new ResourceMissingInDatabase(String.format("Address with id %d does not exist", address.getId()));
    }

    @Override
    public List<Address> getAddressesOfLoggedUser() {
        User user = userService.getLoggedUser();
        if (user.getUserAddress() != null) {
            return user.getUserAddress();
        } else {
            Address address = new Address();
            address.setUser(user);
            user.setUserAddress((List<Address>) address);
            userService.update(user);
        }
        return user.getUserAddress();
    }

    private Address updateAddress(Address newAddress, Optional<Address> addressInDatabase) {
        Address oldAddress = addressInDatabase.get();
        oldAddress.setCity(newAddress.getCity());
        oldAddress.setCounty(newAddress.getCounty());
        oldAddress.setNumber(newAddress.getNumber());
        oldAddress.setStreet(newAddress.getStreet());
        oldAddress.setZipcode(newAddress.getZipcode());
        return oldAddress;
    }
}
