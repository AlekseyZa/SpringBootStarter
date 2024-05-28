package com.alekseyz.testtask.crudapp.service.implementation;

import com.alekseyz.testtask.crudapp.dto.ProductDto;
import com.alekseyz.testtask.crudapp.entity.Product;
import com.alekseyz.testtask.crudapp.mapper.ReadProductMapper;
import com.alekseyz.testtask.crudapp.mapper.SaveEditProductMapper;
import com.alekseyz.testtask.crudapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImplementation implements ProductService {

    private final Map<Long, Product> productMap = new HashMap<>();
    private final SaveEditProductMapper saveEditProductMapper;
    private final ReadProductMapper readProductMapper;
    private long idSequence = 0;


    public List<ProductDto> findAllProduct() {
        List<Product> list = new ArrayList<>(productMap.values());
        return list.stream().map(readProductMapper::map).toList();
    }

    public ProductDto findById(long id) {
        return readProductMapper.map(productMap.get(id));
    }



    public ProductDto saveProduct(ProductDto productDto) {
        long id = getIdSequence();
        productDto.setId(id);
        productMap.put(id,saveEditProductMapper.map(productDto));
        return productDto;
    }


    public ProductDto updateProduct(long id, ProductDto productDto) {
        productMap.put(id,saveEditProductMapper.map(productDto));
        return productDto;
                    }


    public boolean deleteProduct(long id) {
        if (productMap.isEmpty()){
            return false;
        }
        productMap.remove(id);
        return true;
    }

    private long getIdSequence() {
        return ++idSequence ;
    }


}

