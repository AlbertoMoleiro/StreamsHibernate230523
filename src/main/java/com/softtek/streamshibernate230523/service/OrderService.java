package com.softtek.streamshibernate230523.service;

import com.softtek.streamshibernate230523.model.Order;
import com.softtek.streamshibernate230523.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderRepo orderRepo;

    public List<Order> getAll(){
        return orderRepo.findAll();
    }
}
