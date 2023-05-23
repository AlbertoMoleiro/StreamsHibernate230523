package com.softtek.streamshibernate230523.service;

import com.softtek.streamshibernate230523.model.Product;
import com.softtek.streamshibernate230523.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepo repo;

    @Override
    public List<Product> getAll(){
        return repo.findAll();
    }
}
