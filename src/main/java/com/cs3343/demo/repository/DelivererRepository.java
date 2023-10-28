package com.cs3343.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cs3343.demo.entity.DelivererEntity;
public interface DelivererRepository extends JpaRepository<DelivererEntity, Long> {

}
