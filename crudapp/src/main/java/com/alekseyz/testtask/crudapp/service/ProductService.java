package com.alekseyz.testtask.crudapp.service;

import com.alekseyz.testtask.crudapp.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAllProduct();

    ProductDto findById(long id);

    ProductDto saveProduct(ProductDto productDto);

    ProductDto updateProduct(long id, ProductDto productDto);

    boolean deleteProduct(long id);


}
