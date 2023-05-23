package com.softtek.streamshibernate230523.repository;

import com.softtek.streamshibernate230523.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomersRepo extends JpaRepository<Customer, String>{
}
