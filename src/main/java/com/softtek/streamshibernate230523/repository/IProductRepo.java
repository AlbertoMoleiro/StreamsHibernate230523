package com.softtek.streamshibernate230523.repository;

import com.softtek.streamshibernate230523.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<Product, Short>{

}
