package com.alekseyz.testtask.crudapp.controller;



import com.alekseyz.testtask.crudapp.dto.ProductDto;
import com.alekseyz.testtask.crudapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.findAllProduct();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addNewProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") long id) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        if (!productService.deleteProduct(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
