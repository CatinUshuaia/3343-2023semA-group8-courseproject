package com.cs3343.demo.services;

import com.cs3343.demo.entity.CookEntity;
import com.cs3343.demo.entity.DelivererEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DelivererService {
    List<DelivererEntity> findAll();

    void save(DelivererEntity delivererEntity);

    Optional<DelivererEntity> findById(Long id);

    void delete(Long id);
}
