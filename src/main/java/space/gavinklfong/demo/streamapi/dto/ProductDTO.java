package space.gavinklfong.demo.streamapi.dto;

import lombok.With;
import space.gavinklfong.demo.streamapi.models.Product;

public class ProductDTO {
    private String name;
    private String category;
    private Double price;
    public ProductDTO(String name, String category, Double price)
    {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public static ProductDTO productToDTO(Product product)
    {
        return new ProductDTO(product.getName(),product.getCategory(), product.getPrice());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
