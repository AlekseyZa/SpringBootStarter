package com.alekseyz.testtask.crudapp.mapper;

import com.alekseyz.testtask.crudapp.dto.ProductDto;
import com.alekseyz.testtask.crudapp.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ReadProductMapper implements Mapper<Product, ProductDto> {
    @Override
    public ProductDto map(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .articul(product.getArticul())
                .name(product.getName())
                .description(product.getDescription())
                .type(product.getType())
                .price(product.getPrice())
                .count(product.getCount())
                .build();
    }

}
