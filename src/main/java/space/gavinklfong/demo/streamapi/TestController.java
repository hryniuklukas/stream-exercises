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

import java.util.List;

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
    public String hello()
    {
        return "Hello world";
    }

    @GetMapping("/order")
    public Customer customer()
    {
        return customerRepo.findById(1l).get();
    }
    @GetMapping("/ex1")
    public List<ProductDTO> productList()
    {
        return serviceE1.getBooksWithPriceOver100();
    }
    @GetMapping("/ex2")
    public List<OrderDTO> ordersList()
    {
        return serviceE1.getOrdersWithBabies();
    }
    @GetMapping("/ex3")
    public List<ProductDTO> toysList(){return serviceE1.getToysAndDiscount10();}
    @GetMapping("/ex4")
    public List<ProductDTO> distinctList(){return serviceE1.getProductsOrderedByTier2Between010221and01042021();}
}
