package com.softtek.streamshibernate230523.service;

import com.softtek.streamshibernate230523.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
}
