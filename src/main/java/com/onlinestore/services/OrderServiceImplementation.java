package com.onlinestore.services;

import com.onlinestore.entities.*;
import com.onlinestore.exceptions.EmptyShoppingCartException;
import com.onlinestore.exceptions.InsufficientStockException;
import com.onlinestore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService{

    private OrderRepository orderRepository;
    private UserService userService;
    private ProductService productService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository,
                                      UserService userService,
                                      ProductService productService,
                                      ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order save() {
        // validam stock produse - ok
        // salvam order
        // salvam stock updatat
        // golire cos cumparaturi
        ShoppingCart shoppingCart = userService.getLoggedUser().getShoppingCart();
        validateShoppingCart(shoppingCart);
        isStockEnough(shoppingCart.getProductsInCart());
        return processOrder(shoppingCart);
    }

    private void validateShoppingCart(ShoppingCart shoppingCart){
        if (shoppingCart.getProductsInCart().isEmpty()) {
            throw new EmptyShoppingCartException("Your shopping cart is empty");
        }
    }

    private Order processOrder(ShoppingCart shoppingCart) {
        Order order = buildOrder(shoppingCart);
        orderRepository.save(order);
        updateProductStock(shoppingCart);
        shoppingCartService.resetShoppingCart();
        return order;
    }

    private void updateProductStock(ShoppingCart shoppingCart) {
        shoppingCart
                .getProductsInCart()
                .forEach(productQuantity -> {
                    productQuantity.getProduct().setStock(productQuantity.getProduct().getStock()
                            - productQuantity.getQuantity());
                    productService.save(productQuantity.getProduct());
                });
    }

    private Order buildOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        List<ProductOrderCopy> productOrderCopies =  shoppingCart
                .getProductsInCart()
                .stream()
                .map(productQuantity -> toProductOrderCopy(productQuantity.getProduct()))
                .collect(Collectors.toList());
        order.setProductOrderCopyList(productOrderCopies);

        Double total = shoppingCart
                .getProductsInCart()
                .stream()
                .map(productQuantity -> productQuantity.getProduct().getPrice())
                .reduce(0D, (a,b) -> a+b);

        order.setTotal(total);
        order.setUser(userService.getLoggedUser());
        return order;
    }

    public boolean isStockEnough(List<ProductQuantity> productList) {
        StringBuilder message = new StringBuilder();
        boolean isValid = true;
        for (ProductQuantity productQuantity : productList) {
            if (productQuantity.getProduct().getStock() - productQuantity.getQuantity() < 0) {
                isValid = false;
                message.append(String.format("Product id = %d has insufficient stock %n", productQuantity.getId()));
            }
        }
        if (isValid) {
            return true;
        } else {
            throw new InsufficientStockException(message.toString());
        }
    }

    @Override
    public List<Order> getLoggedUserOrders() {
        User user = userService.getLoggedUser();

        return orderRepository.findAllByUser(user);
    }

    // prod id = 50
    // reference id = #50
    private ProductOrderCopy toProductOrderCopy(Product product) {
        ProductOrderCopy productOrderCopy = new ProductOrderCopy();
        productOrderCopy.setName(product.getName());
        productOrderCopy.setPrice(product.getPrice());
        productOrderCopy.setReference(String.format("#%d", product.getId())); // de ex daca id-ul produsului e 50, reference o sa fie #50
        return productOrderCopy;
    }
}
