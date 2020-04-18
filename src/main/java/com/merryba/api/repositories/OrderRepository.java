package com.merryba.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.merryba.api.Model.Order;
import com.merryba.api.Model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
	public List<Order> findAll();
	
	public Order findByorderid(Long orderid);

}