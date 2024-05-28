package com.alekseyz.testtask.crudapp.mapper;

import com.alekseyz.testtask.crudapp.dto.ProductDto;
import com.alekseyz.testtask.crudapp.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class SaveEditProductMapper implements Mapper<ProductDto, Product> {
    @Override
    public Product map(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .articul(productDto.getArticul())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .type(productDto.getType())
                .price(productDto.getPrice())
                .count(productDto.getCount())
                .build();
    }

    public Product map(ProductDto productDto, Product product) {
        product.setId(productDto.getId());
        product.setArticul(productDto.getArticul());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setType(productDto.getType());
        product.setPrice(productDto.getPrice());
        product.setCount(productDto.getCount());
        return product;
    }

}
