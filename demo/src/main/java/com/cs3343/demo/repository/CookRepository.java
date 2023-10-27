package com.cs3343.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs3343.demo.entity.CookEntity;

@Repository
public interface CookRepository extends JpaRepository<CookEntity, Long>{
}
