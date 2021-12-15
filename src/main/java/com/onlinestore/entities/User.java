package com.onlinestore.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

/**
 * {
 *     "id" : 1,
 *     "name" : "elon musk",
 *     "address" : {
 *         "id" : 2,
 *         "county" : SV,
 *         "user" : {
 *             "id": 1,
 *             "name" : "elon musk",
 *             "address" : {
 *                 "id" : 2,
 *                 "county" : SV,
 *                 "user" : {
 *                     "id": 1,
 *                     "name" : "elon musk",
 *                     "address" : {
 *
 *                     }
 *                 }
 *             }
 *         }
 *     }
 * }
 */
@Entity(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Email
    @Column(unique = true)
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> userAddress = new ArrayList<>();

    @OneToOne
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart = new ShoppingCart();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(List<Address> userAddress) {
        this.userAddress = userAddress;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User removeAddress(Address addressToRemove) {
        addressToRemove.setUser(null);
        this.userAddress.remove(addressToRemove);
        return this;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
