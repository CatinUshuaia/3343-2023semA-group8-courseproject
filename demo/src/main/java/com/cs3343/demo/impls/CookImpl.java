package com.cs3343.demo.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cs3343.demo.entity.CookEntity;
import com.cs3343.demo.repository.CookRepository;
import com.cs3343.demo.services.CookService;

@Service
@Component
public class CookImpl implements CookService{

    @Autowired
    private CookRepository cookRepository;

    @Override
    public List<CookEntity> findAll() {
        return cookRepository.findAll();
    }

    @Override
    public void save(CookEntity cookEntity) {
        cookRepository.save(cookEntity);
    }

    @Override
    public Optional<CookEntity> findById(Long id) {
        return cookRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        cookRepository.deleteById(id);
    }
}
