package com.softtek.streamshibernate230523.repository;

import com.softtek.streamshibernate230523.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Order, Short> {
}
