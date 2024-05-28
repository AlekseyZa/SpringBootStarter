package com.alekseyz.testtask.crudapp.entity;


import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product{

    private Long id;
    private Long articul;
    private String name;
    private String description;
    private String type;
    private BigDecimal price;
    private Long count;


}
