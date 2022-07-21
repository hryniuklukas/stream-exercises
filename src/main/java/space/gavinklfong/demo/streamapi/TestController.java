package space.gavinklfong.demo.streamapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.gavinklfong.demo.streamapi.dto.OrderDTO;
import space.gavinklfong.demo.streamapi.dto.ProductDTO;
import space.gavinklfong.demo.streamapi.models.Customer;
import space.gavinklfong.demo.streamapi.models.Order;
import space.gavinklfong.demo.streamapi.models.Product;
import space.gavinklfong.demo.streamapi.repos.CustomerRepo;
import space.gavinklfong.demo.streamapi.repos.ProductRepo;
import space.gavinklfong.demo.streamapi.service.ServiceE1;

import java.util.*;

@RestController
public class TestController {
  private final CustomerRepo customerRepo;
  private final ServiceE1 serviceE1;

  @Autowired
  public TestController(CustomerRepo customerRepo, ServiceE1 serviceE1) {
    this.customerRepo = customerRepo;
    this.serviceE1 = serviceE1;
  }

  @GetMapping("/test")
  public String hello() {
    return "Hello world";
  }

  @GetMapping("/order")
  public Customer customer() {
    return customerRepo.findById(1l).get();
  }

  @GetMapping("/ex1")
  public List<ProductDTO> productList() {
    return serviceE1.getBooksWithPriceOver100();
  }

  @GetMapping("/ex2")
  public List<OrderDTO> ordersList() {
    return serviceE1.getOrdersWithBabies();
  }

  @GetMapping("/ex3")
  public List<ProductDTO> toysList() {
    return serviceE1.getToysAndDiscount10();
  }

  @GetMapping("/ex4")
  public List<ProductDTO> distinctList() {
    return serviceE1.getProductsOrderedByTier2Between010221and01042021();
  }

  @GetMapping("/ex5")
  public Optional<ProductDTO> cheapestBook() {
    return serviceE1.getCheapestBook();
  }

  @GetMapping("/ex6")
  public List<OrderDTO> threeMostRecentOrders() {
    return serviceE1.getThreeMostRecentOrders();
  }

  @GetMapping("/ex7")
  public List<ProductDTO> getProductsFromOrdersOn15March() {
    return serviceE1.getProductsFromOrdersFromMarch15();
  }

  @GetMapping("/ex8")
  public Double getSumOfFebruaryOrders() {
    return serviceE1.getSumOfOrdersFebruary();
  }

  @GetMapping("/ex9")
  public Double getAveragePaymentMarch14() {
    return serviceE1.getAveragePaymentOnMarch15();
  }

  @GetMapping("/ex10")
  public List<Double> getBooksStatistics() {
    DoubleSummaryStatistics stat = serviceE1.getBooksStatistics();
    List<Double> returnList = new ArrayList<>();
    returnList.add((double) stat.getCount());
    returnList.add(stat.getAverage());
    returnList.add(stat.getMax());
    returnList.add(stat.getMin());
    returnList.add(stat.getSum());
    return returnList;
  }

  @GetMapping("/ex11")
  public Map<Long, Integer> getNumberOfProductsOnOrders() {
    return serviceE1.getCountOfProductsOnOrders();
  }

  @GetMapping("/ex12")
  public Map<Customer, List<Order>> getOrdersGroupedByCustomers() {
    return serviceE1.groupOrdersByCustomers();
  }

  @GetMapping("/ex13")
  public Map<OrderDTO, Double> getOrdersWithTheirSum() {
    return serviceE1.getOrdersListWithTheirSum();
  }
}
