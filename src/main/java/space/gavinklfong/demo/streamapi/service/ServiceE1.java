package space.gavinklfong.demo.streamapi.service;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import space.gavinklfong.demo.streamapi.dto.OrderDTO;
import space.gavinklfong.demo.streamapi.dto.ProductDTO;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.OrderRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

  public List<ProductDTO> getProductsOrderedByTier2Between010221and01042021()
  {
    return orderRepo.findAll().stream()
            .filter(order -> order.getCustomer().getTier()==2)
            .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021,2,1)) >=0)
            .filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021,4,1))<=0)
            .flatMap(order -> order.getProducts().stream())
            .distinct()
            .map(ProductDTO::productToDTO)
            .collect(Collectors.toList());
  }
  public Optional<ProductDTO> getCheapestBook()
  {
    return productRepo.findAll().stream()
            .filter(product -> product.getCategory().equalsIgnoreCase("Books"))
            .min(Comparator.comparing(Product::getPrice))
            .map(ProductDTO::productToDTO);

  }
  public List<OrderDTO> getThreeMostRecentOrders()
  {
    return orderRepo.findAll().stream()
            .sorted(Comparator.comparing(Order::getOrderDate).reversed())
            .limit(3)
            .map(OrderDTO::orderToDTO)
            .collect(Collectors.toList());
  }
  public List<ProductDTO> getProductsFromOrdersFromMarch15()
  {
    return orderRepo.findAll().stream()
            .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021,3,15)))
            .peek(System.out::println)
            .flatMap(order -> order.getProducts().stream())
            .distinct()
            .map(ProductDTO::productToDTO)
            .collect(Collectors.toList());
  }
}
