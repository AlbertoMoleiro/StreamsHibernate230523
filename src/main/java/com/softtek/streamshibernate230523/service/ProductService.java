package com.softtek.streamshibernate230523.service;

import com.softtek.streamshibernate230523.dto.PromedioProveedorDTO;
import com.softtek.streamshibernate230523.dto.PromedioCategoriaDTO;
import com.softtek.streamshibernate230523.dto.SumCategoriaDTO;
import com.softtek.streamshibernate230523.dto.SumaProveedorDTO;
import com.softtek.streamshibernate230523.model.Product;
import com.softtek.streamshibernate230523.repository.IProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService extends CRUDImpl<Product,Short> implements IProductService{
    @Autowired
    private IProductRepo repo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public IProductRepo getRepo() {
        return repo;
    }

    @Override
    public List<Product> getAll(){
        return repo.findAll();
    }

    @Override
    public List<PromedioProveedorDTO> getPromedioPrecios(){
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(Product::getSupplierId, Collectors.averagingDouble(Product::getUnitPrice)))
                .entrySet().stream().map(entry -> new PromedioProveedorDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SumaProveedorDTO> getSumaStock(){
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(Product::getSupplierId, Collectors.summingInt(Product::getUnitsInStock)))
                .entrySet().stream().map(entry -> new SumaProveedorDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PromedioCategoriaDTO> getPromedioCategory(){
        return repo.findAll().stream()
                .filter(product -> product.getDiscontinued() == 0)
                .collect(Collectors.groupingBy(Product::getCategoryId, Collectors.averagingDouble(Product::getUnitPrice)))
                .entrySet().stream().map(entry -> new PromedioCategoriaDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SumCategoriaDTO> getSumaCategory(){
        return repo.findAll().stream().filter(product -> product.getUnitPrice() > 10 && product.getUnitPrice() < 60)
                .collect(Collectors.groupingBy(Product::getCategoryId, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 12)
                .map(entry -> new SumCategoriaDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
