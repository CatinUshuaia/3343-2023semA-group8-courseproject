package com.cs3343.demo.services;

import java.util.List;
import java.util.Optional;

import com.cs3343.demo.entity.CookEntity;
import org.springframework.stereotype.Service;

@Service
public interface CookService {
    List<CookEntity> findAll();

    void save(CookEntity cookEntity);

    Optional<CookEntity> findById(Long id);

    void delete(Long id);
}
