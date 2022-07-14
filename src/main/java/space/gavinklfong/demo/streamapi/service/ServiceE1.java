package space.gavinklfong.demo.streamapi.service;

import org.springframework.stereotype.Service;
import space.gavinklfong.demo.streamapi.dto.OrderDTO;
import space.gavinklfong.demo.streamapi.dto.ProductDTO;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceE1 {
  ProductRepo productRepo;
  OrderRepo orderRepo;

  public ServiceE1(ProductRepo productRepo, OrderRepo orderRepo) {
    this.productRepo = productRepo;
    this.orderRepo = orderRepo;
  }

  public List<ProductDTO> getBooksWithPriceOver100() {
    return productRepo.findAll().stream()
        .filter(p -> p.getCategory().equals("Books"))
        .filter(p -> p.getPrice() > 100)
        .map(ProductDTO::productToDTO)
        .collect(Collectors.toList());
  }
  public List<OrderDTO> getOrdersWithBabies()
  {
    return orderRepo.findAll().stream()
        .filter(
            order ->
                order.getProducts().stream()
                    .anyMatch(product -> product.getCategory().equalsIgnoreCase("Baby")))
            .map(OrderDTO::orderToDTO)
            .collect(Collectors.toList());
  }
  public List<ProductDTO> getToysAndDiscount10()
  {
    return productRepo.findAll().stream()
            .filter(product -> product.getCategory().equalsIgnoreCase("Toys"))
            .map(toy -> toy.withPrice(toy.getPrice()*0.9))
            .map(ProductDTO::productToDTO)
            .collect(Collectors.toList());
  }
}
