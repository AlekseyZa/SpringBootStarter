package com.alekseyz.testtask.crudapp.dto;

import lombok.*;

import java.math.BigDecimal;


@Data
@Builder
@Getter
@Setter
public class ProductDto {

    Long id;
    Long articul;
    String name;
    String description;
    String type;
    BigDecimal price;
    Long count;

}
