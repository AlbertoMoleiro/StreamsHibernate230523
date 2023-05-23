package com.softtek.streamshibernate230523.service;

import com.softtek.streamshibernate230523.model.Customer;
import com.softtek.streamshibernate230523.model.Order;
import com.softtek.streamshibernate230523.repository.ICustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private ICustomersRepo orderRepo;

    public List<Customer> getAll(){
        return orderRepo.findAll();
    }
}
