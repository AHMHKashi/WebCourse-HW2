package edu.webclass.restapi.Product.Management.System.controllers;

import edu.webclass.restapi.Product.Management.System.models.dto.ProductDto;
import edu.webclass.restapi.Product.Management.System.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<ProductDto> listAllProducts() {
        return productService.findAllProducts().stream().map(product -> new ProductDto(product)).toList();
    }

    @PostMapping("/add")
    public boolean addProduct(@RequestHeader("name") String title, @RequestHeader String brand, @RequestHeader int price) {
        return productService.addProduct(title, brand, price);
    }

    @GetMapping("/{pid}")
    public ProductDto getProductByID(@PathVariable String pid) {
        var product = productService.getProductByID(pid);
        if (product.isPresent()) {
            return new ProductDto(product.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }
    }
}
