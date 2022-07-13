package space.gavinklfong.demo.streamapi.dto;


import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;

import java.util.Set;
import java.util.stream.Collectors;

public class OrderDTO {
    private String status;
    private Customer customer;
    Set<ProductDTO> products;
    public OrderDTO(String status, Customer customer, Set<ProductDTO> products)
    {
        this.status = status;
        this.customer = customer;
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    public static OrderDTO orderToDTO(Order order)
    {
        return new OrderDTO(order.getStatus(), order.getCustomer(),
                order.getProducts().stream()
                .map(ProductDTO::productToDTO)
                .collect(Collectors.toSet()));
    }
}
