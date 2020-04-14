package com.api.merryba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.merryba.Model.Order;
import com.api.merryba.Model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
	public List<Order> findAll();
	
	public Order findByorderid(Long orderid);

}