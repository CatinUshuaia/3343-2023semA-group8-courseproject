package com.cs3343.demo.impls;

import java.util.List;
import java.util.Optional;

import com.cs3343.demo.repository.DelivererRepository;
import com.cs3343.demo.services.DelivererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cs3343.demo.entity.DelivererEntity;
import com.cs3343.demo.repository.DelivererRepository;
import com.cs3343.demo.services.DelivererService;
@Service
@Component
public class DelivererImpl implements DelivererService {
    @Autowired
    private DelivererRepository delivererRepository;

    @Override
    public List<DelivererEntity> findAll() {
        return delivererRepository.findAll();
    }

    @Override
    public void save(DelivererEntity delivererEntity) {
        delivererRepository.save(delivererEntity);
    }

    @Override
    public Optional<DelivererEntity> findById(Long id) {
        return delivererRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        delivererRepository.deleteById(id);
    }


    }

